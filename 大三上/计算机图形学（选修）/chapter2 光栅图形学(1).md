[TOC]

# 【XJTUSE计算机图形学】第二章 光栅图形学（1）

## 1、基本概念

扫描转换（光栅化）：确定最佳逼近图形的像素几何，并用指定属性写像素的过程

区域填充：光栅化过程中确定区域对应的像素集，并用指定的属性或图案显示

裁减：确定一个图形的哪部分在窗口内显示

<font color="red">**走样**</font>：**由于显示器的空间分辨率有限，因像素逼近误差，使所画图形产生畸变（台阶、锯齿）的现象**

**<font color="red">反走样</font>：减少或消除走样的技术：硬件、软件的方法**

隐藏部分：当不透光的物体遮挡了来自某些物体部分的光线，使其无法到达观察者

消隐：把隐藏的部分从图中删除，消除歧义性

## 2、直线段的扫描转换算法

**直线的扫描转换**: 确定最佳逼近于直线的一组象素，且按扫描线顺序，对这些象素进行写操作

### 数值微分(DDA)法

已知过端点$P_0(x_0,y_0),P_1(x_1,y_1)$的直线段L：$y=kx+b$直线斜率为 
$$
k=\frac{y_1-y_0}{x_1-x_0}
$$
从$x$的左端点$x_0$开始，向$x$右端点步进。步长=1(个象素)，计算相应的y坐标$y=kx+b$；取象素点(x, round(y))  作为当前点的坐标

> <mark>方法直观，效率低</mark>
>
> 作为最底层的光栅图形算法，在通常的CAD/图形系统中，会被大量应用
>
> 由此出发，导致增量算法的思想

#### 增量算法

在一个迭代算法中，每一步的x、y值是用前一步的值加上一个增量来获得
$$
计算y_{i+1}=kx_{i+1}+b=kx_i+b+k\Delta x=y_i+k\Delta x\\
当\Delta x=1时: y_{i+1}=y_i+k
$$
当x每递增1，y递增k(即直线斜率)；

> 由此注意上述分析的算法仅适用于|k| ≤1的情形。在这种情况下，x每增加1，y最多增加1
>
> 当 |k| >1时，必须把x，y位置互换

