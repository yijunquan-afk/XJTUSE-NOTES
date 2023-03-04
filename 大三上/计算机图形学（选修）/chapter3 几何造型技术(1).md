[TOC]

# 【XJTUSE计算机图形学】第三章 几何造型技术(1)——参数曲线和曲面

**几何造型技术**

> 研究在计算机中，如何表达物体模型形状的技术；
>
> 70年代，已有不少实用化系统；
>
> 已应用于航空航天、汽车、机械、造船、建筑和电子等领域。

<mark>描述物体的三维模型</mark>: **线框模型、曲面模型、实体模型**。

> **线框模型**:  利用形体的**顶点和棱边**来表示物体。
>
> **曲面模型**：通过有向棱边构成形体的表面，用**面的几何**表达相应的形体。
>
> **实体模型**：定义一些基本体素，并通过集合运算将它们组合成复杂的几何形体。

## 参数曲线和曲面

### 曲线曲面参数表示

#### 非参数表示

**显式表示**:y=f(x)，无法表示封闭或多值曲线，如圆。

**隐式表示**:f(x,y)=0，易于判断函数值与零的关系，确定点与曲线的关系。

存在下述问题：

> 与坐标轴相关；
>
> 会出现斜率为无穷大的情形(如垂线)。

#### 参数表示

**参数表示**:曲线上任一点的坐标均表示成给定参数的函数。假定用t表示参数

平面曲线上任一点P: $P(t)=[x(t),y(t)]$

空间曲线上任一三维点P: $P(t)=[x(t),y(t),z(t)]$

参数表示例子：

> 直线：$P(t)=P_1+(P_2-P_1)t$
>
> 圆：$P(t)=[\frac{1-t^2}{1+t^2},\frac{2t}{1+t^2}]$

参数表示的**优点**：

> 满足几何不变性的要求；
> 有更大的自由度来控制曲线、曲面的形状；
> 对参数方程进行几何变换即实现对曲线(面)的变换；
> 便于处理斜率为无穷大的情形；
> 参数方程中，代数、几何相关和无关的变量是完全分离的，且对变量个数不限，便于用户把低维空间中曲线、曲面扩展到高维空间去；
> 规格化的参数变量t∈[0,1]，使其相应的几何分量是有界的，不必用另外的参数去定义边界；
> 易于用矢量和矩阵表示几何分量，简化了计算。

### 曲线的基本概念

:one: **三维曲线**

