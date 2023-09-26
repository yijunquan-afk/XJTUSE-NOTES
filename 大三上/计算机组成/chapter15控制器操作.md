[TOC]

# 第十五章 控制器操作Control Unit Operation

## **Key points**

> :one: Concept of micro operation
> :two: Model and organization of control unit
> :three: Implementing modes of CU
>
> **1、控制器的任务有什么，什么是微操作，划分的原则是什么，指令周期如何被划分成子周期，各个子周期涉及的微操作有哪些，ICC的作用是什么，控制器的模型，处理器内部总线结构中YZ寄存器的作用有什么**
>
> **2、微程序控制的基本思想是什么？硬连线控制器和微程序式控制器的优缺点**
>
> **3、硬连线式控制器的设计过程**

控制器负责向处理器外部发出控制信号，从而控制与存储器或I/0模块间的数据交换;控制器还需要向处理器内部发送控制信号，以在寄存器间移动数据，并引发ALU完成指定功能以及其他内部调整操作。控制器的输入包括指令寄存器、标志和来自外部的控制信号(例如中断信号等)。



## 15.1 微操作

**微操作micro-operation**：每个指令周期由若干子周期组成，每个子周期包含若干小的步骤，人们就把这些步骤称为微操作

微操作$uOP$是CPU基本的、原子的操作

