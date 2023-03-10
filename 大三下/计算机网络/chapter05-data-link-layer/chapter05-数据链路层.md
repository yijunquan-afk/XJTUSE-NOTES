[TOC]

# 第五章——数据链路层

## 5.1 链路层概述

**节点(node)**：运行链路层协议(即第2层)协议的任何设备。节点包括**主机、路由器**、交换机和WiFi接入点

**链路**:**连接相邻节点的通信通道**

> 局域网,无线局域网也是链路

<mark>数据链路层负责**通过链路将数据报从一个节点传输到相邻节点**</mark>

第2层**数据包是一个帧frame：封装数据报**

**在不同的链路上通过不同的链路协议传输的数据报,提供的服务也不同**

> Ethernet link
>
> Ppp link
>
> ATM link 
>
> Frame relay
>
> Fiber link
>
> 802.11 link: one type of wireless links：WIFI
>
> 4G/5G link
>
> 使用的技术都比较简单，没可靠性控制

### 5.1.1 链路层提供的服务

:one: **成帧(framing)**。在每个**网络层数据报**经链路传送之前，几乎所有的链路层协议都要将其用链路层帧封装起来。一个帧由一个数据字段和若干首部字段组成，其中网络层数据报就插在数据字段中。帧的结构由链路层协议规定。

:two: **链路接入**。<mark>**媒体访问控制(Medium Access Control,MAC)协议**</mark>(`网卡地址，48位，6个字段`)规定了帧在链路上传输的规则。对于在链路的一端仅有一个发送方、链路的另一端仅有一个接收方的点对点链路，MAC协议比较简单（或者不存在），即无论何时链路空闲，发送方都能够发送帧。更有趣的情况是当多个节点共享单个广播链路时，即所谓多路访问问题。这里，MAC协议用于协调多个节点的帧传输。

:three: **可靠交付(reliable delivery)**。当链路层协议提供可靠交付服务时，它保证无差错地经链路层移动每个网络层数据报。前面讲过，某些运输层协议（例如TCP)也提供可靠交付服务。与运输层可靠交付服务类似，链路层的可靠交付服务通常是**通过确认和重传取得**的。链路层可靠交付服务通常**用于易于产生高差错率的链路**，例如无线链路，其目的是本地（也就是在差错发生的链路上）纠正一个差错，而不是通过运输层或应用层协议迫使进行端到端的数据重传。然而，对于低比特差错的链路，包括光纤、同轴电缆和许多双绞铜线链路，链路层可靠交付可能会被认为是一种不必要的开销。由于这个原因，许多有线的链路层协议不提供可靠交付服务。

:four: **差错检测Error detection和纠正Error correction**。有01跳变就把帧扔掉。传输层和网络层也提供差错检测。**链路层的差错检测通常更复杂，并且用硬件实现**。差错纠正类似于差错检测，区别在于接收方不仅能检测帧中出现的比特差错，而且能够准确地确定帧中的差错出现的位置（并**因此纠正这些差错**)。网卡没有差错恢复功能。

:five: **半双工和全双工通信**(*Half-duplex and full-duplex communication*)：在半双工模式下，链路两端的节点可以进行传输，但不能同时进行（对讲机）

:six: **流控制（Flow Control）**：在相邻的发送和接收节点之间移动

### 5.1.2 链路层在何处实现

链路层的主体部分是在**网络适配器(network adapter)**中实现的，网络适配器有时也称为**网络接口卡(Network Interface Card,NIC)**。位于网络适配器核心的是链路层控制器，该控制器通常是一个实现了许多链路层服务（成帧、链路接人、差错检测等)的**专用芯片**。因此，链路层控制器的许多功能是用**硬件**实现

![image-20220502135523686](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/image-20220502135523686.png)

> PCMCIA：Personal Computer Memory Card International Association个人计算机存储卡国际协会
>

**发送接收过程**

> :one: **发送端**，控制器取得了由协议栈较高层生成并存储在主机内存中的数据报，在链路层顿中封装该数据报（填写该顿的各个字段），然后遵循链路接入协议将该帧传进通信链路中。
>
> :two: **接收端**，控制器接收了整个帧，抽取出网络层数据报。如果链路层**执行差错检测**，则需要发送控制器在该帧的首部设置差错检测比特，由接收控制器执行差错检测。
>
> ![image-20220412103612010](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/qshell/image-20220412103612010.png)

