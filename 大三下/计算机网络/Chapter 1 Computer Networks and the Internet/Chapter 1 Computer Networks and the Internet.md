[TOC]

# [计算机网络]第一章——计算机网络和因特网

<font color="red" size=4>**仅供交流，请勿转载，侵权必删**</font>

## 概述

> 互联网是什么?
>
> 什么是协议protocol?
>
> 网络边缘;主机、接入网络、物理媒体
>
> 网络核心:分组/电路交换，Internet结构
>
> 性能:丢失、延迟、吞吐量
>
> 安全
>
> 协议层，服务模型
>
> 历史

## 一、什么是因特网

因特网是一个世界性的计算机网络

因特网是一个公共网络

> A network that interconnects millions of computing devices throughout the world

<mark>**因特网的组成**</mark>: **edges边缘,core内核 and links链路**

### 因特网组成

#### Hardware

:one: **Ends systems**：主机=端系统——运行网络应用程序

:two: **Core**: routers路由器——转发数据包(数据块)

> 由若干个路由器组成的mesh(网)
>
> 一般是有线的

:three: **Links**：通信链路

> 光纤fiber，铜，无线电radio，卫星，局域网LAN
>
> 传输速率=带宽

#### Software

Protocols & App：协议提供通信支撑

> 协议控制报文message发送、接收、转发

Networking OS：内置到操作系统中