![image-20211124114738784](https://raw.githubusercontent.com/yijunquan-afk/img-bed-1/main/img2/1695704541.png)



### 取指周期

**涉及的微操作**

> :one: 从PC中取出要执行指令的地址送到MAR
>
> :two: MAR将地址送到地址总线
>
> :three: 控制单元CU向内存发送读命令Read信号，启动内存读操作
>
> :four: 内存将读到的结果放到数据总线上，并复制到 MBR
>
> :five: 将MBR中的内容再传送给IR,MBR变得空闲，可继续为之后的数据存取服务
>
> :six: 同时 PC +x
>
> > x表示指令的长度
> >
> > 此操作可和从内存中读指令同步进行

**符号化表示**

> <img src="https://raw.githubusercontent.com/yijunquan-afk/img-bed-1/main/img2/1695704543.png" alt="image-20211227120136938" style="zoom:67%;" />

<img src="https://raw.githubusercontent.com/yijunquan-afk/img-bed-1/main/img2/1695704544.png" alt="image-20211227120158046" style="zoom:67%;" />

微操作的分组必须遵守下面两个简单的原则:

:one: **事件的流动顺序必须是恰当的**。于是，(MAR←(PC))必须先于(MBR←内存)，因为内存读操作要使用MAR中的地址。

:two: **必须避免冲突**。不要试图在一个时间单位里去读、写同-一个寄存器，否则结果是不可预料的。例如，(MBR←内存)和(IR←MBR)这两个微操作不应出现在同一时间单位里。

### 间接周期

取到指令以后，下一步就是取源操作数，若指令是间接寻址，则存在一个间接周期

> $t_1:   MAR ←(IR_{address}) - address\ field\ of\ IR$
>
> $t_2:   R ← 1(发读命令), MBR ← (memory)$ 
>
> $t_3:   IR_{address} ← MBR$

原本IR存的是间接地址，经过间接周期，存的就是直接地址

### 中断周期

```c
t1: MBR ← (PC)
t2: MAR ← 保存断点的内存地址
	PC ← 中断服务程序首地址
t3: W ← 1, memory ← (MBR) //MBR相当于常用的temp变量
```

:warning: 注意，保存各个通用寄存器的内容是由中断处理程序做的，不属于中断周期的微操作，中断周期结束，CPU就为执行中断服务程序中的指令做好了准备

### 执行周期

首先考虑ADD R1,X，它将存储器X的内容加到寄存器R1，该加法指令可能产生如下的微操作序列：

> $t_1:   MAR ←(IR_{address}) $
>
> $t_2:   R ← 1, MBR ← (memory)$ 
>
> $t_3:   R_1 ← R_1+(MBR)$

再来看更复杂的ISZ X(自增，如果结果为0则跳步),表示先对内存地址为X的内容加1，再将结果存入X地址，同时如果此结果为0，则跳过下一条指令，继续从第三条指令执行

> $t_1:   MAR ←(IR_{address}) $
>
> $t_2:   R ← 1, MBR ← (memory)$ 
>
> $t_3:   MBR ← 1+(MBR)$
>
> $t_4: W \leftarrow 1(发送写命令)，memory \leftarrow (MBR)$
> 		$if(MBR)==0\ then\ PC \leftarrow (PC)+I$

最后来看子程序调用指令，转移并保存地址指令BSA  X
把BSA指令后下一指令地址存入地址为X的内存地址，同时程序从X＋I处开始继续执行，用于**子程序调用**

> $t_1:   MAR ←(IR_{address}) $
> 		$MBR \leftarrow (PC)$
>
> $t_2:   W ← 1,  (memory) ← (MBR)$ 
> 		$PC \leftarrow (IR_{address})$
>
> $t_3:   PC ← (PC)+I$

指令开始时PC中的地址是下一条指令的地址，他被保存在IR指定的地址位置中，此后，PC中的地址也递增1，以提供下一指令周期的指令地址

### 指令周期

为区分CPU处于哪个周期，设置有2位的新寄存器，称为指令周期代码**Instruction Cycle Code ICC**
`00:取指	01：间接	10：执行	11：中断`

<img src="https://raw.githubusercontent.com/yijunquan-afk/img-bed-1/main/img2/1695704545.png" alt="image-20211126103832963" style="zoom:67%;" />

## 15.2 处理器控制

微操作可以有如下分类

> :one:在寄存器之间传送数据。
> :two:将数据由寄存器传送到外部接口( 如系统总线)。
> :three:将数据由外部接口传送到寄存器。
> :four:将寄存器作为输人、输出，完成算术或逻辑运算。

控制器主要完成两项基本任务

> 排序( sequencing):根据正被执行的程序，控制器使CPU以恰当的顺一步步通过一系列微操作。
>
> 执行(execution): 控制器使每个微操作得以完成。

控制器如何实现这些功能的关键是对**控制信号**的使用。

### 控制信号

对于控制器的输入输出

> 输入包括：时钟、各种标志falg、指令寄存器、来自控制总线的控制信号
>
> 输出是：CPU内的控制信号、到控制总线的控制信号

<img src="https://raw.githubusercontent.com/yijunquan-afk/img-bed-1/main/img2/1695704546.png" alt="image-20211126105359205" style="zoom:67%;" />

### 控制信号举例

<img src="https://raw.githubusercontent.com/yijunquan-afk/img-bed-1/main/img2/1695704547.png" alt="image-20211126105735679" style="zoom:67%;" />

<img src="https://raw.githubusercontent.com/yijunquan-afk/img-bed-1/main/img2/1695704549.png" alt="image-20211126111442423" style="zoom:67%;" />

### 处理器内部的组织

使用一个CPU的**内部总线**，ALU和所有的CPU寄存器都连接到单一的内部总线上<img src="https://raw.githubusercontent.com/yijunquan-afk/img-bed-1/main/img2/1695704550.png" alt="image-20211126112510825" style="zoom: 60%; float: right;" />

新增了两个寄存器，**分别标记为Y和Z，两个操作数来的时候，将其中一个通过Y进行暂存。**

ALU 是一个组合逻辑电路，其内部无存储电路。这样，当控制信号激活ALU的某个功能时，ALU输人通过ALU的组合逻辑电路被转换为ALU的输出。因此，ALU的输出不能直接连到内部总线上，因为这个输出会又反馈为输入。为此，**寄存器z提供了这个输出的暂时存储**

将存储器的值加到AC的操作有如下步骤：![image-20211126113440247](https://raw.githubusercontent.com/yijunquan-afk/img-bed-1/main/img2/1695704542.png)

## 15.3 硬连线实现Hardwired Implementation

控制器的实现方法可分为两类：硬连线式实现和微程序式实现

硬连线实现中，控制器必须是组合电路

### 控制器输入

**标志与控制总线信号**：每一位都代表特定意义，比如溢出，中断请求

**指令寄存器**：不同指令的指令码引起控制器发送不同的控制信号，从而完成相应的动作；应该使每一组01组合的操作码产生唯一的逻辑输出

**时钟**：

> 重复的脉冲序列
>
> 用来计量微操作的持续时间
>
> 时钟脉冲周期要足够长以满足信号传递的需求
>
> 控制器在一个指令周期里是以不同的时间周期发送不同的控制信号
>
> 等一个指令周期结束的时候，控制器必须返回给定时器一个初值以便使它重新计数

### 控制器逻辑

对每个输出信号都求出它关于输入信号的函数

这个函数可以解释为布尔表达式

布尔表达式通过组合电路来实现

硬件电路实现控制器比较复杂

微程序设计方式更为简单

### 优缺点

**优点**

> 具有更高的执行速度
>
> 适用于指令集规模比较小的系统的实现
>
> 适合RISC机CU设计的要求

**确定**

> 线路结构庞杂且不规整
>
> 指令系统越复杂，微操作命令就越多，线路就越复杂，调试也就越困难
>
> 添加新的指令很困难