## 5.2 差错检测与纠正

在发送节点。为了保护比特免受差错。使用**差错检测和纠正比特EDC**（Error Detection and Correction bits）来增强数据D

接收方的挑战是在只收到D'和EDC'的情况下，确定D'是否和初始的D相同

即使使用差错检测比特，还是有可能有未检出比特差错；差错检测和纠错计数越复杂，开销也越大

![image-20220502141152377](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/image-20220502141152377.png)

在传输数据中检测差错的3种技术：

:one: **奇偶校验**（它用来描述差错检测和纠正背后隐含的基本思想)

:two: **检验和方法**（它通常更多地应用于传输层）

:three: **循环冗余检测**（它通常更多地应用在适配器中的链路层）。

### 5.2.1 奇偶校验

单个奇偶校验（single parity bit）：检测一位的错误

![image-20220502141955395](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/image-20220502141955395.png)

二维奇偶校验（two-dimensional parity)：可以检测出单个比特的错误，而且可以找出位置并纠正

![image-20220502142425029](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/image-20220502142425029.png)

### 5.2.2 循环冗余校验CRC【Cyclic Redundancy Check】

现今的计算机网络中广泛应用的差错检测技术基于循环冗余检测(Cyclic Redundancy Check,CRC)编码。CRC编码也称为**多项式编码(polynomial code)**，因为该编码能够将要发送的比特串看作为系数是0和1一个多项式，对比特串的操作被解释为多项式算术。

CRC编码操作如下。考虑d比特的数据D,发送节点要将它发送给接收节点。发送方和接收方首先必须协商一个**r+1比特模式，称为生成多项式(generator),我们将其表示为G**。我们将要求G的最高有效位的比特（最左边）是1。CRC编码的关键思想如图所示。对于一个给定的数据段D,发送方要选择r个附加比特R,并将它们附加到D上，使得得到的d+r比特模式（被解释为一个二进制数)用模2算术恰好能被G整除(即没有余数)。用CRC进行差错检测的过程因此很简单：**接收方用G去除接收到的d+r比特。如果余数为非零，接收方知道CRC出现了差错；否则认为数据正确而被接收。**

![image-20220502143257508](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/image-20220502143257508.png)

所有CRC计算采用模2算术来做，在加法中不进位，在减法中不借位。这意味着加法和减法是相同的，而且这两种操作等价于**操作数的按位异或(XOR)**。因此，举例来说：
$$
1011\oplus 0101=1110\\
1001\oplus 1101=0100
$$
类似的有
$$
1011- 0101=1110\\
1001- 1101=0100
$$
除了所需的加法或减法操作没有进位或借位外，乘法和除法与在二进制算术中是相同的。如在通常的二进制算术中那样，乘以2^k^就是以一种比特模式左移k个位置。这样，给定D和R，D·2^r^ XOR R产生如所示的d+r比特模式。

要求出R使得对于n有：
$$
D\cdot 2^r\oplus R=nG
$$
等价于
$$
D\cdot 2^r=nG\oplus R
$$
这个等式告诉我们，如果我们用G来除D·2^r^，余数值刚好是R。换句话说，我们可以这样计算R：
$$
R=\text{remainder}\frac{D\cdot 2^r}{G}
$$
:apple: **举例说明**：D=101110，d=6，G=1001，r=3，传输的数据为101110011

<img src="https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/image-20220502144710320.png" alt="image-20220502144710320" style="zoom:50%;" />



具有r检测位的多项式能检测出所有小于或等于r的突发错误

长度大于r+1的错误逃脱的概率为1/2^r^

## 5.3 多路访问链路和协议

有三种类型的网络链路：点对点链路、广播链路以及交换链路。

:one: 点对点链路(point--to-point link)：由链路一端的单个发送方和链路另一端的单个接收方组成。许多链路层协议都是为点对点链路设计的，如**点对点协议(point-to-point protocol,PPP)和高级数据链路控制(high-level data link control,HDLC)**就是两种这样的协议

:two: 广播链路(broadcast link),它能够让多个发送和接收节点都连接到相同的、单一的、共享的广播信道上。这里使用术语“广播”是因为当任何一个节点传输一个帧时，信道广播该帧，每个其他节点都收到一个副本。

> 老式的以太网
>
> upstream HFC
>
> 802.11 wireless LAN