![image-20220404213343943](https://img-blog.csdnimg.cn/img_convert/235ec3288fd5051cf151387948568974.png)

**因特网是网中网，松散分层**

> 公共因特网Internet与私人内部网intranet

**因特网标准**

> RFC:  Request for comments 意即“请求评论”，包含了关于Internet的几乎所有重要的文字资料
>
> 由Internet工程委员会“因特网工程师任务组”IETF以及IETF 下属的“因特网工程师指导组”IESG 定义

### 服务角度

通信基础设施支持分布式应用:

网络，网络电话，电子邮件，游戏，电子商务，文件共享

因特网为应用程序提供的通信服务:

> :one: 可靠的：不会丢失、顺序不乱：TCP协议
>
> :two: 不可靠的**“best effort” 尽力而为(unreliable)** data delivery：UDP协议
>

## 二、协议protocol

<mark>协议</mark>定义了报文的**格式、命令以及报文传输和接收时所采取的行动**

> Protocol={*format*格式, *order*命令, *actions*动作}  ={syntax语法,semantic语义,rules规则}

因特网上的所有通信活动都受协议控制

协议在Internet中无处不在，不同的协议被用来完成不同的通信任务

## 三、因特网边缘edge

**端系统(主机)**

> 运行应用程序
>
> 例如网络,电子邮件
>
> 在“网络边缘”

**客户机/服务器模型C/S**

> 客户端主机请求，从始终在线的服务器接收服务
>
> 例如Web浏览器/服务器;电子邮件客户机/服务器

**对等模型P2P**:

> 最少(或不)使用专用服务器
>
> 例如Skype,BitTorrent

终端系统如何连接到边缘路由器?

> 住宅访问网
>
> 机构接入网(学校、公司)
>
> 移动接入网络

考虑因素

> 接入网的带宽(比特/秒)
>
> 共享或专用
>
> 价格

### 接入网

接入网，这是指将端系统物理连接到其边缘路由器(edge router)的网络。

边缘路由器是端系统到任何其他远程端系统的路径上的第一台路由器。

#### 家庭入网方式

> 宽带入网的两种流行方式：数字用户线DSL和电缆
>
> 新兴技术：FTTH光纤入户

##### Dial-up Modem拨号调制解调器

![image-20220118114703466](https://img-blog.csdnimg.cn/img_convert/1ba9a03694f31c4f97bead7d7df03667.png)

单个网卡来说，是串行通信

PSTN：电话网络

使用现有的电话基础设施

> Home与中心办公室相连
>
> 最高达56Kbps的带宽(通常更少)
>
> 不能同时上网和打电话:不能“一直开机”

##### Asymmetric Digital Subscriber Line (ADSL)非对称数字用户线

上行链路（家庭给服务商）带宽窄，下行链路（服务商给家庭）带宽宽

ISP: 网络服务提供者

![image-20220118115004966](https://img-blog.csdnimg.cn/img_convert/2325c704415ee43fe1d455bf63b3dba4.png)

> 也使用现有的电话基础设施
>
> 最高可达1Mbps(目前通常小于256kbps)
>
> 下行8Mbps(现在通常小于1Mbps)
>
> 专用物理线路到电话中心办公室

##### HFC混合光纤同轴电缆

驻家同轴电缆10Mbps，离家部分是光纤（传输更快）

需要光电转换设备

![image-20220118115610294](https://img-blog.csdnimg.cn/img_convert/b5e121b372e333aa0479659f327b4910.png)

##### FTTH：Fiber to the Home光纤入户

从本地中心局直接到家庭提供了一条光纤路径

全部采用光纤通信

![image-20220118115850169](https://img-blog.csdnimg.cn/img_convert/3dfb23c51d70159c0a7a235813b7b560.png)

两种相互竞争的光学技术:

> 被动光网络: PON，无电源
>
> 主动光网络: AON

速度非常快;光纤还承载电视和电话服务

#### 企业（和家庭）接入

##### 以太网Ethernet Internet access

![image-20220302142709496](https://img-blog.csdnimg.cn/img_convert/6123d74d8d121d9817e9aa34e40bd8ce.png)

通常用于公司、大学等

10mbps, 100Mbps, 1Gbps, 10Gbps以太网

现在，终端系统通常连接到以太网交换机

##### Wireless access networks 无线接入

![image-20220302142741843](https://img-blog.csdnimg.cn/img_convert/ceed23f58923a0ae2d8e7a7e9d306afb.png)

共享无线接入网络将终端系统连接到路由器

> 通过基站也就是“接入点”

有两种方式

> 无线局域网WLAN：Wifi
>
> wider-area wireless access：运营商提供：4g/5g

### Physical Media物理媒介

物理媒介分为两种：

> **有向方式媒介guided media**，如光缆、双绞铜线或同轴电缆
>
> **无向方式媒介unguided media**：电波在空气或外层空间中传播，如无线局域网

#### 双绞铜线Twisted Pair (TP)

最便宜而且最常用，电话线就是双绞铜线

#### 同轴电缆Coaxial cable

单通道电缆

传统以太网

#### 光纤电缆Fiber optic cable

玻璃纤维携带光脉冲，每个脉冲一个比特

**优点**：带宽宽；误差率低: 中继器间隔较远;**不受电磁噪声影响**；信号不容易被劫持

#### 无线电类型

###### 地面微波

例如高达45Mbps的频道

##### WLAN(例如无线网络)

11Mbps, 54 Mbps,300+Mbps

##### 广域(如手机)

4G/5G cellular: 1G-10+Gbps

##### 卫星

Kbps至45Mbps频道(或多个较小频道)

270msec端到端延迟

地球同步与低空

## 四、网络内核

<mark>网络内核是由路由器互联组成的网</mark>

主要部件是路由器：保证数据包走最优路径到目标端

<mark>**数据交换方式**</mark>

> :one: **电路交换circuit switching**：使用电话拨号,信号从发送端直接发送到接收端，一位位传，实时性比较好
>
> :two: **分组交换packet-switching**：路由器采用，不需要拨号，将数据封装成小包，放到队列中，按照算法将数据包放到数据端口，发送到下一个端口。存储：排队的过程；转发：找到出口
>
> :three: **虚电路交换**：数据按照包传输，但是需要拨号，拨号的过程就是链路建立的过程，没有一根线连起来，中间交换设备会将资源准备好，ATM网络

### 电路交换

需要拨号，拨号的过程是资源预留的过程，拨通了才能传输数据。打电话就是这个方式。

<img src="https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/qshell/image-20220302145306543.png" alt="image-20220302145306543" style="zoom:50%;" />

每个呼叫会分配到一部分资源（带宽），资源的常用使用方式有两种FDM频分复用、TDM时分复用

<img src="https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/qshell/image-20220302145617686.png" alt="image-20220302145617686" style="zoom:50%;" />

**举例**

> 通过电路交换网络，从主机A发送一个64万比特的文件到主机B需要多长时间?
>
> 所有链路的带宽为1.536 Mbps
>
> 每条链路使用24slots/sec的时分复用(TDM)
>
> 500毫秒建立端到端电路
>
> 解答：
>
> 数据传输：640000 / (1.536 Mbps / 24) = 10 (s)
>
> 总时长：10s+0.5s=10.5s

### 分组交换

因特网的网络内核使用到的就是分组交换

为了从源端系统向目的端系统发送一个报文，源将长报文划分为较小的数据块，称之为分组（packet）。在源和目的地之间，每个分组都通过**通信链路和分组交换机**（packet switch）传送。（交换机主要有两类：**路由器（router）**和**链路层交换机（link-layer switch）**。）分组以等于该链路最大传输速率的速度传输通过通信链路

#### 存储转发传输

**存储转发**指的是路由器（交换机）在向输出链路传输该包的第一个bit前必须等待包中的所有内容到达队列

![image-20220302152831810](https://img-blog.csdnimg.cn/img_convert/230726e98b0f30f7ab483cb56e6f2434.png)

如上图：发送L位的包，中间有两个路由器，三个链路，带宽为R

总共花费的时间是三倍的L/R

#### 统计复用

分组交换资源使用的方式是：<mark>**统计复用statistical multiplexing**（因特网内核资源使用方式）</mark>

指的是多个用户都可以发送数据，最终按照实际使用量去计算，资源没有划分开

![image-20220302152434887](https://img-blog.csdnimg.cn/img_convert/6e90741f67c06cbdca7b1dcc64dc5e8d.png)

图中A、B数据传输的最大带宽是1.5Mb/s

### 电路交换与分组交换的比较

假设链路带宽为1Mb/s，每个用户使用带宽为100kb/s，每次使用10%的时间

对于电路交换，最多容纳10位用户

对于分组交换，可以容纳35个用户以上

:one: <mark>分组交换允许更多的用户使用网络</mark>

:two: 分组交换适用于突发数据

:three: 分组交换资源利用率高

:four: 分组交换技术比较简单

:five: 电话交换实时性更好

### 因特网架构——网中网

有许多一级服务商，每个一级服务商对应许多二级服务商，每个二级服务商对应许多本地服务商

![image-20220120174257475](https://img-blog.csdnimg.cn/img_convert/a4d1c96b45ec882e7e4fb429da313882.png)

## 五、分组交换网中的延时、丢包和吞吐量

### 延时delay

#### 何时发生

数据包在路由器缓冲区中排队

> 数据包到达链路的速率超过了链路的输出容量
>
> 分组排队，等待轮到

![image-20220301102936843](https://img-blog.csdnimg.cn/img_convert/8fe2a839f6f7e9ad1a227ff52c7b757e.png)

#### 类型

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

![image-20220305095744588](https://img-blog.csdnimg.cn/img_convert/8ede7ea7a93e1dd6a536607bb29ccef0.png)

每经历一跳，都会经历这四个延时，排队延时不可忽略，其他的都可以忽略，卫星电路的传播延时不可忽略

对于排队延迟，可以用堵塞程度来描述

> R =链路带宽(bps)
>
> L =包长度(bit)
>
> a =数据包平均到达速率
>
> traffic intensity = La/R                             
>
> > La/R ~ 0: 平均排队延迟小
> >
> > La/R -> 1: 延迟变大
> >
> > La/R > 1: 到达的“工作”比能得到的服务更多，平均延迟无限大

### 吞吐量

**吞吐量throughput**: 发送端和接收端之间传输比特的速率(比特/时间单位)

> 瞬时instantaneous: 给定时间点的速率
>
> 平均average: 在较长的一段时间内的比率

![image-20220301102504433](https://img-blog.csdnimg.cn/img_convert/ff7c903e9788f58eec37f76690551ebf.png)

吞吐量取决于Rs、R和Rc中较小的部分（瓶颈bottleneck）

![image-20220301103651781](https://img-blog.csdnimg.cn/img_convert/3d9282d528d82eb7dfe628834f2fe6ce.png)

若有n个连接共享R，则吞吐量计算时还要将R➗n来算最小值

## 六、协议层次及其模型

Protocol “Layers”

互联网十分复杂，采用层次组织

**分层好处**：

> 处理复杂系统:明确的结构允许识别复杂系统的各部分的关系；模块化便于系统的维护和更新

分层是有害的吗?层与层之间存在依赖

### 因特网协议栈TCP / IP模型

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

### ISO/OSI参考模型

> ISO：国际标准化组织
>
> OSI：开放系统互连
>
> 将各个局域网互连起来

相较于TCP/IP模型，多了两个层

:label: **表示层**: 允许应用程序解释数据的含义，例如加密、压缩、特定于机器的约定

:label: **会话层**: 同步，检查点，恢复数据交换

<img src="https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/qshell/image-20220301105937817.png" alt="image-20220301105937817" style="zoom:67%;" />

### 封装

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

因特网不太适合实时通信、电话网络比较适合

## 七、因特网发展历史

:one: 分组交换的发展：1961-1972

> 最开始是为了发邮件
>
> 1967: ARPAnet

:two: 专用网络和网络互连：1972-1980

> 1983：TCP/IP开始部署 （TCP/IP不只是表示两个协议，而是所有的网络协议，不能少斜杠）

## 练习题

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

## 参考资料

[1] James F.Kurose，Keith W.Ross.Computer Networking—A Top-Down Approach（第6版）.北京：高等教育出版社出版者，2013年。

[2] 西安交通大学Computer Networking2022年春 课程PPT 朱利