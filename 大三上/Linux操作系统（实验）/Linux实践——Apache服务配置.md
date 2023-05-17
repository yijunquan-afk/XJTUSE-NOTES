@[toc]
# Linux操作系统实践——Apache服务器配置

<font color="red" size=5>**仅供参考，谢绝抄袭**</font>

## 一、实验目的

熟练掌握 Linux 操作系统的使用，掌握 Linux 的各项系统管理功能，掌握 Linux 下各类网络服务的安装、配置以及使用，并能用 shell 脚本实现简单的管理 任务。

## 二、实验内容

### ①  Apache服务器配置

1）设置Web页面的主目录为/var/www/web；

2）设置Apache监听的端口号为8080；

3）建立一个名为temp的虚拟目录，其对应的物理路径是/var/www/temp，并对该虚拟目录启用用户认证，只允许用户tux和lily访问。

4）允许每个用户拥有自己的个人主页。制作你的个人主页，并给出你的个人主页显示结果。

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

 ![image-20220323114039195](https://img-blog.csdnimg.cn/img_convert/5aeedee9c1626c27df5278ca95854308.png)

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

## 四、   配置文件关键修改处的说明及运行情况

### ①  Apache服务器配置

#### 0)   设置web页面的主目录为/var/www/web

<img src="https://gitee.com/yi-junquan/image_gitee/raw/master/images/image-20220323120204228.png" alt="image-20220323120204228" style="zoom:150%;float:left" />

#### 1)   设置监听端口为8080

 ![image-20220323120214291](https://img-blog.csdnimg.cn/img_convert/4825039d255cd3049c5686c9630e46c5.png)

#### 2)   制作主目录页面

 <img src="https://gitee.com/yi-junquan/image_gitee/raw/master/images/image-20220323120244512.png" alt="image-20220323120244512" style="zoom:150%;" />

#### 3)   启动http服务

 ![image-20220323120305617](https://img-blog.csdnimg.cn/img_convert/6b859f398d64df816a273a0d30909dcc.png)

#### 4)   访问主页`http://localhost:8080/`

 ![image-20220323120319316](https://img-blog.csdnimg.cn/img_convert/0e9535eac1fab0bf00fb8b5c8e282a5d.png)

#### 5)   创建虚拟目录对应的物理目录

 ![](https://img-blog.csdnimg.cn/img_convert/c5a8d7eec8787e9b45b99c9db63bde84.png)

#### 6)   新建两个用户

<img src="https://gitee.com/yi-junquan/image_gitee/raw/master/images/image-20220323120357709.png" alt="image-20220323120357709" style="zoom:150%;float:left" />

#### 7)   为万维网服务器创建用户帐户，实现用户登录

 ![image-20220323120433198](https://img-blog.csdnimg.cn/img_convert/fd8b02d8c74d97c3a93804cd58de4fb3.png)

#### 8)   建立一个名为temp的虚拟目录，并设置访问权限

 ![image-20220323120452793](https://img-blog.csdnimg.cn/img_convert/6184a233820b58ae0be95105a3800460.png)

#### 9)   登录tux和lily可以进行访问

 ![image-20220323120517960](https://img-blog.csdnimg.cn/img_convert/3c1bd5a2a2e82c0afade5c91b970b714.png)

<img src="https://gitee.com/yi-junquan/image_gitee/raw/master/images/image-20220323120536874.png" alt="image-20220323120536874" style="zoom:150%;float:left" />

#### 10)  其他用户无法访问

 [外链图片转存失败,源站可能有防盗链机制,建议将图片保存下来直接上传(img-KJF07VmB-1648008613260)(https://gitee.com/yi-junquan/image_gitee/raw/master/images/image-20220323120558379.png)]

#### 11)  制作用户yjq的个人主页

<img src="https://gitee.com/yi-junquan/image_gitee/raw/master/images/image-20220323120616124.png" alt="image-20220323120616124" style="zoom:150%;" />   

 <img src="https://gitee.com/yi-junquan/image_gitee/raw/master/images/image-20220323120620868.png" alt="image-20220323120620868" style="zoom:150%;" />

#### 12)  创建个人主页文件index.html

 ![image-20220323120648422](https://img-blog.csdnimg.cn/img_convert/b5091b69817ad180572129db8e82eb37.png)

#### 13)  访问个人主页如下

 ![image-20220323120657795](https://img-blog.csdnimg.cn/img_convert/842ba1a3c1d436c50caada9060c4ed71.png)