:three: 交换式链路：以太网

因为所有的节点都能够传输帧，所以多个节点可能会同时传输帧。当发生这种情况时，所有节点同时接到多个帧；这就是说，传输的帧在所有的接收方处碰撞(collide)了。通常，当碰撞发生时，没有一个接收节点能够有效地获得任何传输的帧；在某种意义下，碰撞帧的信号纠缠在一起。因此，涉及此次碰撞的所有帧都丢失了，在碰撞时间间隔中的广播信道被浪费了。显然，如果许多节点要频繁地传输帧，许多传输将导致碰撞，广播信道的大量带宽将被浪费掉。当多个节点处于活跃状态时，为了确保广播信道执行有用的工作，以某种方式协调活跃节点的传输是必要的。这种协调工作由**多路访问协议**负责。

多路访问控制协议MAC protocol可以划分为三种类型：

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

### 5.3.1 信道划分协议

#### TDMA：time division multiple access时分多路复用

将时间划分为时间帧，并进一步划分每一个时间帧为N个时隙slot，把每个时隙分给N个节点中的一个

![image-20220502155039255](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/image-20220502155039255.png)

相当于一个人讲完话，再轮到下一个人讲相同的时间

假设信道通信速率为R，TDMA使得每个结点得到的速率只有R/N，即便只有它一个在传输数据

**座机电话**

#### FDMA：frequency division multiple access频分多路复用

将信道划分为不同的频段，每个频段具有R/N带宽，并将每个频率分给N个结点中的一个

![image-20220502155305873](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/image-20220502155305873.png)

FDM在单个较大的Rbps信道中创建了N个较小的R/Nbps信道

**ADSL、WIFI**

#### CDMA：code division multiple access码分多路复用

CDMA为每个节点分配一个不同的编码，抗干扰能力强，不同节点能够同时传输

### 5.3.2随机接入协议

在随机接入协议中，一个传输节点总是**以信道的全部速率（即Rbps)进行发送**。当有碰撞时，涉及碰撞的每个节点反复地**重发**它的帧（也就是分组），到该帧无碰撞地通过为止。但是当一个节点经历一次碰撞时，它不必立刻重发该帧。相反，它在重发该桢之前**等待一个随机时延**。涉及碰撞的每个节点**独立地选择随机时延**。因为该随机时延是独立地选择的，所以下述现象是有可能的：这些节点之一所选择的时延充分小于其他碰撞节点的时延，并因此能够无碰撞地将它的帧在信道中发出。

> ALOHA
>
> slotted ALOHA
>
> CSMA，CSMA/CD，CSMA/CA
>

#### slotted ALOHA

ALOHA：additive link on-link hawaii system

**有如下假设**

> 所有帧由L比特组成
>
> 时间被划分成长度为L/R秒的时隙（这就是说，**一个时隙(时间片)等于传输一帧的时间**）。
>
> 节点只在**时间片起点开始传输帧**。
>
> 节点是同步的，每个节点都知道时间片何时开始。
>
> 如果在一个时间片中有两个或者更多个帧碰撞，则所有节点在该时间片结束之前检测到该碰撞事件。

令P是一个概率，即一个在0和1之间的数。在每个节点中，时隙ALOHA的操作是简单的：

> :one: 当节点有一个新帧要发送时，它等到下一个时间片开始并在该时间片传输整个帧。
>
> :two: 如果没有碰撞，该节点成功地传输它的帧，从而不需要考虑重传该帧。（如果该节点有新帧，它能够为传输准备一个新帧。)
>
> :three: 如果有碰撞，该节点在时间片结束之前检测到这次碰撞。该节点以概率在后续的每个时间片中重传它的帧，直到该帧被无碰撞地传输出去。

![image-20220502160313204](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/image-20220502160313204.png)

C：碰撞时间片	E：空闲时间片	S：成功时间片

节点1、2和3在第一个时间片碰撞。节点2最终在第4个时间片成功，节点1在第8个时间片成功，节点3在第9个时间片成功

**效率分析**