![image-20220106105953735](https://gitee.com/yi-junquan/image_gitee/raw/master/images/image-20220106105953735.png)

```c
void DDALine(int x0,int y0,int x1,int y1,int color)     
{     
      int x；							
	float dx, dy, y, k;						
	dx= x1-x0, dy=y1-y0;					   
	k=dy/dx, y=y0;	 					   
	for (x=x0; x<=x1, x++)					   
	{
       drawpixel (x, int(y+0.5), color);		   
	   y=y+k;
     }
}
```

在此算法中，y、k必须是float，且每一步都必须对y进行舍入取整，**不利于硬件实现**

上述算法仅适用于|k|  ≤  1  的情况.   当|k|  >  1  时,  必须把x,  y  的位置互换.

### 中点画线法[重点]

将一个加法改为一个整数加法

**基本思想**

对于理想直线$L(p_0(x_0,y_0),p_1(x_1,y_1))$,采用直线$F(x,y)=ax+by+c=0$表示。其中$a=y_0-y_1, b=x_1-x_0, c=x_0y_1-x_1y_0$

当前象素点为$(x_p, y_p) $，下一个象素点为$P_1$ 或$P_2$ 。设$M=(x_p+1, y_p+0.5)$为$P_1$ 与$P_2$之中点，Q为理想直线与$x=x_p+1$垂线的交点。将Q与M的y坐标进行比较。

> 当M在Q的下方，则$P_2$应为下一个象素点
>
> M在Q的上方，应取$P_1$为下一点
>
> <img src="https://gitee.com/yi-junquan/image_gitee/raw/master/images/image-20220106110837433.png" alt="image-20220106110837433" style="zoom:67%;" />

<font color="red">**构造判别式**</font>
$$
d=F(M)=F(x_p+1,y_p+0.5)=a(x_p+1)+b(y_p+0.5)+c\\

其中a=y_0-y_1, b=x_1-x_0, c=x_0y_1-x_1y_
$$

> 当d<0，M在L(Q点)下方，取右上方P2为下一个象素
>
> 当d>0，M在L(Q点)上方，取右下方P1为下一个象素
>
> 当d=0，选P1或P2均可，约定取P1为下一个象素

**计算量**：每一个象素的是四个加法，两个乘法。采用增量算法如下：

:label: 若当前象素处于$d\ge0$情况，则取正右方象素$P_1 (x_p+1, y_p)$, 要判下一个象素位置，应计算

 $  d_1=F(x_p+2, y_p+0.5)=a(x_p+2)+b(y_p+0.5)=d+a$； **增量为a**

:label: 若$d<0$时，则取右上方象素$P_2 (x_p+1, y_p+1)$。要判断下一象素，则要计算

 $ d_2= F(x_p+2, y_p+1.5)=a(x_p+2)+b(y_p+1.5)+c=d+a+b$；**增量为a＋b**

即

> $d\ge 0\rarr d+a$
>
> $d\le 0\rarr d+a+b$

**进一步改进，实现整数运算**

画线从$(x_0, y_0)$开始，$F(x_0,y_0)=0$ ,d的初值
$$
d_0=F(x_0+1, y_0+0.5)=F(x_0, y_0)+a+0.5b =a+0.5b\\

a=y_0-y_1, b=x_1-x_0
$$
可以用2d代替d来摆脱小数，提高效率。

令 $d_0=2a+b, d_1=2a, d_2=2a+2b$,我们有如下算法 。

```c
void MidpointLine (int x0, int y0, int x1, int y1, int color) {
	int a, b, d1, d2, d, x, y;
	a = y0 - y1, b = x1 - x0, d = 2 * a + b;
	d1 = 2 * a, d2 = 2 * (a + b);
	x = x0, y = y0;
	drawpixel(x, y, color);
	while (x < x1) {
		if (d < 0)       {
			x++, y++, d += d2;//取右上方
		} else       {
			x++, d += d1;//取右方
		}
		drawpixel (x, y, color);
	}  /* while */
} /* mid PointLine */
```

:label: d只影响判断取哪一个点，并不影响坐标本身的值，所以乘上2不影响直线

<img src="https://gitee.com/yi-junquan/image_gitee/raw/master/images/image-20220106115521255.png" alt="image-20220106115521255" style="zoom:80%;" />

### Bresenham算法[重点 很有可能会考]

**基本思想**

–DDA算法采用点斜式，中点法采用隐式表示。

–中点法可以有整数算法。还有其它整数算法吗？

–过各行各列象素中心构造虚拟网格线：按直线从起点到终点顺序计算直线与各垂直网格线的交点，根据误差项符号确定该列象素中与此交点最近的象素

![image-20220106115900576](https://gitee.com/yi-junquan/image_gitee/raw/master/images/image-20220106115900576.png)

设直线方程为$y=kx+b\ 则有y_{i+1}=y_i+k(x_{i+1}-x_i)=y_i+k$，其中k=dy/dx。 因为直线的起始点在象素中心，所以误差项d的初值$d_0＝0$

X下标每增加1，d的值相应递增直线的斜率值k，即d＝d＋k。一旦d≥0.5，就把它减去1

> :one: 当d≥0.5时，最接近于当前象素的右上方象素$(x_{i+1},y_{i+1})$
>
> :two: 而当d<0.5时，更接近于右方象素$(x_{i+1},y_i)$。

为方便计算，令e＝d-0.5，e的初值为-0.5，增量为k。

> :one: 当e≥0时，取当前象素$(x_i,y_i)$的右上方象素$(x_{i+1},y_{i+1})$
>
> :two: 而当e<0时，更接近于右方象素$(x_{i+1},y_i)$。

**代码**

```c
void BresenhamLine (int x0, int y0, int x1, int y1, int color) {
	int x, y, dx, dy,i;
	float k, e;
	dx = x1 - x0, dy = y1 - y0, k = dy / dx;
	e = -0.5, x = x0, y = y0;
	for (i = 0; i <= dx; i++) {
		drawpixel (x, y, color);
		x = x + 1，e = e + k;
		if (e >= 0) {
			y++, e = e - 1;
		}
	}
}
```

<img src="https://gitee.com/yi-junquan/image_gitee/raw/master/images/image-20220106160219331.png" alt="image-20220106160219331" style="zoom:60%;" />

可以改用整数以避免除法。由于算法中只用到误差项的符号，因此可作如下替换$e'=2\times e\times dx$， e的初值为-0.5

**代码（优化后）**

```c
void BresenhamLine (int x0, int y0, int x1, int y1, int color) {
	int x, y, dx, dy, i, e;
	dx = x1 - x0, dy = y1 - y0, e = -dx;
	x = x0, y = y0;
	for (i = 0; i <= dx; i++) {
		drawpixel (x, y, color);
		x++, e = e + 2 * dy;
		if (e >= 0)   {
			y++, e = e - 2 * dx;
		}
	}
}
```



### 总结

:label: DDA方法：根据直线方程 X→Y；

:label: 其它两种的原理：0<K<1时，下一像素只可能是当前像素的右点或右上点

:label: 如何决定取右点或右上点？构造判别式（二者区别）

> –中点算法：根据直线正负划分性，将中点坐标代入方程得到判别式
>
> –Bresenham算法：误差项d->e
>
> ![image-20220106160849896](https://gitee.com/yi-junquan/image_gitee/raw/master/images/image-20220106160849896.png)

## 3、圆弧的扫描转换算法

**圆的特征**：八对称性。只要扫描转换八分之一圆弧，就可以求出整个圆弧的象素集

```c
void CirclePoints(int x, int y, int color) {
	drawpixel(x, y, color);
	drawpixel(y, x, color);
	drawpixel(-x, y, color);
	drawpixel(-y, x, color);
	drawpixel(x, -y, color);
	drawpixel(y, -x, color);
	drawpixel(-x, -y, color);
	drawpixel(-y, -x, color);
}
```

![image-20220106161302317](https://gitee.com/yi-junquan/image_gitee/raw/master/images/image-20220106161302317.png)

以圆心在原点、半径R为整数的圆为例，讨论圆的生成算法。假设圆的方程为：$ X^2 + Y^2 = R^2$

### 圆弧扫描算法

$Y=\pm \sqrt{R^2-X^2}$

在一定范围内，每给定 X值，可得一Y值

> –缺点：浮点运算，开方，取整，不均匀。

<img src="https://gitee.com/yi-junquan/image_gitee/raw/master/images/image-20220106161600997.png" alt="image-20220106161600997" style="zoom:50%;float:left" />

### 角度DDA法

$$
x_n+1 =x_n + dx\qquad y_n+1 =y_n + dy\\

x = x_0 + Rcos\theta\qquad y = y0 + Rsin\theta\\

dx =- Rsin\theta d\theta\qquad dy = Rcos\theta d\theta\\

  x_{n+1} = x_n + dx = x_n - Rsin\theta d\theta =x_n - (y_n - y_0 )d\theta\\

  y_{n+1} = y_n + dy = y_n + Rcos\theta d\theta =y_n + (x_n - x_0 )d\theta\\
$$

确定x,y的初值及dq值后，即可以增量方式获得圆周上的坐标，然后取整可得象素坐标。

但要采用浮点运算、乘法运算、取整运算。

### 中点画圆法

考虑中心在原点，半径为R的第二个8分圆，构造函数$F(x,y)=x^2+y^2-R^2$

构造**判别式（圆方程）**
$$
d=F(M)=F（x_p+1,y_p-0.5）=(x_p+1)^2+(y_p-0.5)^2-R	^2
$$
<img src="https://gitee.com/yi-junquan/image_gitee/raw/master/images/image-20220106162743077.png" alt="image-20220106162743077" style="zoom:50%;" />

若 d<0, 则取P1为下一象素，而且再下一象素的判别式为
$$
d_1=F(P_1)=F（x_p+2,y_p-0.5）=(x_p+2)^2+(y_p-0.5)^2-R	^2=d+2x_p+3
$$
 若d>=0, 则应取P2为下一象素，而且下一象素的判别式为
$$
d_2=F(P_2)=F（x_p+2,y_p-1.5）=(x_p+2)^2+(y_p-0.5)^2-R	^2=d+2(x_p-y_p)+5
$$
 第一个象素是（0,R），判别式d的初始值为
$$
d_0=F(1,R-0.5)=1.25-R
$$
<font color="red">**优化过程**</font>

:label: 有浮点数，用e=d-0.25代替：

:label: 初始值：$d_0=1.25–R 对应于e_0=1-R$

:label: 判别式：d<0对应于e<-0.25(因为e的初值e0为整数，运算过程中的增量$2(x_p-y_p)+5或2x_p+3$也为整数，故e**始终为整数**，**所以e<-0.25 等价于e<0**

经优化，程序不存在浮点数

```c
void MidPointCircle(int r, int color) {
	int x, y, d;
	x = 0;
	y = r;
	e = 1 - r;                    // 初值e=1-r
	Circlepoints (x, y, color);         // 画八分对称性的其他点
	while (x <= y) {                        // 画到直线x=y结束
		if (e < 0) e += 2 * x + 3;        // 取右侧点
		else {
			e += 2 * (x - y) + 5;    // 取右下点
			y--;
		}
		x++;
		Circlepoints (x, y, color);       // 画八分对称性的其他点
	}
}
```

<img src="https://gitee.com/yi-junquan/image_gitee/raw/master/images/image-20220106163457914.png" alt="image-20220106163457914" style="zoom:67%;" />

## 4、多边形的扫描转换与区域填充

多边形两种重要的表示方法：

> **顶点表示**：用多边形的顶点序列来表示
>
> >  直观，占内存少，便于几何变换，不能用于面着色
>
> **点阵表示**：用位于多边形内部的像素几何刻画
>
> >  便于帧缓冲器表示图形且是面着色所需的多边形表示形式

多边形的扫描转换:把多边形的**顶点表示转换为点阵表示**

出于简化的目的，这里讨论的多边形都是凸多边形

### 扫描线算法【需要完全掌握】

#### 基本思想

按扫描线顺序，计算扫描线与多边形的相交区间，再用要求的颜色显示这些区间的象素，即完成填充工作

对于一条扫描线填充过程可以分为四个步骤：求交、排序、配对、填色

![image-20220106164409919](https://gitee.com/yi-junquan/image_gitee/raw/master/images/image-20220106164409919.png)

#### 数据结构【要掌握】

:label: **活性边表(AET)**：把**与当前扫描线相交的边称为活性**边，并把它们按与扫描线交点x坐标递增的顺序存放在一个链表中，**结点内容**

>  x：当前扫描线与边的交点坐标；
>
> △x：从当前扫描线到下一条扫描线间x的增量(边的斜率)
>
> $y_{max}$：该边所交的最高扫描线号$y_{max}$。

![image-20220106164616084](https://gitee.com/yi-junquan/image_gitee/raw/master/images/image-20220106164616084.png)

:label: **新边表（NET）**：需为每条扫描线建立一个新边表，存放在该扫描线第一次出现的边。若某边的较低端点为ymin，则该边就放在扫描线ymin的新边表中:

![image-20220106165036944](https://gitee.com/yi-junquan/image_gitee/raw/master/images/image-20220106165036944.png)

假定当前扫描线与多边形某一条边的交点的X坐标为x，则下一条扫描线与该边的交点不要重计算，只要加一个增量△x。

设该边的直线方程为：ax+by+c=0；$\Delta x=\frac{-b}{a}$

#### 交点的取舍

:one: 如扫描线与多边形相交的边分别处于扫描线的两侧，则计一个交点，如P5 , P6。

:two: 如扫描线与多边形相交的边分处扫描线同侧，且yi<yi+1, yi<yi-1,则计两个交点,如P2 ；如yi>yi+1, yi>yi-1,则计0个交点,如P1 

:three: 如扫描线与多边形边界重合，则计一个交点,如边P3P4。 

只需检查顶点的两条边的另外两个端点的y值。按这两个y值中大于交点y值的个数是0,1,2来决定。

![](https://gitee.com/yi-junquan/image_gitee/raw/master/images/image-20220222222550626.png)

#### 算法过程

:one:创建新边表NET；

:two: 把新边表NET［i］中的边结点，用插入排序法插入活性边表

​    AET，使之按X坐标递增顺序排序；

:three: 遍历AET表，把配对交点之间的区间(左闭右开)上的各象素(X，Y)，用drawpixel(x,y,color)**改写象素颜色值**；

:four: 遍历AET表，把Ymax=i的结点从AET表中**删除**，并把**Ymax＞i** 的结点的**X值递增△X**；

:five: 重复各扫描线。

#### 示例

点按照逆时针来构成边

![image-20220106170627889](https://gitee.com/yi-junquan/image_gitee/raw/master/images/image-20220106170627889.png)

![image-20220106170753485](https://gitee.com/yi-junquan/image_gitee/raw/master/images/image-20220106170753485.png)

![image-20220106170804348](https://gitee.com/yi-junquan/image_gitee/raw/master/images/image-20220106170804348.png)

![image-20220106170814187](https://gitee.com/yi-junquan/image_gitee/raw/master/images/image-20220106170814187.png)

**优点**：

> 对每个像素只访问一次
>
> 与设备无关

**缺点**：

> 数据结构复杂
>
> 只适合软件实现

### 边界标志算法【了解】

#### 基本思想

帧缓冲器中对多边形的每条边进行直线扫描转换，亦即对多边形边界所经过的象素打上**标志**，然后再采用和扫描线算法类似的方法将位于多边形内的各个区段着上所需颜色

#### 实现步骤

:one: 扫描线依从左到右顺序访问该扫描线上的像素

:two: 使用一个布尔量inside来指示当前点是否在多边形内的状态:初值为假，访问到边界像素时，取反

:three: 对于被访问的像素，如inside为真，则填充颜色

```c
void edgemark_fill(polydef, color)
多边形定义  polydef；   int color;
{
	对多边形polydef每条边进行直线扫描转换；
	inside = FALSE;
	for (每条与多边形polydef相交的扫描线y )
		for (扫描线上每个象素x ) {
			if (象素 x 被打上边标志)
				inside = ! (inside);
			if (inside！ = FALSE)
				drawpixel (x, y, color);
			else drawpixel (x, y, background);
		}
}
```

–用软件实现时，扫描线算法与边界标志算法的执行速度几乎相同

–由于边界标志算法不必建立维护边表以及对它进行排序，所**更适合硬件实现**，其执行速度比有序边表算法快一至两个数量级

### 区域填充算法【了解】

区域：已表示成点阵形式的填充图形，象素集

区域可采用两种表示形式：

:one: 内点表示

> 枚举出区域内部的所有像素
>
> 内部的所有像素着同一个颜色
>
> ![image-20220106173218710](https://gitee.com/yi-junquan/image_gitee/raw/master/images/image-20220106173218710.png)

:two: 边界表示

> 枚举出边界上所有的像素
>
> 边界上的所有像素着同一颜色
>
> ![image-20220106173225468](https://gitee.com/yi-junquan/image_gitee/raw/master/images/image-20220106173225468.png)

区域填充:先将区域的一点赋予指定的颜色，然后将该颜色扩展到整个区域的过程

**区域填充算法要求区域是连通的**

**4连通**：两个像素点是上下或左右相连

**8连通**：两个像素点是上下或左右相连的，或者是对角线方向相连的

![image-20220106173424645](https://gitee.com/yi-junquan/image_gitee/raw/master/images/image-20220106173424645.png)

默认是四连通

#### 种子填充算法

**种子填充算法**：设G为一内点表示的区域，(x,y)为区域内一点，old_color为G的原色。现取(x,y)为种子点对区域G进行填充：种子象素入栈，当栈非空时，执行如下三步操作：

:one: 栈顶象素出栈

:two: 将**出栈象素置成*new*_*color*** 

:three: 按**右、上、左、下**的顺序检查与出栈象素相邻的四个象素，若其中某个象素在边界内且未置成*new*_*color* ，则把该象素入栈

![image-20220106173701989](https://gitee.com/yi-junquan/image_gitee/raw/master/images/image-20220106173701989.png)

同一个像素可能入栈多次

**解决方法**：扫描线填充算法：一次填充种子所在行；适用于边界表示的4连通区域

#### 扫描线算法

–首先填充种子点所在扫描线上的位于给定区域的一个区段

–然后确定与这一区段相连通的上、下两条扫描线上位于给定区域内的区段，并依次保存下来

–反复这个过程，直到填充结束

## 5、字符

**字符**：指数字、字母、汉字等符号

> 计算机中由一个数字编码唯一标识
>
> 国际上最流行的字符集： ASCII码，用7位二进制数进行编码表示128个字符
>
> 汉字编码的标准字符集：GB2312－80，分为94个区，94个位，每个符号由一个区码和一个位码共同标识
>
> 采用字节的最高位来标识：最高位为0表示ASCII码；最高位为1表示表示汉字编码

**字库**：为了在显示器等输出设备上输出字符，系统中必须装备有相应的字库

矢量型和点阵型

![image-20220106174505062](https://gitee.com/yi-junquan/image_gitee/raw/master/images/image-20220106174505062.png)

### 点阵字符

每个字符由一个位图表示，用矩阵（字符掩膜）表示

显示：从字库中将它的位图检索出来；将检索到的位图写到帧缓冲器中

字库的存储空间十分庞大，压缩技术：**轮廓字形法**

采用直线或Bezier曲线的集合描述字符的轮廓线，形成封闭的平面区域，外加控制信息构成字符的压缩数据

压缩比大，且能保证字符质量

### 矢量字符

矢量字符记录字符的笔画信息，具有存储空间小，美观、变换方便等优点

只需对其几何图素进行变换即可实现字符的旋转、放大、缩小等几何变换

**显示**：从字库中检索字符信息；取出端点坐标，对其进行适当的几何变换，再显示字符

## 6、裁剪

用计算机处理图形信息时，计算机内部存储的图形往往比较大，而屏幕显示的只是一部分。

**裁剪**：确定图形中哪些部分落在**显示区(剪裁窗口)**之内，以便只显示落在显示区内的那部分图形

**先裁剪再扫描**

分类：直线段裁，多边形裁剪。

![image-20220106203232713](https://gitee.com/yi-junquan/image_gitee/raw/master/images/image-20220106203232713.png)

•图形裁剪中最基本的问题：

–假设窗口为矩形，其左下角坐标为$(x_L,y_B)$，右上角坐标为$(x_R,y_T)$，对于给定点P(x,y),则P点在窗口内的条件是要满足下列不等式：
$$
xL\le x\le xR\ 且\ yB\le y \le yT
$$
否则，P点就在窗口外。

<img src="https://gitee.com/yi-junquan/image_gitee/raw/master/images/image-20220106203140325.png" alt="image-20220106203140325" style="zoom:67%;" />

### 直线段裁剪

#### 直接求交算法

直线与窗口边都写成参数形式，求参数值

将线段P0P1和矩形窗口的下边写成参数形式

![image-20220106203750851](https://gitee.com/yi-junquan/image_gitee/raw/master/images/image-20220106203750851.png)

P0P1与窗口的下边的交点满足

![image-20220106203809620](https://gitee.com/yi-junquan/image_gitee/raw/master/images/image-20220106203809620.png)

–求解得到交点对应参数对$(t_{line,}t_{edge})$，只有当它们落在[0,1]区间, $(t_{line,}t_{edge})$对应交点有效。

![image-20220106204208551](https://gitee.com/yi-junquan/image_gitee/raw/master/images/image-20220106204208551.png)

•**裁剪线段与窗口的关系**: 线段完全可见、显然不可见、其它

#### Cohen-Sutherland裁剪

##### 基本思想

对于每条线段P1P2分为三种情况处理:

:one: 若P1P2完全在窗口内，则显示该线段P1P2简称“取”之；

:two: 若P1P2明显在窗口外，则丢弃该线段，简称“弃”之；

:three: 若线段不满足“取”或 “弃”的条件，则在交点处把线段分为两段。其中一段完全在窗口外，可弃之。然后对另一段重复上述处理。

为快速判断，采用如下编码方法：

> 将裁减窗口边线两边沿长，得到九个区域，每一个区域都用一个四位二进制数标识，直线的端点都按其所处区域赋予相应的区域码，用来标识出端点相对于裁剪矩形边界的位置。

![image-20220106204447458](https://gitee.com/yi-junquan/image_gitee/raw/master/images/image-20220106204447458.png)

<img src="https://gitee.com/yi-junquan/image_gitee/raw/master/images/image-20220106204530141.png" alt="image-20220106204530141" style="zoom:67%;" />

##### 法则

:one: 若P1P2完全在窗口内code1=0,且code2=0,则“取”；

:two: 若P1P2明显在窗口外code1&code2≠0，则“弃” ；(按位取与)

:three: 在交点处把线段分为两段。其中一段完全在窗口外，可弃之。然后对另一段重复上述处理

•如何判定应该与窗口的哪条边求交呢：编码中对应位为1的边.**上下右左**

```c
if (LEFT & code != 0) {
	x = XL;
	y = y1 + (y2 - y1) * (XL - x1) / (x2 - x1);
} else if (RIGHT & code != 0) {
	x = XR;
	y = y1 + (y2 - y1) * (XR - x1) / (x2 - x1);
} else if (BOTTOM & code != 0) {
	y = YB;
	x = x1 + (x2 - x1) * (YB - y1) / (y2 - y1);
} else if (TOP & code != 0) {
	y = YT;
	x = x1 + (x2 - x1) * (YT - y1) / (y2 - y1);
}
```

**特点**：用编码方法可快速判断线段--完全可见和显然不可见。

特别适用二种场合：

> 大窗口场合(大多数对象都在窗口中)；
>
> 窗口特别小的场合(如光标拾取图形时，光标看作小的裁剪窗口。)

•最坏情形，线段求交四次，

##### 优点

> 简单，易于实现。在这个算法中求交点是很重要的，它决定了算法的速度。
>
> 编码方法可快速判断线段的完全可见和显然不可见。

##### 缺点

> 对于其他形状的窗口未必同样有效；
>
> 全部摒弃的判断只适合于那些仅在 窗口同一侧的线段。

#### 中点分割裁剪算法

对线段端点进行编码，并把线段与窗口的关系分为：

> 前两种，进行一样的处理；
>
> 第三种，用中点分割的方法求线段与窗口的交点。

–从P0点出发找出离P0最近的可见点，从P1点出发找出离P1最近可见点：两个可见点的连线就是原线段的可见部分。

![image-20220106210153895](https://gitee.com/yi-junquan/image_gitee/raw/master/images/image-20220106210153895.png)

**二分寻找最近点**

##### 算法特点

> 分辩率为$2^N*2^N$的显示器，上述二分过程至多进行N次；
>
> 主要过程只用到加法和除法运算，适合硬件实现
>
> 适合于并行计算

#### 梁友栋－Barsky算法

##### 特点

> 把**二维裁剪问题化成二次一维裁剪**问题，且把裁剪问题转化为解一组不等式的问题；
>
> 改善了Cohen-Sutherland 的编码算法中全部摒弃的判断只适合于那些仅在窗口同一侧线段的不足。

#### Nicholls-Lee-Nicholl算法[考试不作要求]

通过在剪裁窗口周围创建多个区域，在求交点之前进行更多的区域测试，从而减少求交计算。

**算法步骤**

:one: 从P1点向窗口的四个顶角点发出射线，这四条射线和窗口的四条边所在的直线一起将二维平面划分为更多的小区域；

:two: 确定P2相对于的P1位置；

:three: 计算直线段P1P2和窗口边界的交点

<img src="https://gitee.com/yi-junquan/image_gitee/raw/master/images/image-20220106211059668.png" alt="image-20220106211059668" style="zoom:67%;" />

•端点与裁剪窗口的关系：

–端点P1相对于裁剪窗口有九个可能的区域位置，只需考虑其中三个区域，其它几个区域可以利用简单变换其变换到所考虑的三个区域中的任一个中。

![](https://gitee.com/yi-junquan/image_gitee/raw/master/images/image-20220106211243716.png)

![image-20220106211548653](https://gitee.com/yi-junquan/image_gitee/raw/master/images/image-20220106211548653.png)

#### 总结

:one: 直接求交法原理简单，效率低；

:two: Cohen-Sutherland与中点法在区域码测试阶段能以位运算方式高效率地进行，因而当大多数线段能够简单的取舍时，效率较好。**中点分割线法适合于用硬件实现；**

:three: 梁友栋—Barskey算法只能应用于矩形窗口的情形，但其效率比前两者要高，这是因为运算只涉及到参数，仅到必要时才进行坐标计算；

:four: Nicholl-Lee-Nicholl算法仅适用于二维裁减。

### 多边形裁剪

多边形裁剪的解，不是它每条边裁剪的解的叠加

#### 逐边裁剪算法

分割处理策略

> 将多边形关于矩形窗口的裁剪分解为多边形关于窗口四边所在直线(裁剪器)的裁剪。

流水线过程(左上右下)

> 一个裁剪器完成一对顶点的处理后，将获得的结果立即送给下一个裁剪器。

**基本思想**

一次用窗口的一条边裁剪多边形。

窗口的一条边以及延长线构成的裁剪线把平面分成两个部分:可见一侧(包含裁减窗口的一侧)；不可见一侧(不包含裁减窗口的一侧)。

多边形的各条边的两端点S、P。它们与裁剪线的位置关系只有四种[**四个规则要记清楚**]

![image-20220106212128020](https://gitee.com/yi-junquan/image_gitee/raw/master/images/image-20220106212128020.png)

:one: 情况（1）仅输出顶点P；

:two: 情况（2）输出0个顶点；

:three: 情况（3）输出线段SP与裁剪线的交点I；

:four: 情况（4）输出线段SP与裁剪线的交点I和终点P。

![image-20220106212735928](https://gitee.com/yi-junquan/image_gitee/raw/master/images/image-20220106212735928.png)

> - 凹多边形：当裁剪后的多边形有两个或者多个分离部分时，会出现多余的直线。
> - 把凹多边形分割成若干个凸多边形，分别处理；
> - 修改本算法：沿着任何一个裁剪窗口边检查顶点表，正确的连接顶点对：采用Weiler-Atherton算法

### 字符裁剪【填空题】

•**字符裁剪问题**：字符和文本部分在窗内、部分在窗外时而出现的问题。

> **串精度**：将包围字串的外接矩形对窗口作裁剪,当字符串方框整个在窗口内时予以显示，否则不显示。
>
> **字符精度**：将包围字的外接矩形对窗口作裁剪,当字符方框整个在窗口内时予以显示，否则不显示。
>
> **笔画\象素精度**：将矢量字符的笔划分解成直线段对窗口作裁剪；将点阵字符像素相对窗口边界作取舍当字符方框整个在窗口内时予以显示，否则不显示。

![](https://gitee.com/yi-junquan/image_gitee/raw/master/images/image-20220106213232560.png)

## 7、反走样【重点】

在光栅显示器上显示图形时，直线段或图形边界或多或少会呈锯齿状：

![image-20220106220459094](https://gitee.com/yi-junquan/image_gitee/raw/master/images/image-20220106220459094.png)

–图形信号是连续的，而在光栅显示系统中，采用离散的象素表示图形；

<font color="red">**走样：用离散量表示连续量引起的失真现象**</font>

–阶梯状边界；

–图形细节失真；

狭小图形遗失：动画序列中时隐时现，产生闪烁

<font color="red">**反走样：在图形显示过程中，用于减少或消除走样现象的方法或技术**</font>

<mark>反走样几种方法</mark>

> 提高分辨率方法
>
> 非加权区域采样
>
> 加权区域采样
>
> 半色调技术

### 提高分辨率

假如把显示器分辨率（水平、垂直）提高一倍

![image-20220106221557088](https://gitee.com/yi-junquan/image_gitee/raw/master/images/image-20220106221557088.png)

帧缓存容量则增加到原来的4倍，而扫描转换同样大小的图形却要花4倍时间

方法简单，但代价非常大，不经济

### 区域采样

**改变直线段模型**

**方法步骤**：

> 将直线段看作具有一定宽度的狭长矩形；
>
> 直线段与某象素有交时，求两者相交区域的面积；
>
> 根据相交区域的面积，确定该象素的亮度值。

![image-20220106222050573](https://gitee.com/yi-junquan/image_gitee/raw/master/images/image-20220106222050573.png)

直线段对一个像素亮度的贡献：

> 与两者相交区域的面积成正比；
>
> 和像素中心点距直线段的距离成反比。

![image-20220106222542238](https://gitee.com/yi-junquan/image_gitee/raw/master/images/image-20220106222542238.png)

> 首先将屏幕象素均分成n个子象素；
>
> 然后计算中心点落在直线段内的子象素的个数k；
>
> 将屏幕该象素的亮度置为相交区域面积的近似值可k/n。

**缺点**

> 象素的亮度与相交区域的面积成正比，而与相交区域落在象素内的位置无关，仍然会导致锯齿效应；
>
> 直线条上沿理想直线方向的相邻两个象素有时会有较大的灰度差。

### 加权区域取样

相交区域对象素亮度的贡献依赖于该区域与象素中心的距离。

![image-20220106222843098](https://gitee.com/yi-junquan/image_gitee/raw/master/images/image-20220106222843098.png)

权函数可以取高斯函数(左)或恒等函数(右)

:one: 高斯函数![image-20220106223200692](https://gitee.com/yi-junquan/image_gitee/raw/master/images/image-20220106223200692.png)

:two: 取恒等函数时,加权区域采样方法退化为非加权区域采样方法

![image-20220106223215406](https://gitee.com/yi-junquan/image_gitee/raw/master/images/image-20220106223215406.png)

<img src="https://gitee.com/yi-junquan/image_gitee/raw/master/images/image-20220106223222437.png" alt="image-20220106223222437" style="zoom:67%;" />

可采用离散计算方法 

> 将象素分割成n个等面积的子象素,计算每个子象素对原象素的贡献，并保存在一张二维的加权表中；
>
> 求出所有中心落于直线段内的子象素；
>
> 计算所有这些子象素对原象素亮度贡献之和的值；
>
> 该值乘以象素的最大灰度值作为该象素的显示灰度值。

![image-20220106223745335](https://gitee.com/yi-junquan/image_gitee/raw/master/images/image-20220106223745335.png)

### 半色调技术

简单和加权区域取样技术的**前提**是多级灰度，利用多级灰度来提高视觉分辨率

:question: 如果只有两级灰度

对于给定的分辨率，通过将几个像素组合成一个单元来获得多级灰度；

例：在一个显示器中将四个像素组成一个单元，可产生5种光强。

![image-20220106223927535](https://gitee.com/yi-junquan/image_gitee/raw/master/images/image-20220106223927535.png)

用如下矩阵来表示

![image-20220106223954996](https://gitee.com/yi-junquan/image_gitee/raw/master/images/image-20220106223954996.png)

它表示黑色像素填入2´2个位置中的次序，每一级灰度再添上一个黑色像素就得到下一级灰度

> 要尽量避免连成一条直线的花样；
>
> 花样是可以选择的，单元也可以是长方形

**以牺牲空间分辨率为代价的**