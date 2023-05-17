@[toc]
# Linux操作系统实践——Samba服务器搭建

<font color="red" size=5>**仅供参考，谢绝抄袭**</font>

## 一、实验目的

熟练掌握 Linux 操作系统的使用，掌握 Linux 的各项系统管理功能，掌握 Linux 下各类网络服务的安装、配置以及使用，并能用 shell 脚本实现简单的管理 任务。

## 二、实验内容

### ②  学生教师服务

根据所学内容，使用一种或多种服务（如 ftp、samba、Http 等）搭建一台服务器，支持多用户访问，并能完成下述功能：

1）学生用户能实现学生作业的上传，学生以“姓名+学号”命名作业。学生 能看到作业列表，但是不能下载其他用户的作业。

2）在指定的交作业截止时间到时，编写脚本自动统计交作业的学生名单和 人数，生成文档，供教师查看。

3）教师用户能够查看学生提交的作业，在生成的交作业名单录入成绩， 并发布成绩，供学生查看。

4）对于指定格式的作业，编写脚本自动批改作业并在交作业名单中记录成绩。（注：格式可以自己指定，也可做成模板供学生下载使用）

5）教师可以提供课件和参考资料供学生下载。

## 三、   题目分析及基本设计过程分析

### ② 学生教师服务

#### 1)   整体思路

对于这道题，我在RedHat系统上使用了Samba搭建服务器，能使学生和老师在Windows客户机上上传和下载文件，老师能在Linux系统上启动自动批改脚本批改指定格式的作业。

关于Samba服务器的配置，会在第四部分进行说明。

#### 2)  系统用户和组分配设计说明

系统中一共有三个组os，student和teacher

①  os组中有所有的学生和教师：stu1，stu2，stu3，stu4以及teacher，服务于课程。

②  student组中有所有的学生：stu1，stu2，stu3以及stu4，服务于学生。

③  teacher组中有所有的教师：teacher，服务于教师

