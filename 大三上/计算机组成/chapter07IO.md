[TOC]

# 第七章 I/O

## KEY POINTS

> **1、常见的外设有哪些**
>
> **2、I/O模块，为什么需要I/O模块、I/O模块的功能、掌握完成I/O模操作需要完成的三种技术**
>
> **3、编程式I/O，执行过程，两方面CPU和I/O模块，共享公共总线时，两种寻址方式，各有什么特点，如何区分**
>
> **4、中断驱动式I/O，过程如何，结合中断，掌握中断的处理机制，设计时要考虑识别中断设备的技术，四种方式分别是什么**
>
> **5、DMA是什么，为什么效率更高，可采用哪些方式与CPU复用内存：块传送、周期窃取、交替方式、各自如何处理**
>
> **6、I/O通道，I/O通道的概念、功能、分类、最大传输率的计算与应用**

<img src="https://raw.githubusercontent.com/yijunquan-afk/img-bed-1/main/img2/1695704188.png" alt="image-20211022114603362" style="zoom:60%;" />

外围设备种类繁多

> 提供不同数量的数据
>
> 以不同的速度
>
> 根据不同的计时顺序
>
> 以不同的格式

 都比 CPU 和 RAM 慢

外围设备不直接连接到系统总线 , 需要 I/O 模块

**I/O模块**

> 接口到一个或多个外设
>
> I/O 模块化架构旨在提供系统的方法来控制与外部世界的交互，并为 OS 提供信息以有效管理 I/O 活动

## 7.1 外设

**人类可读**

> 屏幕、打印机、键盘

**机器可读**

> 磁盘和控制器

**通信**

> 网络接口卡

一些设备如下：