> 现在我们继续概要讨论时隙ALOHA最大效率的推导过程。为了保持该推导简单，我们对协议做了一点修改，假设每个节点试图在每个时隙以概率P传输一帧。（这就是说，我们假设每个节点总有帧要发送，而且节点对新帧和已经经历一次碰撞的顿都以概率传输。)假设有N个节点。则一个给定时隙是成功时隙的概率为节点之一传输而余下的N一1个节点不传输的概率。一个给定节点传输的概率是P；剩余节点不传输的概率是(1-P)^N-1^。因此，一个给定节点成功传送的概率是p(1-p)^N-1^。因为有N个节点，任意一个节点成功传送的概率是Np(1-p)^N-1^。
>
> <mark>这个协议的最大效率为1/e=37%</mark>，即有效传输速率为0.37Rbps，37%的时间片是空闲的。

**优缺点**

> 优点
>
> > 单个活动节点可以以全信道速率连续传输
> >
> > 高度去中心化:只有节点中的时间片才需要同步
> >
> > 简单
>
> 缺点
>
> > 碰撞会浪费时间片
> >
> > 存在空闲时间片
> >
> > 节点必须在时间片结束之前检测碰撞
> >
> > 时钟需要同步

#### Pure(unslotted) ALOHA

时隙ALOHA协议要求所有的节点同步它们的传输，以在每个时隙开始时开始传输。第一个ALOHA协议[Abramson1970]实际上是一个非时隙、完全分散的协议。在纯ALOHA中，当一帧首次到达（即一个网络层数据报在发送节点从网络层传递下来），节点立刻将该帧完整地传输进广播信道。如果一个传输的帧与一个或多个传输经历了碰撞，这个节点将立即（在完全传输完它的碰撞帧之后）以概率重传该帧。否则，该节点等待个帧传输时间。在此等待之后，它则以概率p传输该帧，或者以概率1-p在另一个帧时间等待（保持空闲）。

为了确定纯ALOHA的最大效率，我们关注某个单独的节点。我们的假设与在时隙ALOHA分析中所做的相同，取帧传输时间为时间单元。在任何给定时间，某节点传输一个帧的概率是P。假设该帧在时刻。开始传输。如图所示，为了使该帧能成功地传输，在时间间隔[t~0~-1,t~0~]中不能有其他节点开始传输。这种传输将与节点i的帧传输起始部分相重叠。所有其他节点在这个时间间隔不开始传输的概率是(1-P)^N-1^。类似地，当节点i在传输时，其他节点不能开始传输，因为这种传输将与节点i传输的后面部分相重叠。所有其他节点在这个时间间隔不开始传输的概率也是(1-P)^N-1^。因此，一个给定的节点成功传输一次的概率是P(1-P)^2(N-1)^。通过与时隙ALOHA情况一样来取极限，我们求得纯ALOHA协议的<mark>最大效率仅为**1/(2e)**</mark>,这刚好是时隙ALOHA的一半。这就是完全分散的ALOHA协议所要付出的代价。

![image-20220502161729094](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/image-20220502161729094.png)

#### CSMA

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

#### CSMA/CD(Collision Detection)

<mark>CSMA/CD：具有碰撞检测的载波侦听多路访问</mark>

<mark>检测到碰撞时中止传输</mark>

![image-20220502163145384](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/image-20220502163145384.png)

碰撞检测:

> 在有线局域网中实现简单：测量信号强度，比较发射、接收信号
>
> 在无线局域网中困难：接收到的信号强度超过了本地传输强度，使用避免碰撞

### 5.3.3 轮流协议Taking-turns Protocol

#### 三种协议的比较

信道划分MAC协议：在高负载时有效和公平地共享信道；低负载低效率

随机访问MAC协议：低负载高效:单节点可以充分利用信道；高负载会产生碰撞开销

轮流协议：二者的优点结合

#### 集中式轮询polling

轮询协议要求这些节点之一要被指定为主节点。主节点以循环的方式轮询每个节点。特别是，主节点首先向节点1发送一个报文，告诉它（节点1）能够传输的帧的最多数量。在节点1传输了某些帧后，主节点告诉节点2它（节点2）能够传输的帧的最多数量。主节点能够通过观察在信道上是否缺乏信号，来决定一个节点何时完成了帧的发送。上述过程以这种方式继续进行，主节点以循环的方式轮询了每个节点

![image-20220502163956902](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/image-20220502163956902.png)

#### 令牌传递token passing

