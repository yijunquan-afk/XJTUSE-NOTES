[TOC]

# 计算机网络复习重点

## 第一章 计算机网络和因特网

### 概念与应用

#### 1、什么是因特网

因特网是一个连接全世界上百万计算设备的网络，由**edges边缘,core内核 and links**链路组成（架构）

> :one: 边缘主要包括端系统（主机）（上面运行着应用程序）
>
> > **客户机/服务器模型C/S**、**对等模型P2P**
>
> :two: <mark>网络内核是由路由器互联组成的网</mark>
>
> :three: 链路即通信链路：包括光纤、铜、无线电、卫星等
>
> 胖边缘、瘦内核

#### 2、协议protocol

<mark>协议</mark>定义了报文的**格式、命令以及报文传输和接收时所采取的行动**

#### 3、入网方式

:one: 数字用户线DSL：家庭入网

:two: 电缆入网：家庭入网

:three: FTTH光纤入户：家庭入网

:four: 以太网：家庭入网和企业入网

:five: WIFI: 家庭入网和企业入网

:six: 3G (3rd generation)无线和长期演进(Long-Term Evolution, LTE): 广域无线入网

#### 4、物理媒介

物理媒介分为两种：

> **有向方式媒介guided media**，如光缆、双绞铜线或同轴电缆
>
> > 双绞铜线Twisted Pair (TP)
> >
> > 光纤电缆Fiber optic cable：不受电磁噪声影响
> >
> > 同轴电缆Coaxial cable
>
> **无向方式媒介unguided media**：电波在空气或外层空间中传播，如无线局域网
>
> 地面微波、4G/5G 、卫星无线电通道

#### 5、数据交换模式

:one: **电路交换circuit switching**：使用电话拨号,信号从发送端直接发送到接收端，一位位传，实时性比较好

> 需要拨号，拨号的过程是资源预留的过程，拨通了才能传输数据。打电话就是这个方式。每个呼叫会分配到一部分资源（带宽），资源的常用使用方式有两种FDM频分复用、TDM时分复用
>
> <img src="https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/qshell/image-20220302145617686.png" alt="image-20220302145617686" style="zoom:50%;" />

:two: **分组交换packet-switching**：路由器采用，不需要拨号，将数据封装成小包，放到队列中，按照算法将数据包放到数据端口，发送到下一个端口。存储：排队的过程；转发：找到出口

因特网的网络内核使用到的就是分组交换

为了从源端系统向目的端系统发送一个报文，源将长报文划分为较小的数据块，称之为分组（packet）

**存储转发**指的是路由器（交换机）在向输出链路传输该包的第一个bit前必须等待包中的所有内容到达队列

分组交换资源使用的方式是：<mark>**统计复用statistical multiplexing**（因特网内核资源使用方式）</mark>。指的是多个用户都可以发送数据，最终按照实际使用量去计算，资源没有划分开

:three: **虚电路交换**：数据按照包传输，但是需要拨号，拨号的过程就是链路建立的过程，没有一根线连起来，中间交换设备会将资源准备好，ATM网络

电路交换与分组交换比较

> :one: <mark>分组交换允许更多的用户使用网络</mark>
>
> :two: 分组交换适用于突发数据
>
> :three: 分组交换资源利用率高
>
> :four: 分组交换技术比较简单
>
> :five: 电话交换实时性更好

#### 6、延时与丢包

##### 什么时候发生延时？

> 当数据包到达链路的速率超过了链路的输出速率（带宽），此时分组需要排队，等待轮到自己
>
> <img src="https://img-blog.csdnimg.cn/img_convert/8fe2a839f6f7e9ad1a227ff52c7b757e.png" alt="image-20220301102936843" style="zoom:50%;" />

##### 延时的类型

![image-20220302154933035](https://img-blog.csdnimg.cn/img_convert/81f383cb41ad0743ce98d9f8d963a248.png)

:one: 节点处理延时

> 校验位错误、确定输出链接

:two: 排队延时

> 在输出链路上等待传输的时间。取决于路由器的拥塞程度

:three: 传送延时Transmission delay

> R=从路由器A到路由器B的链路传输速率 (bps)
>
> L=包的位长(bits)
>
> 传送（发出）到链路的时间 = L/R

:four: 传播延时Propagation delay 

> d =物理链路长度
>
> v =介质中的传播速度($~3\times10^8$m/s)
>
> 传播延时= d/v

$$
d_{nodal}=d_{proc}+d_{queue}+d_{trans}+d_{prop}
$$

##### 丢包何时发生

> 当路由器的输出缓存满了，下一个到达的包会被丢弃

#### 7、协议层次与模型

##### 因特网协议栈TCP / IP模型

自顶向下

:five: 应用层 :支持网络应用

> FTP、SMTP、HTTP、应用程序……

:four: 传输层: 过程-过程数据传输

> <mark>TCP（可靠), UDP(不可靠)</mark>——就两个协议

:three: 网络层:数据报从源到目的地的路由

> IP, 路由协议,…

:two: 链路层: 相邻网络元素之间的数据传输

> 将整个帧从一个网络元素移动到临近的网络元素
>
> PPP、以太网、ADSL、FTTH 、WI-FI……

:one: 物理层: bit "on the wire"

> 物理层的任务是将链路层中的帧中的一个个bit从一个节点移动到下一个节点

##### ISO/OSI参考模型

> ISO：国际标准化组织
>
> OSI：开放系统互连
>
> 将各个局域网互连起来

相较于TCP/IP模型，多了两个层

:label: **表示层**: 允许应用程序解释数据的含义，例如加密、压缩、特定于机器的约定

:label: **会话层**: 同步，检查点，恢复数据交换

<img src="https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/qshell/image-20220301105937817.png" alt="image-20220301105937817" style="zoom:67%;" />

##### 协议数据单元DPU

要记住PDU在每层中分别是什么

> 应用层：报文
>
> 传输层：段
>
> 网络层：数据报
>
> 链路层：帧
>
> 物理层：位

![image-20220505143742929](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/image-20220505143742929.png)

### 名词缩写解释

**TCP**: Transmission Control Protocol 传输控制协议

**HTTP**: Hyper Text Transfer Protocol 超文本传输协议

**SMTP**: Simple Mail Transfer Protocol 简单邮件传输协议,

**DNS**: Domain Name System 域名系统

**FTP**: File Transfer Protocol 文件传输协议

**ATM**: Asynchronous Transfer Mode 异步传输模式

**ISDN**: Integrated Services Digital Network 综合业务数字网

**ADSL**: Asymmetric Digital Subscriber Line 非对称数字用户线路

**HFC**: Hybrid Fiber Coax 混合光纤同轴电缆

**ISP**:  Internet Service Provider 网络业务提供商

**LAN**: Local Area Network 局域网

**WAN**:  Wide Area Network 广域网,

**MAN**: Metropolitan Area Network 城域网

**WLAN**: Wireless Local Area Networks 无线局域网

**ISO**: **International Organization for Standardization** 国际标准化组织

**OSI**: Open System Interconnection 开放系统互联

**FDM：频分复用**

**TDM：时分复用**

**CDMA：码分复用**

### 练习题

:one: 在新的网络安装中，网络管理员决定使用不受电噪声影响的介质。哪种电缆类型最符合这个标准?

a) coaxial      b)     screened twisted pair        c) shielded twisted pair     d)  unshielded twisted pair        e) fiber optic 

> e) fiber optic 光缆

:two: 选择所需的必要信息，以计算将数据从一个位置传输到另一个位置所需的估计时间。(选择两个。)

a) file size        b) data format      c)   network in use        d) type of medium       e)   bandwidth of the link 

> a)文件大小和e) 带宽

:three: 哪个协议在TCP/IP协议组的网络层起作用?

a) File Transfer Protocol (FTP)     b)  Trivial File Transfer Protocol (TFTP)（不可靠，适用于局域网）   c) Transmission Control Protocol (TCP)   d) Internet Protocol (IP)         e)  User Datagram Protocol (UDP)        f) Simple Mail Transport Protocol (SMTP) 

> d) IP

:four: Which of the following is the Layer 4 PDU?

a) bit      b)  frame       c)  packet         d) segment 

> d) segment

:five: Which OSI layer encapsulates data into packets?

a)  session     b)   transport       c) network     d)   data link  

> c) 网络层

:six: Why are internets necessary? (Choose three.)

a) to overcome LAN scalability limitations           

b) to overcome LAN speed limitations 

c) to overcome LAN distance limitations

d) to prevent collision and congestion conditions 

e) to network networks 

> a) 克服扩展性
>
> c) 克服远距离传输
>
> e) 网间网

:seven: 排队论中有一个著名公式——李特尔公式：N=a x d，其中N表示在缓存中的分组加上被传输的分组的平均数，a表示到达该链路的分组速率，d表示一个分组历经的平均总时延即排队时延加传输时延。假定该缓存平均包含10个分组，并且平均分组排队时延是10ms，该链路的传输速率是100分组/秒，则在没有丢包的情况下，平均分组到达率是**550分组/秒**

> N= 10 + 1= 11个分组 d= 10ms(传播时延) + 1/100s(传输时延) = 0.02s a=N/d= 11/0.02 =550分组/s

---

## 第二章 应用层

### 概念与应用

**应用层只是网络应用的一部分**

#### 1、应用层协议原理

##### C/S与P2P

客户端-服务器体系结构

> 服务器：不间断的主机；**永久的IP地址**；用于**扩展**的服务器群
>
> 客户端：与服务器通信；可能是间歇性连接；可能有动态IP地址
>
> > Web、FTP、Telnet和电子邮件

##### P2P（对等）体系结构

没有不间断服务器；任意端系统直接通信；**对等体**之间间歇式连接，IP地址变化

<mark>高度可扩展但难以管理</mark>

> 适用于流量密集型的应用

##### 进程通信

进程通过一个称为socket套接字的软件接口向网络发送报文和从网络接受报文

> 进程可类比于一座房子:house: ， 而它的套接字可以类比于它的门:door: 。当一个进程想向位于另外一台主机上的另一个进程发送报文时，它把报文推出该门(套接字)。 
>
> 该发送进程假定该门到另外一侧之间有运输的基础设施，该设施将把报文传送到目的进程的门口。
>
> 一且该报文抵达目的主机，它通过接收进程的门(套接字)传递，然后接收进程对该报文进行处理。

套接字是同一台主机内**应用层与传输层**之间的接口。由于该套接字是建立网络应用程序的可编程接口，因此套接字也称为<mark>应用程序和网络之间的应用程序编程接口（API）</mark>

进程的标识符包括与主机上的进程关联的<mark>**IP地址和端口号**</mark>。例如端口号:**HTTP服务器:80**；**邮件服务器:25**

#### 2、互联网上的QoS(服务质量)要求

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

#### 3、HTTP

##### HTTP使用的是客户端/服务器模型

> 客户端：请求、接收、显示Web对象的浏览器
>
> 服务端：Web服务器发送对象来响应请求

##### HTTP使用TCP作为它的支撑传输协议

:one: 客户端发起TCP连接(创建套接字)到服务器，端口80

:two: 服务器接受来自客户端的TCP连接

:three: 浏览器(HTTP客户端)和Web服务器(HTTP服务器)之间交换HTTP消息(应用层协议消息)

:four: TCP连接关闭

HTTP是一个**无状态stateless的协议**：服务器不保存以前的客户端的请求信息，因为保存这些状态十分复杂

##### 非持续连接和持续连接

假设用户进入网址：`www.someSchool.edu/someDepartment/home.index`，其中包含了对十张JPEG图形的引用，这十一个对象位于同一个服务器中。

<mark>非持续连接</mark>：每个请求/响应对是经过一个单独的TCP连接发送；一个TCP连接最多发送一个对象。

> 非持续性连接中每个TCP连接只传输一个请求报文和响应报文，因此上例中当用户请求该Web页面时需要产生**11个TCP连接**,每个对象需要2个RTT
>
> 全部的时间：2RTT+20RTT+文件传输时间

<mark>**持续连接**</mark>：客户端和服务器之间可以通过单个TCP连接发送多个对象。

> 服务器在发送响应后**保持连接打开**
>
> 在同一客户端/服务器之间通过打开的连接发送的后续HTTP消息
>
> 客户端一遇到引用的对象就发送请求
>
> 对于所有被引用的对象，只有一个RTT：**一开始连接需要两个RTT，后面的对象获取只用1个RTT**
>
> 全部的时间：2RTT+10RTT+文件传输时间

全部的时间 = 2RTT+文件发送时间

<img src="https://img-blog.csdnimg.cn/img_convert/ae1a77cdbd71a77e194bda01a80b9b1f.png" alt="image-20220308102712563" style="zoom:50%;" />

##### 请求报文与响应报文

HTTP的报文包括两种：请求request和响应response

> 由三部分组成：开始行、首部行、实体主体

**请求报文**

