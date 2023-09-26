# 第十章 指令集：特征与功能

## KEY POINTS

> **1、指令集设计需要考虑的重要方面有哪些、**
>
> **2、操作的类型、典型的数据传输、算术逻辑转换、I/O系统控制、控制传递都要了解，不需要知道具体指令，要清楚各个类能实现什么功能，主要给谁使用**
>
> **3、MMX指令，考的简单**

## 10.1 机器指令特征

CPU的操作由它所执行的指令确定，这些指令称为机器指令machine instruction

CPU能执行的各种不同指令的集合称为CPU的指令集instruction set

为了便利，汇编人员会使用汇编码与机器码对应

### 10.1.1 机器指令要素

**操作码**operation code：将要完成的操作

**源操作数引用**source operand reference：操作所需的输入

**结果操作数引用**result operand reference：操作产生的结果

**下一条指令引用**next instruction reference：执行当前指令以后去取下一条

<img src="https://raw.githubusercontent.com/yijunquan-afk/img-bed-1/main/img2/1695704491.png" alt="image-20211105103150013" style="zoom:67%;" />

源操作数和结果操作数可能位于的范围

> :o:主存或虚拟内存
>
> :o:处理器寄存器
>
> > 如果只有一个寄存器，引用可以是隐式的；若有多个，需要指定唯一的的名字或编号
>
> :o:I/O设备
>
> > 如果使用存储器映射I/O方式，形式上将会是另一个主存或虚存

### 10.1.2 指令表示

对于机器码，每一条指令有一个唯一的位串，格式如下：

<img src="https://raw.githubusercontent.com/yijunquan-afk/img-bed-1/main/img2/1695704493.png" alt="image-20211105103825116" style="zoom:80%;" />

为便于使用，普遍使用机器指令符号表示法

操作码可以用助记符表示，如`ADD：加	SUB：乘`	操作数也可以用符号表示，如`ADD R,Y`

### 10.1.3 指令类型

指令类型分为：

> :o: **数据处理**：算术和逻辑指令
>
> **:o: 数据存储**：存储器指令
>
> :o: **数据传送**：I/O指令
>
> :o: **控制**：测试和分支指令

### 10.1.4 地址数目

**4地址指令**：现实中没有应用

#### 3地址指令

> 两个源操作数地址，一个运算结果地址
>
> 下一条指令被隐藏

![23](https://raw.githubusercontent.com/yijunquan-afk/img-bed-1/main/img2/1695704484.png)

#### 2地址指令

![21](https://raw.githubusercontent.com/yijunquan-afk/img-bed-1/main/img2/1695704486.png)

> 一个地址重用为源操作数地址和目标操作数地址
>
> 地址空间扩大了
>
> :label: <mark>二地址指令有两个操作数，这些操作数并不一定都在主存中，往往有一个在通用寄存器中</mark>

#### 1地址指令

![21](https://raw.githubusercontent.com/yijunquan-afk/img-bed-1/main/img2/1695704487.png)

> :one: 只有目的操作数的单操作数指令，从A1读取操作数，进行OP操作后，返回A1。操作码字段通常为加一、减一、求反、求补
>
> :two: 隐含约定目的地址的双操作数指令，按指令地址A1可读取源操作数，指令可隐含地约定另一个操作数由累加器ACC提供，运算结果也放在ACC中

#### 0地址指令

![image-20211105111548714](https://raw.githubusercontent.com/yijunquan-afk/img-bed-1/main/img2/1695704488.png)

零地址指令只给出操作码字段，主要包含以下两种情况

> :one: 不需要操作数的指令，如空操作指令、停机指令、关中断指令等
>
> :two: 堆栈计算机中的零地址运算类指令。堆栈计算机中参与运算的两个操作数隐含地从栈顶和次栈顶弹出，送到运算器进行运算，运算的结果再隐含地压入堆栈中

现代的计算机通常使用2~3地址指令

### 10.1.5 指令集设计

对于不同的应用，对应着不同的操作，自然也就有不同的指令集；也要考虑数据类型、指令格式、寄存器，设计考虑的重要方面如下：

> **操作指令表（operation repertoire）**：应提供多少和什么样的操作，操作将是何等复杂。
> **数据类型（data type）**：对哪几种数据类型完成操作。
> **指令格式（instruction format）**：指令的（位）长度、地址数目、各个字段的大小等。
> **寄存器（register）**：能被指令访问的处理器寄存器数目以及它们的用途。
> **寻址（addressing）**：指定操作数地址的产生方式。

## 10.2 操作数类型

机器指令对数据进行操作，数据通常分为：**地址、数值、字符、逻辑数据**

## 10.3 操作类型

### 数据传送Data Transfer

数据传送指令必须指明

> 1、源和目的的操作数位置必须指明
>
> 2、必须指明将要传送数据的长度
>
> 3、必须为每个操作数指明寻址方式

> Move
>
> Store
>
> Load
>
> Exchange:swap contents of source and destination
>
> Clear : all 0
>
> Set:   all 1
>
> Push/pop

### 算术运算Arithmetic

加减乘除，也可能有各种单操作数指令：Absolute、Negate

### 逻辑运算Logical

进行位控制

逻辑移位与逻辑移位以及循环移位

![image-20211105113749610](https://raw.githubusercontent.com/yijunquan-afk/img-bed-1/main/img2/1695704489.png)



### 转换Conversion

转换数据格式

可能是独立的指令（分离式I/O)

### 输入/输出

输入/读 IN(51)

输出/写 OUT(51)

启动 I/O

测试 I/O

可能通过数据传送指令完成 (存储映射式I/O)

可能通过CPU执行，也可能被分离的控制器 (DMA或通道)来完成

### 系统控制System Control

通常是特权指令

### 控制转移Transfer of Control

**过程调用指令**Procedure Call Instruction

> 属于跳跃指令
>
> 可实现代码重用和任务分解

<img src="https://raw.githubusercontent.com/yijunquan-afk/img-bed-1/main/img2/1695704494.png" alt="image-20211105114528397" style="zoom:50%;" />

过程调用是用户可控的，中断是系统决定的

地址返回方式：寄存器、子程序的首地址、**栈顶**

<img src="https://raw.githubusercontent.com/yijunquan-afk/img-bed-1/main/img2/1695704495.png" alt="image-20211105115211075" style="zoom:67%;" />

### 10.4 MMX

多媒体扩展——多媒体应用的一组高度优化的指令集

在单个时钟周期 （SIMD） 中同时对多个数据元素执行相同的操作，对于适当的应用，这些并行操作可以产生 2~8 倍的速度

![image-20211105115739231](https://raw.githubusercontent.com/yijunquan-afk/img-bed-1/main/img2/1695704490.png)

