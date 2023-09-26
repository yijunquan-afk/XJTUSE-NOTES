[TOC]

# 第二章 计算机演变与性能

嵌入式计算机的概念和应用

指标

<mark>单位要记住</mark>

<img src="https://raw.githubusercontent.com/yijunquan-afk/img-bed-1/main/img13/image-20211001221924587.png" alt="image-20211001221924587" style="zoom:67%;" />

## 2.1 A Brief History of Computer

### (1) 计算机发展历史

第一代计算机：1946——1957年 电子管时代

> ENIQC、UNIVAC、IBM 701

第二代计算机：1958——1964年 晶体管时代

> IBM 7000系列  DEC ：PDP-1

第三代计算机：1965——1971年 中小规模集成电路时代

> IBM System/360 and DEC PDP-8

第四代计算机：1972年至今 大规模与超大规模集成电路时代（VLSI)

第五代计算机：2009年至今（有争议，了解即可）

> 世界上第一台通用电子计算机——**ENICA** 宾夕法尼亚大学——真空管计算机
>
> DEC公司研发的第一步计算机——**PDP-1**——晶体管
>
> 集成电路的出现，意味着**微电子时代**的到来
>
> 第一台小型机——|**DEC PDP-8 (1965)**——集成电路
>
> :star: 第一个在单个芯片上包含CPU所有组件的芯片——**1971 Intel developed 4004**——微处理器的诞生
>
> 第一个8位微处理器——1972 Intel developed 8008
>
> •First general purpose microprocessor——1974 Intel developed 8080

### (2) IAS(重点)

<mark>第一台计算机</mark>：ENIAC 1946年在宾夕法尼亚大学完成

<mark>冯诺依曼机(图灵机/IAS)</mark>：包括控制器、存储器、运算器、输入输出设备

> 存储程序的概念
>
> 存储程序和数据的主存储器
>
> 运算二进制数据的ALU
>
> 控制单元从存储器中解释指令并执行它们
>
> 由控制单元操作的输入和输出设备

<img src="https://raw.githubusercontent.com/yijunquan-afk/img-bed-1/main/img13/image-20211224160249716.png" alt="image-20211224160249716" style="zoom:50%;" />

#### 1）IAS存储格式

The memory of the IAS consists of 1000 storage locations (called **words**字) of 40 bits each 包括数据与指令