![image-20220316133537668](https://img-blog.csdnimg.cn/img_convert/2d71e642b9c4fd9024ac48c153740bdd.png)

**响应报文**

![image-20220308104134863](https://img-blog.csdnimg.cn/img_convert/f5b0a4c3445a6126d242c61166fbcb4a.png)

##### Web缓存

<mark>使用Web缓存的原因</mark>：**减少客户端请求的响应的时间；改善用户体验；节省主干带宽的流量**

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

###### Web缓存(proxy cache)

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
> 对于1个请求（1去1回）：总的延时为=因特网延时+链路延时+局域网延时=(2s+2/15s+2/100s)x60%+(2/100)x40%=1.37s
>
> 可见，对延时的改善效果很好

###### Conditional Get

保存在服务器中的对象自该副本缓存在客户上以后可能已经被修改了。HTTP协议有一种机制，<mark>允许缓存器证实它的对象是最新的</mark>。这种机制就是条件GET（conditional GET）方法。如果：①请求报文使用 GET方法；并且②请求报文中包含一个“If-Modified-Since;”首部行。那么，这个HTTP 请求报文就是一个条件 GET 请求报文。

缓存器在将对象转发到请求的浏览器的同时，也会在本地缓存了该对象，同时会存储最后修改日期。只有当缓存对象被修改了才从服务器中发送对象，否则直接读取cache中的对象。

###### distributed cache

许多缓存是合作的

> 本地访问丢失，缓存链接邻居
>
> 通过http或ICP

如果邻居没有数据，则访问源服务器

操作不便，一般采用镜像服务器

###### server cache:cluster

多台服务器以集群方式构造

内容相同或不同的内容

连接被传输到轻载服务器(缓存)

> 高并行性、可靠性
>
> 负载均衡需要
>
> 所需目标定位算法

受到广泛的采用

#### 4、FTP

FTP：file transfer protocol：主机之间进行文件传输的协议，建立在TCP之上

采用客户端/服务器模型

##### 采用独立的控制连接和数据连接

在进行文件传输时，FTP的客户机和服务器之间要建立两个TCP连接，一个用于传输控制命令和响应，称为**控制连接**(21)，一个用于实际的文件内容传输，称为**数据连接**(20)

> 客户端通过**控制连接**发送命令浏览远程目录：<mark>out of band</mark>
>
> > 传输层协议使用带外数据（out-of-band，OOB）来发送一些重要的数据，如果通信一方有重要的数据需要通知对方时，协议能够将这些数据快速地发送到对方。为了发送这些数据，协议一般不使用与普通数据相同的通道，而是使用另外的通道。
>
> 当服务器收到文件传输命令时，服务器打开第二次TCP连接(文件)到客户端：<mark>in band</mark>
>
> <mark>传输一个文件后，服务器关闭数据连接。</mark>——非持续连接

<mark>FTP是有状态协议,会保留用户的登录信息和在远程的操作</mark>——stateful

#### 5、电子邮件

##### 三个主要组件

> 用户代理（如浏览器）user agent
>
> 邮件服务器 mail server
>
> 简单邮件传输协议:SMTP

##### SMTP

使用TCP在客户端和服务器之间可靠地传输邮件消息，<mark>**端口号为25**</mark>

直连:发送服务器到接收服务器，没有通过中间服务器

> 命令:ASCII文本
>
> 响应:状态码和短语

SMTP使用的是<mark>持续连接</mark>

###### 与HTTP比较

**相同点**

> 这两个协议都用于从一台主机向另一台主机传送文件
>
> 当进行文件传输时，持续的HTTP和SMTP都使用持续连接

**区别**

> :one: HTTP是一个拉协议(pull protocol)：TCP连接是由想要接收文件的机器发起的；SMTP是一个推协议(push protocol)：TCP连接是由要发送该文件的机器发起的
>
> :two: SMTP要求每个报文使用7bitASCII码格式，HTTP数据不受这种限制
>
> :three: HTTP将每个对象封装到他自己的HTTP响应报文中，请求报文中不含有对象，而SMTP将所有报文对象都放在一个报文中

##### 邮件访问协议

SMTP:发送/存储到接收者的服务器

邮件访问协议:从服务器取回邮件

![image-20220403193312668](https://img-blog.csdnimg.cn/img_convert/fec2e77d12a88d7e90465bc36a91aaa2.png)

:one: POP3: Post Office Protoco-version 3 第三版邮局协议

> 授权(代理<——>服务器)和下载，端口:110
>
> POP3跨会话是<mark>无状态的</mark>

:two: IMAP: 因特网邮件访问协议

> 更多功能(更复杂)，端口:143
>
> 操作服务器上存储的MSGS
>
> IMAP保持用户跨会话的状态:<mark>有状态的</mark>

:three: HTTP: gmail, Hotmail, Yahoo邮件等。

##### MIME

MIME：Multipurpose Internet Mail Extension**多用途互联网邮件扩展**，RFC 2045, 2056

对于非ASCII文本，需要在msg中添加额外的头信息

消息头中的其他行声明MIME内容类型

> 内容类型:提醒接收器使用哪个显示程序
>
> 内容传输编码: ASCII编码时使用的编码类型

##### 邮件传输过程

Alice发邮件给Bob

:one: Alice调用她的邮件代理程序并提供Bob的邮件地址（例如bob@ someschool.edu)，撰写报文，然后指示用户代理发送该报文。

:two: Alice的用户代理把报文发给她的邮件服务器，在那里该报文被放在报文队列中。

:three: 运行在Alice的邮件服务器上的SMTP客户端发现了报文队列中的这个报文，它就创建一个到运行在 Bob 的邮件服务器上的SMTP 服务器的 TCP 连接。

:four: 在经过一些初始SMTP握手后，SMTP客户通过该TCP连接发送Alice的报文。

:five: 在Bob的邮件服务器上，SMTP的服务器端接收该报文。Bob的邮件服务器然后将该报文放人 Bob 的邮箱中。

:six: 在Bob方便的时候，他调用用户代理阅读该报文

![image-20220403145842097](https://img-blog.csdnimg.cn/img_convert/495b2d2493b51b229ee1c1c932b7781e.png)

#### 6、DNS：域名服务

主机的一种标识方法是用它的主机名hostname，例如www.baidu.com

也可以使用IP地址进行标识，例如192.168.1.202

域名：baidu.com

<mark>IP和域名是多对多的关系</mark>

ps：<mark>DNS属于网络内核的功能，但是放在网络边缘</mark>

> 因特网：“瘦内核，胖边缘”
>
> ATM网络：“胖内核，瘦边缘”

<mark>DNS运行在UDP上，使用53号端口</mark>

DNS是:one: 一个由分层的DNS服务器实现的分布式数据库 :two: 一个使得主机能够查询分布式数据库的应用层协议

##### DNS服务

1. 主机名到IP名的转换
2. 主机别名
3. 邮件服务器别名
4. 负载分配

##### DNS层次结构

![image-20220403193754596](https://img-blog.csdnimg.cn/img_convert/153fa425aeb35a80573e4117a4679478.png)

**根DNS服务器**：400多个，13个组织管理

> 根域名服务器用来管辖顶级域名，它并不直接把查询得域名转换成IP地址，而是告诉本地域名服务器应当找哪一个顶级域名服务器进行查询

**顶级域(TLD)DNS服务器**

**注册DNS服务器**：记录项一直存在，只要不欠费

> 负责一个区的域名服务器

本地DNS服务器：不属于上面的层次结构，通常与主机位于同一个局域网中。

##### 域名解析过程

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

本地 DNS 服务器也能够缓存 TLD 服务器的 IP 地址，因而允许本地 DNS 绕过查询链中的根 DNS 服务器。事实上，因为缓存，除了少数 DNS查询以外，根服务器被绕过了

### 名词解释

**URL**：uniform resource locator，即统一资源定位系统，是因特网的万维网服务程序上用于指定信息位置的表示方法

**HTML**：Hyper Text Markup Language,即超文本标记语言，它包括一系列标签．通过这些标签可以将网络上的文档格式统一，使分散的Internet资源连接为一个逻辑整体。

**RTT**：Round trip time，即往返时间，一个小数据包在客户端和服务器之间往返传输的时间。

**MIME**：Multipurpose Internet Mail Extensions，即多用途互联网邮件扩展类型。是设定某种扩展名的文件用一种应用程序来打开的方式类型，当该扩展名文件被访问的时候，浏览器会自动使用指定应用程序来打开。多用于指定一些客户端自定义的文件名，以及一些媒体文件打开方式。

**TFTP**：Trivial File Transfer Protocol,即简单文件传输协议，是TCP/IP协议族中的一个用来在客户机与服务器之间进行简单文件传输的协议，提供不复杂、开销不大的文件传输服务。端口号为69。

**NFS**：Network File System，即网络文件系统，是基于UDP/IP协议的应用，其实现主要是采用远程过程调用RPC机制，RPC提供了一组与机器、操作系统以及低层传送协议无关的存取远程文件的操作。

**SNMP**：Simple Network Management Protocol，即简单网络管理协议，是专门设计用于在 IP 网络管理网络节点（服务器、工作站、路由器、交换机及HUBS等）的一种应用层协议。

**JPEG**：Joint Photographic Experts Group,即联合图像专家组，是用于连续色调静态图像压缩的一种标准，文件后缀名为.jpg或.jpeg，是最常用的图像文件格式

**MPEG**：Moving Picture Experts Group，即动态图像专家组是，ISO（International Standardization Organization，国际标准化组织）与IEC（International Electrotechnical Commission，国际电工委员会）于1988年成立的专门针对运动图像和语音压缩制定国际标准的组织。

**ASCII** ((American Standard Code for **Information Interchange**): 美国信息交换标准代码）是基于[拉丁字母](https://baike.baidu.com/item/拉丁字母/1936851)的一套电脑[编码](https://baike.baidu.com/item/编码/80092)系统，主要用于显示现代[英语](https://baike.baidu.com/item/英语/109997)和其他[西欧](https://baike.baidu.com/item/西欧/3028649)语言

### 练习题

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

10、TCP是公平的的协议，但当两个TCP连接共享一个瓶颈链路时，它们在这条瓶颈链路上获得的带宽并**不自始至终是一样的**。



## 第三章 传输层

传输层协议为运行在不同主机上的<mark>**应用进程之间**</mark>提供了<mark>**逻辑通信( logic communication)**</mark>功能。

传输层协议是<mark>**在端系统中而不是在路由器中实现的**</mark>。

因特网网络层协议有一个名字叫IP,即网际协议。IP为主机之间提供了逻辑通信。IP 的服务模型是尽力而为交付服务( best- effort delivery service)。这意味着IP尽它“最大的努力”在通信的主机之间交付报文段，但它并不做任何确保。特别是，它<mark>不确保报文段的交付，不保证报文段的按序交付</mark>，不保证报文段中数据的完整性。由于这些原因，IP被称为不可靠服务( unreliable service)。 在此还要指出的是,每台主机至少有一个网络层地址，即所谓的IP地址。

UDP和TCP最基本的责任是，**将两个端系统间IP的交付服务扩展为运行在端系统上的两个进程之间的交付服务**。将主机间交付扩展到进程间交付被称为传输层的多路复用(transport- layer multiplexing)与多路分解( demultiplexing)。

:apple: <mark>**传输层的功能**</mark> 

> :one: 提供应用进程之间的逻辑通信（网络层提供主机之间的逻辑通信）
>
> :two: 提供复用与分用
>
> :three: 差错检测
>
> :four: 提供无连接的或面向连接的服务

### 概念与应用

#### 1、多路复用与多路分解

套接字是同一台主机内**应用层与传输层**之间的接口。由于该套接字是建立网络应用程序的可编程接口，因此套接字也称为<mark>应用程序和网络之间的应用程序编程接口（API）</mark>

![image-20220317153846421](https://img-blog.csdnimg.cn/img_convert/a3de00ab703c8c6a0ed4d12c1d0430ef.png)

每个传输层报文段中具有几个字段。在接收端，传输层检查这些字段，标识出接收套接字，进而将报文段定向到该套接字。**将传输层报文段中的数据交付到正确的套接字的工作称为<mark>多路分解</mark>**（demultiplexing）。

> 类似于打开微信和qq，qq的消息不会给微信，微信的消息不会给qq

**在源主机从不同套接字中收集数据块，并为每个数据块封装上首部信息从而生成报文段，然后将报文段传递到网络层，所有这些工作称为<mark>多路复用（multiplexing）</mark>**

> 发送端进行多路复用；接收端进行多路分解
>
> 例如小明从邮递员收到新建，并通过查看收信人姓名而将信件交付给他的朋友时执行的就是多路分解；而当小美从朋友手中收集信件并交给邮递员时，执行的就是多路复用

##### UDP多路复用与多路分解

此时，<mark>一个UDP套接字是由一个二元组全面标识的</mark>，该二元组包括一个目的IP地址和一个目的端口号

> 具有不同源IP地址和源端口号，但是具有相同目的IP地址和目的端口号的两个报文段会通过同一个套接字被送到同一个目的进程中。

报文段中的源端口号和源IP地址可以作为报文段回发时的返回地址使用

![image-20220315105623139](https://img-blog.csdnimg.cn/img_convert/472e932d9e67712cc15d1e1e22c9e952.png)

##### TCP多路复用与多路分解

<mark>一个TCP套接字需要一个四元组</mark>

> 源IP地址和目的IP地址
>
> 源端口号和目的端口号

> 具有不同源IP地址和源端口号，但是具有相同目的IP地址和目的端口号的两个报文段会被送到**不同的套接字中**

每个带有端口号的客户端应用程序都指向服务器上的套接字

![image-20220315105830413](https://img-blog.csdnimg.cn/img_convert/60ef5f767fa267ba93592a25f4c4a4d9.png)

#### 2、UDP|User Datagram Protocol :用户数据报协议——极为重要

UDP的段叫数据报

UDP只具有传输协议能够做的最少工作：**多路复用/多路分解；差错检测**。几乎是直接跟IP打交道

使用UDP时，在发送报文段之前，发送方和接收方的传输层实体之间没有握手，因此UDP被称为是<mark>*connectionless*</mark>

提供的也是"best effort"服务

> 交付报文段，但它并不做任何确保。特别是，它<mark>不确保报文段的交付，不保证报文段的按序交付</mark>，不保证报文段中数据的完整性。

<mark>**特征**</mark>

> :one: 实现简单：发送方、接收方没有连接状态
>
> :two: 数据段首部head小(8字节)，传输开销小，时延较短
>
> :three: 速度快：不用控制

<mark>**典型应用**</mark>

> Remote file server (NFS)
>
> Streaming multimedia流式多媒体
>
> Internet telephony
>
> Network management
>
> Routing protocol(RIP)
>
> Name translation (DNS)
>
> Multicasting 
>
> Real-time involved apps(RTP)实时传输协议
>
> TFTP
>
> DHCP动态主机配置协议

##### UDP报文段结构

**段头只有8个字节**：源端口号、目的端口号、长度、校验和各2B

![image-20220317155726844](https://img-blog.csdnimg.cn/img_convert/8c801456e8d94c5050bb665fcb1b4cdd.png)



##### UDP校验和checksum

UDP检验和提供了差错检测功能。这就是说，检验和用于确定当UDP报文段从源到达目的地移动时，其中的比特是否发生了改变(例如，由于链路中的噪声干扰或者存储在路由器中时引入问题)。

**发送方**

> :one: 将段内容视为16位整数序列
>
> > 0110011001100110
> > 0101010101010101
> > 0000111100001111
>
> :two: 对段内容相加，取低16位，按位取反，得到校验和
>
> > 相加得到1100101011001010
> >
> > 按位取反得到0011010100110101
>
> :three: 发送方将校验和0011010100110101输入UDP校验和字段

**接收方**

> :one: 将段内容视为16位整数序列
>
> > 0110011001100110
> > 0101010101010101
> > 0000111100001111
>
> :two: 对段内容相加，取低16位
>
> > 相加得到1100101011001010
>
> :three: 与checksum再相加，检查是否全为1（这里相加就是1111111111111111）
>
> > NO -检测到错误
> >
> > YES -没有检测到错误
> >
> > 但是，不能纠正

这种检错能力很弱

#### 3、TCP|Transmission Control Protocol传输控制协议——极为重要

##### TCP概述

<mark>**特点**</mark>

> :one: 点对点：一个发送方，一个接收方(不能用于多播)
>
> :two: 可靠的、字节有序的流式发送数据：没有“报文边界”
>
> :three: 流水线式：TCP拥塞和流量控制设置窗口大小
>
> :four: 需要开辟发送和接收缓冲区
>
> :five: 全双工数据full duplex data：在同一连接中双向数据流；(UDP也是)

**提供的服务**

> <mark>三控一管</mark>：**连接管理、可靠性控制、流量控制、拥塞控制**

![image-20220403211900515](https://img-blog.csdnimg.cn/img_convert/9b01aa2d015074577bc85e365aa36e2f.png)

所谓的连接只是逻辑上的，发送方和接收方开辟缓存，设置变量，交换序列号

##### 段文格式

仅从TCP报文段的首部是无法得知目的IP地址的。因此，TCP必须告诉IP层此报文段要发送给哪一个目的主机（给出其IP地址）。**此目的IP地址填写在IP数据报的首部**中。

<img src="https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/qshell/image-20220315113207545.png" alt="image-20220315113207545" style="zoom:50%;" />

TCP段头指的是前面五行，一共<mark>**20个字节**</mark>

:one: 源端口号和目的端口号：各占2B

:two: **序列号**：TCP是面向字节流的，传送时按照一个个字节传送，所以在一个TCP连接中传送的字节流需要编号，这样才能保证按序交付

> 例如，某报文段的序号从301开始，而携带的数据共有100B.这就表明本报文段数据的第一个字节的序号是301,最后一个字节的序号是400.显然，下一个报文段（如果还有）的数据序号应当从401开始，即下一个报文段的序号字段应为401,这个字段名也称为“报文段序号”。

:three: **确认号**acknowledgement number：占4B。TCP是含有确认机制的，所以**接收端需要给发送端发送确认号**，这个确认号只需记住一点：若确认号等于N,则表明到序号N-1为止的所有数据都已经正确收到。

> 例如，B正确收到了A发送过来的一个报文段，其序号字段值是501,而数据长度是200B（序号501~700),这表明B正确收到了A发送的到序号700为止的数据。因此，**B期望收到A的下一个数据序号**是701,于是B将发送给A的确认报文段中的确认号设置为701.注意，现在的确认号不是501,也不是700,而是701.

:four: 首部长度

:five: 保留字段：占6位。保留为今后使用，但目前应置为0,该字段可以忽略不计。

:six: 紧急 URG:当URG=1时，表明紧急指针字段有效。它告诉系统此报文段中有紧急
数据，应尽快传送（相当于高优先级的数据）

:seven: **确认比特ACK**:只有当ACK=1时，确认号字段才有效；当ACK=0时，确认号无效。TCP规定，一旦连接建立了，所有传送的报文段都必须把ACK置1.

:eight: 推送比特PSH:TCP收到推送比特置1的报文段，就尽快地交付给接收应用进程，而不再等到整个缓存都填满后再向上交付。

:nine: 复位比特RST:当RST=1时，表明TCP连接中出现严重差错（如由于主机崩溃或其他原因），必须释放连接，然后再重新建立传输连接。

:one: :zero: 同步比特SYN:同步比特SYN置为1,表示这是一个连接请求或连接接收报文，后面的TCP连接会详细讲到。

:one: :one: 终止比特FIN:释放一个连接。当FIN=1时，表明此报文段的发送端的数据已发送完毕，并要求释放传输连接。

:one: :two: **窗口字段rcvr window size**：占2B.窗口字段用来控制对方发送的数据量，单位为字节（B).记住一句话：**窗口字段明确指出了现在允许对方发送的数据量**。例如，设确认号是701,窗口字段是1000.这就表明，从701号开始算起，发送此报文段的一方还有接收1000B数据的接收缓存空间。

:one: :three: 校验和字段：占2B.校验和字段检验的范围包括首部和数据两部分。在计算校验和时，和UDP一样，要在TCP报文段的前面加上12B的伪首部（只需将UDP伪首部的第4个字段的17改为6,其他和UDP一样）。

:one: :four: 紧急指针字段：占2B.前面已经讲过紧急指针指出在本报文段中的紧急数据的最后
一个字节的序号。

:one: :five: 选项字段：长度可变。TCP最初只规定了一种选项，即最大报文段长度MSS.MSS告诉对方TCP:“我的缓存所能接收的报文段的数据字段的最大长度是MSS字节。”

:one: :six: 填充字段：为了使整个首部长度是4B的整数倍。

##### 可靠性控制

<mark>**丢包重传**</mark>

:one: **发送方**:

**重新发送丢失的片段**，未被正确接收前一直存在发送方的缓存区

需要开辟发送缓冲区（发送窗口）：开始指针：send_base（指向发送缓存的最左侧），窗口大小：n，下一个序列号：nextseqnum

> 当有数据需要发送时，检查nextseqnum是否有效，有效就将其封装成TCP的段发送，发送后将nextseqnum右移，一直移动到nextseqnum-send_base=N，此时窗口就满了，应用层再给数据就无法发送了。接收方确认数据收到，然后发送窗口右移。

![image-20220315114330990](https://img-blog.csdnimg.cn/img_convert/d8177a05e02af9cc72d3ea644d47208a.png)

:question: 如何知道段丢失了吗? 

> 接收方发送确认段序号
>
> 计时器时间到了还没收到确认信号就认为段丢了，重传

:question: 如何知道哪些部分丢失了? 序列号

:two: **接收方**:

<mark>对期望的那个段进行确认</mark>——返回的段序号是它期待的那个段的段序号

需要开辟接收缓冲区：开始指针：rcv_base（指向的段序号就是接收方期待的段的段序号）以及窗口大小N

![image-20220315115032358](https://img-blog.csdnimg.cn/img_convert/1445985d0825e6856b04d99bb49e5f16.png)

> 第一个灰色：rcv_base指向的段序号就是接收方期待的段的段序号
>
> 紫红色：收到了这个段，没有差错，先缓存起来，但是不能送给应用层，如果送给应用层会乱序（不可靠），因为期待的那个段还没有到，到了一起送
>
> 第二个灰色：还没有收到，下一个期待的段
>
> 蓝色：空闲接收缓存

**示例**

后一个确认号=前一个段的段序号+前一个段的数据域长度

Seq：段数据的第一个字节的字节流“数”，随机选择一个初始序列号

ACK：从另一端期望的下一个字节的Seq

![image-20220403223248634](https://img-blog.csdnimg.cn/img_convert/8c1c1e5455415e73104be48216b91526.png)

##### 流量控制

**TCP为它的应用程序提供了流量控制服务，以消除发送方使接收方缓存溢出的可能性。流量控制是一个速度匹配服务，即发送方的发送速率和接收方应用程序的读取速率相匹配。**

TCP通过让<mark>发送方</mark>维护一个称为**接收窗口receive window**的变量来提供流量控制，接收窗口用于给发送方一个指示——**接收方还有多少可用的缓存空间**。接收缓存中的空闲空间。

![image-20220404100443212](https://img-blog.csdnimg.cn/img_convert/ca5d5105769e3b33fd3a0732a265a447.png)

**TCP段头中有一个字段表示接收窗口的大小。**

假设主机A通过一条TCP连接向主机B发送一个大文件，主机B为该连接分配了一个接收缓存，并用RcvBuffer来表示其大小，主机B上的应用进程不时地从该缓存中读取数据，有如下变量定义

> LastByteRead：主机B上的应用进程从缓存读出的数据流的最后一个字节的编号。（被读走的最后一个段序号）
>
> LastByteRcvd：从网络中到达的并且已放入主机B接收缓存中的数据流的最后一个字节的编号。（刚收到的段的段序号）

由于 TCP 不允许已分配的缓存溢出，下式必须成立：
$$
LastByteRcvd-LastByteRead≤RcvBuffer
$$
接收窗口(空闲缓存)用RcvWindow（rwnd）表示，根据缓存可用空间的数量来设置：
$$
RcvWindow = RcvBuffer - [ LastByteRcvd - LastByteRead]
$$
主机 B 通过把**当前的RcvWindow值放入它发给主机 A 的报文段接收窗口字段**中，通知主机 A 它在该连接的缓存中还有多少可用空间。开始时，主机B设定RcvWindow = RcvBuffer。

主机A 轮流跟踪两个变量，LastByteSent 和 LastByteAcked，这两个变量的意义很明显。注意到这两个变量之间的差LastByteSent - LastByteAcked，就是**主机A发送到连接中但未被确认的数据量**。通过将未确认的数据量控制在值 rwnd 以内，就可以保证主机 A 不会使主机B的接收缓存溢出。因此，主机A在该连接的整个生命周期须<mark>**保证**</mark>：
$$
LastByteSent-LastByteAcked≤rwnd
$$
对于这个方案还存在一个小小的技术问题。为了理解这一点，假设主机B的接收缓存已经存满，使得rwnd=0。在将rwnd=0通告给主机A之后，还要假设主机B没有任何数据要发给主机A。此时，考虑会发生什么情况。因为主机B上的应用进程将缓存清空，TCP并不向主机A发送带有rwnd新值的新报文段；事实上，**TCP仅当在它有数据或有确认要发时才会发送报文段给主机A**。这样，主机A不可能知道主机B的接收缓存已经有新的空间了，即主机A被阻塞而不能再发送数据！为了解决这个问题，TCP规范中要求：**当主机B的接收窗口为0时，主机A继续发送只有一个字节数据的报文段**。这些报文段将会被接收方确认。**最终缓存将开始清空，并且确认报文里将包含一个非0的rwnd值**。

**举例**

> 主机A和主机B通过100Mbps的链路直连。两台主机之间有一个TCP连接，主机A通过这个连接向主机B发送一个大文件。主机A可以以高达120 Mbps的速率将应用程序数据发送到它的TCP套接字中，但是主机B可以以最高50 Mbps的速率从它的TCP接收缓冲区读取数据。描述TCP流控制的效果。
>
> > 答：
> >
> > 因为$100Mbps<120Mbps$，所以主机A的发送速率最多可以达到100Mbps。
> >
> > 但是$100Mbps>50Mbps$，主机A向接收缓冲区发送数据的速度还是比主机B从接收缓冲区获取数据的速度快。
> >
> > 接收缓冲区以大约50Mbps的速度填满。当缓冲区已满时，主机B设置RcvWindow = 0。**当主机B的接收窗口为0时，主机A继续发送只有一个字节数据的报文段**。这些报文段将会被接收方确认。**最终缓存将开始清空，并且确认报文里将包含一个非0的rwnd值（新的接收窗口大小）**。平均而言，主机A发送数据到主机B的速率会是50Mbps

##### TCP连接管理(重要)

<mark>连接的开启：三个握手</mark>

> :one: 发送端(客户端)给接收端(服务器端)发送一个SYN段(在 TCP 标头中 SYN 位字段为 **1** 的 TCP/IP 数据包), 该段中也包含客户端的初始序列号(序列号Sequence number = J)。
>
> > 同步比特SYN:同步比特SYN置为1,表示这是一个连接请求或连接接收报文
>
> :two: 接收端返回给发送端 SYN +ACK 段(在 TCP 标头中SYN和ACK位字段都为 1 的 TCP/IP 数据包)， 该段中包含接收端的初始序列号(序列号 = K)；同时使确认号Acknowledgement number = J + 1来表示确认已收到客户端的 SYN段(序列号 = J)。
>
> > 第二次握手，接收端开辟缓存
>
> :three: 发送端给接收端响应一个ACK段(在 TCP 标头中 ACK 位字段为 **1** 的 TCP/IP 数据包), 该段中使 确认号 = K + 1来表示确认已收到服务器的 SYN段(序列号 = K)。
>
> > 第三次握手，发送端开辟缓存

![image-20220404103932103](https://img-blog.csdnimg.cn/img_convert/8172e0d61a16527d6a44fb28cbb3b6fe.png)

一旦完成这三个步骤，客户和服务器主机就可以互相发送包括数据的报文段了。

**DoS攻击**：半连接攻击(第三个握手永远不做，没完没了发送连接请求，使服务器端不断开辟缓存，使服务器崩溃)

> 很多台客户端攻击叫DDos

<mark>连接的关闭：两次握手</mark>（四次挥手）

> :one:客户端系统向服务器发送TCP FIN控制段
>
> > 终止比特FIN:释放一个连接。当FIN=1时，表明此报文段的发送端的数据已发送完毕，并要求释放传输连接。
>
> :two:服务器接收到FIN，返回ACK。关闭连接，发送FIN。
>
> :three:客户端收到FIN，以ACK回应。
>
> 计时器定时等待
>
> :four:服务器，接收ACK。连接关闭。
>
> timed wait后TCP才真正的关闭
>
> ![image-20220404103944641](https://img-blog.csdnimg.cn/img_convert/20f3f65f358d3f876f58a26101b9478e.png)

##### 拥塞控制Congestion Control

拥塞指的是路由器拥塞：有太多的发送端发送数据，发的太快

表现：丢包(路由器缓冲区溢出)；长延迟(在路由器缓冲区中排队)

###### 拥塞控制方法分类

:one: **网络帮助的拥塞控制**

ATM([异步传输模式](https://baike.baidu.com/item/异步传输模式/511955))的内核设备不叫路由器，叫ATM交换机（胖内核，瘦端系统，内核功能强大），拥塞以后通知发送端——**网络帮助的拥塞控制**

> 交换机直接通知给发送端
>
> 交换机通知给接收端，接收端再通知发送端（用的更多）

:two: **端到端的拥塞控制**

因特网是瘦内核，胖端系统的网络，端系统功能强大（如DNS就放在端系统），路由器的功能尽量简单，拥塞了不通知发送端，靠端系统自行感知。

> 超时了说明网络重度拥塞（端到端的延时跟距离有关）
>
> 收到三个相同ACK（丢包）说明网络轻度拥塞——通常采用此方法

###### TCP拥塞控制（重要）

对于TCP中的拥塞，一共有两种判断

> 超时了说明网络重度拥塞（端到端的延时跟距离有关）
>
> 收到三个相同ACK（丢包）说明网络轻度拥塞——通常采用此方法

**探测拥塞**

> 为了探测网络是否拥塞,   先发一个段探测一下,   如果这个段的确认信息正确返回，则没有问题； 下一个RTT开始时发送两个段,   如果还没有问题,   下一个RTT开始时发送四个段, ……，每个RTT发送窗口以2的倍数增加，经过若干次探测之后,  此时还没丢包，发送窗口不能再x2, 每个RTT开始时窗口大小+1 （慢慢地增加）

发送窗口以2的倍数增加的过程叫**慢启动**,  经过若干RTT后+1过程叫做**拥塞避免**，这两个结合起来就是TCP的拥塞算法

###### 拥塞避免中的吞吐率

设W是丢包时的窗口大小（以段为单位）

> 当窗口大小是W，吞吐率=$W/RTT$
>
> 当发生了丢包，窗口大小减少为W/2，吞吐率=$W/2RTT$
>
> 平均窗口大小为(W+W/2)/2=0.75W，平均吞吐率=$0.75W/RTT$

如果直到丢包率L，MSS为最大段大小(可要可不要），平均吞吐率为
$$
\approx \frac{1.22MSS}{RTT\sqrt{L}}
$$


推导过程如下：

> 在拥塞避免期间，发送窗口大小从w/2变化到w(段)
>
> 第一个RTT，窗口大小=w/2
>
> 第二个RTT，窗口大小=w/2+1
>
> 第三个RTT，窗口大小=w/2+2
>
> ……
>
> 当丢包时，窗口大小=w/2+w/2=w
>
> 在此期间发送的段的总数(包数)
> $$
> \frac{w}{2}(\frac{w}{2}+1)+(1+2+,...+\frac{w}{2})=\frac{3w^2}{8}+\frac{3w}{4}\approx \frac{3w^2}{8}
> $$
> 总共丢了一个包，丢包率为
> $$
> L=\frac{8}{3w^2}\Rightarrow w=\sqrt{\frac{8}{3L}}
> $$
> 平均吞吐率则为
> $$
> \frac{3}{4}w/RTT=\frac{3}{4RTT}\sqrt{\frac{8}{3L}}\approx \frac{1.22MSS}{RTT\sqrt{L}}
> $$

###### 算法总结

TCP拥塞避免算法：AIMD：radditive increase, multiplicative decrease

> 线性增加，指数减少
>
> 每一个RTT增加一次窗口；每次丢包减少为原来窗口大小的1/2

<mark>这个算法具有四个特性</mark>（也称为TCP的四个特性）

> :one: 有效性：Effectiveness
>
> :two: 收敛性
>
> :three: 公正性：Fairness
>
> > 如果N个TCP会话共享同一条瓶颈链路，则每个会话的链路容量应为1/N
> >
> > 没有绝对的公平
>
> :four: 友好性：Friendliness
>
> > 如果TCP和UDP用户共同使用带宽。如果两者发送数据的速度>R(路由器最大交换能力),TCP就会将发送速率降一半，腾出资源给UDP，最终TCP只能发送一个段，资源几乎都给了UDP

### 名词解释

**UDP**：User Datagram Protocol用户数据报协议

**TCP**：Transport Control Protocol传输控制协议

**FSM**：Finite State Machine有限状态机

**EFCI**：Explicit Forward Congestion Indication明确转发拥塞指示

**AIMD**：Aadditive Increase, Multiplicative Decrease线性增加，指数减少

**MSS**：Maximum Segment Size最大段大小

Exponential weighted moving average**指数加权运动平均**；**给定样本的影响以指数速度递减**

### 算法

#### TCP可靠性控制伪代码

**发送端**

```java
send_base = init;
nextseqnum = init;
loop(forever){
    switch(event):
    	event:data reveived from application above
            if(nextsequm-send_base<N){
                create TCP segment with sequence number nextseqnum;
                start timer for segment nextseqnum;
                pass segment to IP;
                nextseqnum = nextseqnum + length(data);
            }else{
                refuse to send segment;
            }
    	event:timer timeout for segment with sequence number y
    		retransmit segment with sequence number y;
    		compute new timeout interval for segment y'
            restart time for segement y;
    	event:ACK received,with ACK field value of y
            if(y>send_base){
                cancel all timers for segments with sequence numbers<y;
                send_base = y;
            }else if(y==send_base){
                increment number of duplicated ACKs reveived for y;
                if (number of duplicate ACKS received for y == 3) { 
                      /* TCP fast retransmit */ 
                     resend segment with sequence number y ;
                     restart timer for segment y ;

            }
            
}

/*发送端可靠性控制伪代码*/
/*假设发送方不受TCP流量和拥塞控制，来自上层数据的长度小于MSS，而且数据传输只在一个方向进行*/
send_base = init_sequence number;
nextseqnum = init_sequence number;
loop(永远){
	switch(事件)：
        事件：应用层有数据让TCP传输
        	if(nextsequm-send_base<N){
                创建段序号为nextseqnum的段;
                启动计时器;
                将段发送给IP层；
                nextseqnum=nextseqnum+数据长度;
            }else{
                拒绝发送段;
            }
		事件：段序号为y的段的计时器超时
            重传这个段;
            重新计算计时器超时间隔;
		    重启计时器;
		事件：接收到ACK，字段值为y
            if(y>send_base){
                取消掉段y之前的所有的段的计时器;
                send_base=y;
            }else if(y=send_base){
                对y收到的冗余ACK数+1;
                if(y收到的冗余ACK数==3){
                    快速重传段y;
                    重启段y的计时器;
                }
            }
}
```

**接收端**

![image-20220315115032358](https://img-blog.csdnimg.cn/img_convert/569cf49a71f13244a609d64e7aa6954e.png)

| 编号 | 事件                                                         | TCP接收端动作                                                |
| ---- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| ①    | 有序到达一个段，中间没有间隙，所有的其他段都被确认过了       | 做一个延迟，等待下一个段500ms，如果下一个段到来了一起确认，没有到来的话发送ACK |
| ②    | 有序到达一个段，中间没有间隙，有一个ACK在做延时              | 不能再做延时，立即发送一个ACK以确认两个有序段                |
| ③    | 比期望序号大的报文段乱序到达，检测出数据流中的间隔（如红色部分） | 立即发送一个冗余ACK（跟前一个一样），ACK为期待的段的段序号（rcv_base指向的段的段序号） |
| ④    | 到达一个段，这个段部分或全部的填满了间隔                     | 若该报文段起始于间隔的低端，则立即发送ACK<br />如果是rcv_base指向的段（左边灰色的），则这个段变为红色，连同身后红色的段一起送给应用层，接收窗口右移到下一个期待的段（右边灰色的）；否则（右边灰色的），则这个段变为红色，返回一个ACK，ACK为期待的段的段序号（rcv_base指向的段的段序号）<br /><br />总之要补齐gap |
| ⑤    | 如果收到一个段位于窗口左侧                                   | <mark>将其丢弃（这种情况是老师上课补充的）</mark>            |

**举例分析如下**

![image-20220404003400090](https://img-blog.csdnimg.cn/img_convert/8954c5bd3496d47a362f5083a008bf57.png)

#### 拥塞控制

**算法中的重要变量**

> :one: 从慢启动到拥塞避免的分界线用一个变量threshold（阈值）表示
>
> :two: Congwin：拥塞窗口大小（拥塞控制时使用的发送窗口）

慢启动算法伪代码表示

```c
initialize: threshold=appropriate_value
initialize: Congwin = 1    
for (each segment ACKed)
      Congwin++
until (loss event OR CongWin > =threshold)

初始化：threshold=适当的值(10、20...不要太大)
初始化：Congwin=1
 for(每个确认段)
     Congwin++
 until(丢包orCongWin>=threshold)
```

> 💻 这里是说每收到一个对新的报文段的确认后，拥塞窗口就+1，第一轮收到1个确认，第二轮2个，第三轮4个，以此类推，按轮次加倍
>
> ![image-20220404121251789](https://img-blog.csdnimg.cn/img_convert/e9f4576f4b389a41eafdff946c84735d.png)

**Tahoe 拥塞避免算法伪代码**

```c
/*slowstart is over*/
/*Congwin>threshold*/
while(no loss event){
    every w segments Acked:
    	Congwin++;
}
threshold = Congwin/2;
Congw=1;
perform slow start;

/*慢启动结束*/
 while (没有丢包) {
   每w个段被确认:
       Congwin++/*每个RTT，窗口+1*/
 }/*线性增加*/
 /*丢包了*/
 threshold = Congwin/2
 Congwin = 1
 进行慢启动
```

![image-20220404121844273](https://img-blog.csdnimg.cn/img_convert/82633210c38fa8e5edfb97d6e81d64af.png)

轻度拥塞也会时发送窗口变为1，显得不太合理

**Reno 拥塞避免算法伪代码**

```c
while(no loss event){
    for every w segments ACKed:
    	Congwin++;
    threshold = Congwin/2;
    if(loss detected by timeout){
        Congwin = 1;
        perform slow start;
    }
    if(loss detect by reveiving triple duplicated ACK){
        Congwin=Congwin/2;
    }
} 

/*慢启动结束*/
 while (没有丢包) {
   每w个段被确认:
       Congwin++/*每个RTT，窗口+1*/
 }/*线性增加*/
 /*丢包了*/
 threshold = Congwin/2
 if(因为超时丢包){/*重度拥塞*/
     Congwin = 1
     进行慢启动/*回到慢启动*/
 }
 if(因为收到三个相同确认段丢包){/*轻度拥塞*/
     Congwin = Congwin /2/*回到while*/
 }/*快速恢复*/
```

![image-20220404124733928](https://img-blog.csdnimg.cn/img_convert/b27f9bfd6c97aa49e2689283674b6fd4.png)

问题：如果又收到三个相同的确认段，此时应该再减半

**两个算法的比较**

<img src="https://img-blog.csdnimg.cn/img_convert/1eb6430d4808472d0be8b46822c20423.png" alt="img" style="zoom:67%;" />

Reno算法的吞吐率更高，震荡率更小

### 练习题

1、‎如果选择性重发协议SR的发送窗口和接收窗口的大小为1，那么它的表现和停止等待协议一样。

:accept:

## 第四章 网络层

网络层实现了**主机到主机的通信服务**，与传输层和应用层不同的是：在网络中的每一台主机和路由器都有一个网络层部分。网络层是协议栈中最复杂的层次。

**互联网都是指用路由器进行互连的网络。**

### 概念与应用

#### 1、网络层的核心功能

网络层的作用是将分组（包）从一台发送主机移动到一台接收主机，为此需要两种重要的网络层功能：**路由选择**(确定哪一条路径)与**分组转发**(当一个分组到达时所采用的动作)，这也是路由器的主要功能

:one:**路由选择: 是指确定分组从源到目的地所采取的端到端路径的网络范围处理过程。路由选择发生的时间尺度长得多（通常为几秒），因此通常用软件来实现**。如果子网内部使用数据报，那么对每一个进来的分组都要重新选择路径。如果子网内部使用虚电路，那么只有当创建一个新的虚电路时，才需要确定路由路径。

:two: **分组转发：是指将分组从一个输入链路接口转移到适当的输出链路接口的路由器本地动作。转发发生的时间尺度很短（通常为几纳秒），因此通常用硬件来实现。——路由器的核心功能**

某些网络架构中（如**ATM网络架构**）的第三个重要功能——连接建立

#### 2、网络层服务模型

端到端传输的QoS

> **不丢包**。该服务确保分组将最终到达目的地。
>
> **没包间隔或者很小（没有抖动）**
>
> **实时性**。该服务不仅确保分组的交付，而且在特定的主机到主机时延上界内（例如在100ms内）交付。
>
> **有序分组交付**。该服务确保分组以它们发送的顺序到达目的地。
>
> **确保最小带宽**。这种网络层服务模仿在发送和接收主机之间一条特定比特率（例如1Ms)的传输链路的行为。只要发送主机以低于特定比特率的速率传输比特（作为分组的组成部分)，则所有分组最终会交付到目的主机。
>
> **安全性**。网络层能够在源加密所有数据报并在目的地解密这些分组，从而对所有运
> 输层报文段提供机密性。
>
> **差错检测与处理**
>
> **拥塞反馈**

| 网络架构 | 服务模型      | 带宽保证       | 保证不丢包 | 有序性 | 实时性 | 拥塞反馈 |
| -------- | ------------- | -------------- | ---------- | ------ | ------ | -------- |
| Internet | best effort   | 否             | 否         | 否     | 否     | 否       |
| ATM      | CBR固定比特率 | 不变速率       | 是         | 是     | 是     | 无拥塞   |
| ATM      | VBR动态比特率 | 可变           | 是         | 是     | 是     | 无拥塞   |
| ATM      | ABR可用比特率 | 指定峰值、谷值 | 否         | 是     | 否     | 是       |
| ATM      | UBR未定比特率 | 无             | 否         | 是     | 否     | 否       |

:one:  **ABR**:  Available  Bit  Rate 有效位率服务,主要用于视频服务; 可以保证一个最小带宽,可能丢包.

:two: CBR :  Constant  Bit  Rate 主要用于实时语音通信; 不会丢包,不需要拥塞控制.

:three: VBR :  Variable  Bit  Rate 不会丢包,不需要拥塞控制.

:four: UBR :  Unspecified  Bit  Rate 使用时有资源则使用,无资源则丢包,免费使用,无拥塞控制

“尽最大努力交付try best effort”都有哪些含义?

> 1)不保证源主机发送出来的IP数据报**一定无差错地**交付到目的主机。
>
> 2)不保证源主机发送出来的IP数据报都在**某一规定的时间内**交付到目的主机。
>
> 3)不保证源主机发送出来的IP数据报**一定按发送时的顺序**交付到目的主机。
>
> 4)不保证源主机发送出来的IP数据报**不会重复交付**到目的主机。
>
> 5)**不故意丟弃IP数据报**。丢弃IP数据报的情况:路由器检测出首部检验和有错误;或由于网络中通信量过大,路由器或目的主机中的缓存已无空闲空间。

#### 3、路由器结构

<img src="https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/qshell/image-20220331151429700.png" alt="image-20220331151429700" style="zoom:60%;"/>

路由器的结构分为两大类：路由选择部分和分组转发部分

路由选择部分包括**路由选择处理器**

分组转发部分由三部分组成：**一组输入端口、交换结构和一组输出端口**

**交换结构从输入端口接收到分组后，根据转发表对分组进行处理，然后从一个合适的输出端口转发出去。**

三种交换结构

![image-20220413111007594](https://img-blog.csdnimg.cn/img_convert/0fd3403e578794262f2683d74fb6c65f.png)

> :one: **通过存储器进行交换**：输入端口将数据包放到内存里,输出端口将其从内存中读走;
>
> :two: **通过总线进行交换**：任意输入端口的数据放到总线上,输出端口从总线上读取数据;
>
> :three: **通过交叉开关网络crossbar进行交换**：交叉开关网络横线与竖线的交叉点是一个“bar ”,当需要进行通信时“bar ”才会导通,平时是断开的.

#### 4、IPv4

##### 转发表

![image-20220413104951814](https://img-blog.csdnimg.cn/img_convert/6b1b9aa37adb83a4d21d1e1d48b836e3.png)

采用最长前缀匹配原则

![image-20220413105403155](https://img-blog.csdnimg.cn/img_convert/ac0f346cc90b481690e1eea0dce37900.png)

![image-20220413105246695](https://img-blog.csdnimg.cn/img_convert/6d3752112fcd5e888a779d84190e4024.png)

第二个目的地址(DA)可以匹配接口2 (0011)和接口1(0011000),根据最长前缀原则,选择接口1

##### 数据包格式

![image-20220413112947228](https://img-blog.csdnimg.cn/img_convert/f234d95e06cc6899ff611641709fc2dd.png)

**IP的首部也是20个字节（20B)，跟TCP段头一样**

:one: version：区别是IPv4还是IPv6，通信双方的版本必须一致

:two: head len：首部长

:three: type of service：服务类型，IPv6才有区别，IPv4未启用

:four: flags : 切片包时用的

:five: fragmentation : 将包切片时用的.

> 切片: 将IP 包切分成若干个比较小的片,使之能够放到信元中.
>
> 网络链路层有MTU (最大传输单元),不同的链路有不同的MTU.
>
> 对于较大的数据包,如果MTU 装不下包,则会将其切分.
>
> 被切分的数据包,只有在接收端才会进行装配.

:six: checksum：没用，链路层已经检查过01跳变了

:seven: 源IP地址、目的IP地址

#####  IPv4地址

IP地址跟网络接口，主机对应，路由器有许多接口也就有许多IP地址

<mark>同一网段，网络部分相同；不同网段，网络部分不同</mark>

IP地址由主机部分和网络部分组成

把整个因特网看作一个单一的，抽象的网络，IP地址就是给每个连接在网络上的主机（或路由器）分配一个在全世界范围内是唯一的32位的标识符。一般将IP地址分为A,B,C,D,E类地址（E类地址不使用）。

**IP地址是标志一个主机(或路由器)和一条链路的接口**。

###### 地址分类

![image-20220329102141882](https://img-blog.csdnimg.cn/img_convert/7f3a46dbc59e7433524ed23b8a52cbdd.png)

:one: A类地址高一位字段是网络部分，低三位字段是主机部分，最高位固定为0

> 可以指派的网络数为$2^7-1$：网络地址全0为保留地址，“本网络”，网络号为01111111保留为环回地址
>
> 主机数最多为$2^{24}-2$

:two: B类地址高两位字段是网络部分，低两位字段是主机部分，最高位固定为10

> 网路地址128.0.0.0实际上不指派，所以最少从128.1.0.0开始，可以指派的网络数为$2^{14}-1$
>
> 主机数最多为$2^{16}-2$

:three: C类地址高三位字段是网络部分，低一位字段是主机部分，最高位固定为110

> 网路地址192.0.0.0实际上不指派，所以最少从192.1.0.0开始，可以指派的网络数为$2^{21}-1$
>
> 主机数最多为$2^{8}-2$

:four: D类地址高四位字段是网络部分最高位固定为1110

> 多播通信时作为目的地址
>
> 多播：类似于微信的群聊

###### 特殊IP地址

| 特殊地址             | 网络号 | 主机号       | 源地址或目的地址     |
| -------------------- | ------ | ------------ | -------------------- |
| 网络地址             | 特定的 | 全0          | 都不是               |
| 直接广播地址         | 特定的 | 全1          | 目的地址             |
| 受限广播地址         | 全1    | 全1          | 目的地址             |
| 这个网络上的这个主机 | 全0    | 全0          | 源地址或默认目的地址 |
| 这个网络上的特定主机 | 全0    | 特定的       | 目的地址             |
| 环回地址             | 127    | 不是全1或全0 | 源地址或目的地址     |

> :one: 直接广播地址：主机号全1，路由器使用这个地址把一个分组发送到一个特定的网络上的所有主机，所有主机都会收到具有这种类型的**目的地址**的分组.
>
> :two: 受限广播地址：IP为255.255.255.255，<mark>用于定义在当前网络（不是整个互联网）</mark>。会向本地局域网中的其他主机发送分组，路由器会阻拦其传播到局域网外。
>
> :three: 这个网络上的这个主机：IP为0.0.0.0，不知道自己的IP地址时的主机发送一个报文给引导服务器，使用0.0.0.0作为源地址，使用255.255.255.255作为目的地址
>
> :four: 这个网络上的特定主机：属于A类地址，用于向同一网络上的某个主机发送报文。
>
> :five: 环回地址：用于自我测试，属于A类地址

#### 5、子网

子网划分，使两级的IP地址变为三级的IP地址。划分子网是一个单位内部的事情，单位对外仍然表现为没有划分子网的网络。

**划分子网的思路**：从主机号借用若干比特作为子网号，而主机号也就相应减少了若干个比特，网络号不变，于是IP地址可记作
$$
\text{IP地址}::=\{\text{<网络号>，<子网号>，<主机号>}\}
$$
凡是从其他网络发送给本单位某个主机的IP数据报，仍然根据IP分组的目的网络号先找到连接在本单位网络上的路由器。然后此路由器在收到IP数据报后，再按照目的网络号和子网号找到目的子网，最后将IP数据报交给目的主机。

子网划分与否是看不出来的，如果要告诉主机或路由器是否对一个A类、B类、C类网络进行了子网划分，则需要**子网掩码**。

子网掩码是一个与IP地址相对应的32位的二进制串，它由一串1和0组成。其中，1对应于IP地址中的网络号和子网号，0对应于主机号。因为1对1进行与操作，结果为1；1对0进行与操作，结果为0。所以使用一串1对网络号和子网号进行与操作，就可以得到网络号。

:label: **不管网络有没有划分子网，只要将子网掩码和IP地址进行逐位的“与”运算，就一定能得到子网地址**

如果没有给出子网掩码，则采用默认的：A：255.0.0.0、B：255.255.0.0、C：255.255.255.0

> IP地址：32位地址
>
> 网络地址：32位地址，网络部分不变，后面为0
>
> 子网地址：32位地址，子网以前不变，后面为0

**举例**

> A small company has a class C network license and needs to create 10 usable subnets, each subnet capable of accommodating at least 12hosts. Which of the following is the appropriate subnet mask?
>
>  255.255.255.0 
>
>  255.255.255.192 
>
>  255.255.255.224 
>
>  255.255.255.240✅ 子网字段28位，主机部分4位

##### CIDR

classless interdomain routing无类别域间路由选择

CIDR消除了传统的A类，B类，C类地址以及划分子网的概念，采用了斜线记法

![image-20220413154037936](https://img-blog.csdnimg.cn/img_convert/f503c31fd61d2622ccac6fbb69086d12.png)

a.b.c.d/x 代表高x 位是子网部分.

#### 6、NAT、DHCP、ICMP

##### NAT

**NAT：network address translation 网络地址变换**

**路由器看到专用地址时不转发**，所以说专用地址作为目的地址是不可能在因特网上传送的。因特网已经规定了以下地址作为专用地址。

> 10.0.0.0~10.255.255.255 (相当于一个A类网络)。
>
> 172.16.0.0~172.31.255.255 (相当于16个连续的B类网络)。
>
> 192.168.0.0~192.168.255.255 (相当于256个连续的C类网络)。

专用网的主机想和因特网的主机通信，怎么办?这时NAT就诞生了。**NAT就是将专用网内部使用的本地IP地址转换成有效的外部全球IP地址，使得整个专用网只需要一个全球IP地址就可以与因特网连通。**

NAT并不能从根本上解决IP地址的耗尽问题,因为NAT并没有增加IP地址的个数。而**真正解决IP地址耗尽问题的是IPv6**

![image-20220413160409813](https://img-blog.csdnimg.cn/img_convert/2c4247e69cda889db043668cd6ac4ae6.png)

如果从广域网到达NAT路由器的所有数据报都有相同的目的IP地址（特别是对NAT 路由器广域网一侧的接口)，那么该路由器怎样知道它应将某个分组转发给哪个内部主机呢？

使用NAT路由器上的一张**NAT转换表(NAT translation table)**,并且在表项中包含了**端口号及其IP地址**。

考虑上图中的例子。

> :one: 假设一个用户坐在家庭网络主机10.0.0.1后，请求P地址为128.119.40.186的某台Web服务器（端口80）上的一个Wb页面。主机10.0.0.1为其指派了（任意）源端口号3345并将该数据报发送到LAN中。
>
> :two: NAT路由器收到该数据报，为该数据报生成一个新的源端口号5001，将源P替代为其广域网一侧接口的P地址138.76.29.7,且将源端口3345更换为新端口5001。当生成一个新的源端口号时，NAT路由器可选择任意一个当前未在NAT转换表中的源端口号。（注意到因为端口号字段为16比特长，NAT协议可支持超过60000个并行使用路由器广域网一侧单个P地址的连接)
>
> :three: 路由器中的NAT也在它的NAT转换表中增加一表项。Wb服务器并不知道刚到达的包含HTTP请求的数据报已被NAT路由器进行了改装，它会发回一个响应报文，其目的地址是NAT路由器的P地址，其目的端口是5001。
>
> :four: 当该报文到达NAT路由器时，路由器使用目的IP地址与目的端口号从NAT转换表中检索出家庭网络浏览器使用的适当IP地址(10.0.0.1)和目的端口号(3345)。于是，路由器重写该数据报的目的P地址与目的端口号，并向家庭网络转发该数据报。

进出都要涉及变换,因此会使通信效率下降

:one: 静态配置NAT表(Statically  Configure  NAT)

> 每有一个新的应用，就需要往NAT表中加一条表项
>
> 由网络管理员配置.

:two: 动态配置Universal  Plug  and  Play (UPnP)

> 通过软件自动添加
>
> 这类软件核心协议是IGD 协议(Internet  Gateway  Device  Protocol)

##### DHCP

Dynamic Host Configuration Protocol动态主机配置协议：给主机动态地分配IP地址

他是一个**应用层协议**，DHCP报文使用**UDP**传输

它提供了**即插即用连网的机制**，这种机制允许一台计算机加入新的网络和获取IP地址而不用手工参与。例如，现在有一台主机需要 IP地址。在该主机启动时就可以向DHCP服务器广播发送报文，将源地址设置为`0.0.0.0`,目的地址设置为`255.255.255.255`(特殊IP地址)。这时主机就成为DHCP 的客户，发送广播报文主要是因为现在该主机还不知道DHCP在哪，这样在本网络上的所有主机都能够收到该广播报文，但是只有DHCP服务器能够应答。DHCP服务器先在其数据库中查找该计算机的配置信息，若找到，则返回找到的信息，若找不到，则从服务器的IP地址池中取一个地址分配给该计算机。

DHCP服务器和DHCP客户端的交换过程如下（4路广播）

> :one: DHCP客户端广播<mark>"DHCP discover"</mark>报文，试图找到网络中的DHCP服务器，服务器获得一个IP地址
>
> :two: DHCP服务器收到报文后，就向网络中广播<mark>"DHCP offer"</mark>报文，其中包括提供DHCP客户端的IP地址和相关配置信息
>
> :three: DHCP客户机收到<mark>“DHCP offer”</mark>报文，如果接受DHCP服务器所提供的相关参数，则通过广播<mark>“DHCP request”</mark>报文向DHCP服务器请求提供IP地址。
>
> :four: DHCP服务器广播<mark>“DHCP ack”</mark>报文，将IP地址分配给DHCP客户机。同时其他DHCP 服务器将其IP 地址收回。
>
> DHCP允许网络上配置多台DHCP服务器，当DHCP客户发出DHCP请求时，就有可能收到多个应答报文。这时，DHCP客户只会挑选其中的一个，通常是挑选“最先到达的报文。
>
> ![image-20220413154204422](https://img-blog.csdnimg.cn/img_convert/1710c8f41070e5871f862f6b842fa6c6.png)

除了主机IP地址分配以外，DHCP还允许一台主机得到其他信息：

> 它的子网掩码
>
> 它的第一跳路由器地址（默认网关）
>
> 它的本地DNS服务器地址

##### ICMP

Internet Control Message Protocol 网络控制报文协议

主机在发送数据报时，经常会由于各种原因**发送错误**，如路由器拥塞丢弃了或者传输计程中出现错误丢弃了(注意:如果是首部出错，当然可以发，但是一般都不发，因为首部中错很有可能是源IP地址都错了，所以即使发了源主机也不一定收到)。如果检测出错误的路由器或主机都能把这些错误报告通过一些控制消息告诉发送数据的主机，那么发送数据的主机就可根据ICMP报文确定发生错误的类型，并确定如何才能更好地重发失败的数据报(比如ICMP报文发过来的是改变路由，那么主机就不能继续按照这个路由线路发送了，需要用另外一条路由线路发送数据)。尽管这些控制消息并不传输用户数据，但是对于用户数据的传递起着重要的作用。

ICMP报文分为两种，即<mark>ICMP差错报告报文和ICMP询问报文</mark>。

:one: **ICMP差错报告报文的分类**

> 1)终点不可达。当路由器或主机不能交付数据报时，就向源点发送终点不可达报文。
>
> 2)源站抑制。当路由器或主机由于拥塞而丢弃数据报时，就向源点发送源点抑制报文，使源点知道应当把数据报的发送速率放慢。
>
> 3)时间超过。当IP分组的TTL值被减为0后，路由器除了要丢弃该分组外，还要向源点发送时间超过报文。当终点在预先规定的时间内不能收到一个数据报的全部数据报片时，就把已收到的数据报片都丢弃，并向源点发送时间超过报文。
>
> 4)参数问题。当路由器或目的主机收到的数据报的首部中有字段的值不正确时，就丢弃该数据报，并向源点发送参数问题报文(现在一般都不发)。
>
> 5)改变路由(重定向)。路由器把改变路由报文发送给主机，让主机知道下次应将数措报发送给其他的路由器(比当前更好的路由)。

:two: 询问报文的分类

> 1)有回送请求和回答报文。
>
> 2)时间戳请求和回答报文。
>
> 3)掩码地址请求和回答报文。
>
> 4)路由器询问和通告报文。

:three: 不应发送ICMP差错报告报文的几种情况

> 1)对ICMP差错报告报文不再发送ICMP差错报告报文。
>
> 2)对第一个分片的数据报片的所有后续数据报片都不发送ICMP差错报告报文。
>
> 3)对具有组播地址的数据报都不发送ICMP差错报告报文。
>
> 4)对具有特殊地址(如127.0.0.0或0.0.0.0)的数据报不发送ICMP差错报告报文。

ICMP的两个典型应用，其实在日常生活中经常用，即**ping和tracert**.。

> ping 用来测试两个主机之间的连通性。ping 使用了ICMP**回送请求与回送回答**报文。ping 是**应用层**直接使用网络层ICMP的例子，它没有通过传输层的TCP或UDP。
>
> tracert（使用UDP）可以用来跟踪分组经过的路由，它工作在**网络层**。

#### 7、IPv6

##### 优点

:one: IPv6第一个主要改进也是最重要的，即IPv6有比IPv4长得多的地址。

:two: IPv6第二个主要改进是简化了IP分组的基本首部，它包含8个段（IPv4是12个段）。这一改变使得路由器能够**更快地处理数据报**，从而可以改善吞吐率。

:three: IPv6第三个主要改进是IPv6更好地支持选项。有助于促进QoS。

##### 首部格式

IPv6的首部格式如下（一共40B）：

![ ](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/image-20220502133146399.png)

优先级priority：确定流中数据报的优先级

流标号flow label：识别相同“流”的数据报。

下一个报头next header：识别数据的上层协议

地址长128位；IPv4只有32位

##### 从IPv4转变到IPv6

IPv4和IPv6混合的网络将如何运行?

> 隧道技术*Tunneling*
>
> 双栈技术*Dual-stack*

:one: **双栈技术*Dual-stack***

![image-20220502133215019](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/image-20220502133215019.png)

到达边缘路由器的时候将包进行转换（IPv4<->IPv6）

:two: **隧道技术*Tunneling***

![image-20220506210033288](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/image-20220506210033288.png)

将IPv6(IPv4)的包整个封装到IPv4(IPv6)的包中

隧道技术不止这一种

#### 8、路由选择

将路由器聚合成区域形成自治系统(AS：Autonomous System)

> 同一AS内的路由器运行相同的路由协议：**内部网关协议**（Interior Gateway Protocols **(IGP)**）
>
> 不同AS中的路由器可以运行不同的AS内路由协议
>
> **边界网关协议BGP：broder gateway protocol**，所有的AS运行相同的AS间路由选择协议
>
> 一个自治系统由其全局唯一的AD号（ASN）所标识

##### 热土豆路由选择

热土豆路由选择依据的思想是：对于路由器1b,尽可能快地将分组送出其AS(更明确地说，用可能的最低开销)，而不担心其AS外部到目的地的余下部分的开销。

在路由转发表中增加AS外部目的地的步骤如下

> :one: 从AS间协议学到经多个网关可达子网x
>
> :two: 使用来自AS内部协议的路由选择信息，以决定到达每个网关的最低开销路径的开销
>
> :three: 热土豆路由选择：选择具有最小最低开销的网关
>
> :four: 从转发表确定通往最低开销网关的接口I，往转发表中添加表项转发表项(x,I)

##### 内部网关协议

常见的内部网关协议IGP如下

> **RIP:Routing Information Protocol路由信息协议**
>
> **OSPF:Open Shortest Path First开放最短路径优先**
>
> EIGRP：增强内部网关路由协议(Cisco专有)
>
> IS-IS:Intermediate System to Intermediate System,RFC 1122

###### RIP

内部网关协议的一种

> :one: 使用距离矢量算法
>
> :two: AS内部最远的两点的距离不超过15跳，直径不超过15跳
>
> :three: 每30秒将距离矢量广播给邻居
>
> :four: AS周长不超过25跳
>
> :five: 每180秒没有收到来自邻居的信息，则认为跟邻居的链路断开了，BF方程重新进行计算
>
> > 链路故障的信息会很快传播给整个AS
>
> :six: 如果有多条最优路径，选择其中的一条走

报文类型为UDP，周期性重复，端口为520

![image-20220413200017142](https://img-blog.csdnimg.cn/img_convert/274dea832d9e7d0d37582d612aa93755.png)

###### OSPF

内部网关协议的一种

> :one: 使用链路状态算法
>
> > 使用链路状态数据包传播
> >
> > 需要维护每个节点的拓扑图
> >
> > 使用Dijkstra算法进行路由计算
>
> :two: 周期性地广播自己跟谁连，代价是多少，广播给整个AS，周期通常是30分钟
>
> :three: 报文直接封装在IP的报文中，**OSPF协议直接运行在网络层上部**，也是不可靠的协议

**OSPF的优势（相较于RIP)**

> :one: 安全性：两个路由器通信之前先对路由器进行认证authenticated，避免恶意入侵
>
> :two: 两点之间允许多条等价的最优路径，都可以走(RIP中只有一条路径)
>
> :three: 对同一条链路，传送不同业务时配置的代价可以不一样
>
> :four: 集成单播和多播支持
>
> :five: AS比较大时可以使用分层OSPF：各自在各自的domain中运行OSPF协议

##### BGP

AS之间的路由协议只有一个:BGP边界网关协议

采用路径-矢量协议

> 边界网关维护的一般不是路由表而是路径表

BGP为每个AS提供了一种方法:

> :one: 向邻居应用服务器获取子网可达性信息。
>
> :two: 将可达性信息传播到所有AS内部路由器。
>
> :three: 根据可达性信息和策略确定到AS的“好”路由。

允许**AS**向他的上一跳广播：我在这

**使用半永久的TCP连接**，可以通过会话（应用层）报文关闭

###### 举例说明

> AS2向AS1发布前缀时:
>
> AS2承诺它将向该前缀转发数据报。
>
> AS2可以聚合其通告中的前缀
>
> AS3通过3a和1c之间的eBGP会话向AS1发送前缀可达性信息。
>
> > 1c可以使用iBGP将新的前缀信息分发给AS1中的所有路由器
> >
> > 1b可以通过1b-to-2a eBGP会话向AS2重新发布新的可达性信息
>
> 当路由器学习到新的前缀时，它会在转发表中为前缀创建一个表项。

![image-20220507085442344](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/image-20220507085442344.png)



**路由选择**

> 根据本地的喜好制定策略，策略的优先级高于路径矢量算法
>
> 缺省时使用最短的路径
>
> 热土豆准则

**BGP的四种报文**

> OPEN报文：与相邻的另一个BGP发言人建立关系
>
> UPDATE报文：用于发送某一路由的信息以及列出要撤销的多条路由
>
> KEEPALIVE报文：用于确认打开报文和周期性地证实邻居关系
>
> NOTIFICATION报文：报告报文错误以及用来关闭TCP连接

##### 比较

| 主要特点     | RIP                                                          | OSPF                                               | BGP                |
| ------------ | ------------------------------------------------------------ | -------------------------------------------------- | ------------------ |
| 网关协议     | 内部                                                         | 内部                                               | 外部               |
| 路由表内容   | 目的网络，下一跳，距离                                       | 目的网络，下一跳，距离                             | 目的网络，完整路径 |
| 最优通路依据 | 跳数                                                         | 费用                                               | 多种有关策略       |
| 算法         | 距离-矢量算法                                                | 链路状态算法                                       | 路径-矢量算法      |
| 传送方式     | UDP                                                          | IP数据报                                           | TCP连接            |
| 其他         | 简单，效率低，**跳数为16不可达**，好消息传得快，坏消息传得慢 | 效率高、路由器频繁交换信息，难以维持一致性；规模大 |                    |



### 名词解释

AS：Autonomous System自治系统，由路由器聚合而成

RIP：Routing Information Protocol路由信息协议

OSPF：Open Shortest Path First开放最短路径优先

IGRP：Interior Gateway Routing Protocol内部网关路由协议，采用距离矢量算法

ICMP：Internet Control Message Protocol 网络控制报文协议

BGP：broder gateway protocol边界网关协议

ARP：Address Resolution Protoco地址解析协议，是根据IP地址获取物理地址的一个TCP/IP协议

RARP：Reverse Address Resolution Protoco反向地址转换协议，允许局域网的物理机器从[网关](https://baike.baidu.com/item/网关/98992)服务器的 ARP 表或者缓存上请求其 IP 地址

CIDR：classless inter domain routing 无类别域间路由选择

DHCP：Dynamic Host Configuration Protocol动态主机配置协议：给主机动态地分配IP地址

MTU：最大传输单元（Maximum Transmission Unit，MTU）用来通知对方所能接受数据服务单元的最大尺寸，说明发送方能够接受的有效载荷大小。

NAT：network address translation 网络地址变换

IGP：内部网关协议

EGP：外部网关协议

### 算法

现代的计算机网络通常使用动态路由选择算法。动态路由算法又可分为两种基本类型：**距离－向量路由算法和链路状态路由算法**。

**全局性**的路由算法：链路状态路由算法，维护一个全局的拓扑图。

**分散性**的路由算法：距离－向量路由算法

#### 链路状态路由算法LS算法

链路状态路由算法要求每个参与该算法的节点都有完全的网络拓扑信息，它们执行下述两项任务。

:one: 主动测试所有邻接节点的状态。两个共享一条链接的节点是相邻节点，它们连接到同一条链路

:two:定期地将链路状态传播给其他所有节点

##### Dijkstra 算法（重要）

**概述**

> 网络拓扑结构，所有节点都知道链路开销
>
> 通过“link state broadcast”完成所有节点都有相同的信息
>
> 计算从一个节点(“源”)到所有其他节点的最小开销路径
>
> 迭代:经过k次迭代，知道到达k dest的最小代价路径

**符号**

> C (x,y):节点x到y的链路开销;如果不是直接邻居=∞
>
> D(v):从source到dest的路径开销的当前值
>
> P (v):从源到v路径上的<mark>**前继节点**</mark>
>
> N':已知最小代价路径的节点集合

**算法伪代码**

```c
Initialization:
     N’= {u}
 	 for(所有的节点v){
   		if(v与u相邻){
           D(v)= c(u,v);
        }else{
           D(v)= ∞; 
        }
     } 
Loop:
    找到在集合N’之外的离源结点最近的结点w;
    将w加入N’;
    for(所有与w相邻但是不在N’中的节点v){
      D(v)= min( D(v), D(w)+ c(w,v));
    }
until 所有的节点都在N’中

```

![image-20220331173311285](https://img-blog.csdnimg.cn/img_convert/dc9d5bd5b088945c6e2f086eb4ae3529.png)

**时间代价**

> 算法复杂度:n个节点
>
> 每次迭代:需要检查所有节点w，而不是N
>
> n (n +1)/2的比较: $O (n^2)$
>
> 更有效的实现：$O(n\text{logn})$——优先队列
>
> 可能会产生振荡:
>
> 例如，链路成本=承载的流量

每个路由器在运行Dijkstra算法时会引入随机延迟

#### 距离-向量路由算法——DV算法

不要求维护一个全局的拓扑图

**距离向量(Distance-Vector,DV)算法**是一种<mark>**迭代的、异步的和分布式**</mark>的算法，而LS算法是一种使用全局信息的算法。

> :one: **分布式的**：因为每个节点都要从一个或多个直接相连邻居接收某些信息，执行计算，然后将其计算结果分发给邻居。
>
> :two: **迭代的**：此过程一直要持续到邻居之间无更多信息要交换为止。此算法是自我终止的，即没有计算应该停止的信号，它就停止了。
>
> :three: **异步的**：是因为它不要求所有节点相互之间步伐一致地操作。
>
> :four: **自终止性**

令$d_x(\text{y})$是从**结点x到结点y的最低开销路径的开销**，则该路径开销与Bellman-Ford方程相关
$$
d_x(\text{y})=min_v\{c(x,v)+d_v(\text{y})\}
$$
方程中的$min_v$**是对于x的所有邻居的**。Bellman-Ford方程是相当直观的。实际上，从x到v遍历之后，如果我们接下来取从v到y的最低开销路径，则该路径开销将是$c(x,v)+d_v(y)$。因此我们必须通过遍历某些邻居v开始，**从x到y的最低开销是对所有邻居v的$c(x,v)+d_v(y)$的最小值**。

令$\pmb{D}_x=[\pmb{D}_x(y):y\in N]$是节点x的距离向量，该向量是**从x到在N中的所有其他节点y的开销估计向量**，使用DV算法，**每个结点x维护以下路由选择信息**

> :one: 对于每个邻居v，从x到直接邻居v的开销为c(x,v)
>
> :two: 结点x的距离向量，即$\pmb{D}_x=[\pmb{D}_x(y):y\in N]$，包含了从x到在N中的所有其他节点y的开销估计向量
>
> :three: 它的每个邻居的距离向量，对x的每个邻居v，有$\pmb{D}_v=[\pmb{D}_v(y):y\in N]$

在该算法中，每个节点周期性地向它的每个邻居发送它的距离向量副本，当当节点x从它的任何一个邻居v接收到一个**新距离向量**，它保存v的距离向量，然后使用Bellman-Ford方程更新它自己的距离向量如下：
$$
\pmb{D}_x(\text{y})=min_v\{c(x,v)+\pmb{D}_v(\text{y})\}\quad 对于N中的每个结点
$$
**如果节点x的距离向量因这个更新步骤而改变，节点x接下来将向它的每个邻居发送其更新后的距离向量，这继而让所有邻居更新它们自己的距离向量。**只要所有的节点继续以异步方式交换它们的距离向量，每个开销估计$\pmb D_v(y)$收敛到$d_v(y)$，$d_v(y)$为从节点x到节点y的实际最低开销路径的开销

求节点x的距离向量的**算法步骤**

```c
Initialization(){
    for(所有的目的节点 y){
        if(y是x的邻居节点){
        	Dx(y)=c(x,y);
        }else{
        Dx(y)=∞;
        }
    }
    for(x所有的邻居节点w){
        将x的距离向量Dx=[Dx(y):y in N]发送给w
    }
}
loop(forever){
   wait(直到x发现邻居节点w的cost改变或者x收到来自邻居节点w的距离向量更新);
   for(对于所有的目的节点y){
    	Dx(y)=minv{c(x,v)+Dv(y); /*更新x的距离向量Dx*/       
   }
   if(对于任何的目的节点y Dx(y)有改变){
   		将新的 Dx(y) 发送给x的所有邻居;
   }
}
```

**举例说明**

![image-20220508000826340](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/image-20220508000826340.png)

##### 链路开销改变与链路故障

当一个运行DV算法的节点检测到从它自己到邻居的链路开销发生变化时（I see a link cost change to neighbor w )，它就更新其距离向量并且如果最低开销路径的开销发生了变化，向邻居通知其新的距离向量。

###### 好消息传达速度快

下图示了从y到x的链路开销从4变为1的情况。我们在此**只关注y与z到目的地x的距离表中的有关表项。该DV算法导致下列事件序列的出现**

![image-20220407153504800](https://img-blog.csdnimg.cn/img_convert/fdddeaefcae60a4c581f29899c923444.png)

> :one: 在$t_0$时刻，y检测到链路开销变化（开销从4变为1），更新其距离向量，并通知其邻居这个变化，因为最低开销路径的开销已改变。
>
> :two: 在$t_1$时刻，z收到来自y的更新报文并更新了其距离表。它计算出到x的新最低开销（从开销5减为开销2），它向其邻居发送了它的新距离向量。
>
> :three: 在$t_2$时刻，y收到来自z的更新并更新其距离表。y的最低开销未变，因此y不发送任何报文给z。该算法进入静止状态。

![image-20220407154218015](https://img-blog.csdnimg.cn/img_convert/cc2e928f63674bea7e32ed644b9789ad.png)

因此，对于该DV算法只**需两次迭代就到达了静止状态**。在x与y之间开销减少的好消息通过网络得到了迅速传播。

###### 坏的消息传送速度慢

假设x与y之间的链路开销从4增加到60，此时整个链路图变化如下：

:one:在链路开销变化之前，D,(x)=4,D,(z)=1,D(y)=1和D,(x)=5。在t0时刻，y检测到链路开销变化（开销从4变为60）。y计算它到x的新的最低开销路径的开销，其值为
$$
D_y(x)=min\{c(y,x)+D_x(x),c(y,z)+D_z(x)\}=min\{60+0,1+5\}=6
$$
当然，从网络全局的视角来看，我们能够看出经过z的这个新开销是错误的。但节点y仅有的信息是：它到x的直接开销是60，且z上次已告诉y,z能以开销5到x。因此，为了到达x,y将通过z路由，完全期望z能以开销5到达x。到了t1时刻，我们遇到**路由选择环路(routing loop),即为到达x,y通过z路由，z又通过y路由**。路由选择环路就像一个黑洞，即目的地为x的分组在1时刻到达y或z后，将在这两个节点之间不停地（或直到转发表发生改变为止)来回反复。

:two: 因为节点y已算出到x的新的最低开销，它在t,时刻将该新距离向量通知z。

:three: 在t1后某个时间，z收到y的新距离向量，它指示了y到x的最低开销是6。z知道它能以开销1到达y,因此计算出到x的新最低开销$D_z(x)=min\{50+0,1+6\}=7$。因为z到x的最低开销已增加了，于是它便在2时刻通知y其新开销。

:four: 以类似方式，在收到z的新距离向量后，y决定D,(x)=8并向z发送其距离向量。接下来z确定D,(x)=9并向y发送其距离向量，等等

以上的过程要一直迭代44次，知道z最终算出它经由y的路径开销大于50为止。这种坏消息会造成**无穷计数问题**count to infinity

![image-20220407154848619](https://img-blog.csdnimg.cn/img_convert/39048b05e4dc7b5681fc7f7fc81aadbc.png)

##### 算法改进：增加毒性逆转poisoned reverse

如果z通过y路由选择到目的地x,则z将通告y,它(即z)到x的距离是无穷大，也就是z将向y通告$D_z(x)=∞$（即使z实际上知道$D_z(x)=
5$)。只要z经y路由选择到x,z就持续地向y讲述这个**善意的谎言**。因为y相信z没有到x的路径，故只要z继续经y路由选择到x(并这样去撒谎)，y将永远不会试图经由z路由选择到x。

**但是3个或更多节点（而不只是两个直接相连的邻居节点）的环路将无法通过该技术检测到**

![image-20220407155256448](https://img-blog.csdnimg.cn/img_convert/52c3fb16ab32adbf4ad4abb67066f951.png)



#### LS算法和DV算法的比较

DV和LS算法采用互补的方法来解决路由选择计算问题。在DV算法中，每个节点仅与它的直接相连的邻居交谈，但它为其邻居提供了从它自己到网络中（它所知道的）所有其他节点的最低开销估计。LS算法需要全局信息。因此，当在每台路由器中实现时，每个节点（经广播）与所有其他节点通信，但仅告诉它们与它直接相连链路的开销。我们通过快速比较它们各自的属性来总结所学的链路状态与距离向量算法。记住N是节点（路由器）的集合，而E是边（链路）的集合。

**报文复杂性**。我们已经看到**LS算法**要求每个节点都知道网络中每条链路的开销，这就要求要发送$O(|N||E|)$个报文。而且无论何时一条链路的开销改变时，必须向所有节点发送新的链路开销。DV算法要求在每次迭代时，**在两个直接相连邻居之间交换报文**。我们已经看到，算法收敛所需时间依赖于许多因素。当链路开销改变时，DV算法仅当在新的链路开销导致与该链路相连节点的最低开销路径发生改变时，才传播已改变的链路开销。

**收敛速度**。我们已经看到LS算法的实现是一个要求$O(|N||E|)$个报文的$O(|N|^2)$算法。**DV算法收敛较慢，且在收敛时会遇到路由选择环路。**DV算法还会遭遇无穷计数的问题。

**健壮性Robustness**。如果一台路由器发生故障、行为错乱或受到蓄意破坏时情况会怎样呢？

> LS算法：节点可以通告错误的**链路link开销**；每个节点只计算自己的转发表
>
> DV算法：节点可以通告不正确的**路径path开销**；每个节点的表被其他节点使用；错误通过网络传播

两个算法都有在使用。

|            | 链路状态路由选择算法 | 距离矢量路由选择算法 |
| :--------- | :------------------- | :------------------- |
| 报文复杂性 | 相对较高             | 相对较低             |
| 收敛速度   | 相对较快             | 相对较慢             |
| 健壮性     | 相对较强             | 较弱                 |

### 练习题

1、**在数据报网络中，网络层的两个最重要功能是什么？虚电路网络中网络层的3个最重要的功能是什么？**

> Internet数据报：路由选择和转发
>
> 虚电路：路由选择、转发、建立连接。

**2、路由选择和分组转发的区别是什么？**

> 分组转发：（根据转发表进行查找）当一个分组到达某个路由器的输入链路时，该路由器必须将其移动到合适的输出链路。
>
> 路由选择：（如何构建转发表）确定包从发送方流向接收方时所经过的路由

3、假定在源主机A和目的主机B之间的数据报被限制为1500字节（包括首部）。假设IP首部为20字节，要发送一个5MB组成的MP3需要多少个数据报？

> 1M=1024k=1048576字节
> 数据长度=1500-20=1480字节
> 5*1048576/1480=3542个

3、IP分组在传输过程中会发生的问题包括**分组丢失**和**乱序**。

4、路由器在进行分组转发时（不考虑分段），会改变IP头部（首部）中的一些域，这些域包括**TTL**和**校验和**。（校验和是根据TTL进行计算的）。

5、关于IP分段，只有目的节点才会执行分段重组。不止[路由器](https://so.csdn.net/so/search?q=路由器&spm=1001.2101.3001.7020)才会执行分段，发送的主机等也会执行分段。

6、当IPv6以隧道形式通过IPv4路由器时，IPv6将IPv4隧道作为链路层协议

7、在距离矢量路由选择中的"无穷计数"是什么意思?

由于链路开销增加的消息传播得很慢, 如果某条链路上的开销突然有一个巨大的增幅, 会造成路由选择环路, 有时也被称为无穷计数.

‍8、关于CIDR，下面哪个或者哪些说法是正确的：

> A.扩展了IP地址空间，增加了IP地址数量
>
> B.实现了网络前缀/网络号长度可变:+1:
>
> C.路由表采用自上而下逐一匹配的方式，当匹配到某个条目时，按照条目中的下一跳执行转发
>
> D.通过前缀汇聚（也称为路由聚合）来减少路由表表项:+1:

## 第五章 数据链路层

**节点(node)**：运行链路层协议(即第2层)协议的任何设备。节点包括**主机、路由器**、交换机和WiFi接入点

**链路**:**连接相邻节点的通信通道**

数据链路层负责**通过链路将数据报从一个节点传输到相邻节点**</mark>

链路层的主体部分是在**网络适配器(network adapter)**中实现的，网络适配器有时也称为**网络接口卡(Network Interface Card,NIC)**。位于网络适配器核心的是链路层控制器，该控制器通常是一个实现了许多链路层服务（成帧、链路接人、差错检测等)的**专用芯片**。因此，链路层控制器的许多功能是用**硬件**实现

### 概念与应用

#### 1、链路层提供的服务

:one: **成帧(framing)**：将网络层的数据包封装成帧

:two: **链路接入**。<mark>**媒体访问控制(Medium Access Control,MAC)协议**</mark>(`网卡地址，48位，6个字段`)规定了帧在链路上传输的规则。

:three: **可靠交付(reliable delivery)**。与运输层可靠交付服务类似，链路层的可靠交付服务通常是**通过确认和重传取得**的。链路层可靠交付服务通常**用于易于产生高差错率的链路**，**许多有线的链路层协议不提供可靠交付服务。**

:four: **差错检测Error detection和纠正Error correction**。有01跳变就把帧扔掉。传输层和网络层也提供差错检测。**链路层的差错检测通常更复杂，并且用硬件实现**。差错纠正类似于差错检测，区别在于接收方不仅能检测帧中出现的比特差错，而且能够准确地确定帧中的差错出现的位置（并**因此纠正这些差错**)。网卡没有差错恢复功能。

:five: **半双工和全双工通信**(*Half-duplex and full-duplex communication*)：在半双工模式下，链路两端的节点可以进行传输，但不能同时进行（对讲机）

:six: **流控制（Flow Control）**：在相邻的发送和接收节点之间移动

#### 2、差错检测与纠正（了解）

在传输数据中检测差错的3种技术：

:one: **奇偶校验**（它用来描述差错检测和纠正背后隐含的基本思想)

:two: **检验和方法**（它通常更多地应用于传输层）

:three: **循环冗余检测**（它通常更多地应用在适配器中的链路层）。

**循环冗余检测**：接收方用G去除接收到的d+r比特。如果余数为非零，接收方知道CRC出现了差错；否则认为数据正确而被接收。

用G来除D·2^r^，余数值刚好是R。换句话说，我们可以这样计算R：
$$
R=\text{remainder}\frac{D\cdot 2^r}{G}
$$
:apple: **举例说明**：D=101110，d=6，G=1001，r=3，传输的数据为101110011

<img src="https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/image-20220502144710320.png" alt="image-20220502144710320" style="zoom:50%;" />

#### 3、多路访问协议multiple access protocol

##### 链路类型

有三种类型的网络链路：点对点链路、广播链路以及交换链路。

:one: 点对点链路(point--to-point link)：由链路一端的单个发送方和链路另一端的单个接收方组成。许多链路层协议都是为点对点链路设计的，如**点对点协议(point-to-point protocol,PPP)和高级数据链路控制(high-level data link control,HDLC)**就是两种这样的协议

:two: 广播链路(broadcast link),它能够让多个发送和接收节点都连接到相同的、单一的、共享的广播信道上。这里使用术语“广播”是因为当任何一个节点传输一个帧时，信道广播该帧，每个其他节点都收到一个副本。

> 老式的以太网
>
> upstream HFC
>
> 802.11 wireless LAN

:three: 交换式链路：以太网

##### 多路访问协议类型

多路访问控制协议MAC protocol可以划分为三种类型

> :one:  **信道划分协议Channel Partitioning Protocol**
>
> 将信道分成更小的“片”(时隙、频率、码、波、空间WDM)
>
> 将片分配给节点独占使用
>
> 适合用户量大的情况
>
> **:two:  随机接入协议Random Access Protocol**
>
> 适合用户量不是很大的情况
>
> 信道不分开，允许碰撞
>
> ALOHA、S-ALOHA、CSMA、CSMA/CA(应用于802.11）、CSMA/CD（应用于以太网）
>
> **:three:  轮流协议Taking-turns Protocol**
>
> 节点轮流发送，但要发送更多的节点可能需要更长的时间：蓝牙（集中式轮询）

理想的多路访问协议

> 速率为R bps的广播信道
>
> :one: 当仅有一个节点想要发送时，它可以以R bps发送。
>
> :two: 当M个节点需要发送时，每个节点可以以平均速率R/M发送
>
> :three: 充分分散的：不会因为某个主节点故障而使系统崩溃
>
> :four: 简单的

##### 信道划分协议

###### TDMA：time division multiple access时分多路复用

将时间划分为时间帧，并进一步划分每一个时间帧为N个时隙slot，把每个时隙分给N个节点中的一个

![image-20220502155039255](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/image-20220502155039255.png)

相当于一个人讲完话，再轮到下一个人讲相同的时间

假设信道通信速率为R，TDMA使得每个结点得到的速率只有R/N，即便只有它一个在传输数据

**座机电话**

###### FDMA：frequency division multiple access频分多路复用

将信道划分为不同的频段，每个频段具有R/N带宽，并将每个频率分给N个结点中的一个

![image-20220502155305873](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/image-20220502155305873.png)

FDM在单个较大的Rbps信道中创建了N个较小的R/Nbps信道

**ADSL、WIFI**

###### CDMA：code division multiple access码分多路复用

CDMA为每个节点分配一个不同的编码，抗干扰能力强，不同节点能够同时传输

##### 随机接入协议

在随机接入协议中，一个传输节点总是**以信道的全部速率（即Rbps)进行发送**。当有碰撞时，涉及碰撞的每个节点反复地**重发**它的帧（也就是分组），到该帧无碰撞地通过为止。但是当一个节点经历一次碰撞时，它不必立刻重发该帧。相反，它在重发该桢之前**等待一个随机时延**。涉及碰撞的每个节点**独立地选择随机时延**。因为该随机时延是独立地选择的，所以下述现象是有可能的：这些节点之一所选择的时延充分小于其他碰撞节点的时延，并因此能够无碰撞地将它的帧在信道中发出。

> ALOHA
>
> slotted ALOHA
>
> CSMA，CSMA/CD，CSMA/CA

###### CSMA

<mark>CSMA：carrier sense multiple access **载波侦听多路访问**</mark>——重要概念

> 如果信道检测空闲：发送整个帧
>
> 如果信道感知到忙，则延迟传输

虽然对所有的节点都进行了侦听，但是碰撞仍然会发生：**传播延迟意味着两个节点可能听不到彼此的传输**，此时整个包的传输时间都被浪费了，因为需要重传

> 4个节点A、B、C、D
>
> 在时刻t~0~，节点B侦听到信道是空闲的，因为当前没有其他节点在传输。因此节点B开始传输，沿着广播媒体在两个方向上传播它的比特。图中B的比特随着时间的增加向下传播，这表明B的比特沿着广播媒体传播所实际需要的时间不是零（虽然以接近光的速度)。在时刻t~1~(t~1~>t~0~),节点D有一个顿要发送。尽管节点B在时刻t1正在传输但B传输的比特还没有到达D,因此D在t~1~侦听到信道空闲。根据CSMA协议，从而D开始传输它的帧。一个短暂的时间之后，B的传输开始在D干扰D的传输。从图中可以看出，显然广播信道的**端到端信道传播时延**(channel propagation delay)(信号从一个节点传播到另一个节点所花费的时间)在决定其性能方面起着关键的作用。该传播时延越长，载波侦听节点不能侦听到网络中另一个节点已经开始传输的机会就越大。
>
> ![image-20220502162626035](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/image-20220502162626035.png)

###### CSMA/CD(Collision Detection)

<mark>CSMA/CD：具有碰撞检测的载波侦听多路访问</mark>

<mark>检测到碰撞时中止传输</mark>

![image-20220502163145384](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/image-20220502163145384.png)

碰撞检测:

> 在有线局域网中实现简单：测量信号强度，比较发射、接收信号
>
> 在无线局域网中困难：接收到的信号强度超过了本地传输强度，使用避免碰撞

##### 轮流协议

###### 三种协议的比较

信道划分MAC协议：在高负载时有效和公平地共享信道；低负载低效率

随机访问MAC协议：低负载高效:单节点可以充分利用信道；高负载会产生碰撞开销

轮流协议：二者的优点结合

###### 集中式轮询polling

轮询协议要求这些节点之一要被指定为主节点。主节点以循环的方式轮询每个节点。特别是，主节点首先向节点1发送一个报文，告诉它（节点1）能够传输的帧的最多数量。在节点1传输了某些帧后，主节点告诉节点2它（节点2）能够传输的帧的最多数量。主节点能够通过观察在信道上是否缺乏信号，来决定一个节点何时完成了帧的发送。上述过程以这种方式继续进行，主节点以循环的方式轮询了每个节点

![image-20220502163956902](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/image-20220502163956902.png)

###### 令牌传递token passing

在这种协议中没有主节点。一个称为令牌(tokn)的小的特殊帧在节点之间以某种固定的次序进行交换。例如，节点1**可能总是把**令牌发送给节点2，节点2可能总是把令牌发送给节点3，而节点N可能
总是把令牌发送给节点1。当一个节点收到令牌时，仅当它有一些帧要发送时，它才持有这个令牌；否则，它**立即向下一个节点转发该令牌**。当一个节点收到令牌时，如果它确实有帧要传输，它发送最大数目的帧数，然后把令牌转发给下一个节点。

令牌传递是分散的，并有很高的效率。但是它也有自己的一些问题。例如，一个节点的故障可能会使整个信道崩溃。或者如果一个节点偶然忘记了释放令牌，则必须调用某些恢复步骤使令牌返回到循环中来。

<img src="https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/image-20220502164221606.png" alt="image-20220502164221606" style="zoom:50%;" />



#### 4、局域网

##### Mac地址

32位的IP地址：网络层地址；用于将数据报传送到目的IP子网

MAC(或局域网或物理或网卡)地址:**Media Access Control Address媒体存取控制位址**

> 功能:从一个接口获取帧到另一个物理连接的接口(同一网络)
>
> 48位MAC地址(适用于大多数局域网)：1A-23-F9-CD-06-9B(16进制)
>
> 刻录在网卡ROM，有时也可设置软件

当某适配器要向某些目的适配器发送一个帧时，发送适配器将目的适配器的MAC地址插入到该帧中

##### ARP

ARP：地址解析协议address resolution protocol

> DNS在因特网中的任何地方的主机解析主机名
>
> ARP只为同一个子网上的主机和路由器接口解析IP地址

**每台主机或路由器在其内存中**具有一个ARP表(ARP table),这张表包含IP地址到MAC地址的映射关系。下图显示了在主机222.222.222.220中可能看到的ARP表中的内容。该ARP表也包含一个寿命(TTL)值，它指示了从表中删除每个映射的时间。注意到这张表不必为该子网上的每台主机和路由器都包含一个表项；某些可能从来没有进入到该表中，某些可能已经过期。从一个表项放置到某ARP表中开始，一个表项通常的过期时间是20分钟。

![image-20220502170228359](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/image-20220502170228359.png)

关于ARP协议有两件有趣的事情需要注意。首先，**查询ARP报文是在广播帧中发送的，而响应ARP报文在一个标准帧中发送**。其次，ARP是即插即用的，这就是说，一个ARP表是自动建立的，即它不需要系统管理员来配置。并且如果某主机与子网断开连接，它的表项最终会从留在子网中的节点的表中删除掉。

ARP协议是跨越链路层和网络层边界两边的协议，不完全符合简单的分层协议栈

##### 一台主机往子网之外的主机发送网络层数据报的情况——过程要明白

![image-20220414162212422](https://gitee.com/yi-junquan/image_gitee/raw/master/images/image-20220414162212422.png)

上图中的路由器R有两个接口，每个接口对应一个IP地址和一个MAC地址

子网1的网络地址为`111.111.111/24`，子网2的网络地址为`222.222.222/24`，考虑从子网1的主机`111.111.111.111`(A)要往子网2的主机`222.222.222.222`(B)发送一个IP数据报——<mark>这个过程需要理解明白</mark>

> :one: A使用源A和目的B创建IP数据报
>
> :two: A使用ARP获取R的MAC地址为111.111.111.110
>
> :three: 创建以R的MAC地址为目的的链路层帧，帧包含A-to-B IP数据报
>
> :four: A的网络适配器发送帧
>
> :five: R接口`111.111.111.110`的网络适配器接收帧
>
> :six: R接口`111.111.111.110`从帧中移出IP数据报，看到它的目的地是B，使用**路由表**转发它（决定该数据报要被转发的正确接口`222.222.222.220`）
>
> :seven: R接口`222.222.222.220`使用ARP获取B的MAC地址
>
> :eight: R接口`222.222.222.220`创建包含A-to-B IP数据报发送给B的帧
>
> :nine: R接口`222.222.222.220`网络适配器发送该帧给B
>
> :one::zero: B从帧中移出IP数据报

#### 5、以太网

##### 以太网类型

:one: 总线式的以太网：**10 Base 2——总线最长185米，使用同轴电缆**

:two: 基于集线器的星形拓扑以太网：中间设备为集线器hub

:three: **交换式的以太网**：中间设备为交换机switch

##### 以太网服务模型（CRC+CSMA/CD）

:one: 提供的是**无连接不可靠服务**：感知信道空闲则发送

:two: 接收端进行CRC检查

:three: 以太网使用基带传输baseband transmission:直接向广播信道发送数字信号

> Manchester encoding：电平从上到下代表1，从下到上代表0）用电平变化表示0/1）

![image-20220414165303566](https://gitee.com/yi-junquan/image_gitee/raw/master/images/image-20220414165303566.png)

每次电平变化表示一个0/1，抗干扰能力强

#### 6、数据链路层设备

##### Hub集线器

Hub集线器：**物理层设备**，<mark>不能隔离碰撞域</mark>——节点越多，碰撞域越大

集线器是一种物理层设备，作用于比特，当表示一个0或者一个1的比特到达一个接口时，集线器重新生成这个比特，将其能量放大，然后将其向所有其他接口传输出去

也是一种广播局域网，如果同时收到两个不同的接口的帧，会发生碰撞，节点需要重新传输帧

没有CSMA/CD，没有帧缓冲

##### 交换机

![image-20220507114703310](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/image-20220507114703310.png)

交换机是一种用于电(光)信号转发的网络设备，能连接多台设备形成一个局域网，简单来说就是可以把多台计算机连接起来，形成一个局域网。交换机是用于连接多台设备，让这些设备相互具备网络互通。

:one: 链路层设备:比Hubs更智能，更主动

> 存储、转发以太网帧
>
> 检查传入帧的MAC地址，有选择地将帧转发到一个或多个输出链路;当帧在段上转发时，使用CSMA/CD访问段

:two: 透明的：主机不知道交换机的存在

:three: 即插即用,自学习：交换机无需配置

##### 交换机与路由器比较

路由器：寻址，转发（依靠 IP 地址），交换机：过滤，转发（依靠 MAC 地址）

交换机用于连接局域网，数据包在局域网内网的数据转发，路由器用于连接局域网和外网，数据包可以在不同局域网转发。

交换机工作于TCP/IP协议的最后一层数据链路层（物理层），路由器工作于网络层

交换机负责具体的数据包传输，路由器不负责包的实际传输，路由器只封装好要传输的数据包，然后交给交换机去传输（不一定是交换机，可能是其他传输技术），用java比喻大概简单理解为路由器是抽象类，定义好传输的数据包格式，交换机是具体实现类，也可以有其他实现类

交换机没有MAC地址和IP地址，路由器有MAC地址和IP地址（指纯碎的交换机和路由器，三层交换机是可以有IP地址的，路由器也有内置交换机功能的）

两者都是存储转发互连设备，都能隔离碰撞域

> 路由器:网络层设备(检查网络层报头)
>
> 交换机:是链路层设备

路由器维护路由表，实现路由算法

交换机维护交换表，实现**CSMA/CD，过滤和转发，自学习算法**

##### 集线器和交换机的区别

集线器和交换机都是工作在TCP/IP协议的最后一层，数据链路（物理层），都是连接多个设备形成局域网的。

集线器会把接收到的数据包每次都广播给局域网局域网的所有计算机，而交换机只有首次在MAC地址表找不到记录才广播，其他时候是直接单独发送给对应MAC地址的计算机。交换机可以说是集线器的升级改良版，在集线器的基础上多了MAC地址表，可以分割冲突域，更加智能化。

集线器的数据传输方式是广播方式，而交换机的数据传输是有目的的，数据只对目的节点发送，只是在自己的MAC地址表中找不到的情况下第一次使用广播方式发送，然后因为交换机具有MAC地址学习功能，第二次以后就不再是广播发送了，又是有目的的发送。这样的好处是数据传输效率提高，不会出现广播风暴，在安全性方面也不会出现其它节点侦听的现象

### 名词解释

MAC：媒体访问控制(Medium Access Control）

NIC：Network Interface Card,网络接口卡，也称为网络适配器，是链路层的主体部分

PCMCIA：Personal Computer Memory Card International Association个人计算机存储卡国际协会

PPP：point-to-point protocol,点对点协议

HDL：high-level data link control高级数据链路控制

TDMA：time division multiple access时分多路复用

FDMA：frequency division multiple access频分多路复用

CDMA：code division multiple access码分多路复用

<mark>CSMA：carrier sense multiple access **载波侦听多路访问**</mark>

<mark>CSMA/CD：具有碰撞检测的载波侦听多路访问</mark>

MAC(或局域网或物理或网卡)地址:**Media Access Control Address媒体存取控制位址**

TTL：time to live：生存时间值



### 算法

#### 以太网CSMA/CD算法（记住）

<mark>算法伪代码</mark>

```c
A：感知信道
   if(信道空闲)
   {
       传输数据，监听信道;
       if(检测到另一个传输)//发生碰撞，信道电平会升高
        {
           中止传输并发送堵塞信号;  
           更新碰撞次数，碰撞次数+1;
           根据指数性规避算法进行延时;
           goto A;
        }else{
            将帧发送完;
            将碰撞次数设置为0;
        }
	}else{
    	等待正在进行的传输结束;
        goto A;
	}
```

**指数性的规避算法**

> 发送碰撞的站(station)在停止发送数据后，要规避一个随机时间才能再次发送数据
>
> 第一次碰撞:从{0,1}中选择K：延时为**K*512位**所需的传送延时
>
> 在第二次碰撞后:从{0,1,2,3}中选择K…
>
> 10次碰撞后，从{0,1,2,3,4，…，1023}中选择K

算法效率
$$
efficiency=\frac{1}{1+5t_{prop}/t_{trans}}
$$

**性能比ALOHA更好**:简单、便宜、去中心化

#### 自学习算法

##### 自学习

交换机具有自学习性：它的交换表是**自动、动态和自治地**建立的，即没有来自网络管理员或来自配置协议的任何干预。

这种能力是以如下方式实现的：

:one: 交换机表初始为空。

:two: 对于在每个接口接收到的每个入帧，该交换机在其表中存储：

> ①在该帧源地址字段中的MAC地址；
>
> ②该帧到达的接口；
>
> ③TTL

交换机以这种方式在它的表中记录了**发送节点所在的局域网网段**。如果在局域网上的每个主机最终都发送了一个帧，**则每个主机最终将在这张表中留有记录**。

:three: 如果在一段时间（称为老化期(aging time)后，交换机没有接收到以该地址作为源地址的帧，就在表中删除这个地址。以这种方式，如果一台PC被另一台PC（具有不同的适配器)代替，原来PC的MAC地址将最终从该交换机表中被清除掉。

假设在时刻9:39，源地址为`01-12-23-34-45-56`的一个帧从接口2到达。假设这个地址不在交换机表中。于是交换机在其表中增加一个新的表项，如图中所示假设该交换机的老化期是60min，在9:32~10:32期间源地址是`62-FE-F7-11-89-A3`的帧没有到达该交换机。那么在时刻10:32，这台交换机将从它的表中删除该地址。

![image-20220502213727337](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/image-20220502213727337.png)

#### 帧过滤和转发算法

**过滤(iltering)**是决定一个帧应该转发到某个接口还是应当将其丢弃的交换机功能。

**转发(forwarding)**是决定一个帧应该被导向哪个接口，并把该帧移动到那些接口的交换机功能。

交换机的过滤和转发借助于**交换机表(switch table)**完成。该交换机表包含某局域网上某些主机和路由器的但不必是全部的表项。交换机表中的一个表项包含：

> ①一个MAC地址
>
> ②通向该MAC地址的交换机接口
>
> ③TTL

![image-20220502212448883](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/image-20220502212448883.png)


为了理解交换机过滤和转发的工作过程，假定目的地址为`DD-DD-DD-DD-DD-DD`的帧从交换机接口x到达。交换机用MAC地址`DD-DD-DD-DD-DD-DD`索引它的表。有3种可能的情况：

:one: 表中没有对于`DD-DD-DD-DD-DD-DD`的表项。换言之，如果没有对于目的地址的表项，交换机广播该帧（flooding）

:two: 表中有一个表项将`DD-DD-DD-DD-DD-DD`与接口x联系起来。在这种情况下，该帧从包括适配器`DD-DD-DD-DD-DD-DD`的局域网网段到来。无须将该帧转发到任何其他接口，交换机通过丢弃该帧**执行过滤功能即可**。

:three: 表中有一个表项将`DD-DD-DD-DD-DD-DD`与接口y≠x联系起来。在这种情况下，该帧需要被**转发到与接口y相连的局域网网段**。交换机通过将该帧放到接口y前面的输出缓存完成转发功能。

<mark>过滤转发算法</mark>

```c
When frame received:
1. record link associated with sending host(self-learning)
2. index switch table using MAC dest address
3. if entry found for destination
   then {
     if dest on segment from which frame arrived
     then drop the frame
     else {
     	CSMA/CD
        forward the frame on interface indicated
     }
    }   
    else {
    	CSMA/CD
    	flood
    }
当接收到帧:
1. 通过自学习记录与发送主机关联的链路，在交换表中添加表项;
2. 使用帧中的目的MAC地址索引交换表表项;
3. if(使用目的MAC地址找到相应表项){
		if(该表项中的接口与该帧到达的交换机的接口一致)//即目的主机与源主机位于同一个网段
		{
			丢弃该帧;
		}else{
			执行CSMA/CD算法;
			将该帧转发到相应的接口;
		}
	}else{
		执行CSMA/CD算法;
		交换机广播该帧;
	}
```

### 练习题

1、下列哪个或者哪些设备可以隔离广播域：路由器

> 交换机和路由器都能隔离碰撞域

2、以下关于以太网交换机的叙述哪个或者哪些是正确的：

> A.可以隔离碰撞（冲突）:+1:
>
> B.运行路由协议生成帧转发表
>
> C.执行源MAC地址学习:+1:
>
> D.不能隔离广播:+1:P

3、主机A向位于不同IP网络中的主机B发送数据，在数据经过不同链路传输时，下面哪个或者哪些叙述是正确的：

> A.帧的源MAC地址和目的MAC发生变化:+1:
>
> B.帧的源MAC地址不变，目的MAC地址会发送变化
>
> C.IP分组的源IP地址和目的IP地址保持不变:+1:
>
> D.IP分组的源IP地址会发送变化，目的IP地址保持不变

4、以太网交换机接收到数据帧以后，执行的操作包括**校验和查找帧转发表**进行转发。

## 参考资料

[1] James F.Kurose，Keith W.Ross.Computer Networking—A Top-Down Approach（第6版）.北京：高等教育出版社出版者，2013年。

[2] 西安交通大学Computer Networking2022年春 课程PPT 朱利

[3] 天勤第11版 2023版计算机网络高分笔记

[4] [计算机网络-路由器和交换机的区别](https://blog.csdn.net/qq_21187515/article/details/119416934)