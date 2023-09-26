[TOC]

# 第十八章 多核计算机

## KEY POINTS

> Why multicore？
>
> TLP and SMT architecture
>
> Multicore organization contents and main modes
>
> Software on multicore
>
> 多核的定义
>
> 出现的必然性
>
> 超标量过渡到超线程
>
> 多核的架构物理组成不同
>
> 二级cache的优点

多核计算机也称为单芯片多处理器，指在一个单独的硅片上结合两个或多个处理器（称为核）。

## 硬件性能问题

### 功耗 power dissipation

#### 散热问题Heat Dissipation Problem

散热是几个因素的函数，其中两个是处理器密度和时钟速度：是直接成比例影响的

**Pollack规则**：性能增长与复杂度增加的平方成比例

散热要求**芯片密度和时钟频率低**->多核是唯一的选择

### 并行需要



![image-20211208101405904](https://raw.githubusercontent.com/yijunquan-afk/img-bed-1/main/img2/1695704570.png)

![image-20211208101528637](https://raw.githubusercontent.com/yijunquan-afk/img-bed-1/main/img2/1695704571.png)

![image-20211208102437894](https://raw.githubusercontent.com/yijunquan-afk/img-bed-1/main/img2/1695704572.png)

只有到多核以后，硬件上才真正支持多核

### 多核框架

![image-20211208103209678](https://raw.githubusercontent.com/yijunquan-afk/img-bed-1/main/img2/1695704574.png)

#### 共享二级cache优点

建设性干扰可以降低整体未命中率

>  一个内核上的线程访问主内存位置会导致块传输到共享缓存
>
>  如果另一个内核上的线程很快访问相同的内存块，它将命中

 多个内核共享的数据不会在共享缓存级别复制

 分配给每个核心的共享缓存量是动态的

>  位置较少的线程可以使用更多缓存

 通过共享缓存轻松实现核心间通信

 共享 L2 高速缓存将高速缓存一致性问题限制在 L1 高速缓存级别

