[TOC]

# [计算机网络]第二章——应用层

<font color="red" size=4>**仅供交流，请勿转载，侵权必删**</font>

## 概述

> :one: 网络应用协议的概念和实现方面
>
> > 传输层服务模型
> >
> > 客户端-服务器模式
> >
> > 点对点模式
>
> :two: 通过研究流行的应用程序级协议来了解协议
>
> > HTTP
> >
> > FTP
> >
> > SMTP / pop3 / imap
> >
> > DNS
>
> :three: 网络应用程序编程
>
> > 套接字socket API

## 一、应用层协议原理

广播、电视、报纸、网站：四大媒介

网络核心设备并不在应用层上起作用，而是在较低层起作用。将应用软件限制在端系统，促进了大量的网络应用程序的迅速研发和部署。

研发网络应用程序的核心是写出能够运行在不同的端系统和通过网络彼此通信的程序。

> 客户端-服务端模式
>
> P2P模式
>
> 混合模式

### 1、网络应用程序体系结构

有两种主流的结构：客户端-服务器体系结构与P2P（对等）体系结构

#### 客户-服务器体系结构C/S

服务器：不间断的主机；**永久的IP地址**；用于**扩展**的服务器群

客户端：与服务器通信；可能是间歇性连接；可能有动态IP地址

> Web、FTP、Telnet和电子邮件