在这种协议中没有主节点。一个称为令牌(tokn)的小的特殊帧在节点之间以某种固定的次序进行交换。例如，节点1**可能总是把**令牌发送给节点2，节点2可能总是把令牌发送给节点3，而节点N可能
总是把令牌发送给节点1。当一个节点收到令牌时，仅当它有一些帧要发送时，它才持有这个令牌；否则，它**立即向下一个节点转发该令牌**。当一个节点收到令牌时，如果它确实有帧要传输，它发送最大数目的帧数，然后把令牌转发给下一个节点。

令牌传递是分散的，并有很高的效率。但是它也有自己的一些问题。例如，一个节点的故障可能会使整个信道崩溃。或者如果一个节点偶然忘记了释放令牌，则必须调用某些恢复步骤使令牌返回到循环中来。

<img src="https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/image-20220502164221606.png" alt="image-20220502164221606" style="zoom:50%;" />

## 5.4 交换局域网

### 5.4.1 Mac地址和APR

32位的IP地址：网络层地址；用于将数据报传送到目的IP子网

MAC(或局域网或物理或网卡)地址:**Media Access Control Address媒体存取控制位址**

> 功能:从一个接口获取帧到另一个物理连接的接口(同一网络)
>
> 48位MAC地址(适用于大多数局域网)：1A-23-F9-CD-06-9B(16进制)
>
> 刻录在网卡ROM，有时也可设置软件

当某适配器要向某些目的适配器发送一个帧时，发送适配器将目的适配器的MAC地址插入到该帧中

#### 5.4.2 ARP

因为存在网络层地址（例如，因特网的P地址）和链路层地址（MAC地址），所以需要在它们之间进行转换。对于因特网而言，这是地址解析协议的任务

ARP：地址解析协议address resolution protocol

> DNS在因特网中的任何地方的主机解析主机名
>
> ARP只为同一个子网上的主机和路由器接口解析IP地址

**每台主机或路由器在其内存中**具有一个ARP表(ARP table),这张表包含IP地址到MAC地址的映射关系。下图显示了在主机222.222.222.220中可能看到的ARP表中的内容。该ARP表也包含一个寿命(TTL)值，它指示了从表中删除每个映射的时间。注意到这张表不必为该子网上的每台主机和路由器都包含一个表项；某些可能从来没有进入到该表中，某些可能已经过期。从一个表项放置到某ARP表中开始，一个表项通常的过期时间是20分钟。

![image-20220502170228359](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/image-20220502170228359.png)

现在假设主机222.222.222.220要发送一个数据报，该数据报要P寻址到本子网上另一台主机或路由器。发送主机需要获得给定P地址的目的主机的MAC地址。如果发送方的ARP表具有该目的节点的表项，这个任务是很容易完成的。

如果ARP表中当前没有该目的主机的表项？特别是假设222.222.222.220要向222.222.222.222发送数据报。在这种情况下，发送方用ARP协议来解析这个地址。首先，发送方构造一个称为ARP分组(ARP packet)的特殊分组。一个ARP分组有几个字段，包括发送和接收IP地址及MAC地址。ARP查询分组和响应分组都具有相同的格式。**ARP查询分组的目的是询问子网上所有其他主机和路由器，以确定对应于要解析的P地址的那个MAC地址。**

回到我们的例子上来，222.222.222.220向它的适配器传递一个ARP查询分组，并且指示适配器应该用**MAC广播地址**（即FF-FF-FF-FF-FF-FF)来发送这个分组。适配器在链路层帧中封装这个ARP分组，用广播地址作为帧的目的地址，并将该帧传输进子网中。包含该ARP查询的帧能被子网上的所有其他适配器接收到，并且（由于广播地址）每个适配器都把在该帧中的ARP分组向上传递给ARP模块。这些ARP模块中的每个都检查它的IP地址是否与ARP分组中的目的IP地址相匹配。与之匹配的一个给查询主机**发送回一个带有所希望映射的响应ARP分组**。**然后查询主机222.222.222.220能够更新它的ARP表，并发送它的IP数据报，该数据报封装在一个链路层帧中**，并且该帧的目的MAC就是对先前ARP请求进行响应的主机或路由器的MAC地址。

关于ARP协议有两件有趣的事情需要注意。首先，**查询ARP报文是在广播帧中发送的，而响应ARP报文在一个标准帧中发送**。其次，ARP是即插即用的，这就是说，一个ARP表是自动建立的，即它不需要系统管理员来配置。并且如果某主机与子网断开连接，它的表项最终会从留在子网中的节点的表中删除掉。