![image-20211022115523436](https://raw.githubusercontent.com/yijunquan-afk/img-bed-1/main/img2/1695704169.png)

![image-20211022115707831](https://raw.githubusercontent.com/yijunquan-afk/img-bed-1/main/img2/1695704171.png)

### 1、键盘/监视器

信息交换的基本单位是字符

### 2、磁盘驱动器

包含两部分：一是与I/O模块交换数据、控制和状态信号，二是用于控制磁盘的读/写机制

## 7.2 I/O模块

### 模块功能

I/O模块的功能如下：

> Control & Timing  控制与定时
>
> CPU ~ I/O Communication	处理器通信
>
> Device ~I/O Communication	设备通信
>
> Memory ~ I/O communication	存储通信
>
> Data Buffering	数据缓冲
>
> Error Detection	检错

控制从外设到处理器的数据传送包括以下几个步骤：

> :one: 处理器查询I/O模块，检查所连设备的状态
>
> :two: I/O模块返回设备状态
>
> :three: 如果设备状态正常且就绪，CPU通过向I/O模块发出命令，请求数据传送
>
> :four: I/O模块获得来自外设的一个数据单元(8位或者16位)
>
> :five: 数据从I/O模块传送到处理器

#### I/O communication

**命令译码command decoding**: I/0 模块接受来自处理器的命令，这些命令一般作为信号发送到控制总线。例如，一个用于磁盘驱动器的I/0模块，可能接受READ SECTOR (读扇区)、WRITE、SECTOR (写扇区)、SEEK (寻道)磁道号和SCAN (扫描)记录标识等命令。后两条命令中的每条都包含一个发送到数据总线上的参数。

**数据交换data exchange**:数据是在处理器和I/0模块间经由**数据总线来交换的**。

**状态报告**:由于外设速度很慢，所以知道I/O模块的状态很重要。例如，如果要求-一个I/O模块发送数据到处理器( 读操作),而该I/0模块仍在处理先前的I/O命令而对此请求未能就绪，则可以用状态信号来报告这个事实。常用的状态信号有忙( BUSY)和就绪(READY)，还有报告各种出错情况的信号。

**地址识别**:正如存储器中每个字对应一个地址- -样，每个I/0设备也有地址。因此，I/O模块必须能识别它所控制的每个外设的唯一地址 。另一方面，V0模块必须能进行设备通(devicecommunication),通信内容包括命令、状

#### Data Buffering 数据缓冲

这是I/O模块的一个重要作用

#### Error Detection 检错

包括硬件故障(纸张堵塞)与传输错误(位错误、数据损失)

### 模块结构

![image-20211027103008202](https://raw.githubusercontent.com/yijunquan-afk/img-bed-1/main/img2/1695704172.png)

地址线、控制线是CPU的，I/O设备的地址通过I/O模块识别

数据线将零碎的控制信息作为参数进行传输：比如奇偶页信息；含义明确的控制信号通过控制线传输

### I/O模块决策

支持多个或单个设别

将设备属性隐藏或揭示给CPU

控制设备功能/离开CPU

## 7.3 编程式I/O

对于编程式I/O programmed I/O，数据在处理器和I/O模块之间交换，处理器通过执行程序来直接控制I/O操作，包括检测设备状态、发送读或写命令、以及传送数据

CPU 在发出 I/O 命令时等待 I/O 模块完成操作，会浪费CPU

![](https://raw.githubusercontent.com/yijunquan-afk/img-bed-1/main/img2/1695704173.png)

**执行过程**

:one: CPU遇到一条I/O指令

:two: 它通过向I/O模块发送一个命令来执行它，并等待I/O模块就绪

:three: I/O模块执行命令，然后在I/O状态寄存器中设置适当的位

:four: CPU定期检查状态位，直到它发现操作完成

为了执行与I/O相关的指令，处理器发送一个指定具体I/O模块和外设的地址，并发送一条I/O命令。当I/O模块被处理器寻址时，它可能会就收**四种I/O命令**：

> 控制命令、测试命令、读命令、写命令

当处理器、主存和I/O共享一条公共总线时，**编址方式**主要有两种：

> **存储器映射式**memory-mapped
>
> > 设备和内存共享地址空间
> >
> > I/O看起来就像存储器read/write
> >
> > 没有针对I/O的特别命令
>
> **分离式**isolated
>
> > 单独的地址空间
> >
> > 需要I/O或存储器选择线
> >
> > 有I/O特殊命令：指令集不会太多

![image-20211027111101075](https://raw.githubusercontent.com/yijunquan-afk/img-bed-1/main/img2/1695704175.png)

**优势**

> 简单、处理器完全在控制中

**缺点**

> 耗费CPU

**解决方案**

> 使用中断机制

## 7.4 中断驱动(interrupt driven)I/O

处理器发送一个I/O命令道模块，然后去处理其他有用的工作，当I/O模块准备与处理器交换数据时，它中断处理器以请求服务，然后处理器执行数据传送，最后恢复原来的处理工作

![image-20211027112615844](https://raw.githubusercontent.com/yijunquan-afk/img-bed-1/main/img2/1695704176.png)

中断信号的发出由I/O自控

<font color="red">**中段处理过程**</font>

<img src="https://raw.githubusercontent.com/yijunquan-afk/img-bed-1/main/img2/1695704189.png" alt="image-20211027112835234" style="zoom:67%;" />

![image-20211027113524696](https://raw.githubusercontent.com/yijunquan-afk/img-bed-1/main/img2/1695704178.png)

**中断源的识别**

:one: 多条中断线

:two: 软件轮询式

> 当处理器检测到一个中断时，进入中断服务程序，轮询每一个I/O模块来确定时哪一个模块产生的中断

:three: 链式查询(硬件轮询)

> 所有I/O模块共享一个共同的中断请求行为，一旦CPU感受到中断，中断识别就会从链上发送下来。
>
> 硬件轮询更快、但是对故障很敏感

:four: 仲裁式

> 采用中断向量
>
> 模块必须占用总线才能引起中断

## 7.5 Direct Memory Access直接存储访问

编程式和中断式的**缺点**

> 编程 I/O 需要占用所有 CPU 时间 
>
> 中断驱动的 I/O 仍需要主动 CPU 干预，尽管 CPU 使用效率高于编程 I/O（传输率较低)
>
> 数据的传输必须通过CPU，转移率有限
>
> 当传输大量数据时，DMA 是一种更高效的技术

### DMA概念

DMA控制器从CPU接管I/O，无需CPU干预就可以将数据块传输到内存或者从内存中获取

对总线的使用优先级上：DMA>CPU

CPU和DMA的数据传输模式：

> :one: block transfer mode 块传输方式
>
> :two:cycle stealing mode 周期窃取式
>
> > DMA 仅在 CPU 不需要时才使用总线，或迫使 CPU 暂时停止运行 
> >
> > DMA 传输一个字的数据，然后释放总线 
> >
> > DMA 交错传输指令和数据
>
> :three:transparent mode 透明方式

![image-20211029103840985](https://raw.githubusercontent.com/yijunquan-afk/img-bed-1/main/img2/1695704179.png)

### DMA结构

DMA结构如下：

<img src="https://raw.githubusercontent.com/yijunquan-afk/img-bed-1/main/img2/1695704190.png" alt="image-20211029101346115" style="zoom:67%;" />

DMA不用解析地址，直接将地址信号传给后面的I/O设备，让其进行判断

### DMA操作

**预处理**：CPU通知DMA控制器

> 阅读/写作 
>
> 设备地址 
>
> 数据内存块的始地址 
>
> 要传输的数据量 
>
> CPU 继续开展其他工作

**数据传输**：DMA控制器处理传输（逐字传输）

**后处理**：DMA控制器在完成后**发送中断**

一块数据传输完以后才发送中断，不像中断驱动一个字终中断一次

![image-20211029103138623](https://raw.githubusercontent.com/yijunquan-afk/img-bed-1/main/img2/1695704180.png)

### DMA Transfer Cycle Stealing

前两种方式的缺点

> I/O传送速度受处理器测试和服务设备速度的限制。
>
> 处理器负责管理 I/O传送，对于每一次的 I/O传送，处理器必须执行很多指令

DMA的特点

> DMA控制器按周期掌管总线
>
> 传输一个字的数据
>
> 不是一个中断，CPU不切换上下文
>
> CPU在进入总线前暂停
>
> 会使CPU速度变慢

### DMA配置

![image-20211029104043056](https://raw.githubusercontent.com/yijunquan-afk/img-bed-1/main/img2/1695704182.png)

![image-20211029104104606](https://raw.githubusercontent.com/yijunquan-afk/img-bed-1/main/img2/1695704183.png)

![image-20211029104132284](https://raw.githubusercontent.com/yijunquan-afk/img-bed-1/main/img2/1695704184.png)

最常使用第三种，CPU只暂停一次

**DMA三种方式跟CPU传送数据**

:one: **块传送模式Block transfer mode (独占模式)**

> 整个数据块按一个连续的顺序传输
>
> 如果DMA传输数据，CPU将被禁用一段时间，直到DMA释放总线
>
> 用于将程序或数据文件装入内存
>

:two: **周期窃取模式§Cycle stealing mode**

> DMA仅在CPU不需要或强制CPU暂时暂停运行时才使用总线
>
> DMA传输一个字的数据，然后释放总线
>
> DMA交错指令和数据传输
>

:three: 透明模式(交替模式**alternate mode**)

> DMA和CPU采用分时复用的方式使用总线
>
> 需要的时间最多，**但效率最高**
>
> 适用于CPU处理时间比主存存取时间长的情况

### 题

![image-20211029104756694](https://raw.githubusercontent.com/yijunquan-afk/img-bed-1/main/img2/1695704185.png)

定一秒的时间区级

### 例题

假设一台计算机的CPU时钟频率为500Mhz，其CPI为5(意味着平均执行一条指令需要5个时钟周期)。中断模式下，I/O设备的数据传输速率为0.5 MB/s，传输单元为32位。中断服务程序包含18条指令，中断服务的其他开销相当于2条指令的执行时间。请回答:

1）在中断模式下，CPU用于I/O传输的时间与总CPU运行时间的比值是多少?

2）假设设备的数据传输速率为5MB/s，采用DMA模式传输5000B块的数据，DMA预处理和后处理的总成本为500个时钟周期。CPU用于I/O传输的时间占总CPU运行时间的比例是多少?(假设DMA和CPU之间没有内存访问冲突)。

> 解决方案:
>
> 中断模式
>
> > 在每个中断中，CPU用于I/O传输的时间= 5×18+5×2=100个时钟周期。
> >
> > **在一秒钟**,I/O设备传输0.5MB的数据和，需要中断时间= 0.5 MB / 32 = 0.5 MB / 4 B = 0.125 M。
> >
> > 中断成本=0.125M×100=12.5M时钟周期
> >
> > 所需比例= 12.5M/500M=2.5%
> >
>
> DMA模式
>
> > 在每个DMA中，CPU用于I/O传输的时间=500个时钟周期。
> >
> > 在一秒钟,I/O设备传输5MB的数据和，需要DMA处理时间=5MB/5000B=1K。
> >
> > DMA处理成本=1K×500=0.5M时钟周期 
> >
> > 所需比例= 0.5M /500M=0.1%

## 7.6 I/O通道

**I/O通道是拥有自己的处理器来执行I/O程序的I/O模块**

![image-20211226105141097](https://raw.githubusercontent.com/yijunquan-afk/img-bed-1/main/img2/1695704186.png)

I/O 程序位于主内存中 ，事实上，I/O 通道代表了 DMA 概念的扩展。 因此，I/O 通道能够执行 I/O 指令并控制 I/O 操作

### 通道功能

> 接收来自CPU的命令
>
> 从内存中加载I/O程序，将命令发送给设备
>
> 缓冲、控制和传输数据，为传输提供路径
>
> 报告设备状态，发送中断

### 通道类型

通道容量/数据传输速率：每个通道单位时间传输的最大数据

#### 选择通道

在任何时候，只选择一台设备来传输数据 ；适用于高速设备

#### 多路通道

字节多路通道

> 轮转
>
> 适用于低速设备
>
> 每次传输一个字节给一个设备
>
> **最终的通道容量要加起来算**

块多路通道

> 轮转
>
> 每次传输K个字节给一个设备
>
> **通道容量取决于最快的那个设备**

## 7.7  外部接口

点对点和多点配置

串行和并行