用参数表示的三维曲线是一个有界的点集，可以表示成一个带参数的、连续的和单值的数学函数：
$$
\left\{ 
    \begin{array}{lc}
        x=x(t) \\
        y=y(t),\quad 0\le t\le 1\\
        z=z(t)
    \end{array}
\right.
$$

:two: **位置矢量**

曲线上任一点的位置矢量可表示为：$P(t)=[x(t),y(t),z(t)]$

如存在k阶导数矢量，则：$P^k(t)=\frac{d^kP}{dt^k}$

:three: **切矢量**

选择弧长**s**作为参数，则 $T=\frac{dP}{ds}=\underset{\Delta s \to0}{\lim}\frac{\Delta P}{\Delta s} $        是单位切矢量

根据弧长微分公式有：

![image-20220213123228060](https://gitee.com/yi-junquan/image_gitee/raw/master/images/image-20220213123228060.png)

于是有  <mark>$\frac{dP}{ds}=\frac{dP}{dt}.\frac{dt}{ds}=\frac{P'(t)}{|P'(t)|}$</mark>

 即*T* 为单位矢量

:four: **法矢量**

所有垂直于切矢量*T* 的矢量有一束，且位于法平面上

$\frac{dT}{ds}$是与T垂直的矢量；与$\frac{dT}{ds}$平行的法矢称为曲线在该点的**主法矢(N)**

矢量积 $B=T\times N$ 是第三个单位矢量，它垂直于T和N。把平行于矢量B的法矢称为曲线的**副法矢**量；

可以推导出：

![image-20220213123733723](https://gitee.com/yi-junquan/image_gitee/raw/master/images/image-20220213123733723.png)

T(切矢)、N(主法矢)和B(副法矢)构成了曲线上的活动坐标架；

N、B构成的平面称为**法平面**，N、T构成的平面称为**密切平面**，B、T构成的平面称为**从切平面**。

:five: 曲率和挠率

圆的半径越小，曲率越大

![image-20220213124835263](https://gitee.com/yi-junquan/image_gitee/raw/master/images/image-20220213124835263.png)

![image-20220213124818254](https://gitee.com/yi-junquan/image_gitee/raw/master/images/image-20220213124818254.png)

![image-20220213125013086](https://gitee.com/yi-junquan/image_gitee/raw/master/images/image-20220213125013086.png)

### 插值、拟合和光顺(掌握概念)

:one: **<mark>插值</mark>**: 给定一组有序的数据点*P*i构造一条曲线顺序**通过这些数据点**，所构造的曲线称为插值曲线。

> **线性插值**：$y=ax+b$
>
> **抛物线插值**：$\varphi(x)=ax^2+bx+c$
>
> ![image-20220213125636751](https://gitee.com/yi-junquan/image_gitee/raw/master/images/image-20220213125636751.png)

:two: **<mark>拟合</mark>**：构造一条曲线使之在某种意义下**最接近给定的数据点**，所构造的曲线为**拟合**曲线。

:three: **逼近**:在计算数学中，逼近通常指用一些性质较好的函数近似表示一些性质不好的函数。在计算机图形学中，逼近继承了这方面的含义

> <mark>包含插值和拟合</mark>

:four: **过拟合：**模型在训练集上效果很好，在测试集上效果差(不考)

:five: **光顺**(Fairing)：指曲线的拐点不能太多。对平面曲线而言，相对光顺的条件是：

> a. 具有<mark>二阶几何连续性(G2)</mark>
>
> b. 不存在多余拐点和奇异点；
>
> c. 曲率变化较小。

### 参数化

#### 概念

过三点P0、P1和P2构造参数表示的插值多项式可以有无数条：

> 对应地参数*t*, 在[0,1]区间中有无数种取法；
>
> 参数值称为节点(knot)。

对于一条插值曲线，型值点$P_0,P_1,...,P_n$与其参数域$t\in[t_0,t_n]$内的节点之间有一种对应关系：

对于一组有序的型值点，所确定一种参数分割，称之为这**组型值点的参数化**。

#### 参数化常用方法

:one: 均匀参数化(等距参数化)； 

节点在参数轴上呈等距分布,  $t_{i+1}=t_i+正常数$。

:two: 累加弦长参数化；
$$
\left\{     
\begin{array}{lc}       
t_0=0 \\        
t_i=t_{i-1}+|\Delta P_{i-1}|,\ i=1,2,...,n,\\        
  
\end{array}
\right.
\qquad \Delta P_i=P_{i+1}-P_i
$$
反映型值点按弦长的分布情况；

能克服均匀参数化所出现的问题。

:three: 向心参数化法；

:four: 修正弦长参数化法。

#### 参数区间的规格化

我们通常将参数区间$[t_0,t_n]$规格化为[0,1]，$[t_0,t_n]\not = [0,1]$，只需对参数化区间作如下处理：
$$
t_0=0,\ t_i=\frac{t_i}{t_n},\ i=0,1,...,n
$$

### 参数曲线的代数和几何形式(了解一下)

以三次参数曲线为例，讨论参数曲线的代数和几何形式

#### 代数形式

![image-20220213141426456](https://gitee.com/yi-junquan/image_gitee/raw/master/images/image-20220213141426456.png)

上述代数式写成矢量式是<img src="https://gitee.com/yi-junquan/image_gitee/raw/master/images/image-20220213141438490.png" alt="image-20220213141438490" style="zoom:50%;" />

#### 几何形式

对三次参数曲线，可用其端点位矢P(0)、P(1)和切矢P‘(0)、P‘(1)描述。

将P(0)、P(1)、P’(0)和P‘(1)简记为P0、P1、P‘0和P’1，代入

![image-20220213141527888](https://gitee.com/yi-junquan/image_gitee/raw/master/images/image-20220213141527888.png)，得

![image-20220213141544736](https://gitee.com/yi-junquan/image_gitee/raw/master/images/image-20220213141544736.png)

![image-20220213141549416](https://gitee.com/yi-junquan/image_gitee/raw/master/images/image-20220213141549416.png)

令![image-20220213141628288](https://gitee.com/yi-junquan/image_gitee/raw/master/images/image-20220213141628288.png)

简化为<img src="https://gitee.com/yi-junquan/image_gitee/raw/master/images/image-20220213141639553.png" alt="image-20220213141639553" style="zoom:67%;" />

上式是三次Hermite(Ferguson)曲线的**几何形式**

> •**几何系数**：$ P_0、P_1、P'_0和P'_1$
>
> •**调和系数**：$F_0、F_1、G_0、G_1$

![image-20220213141911351](https://gitee.com/yi-junquan/image_gitee/raw/master/images/image-20220213141911351.png)

参数$F_0,F_1$专门控制端点的函数值对曲线的影响；

参数$G_0,G_1$专门控制端点的一阶导数值对曲线的影响。

### 连续性

设计制造时，组合多段曲线，因此需要解决曲线段之间的**光滑连接问题**。

<mark>曲线间连接的光滑度的度量</mark>(会考概念)

> **参数连续性**：组合参数曲线在连接处具有直到*n*阶连续导矢，即*n*阶连续可微，称为*n*阶参数连续性$C^n$
>
> **几何连续性**：组合曲线在连接处满足不同于$C^n$的某一组约束条件，称为具有*n*阶几何连续性$G^n$。
>
> > 介于n-1阶参数连续性和n阶参数连续性之间
>
> 同阶参数连续性的要求比几何连续性高

#### 引进几何连续的重要性

:label: **举例**

![image-20220213142246320](https://gitee.com/yi-junquan/image_gitee/raw/master/images/image-20220213142246320.png)

> 第$\Phi(t)$在[0,2]上表示一条连接$V_0,V_1$的直线段；
>
> 左右导数不等: $\Phi(1^-)=\frac{1}{3}(V_1-V_0),\ \Phi(1^+)=\frac{2}{3}(V_1-V_0)$
>
> 参数连续描述光滑性不恰当。

#### <mark>举例说明</mark>

对于参数$t\in [0,1]$的两条曲线*P*(t)和*Q*(*t*)

![image-20220213144235983](https://gitee.com/yi-junquan/image_gitee/raw/master/images/image-20220213144235983.png)

:one: 若要求在结合处达到$C^0$连续或$G^0$连续，即两曲线在结合处位置连续：$P(1)=Q(0)$

:two: 若要求在结合处达到$G^1$连续，就是说两条曲线在结合处在满足$G^0$连续的条件下，并有公共的切矢
$$
Q'(0)=\alpha P'(1)\qquad (\alpha >0)
$$
当$\alpha = 1$时， $G^1$连续就成为$C^1$ 连续

> 若*P* 和*Q* 在连接处已有$C^0 C^1$连续性且曲率的大小和方向均相等，即$P''(1)=Q''(0)$则*P* 和*Q* 在连接处具有$C^2$连续
>
> 若*P* 和*Q* 在连接处已有$C^0 C^1$连续性且曲率的**大小不相等但方向相等**，则*P* 和*Q* 在连接处具有$G^2$连续。

:three: 若要求在结合处达到$G^2$连续，就是说两条曲线在结合处在满足$G^1$连续的条件下，并**有公共的曲率矢**：

![image-20220213144734310](https://gitee.com/yi-junquan/image_gitee/raw/master/images/image-20220213144734310.png)

这个关系可写为<img src="https://gitee.com/yi-junquan/image_gitee/raw/master/images/image-20220213144756444.png" alt="image-20220213144756444" style="zoom: 67%;" />

$\beta$为任意常数，当$\alpha =1 , \beta = 0$时，$G^2$连续就成了$C^2$连续

### 参数曲面基本概念

一张定义在矩形域上的参数曲面可以表示为

![image-20220213145226314](https://gitee.com/yi-junquan/image_gitee/raw/master/images/image-20220213145226314.png)

可记为<img src="https://gitee.com/yi-junquan/image_gitee/raw/master/images/image-20220213145231332.png" alt="image-20220213145231332" style="zoom:50%;" />

曲面上的点：将给定的参数值$u_0,v_0$代入参数方程，可得曲面上的点$P(u_0,v_0)$

曲面上一点的切向量(切矢)：
$$
\frac{\partial{}P(u,v)}{\partial{}u}|u=u_0,v=v_0 \qquad \frac{\partial{}P(u,v)}{\partial{}v}|u=u_0,v=v_0
$$
曲面上一点的法向(法矢)：

<img src="https://gitee.com/yi-junquan/image_gitee/raw/master/images/image-20220213150457701.png" alt="image-20220213150457701" style="zoom: 67%;" />

角点：$P(0,0),P(0,1),P(1,0),P(1,1)$

边界线：$P(u,0),P(u,1),P(0,w),P(1,w)$

![image-20220213150623023](https://gitee.com/yi-junquan/image_gitee/raw/master/images/image-20220213150623023.png)