![image-20211001213500950](https://raw.githubusercontent.com/yijunquan-afk/img-bed-1/main/img13/image-20211001213500950.png)

#### 2）IAS寄存器

:one: Memory buffer register (MBR) **存储缓冲寄存器**

> 包含一个要存储在存储器或发送到I/O单元的字或用于从存储器或I/O单元接收一个字
>

:two: Memory address register (MAR) **存储地址寄存器**

> 指定要从MBR中写入或读入的字在内存中的位置

:three: Instruction register (IR) **指令寄存器**

> 包含正在执行的8位操作码指令

:four: Instruction buffer register (IBR) **指令缓冲区寄存器**

> 现代计算机没有左右指令之分，不用该寄存器

:five:<mark> Program counter (PC) **程序计数器**</mark>

> 指令的执行永远从它开始，包含要从内存中提取的下一个指令的地址

:six: Accumulator (AC) and multiplier quotient (MQ) **累加器(AC)和乘数商(MQ)**
<img src="https://raw.githubusercontent.com/yijunquan-afk/img-bed-1/main/img13/image-20211001214537199.png" alt="image-20211001214537199" style="zoom:50%;" />

IAS让程序执行的过程：PC把下一条要执行的指令的地址放在MAR->MAR通知主存->主存把地址放入MBR暂存->MBR先把左指令分为操作码和地址码两部分->操作码放给IR，地址码放给MBR->然后是右指令->IR译码以后产生跟指令相关的控制信号，要计算的话给ALU进行计算，同时操作数的地址驱动主存取数据->然后将结果暂存在MBR->主存从MBR取结果储存->程序执行结束，PC+1

#### 3）IAS指令集

21个指令：数据传输、无条件转、条件分支、计算、地址修改

### (3) Moore‘s Law

集成电路上可以容纳的晶体管数目在大约每经过18个月便会增加一倍。

> 芯片的成本几乎保持不变
>
> 更高的堆积密度意味着更短的电路径，从而提供更高的性能
>
> 更小的尺寸增加了灵活性
>
> 减少电力和冷却需求
>
> 互连越少，可靠性越高

## 2.2 Designing for Performance

### (1) Microprocessor speed

除非以计算机指令的形式不断向他提供持续的工作流，否则微处理器将达不到它的潜在速度，任何阻碍工作流的事件都会降低处理器的功能

CPU/内存容量遵循摩尔定律

满足CPU速度的技术：**分支预测、数据流分析、推测执行**

**困境**：其他关键部件尤其是主内存速度慢于CPU速度、CPU需要等待从而陷入瓶颈，降低整体性能

### (2) Performance Balance

DRAM：动态随机存取存储器

DRAM速度的增长比处理器速度增长的慢——不平衡

**解决办法**：

:one: 优化系统结构，平衡CPU、内存、I/O整体性能

:two: 改进CPU和内存之间的接口

- 接口是传输指令和数据的关键路径
- 增加一次接受的比特数：让DRAM“更宽”而不是“更深”
- 改变DRAM接口：使用缓存(集成度低)
- 减少内存访问频率：更复杂的缓存和芯片上的缓存
- 增加互连带宽：高速总线

:three: 缓存和缓冲方案

:four: 高速互连总线和更精细的互连结构

:five: 使用多处理器配置可以帮助满足I/O需求

### (3) Improvements in Chip Organization and Architecture

:one: 提高处理器的硬件速度

- 基本上是由于逻辑门尺寸的缩小


:two: 增加缓存的大小和速度

:three: 改善处理器组织和架构

时钟频率和芯片密度的提升也会受到一些阻碍：

:one: 功耗：功率密度随逻辑密度和时钟速度而增加,散热成为一大问题

:two: RC 延迟:：电子流动的速度受到连接它们的金属线的电阻和电容的限制

:three: 内存速度滞后于处理器速度

## 2.3 Multicore, MICs and GPGPUs(重点)

**Multicore**：即多核，在一个芯片上使用多个处理器可在不提高时钟速率的情况下提高性能

**MIC**：即集成众核，MIC 是联合处理器的软件架构，使用的core的数量多达几十个，多核通常只有几个core

**GPU**：Graphics Processing Unit，即图形处理单元，是一种专门在各种设备上做图像和图形相关运算工作的微处理器，常用于深度学习

## 2.4 Embedded System and the ARM(概念)

**嵌入式系统**：执行专用功能的系统，是计算机硬件与软件的结合，可能还有额外的机械或其他部件

**ARM**：ARM处理器是英国Acorn有限公司设计的低功耗成本的第一款RISC微处理器。全称为Advanced RISC Machine。ARM处理器的三大特点是：耗电少功能强、16位/32位双[指令集](https://baike.baidu.com/item/指令集)和合作伙伴众多。

## 2.5 Performance Assessment(重点)

### (1) 性能评估计算

**时钟频率**：处理器执行的操作由系统时钟控制，处理器的速度由系统时钟的脉冲频率决定，以每秒周期(Hz)为时钟频率

**处理器时间**T：处理器执行给定程序所需的时间

:red_circle: $T= 程序的CPU时钟周期*时钟周期τ=程序的CPU时钟周期/时钟速率$

:red_circle: $T= CPI ×I_C ×τ = CPI ×I_c /时钟速率$

$CPI$: 每条指令的平均周期	$I_c$: 指令数

受指令集体系结构、编译技术、处理器实现和内存层次结构的影响

**处理器速度**：指令执行的速度，表示为每秒数百万条指令(MIPS)，称为MIPS速率
$$
MIPS\ rate = \frac{I_c}{T\times10^6}=\frac{f}{CPI\times10^6}
$$
每秒数百万个浮点指令(MFLOPS)

![image-20211006200128178](https://raw.githubusercontent.com/yijunquan-afk/img-bed-1/main/img13/image-20211006200128178.png)

### (2) 基准测试程序

**Benchmarks**：用高级语言定义的一组程序，在一个特定的应用程序或系统编程领域中，尝试提供一个计算机的代表性测试

**特征**：

> - 用高级语言编写，使其可移植到不同的机器
>
> - 代表一种特殊编程风格，如系统编程、数字编程或商业编程
>
> - 测量容易
>
> - 分布广泛



**SPEC**：一个行业协会，定义和维护最知名的基准测试套件集合

性能测量被广泛用于比较和研究目的

**SPEC CPU 2006** : 最著名的SPEC基准套件,用于处理器密集型应用程序而不是I/O的行业标准套件

### (3) Amdahl定律计算(重点)

> 对系统某部分加速时，其对系统整体影响取决于该部分重要性和加速程度。
>
> 要想显著加速整个系统，必须提升全系统中相当大的部分的速度。

$$
设变量\ \ F_e=\frac{能改进的部分}{整体}\le1 \qquad  \  S_e=\frac{改进前的时间}{改进后的时间}\ge1\qquad\qquad
$$

$再设\   T_0:任务在性能提升前的执行时间$

改进后整个任务的执行时间为：
$$
T_n = T_0(1-F_e+\frac{F_e}{S_e})
$$
加速比为：
$$
S_n=\frac{T_0}{T_n}=\frac{1}{(1-F_e)+\frac{F_e}{S_e}}
$$
$F_e$对系统性能提升的限制作用很强

**举例**：假设一个任务大量使用浮点运算，40%的时间被浮点运算占用。在新的硬件设计下，浮点模块的速度提高了k倍。这种改进带来的总体加速是多少?

解: $F_e=0.4,S_e=K$，根据Amdahl定律:$S_n=\frac{T_0}{T_n}=\frac{1}{(1-F_e)+\frac{F_e}{S_e}}=\frac{1}{0.6+0.4/K}$

当$S_e= ∞, Sn=1.67 $，所以总体加速最多为1.67