![image-20220403110937576](https://img-blog.csdnimg.cn/img_convert/9659d83c8b2963df83e7715faa0c6b9e.png)

#### P2P（对等）体系结构

没有不间断服务器；任意端系统直接通信；**对等体**之间间歇式连接，IP地址变化

<mark>高度可扩展但难以管理</mark>

> 适用于流量密集型的应用

![image-20220403111044753](https://img-blog.csdnimg.cn/img_convert/94336768ff23f807d8ffe675d7a57b12.png)

P2P模型从本质上来看仍然是使用客户/服务器方式，只是对等连接中的每一个主机即是客户又是服务器。多个客户机之间可以直接共享文档。

#### 混合C/S加P2P的体系结构

:one: **Skype**

> 基于ip的P2P应用程序
>
> 集中式服务器：查找远程方地址
>
> 客户端-客户端连接:直接(不通过服务器)

:two: **即时通信**

> 两个用户之间的聊天是P2P
>
> 集中式服务：客户端在线检测/位置
>
> > 当用户上线时，向中央服务器注册IP地址
> >
> > 用户联系中央服务器，查找好友的IP地址

### 2、进程通信

进程:在主机上运行的程序。

:one: 在同一个主机中，两个进程使用<mark>进程间通信(由操作系统定义)</mark>进行通信。

:two: 不同主机上的进程通过<mark>交换消息</mark>进行通信

#### 客户和服务器进程

客户端进程:发起通信的进程

服务器进程:等待联系的进程

#### 进程与计算机网络之间的接口

进程通过一个称为socket套接字的软件接口向网络发送报文和从网络接受报文

> 进程可类比于一座房子:house: ， 而它的套接字可以类比于它的门:door: 。当一个进程想向位于另外一台主机上的另一个进程发送报文时，它把报文推出该门(套接字)。 
>
> 该发送进程假定该门到另外一侧之间有运输的基础设施，该设施将把报文传送到目的进程的门口。
>
> 一且该报文抵达目的主机，它通过接收进程的门(套接字)传递，然后接收进程对该报文进行处理。

![image-20220403111650959](https://img-blog.csdnimg.cn/img_convert/40e245b054e2d0c89dac845d9bf9bf71.png)

套接字是同一台主机内**应用层与传输层**之间的接口。由于该套接字是建立网络应用程序的可编程接口，因此套接字也称为<mark>应用程序和网络之间的应用程序编程接口（API）</mark>

#### 进程寻址

为了接受报文，进程需要有标识符

> :question: 运行进程的主机的IP地址(32位)是否足以识别进程?
>
> :label:不，许多进程可以运行在同一平台上
>
> 标识符包括与主机上的进程关联的<mark>**IP地址和端口号**</mark>。
>
> > 例如端口号:
> >
> > **HTTP服务器:80**
> >
> > **邮件服务器:25**

### 3、应用层协议的定义

应用层协议定义了：

> :one: 交换的报文**类型**，例如,请求、响应
>
> :two: 消息的**语法**：消息中的哪些字段&字段是如何描述的
>
> :three: 字段的**语义**：字段中信息的含义
>
> :four: 进程何时以及如何发送和响应报文的**规则**

RTP：实时传输协议

种类：

> :one: 公共协议：在RFC[请求注解（Request For Comments）]定义；允许互操作性
>
> 例如，HTTP, SMTP, BitTorrent
>
> :two: 专用协议：例如,Skype, ppstream

**应用层只是网络应用的一部分**

### 4、互联网上的QoS(服务质量)要求

:one: **丢包率data loss**

一些应用程序(如音频)可以承受一些损失

其他应用程序(例如，文件传输，telnet)需要100%可靠的数据传输

:two: **实时性timing**

一些应用程序(如网络电话、互动游戏)需要低延迟才能“有效”。

:three: **吞吐量throughput**

一些应用程序(如多媒体)需要一定的吞吐量才能“有效”：带宽敏感应用

其他应用程序(“弹性应用程序”)利用他们所获得的任何吞吐量

:four: **安全security**

加密的数据，数据的完整性

**一些常用应用的需求**

| 应用程序        | 丢包           | 吞吐量                                   | 时间敏感性  |
| --------------- | -------------- | ---------------------------------------- | ----------- |
| 文件传输        | 不允许         | 弹性的                                   | 不敏感      |
| e-mail          | 不允许         | 弹性的                                   | 不敏感      |
| Web文档         | 不允许         | 弹性的                                   | 不敏感      |
| 实时音频/视频   | 容忍一定的丢包 | 音频: 5kbps-1Mbps<br />视频:10kbps-5Mbps | 敏感：100ms |
| 存储式音频/视频 | 容忍一定的丢包 | 音频: 5kbps-1Mbps<br />视频:10kbps-5Mbps | 敏感：几秒  |
| 互动游戏        | 容忍一定的丢包 | 超过几kbs                                | 敏感：100ms |
| 实时发信息      | 不允许         | 弹性的                                   | 是和不是    |

### 5、因特网提供的传输服务

#### TCP传输控制协议（Transmission Control Protocol）服务

:one: **连接管理**：客户端和服务器进程之间需要设置

:two: **可靠性控制**：发送和接收过程之间的可靠传输

:three: **流量控制**：发送方不会淹没接收方

:four: **拥塞控制**：网络过载时对发送方进行节流

<mark>不提供</mark>：实时性，最小吞吐量保证，安全性

> 因特网界研制了TCP的加强版。称为安全套接字层Secure Socket Layer (SSL)，这种强化是在应用层上实现的

#### UDP用户数据报协议（User Datagram Protocol）服务

发送和接收过程之间**不可靠的数据传输**

<mark>不提供</mark>：连接管理，可靠性，流量控制，拥塞控制，实时性，吞吐量保证，安全性

#### 流行的因特网应用机器应用层协议和支撑的传输协议

| 应用         | 应用层协议                          | 支撑的传输层协议      |
| ------------ | ----------------------------------- | --------------------- |
| 电子邮件     | SMTP [RFC 2821]                     | TCP                   |
| 远程终端访问 | Telnet [RFC 854]                    | TCP                   |
| 网页         | HTTP [RFC 2616]                     | TCP                   |
| 文件传输     | FTP [RFC 959]                       | TCP                   |
| 流式多媒体   | HTTP (eg Youtube),   RTP [RFC 1889] | TCP or UDP(局域网内） |
| 网络电话     | SIP, RTP, proprietary               | 通常是UDP             |

## 二、Web和HTTP

**Web页面**由**对象**组成：对象可以是HTML文件，JPEG图像，Java applet，音频文件，…

Web页面由**基本HTML(超文本标记语言)文件**组成，其中包括几个引用的对象

每个对象都可通过URL寻址：

![<img src="https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/qshell/image-20220303175113350.png" alt="image-20220303175113350" style="zoom:67%;" />](https://img-blog.csdnimg.cn/d0b7b6215d4742648547e81c57dd39a1.png)


someschool.edu是域名

### 1、HTTP概况

HTTP：hypertext transfer protocol超文本传输协议

Web的应用层协议：用于通信

客户机/服务器模型

> 客户端：请求、接收、显示Web对象的浏览器
>
> 服务端：Web服务器发送对象来响应请求

![image-20220403113429373](https://img-blog.csdnimg.cn/img_convert/61f7b426f8de250a62ff86e059b0c72d.png)

HTTP使用TCP作为它的支撑传输协议

> :one: 客户端发起TCP连接(创建套接字)到服务器，端口80
>
> :two: 服务器接受来自客户端的TCP连接
>
> :three: 浏览器(HTTP客户端)和Web服务器(HTTP服务器)之间交换HTTP消息(应用层协议消息)
>
> :four: TCP连接关闭

HTTP是一个**无状态stateless的协议**：服务器不保存以前的客户端的请求信息，因为保存这些状态十分复杂

### 2、非持续连接和持续连接

#### 非持续的HTTP：Non-Persistent HTTP

<mark>非持续连接</mark>：每个请求/响应对是经过一个单独的TCP连接发送；一个TCP连接最多发送一个对象。

假设用户进入网址：`www.someSchool.edu/someDepartment/home.index`，其中包含了对十张JPEG图形的引用，这十一个对象位于同一个服务器中。

> :label: JPEG（ Joint Photographic Experts Group）即联合图像专家组，是用于连续色调静态[图像压缩](https://baike.baidu.com/item/图像压缩/8325585)的一种标准，[文件后缀名](https://baike.baidu.com/item/文件后缀名/492702)为.jpg或.jpeg，是最常用的图像文件格式

过程如下：

> :one: HTTP客户进程在端口号80发起一个到服务器`www.someSchool.edu`的TCP连接,
> 该端口号是HTTP的默认端口。在客户和服务器上分别有一个套接字与该连接相关联。
>
> :two: HTTP 客户经它的套接字向该服务器发送一个HTTP请求报文。请求报文中包含了
> 路径名`/someDepartment/home.index` 。
>
> :three: HTTP 服务器进程经它的套接字接收该请求报文，从其存储器(RAM 或磁盘)中
> 检索出对象`www.someSchool.edu/someDepartment/home.index`，在一个HTTP响应报文中封装对象，并通过其套接字向客户发送响应报文。
>
> :four: HTTP 服务器进程通知TCP断开该TCP连接。(但是直到TCP确认客户已经完整地收到响应报文为止，它才会实际中断连接。)
>
> :five: HTTP 客户接收响应报文，TCP连接关闭。该报文指出封装的对象是一个HTML文件，客户从响应报文中提取出该文件，检查该HTML文件，得到对10个JPEG图形的引用。
>
> :six: 对每个引用的JPEG图形对象重复前4个步骤。

非持续性连接中每个TCP连接只传输一个请求报文和响应报文，因此上例中当用户请求该Web页面时需要产生**11个TCP连接**

<mark>**RTT(round trip time)**</mark>的定义:往返时间，一个小数据包在客户端和服务器之间往返传输的时间。

全部的时间 = 2RTT+文件发送时间

![image-20220308102712563](https://img-blog.csdnimg.cn/img_convert/ae1a77cdbd71a77e194bda01a80b9b1f.png)

#### 持续连接的HTTP：Persistent HTTP

**持续连接**：客户端和服务器之间可以通过单个TCP连接发送多个对象。

非持久的HTTP问题:

> 每个对象需要2个RTT
>
> 每个TCP连接需要操作系统开销
>
> 浏览器经常打开并行TCP连接来获取被引用的对象

持续的HTTP

> 服务器在发送响应后**保持连接打开**
>
> 在同一客户端/服务器之间通过打开的连接发送的后续HTTP消息
>
> 客户端一遇到引用的对象就发送请求
>
> 对于所有被引用的对象，只有一个RTT：**一开始连接需要两个RTT，后面的对象获取只用1个RTT**

### 3、HTTP报文格式

两种类型的HTTP消息:请求request，响应response

> 由三部分组成：开始行、首部行、实体主体

#### HTTP请求报文request message

ASCII ((American Standard Code for **Information Interchange**): 美国信息交换标准代码）是基于[拉丁字母](https://baike.baidu.com/item/拉丁字母/1936851)的一套电脑[编码](https://baike.baidu.com/item/编码/80092)系统，主要用于显示现代[英语](https://baike.baidu.com/item/英语/109997)和其他[西欧](https://baike.baidu.com/item/西欧/3028649)语言

![image-20220316133537668](https://img-blog.csdnimg.cn/img_convert/2d71e642b9c4fd9024ac48c153740bdd.png)

#### HTTP响应报文response message

![image-20220308104134863](https://img-blog.csdnimg.cn/img_convert/f5b0a4c3445a6126d242c61166fbcb4a.png)

### 4、用户和服务器的交互：cookie

许多主要网站使用cookie:存储在**客户端**中的小文件；用于身份验证

四个组件:

> :one: 在HTTP响应报文中的一个cookie首部行;
>
> :two: 在HTTP请求报文中的一个cookie首部行;
>
> :three: 在用户端系统中保留有一个cookie文件，并由用户的浏览器进行管理;
>
> :four: 位于Web站点的一个后端数据库。

**举例**

> 假设Susan总是从家中PC使用InternetExplorer 上网，她首次与Amazon.com联系。我们假定过去她已经访问过eBay站点。当请求报文到达该 Amazon Web 服务器时，该 Web 站点将产生一个唯一识别码，并以此作为索引在它的后端数据库中产生一个表项。<mark> 接下来 Amazon Web 服务器用一个包含 Set-cookie:首部的HTTP 响应报文对Susan的浏览器进行响应</mark>，其中Set-cookie：首部 含有该识别码。
> 例如，`该首部行可能是Set-cookie:1678`
>
> 当Susan的浏览器收到了该HTTP响应报文时，它会看到该Set-cookie：首部。<mark>该浏览器在它管理的特定cookie文件中添加一行，该行包含服务器的主机名和在Set-cookie：首部
> 中的识别码</mark>。值得注意的是该cookie 文件已经有了用于 eBay 的表项，因为Susan 过去访问过该站点。当Susan 继续浏览 Amazon 网站时，每请求一个Web页面，其浏览器就会查询该cookie 文件并抽取她对这个网站的识别码，并放到 HTTP 请求报文中包括识别码的 cookie 首部行中。
>
> 如果Susan再次访问Amazon站点，比如说一个星期后，她的浏览器会在其请求报文中继续放入首部行cookie：1678。Amazon将根据Susan过去在Amazon访问的网页向她推荐产品。
>
> ![image-20220403130524420](https://img-blog.csdnimg.cn/img_convert/db3155c83199c46c362a8b4be3c50aa8.png)
>
> 



### 5、Web缓存(代理服务器)

使用Web缓存的原因：**减少客户端请求的响应的时间；改善用户体验；节省主干带宽的流量**

**举例：假设浏览器正在请求对象：http://www.someschool.edu/campus.gif**

> 1)浏览器创建一个到Wb缓存器的TCP连接，并向Wb缓存器中的对象发送一个HTTP请求。
>
> 2)Web缓存器进行检查，看看本地是否存储了该对象副本。如果有，Wb缓存器就向客户浏览器用HTTP响应报文返回该对象。
>
> 3)如果Wb缓存器中没有该对象，它就打开一个与该对象的初始服务器（即www.someschool.edu)的TCP连接。Web缓存器则在这个缓存器到服务器的TCP连接上发送一个对该对象的HTTP请求。在收到该请求后，初始服务器向该Wb缓存器发送具有该对象的HTTP响应。
>
> 4)当Wb缓存器接收到该对象时，它在<mark>**本地存储空间存储一份副本**</mark>，并向客户的浏览器用HTTP响应报文发送该副本（通过现有的客户浏览器和Wb缓存器之间的TCP连接）。

Web cache的类别：代理cache：proxy cache；客户端cache：client cache；分布式cache：distributed cache；服务端cache：cluster（集群）

> <mark>普遍用的是客户端cache和服务端cache</mark>

![image-20220403131126538](https://img-blog.csdnimg.cn/img_convert/f41f8432aa7cf4796a0566002158e880.png)

#### proxy cache

目标:在不涉及源服务器的情况下满足客户端请求

> 用户设置浏览器:通过缓存访问Web
>
> 浏览器将所有的HTTP请求发送到缓存
>
> > 对象在缓冲中则让缓存返回对象；否则从源服务器缓存请求对象，然后返回对象给客户端

:globe_with_meridians: <mark>**举例**</mark>：如下图右两个网络：内部网络和公共因特网的一部分。内部网络是一个高速的局域网，它的一台路由器与因特网上的一台路由器通过一条15Mbps的链路。这些初始服务器与因特网相连但位于全世界各地。假设对象的平均长度为1Mb,从机构内的浏览器对这些初始服务器的平均访问速率为每秒15个请求。假设HTTP请求报文小到可以忽略，因而不会在网络中以及接入链路（从机构内部路由器到因特网路由器）上产生什么通信量。我们还假设在图中从互联网路由器到任何源服务器和返回路由器的延迟= 2秒。我们非正式地将该持续时延称为“因特网时延”.

![image-20220403135244421](https://img-blog.csdnimg.cn/img_convert/3f328ba94ea7ee7bd27c3f58d6e8b489.png)

:one: 对于情况一

> LAN的利用率为15x1/100=15%
>
> 接入链路的利用率为15x1/15=100%
>
> 对于1个请求（1去1回）：总的延时为=因特网延时+链路延时+局域网延时=2s+2/15s+2/100s=2.15s

:two: 对于情况二

> 我们拓宽链路的带宽为100Mbps
>
> LAN的利用率为15x1/100=15%
>
> 接入链路的利用率为15x1/100=15%
>
> 对于1个请求（1去1回）：总的延时为=因特网延时+链路延时+局域网延时=2s+2/100s+2/100s=2.04s
>
> 可见，对延时的改善效果并不好

:three: 对于情况三

> 不升级链路带宽而是安装一个Web缓存器
>
> 我们假设缓冲命中率为0.4，即40%的请求将几乎立即得到满足，60%的请求被原始服务器满足
>
> 对于1个请求（1去1回）：总的延时为=因特网延时+链路延时+局域网延时=(2s+2/100s+2/100s)x60%+(2/100)x40%=1.37s
>
> 可见，对延时的改善效果很好

#### Client Cache: Conditional Get

保存在服务器中的对象自该副本缓存在客户上以后可能已经被修改了。HTTP协议有一种机制，<mark>允许缓存器证实它的对象是最新的</mark>。这种机制就是条件GET（conditional GET）方法。如果：①请求报文使用 GET方法；并且②请求报文中包含一个“If-Modified-Since;”首部行。那么，这个HTTP 请求报文就是一个条件 GET 请求报文。

缓存器在将对象转发到请求的浏览器的同时，也会在本地缓存了该对象，同时会存储最后修改日期。只有当缓存对象被修改了才从服务器中发送对象，否则直接读取cache中的对象。

#### distributed cache

许多缓存是合作的

> 本地访问丢失，缓存链接邻居
>
> 通过http或ICP

如果邻居没有数据，则访问源服务器

操作不便，一般采用镜像服务器

#### server cache:cluster

多台服务器以集群方式构造

内容相同或不同的内容

连接被传输到轻载服务器(缓存)

> 高并行性、可靠性
>
> 负载均衡需要
>
> 所需目标定位算法

受到广泛的采用

## 三、因特网的电子邮件

### 1、FTP—— file transfer protocol

![image-20220308112421791](https://img-blog.csdnimg.cn/img_convert/17aef7f9750c049029f69997f321919c.png)

向远程主机传输文件

客户机/服务器模型

> 客户端:发起传输(远程传输或远程传输)的端
>
> 服务器:远程主机

ftp: RFC 959

FTP server: **21端口**，用于控制连接

#### 独立的控制连接和数据连接

在进行文件传输时，FTP的客户机和服务器之间要建立两个TCP连接，一个用于传输控制命令和响应，称为**控制连接**(21)，一个用于实际的文件内容传输，称为**数据连接**(20)

![image-20220308112628422](https://img-blog.csdnimg.cn/img_convert/510c9b38933e9149c243f2e182ad4a47.png)

### 2、电子邮件

三个主要组件:

> 用户代理（如浏览器）user agent
>
> 邮件服务器 mail server
>
> 简单邮件传输协议:SMTP

以下通过Alice发电子邮件给接收方Bob的场景进行说明。

![image-20220403145418519](https://img-blog.csdnimg.cn/img_convert/fded854f6e442ef4795d44535383dc74.png)

#### 用户代理

用户代理允许用户阅读、回复、转发、保存和撰写报文。微软的 Outlook 和 Apple Mail 是电子邮件用户代理的例子。当Alice 完成邮件撰写时，她的邮件代理向其邮件服务器发送邮件，此时<mark>邮件放在邮件服务器</mark>的外出报文队列中。当 Bob 要阅读报文时，他的用户代理在其邮件服务器的邮箱中取得该报文。

#### 邮件服务器

邮件服务器形成了电子邮件体系结构的核心。每个接收方（如Bob）在其中的某个邮件服务器上有一个**邮箱**（mailbox）。Bob 的邮箱管理和维护着发送给他的报文。一个典型的邮件发送过程是：从发送方的用户代理开始，传输到发送方的邮件服务器，再传输到接收方的邮件服务器，然后在这里被分发到接收方的邮箱中。当 Bob 要在他的邮箱中读取该报文时，包含他邮箱的邮件服务器（使用用户名和口令）来鉴别 Bob。

当邮件无法发送成功，在邮件服务器的一个报文队列中保持该报文并在以后再次尝试发送，多次尝试失败后则进行删除并通知给发送方。

SMTP协议在邮件服务器之间发送邮件消息

> 客户端:发送邮件服务器
>
> 服务端:接收邮件服务器

#### SMTP

使用TCP在客户端和服务器之间可靠地传输邮件消息，<mark>**端口号为25**</mark>

直连:发送服务器到接收服务器，没有通过中间服务器

> 命令:ASCII文本
>
> 响应:状态码和短语

消息必须是7位ASCII码——<mark>**只是英文邮件**</mark>

SMTP使用的是持续连接

:label: 具体的场景说明

> Alice发邮件给Bob
>
> :one: Alice调用她的邮件代理程序并提供Bob的邮件地址（例如bob@ someschool.edu)，撰写报文，然后指示用户代理发送该报文。
>
> :two: Alice的用户代理把报文发给她的邮件服务器，在那里该报文被放在报文队列中。
>
> :three: 运行在Alice的邮件服务器上的SMTP客户端发现了报文队列中的这个报文，它就创建一个到运行在 Bob 的邮件服务器上的SMTP 服务器的 TCP 连接。
>
> :four: 在经过一些初始SMTP握手后，SMTP客户通过该TCP连接发送Alice的报文。
>
> :five: 在Bob的邮件服务器上，SMTP的服务器端接收该报文。Bob的邮件服务器然后将该报文放人 Bob 的邮箱中。
>
> :six: 在Bob方便的时候，他调用用户代理阅读该报文
>
> ![image-20220403145842097](https://img-blog.csdnimg.cn/img_convert/495b2d2493b51b229ee1c1c932b7781e.png)



### 3、与HTTP的对比

**相同点**

> 这两个协议都用于从一台主机向另一台主机传送文件
>
> 当进行文件传输时，持续的HTTP和SMTP都使用持续连接

**区别**

> :one: HTTP是一个拉协议(pull protocol)：TCP连接是由想要接收文件的机器发起的；SMTP是一个推协议(push protocol)：TCP连接是由要发送该文件的机器发起的
>
> :two: SMTP要求每个报文使用7bitASCII码格式，HTTP数据不受这种限制
>
> :three: HTTP将每个对象封装到他自己的HTTP响应报文中，而SMTP将所有报文对象都放在一个报文中

### 4、邮件报文格式

![image-20220403192705203](https://img-blog.csdnimg.cn/img_convert/4fb46cb062b53fd7e025ccc2d1d82ab2.png)

### 5、邮件访问协议

![image-20220403193312668](https://img-blog.csdnimg.cn/img_convert/fec2e77d12a88d7e90465bc36a91aaa2.png)

SMTP:发送/存储到接收者的服务器

邮件访问协议:从服务器取回邮件

:one: POP3: Post Office Protoco-version 3 第三版邮局协议

> 授权(代理<——>服务器)和下载，端口:110
>
> POP3跨会话是<mark>无状态的</mark>

:two: IMAP: Internet邮件访问协议

> 更多功能(更复杂)，端口:143
>
> 操作服务器上存储的MSGS
>
> IMAP保持用户跨会话的状态:<mark>有状态的</mark>

:three: HTTP: gmail, Hotmail, Yahoo邮件等。

### 5、非ASCII数据的MIME扩展

MIME：Multipurpose Internet Mail Extension多用途互联网邮件扩展，RFC 2045, 2056

对于非ASCII文本，需要在msg中添加额外的头信息

消息头中的其他行声明MIME内容类型

> 内容类型:提醒接收器使用哪个显示程序
>
> 内容传输编码: ASCII编码时使用的编码类型

## 四、DNS：域名服务

主机的一种标识方法是用它的主机名hostname，例如www.baidu.com

也可以使用IP地址进行标识，例如192.168.1.202

域名：baidu.com

<mark>IP和域名是多对多的关系</mark>

ps：DNS属于网络内核的功能，但是放在网络边缘

> 因特网：“瘦内核，胖边缘”
>
> ATM网络：“胖内核，瘦边缘”

### 1、DNS提供的服务

DNS是:one: 一个由分层的DNS服务器实现的分布式数据库 :two: 一个使得主机能够查询分布式数据库的应用层协议

<mark>DNS运行在UDP上，使用53号端口</mark>

提供的服务如下：

> :one: 主机名到IP地址的转换
>
> :two: 主机别名:主机别名更容易记忆
>
> :three: 邮件服务器别名
>
> :four: 负载分配：DNS在冗余的服务器之间进行负载分配，繁忙的站点，如cnn.com被冗余分布在多个服务器上，每个服务器运行在不同的端系统上，每个有不同的IP地址。一个主机对应有一个IP地址集合，DNS数据库存储着这些 IP 地址集合。当客户对映射到某地址集合的名字发出一个 DNS 请求时，该服务器用IP 地址的整个集合进行响应，但在每个回答中循环这些地址次序。因为客户通常总是向IP 地址排在最前面的服务器发送 HTTP 请求报文，所以DNS就在所有这些冗余的Web服务器之间循环分配了负载。DNS的循环同样可以用于邮件服务器，因此，多个邮件服务器可以具有相同的别名。

### 2、DNS工作机理概述

集中式的DNS服务器缺点：单点故障、通信容量、远距离的集中式数据库造成延时长、维护困难

#### 分布式、层次数据库

![image-20220403193754596](https://img-blog.csdnimg.cn/img_convert/153fa425aeb35a80573e4117a4679478.png)

> **根域名服务器**：400多个，13个组织管理
>
> > 根域名服务器用来管辖顶级域名，它并不直接把查询得域名转换成IP地址，而是告诉本地域名服务器应当找哪一个顶级域名服务器进行查询
>
> **顶级域(TLD)服务器**
>
> **注册DNS服务器**：记录项一直存在，只要不欠费
>
> > 负责一个区的域名服务器

本地DNS服务器：不属于上面的层次结构，通常与主机位于同一个局域网中。

#### DNS域名解析示例

主机向本地域名服务器的查询都是采用**递归查询**，如果主机所在询问的本地域名服务器不知道被查询域名的IP地址，那么本地域名服务器就以**DNS客户**的身份向其他域名服务器继续发出查询请求报文。

例如：主机在cis.poly.edu需要IP地址gaia.c.s.umass .edu

:one: 迭代式查询（接近广度查询）

当根域名服务器收到本地域名服务器的迭代查询请求报文时，要么给出所要查询的IP地址，要么告诉本地域名服务器“下一步要向哪一个域名服务器进行查询”，然后让本地域名服务器进行后续查询。

这种查询方式其实也是混合的，因为主机向本地域名服务器的查询都是采用**递归查询**

![image-20220403194630915](https://img-blog.csdnimg.cn/img_convert/48a3749efd525a66fe61637aa9b8dd7b.png)

:two: 递归式查询（接近深度查询）

本地域名服务器只需要向根域名服务器查询一次，其他后面的几次查询都是在其他几个域名服务器之间进行的

![image-20220403194644912](https://img-blog.csdnimg.cn/img_convert/63969bf2d2756b2417429d7e32a20b12.png)

:three: 混合式查询

#### DNS缓存和更新记录

在一个请求链中，当某个DNS服务器接收到一个回答，他能缓存包含在该回答中的任何信息

举一个例子，假定主机 `apricot.nyu.edu` 向 `dns.nyu.edu`查询主机名 `cnn.com`的 IP 地址。此后，假定过了几个小时，纽约大学的另外一台主机如`kiwi.nyu.edu`也向`dns.nyu.edu`查询相同的主机名。因为有了缓存，该本地 DNS 服务器可以立即返回 `cnn.com` 的 IP 地址，而不必查询任何其他 DNS 服务器。本地 DNS 服务器也能够缓存 TLD 服务器的 IP 地址，因而允许本地 DNS 绕过查询链中的根 DNS 服务器。事实上，因为缓存，除了少数 DNS查询以外，根服务器被绕过了

### 3、DNS记录和报文

共同实现DNS分布式数据库的所有DNS服务器存储了资源记录（RR)

RR的格式如下：

```c
(Name，Value，Type，TTL)
```

TTL是该记录的生存时间，它决定了资源记录应当从缓存中删除的时间。在下面给出的记录例子中，我们忽略掉TTL字段。<mark>Name和Value的值取决于Type</mark>

:one: 如果Type =A，则Name是主机名，Value是该主机名对应的IP地址。因此，一条类型为A 的资源记录提供了标准的主机名到IP 地址的映射。例如 `( relay1.bar.foo.com，145.37.93.126，A）`就是一条类型A记录。

:two:如果Type =NS，则Name是个域（如foo.com），而Value是个知道如何获得该域中主机 IP 地址的权威 DNS 服务器的主机名。这个记录用于沿着查询链来路由 DNS查询。例如`（foo.com，dns.foo.com，NS)` 就是一条类型为NS的

:three: 记录如果Type = CNAME，则Value 是别名为Name的主机对应的规范主机名。该记录能够向查询的主机提供一个主机名对应的规范主机名，例如`（foo.com，relay1.bar.foo.com，CNAME)` 就是一条CNAME类型的记录。

:four: 如果Type = MX，则Value是个别名为Name的**邮件服务器**的规范主机名。举例来说，`(foo.com，mail.bar.foo.com，MX）`就是一条MX记录。MX记录允许邮件服务器主机名具有简单的别名。值得注意的是，通过使用 MX 记录，一个公司的邮件服务器和其他服务器（如它的 Web 服务器）可以使用相同的别名。为了获得邮件服务器的规范主机名，DNS客户应当请求一条 MX 记录；而为了获得其他服务器的规范主机名，DNS客户应当请求CNAME记录。

#### DNS协议，报文

DNS协议:查询和应答报文，报文格式相同

![image-20220316151736371](https://img-blog.csdnimg.cn/img_convert/4b6c0139294fad764d23d793cd1101f6.png)

前面12个字节是首部区域header

## 五、搜索引擎

万维网可以视作一个大的`图`：页面是一个`结点`；url是一个`边`

**索引**

> 将页面的关键字作为索引
>
> 关键字<-->url

需要三种数据结构

:one:  线性数组：存储发现的url指针和标题/页面指针

:two:  堆：存储可变长度的标题/页面和url

:three:  哈希表：将url哈希成更短的条目，避免重复访问

索引创建具体过程

:one: 第一:搜索（广度优先搜索）

> 使用递归过程:process_url，输入url，散列url，决定url是否在url_table
>
> 如果url在url _table中，选择下一个url，否则访问页面并将url和标题/页面放入堆中，然后哈希url并处理指针
>
> process_url如上所示处理页面中的所有url(超链接)

:two: 第二:索引

> 对于url_table中的每个条目，提取标题和页面中的**关键字**
>
> 将关键字指向对应的url_table项

## 六、Socket编程

服务器必须在客户端可以发送任何东西之前处于运行状态。

服务器必须有一个socket(门)，通过它接收和发送段segment

类似地，客户端需要一个套接字

套接字在本地由一个端口号标识

类似于建筑物中的apt #

客户端需要知道服务器的IP地址和socket端口号

## 练习题

:one: What are features of the TCP/IP Transport layer? (Choose two.)

>  path determination 
>
>  handles representation, encoding and dialog control 
>
>  uses TCP and UDP protocols :white_check_mark:
>
>  packet switching 
>
>  reliability, flow control and error correction:white_check_mark:

:two: Which OSI layer defines the functions of a router? 

>  physical 
>
>  data link 
>
>  network :white_check_mark:
>
>  transport 
>
>  session 

:three: Which type of institution does the domain suffix .org represent? 

>  government 
>
>  education 
>
>  network 
>
>  non-profit :white_check_mark:

:four: What is established during a connection-oriented file transfer between computers? (Choose two.)

在计算机之间的面向连接的文件传输过程中建立了什么?(选择两个)

>  a temporary connection to establish authentication of hosts 
>
>  a connection used for ASCII or binary mode data transfer  :white_check_mark:
>
>  a connection used to provide the tunnel through which file headers are transported 
>
>  a command connection which allows the transfer of multiple commands directly to the remote server system 
>
>  a control connection between the client and server  :white_check_mark:

:five: Which of the following services is used to translate a web address into an IP address?

> DNS :white_check_mark:
>
> WINS 
>
> DHCP 
>
> Telnet 

:six: Which part of the URL http://www.awsb.ca/teacher gives the name of the domain? 

>  www 
>
>  http:// 
>
>  /teacher 
>
>  awsb.ca  :white_check_mark:

:seven: Using the data transfer calculation T=S/BW, how long would it take a 4MB file to be sent over a 1.5Mbps connection? 

>  52.2 seconds 
>
>  21.3 seconds :white_check_mark:
>
>  6.4 seconds 
>
>  2 seconds 
>
>  0.075 seconds 
>
>  0.0375 seconds 

:eight: If a network administrator needed to download files from a remote server, which protocols could the administrator use to remotely access those files? (Choose two.)

>  NFS 
>
>  ASCII 
>
>  TFTP :white_check_mark:
>
>  IMAP 
>
>  FTP :white_check_mark:
>
>  UDP 

:nine: Which of the following protocols are used for e-mail transfer between clients and servers? (Choose three.) 

> TFTP 
>
> SNMP 
>
> POP3  :white_check_mark:
>
> SMTP  :white_check_mark:
>
> IMAP4  :white_check_mark:
>
> postoffice 