![\[外链图片转存失败,源站可能有防盗链机制,建议将图片保存下来直接上传(img-hcAOgDTc-1648305528719)(C:/Users/26969/AppData/Roaming/Typora/typora-user-images/image-20220325200000679.png)\]](https://img-blog.csdnimg.cn/d681a1e5a93f4ca9b5b60bc35b8bbd11.png)
                               

#### 3) 系统文件权限设计说明

作业管理所用的文件都在linux系统的/home/class目录下，一共有三个目录homework，grade，learning，三个文件grade-count.sh，grade-calculate.sh，grade_log.log

①  **homework**目录：用于学生上传作业和教师下载学生作业。该目录的权限设置为6777，其中6设置了s权限使得学生上传文件的组和homework一致，都是teacher，这是为了保证学生不能下载其他学生的作业。该目录的拥有者为teacher。而学生上传文件的权限被设置为750，即上传者学生可以管理自己的作业，但是不能下载或查看其他学生；而教师可以下载学生的作业，但是不能更改学生的作业。

②  **learning目录**：用于教师上传学习资料以及学生可以下载学习资料。该目录的权限设置为6750，其中6设置了s权限使得教师上传文件的组和learning一致，都是os， 而该目录的拥有者为teacher。教师上传的资料的权限被设置为750，保证学生可以下载文件但是无法修改文件，教师可以任意管理上传学习资料。

③  **grade目录**：用于教师统计学生交作业名单以及录入成绩。该目录的权限设置为6750，其中6设置了s权限使得教师上传文件的组和grade一致，都是os， 而该目录的拥有者为teacher。目录中的文件权限都被设置为750，保证学生可以下载交作业名单与成绩，可以下载作业模板和答案（**答案会在交作业时间截止后由教师上传**），教师可以任意管理上传文件。

> correct.txt：作业的正确答案
>
> answer-standardtxt：作业模板，供学生下载使用
>
> grade.txt：交作业名单与成绩统计

④  **grade-count.sh**：统计交作业名单的脚本，由系统按时执行，权限为777.

⑤  **grade-calculate.sh**：自动批改符合格式的作业的脚本，由教师自己执行，属于teacher组，权限为750。

⑥  **grade_log.log**：记录grade-count.sh脚本执行的情况。

#### 4) 统计交作业名单脚本设计说明

该脚本名称为grade-count.sh

①  利用命令 `date "+%Y-%m-%d %H:%M:%S`记录统计的时间

②  利用正则表达式将作业文件名划分为姓名和学号，如stu1-2194411245.txt就被划分为stu1和2194411245。

③  利用for循环统计homework目录下的所有文件，每有一个文件，count就+1，最后得到所有的交作业学生信息和总数

![image-20220326222526120](https://img-blog.csdnimg.cn/img_convert/4f1acc7edcc1db93839be5ff9b938533.png)

然后再利用crond进程定时执行该脚本，这里我设置为每天的23：59统计一次交作业名单

![image-20220326222542132](https://img-blog.csdnimg.cn/img_convert/5c0dcdce8268a6f67ca3813c42d29532.png)

而且记录了脚本执行日志。

#### 5) 自动批改指定格式的作业脚本设计说明

该脚本名称为grade-calculate.sh

这里我测试使用的作业格式如下：
![<img src="http://r9awog79x.hn-bkt.clouddn.com/image-20220326222604789.png" alt="image-20220326222604789" style="zoom:67%;float:left" />](https://img-blog.csdnimg.cn/c28d86493796468499edd443a41fb77f.png)


其中开头的#standard template用于区别是否使用指定格式的作业，每一道题都以数字+.+答案为格式，因此可以设计脚本如下：

①  遍历homework目录下的文件

②  对于每一个文件，遍历十次

③  利用文本分析工具awk，将学生作业每一行的答案解析出：以.分割序号和答案，将第二个参数打印赋给answer。

④  同理解析标准答案的值correct，如果correct=answer，则该作业加十分

⑤  将遍历得到的分数利用流编辑器sed加入到每一个学生作业记录的后面

![在这里插入图片描述](https://img-blog.csdnimg.cn/b0ae3cb90db045899d3b7f9479a4446e.png)


## 四、   配置文件关键修改处的说明及运行情况

### ②  学生教师服务

#### 0)   samba服务器配置 

```shell
[homework]  
comment = this folder is for students to submmit homework  
path = /home/class/homework  
valid users = @os  
read only = no  
public = no  
create mask = 0750  
force create mode = 0750  
  
[learning]  
comment = this folder is for teacher to share learning material  
path = /home/class/learning  
valid users = @os  
read only = no  
admin users = @teacher  
public = no  
create mask = 0750  
force create mode = 0750  
  
[grade]  
comment = this folder is for teacher to register student grades  
path = /home/class/grade  
valid users = @os  
read only = no  
admin users = @teacher  
public = no  
create mask = 0750  
force create mode = 0750:

```

#### 1)   用户组和用户

cat /etc/group

![image-20220326222812778](https://img-blog.csdnimg.cn/img_convert/4ed5cc18741bd7a7067dfe54d25023bd.png)                    

#### 2)   /home/class/下相关文件

tree /home/class/

 ![image-20220326222832310](https://img-blog.csdnimg.cn/img_convert/708276cf4b9f89b3d408dd003ff9fb03.png)

#### 3)   学生上传作业（Linux系统下）

以stu1学生上传为例

**学生可以在/home/class/grade****下载作业模板**

[外链图片转存失败,源站可能有防盗链机制,建议将图片保存下来直接上传(img-FdzLt6kY-1648305528723)(http://r9awog79x.hn-bkt.clouddn.com/image-20220326222858918.png)]   

**进入homework**目录

![image-20220326222921656](https://img-blog.csdnimg.cn/img_convert/e16b422d0a23351c867ba95b9cbe489f.png) 

**查看当前作业目录**

 ![image-20220326222939071](https://img-blog.csdnimg.cn/img_convert/a298e5b685832410a910d074c55e42f2.png)

**上传作业成功**

 ![image-20220326222952909](https://img-blog.csdnimg.cn/img_convert/be36124cc6df7febb16dbaafd13c1fef.png)

**无法下载他人作业**

 ![image-20220326223011879](https://img-blog.csdnimg.cn/img_convert/8322972a42e4595d31fbfae035dd196b.png)

#### 4)   学生上传作业（Windows系统下）——接下来大部分操作都在Windows下进行

在Windows系统进入[\\192.168.114.130](file://192.168.114.130)，输入账号密码

 ![<img src="http://r9awog79x.hn-bkt.clouddn.com/image-20220326223054214.png" alt="image-20220326223054214" style="zoom:67%;" />](https://img-blog.csdnimg.cn/0501e0e9be5945b681fc1f78a5109d92.png)

进入到共享文件夹下
![<img src="http://r9awog79x.hn-bkt.clouddn.com/image-20220326223118352.png" alt="image-20220326223118352" style="zoom:67%;" />](https://img-blog.csdnimg.cn/464be1c9fe93490ea876089e269281e0.png)
上传作业
![<img src="http://r9awog79x.hn-bkt.clouddn.com/image-20220326223144427.png" alt="image-20220326223144427" style="zoom:50%;" />](https://img-blog.csdnimg.cn/170a133a01214609929a957ad0d26152.png)




#### 5)   查看脚本统计交作业名单

出于测试的目的，这里我将执行时间改成了18:35

**在Windows**系统进入\\\192.168.114.130

 ![image-20220326223453813](https://img-blog.csdnimg.cn/img_convert/c5cc95cd1e8997ba10ef59d7143a17ee.png)

**进入grade**文件夹可以看到统计名单grade.txt

 ![image-20220326223511512](https://img-blog.csdnimg.cn/img_convert/e42a5208ca771775b8f7014cbee9f96e.png)

**也可以下载到自己的电脑中**

 ![image-20220326223546130](https://img-blog.csdnimg.cn/img_convert/87a1e15a87613fcb9598a2bc32d40785.png)

#### 6)   教师使用脚本批改指定格式的作业

在Linux系统下启动grade-calculate.sh脚本

 ![image-20220326223600755](https://img-blog.csdnimg.cn/img_convert/56f578f5829b48b191fda2271f4b2fe8.png)

可以看到自动批改后的交作业名单

 ![image-20220326223615029](https://img-blog.csdnimg.cn/img_convert/89f73738bec64971b01cedc0f58b5c57.png)

因为stu3没有按照格式提交作业，所以系统没有计算他的成绩

#### 7)   教师手动查看学生作业并批改

教师可以查看学生的作业

 ![image-20220326223637470](https://img-blog.csdnimg.cn/img_convert/b4497e5a772f76beb083d7efc1fe586a.png)

看完以后，可以手动输入stu3的成绩

 ![image-20220326223654370](https://img-blog.csdnimg.cn/img_convert/71070ba114c87a16286951444c80e3b6.png)

#### 8)   学生查看成绩与答案

stu1进入文件夹grade中可以查看grade.txt从而看到自己的成绩

 ![image-20220326223706888](https://img-blog.csdnimg.cn/img_convert/0b41c55a7e447aca9f0d40fd3ac7746a.png)

也可以查看correct.txt查看答案

 ![image-20220326223724761](https://img-blog.csdnimg.cn/img_convert/c1ca9ab6e2887d6cd709311d3ec3955c.png)

#### 9)   教师上传学习资料

教师可以将学习资料上传到learning文件夹下

![image-20220326223754277](https://img-blog.csdnimg.cn/img_convert/de7d1c5e094c4ffad482c566972cdddc.png) 

#### 10)  学生下载学习资料

stu1进入文件夹learning中可以下载学习资料

 ![image-20220326223812645](https://img-blog.csdnimg.cn/img_convert/ca199fd477d98a00be6951846e41fa5d.png)

无法上传学习资料

![image-20220326223825038](https://img-blog.csdnimg.cn/img_convert/168b2155dbad6307baed305319c83659.png)