ARP协议是跨越链路层和网络层边界两边的 协议，不完全符合简单的分层协议栈

#### 5.4.3 发送数据报到另一个局域网LAN

现在我们考虑一下一台主机往子网之外的主机发送网络层数据报的情况

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

### 5.4.2 以太网Ethernet

目前最流行的有线局域网，技术实现简单，成本很低，带宽也还行10Mbps~10Gbps

![image-20220414162520930](https://gitee.com/yi-junquan/image_gitee/raw/master/images/image-20220414162520930.png)

90年代初期及以前使用的是总线式的以太网：**10 Base 2——总线最长185米，使用同轴电缆**

90年代后期出现了新型的以太网——基于集线器的星形拓扑以太网

> 集线器是一种物理层设备，作用于比特，当表示一个0或者一个1的比特到达一个接口时，集线器重新生成这个比特，将其能量放大，然后将其向所有其他接口传输出去
>
> 也是一种广播局域网，如果同时收到两个不同的接口的帧，会发生碰撞，节点需要重新传输帧

![image-20220502205427515](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/image-20220502205427515.png)

现如今，位于中心的集线器被交换机switch替代，发展为**交换式的以太网**

#### 以太网帧结构

头和尾合起来称为帧头，位于两端

![image-20220414163255437](https://gitee.com/yi-junquan/image_gitee/raw/master/images/image-20220414163255437.png)

:one: Preamble：前导码，在帧的前面插入8B，时发送端和接收端进行时钟同步。由7B的前同步码和1B的帧开始界符组成

:two: 目的地址、源地址：各是48位的MAC地址

:three: 类型：2B，指出数据中携带的数据应该交给那个协议实体处理：IP，Novell IPX，AppleTalk

:four: CRC：4B，循环冗余检测字段：检测差错

:five: 数据：46B~1500B

#### 以太网服务模型（CRC+CSMA/CD）

:one: 提供的是**无连接不可靠服务**：感知信道空闲则发送

:two: 接收端进行CRC检查

:three: 以太网使用基带传输baseband transmission:直接向广播信道发送数字信号

> Manchester encoding：电平从上到下代表1，从下到上代表0）用电平变化表示0/1）

![image-20220414165303566](https://gitee.com/yi-junquan/image_gitee/raw/master/images/image-20220414165303566.png)

每次电平变化表示一个0/1，抗干扰能力强

##### 以太网CSMA/CD算法（记住）

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

### 5.4.3 数据链路层设备

#### Hub集线器

Hub集线器：**物理层设备**，<mark>不能隔离碰撞域</mark>——节点越多，碰撞域越大

集线器是一种物理层设备，作用于比特，当表示一个0或者一个1的比特到达一个接口时，集线器重新生成这个比特，将其能量放大，然后将其向所有其他接口传输出去

也是一种广播局域网，如果同时收到两个不同的接口的帧，会发生碰撞，节点需要重新传输帧

没有CSMA/CD，没有帧缓冲

#### 交换机

:one: 链路层设备:比Hubs更智能，更主动

> 存储、转发以太网帧
>
> 检查传入帧的MAC地址，有选择地将帧转发到一个或多个输出链路;当帧在段上转发时，使用CSMA/CD访问段
>

:two: 透明的：主机不知道交换机的存在

:three: 即插即用,自学习：交换机无需配置

##### 帧过滤和转发——记住算法

**过滤(iltering)**是决定一个帧应该转发到某个接口还是应当将其丢弃的交换机功能。

**转发(forwarding)**是决定一个帧应该被导向哪个接口，并把该帧移动到那些接口的交换机功能。

交换机的过滤和转发借助于**交换机表(switch table)**完成。该交换机表包含某局域网上某些主机和路由器的但不必是全部的表项。交换机表中的一个表项包含：

> ①一个MAC地址
>
> ②通向该MAC地址的交换机接口
>
> ③表项放置在表中的时间。

![image-20220502212448883](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/image-20220502212448883.png)


为了理解交换机过滤和转发的工作过程，假定目的地址为`DD-DD-DD-DD-DD-DD`的帧从交换机接口x到达。交换机用MAC地址`DD-DD-DD-DD-DD-DD`索引它的表。有3种可能的情况：

:one: 表中没有对于`DD-DD-DD-DD-DD-DD`的表项。在这种情况下，交换机向除接口x外的所有接口前面的输出缓存转发该帧的副本。换言之，如果没有对于目的地址的表项，交换机广播该帧（flooding）

:two: 表中有一个表项将`DD-DD-DD-DD-DD-DD`与接口x联系起来(这里的意思是说`DD-DD-DD-DD-DD-DD`的主机本来就在这个局域网中)。在这种情况下，该帧从包括适配器`DD-DD-DD-DD-DD-DD`的局域网网段到来。无须将该帧转发到任何其他接口，交换机通过丢弃该帧**执行过滤功能即可**。

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

##### 自学习

交换机具有自学习性：它的交换表是**自动、动态和自治地**建立的，即没有来自网络管理员或来自配置协议的任何干预。

这种能力是以如下方式实现的：

:one: 交换机表初始为空。

:two: 对于在每个接口接收到的每个入帧，该交换机在其表中存储：

> ①在该帧源地址字段中的MAC地址；
>
> ②该帧到达的接口；
>
> ③当前时间。

交换机以这种方式在它的表中记录了**发送节点所在的局域网网段**。如果在局域网上的每个主机最终都发送了一个帧，**则每个主机最终将在这张表中留有记录**。

:three: 如果在一段时间（称为老化期(aging time)后，交换机没有接收到以该地址作为源地址的帧，就在表中删除这个地址。以这种方式，如果一台PC被另一台PC（具有不同的适配器)代替，原来PC的MAC地址将最终从该交换机表中被清除掉。

假设在时刻9:39，源地址为`01-12-23-34-45-56`的一个帧从接口2到达。假设这个地址不在交换机表中。于是交换机在其表中增加一个新的表项，如图中所示假设该交换机的老化期是60min，在9:32~10:32期间源地址是`62-FE-F7-11-89-A3`的帧没有到达该交换机。那么在时刻10:32，这台交换机将从它的表中删除该地址。

![image-20220502213727337](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/image-20220502213727337.png)

##### 互联交换机

交换机可以互联在一起

![image-20220414172424610](https://gitee.com/yi-junquan/image_gitee/raw/master/images/image-20220414172424610.png)

采集进出局域网的所有链路：**端口镜像**

#### 交换机与路由器比较

两者都是存储转发互连设备，都能隔离碰撞域

> 路由器:网络层设备(检查网络层报头)
>
> 交换机:是链路层设备
>

路由器维护路由表，实现路由算法

交换机维护交换表，实现**CSMA/CD，过滤和转发，自学习算法**

### 5.4.4 虚拟局域网VLAN——了解一下

支持VLAN的交换机允许经一个单一的物理局域网基础设施定义多个虚拟局域网。在一个VLAN内的主机彼此通信，仿佛它们（并且没有其他主机)与交换机连接。在一个基于端口的VLAN中，交换机的端口（接口）由网络管理员划分为组。每个组构成一个VLAN,在每个VLAN中的端口形成一个广播域（即来自一个端口的广播流量仅能到达该组中的其他端口。

![image-20220502220221612](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/image-20220502220221612.png)



## 练习题

1、 "CompA" is trying to locate a new computer named "CompB" on the network. Which of the following does "CompA" broadcast to find the MAC address of "CompB"?

> MAC request
>
> ARP request  :+1: 
>
> ping 
>
> Telnet 
>
> proxy ARP 
>

4  Which of the following items are common to all 100BASE technologies? (Choose three.)

> frame format:+1:
>
> media 
>
> connectors  
>
> Timing:+1:
>
> multi-part encoding:+1:
>

For which of the following is Ethernet considered the standard? (Choose three.)   

> inter-building connection:+1:
>
> mid-length voice 
>
> video conferencing
>
> vertical wiring:+1:
>
> horizontal wiring   :+1:
>
> diagonal wiring 
>

4、Which term describes an ARP response by a router on behalf of a requesting host?

>  ARP 
>
>  RARP
>
>  Proxy ARP  :+1:
>
>  Proxy RARP 
>

5、What does the "10" in 10Base2 indicate about this version of Ethernet?

>  The version uses Base10 numbering within the frames. 
>
>  The version operates at a transmission rate of 10 Mbps. :+1:
>
>  Frames can travel 10 meters unrepeated. 
>
>  The maximum frame length is 10 octets. 
>


