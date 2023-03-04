[TOC]

# Linux操作系统综合训练

<font color="red" size=5>**仅供参考，谢绝抄袭**</font>

## 一、实验目的

熟练掌握 Linux 操作系统的使用，掌握 Linux 的各项系统管理功能，掌握 Linux 下各类网络服务的安装、配置以及使用，并能用 shell 脚本实现简单的管理 任务。

## 二、实验内容

### ①  Apache服务器配置

1）设置Web页面的主目录为/var/www/web；

2）设置Apache监听的端口号为8080；

3）建立一个名为temp的虚拟目录，其对应的物理路径是/var/www/temp，并对该虚拟目录启用用户认证，只允许用户tux和lily访问。

4）允许每个用户拥有自己的个人主页。制作你的个人主页，并给出你的个人主页显示结果。

### ②  学生教师服务

根据所学内容，使用一种或多种服务（如 ftp、samba、Http 等）搭建一台服务器，支持多用户访问，并能完成下述功能：

1）学生用户能实现学生作业的上传，学生以“姓名+学号”命名作业。学生 能看到作业列表，但是不能下载其他用户的作业。

2）在指定的交作业截止时间到时，编写脚本自动统计交作业的学生名单和 人数，生成文档，供教师查看。

3）教师用户能够查看学生提交的作业，在生成的交作业名单录入成绩， 并发布成绩，供学生查看。

4）对于指定格式的作业，编写脚本自动批改作业并在交作业名单中记录成绩。（注：格式可以自己指定，也可做成模板供学生下载使用）

5）教师可以提供课件和参考资料供学生下载。

### ③  CPU 调度算法实现

在 Linux 环境下编写 C 或 C++程序实现几种 CPU 调度算法：FCFS、SJF 和 优先权调度。 在 Linux 下进行编译和运行， 可使用 Makefile 文件实现程序的编 译、安装和卸载。 并比较这几种 CPU 调度算法的性能，给出等待时间、周转时 间及其平均值。（报告中给出源代码、Makefile 文件、make 运行结果以及程序运 行结果）。

实验所用测试数据如下表（算法均默认为非抢占）

