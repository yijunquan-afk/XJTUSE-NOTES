[TOC]

# 【XJTUSE计算机图形学】第三章 几何造型技术(2)——Bezier 曲线与曲面

## Bezier 曲线与曲面(11分)

**起源**

由于几何外形设计的要求越来越高，传统的曲线曲面表示方法, 已不能满足用户的需求；

1962年，P.E.Bezier构造了一种以逼近为基础的参数曲线和曲面的设计方法，并用这种方法完成了UNISURF系统(1972年投入了应用)；

Bezier方法将函数逼近同几何表示结合起来，使得设计师在计算机上就象使用作图工具一样得心应手。

**优点**

输入的控制点与生成曲线之间的关系明确；

能方便地改变曲线的形状和阶次；

无论是直线或曲线都能在数学上予以描述 (为计算机矢量图形学奠定了基础 )。

**应用领域**

:one: 计算机辅助设计与制造（CAD/CAM）

> 飞机、汽车、船舶外形的设计；
>
> 水泵叶轮和齿轮等机械零件的设计。

:two: 桥梁建筑物以及日用品的设计

:three: 曲线字形轮廓描述

:four: 地图图形管理系统

:five: 移动机器人运动规划

> 处于A点，需要达到D点；
>
> AD之间有障碍物；
>
> 运动往往是沿着曲线进行的。

### Bezier 曲线的定义与性质

#### 定义

给定空间*n*+1个点的位置矢量*Pi*（*i*=0,1,2,…,*n*），则Bezier参数曲线上各点坐标的插值公式是：

<img src="https://gitee.com/yi-junquan/image_gitee/raw/master/images/image-20220213161512575.png" alt="image-20220213161512575" style="zoom:67%;" />

$P_i$构成该Bezier曲线的特征多边形

$B_{i,n}(t)$是n次Bernstein**基函数**：

