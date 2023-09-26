[TOC]

# 第五章 内部存储器

## keypoints

> **1、动态RAM和静态RAM的特点与用途**
>
> **2、ROM的分类、各自的区别、特别注意现在应用最广的FLASH闪存**
>
> **3、模块组织、字长扩展、字数扩展、字长字数扩展<mark>计算</mark>**
>
> **4、纠错不考**
>
> **5、高级DRAM组织中要掌握的就是SDRAM和DDR SDRAM**

## 5.1 半导体主存储器

### 5.1.1 组织

所有的半导体存储位元都有相似的性质：

> 呈现两种稳态(或半稳态),分别代表二进制的1和0;
> 能够写入信息(至少一次)来设置状态;
> 能够读出状态信息。

<img src="https://raw.githubusercontent.com/yijunquan-afk/img-bed-1/main/img2/1695704145.png" alt="image-20211102142126381" style="zoom:67%;" />

![image-20211102142641622](https://raw.githubusercontent.com/yijunquan-afk/img-bed-1/main/img2/1695704134.png)

### 5.1.2 DRAM与SRAM

随机存取：通过编排的寻址逻辑，存储器的单个字直接被存取

**RAM**:随机存取存储器，可以方便的从存储器读取数据和向存储器写入新数据，且读写操作都是通过使用电信号来完成的。同时具有易失性，一旦断电，数据就会丢失。

#### DRAM

**动态随机存储器**：通过电容充电来存储数据，一个半导体器件存储一位二进制，位元中的电容有、无电荷分别表示二进制的1、0.

因为电容会漏电的趋势，因此DRAM需要周期性充电刷新，甚至在有电的时候也要充 

对于下图，当有电压施加到地址线上，晶体管导通；否则晶体管开路

<img src="https://raw.githubusercontent.com/yijunquan-afk/img-bed-1/main/img2/1695704146.png" alt="image-20211225174203136" style="zoom:67%;" />

##### 写操作

> 电压信号施加到位线上：高电压为1，低电压为0；然后一个信号施加到地址线，允许电荷传输到电容器

##### 读操作

> 当地址线被选中，晶体管导通，存储在电容上的电荷被送出到位线和读出放大器。读出放大器将此电容电压与一个参考值比较，确定位元保存的是1还是0，位元读出放掉了电容上的电荷，需要重新存储才算结束。

比较慢，适用于主存

#### SDRAM

**静态随机存储器**：通过触发器存储数据，不需要刷新充电。每一位需要6位MOS，所以更复杂、更昂贵。

因为不用刷新，因此也更快，适用于cache

<img src="https://raw.githubusercontent.com/yijunquan-afk/img-bed-1/main/img2/1695704147.png" alt="image-20211225174218876" style="zoom:67%;" />



#### 二者比较

> 逻辑1：C1高电位，C2低电位，T1,T4关闭，T2,T3打开
>
> 逻辑0：C2低电位，C1高电位，T2,T3打开，T1,T4关闭

##### 读写操作

> 写操作：位值施加到B线，位值的反施加到B非线
>
> 读操作：位值从B线读取

#### SRAM与DRAM比较

**二者都不稳定**

> 都需要持续充电去保存数据

**动态**

> 更密集、更便宜、需要刷新、适用于更大的存储单元

**静态**

> 更快，适用于缓存

### 5.1.3 ROM类型

ROM，即只读存储器，含有不能改变的永久性数据，掉电以后数据还在。

有一个MOS管代表"1"，没有MOS管代表"0"。

存在两个问题

> 固化数据需要较大的固化成本，不管是一片还是上千片
>
> 无出错机会，一位出错，整批销毁

#### PROM

即**可编程ROM**，需要用特殊设备来完成写("编程")，灵活性高，但是价格也高

#### EPROM

**光可擦除只读存储器**。写入操作前，需要让芯片暴露在紫外线辐照下使所有的存储位元都被擦除，还原为初始状态，可重复进行，需要20分钟以上。

可以修改多次，也能长久保存出局，更贵，集成度较低

#### EEPROM

**电可擦除只读存储器**。只需要修改想要改的部分，不用全部都初始化。写操作比读操作长的多，而且**更贵**

#### 快闪存储器flash memory

使用电擦除技术，价格与功能介于EPROM与EEPROM之间

能够擦除存储器中的某些块，而不是整块芯片

包括NOR与NAND，NAND内存更适合外部存储器，如USB闪存驱动器、存储卡，以及所谓的固态磁盘(SSD)

![image-20211015105816853](https://raw.githubusercontent.com/yijunquan-afk/img-bed-1/main/img2/1695704136.png)

### 5.1.4 芯片逻辑

半导体存储器也是封装的芯片，每块芯片包含一组存储位元阵列

![image-20211015112343148](https://raw.githubusercontent.com/yijunquan-afk/img-bed-1/main/img2/1695704137.png)

### 5.1.5 芯片封装

<img src="https://raw.githubusercontent.com/yijunquan-afk/img-bed-1/main/img2/1695704148.png" alt="image-20211102144910639" style="zoom:67%;" />

### 5.1.6 模块组织

![image-20211015113208887](https://raw.githubusercontent.com/yijunquan-afk/img-bed-1/main/img2/1695704138.png)

#### 存储器扩充（考研点)

:one: <font color="red">1kx4 -> 1kx8   字长扩展</font>

![image-20211015114337344](https://raw.githubusercontent.com/yijunquan-afk/img-bed-1/main/img2/1695704139.png)

CS：选中信号端	A0~An：地址线	D0~Dn：数据线	

图中的芯片进行了扩展，地址线不变，都是10位，数据线进行了扩展，由4位扩展成了8位

:two: <font color="red">1kx8-> 2kx8  字数扩展</font>

![image-20211015114413227](https://raw.githubusercontent.com/yijunquan-afk/img-bed-1/main/img2/1695704141.png)

高位做片选比较方便

:three: <font color="red">1kx4-> 4kx8</font>

先1kx4->1kx8再1kx8->4kx8

![image-20211015114901716](https://raw.githubusercontent.com/yijunquan-afk/img-bed-1/main/img2/1695704143.png)

2-4译码器保证了每一次只有一个芯片有效

##### 存储器与内存的连接

解题过程

> :one: **正确选择芯片类型和编号** 
>
> > ROM用于系统区域
> >
> > RAM用于用户区域
>
> :two: **地址线连接** 
>
> > CPU 的地址行通常比内存的多 
> >
> > 低位 = 低位 
> >
> > 用于 CS 的高位 
>
> :three: **CPU 数据线连接** 
>
> > CPU 的数据线必须等于内存的数据位，如有必要，芯片位将放大 
>
> :four: **连接命令线** 
>
> > 读/写行与内存行直接连接 CS 与 MREQ 和 CPU 的高地址位相连 可使用逻辑电路，如解码器 
>

##### CPU-存储器举例

> Suppose CPU has 16 address lines, 8 data lines. MREQ is used for access memory control, WR is read/write cont
>
> •1Kx4 RAM;  4K x8 RAM;  8K x8 RAM; 
>
> •2K x8 ROM; 4K x8 ROM; 8K x8 ROM;
>
> •74LS138 decoder and all kinds of gates, as figure.
>
> •Please draw the diagram of CPU connecting memory, conditions:
>
> 1. 6000H~67FFH is system area; 6800H~6BFFH is user area;
>
> 2. select reasonable chips, how many chips used, respectively？
>
> rol. Now, we have following chips:
>
> <img src="https://raw.githubusercontent.com/yijunquan-afk/img-bed-1/main/img2/1695704149.png" alt="image-20211102150030853" style="zoom:50%;" />

<img src="https://raw.githubusercontent.com/yijunquan-afk/img-bed-1/main/img2/1695704151.png" alt="image-20211102150425367" style="zoom:50%;" />

<img src="https://raw.githubusercontent.com/yijunquan-afk/img-bed-1/main/img2/1695704152.png" alt="image-20211102150446877" style="zoom: 50%;" />

<img src="https://raw.githubusercontent.com/yijunquan-afk/img-bed-1/main/img2/1695704153.png" alt="image-20211020102942584" style="zoom:67%;" />



## 5.2 纠错

半导体存储系统会出差错

硬故障hard failure：永久性的物理故障

软差错soft error：随机非破坏性事件，可以由电源问题或者$\alpha$粒子引起——可以通过海明码解决，检查2位纠正1位

## 5.3 高级DRAM组织

### 5.3.1 同步DRAM(SDARM)

传统的DRAM是异步的，SDRAM通过外部的时钟信号实现同步

由于 SDRAM 会及时与系统时钟移动数据，CPU 知道数据何时可以准备就绪 CPU不必等待，由于latch(插销)它可以做别的事情，

猝发方式(burst mode)允许SDRAM设置数据流并将其以块方式激发

### 5.3.2 Rambus DRAM

使用异步的面向块的协议来传送地址信息和控制信息

成本较高

### 5.3.3 DDR DRAM

SDRAM受限于每个时钟周期仅能发生送一次数据到CPU。双速率SDRAM，能每个时钟周期发送两次数据



参考博文：https://blog.csdn.net/qq_46354489/article/details/108903497