![image-20220323113845737]( http://r9awog79x.hn-bkt.clouddn.com/image-20220323113845737.png)

## 三、   题目分析及基本设计过程分析

### ①  Apache服务器配置

Apache服务器是一种web服务器，这里使用到的是其静态页面的功能，服务器会响应客户的请求，读取本地一个文件发送给客户端。

修改配置文件配置文件 /etc/httpd/conf/httpd.conf可以实现相应的web服务。

#### 1) 修改Web页面的主目录

可以通过修改配置文件中的DocumentRoot "/var/www/html" 成题目要求的即可

#### 2) 修改端口号

可以通过修改配置文件中的Listen 80成题目要求的即可

#### 3) 实现用户认证

为万维网服务器创建用户帐户，实现用户登录

`htpasswd -c /etc/httpd/htpasswd username`

稍后要添加更多用户，请输入

`htpasswd /etc/httpd/htpasswd username`

#### 4) 建立虚拟目录并实现用户认证

在配置文件中添加如下语句可以建立虚拟目录/xxx 

 ![image-20220323114039195]( http://r9awog79x.hn-bkt.clouddn.com/image-20220323114039195.png)

#### 5) 制作个人主页

UserDir：设置用户个人网页目录

> public_html：用户个人目录为public_html
>
> 用户个人目录必须在mod_userdir.c存在时才有效

只允许部分用户拥有个人网页

> `UserDir enabled zhangsan lisi`

假定把test.html放在se.xjtu.edu.cn的/home/zhangsan/public_html目录下

> 访问上述页面的URL为：
>
> `http://se.xjtu.edu.cn/~zhangsan/test.html`
>
> 注意个人目录的权限应为755

 容器设置如下

```shell
<Directory “/home/*/public_html>  
	AllowOverride …    
    Options  …   
	order allow,deny  
</Directory>
```

### ② 学生教师服务

#### 1)   整体思路

对于这道题，我在RedHat系统上使用了Samba搭建服务器，能使学生和老师在Windows客户机上上传和下载文件，老师能在Linux系统上启动自动批改脚本批改指定格式的作业。

关于Samba服务器的配置，会在第四部分进行说明。

#### 2)  系统用户和组分配设计说明

系统中一共有三个组os，student和teacher

①  os组中有所有的学生和教师：stu1，stu2，stu3，stu4以及teacher，服务于课程。

②  student组中有所有的学生：stu1，stu2，stu3以及stu4，服务于学生。

③  teacher组中有所有的教师：teacher，服务于教师

<img src=" http://r9awog79x.hn-bkt.clouddn.com/image-20220323114502716.png" alt="image-20220323114502716" style="zoom:67%;" />                               

#### 3) 系统文件权限设计说明

作业管理所用的文件都在linux系统的/home/class目录下，一共有三个目录homework，grade，learning，三个文件grade-count.sh，grade-calculate.sh，grade_log.log

①  **homework**目录**：用于学生上传作业和教师下载学生作业。该目录的权限设置未6777，其中6设置了s权限使得学生上传文件的组和homework一致，都是teacher，这是为了**保证学生不能下载其他学生的作业。该目录的拥有者为teacher。而学生上传文件的权限被设置为750，即上传者学生可以管理自己的作业，但是不能下载或查看其他学生；而教师可以下载学生的作业，但是不能更改学生的作业。

②  **learning目录**：用于教师上传学习资料以及学生可以下载学习资料。该目录的权限设置未6750，其中6设置了s权限使得教师上传文件的组和learningk一致，都是os， 而该目录的拥有者为teacher。教师上传的资料的权限被设置为750，保证学生可以下载文件但是无法修改文件，教师可以任意管理上传学习资料。

③  **grade目录**：用于教师统计学生交作业名单以及录入成绩。该目录的权限设置未6750，其中6设置了s权限使得教师上传文件的组和grade一致，都是os， 而该目录的拥有者为teacher。目录中的文件权限都被设置为750，保证学生可以下载交作业名单与成绩，可以下载作业模板和答案（**答案会在交作业时间截止后由教师上传**），教师可以任意管理上传文件。

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

 <img src=" http://r9awog79x.hn-bkt.clouddn.com/image-20220323114807732.png" alt="image-20220323114807732" style="zoom:80%;" />

然后再利用crond进程定时执行该脚本，这里我设置为每天的23：59统计一次交作业名单

 ![image-20220323114827767]( http://r9awog79x.hn-bkt.clouddn.com/image-20220323114827767.png)

而且记录了脚本执行日志。

#### 5) 自动批改指定格式的作业脚本设计说明

该脚本名称为grade-calculate.sh

这里我测试使用的作业格式如下：

 ![image-20220323114837964]( http://r9awog79x.hn-bkt.clouddn.com/image-20220323114837964.png)

其中开头的#standard template用于区别是否使用指定格式的作业，每一道题都以数字+.+答案为格式，因此可以设计脚本如下：

①  遍历homework目录下的文件

②  对于每一个文件，遍历十次

③  利用文本分析工具awk，将学生作业每一行的答案解析出：以.分割序号和答案，将第二个参数打印赋给answer。

④  同理解析标准答案的值correct，如果correct=answer，则该作业加十分

⑤  将遍历得到的分数利用流编辑器sed加入到每一个学生作业记录的后面

 <img src=" http://r9awog79x.hn-bkt.clouddn.com/image-20220323114927675.png" alt="image-20220323114927675" style="zoom:80%;" />

### ②    CPU 调度算法实现

#### 1)   程序编译

**gcc命令** 使用GNU推出的基于C/C++的编译器，是开放源代码领域应用最广泛的编译器，具有功能强大，编译代码支持性能优化等特点

**语法**

```
gcc(选项)(参数)
```

**选项**

```
-o：指定生成的输出文件；
-E：仅执行编译预处理；
-S：将C代码转换为汇编代码；
-wall：显示警告信息；
-c：仅执行编译操作，不进行连接操作。
```

 

#### 2) Makefile文件格式

> 一个Makefile文件主要有一系列规则，每条规则都必须包含以下内容：
>
> 一个目标（target），即make最终需要创建的文件，如可执行文件或目标文件，目标也可以是要执行的动作，如clean
>
> 一个或多个依赖文件（dependency）列表，通常是编译目标文件所需要的其他文件
>
> 一系列命令（command），是make执行的动作，通常是把指定的相关文件编译成目标文件的编译命令，每个命令占一行，并且每个命令的起始字符必须为Tab字符。

 

#### 3) make命令

**m**ake命令 是GNU的工程化编译工具，用于编译众多相互关联的源代码文件，以实现工程化的管理，提高开发效率

**语法**

```
make(选项)(参数)
```

**选项**

```
-f：指定“makefile”文件；
-i：忽略命令执行返回的出错信息；
-s：沉默模式，在执行之前不输出相应的命令行信息；
-r：禁止使用build-in规则；
-n：非执行模式，输出所有执行命令，但并不执行；
-t：更新目标文件；
-q：make操作将根据目标文件是否已经更新返回"0"或非"0"的状态信息；
-p：输出所有宏定义和目标文件描述；
-d：Debug模式，输出有关文件和检测时间的详细信息。
```

####  4) 算法设计说明

**定义结构体表示一个进程** 

```c
typedef struct pro {  
    int id;//作业ID  
    int arrive_time;//到达时间  
    int excute_time;//执行时长  
    int priority;//优先级  
    int begin_time;//开始执行时间  
    int waiting_time;//等待时间  
    int turnaround_time;//周转时间  
} process; 

```

**定义其他变量**

```c
process test_data[NUM] = { 
  {1, 800, 50, 0}, {2, 815, 30, 1}, 
  {3, 830, 25, 2}, {4, 835, 20, 2}, 
  {5, 845, 15, 2}, {6, 700, 10, 1}, 
  {7, 820, 5, 0} 

};//测试数据 

process Priority_output[NUM];//Priority的输出 
process SJF_output[NUM];//SJF的输出 
process ready_process[NUM];//就绪进程 
```

**FCFS调度算法** 

<img src=" http://r9awog79x.hn-bkt.clouddn.com/image-20220323115936716.png" alt="image-20220323115936716" style="zoom:50%;float:left" />

**SJF调度算法** 

这里我用到了**就绪进程组**来存已经到达但是无法执行的进程，当CPU资源空出时，则利用find_shortest函数找出执行时间最短的进程，为其分配CPU资源，同时就绪进程组中的进程数减一

<img src=" http://r9awog79x.hn-bkt.clouddn.com/image-20220323115858001.png" alt="image-20220323115858001" style="zoom:50%;float:left" />

**非抢占优先级调度算法** 

该算法的程序实现与SJF相似，都用到了就绪进程组，只是find_shortest函数改成了find_priority，找出优先级最小的进程，其余的都跟SJF一样，不再赘述

## 四、   配置文件关键修改处的说明及运行情况

### ①  Apache服务器配置

#### 0)   设置web页面的主目录为/var/www/web

<img src=" http://r9awog79x.hn-bkt.clouddn.com/image-20220323120204228.png" alt="image-20220323120204228" style="zoom:150%;float:left" />

#### 1)   设置监听端口为8080

 ![image-20220323120214291]( http://r9awog79x.hn-bkt.clouddn.com/image-20220323120214291.png)

#### 2)   制作主目录页面

 <img src=" http://r9awog79x.hn-bkt.clouddn.com/image-20220323120244512.png" alt="image-20220323120244512" style="zoom:150%;" />

#### 3)   启动http服务

 ![image-20220323120305617]( http://r9awog79x.hn-bkt.clouddn.com/image-20220323120305617.png)

#### 4)   访问主页`http://localhost:8080/`

 ![image-20220323120319316]( http://r9awog79x.hn-bkt.clouddn.com/image-20220323120319316.png)

#### 5)   创建虚拟目录对应的物理目录

 ![]( http://r9awog79x.hn-bkt.clouddn.com/image-20220323120341955.png)

#### 6)   新建两个用户

<img src=" http://r9awog79x.hn-bkt.clouddn.com/image-20220323120357709.png" alt="image-20220323120357709" style="zoom:150%;float:left" />

#### 7)   为万维网服务器创建用户帐户，实现用户登录

 ![image-20220323120433198]( http://r9awog79x.hn-bkt.clouddn.com/image-20220323120433198.png)

#### 8)   建立一个名为temp的虚拟目录，并设置访问权限

 ![image-20220323120452793]( http://r9awog79x.hn-bkt.clouddn.com/image-20220323120452793.png)

#### 9)   登录tux和lily可以进行访问

 ![image-20220323120517960]( http://r9awog79x.hn-bkt.clouddn.com/image-20220323120517960.png)

<img src=" http://r9awog79x.hn-bkt.clouddn.com/image-20220323120536874.png" alt="image-20220323120536874" style="zoom:150%;float:left" />

#### 10)  其他用户无法访问

 ![image-20220323120558379]( http://r9awog79x.hn-bkt.clouddn.com/image-20220323120558379.png)

#### 11)  制作用户yjq的个人主页

<img src=" http://r9awog79x.hn-bkt.clouddn.com/image-20220323120616124.png" alt="image-20220323120616124" style="zoom:150%;" />   

 <img src=" http://r9awog79x.hn-bkt.clouddn.com/image-20220323120620868.png" alt="image-20220323120620868" style="zoom:150%;" />

#### 12)  创建个人主页文件index.html

 ![image-20220323120648422]( http://r9awog79x.hn-bkt.clouddn.com/image-20220323120648422.png)

#### 13)  访问个人主页如下

 ![image-20220323120657795]( http://r9awog79x.hn-bkt.clouddn.com/image-20220323120657795.png)

