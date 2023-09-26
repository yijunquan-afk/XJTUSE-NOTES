[TOC]

# 第一章 计算机组成与结构简介

## 重点

**1、计算机的基本功能有哪些?**

**2、通用计算机的主要组成部分是什么？**

**3、CPU包括哪几个组成部分**

**4、摩尔定律的内容和意义**

## 1.1 Organization and Architecture

### (1) 计算机架构、组成、实现的概念

:one: **Computer architecture**: attributes visible to a programmer, These attributes have a direct impact on the logical execution of a program. 

> **计算机体系结构**：是那些对程序员可见的系统属性，这些属性直接影响到程序的逻辑执行

:two: **Computer Organization**: The operational units (components) and their interconnection that realize the architectural specifications. 

> **计算机组成**：实现了某种架构的操作单元以及操作单元的内部连接。<font color="red">组成是架构的一种实现，对系统设计员可见</font>

总而言之，计算机架构是计算机的逻辑设计，组成则是这种逻辑设计的具体实现。

:three: **Computer Implementations**: The hardware out of which we make computer systems. 具体的**物理实现**，透明性强

对于已存在的事物或属性，从某种角度看，它们被看作是不存在的，这个概念叫做**透明度**

### (2) 具体的属性

**Architectural attributes**: instruction set(指令集)、word length(字长)、I/O mechanism(I/O机制)、addressing(地址)

**Organizational attributes**: control signal、interface、memory technology、bus technology等等对程序员透明的硬件细节

**Implement attributes**: Integrated Circuits (ICs), Printed Circuits (PC) boards, Power Supplies, Chassis, Connectors and Cables，etc. 集成电路，印刷电路(PC)板，电源，机箱，连接器和电缆

### (3) 系列机

**Series Computers**: computers with the same architecture and different organization. 架构不变，组成不同。

举例：IBM System/370 architecture was introduced in 1970 and has survived to this day as the architecture of IBM’s mainframe product line.

**Upward compatibility:** program for low level computer can run over high level computer without modification

>  向上兼容性:低电平计算机的程序可以在高电平计算机上运行而不需要修改

**Backward compatibility:** program for current computer can run over future computer without modification 

> 向后兼容性:当前计算机的程序可以在未来的计算机上运行而不需要修改

## 1.2 Structure and Function

**Structure** : the way in which components relate to each other in certain level 

> 组件在一定程度上相互关联的方式

**Function** : the operation of individual components as part of the structure

>  作为结构一部分的单个组件的操作

### (1) function

<mark>计算机功能</mark>包括：**Data processing(数据处理)、Data storage(数据存储)、Data movement(数据传输)、Control(控制)**

计算机可能的一些操作如下：

![image-20211001184056426](https://raw.githubusercontent.com/yijunquan-afk/img-bed-1/main/img13/image-20211001184056426.png)

![image-20211001184152369](https://raw.githubusercontent.com/yijunquan-afk/img-bed-1/main/img13/image-20211001184152369.png)

### (2) structure

![image-20211224114049009](https://raw.githubusercontent.com/yijunquan-afk/img-bed-1/main/img13/image-20211224114049009.png)