![![](https://gitee.com/yi-junquan/image_gitee/raw/master/images/image-20220213161653148.png)](https://gitee.com/yi-junquan/image_gitee/raw/master/images/image-20220213161653148.png)

**举例**![image-20220213161916870](https://gitee.com/yi-junquan/image_gitee/raw/master/images/image-20220213161916870.png)

#### 习题

:one: 设有控制顶点为P0(0,0)，P1(48,96)，P2(120,120)，P3(216,72)的三次Bézier曲线P(t)，试计算P(0,4)的(x,y)坐标，并写出(x(t),y(t))的多项式表示。

![image-20220213163220219](https://gitee.com/yi-junquan/image_gitee/raw/master/images/image-20220213163220219.png)

:two: 设一条三次Bézier曲线的控制顶点为P0，P1，P2，P3。对曲线上一点P(0.5)，及一个给定的目标点T，给出一种调整Bézier曲线形状的方法，使得P(0.5)精确通过点T。

![image-20220213164204503](https://gitee.com/yi-junquan/image_gitee/raw/master/images/image-20220213164204503.png)

#### Bernstein基函数性质

:one: **正性**

![image-20220213164640737](https://gitee.com/yi-junquan/image_gitee/raw/master/images/image-20220213164640737.png)

:two: **端点性质**

![image-20220213164907949](https://gitee.com/yi-junquan/image_gitee/raw/master/images/image-20220213164907949.png)

:three: **权性**

![image-20220213164931020](https://gitee.com/yi-junquan/image_gitee/raw/master/images/image-20220213164931020.png)

:four: **对称性**

![image-20220213165000146](https://gitee.com/yi-junquan/image_gitee/raw/master/images/image-20220213165000146.png)

**推导**

![image-20220213165014714](https://gitee.com/yi-junquan/image_gitee/raw/master/images/image-20220213165014714.png)

![image-20220213165119631](https://gitee.com/yi-junquan/image_gitee/raw/master/images/image-20220213165119631.png)

:five: **递推性**

![image-20220213165158514](https://gitee.com/yi-junquan/image_gitee/raw/master/images/image-20220213165158514.png)

**推导**

![image-20220213165215822](https://gitee.com/yi-junquan/image_gitee/raw/master/images/image-20220213165215822.png)

<mark>即高一次的Bernstein基函数可由两个低一次Bernstein基函数线性组合而成。</mark>>

:six: **导函数**

![image-20220213165313812](https://gitee.com/yi-junquan/image_gitee/raw/master/images/image-20220213165313812.png)

**推导**

![image-20220213165332468](https://gitee.com/yi-junquan/image_gitee/raw/master/images/image-20220213165332468.png)

:seven: **最大值**

![image-20220213165343864](https://gitee.com/yi-junquan/image_gitee/raw/master/images/image-20220213165343864.png)

:eight: **积分**

![image-20220213165354560](https://gitee.com/yi-junquan/image_gitee/raw/master/images/image-20220213165354560.png)

:nine: **升阶公式**

![image-20220213165404786](https://gitee.com/yi-junquan/image_gitee/raw/master/images/image-20220213165404786.png)

#### <mark>Bezier曲线的性质</mark>(选择)

:one: **端点性质**

**曲线端点位置矢量**

> 由Bernstein基函数的端点性质可以推得，当t=0时，$P(0)=P_0$ ；当t=1时，$P(1)=P_n$。由此可见，Bezier曲线的<mark>**起点、终点与相应的特征多边形的起点、终点重合**</mark>。

**切矢量**

> 因为<img src="https://gitee.com/yi-junquan/image_gitee/raw/master/images/image-20220213165707531.png" alt="image-20220213165707531" style="zoom: 80%;" />  所以当t=0时，$P’(0)=n(P_1-P_0)$，当t=1时，$P’(1)=n(P_n-P_{n-1})$，Bezier曲线的<mark>**起点和终点处的切线方向和特征多边形的第一条边及最后一条边的走向一致**</mark>

**二阶导矢**

![image-20220213170004104](https://gitee.com/yi-junquan/image_gitee/raw/master/images/image-20220213170004104.png)

<mark>**2阶导矢只与相邻的3个顶点有关，事实上，*r*阶导矢只与（*r*+1）个相邻点有关，与更远点无关**</mark>

**:two: *k*阶导函数的差分表示**

n次Bezier曲线的k阶导数可用差分公式为：

![image-20220213170205275](https://gitee.com/yi-junquan/image_gitee/raw/master/images/image-20220213170205275.png)

其中**高阶向前差分矢量**由低阶向前差分矢量递推地定义：

![image-20220213170212572](https://gitee.com/yi-junquan/image_gitee/raw/master/images/image-20220213170212572.png)

例如

![image-20220213170225825](https://gitee.com/yi-junquan/image_gitee/raw/master/images/image-20220213170225825.png)

<font color="red">**题目**</font>

试证明n次Bezier曲线退化为（n-1）次Bezier曲线的条件为$\Delta ^0 P_0=0$ 。 

![image-20220213171428110](https://gitee.com/yi-junquan/image_gitee/raw/master/images/image-20220213171428110.png )

:three: **对称性**

由控制顶点$P_i^*=P_{n-i},(i=0,1,...,n)$构造出的新Bezier曲线，与原Bezier曲线形状相同，走向相反。因为：

![image-20220213173655651](https://gitee.com/yi-junquan/image_gitee/raw/master/images/image-20220213173655651.png)这个性质说明Bezier曲线在起点处有什么几何性质，在终点处也有相同的性质。

:four: **凸包性**

由于$\underset{i=0}{\overset{n}{\sum}}B_{i,n}(t)\equiv 0$，且$0\le B_{i,n}(t)\le 1(0\le t \le 1,i=0,1,...,n)$，这一结果说明当*t*在[0,1]区间变化时，对某一个*t*值，*P(t)*是特征多边形各顶点的加权平均，权因子依次是$B_{i,n}(t)$  ；

在几何图形上，意味着Bezier曲线*P(t)*在$t\in [0,1]$中各点是控制点*P*i的**凸线性组合**，即曲线落在*P*i构成的凸包之中 。

:five: **几何不变性**

某些几何特性不随坐标变换而变化的特性；

Bezier曲线位置与形状与其特征多边形顶点$P_i(i=0,1,...,n)$ 的位置有关：

![image-20220213174351235](https://gitee.com/yi-junquan/image_gitee/raw/master/images/image-20220213174351235.png)

<mark>:six: **变差缩减性**</mark>(考点)

若Bezier曲线的特征多边形$P_0P_1...P_n$ 是一个平面图形，则平面内任意直线与*P*(*t*)的交点个数不多于该直线与其特征多边形的交点个数；

反映了Bezier曲线**比其特征多边形的波动还小**

:seven: **仿射不变性**

对于任意的仿射变换*A*：

![](https://gitee.com/yi-junquan/image_gitee/raw/master/images/image-20220213174756396.png)

即在仿射变换下，   的形式不变。

#### 矩阵表示形式

![image-20220213174912045](https://gitee.com/yi-junquan/image_gitee/raw/master/images/image-20220213174912045.png)

### Bezier曲线的递推算法(必考)

#### 算法原理

de Casteljau(德卡斯特里奥)递推算法

便于计算Bezier曲线上的点

<img src="https://upload-images.jianshu.io/upload_images/24521788-3db3d179dce6fa10.png?imageMogr2/auto-orient/strip|imageView2/2/format/webp" alt="img" style="zoom:50%;" />

如图所示，设$P_0、P_0^2、P_2$是一条抛物线上顺序三个不同的点。过$P_0$和$P_2$点的两切线交于$P_1$点,在$P_0^2$点的切线交$P_0P_1$和$P_2P_1$于$P_0^1$和 $P_1^1$，则如下比例成立：

![image-20220213175528804](https://gitee.com/yi-junquan/image_gitee/raw/master/images/image-20220213175528804.png)



[证明过程](https://www.jianshu.com/p/6e53086030c8)

当$P_0$，$P_2$固定，引入参数*t*，令上述比值为*t*:(1-*t*)，即有：

![image-20220213181349195](https://gitee.com/yi-junquan/image_gitee/raw/master/images/image-20220213181349195.png)

*t*从0变到1，第(1)、(2)式就分别表示控制二边形的第一、二条边，它们是两条一次Bezier曲线。

将(1)、(2)式代入第(3)式得

![image-20220213181419189](https://gitee.com/yi-junquan/image_gitee/raw/master/images/image-20220213181419189.png)

当*t*从0变到1时，它表示了由三顶点*P*0、*P*1、*P*2三点定义的一条二次Bezier曲线；

这二次Bezier曲线可以定义为分别由前两个顶点(*P*0,*P*1)和后两个顶点(*P*1,*P*2)决定的一次Bezier曲线的线性组合。

依次类推，由四个控制点定义的三次Bezier曲线  可被定义为分别由(*P*0,*P*1,*P*2)和(*P*1,*P*2,*P*3)确定的两条二次Bezier曲线的线性组合

<mark>由(n+1)个控制点$P_i(i=0,1,...,n)$定义的n次Bezier曲线$P_0^n$可被定义为分别由前、后n个控制点定义的两条(n-1)次Bezier曲线$P_0^{n-1}$与$P_1^{n-1}$的线性组合</mark>

![image-20220213181809957](https://gitee.com/yi-junquan/image_gitee/raw/master/images/image-20220213181809957.png)

由此得到Bezier曲线的递推计算公式：

![image-20220213181839981](https://gitee.com/yi-junquan/image_gitee/raw/master/images/image-20220213181839981.png)

> 在给定参数下，求Bezier曲线上一点*P*(*t*)非常有效。
>
> 上式中：  $P_i^0=P_i$是定义Bezier曲线的控制点，$P_0^n$即为曲线P(t)上具有参数*t*的点。
>
> 算法稳定可靠，直观简便，可以编出十分简捷的程序，是计算Bezier曲线的基本算法和标准算法。

当*n*=3时，算法递推出的$P_i^k$呈直角三角形，对应结果如图所示。从左向右递推，最右边点$P_0^3$即为曲线上的点。

<img src="https://gitee.com/yi-junquan/image_gitee/raw/master/images/image-20220213182222210.png" alt="image-20220213182222210" style="zoom: 67%;" />

#### 习题举例

:one: 已知三次Bezier曲线上的4个点分别为Q0(50,0), Q1(100,0), Q2(0,50), Q3(0,100),它们对应的参数分别为0,1/3, 2/3,1,求Bezier曲线的控制顶点。[了解一下]

![image-20220213182619293](https://gitee.com/yi-junquan/image_gitee/raw/master/images/image-20220213182619293.png)<mark>:two: 计算以(30,0),(60,10),(80,30),(90,60),(90,90)为控制顶点的4次Bezier曲线在t=1/2处的值,并画出de Casteljau三角形</mark>

![image-20220213183117515](https://gitee.com/yi-junquan/image_gitee/raw/master/images/image-20220213183117515.png)

:three: 给出三次Bezier曲线退化为二次Bezier，控制顶点P0,P1,P2,P3应该满足的条件。 

![image-20220213183848289](https://gitee.com/yi-junquan/image_gitee/raw/master/images/image-20220213183848289.png)

:four: 设一条二次Bezier曲线的控制顶点为P0,P1,P2,另一条二次Bezier曲线的控制顶点为Q0,Q1,Q2, P2 =Q0.写出两条曲线可以精确合并(表示)为一条二次Bezier曲线的条件。 

![image-20220213190309738](https://gitee.com/yi-junquan/image_gitee/raw/master/images/image-20220213190309738.png)

### Bezier曲线的拼接(了解)

几何设计中，一条Bezier曲线往往难以描述复杂的曲线形状。

> 增加特征多边形的顶点数，会引起Bezier曲线次数的提高，而高次多项式又会带来计算上的困难；
>
> 可采用**分段设计**，然后将各段曲线相互连接起来，并在接合处保持一定的连续条件。

两段Bezier曲线达到不同阶几何连续的条件

![image-20220213195628738](https://gitee.com/yi-junquan/image_gitee/raw/master/images/image-20220213195628738.png)

•给定两条Bezier曲线*P*(*t*)和*Q*(*t*)，相应控制点为*P*i(*i*=*0*,1,...,*n*)和*Q*j(*j=*0,1,..., *m*)，且令        $a_i=P_i-P_{i-1},b_j=Q_j-Q_{j-1}$，如图所示，我们现在把两条曲线连接起来。

:one: 达到$G^0$连续的充要条件是：$P_n=Q_0$；

:two: 达到$G^1$连续的充要条件是：$P_{n-1},P_n=Q_0,Q_1$三点共线，即：$b_1=\alpha a_n(\alpha>0)$

:three: 达到$G^2$连续的充要条件是：$G^1$在连续的条件下，并满足方程   

![image-20220213200121585](https://gitee.com/yi-junquan/image_gitee/raw/master/images/image-20220213200121585.png)

![image-20220213200149637](https://gitee.com/yi-junquan/image_gitee/raw/master/images/image-20220213200149637.png)

> 选择$\alpha$和 $\beta$的值，可以利用该式确定曲线段$Q(t)$的特征多边形顶点$Q_2$
>
> 顶点$Q_0$、$Q_1$已被$G^1$连续条件所确定，要达到$G^2$连续的话，只剩下顶点$Q_2$可以自由选取；
>
> 如果上式的两边都减去$P_n$，则等式右边可以表示为$(P_n-P_{n-1})$和 $(P_{n-1}-P_{n-2})$ 的线性组合：
>
> ![image-20220213200456320](https://gitee.com/yi-junquan/image_gitee/raw/master/images/image-20220213200456320.png)
>
> 这表明$P_{n-2}、P_{n-1}、P_n=Q_0、Q_1和Q_2$ 五点共面

### Bezier曲线的升阶与降阶（考过了）

#### Bezier曲线的升阶

##### 定义

**升阶**：**保持Bezier曲线的形状与方向不变，增加定义它的控制顶点数，提高该Bezier曲线的次数。**

增加了控制顶点数，增加了对曲线进行形状控制的灵活性，还在构造曲面方面有着重要的应用：

> 对于由曲线生成曲面的算法，要求那些曲线必须是同次的。

应用升阶的方法，可以把低于最高次数的的曲线提升到最高次数，而获得相同的次数。

##### 新控制顶点的计算

设给定原始控制顶点$P_0,P_1,...,P_n$，定义了一条*n*次Bezier曲线：

![image-20220213200936277](https://gitee.com/yi-junquan/image_gitee/raw/master/images/image-20220213200936277.png)

增加一个顶点后，仍定义同一条曲线的新控制顶点为$P_0^*,P_1^*,...,P_{n+1}^*$,则有

![image-20220213201047222](https://gitee.com/yi-junquan/image_gitee/raw/master/images/image-20220213201047222.png)

对上式左边乘以(t+(1-t))，得到

![image-20220213201110375](https://gitee.com/yi-junquan/image_gitee/raw/master/images/image-20220213201110375.png)

比较等式两边$t_i(1-t)^{n+1-i}$项的系数，得到

![image-20220213201157729](https://gitee.com/yi-junquan/image_gitee/raw/master/images/image-20220213201157729.png)

![image-20220213201202957](https://gitee.com/yi-junquan/image_gitee/raw/master/images/image-20220213201202957.png)

其中$P_{-1}=P_{n+1}=(0,0)$

上述结果说明：

> 新的控制顶点$P_i^*$是以参数值$\frac{i}{n+1}$按分段线性插值从原始特征多边形得出的
>
> 升阶后的新的特征多边形在原始特征多边形的凸包内
>
> 特征多边形更靠近曲线。

##### 题目

推导Beizer曲线的升阶公式；给定三次Bezier曲线的控制顶点(0,0), (0,100), (100,0), (100,100),计算升阶一次后的控制顶点。

![image-20220213201927227](https://gitee.com/yi-junquan/image_gitee/raw/master/images/image-20220213201927227.png)

#### Bezier曲线的降阶

给定一条由原始控制顶点定义的*n*次Bezier曲线，要求找到一条由新控制顶点定义的*n*-1次Bezier曲线来逼近原始曲线。

##### 新控制点的计算

–假定$P_i$是由$P_i^*$升阶得到，则由升阶公式有：

![image-20220213202136239](https://gitee.com/yi-junquan/image_gitee/raw/master/images/image-20220213202136239.png)

从前述方程可以导出两个递推公式

![image-20220213202223564](https://gitee.com/yi-junquan/image_gitee/raw/master/images/image-20220213202223564.png)

其中第一个递推公式在靠近*P*0处趋向生成较好的逼近，而第二个递推公式在靠近*Pn*处趋向生成较好的逼近

### Bezier曲面(不考)

#### 定义

设$P_{ij}(i=0,1,...,m;j=0,1,...,n)$为$(m+1)\times (n+1)$个空间点列，则    $m\times n$次张量积形式的Bezier曲面定义为：

![image-20220213203141710](https://gitee.com/yi-junquan/image_gitee/raw/master/images/image-20220213203141710.png)

其中$B_{i,m}(u),B_{j,n}(v)$是Bernstein基函数。

依次用线段连接点列$P_{ij}(i=0,1,...,m;j=0,1,...,n)$中相邻两点所形成的空间网格，称之为特征网格。

矩阵表示式是

![image-20220213203322817](https://gitee.com/yi-junquan/image_gitee/raw/master/images/image-20220213203322817.png)

在一般实际应用中*m*,*n* 不大于4

#### 性质

> 几何不变性。
>
> 对称性。
>
> 凸包性。 

**除变差缩减性质**外，Bezier曲线的其它性质可推广到Bezier曲面：

曲面特征网格的四个角点正好是Bezier曲面的四个角点，即![image-20220213203428766](https://gitee.com/yi-junquan/image_gitee/raw/master/images/image-20220213203428766.png)

曲面特征网格最外一圈顶点定义曲面的四条边界；

#### 曲面的拼接

设两张*m*×*n*次Bezier曲面片

![image-20220213203701325](https://gitee.com/yi-junquan/image_gitee/raw/master/images/image-20220213203701325.png)

分别由控制顶点Pij和Qij定义。

<img src="https://gitee.com/yi-junquan/image_gitee/raw/master/images/image-20220213204344724.png" alt="image-20220213204344724" style="zoom:80%;float:left" />

#### 递推算法

![image-20220213204655145](https://gitee.com/yi-junquan/image_gitee/raw/master/images/image-20220213204655145.png)

