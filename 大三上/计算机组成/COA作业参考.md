# COA作业参考答案

<font color="red">作业答案仅供参考，写这个是方便自己复习与查看，请勿直接抄袭</font>

[TOC]

## 第二章
![在这里插入图片描述](https://img-blog.csdnimg.cn/bddc2ec8ca51433fa54b8e95e7459572.png?x-oss-process=image/watermark,type_ZHJvaWRzYW5zZmFsbGJhY2s,shadow_50,text_Q1NETiBA6Zuo6JC95L-K5rOJ,size_20,color_FFFFFF,t_70,g_se,x_16)

> 答：:label: <mark>数据跟英文版的不一样，思路是一样的</mark>
>
> $CPI=\frac{45000\times 1+32000\times2+15000\times2+8000\times2}{100000}$
>
> $T=CPI\times I_c\times \frac{1}{f}$
>
> $MIPSrate=\frac{I_c}{T\times 10^6}=\frac{f}{CPI\times 10^6}$

![在这里插入图片描述](https://img-blog.csdnimg.cn/98c5257d5e244a0c8e5ba2a634c916ff.png?x-oss-process=image/watermark,type_ZHJvaWRzYW5zZmFsbGJhY2s,shadow_50,text_Q1NETiBA6Zuo6JC95L-K5rOJ,size_20,color_FFFFFF,t_70,g_se,x_16)

> 答：:label: <mark>数据跟英文版的不一样，思路是一样的</mark>
>
> (a)由于$MIPS\ rate=\frac{I_c}{T\times10^6}$，可以得到指令数$I_c=MIPS\ rate\times T\times10^6$
>
> 所以VAX与IBM机器代码指令条数的关系为$\frac{I_c(VAX)}{I_c(IBM)}=\frac{1\times 12x\times 10^6}{18\times x\times 10^6}=0.67$
>
> (b) $T=CPI\times I_c\times \tau$，所以$CPI=\frac{f}{MIPS rate\times 10^6}$
>
> $CPI(VAX)=\frac{5\times  10^6}{1\times 10^6}=5$
>
> $CPI(IBM)=\frac{25\times  10^6}{18\times 10^6}=2.78$



## 第三章

**1、什么是总线？总线传输有何特点？**

> 总线是连接两个或多个设备的通信通路
>
> 总线传输的特点是**某一时刻只允许有一个不见向总线发送消息，而多个部件可以同时从总线上接受相同的信息**



**2、什么是系统总线？它分为哪几类？各有什么作用？分别是单向的还是双向的？它们与机器字长、存储字长及存储单元数有何关系？**

> 系统总线是连接计算机主要部件（处理器、存储器、I/O)的总线按系统总线传输信息不同，可分为3类：数据总线、地址总线和控制总线。
> **数据总线**：数据总线是用来传输个功能部件之间的数据信息，它是双向传输总线，其位数与机器字长、存储字长有关，一般为 8位、16位或 32 位。
> **地址总线**：地址总线主要是用来指出数据总线上的源数据或目的数据在主存单元的地址或Ⅰ/O设备的地址，地址总线上的代码是
> 用来指明CPU欲访问的存储单元或I/O端口的地址,由CPU输出，是单向的，地址线的位数与存储单元的个数有关，如地址线有 20根，则对应的存储单元个数为 2^20。
> **控制总线**：控制总线是用来发出各种控制信号的传输线 ，其传输是单向的

**3、常见的集中式总线控制有几种？各有何特点？ 哪种方式响应时间最快？哪种方式对电路故障最敏感？**

> 常见的集中式总线控制有三种：链式查询、计数器定时查询、独立请求；
>
> 特点：
>
> :one: 链式查询方式连线简单，易于扩充，对电路故障最敏感；
>
> :two: 计数器定时查询方式优先级设置较灵活，对故障不敏感，连线及控制过程较复杂；
>
> :three: 独立请求方式速度最快，但硬件器件用量大，连线多，成本较高。

**4、常见的总线通信方式有哪些？各有什么特点？**

> 1、同步通信：由统一时标控制数据传送
> 2、异步通信：采用应答方式，没有公共始终标准
> 3、半同步通信：同步异步相结合
> 4、分离式通信：充分挖掘系统总线每个瞬间的潜力

**5、某同步总线的时钟频率为100MHz，地址/数据线复用，宽度为32位，每传输一个地址或者数据占用一个时钟周期。若该总线支持猝发（块）传输方式，块大小为16B，则一次“主存写”总线事务传输128位数据所需时间至少为多少？**

> 时钟周期t=10ns
>
> 数据传送次数：16x8/32=4
>
> 由于地址/数据复用，总共占用1+4=5个时钟周期
>
> 所需时间至少为T=5t=50ns



![在这里插入图片描述](https://img-blog.csdnimg.cn/4e41d92ef04f45ed9f4a3421bef6b536.png?x-oss-process=image/watermark,type_ZHJvaWRzYW5zZmFsbGJhY2s,shadow_50,text_Q1NETiBA6Zuo6JC95L-K5rOJ,size_20,color_FFFFFF,t_70,g_se,x_16)
![在这里插入图片描述](https://img-blog.csdnimg.cn/8c52436d027942c08dd208e04fc07573.png?x-oss-process=image/watermark,type_ZHJvaWRzYW5zZmFsbGJhY2s,shadow_50,text_Q1NETiBA6Zuo6JC95L-K5rOJ,size_20,color_FFFFFF,t_70,g_se,x_16)

## 
![在这里插入图片描述](https://img-blog.csdnimg.cn/fd70f3a6d940401a88ab544111ce920c.png)
![在这里插入图片描述](https://img-blog.csdnimg.cn/1ae050250caa4a6d80c337b2a61cd365.png?x-oss-process=image/watermark,type_ZHJvaWRzYW5zZmFsbGJhY2s,shadow_50,text_Q1NETiBA6Zuo6JC95L-K5rOJ,size_20,color_FFFFFF,t_70,g_se,x_16)

> 考虑一个具有图3-19的存储器读时序的微处理器。进行一些分析后，设计者确认存储器提供的读数据大约落后了180ns。
> （a）若总线时钟频率为8MHz，要进行恰当的系统操作，需要插人多少等待状态（时钟周期）？
> （b）为了实施等待状态，使用了Ready（就绪）状态线。一旦处理器发出一个Read 命令，它必须等待直到Ready线有效后才能试图读数据。要强迫处理器插人所要求的等待状态数，Ready线必须在什么时间间隔内保持为低（无效）？
>
> ![image-20211224225227521](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/qshell/image-20211224225227521.png)

![在这里插入图片描述](https://img-blog.csdnimg.cn/d7c14596785944be90db3c2319b7ea03.png?x-oss-process=image/watermark,type_ZHJvaWRzYW5zZmFsbGJhY2s,shadow_50,text_Q1NETiBA6Zuo6JC95L-K5rOJ,size_20,color_FFFFFF,t_70,g_se,x_16)
## 第四章
**4.1 一个组相联cache由64个行组成，每组4行。主存储器包含4K个块，每块128字，请表示主存地址的格式。**

> 答：
>
> cache的组数：64/4=16，需要4位标识
>
> 字地址长度：$log_2128=7$
>
> Tag地址长度：$log_2 4k-4=8$
>
> 表示格式如下：
>
> ![image-20211225163601041](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/qshell/image-20211225163601041.png)

**4.2 一个二路组相联 cache 具有8KB 容量，每行 16 字节。64MB 的主存是字节可寻址的。请给出主存地址格式。**

> 行数：8K/16=2^9
>
> 组地址长度=$log_2 (2^9 /2)=8$
>
> 块数：64MB/16B=2^22
>
> Tag地址长度：22-8=14
>
> 字地址长度：$log_2 16=4$
>
> 主存地址格式如下：
>
> ![image-20211225164214863](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/qshell/image-20211225164214863.png)



4.3

![在这里插入图片描述](https://img-blog.csdnimg.cn/6d56f6e71c8a48a59538188dcfe829fa.png)
![在这里插入图片描述](https://img-blog.csdnimg.cn/8132c1425d9f4c5495bd714dfd11d04f.png)
![在这里插入图片描述](https://img-blog.csdnimg.cn/85fce45ca1c340dc98641a7cd006c72b.png)![在这里插入图片描述](https://img-blog.csdnimg.cn/dfaf00901bc54ff9bf6c98adcbb3d794.png)


![在这里插入图片描述](https://img-blog.csdnimg.cn/76b949c105ac42d28139c08bae6d511f.png)

![在这里插入图片描述](https://img-blog.csdnimg.cn/3f403672cca943b39461bbb0e81646e1.png)

![在这里插入图片描述](https://img-blog.csdnimg.cn/26b70ac8bcae4aa79f2bf298364c7163.png?x-oss-process=image/watermark,type_ZHJvaWRzYW5zZmFsbGJhY2s,shadow_50,text_Q1NETiBA6Zuo6JC95L-K5rOJ,size_20,color_FFFFFF,t_70,g_se,x_16)

For the address sequence: 1 2 3 4 1 2 3 4 1 2 3 4, draw and compute the hit ratio of 3-line cache using FIFO & LRU; which methods can be used to improve the hit ratio?

> 对于地址序列:1 2 3 4 1 2 3 4 1 2 3 4 1 2 3 4，使用FIFO和LRU绘制并计算3行缓存的命中率;有哪些方法可以提高命中率?

![在这里插入图片描述](https://img-blog.csdnimg.cn/bd441e681ce445288fe89b9233b8c74e.png?x-oss-process=image/watermark,type_ZHJvaWRzYW5zZmFsbGJhY2s,shadow_50,text_Q1NETiBA6Zuo6JC95L-K5rOJ,size_20,color_FFFFFF,t_70,g_se,x_16)

> Consider a machine with Cache-main memory system structure. Its main memory has 8 blocks(0-7) which block size is 4 words, and its Cache has 4 lines(0-3) and adapts a organization of 2-way set associative with LRU replacement algorithm, require:
>
> \1) show the structure of main memory address
>
> \2) show the corresponding relationship of main memory block number and Cache line number
>
> \3) Supposed initial Cache status is empty, for the address sequence: 1，2，4，1，3，7，0，1，2，5，4，6，4，7，2，list the assigned addresses of cache lines after each visit.
>
> \4) Given the hit ratio of Cache after above steps. 
> ![在这里插入图片描述](https://img-blog.csdnimg.cn/6f344277db7a41bf9c43873d4068ca79.png?x-oss-process=image/watermark,type_ZHJvaWRzYW5zZmFsbGJhY2s,shadow_50,text_Q1NETiBA6Zuo6JC95L-K5rOJ,size_20,color_FFFFFF,t_70,g_se,x_16)
> ![在这里插入图片描述](https://img-blog.csdnimg.cn/e9b517e13bd94aa99ce056687d47882d.png?x-oss-process=image/watermark,type_ZHJvaWRzYW5zZmFsbGJhY2s,shadow_50,text_Q1NETiBA6Zuo6JC95L-K5rOJ,size_16,color_FFFFFF,t_70,g_se,x_16)
>
> **（3）**
>
> ![image-20211229131631073](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/qshell/image-20211229131631073.png)
>
> **(4) 命中率：2/15**

## 第五章

**1、Compare SRAM to DRAM.**

> **动态随机存储器Dynamic RAM **：通过电容充电来存储数据，一个半导体器件存储一位二进制，位元中的电容有、无电荷分别表示二进制的1、0.
>
> **特点**：电容器有漏电的趋势，需要周期性地充电刷新-->刷新
>
> **用途**：比较慢，适用于主存

> **静态随机存储器**Static RAM ：通过触发器存储数据，不需要刷新充电。每一位需要6位MOS，所以更复杂、更昂贵。
>
> **用途**：因为不用刷新，因此也更快，适用于cache

> **二者都不稳定**
>
> > 都需要持续充电去保存数据
>
> **动态**
>
> > 更密集、更便宜、需要刷新、适用于更大的存储单元
>
> **静态**
>
> > 更快，适用于缓存

**2、Which advanced methods can be used to improve accessing main memory?**

> SDRAM、Rambus DRAM、DDR DRAM

**3、有一个具有22位地址和32位字长的存储器模块。问：**

**1） 该存储器的存储容量为多少字节？**

**2） 如果有若干512Kⅹ16的SRAM芯片，那么由这样的芯片组成该存储器需要几片？**

**3） 画出由该芯片组成需要的存储器模块的连接示意图**。

> :one: 容量：$2^{22}\times \frac{32}{8}B=16MB$
>
> :two: 片数：$\frac{2^{22}\times32}{512k\times16}=16片$
>
> :three: 连接示意图如下: 先扩展字长、再扩展字数，利用三八译码器进行片选
>
> ![image-20211225202156261](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/qshell/image-20211225202156261.png)

**4、某CPU有16条地址线和8条数据线。从0000H到9FFF地址已有40KB内存，现在要求在40KB地址空间之后再增加8KB SRAM。如用4K×8位SRAM芯片来扩容，假设CPU有地址总线，数据总线，控制信号为R/W（读写允许）以及MREQ（当存储器进行读写操作时，该信号只是地址总线上的地址是有效的），SRAM有地址线，数据线，R/W和片选CS信号端，试设计CPU与该8KB SRAM的连接图，可选用如图所示的各种逻辑门及3-8译码器。（图见ppt）**

![image-20211225202235970](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/qshell/image-20211225202235970.png)

![image-20211225203207708](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/qshell/image-20211225203207708.png)

![image-20220221224838082](C:/Users/26969/AppData/Roaming/Typora/typora-user-images/image-20220221224838082.png)

![image-20220221224859664](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/qshell/image-20220221224859664.png)

## 第七章

### 第一题

A DMA module is transferring characters to memory using cycle stealing, from a device transmitting at 9600 bps. The processor is fetching instructions at the rate of 1 million instructions per second (1 MIPS). By how much will the processor be slowed down due to the DMA activity? (默认字长为1B)

![image-20211102212741732](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/qshell/image-20211102212741732.png)

 **解答**

> 由于处理器以 1MPS的速度获取指令，则总线周期应该为 1μs，而现在设备的传输率为 9600b/s， 采用周期窃取方式使用总线，每次窃取一个总线周期来处理一个字节数据，那在 1s 的时间内，需要窃取 9600/8 次总线周期才能满足传输要求，这就需要占以前 CPU 获取指令的周期。所以经过窃取后 CPU 速度=(10^6-9600/8)/s，速度将被减慢的比率=（以前速度－现在速度）/以前速度=[10^6-(10^6-9600/8)]/-10≈0.12%。
> 可见CPU基本没有被减慢。

### 第二题

A 32-bit computer has two selector channels and one multiplexor channel. Each selector channel supports two magnetic disk and two magnetic tape units. The multiplexor channel has two line printers, two card readers, and 10 VDT terminals connected to it. Assume the following transfer rates:

Disk drive      800KB/s

Magnetic tape drive 200KB/s

Line printer     6.6KB/s

Card reader     1.2KB/s

VDT         1KB/s

Estimate the maxium aggregate I/O transfer rate in this system.

![image-20211102212810682](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/qshell/image-20211102212810682.png)

**解答**

> 对于两个选择通道，任何时候只能选择一台设备传输数据，适用于高速设备，所以最多连接两个磁盘驱动器。而多路转换通道适用于低速设备。该系统的最大总I/O传输速率为800+800+6.6x2+1.2x2+1x10=1625.6 KB/s



### 第三题

Assume some I/O device send information to CPU with the maximum frequency of 4000 times per second, while the executing time of the corresponding interrupt handler routine is 40μs. Can this I/O device adopt Interrupt driven mode to work? And why?

**解答**

> 数据发送频率为4kHz
>
> 中断程序执行频率为1000000/40Hz=25kHz
>
> 中断程序执行频率比数据发送频率快，可以采用中断驱动方式工作。



### 第四题

Assume that a disk uses 32-bit word as the data transmission unit with transferring rate of 1MB/s, and CPU clock cycles is 50MHz. Please answer the following questions:

1. In programmed mode, suppose that it takes 100 clock cycles to finish required operation. Please calculate the ratio of time that CPU uses for I/O inquiring (assume that there is enough inquiring operation to avoid data loss).

2. In Interrupt driven mode, the time consumption (including handling interrupt) for each transferring process is 80 clock cycles. Please calculate the ratio of time that CPU takes for data transferring of disk.

3. In DMA mode, assume that it takes 1000 clock cycles to start DMA, and 500 clock cycles to post-process when the DMA finished. If the length of the average transmission data is 4 KB, how much is the ratio of time that CPU use to finish I/O operation when disk working？Ignore the bus-request time of DMA.

   ![image-20211102215933248](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/qshell/image-20211102215933248.png)

> > :one: 编程式I/O以字为单位处理，假设时钟周期为$\tau$
> >
> > 一秒内：能传输字的个数：  $\frac{1MB/s}{32b}=0.25M$
> >
> > 所耗费的I/O查询时间：$0.25M\times 100\tau=25M\tau$
> >
> > 总的时钟周期为$50M\tau$
> >
> > CPU 用于 I/O 查询的时间比例：$\frac{25M\tau}{50M\tau}=0.5$
> >
> > 
> >
> > :two: 中断式I/O以字为单位处理，假设时钟周期为$\tau$
> >
> > 一秒内：能传输字的个数：  $\frac{1MB/s}{32b}=0.25M$
> >
> > 所耗费的I/O查询时间：$0.25M\times 80\tau=20M\tau$
> >
> > 总的时钟周期为$50M\tau$
> >
> > CPU 用于 I/O 查询的时间比例：$\frac{20M\tau}{50M\tau}=0.4$
> >
> > 
> >
> > :three: DMA以块为单位处理，假设时钟周期为$\tau$
> >
> > 一秒内：能传输块的个数：  $\frac{1MB/s}{4kB}=2.5\times 10^{-4}M$
> >
> > 所耗DMA操作的时间：$2.5\times 10^{-4} \times(1000+500)\tau=0.375M\tau$
> >
> > 总的时钟周期为$50M\tau$
> >
> > CPU 用于 I/O 查询的时间比例：$\frac{0.375M\tau}{50M\tau}=0.0075$

## 第九章

**1. 将（20.59375）10转换成为754标准的32位浮点数二进制存储格式。**

解：

> :one: 将十进制转换为二进制数
>
> 整数部分：20转换为二进制为10100
>
> 小数部分：0.59375转换为二进制为10011
>
> 故20.59375转换为二进制为10100.10011
>
> :two: 移动小数点，使之处在1、2位之间
>
> $10100.10011=1.010010011\times2^4->e=4$
>
> 所以有于是得到：S=0, E=4+127=131=10000011, M=010010011
>
> :three: 得到32位浮点数的二进制存储格式为
>
> 0100 0001 1010 0100 1100 0000 0000 0000



**2. 若浮点数X的754的标准存储格式为（41360000）16，求其浮点数的十进制数值。**

解：

> $X=(41360000)_{16}=(0100\ 0001\ 0011\ 0110\ 0000\ 0000\ 0000\ 0000)_2$
>
> 所以S=0      e=E-127=1000 0010-0111 1111=3	M=011011
>
> 所以$X=1.011011\times 2^3=(1011.011)_2=(11.375)_{10}$



**3. 假定在一个8位字长的计算机中运行如下C语言程序段：**

```c
unsigned int x = 134;

unsigned int y = 246;

int m = x;

int n = y;

unsigned int z1 = x - y;

unsigned int z2 = x + y;

int k1 = m - n;

int k2 = m + n;
```

若编译器编译时将8个8位寄存器R1-R8分别分配给变量x,y,m,n,z1,z2,k1,k2. 请回答以下问题：

1）执行上述程序后，寄存器R1，R5和R6的内容分别是多少？

2）执行上述程序后，变量m和k1的值分别是多少？

3）上述程序段中，哪些带符号的整数运算会发生溢出？

 解：

> :one: R1对应的变量为x，即R1存储的内容$=x=(134)_{10}=(1000 0110)_2=(86)_{16}$
>
> R5对应的变量是z1，即R5存储的内容$=z_1=x-y=134-246=(-112)_{10}=(1001 0000)_2=(90)_{16}$，为负数，会溢出
>
> R6对应的变量是z2，即R6存储的内容$=z_2=x+y=134+246=(380)_{10}=(01111100)_2=(7C)_{16}$，大于255，会溢出
>
> 
>
> :two: m的原码为$(11111010)_2$，等于$(-122)_{10}$
>
> 同理，n的原码为$(10001010)_2$，等于$(-10)_{10}$，所以$k_1=-122-(-10)=(-112)_{10}$
>
> <mark>无符号数转换为有符号数，要注意第一位表示正负</mark>
>
> 
>
> :three: $k_2=m+n=-122-10=(-132)_{10}$，小于-127，所以会发生溢出

## 第十二章

**1.Both instructions and data are stored in the internal memory, then how CPU can distinguish them?**

> **答**：根据CPU所处的指令子周期区分指令和数据，即在取指令周期取出的为指令，在执行指令周期取出的即为数据。



**2.Suppose a pipeline with 5 stages: fetch instruction (FI), decode instruction (DI), execute (EX), memory assess (MA) and write back (WB).**

**1)Please draw the spatio-temporal diagram for a sequence of 12 instructions, in which there are no conflicts and no data dependencies.**

**2)Under this situation, what is throughput of this pipeline and the speedup of this pipeline?(Suppose the clock frequency is 100ns)**

> 2。假设一个有5个阶段的流水线:取指令(FI)、解码指令(DI)、执行指令(EX)、内存评估(MA)和回写(WB)
>
> 1)请为12个指令序列绘制时空图，其中没有冲突和数据依赖。

> **答：**<img src="https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/qshell/QQ%E5%9B%BE%E7%89%8720211118212054.png" style="zoom: 67%;" />

> 2)在这种情况下，该流水线的吞吐量和该流水线的加速是多少?

> **答：**吞吐量=$\frac{12}{(5+12-1)\times 10^{-7}}=7.5\times10^6\ 个/秒$
>
> ​		加速比=$\frac{12\times5}{5+12-1}=\frac{60}{16}=3.75$



**3.A pipelined processor has a clock rate of 2.5GHz and executes a program with 1.5 million instrucions. The pipeline has five stages, and instructions are issued at a rate of one per clock cycle. Ignore penalties due to branch instructions and out-of-sequence executions.**

**a What is the speedup of this processor for this program compared to a nonpipelined processor, making the same assumptions used in Section 14.4?**

**b What is throughput of the pipelined processor?**

> 流水线处理器的时钟速率为2.5GHz，并执行一个包含150万条指令的程序。流水线有五个阶段，指令以每个时钟周期一个的速率发出。忽略由于分支指令和乱序执行造成的损失

> a、与非流水线处理器相比，这个处理器对于这个程序的加速是多少?在第14.4节中使用了相同的假设

> **答：**加速比=$\frac{1.5\times10^6\times5}{5+1.5\times10^6-1}\approx5$

> b、流水线处理器的吞吐量是多少?

> **答：**由于指令数远大于流水线阶段数，所以吞吐量$\approx\frac{1}{\tau}=f=2.5G\  个/秒$

## 第十四章

**1.指令并行性主要受限于哪几个方面？**

> **答：**
>
> 主要受限于真实数据相关性（true data dependency）、过程相关性（procedural dependency）、资源冲突（resource conflict）、输出相关性（output dependency）、反相关性（antidependency）

**2.下列指令序列，各存在什么相关性，在超标量中是如何解决这些相关性的？**

  **I1: LOAD  R1, X**

  **I2: ADD  R2, R1**

  **I3: MUL  R2, R4**

  **I4: MOVE R4, R5**

> **答：**
>
> I1和I2存在真实数据相关性
>
> I2和I3存在输出相关性
>
> I3和I4存在反相关性
>
> **解决策略：**
>
> 对于真实数据相关性，可以使用乱序发射或插入不相关指令解决
>
> 对于输出相关性和反相关性，超标量可使用寄存器重命名解决

## 第十五章

**1、某计算机字长16位，采用16位定长指令字结构，部分数据通路结构如图所示。图中所有控制信号为1时表示有效、为0时表示无效，例如控制信号MDRinE为1表示允许数据从DB打入MDR，MDRin为1表示允许数据从总线打入MDR。假设MAR的输出一直处于使能状态。加法指令“ADD(R1), R0”的功能为(R0) + ((R1))  ->(R1)，即将R0中的数据与R1的内容所指主存单元的数据相加，并将结果送入R1的内容所指主存单元中保存。表1给出了上述指令取指和译码阶段每个节拍（时钟周期）的功能和有效控制信号，请按表1描述方式用表格列出指令执行阶段每个节拍的功能和有效控制信号。**

<img src="https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/qshell/image-20211202194433004.png" alt="image-20211202194433004" style="zoom:67%;" /><img src="https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/qshell/image-20211202194442685.png" alt="image-20211202194442685" style="zoom: 60%;" />

**答**：

| 时钟 | 功能                    | 有效控制信号     |
| ---- | ----------------------- | ---------------- |
| C5   | MAR$\leftarrow$(R1)     | R1out, MARin     |
| C6   | MDR$\leftarrow$M(MAR)   | MemR, MDRinE     |
| C7   | A$\leftarrow$(MDR)      | MDRout, Ain      |
| C8   | AC$\leftarrow$(A)+(R0)  | R0out, Add, ACin |
| C9   | MDR$\leftarrow$(AC)     | ACout, MDRin     |
| C10  | M(MAR)$\leftarrow$(MDR) | MDRoutE, MemW    |



**2、CPU结构如下图所示，其中有一个累加寄存器AC、一个状态条件寄存器和其他4个寄存器，各部件之间的连线表示数据通路，箭头表示信息传送方向。**

**（1）标明4个寄存器的名称。**

**（2）简述指令从主存取出送到CPU的数据通路。**

**（3）简述数据在CPU和主存之间进行存取访问的数据通路。**

<img src="https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/qshell/image-20211202194636448.png" alt="image-20211202194636448" style="zoom:50%;" />

**答**：

:one: 数据缓冲寄存器MDR	b:指令寄存器IR   c: 主存地址寄存器	d:程序计数器PC 

:two: PC→MAR→主存→MBR/MDR→IR

:three: 读数据：IR地址码部分X→MAR→主存→MBR/MDR→AC→ALU

​	  写数据：IR地址码部分Y→MAR,AC →MBR/MDR→主存

