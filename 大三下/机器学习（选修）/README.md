[TOC]

# 机器学习复习总结

## 题型

选择20分，共10道——基础概念

填空20分，共10个空

简答和计算60分，5道12分

## 第一课 概述

### 模型

**模型**：模型是用来描述某个特定现象或事务的

归纳模型：由一个数学公式构成，每个变量都有明确物理意义

预测模型：由一个万能函数构成，每个参数一般不具备任何物理意义，一般只能模拟或预测目标系统的输出

直推模型：没有明确的模型或函数，但是可计算出模型在特定点的值

> 每个数据都是对目标世界的取样，当所在世界的取样足够全面和密集时，就获得了对这个世界的完整描述

|          | Inductive inference<br />归纳模型 | Predictive inference<br />预期模型 | Transdictive inference<br />直推模型 |
| -------- | --------------------------------- | ---------------------------------- | ------------------------------------ |
| 目标     | 发现事物的真正规律                | 发现预测规则                       | 评估未知预测函数在某些点的值         |
| 复杂度   | 比较困难                          | 相对容易                           | 最容易                               |
| 适用性   | 少数变量就能描述的简单世界        | 需要多个变量描述的复杂世界         | 需要多个变量描述的复杂世界           |
| 计算成本 | 低                                | 高                                 | 最高                                 |
| 泛化能力 | 低                                | 高                                 | 最高                                 |

### 机器学习分类

:label: **非监督学习与监督学习**

> 非监督学习：不需要训练样本的机器学习算法，如数据聚类算法。
>
> 监督学习：需要训练样本的机器学习算法,如大多数分类、回归算法。

:label: **生成模型与判别模型(generative vs. discriminative）**

> 生成模型计算数据x与标签y的联合概率P(x,y)，用下列公式计算分类概率：P(y|x) = P(x,y)/P(x)
>
> > K-means
>
> 判别模型直接计算分类概率P(y|x)
>
> > GMM

**:label: 简单数据模型与复杂数据模型**

> 简单数据模型：被用来处理相互独立的简单数据
>
> 复杂数据模型：被用来处理具有**时空关联性**的复杂数据：语音识别

### 机器学习的重要组成

<mark>**三个重要方面**</mark>

> :one: **Structural model**:我们选择哪一类函数$f(x, \Theta)$来建立模型?
>
> :two: **Error model**:我们选择哪一类损失函数（lossfunction）$L(y,f(x,\Theta))$来做训练?损失函数相当于为模型的选择制定考核标准。
>
> :three: **Optimization procedure**:我们选择哪一种数值计算方法来获取最优模型$f^*(x,\Theta)$?

![image-20220316222709490](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/qshell/image-20220316222709490.png)

## 第二课 线性回归与逻辑回归

### 线性回归

![image-20220316224043829](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/qshell/image-20220316224043829.png)

当输入变量𝑥的特征/属性数目变为𝑝时，线性回归模型表示为：
$$
f(x)=\theta_0+\theta_1x_1+...+\theta_px_p
$$
向量积表达形式为
$$
f(X)=\sum^p_{i=0}\theta_ix_i=\theta^Tx=x^T\theta\\
\theta=\begin{bmatrix}
\theta_0\\
\theta_1\\...\\
\theta_p
\end{bmatrix}
\qquad x^T=[(x_0=1),x_1,x_2,...,x_p]
$$
多元线性回归的目标是选择一个最优的𝜃，使得预测输出𝑓(𝑥)与实际输出𝑦之间的差别尽可能小

使用均方误差和来衡量损失(假设一共有n个样本)
$$
J(\theta)=\frac{1}{2}\sum^n_{i=1}(x_i^T\theta-y_i)^2
$$
线性回归目标：求解参数𝜃使损失函数$𝐽(𝜃)$的值达到最小。

几何意义：试图找到一个超平面，使所有样本到超平面上的欧式距离之和最小

**隐含假设**：误差（预测$𝜃^𝑇 𝑥_𝑖 $与真实输出𝑦𝑖 差异）服从服从**独立同分布的高斯分布**

#### 梯度下降法

梯度下降是一种计算局部最小值的一种方法。梯度下降思想就是给定一个初始值𝜃，每次沿着函数梯度下降的方向移动𝜃：

<img src="https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/qshell/image-20220316231646546.png" alt="image-20220316231646546" style="zoom: 67%;"/>

![image-20220316231407132](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/qshell/image-20220316231407132.png)

在梯度为零或趋近于零的时候收敛
$$
J(\theta)=\frac{1}{2}\sum^n_{i=1}(x_i^T\theta-y_i)^2
$$
对损失函数求偏导可得到
$$
x_i=(x_{i,0},...,x_{i,p})^T\\
x_{ij}表示第i个样本的第j个分量\\
\frac{\partial}{\theta_j}\frac{1}{2}(x_i^T\theta-y_i)^2=\frac{\partial}{\theta_j}\frac{1}{2}(\sum^p_{j=0}x_{i,j}\theta_j-y_i)^2=(\sum^p_{j=0}x_{i,j}\theta_j-y_i)x_{i,j}=(f(x_i)-y_i))x_{i,j}
\\
\nabla_\theta J=\begin{bmatrix}
\frac{J}{\theta_0}\\
\frac{J}{\theta_1}\\...\\
\frac{J}{\theta_p}
\end{bmatrix}
$$
对于只有一个训练样本的训练组而言，每走一步，𝜃𝑗(𝑗= 0,1,…,𝑝)的更新公式就可以写成：

<img src="https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/qshell/image-20220316233051758.png" alt="image-20220316233051758" style="zoom:80%;"/>

因此，当有 n 个训练实例的时候（批处理梯度下降算法），该公式就可以写为：
$$
\theta_j^{(t+1)}:=\theta_j^{(t)}-\alpha\sum^n_{i=1}(f(x_i)-y_i)x_{i,j}
$$
这样，每次根据所有数据求出偏导，然后根据特定的步长𝛼，就可以不断更新𝜃𝑗，直到其收敛。当<mark>梯度为0或目标函数值不能继续下降的时候</mark>，就可以说已经收敛，即目标函数达到局部最小值。

具体过程可以归纳如下

> :one: 初始化𝜃（随机初始化）
>
> :two: 利用如下公式更新𝜃
> $$
> \theta_j^{(t+1)}:=\theta_j^{(t)}-\alpha\sum^n_{i=1}(f(x_i)-y_i)x_{i,j}
> $$
> 其中α为步长
>
> :three: 如果新的𝜃能使𝐽(𝜃)继续减少，继续利用上述步骤更新𝜃，否则收敛，停止迭代。
>
> > 如何判断收敛？<img src="https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/qshell/image-20220316233856578.png" alt="image-20220316233856578" style="zoom:67%;"/>

**学习率α的影响**

> 小的𝛼值可以帮助找到最优解，但是收敛速度很慢
> 大的𝛼值一开始会使损失下降的较快，但会导致“锯齿”现象School  of  Software Engineering
>
> ![image-20220317092324162](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/qshell/image-20220317092324162.png)

#### 随机梯度下降法

梯度下降算法有一个最大的问题：每次更新，都要利用所有的数据，当数据量十分大的时候，这会使效率变得特别低。因此，又出现了增强梯度下降（随机梯度下降算法），<mark>每次只用训练集中的一个数据</mark>更新𝜃，即：
$$
\theta_j^{(t+1)}:=\theta_j^{(t)}-\alpha(f(x_i)-y_i)x_{i,j}
$$
在深度神经网络学习中，<mark>小批量梯度下降(mini-batch gradient decent)应</mark>用的非常广泛：把数据分为若干个批，按批来更新参数。一个批中的一组数据共同决定了本次梯度的方向，下降起来就不容易跑偏，减少了随机性。另一方面因为批的样本数与整个数据集相比小了很多，计算量也不是很大。

#### 正规方程法

将训练数据表示成矩阵形式

![image-20220317092944026](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/qshell/image-20220317092944026.png)
$$
\vec{x_1}^T=[1,x_{1,1},x_{1,2},...,x_{1,p}]
$$
<img src="https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/qshell/image-20220317093150438.png" alt="image-20220317093150438" style="zoom:67%;"/>

损失函数$𝐽(𝜃)$可变为
$$
J(\theta)=\frac{1}{2}||Y-\hat Y||^2=\frac{1}{2}||Y-X\theta||^2
$$
使用矩阵表达形式转化损失函数
$$
\begin{equation}
\begin{split}
J(\theta)&=\frac{1}{2}||Y-X\theta||^2\\
&=\frac{1}{2}(X\theta-Y)^T(X\theta-Y)\\
&=\frac{1}{2}(\theta ^TX^TX\theta-2Y^TX\theta+Y^TY)(利用了a^Tb=b^Ta求导结果)
\end{split}
\end{equation}
$$
最小化损失函数𝐽(𝜃)，可通过令梯度= 𝟎（𝑝+1维的零向量）实现:

<img src="https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/qshell/image-20220317095048551.png" alt="image-20220317095048551" style="zoom:67%;"/>

利用公式
$$
\frac{\partial x^TBx}{\partial x}=(B+B^T)x\\
\frac{\partial x^Ta}{\partial x}=\frac{\partial a^Tx}{\partial x}=a
$$
可得到
$$
\nabla_\theta J(\theta)=X^TX\theta-(Y^TX)^T=X^TX\theta-X^TY=0
$$
:mag:<mark>正规方程为：</mark>$X^TX\theta=X^TY$，得到解：$\theta^*=(X^TX)^{-1}X^TY$(假设$X^TX$可逆)

对θ再求一次梯度可得到$\nabla_\theta^2J(\theta)=X^TX$，这是<mark>半正定的</mark>，因此,若$\nabla_{\theta}J(\theta^*)=0$，则$J(\theta)$在𝜃∗处取到最小值

> :bookmark: 正定矩阵与半正定矩阵
>
> **正定矩阵**：设M是n阶方阵，如果对任何非零向量z，都有$z^TMz>0$，其中$z^T$表示z的转置，就称M为正定矩阵。正定矩阵的行列式恒为正。
>
> **半正定矩阵**：设M是n阶方阵，如果对任何非零向量z，都有$z^TMz\ge0$，其中$z^T$表示z的转置，就称M为半正定矩阵。矩阵与其转置的矩阵积是一个半正定矩阵。

当训练样本的数目𝑛大于训练样本的维度𝑝+1时，$𝑋^𝑇𝑋$在实际中通常是可逆的

当$𝑋^𝑇𝑋$可逆时，表明该矩阵是正定矩阵，求得的参数𝜃∗是全局最优解

> 矩阵可逆的充要条件：行列式不为0

当$𝑋^𝑇𝑋$不可逆时，可解出多个𝜃。可使用正则化给出一个“归纳偏好”解

> 保留所有特征，但减少θ的大小，通常使其接近于0

#### 三种求解方法的比较

| 方法名称     | 表达式                                                       | 优点               | 缺点                                               |
| ------------ | ------------------------------------------------------------ | ------------------ | -------------------------------------------------- |
| 正规方程     | $\theta^*=(X^TX)^{-1}X^TY$                                   | 有闭式解，实现简单 | 计算量大：需计算矩阵乘积及矩阵的逆，矩阵有可能奇异 |
| 梯度下降     | $\theta_j^{(t+1)}:=\theta_j^{(t)}-\alpha\sum^n_{i=1}(f(x_i)-y_i)x_{i,j}$ | 收敛、实现简单     | 通常收敛速度较慢                                   |
| 随机梯度下降 | $\theta_j^{(t+1)}:=\theta_j^{(t)}-\alpha(f(x_i)-y_i)x_{i,j}$ | 迭代成本低         | 不一定收敛到最优解                                 |

> :bookmark: 奇异矩阵：对应的行列式等于0的方阵
>
> 如果A(n×n)为奇异矩阵（singular matrix）<=> A的秩Rank(A)<n.

### 多项式回归

**多项式回归包含两个主要问题**：

:one: <mark>参数学习</mark>

> 线性回归几乎一样，仅仅是将𝑥→𝜑(𝑥)
>
> 参数仍然是基函数的线性组合

:two: <mark>确定多项式的阶数（模型评估）</mark>

> 选择不好会引起欠拟合(under-fitting)或过拟合问题(over-fitting)
>
> **欠拟合：训练误差较大；过拟合：训练误差几乎为0**
>
> ![image-20220317104922123](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/qshell/image-20220317104922123.png)



### 逻辑回归

**逻辑函数（logistic/sigmoid function，对数几率函数）**来进行替代

> 单调递增、任意阶可微
>
> 输出值在𝑧= 0 附近变化很快

$$
y=\frac{1}{1+e^{-z}}
$$

<img src="https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/qshell/image-20220318153401929.png" alt="image-20220318153401929" style="zoom:67%;" />

不难发现$f_{\theta}(x)\subseteq(0,1)$,因此可将$f_{\theta}(x)$视为样本 𝑥 作为正例的可能性， 则$1-f_{\theta}(x)$ 是反例可能性

> $\hat y$越接近1，认为是正例的可能性越大
>
> $\hat y$越接近0，认为是反例的可能性越大

![image-20220318154143225](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/qshell/image-20220318154143225.png)

合并以上两个公式，可得到伯努利公式
$$
P(y|x;\theta)=(f_{\theta}(x))^y(1-f_{\theta}(x))^{1-y}
$$
logistic回归可以被看做一种**概率模型**，且y发生的概率与 回归参数Θ有关

问题转化为求Logistic回归的最佳系数。

#### 系数求解

对Θ进行极大似然 估计，使得观测数据发生的概率最大：

![image-20220318162048288](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/qshell/image-20220318162048288.png)

转换为对数似然，有

![image-20220318162227593](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/qshell/image-20220318162227593.png)

极大似然法要求最大值，所以使用梯度上升来求

> 在求极值的问题中，有梯度上升和梯度下降两个最优化方法。
>
> 梯度上升用于求极大值，梯度下降用于求极小值。如logistic回归的目标函数 是求参向量𝜃，使得对数似然函数达到最大值，所以使用梯度上升算法。
>
> 而线性回归的代价函数：代表的则是误差，我们想求误差最小值，所以用 梯度下降算法。

梯度上升公式如下：
$$
\theta^{(t+1)}=\theta^{(t)}+\alpha \nabla_\theta \ln({L(\theta^{(t)})})
$$
**求梯度如下**：

<img src="https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/qshell/image-20220318163554005.png" alt="image-20220318163554005" style="zoom:80%;" />

得到：

![image-20220318163833614](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/qshell/image-20220318163833614.png)

与线性回归的公式很相似   $\theta_j^{(t+1)}:=\theta_j^{(t)}-\alpha\sum^n_{i=1}(f(x_i)-y_i)x_{i,j}$

### 逻辑回归与线性回归的比较

| 类别                   | 逻辑回归                                                     | 线性回归                                                     |
| ---------------------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| **输出**               | $y\in\{0,1\}$                                                | $y\in R$                                                     |
|                        | 线性二分类 ：决策边界 $𝜃^𝑇 𝑥 = 0$                            | 线性拟合 ： $𝑦 = 𝜃^𝑇 𝑥$                                      |
|                        | ![image-20220318164310873](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/qshell/image-20220318164310873.png) | ![image-20220318164319006](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/qshell/image-20220318164319006.png) |
| **自变量和因变量关系** | 无要求                                                       | 线性关系                                                     |
| **数据假设**           | P(y\|x)服从伯努利分布                                        | 𝑦 − 𝑓(𝑥)服从iid高斯分布                                      |
| **建模**               | 极大似然方法                                                 | 最小二乘方法                                                 |
| **求解**               | 梯度上升                                                     | 梯度下降                                                     |

### 模型评估

对于给定的标记数据，既要训练，又要测试，该如何做

#### 留出法

:one: 随机挑选一部分标记数据作为测试集（空心点）

:two: 其余的作为训练集（实心点），计算回归模型

:three: 使用测试集对模型评估：MSE =2.4

> MSE: Mean Square Error均方误差

![image-20220317110813810](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/qshell/image-20220317110813810.png)

:book: 测试集不能太大，也不能太小。2 <= n:m <=4

#### 交叉验证

问题：没有足够的数据留出一个测试集

方案：每一个数据既被当作训练样本也被当作测试样本

### 错误率与精度

分类任务性能度量：错误率与精度

**错误率**是分类错误的样本数占样本总数的比例

**精度**是分类正确的样本数占样本总数的比例

#### accuracy、precision与recall

对于二分类问题，有分类结果混淆矩阵

!![image-20220514213901613](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/image-20220514213901613.png)

> True Positive，即正确预测出的正样本个数
>
> False Positive，即错误预测出的正样本个数（本来是负样本，被我们预测成了正样本）
>
> True Negative，即正确预测出的负样本个数
>
> False Negative，即错误预测出的负样本个数（本来是正样本，被我们预测成了负样本）

**准确率(Accuracy)**＝(TP + TN)/总样本

> 定义是: 对于给定的测试数据集，分类器正确分类的样本数与总样本数之比

**精确率(Precision)**＝ TP /(TP + FP)，又称为**查准率**

> 预测为正的样本中有多少是真正的正样本，是**针对我们预测结果**而言的

**召回率(Recall)**＝ TP /(TP + FN)，又称为**查全率**

> 它表示：样本中的正例有多少被预测正确了，是**针对我们原来的样本**而言的

## 第三课 神经网络

### 逻辑回归的二阶段表示

#### 逻辑回归

输入与输出之间的映射关系如下
$$
P(y=1|x;w,b)=\frac{1}{1+e^{-(w^Tx+b)}}
$$
其中，𝑥为输入特征，𝑦为输出标记$y=\{0,1\}$（即二分类任务），𝒘,𝑏为学习参数。

> 𝒘是权重，b是偏值bias

#### 逻辑回归的二阶段表示

:one: **求和**summing function
$$
R^P\rightarrow R
$$

$$
z=w^Tx+b=w_1x_1+...+w_px_p+b
$$

:two: **挤压**sigmoid function
$$
R\rightarrow \{0,1\}
$$

$$
\hat y=P(y=1|x;w,b)=\frac{1}{1+e^{-z}}=\frac{e^{z}}{e^z+1}
$$

挤压函数就是之前逻辑回归学到的sigmoid function：

![image-20220319224232752](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/qshell/image-20220319224232752.png)

<img src="https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/qshell/image-20220319224911567.png" alt="image-20220319224911567" style="zoom:50%;"/>

逻辑回归是包含一个神经元的神经网络

![image-20220319230559000](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/qshell/image-20220319230559000.png)

### 神经元

神经网络包含多个神经元，输入𝒙与多个神经元相连。W需要从向量扩展为矩阵。

<mark>𝑊𝑖𝑗表示的**向量x的第𝑗个元素与向量𝑍的第𝑖个元素之间的连接权重**</mark>

![image-20220319230903851](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/qshell/image-20220319230903851.png)

对于上图的神经网络，W如下：

![image-20220319231013344](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/qshell/image-20220319231013344.png)

#### 具有一个隐藏层的神经网络

![image-20220319231727169](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/qshell/image-20220319231727169.png)

构造特征的特征

<img src="https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/qshell/image-20220319231811728.png" alt="image-20220319231811728" style="zoom:67%;"/>

h1是隐藏层的输出

:one: 多元线性回归直接建立了输入和输出的关系

:two: 逻辑回归利用一个summing function和sigmoid function建立了输入和输出的关系

:three: 神经网络利用了多个summing function和sigmoid function建立了输入和输出的关系

> 神经网络本质上就是一个复合函数
>
> 这里都是全连接

#### 非线性激活函数

引入非线性激活函数的目的是得到非线性决策面

> 不论网络多深，线性激活函数只能输出线性决策面（输出是输入的线性函数）。
>
> 非线性激活函数可以逼近任意复杂函数。

<img src="https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/qshell/image-20220319233111998.png" alt="image-20220319233111998" style="zoom:80%;"/>

当$\hat y=\frac{1}{2}$是无法进行判断的，此时使之成立的边界称为决策面

**常用的非线性激活函数**

| 名字                                   | 图形                                                         | 方程                                                         | 导数                                                         |
| -------------------------------------- | ------------------------------------------------------------ | ------------------------------------------------------------ | ------------------------------------------------------------ |
| Binary step                            | ![image-20220320103516147](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/qshell/image-20220320103516147.png) | ![image-20220320101646493](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/qshell/image-20220320101646493.png) | ![image-20220320102547211](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/qshell/image-20220320102547211.png) |
| <mark>Logistic(a.k.a Soft step)</mark> | ![Image for post](https://img-service.csdnimg.cn/img_convert/e8d11114ba5a984cb98dc2f35b127f18.png) | ![](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/qshell/image-20220320102409148.png) | ![image-20220320102646642](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/qshell/image-20220320102646642.png) |
| TanH                                   | ![Image for post](https://img-service.csdnimg.cn/img_convert/63bb680907c67f6791e6483c98ab06ba.png) | ![image-20220320102804371](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/qshell/image-20220320102804371.png) | ![image-20220320102720150](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/qshell/image-20220320102720150.png) |
| ReLU                                   | ![Image for post](https://img-service.csdnimg.cn/img_convert/a586e007d86138cadc28740d653063e1.png) | ![image-20220320102252623](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/qshell/image-20220320102252623.png) | ![image-20220320102510609](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/qshell/image-20220320102510609.png) |

Hidden layer（隐层）的个数大于1的神经网络，称为深度神经网络

### 训练神经网络

![image-20220320162142265](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/qshell/image-20220320162142265.png)

#### 损失函数

##### 二分类损失

逻辑回归中，使用对数似然度量损失（每个样本属于其真实标记的概率越大越好）

![image-20220320104901264](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/qshell/image-20220320104901264.png)

<mark>交叉熵代价函数cross entropy loss</mark>
$$
E=loss=-\log P(Y=\hat y|X=x)=-y\log(\hat y)-(1-y)\log(1-\hat y)
$$

> $y$是真实输出，$\hat y$是预测输出
>
> $\hat y =f_\theta(x)=\frac{e^{wx+b}}{e^{wx+b}+1}$

损失函数一定是一个**标量**

##### 多分类损失



![image-20220320110332270](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/qshell/image-20220320110332270.png)

Softmax函数：将各个输出节点的输出值范围映射到[0,1]，并且约束各个输出节点的输出值的和为1的函数

<mark>交叉熵代价函数cross entropy loss</mark>
$$
E=loss=-\sum_{j=1,...,K}y_j\log\hat y_j
$$

> 其中$y_j$是One-Hot向量：真实标签的位置为1，其他位置为0
>
> One-Hot编码，又称为一位有效编码，主要是采用N位状态寄存器来对N个状态进行编码，每个状态都由他独立的寄存器位，并且在任意时候只有一位有效。

例如，对于$y^T=(0 \ 1\ 0),\hat y^T=(\ 0.1\ 0.7\ 0.2)$，其损失函数为$E=-(0\times \log0.1+1\times\log0.7+0\times\log0.2)=-\log 0.7$
$$
E=loss=\frac{1}{2}||y-\hat y^2||=\frac{1}{2}\sum^K_{j=1}(y_j-\hat y_j)^2
$$

##### 回归损失

**回归问题多用来预测一个具体的数值**，如预测房价、未来的天气情况等等。例如我们根据一个地区的若干年的PM2.5数值变化来估计某一天该地区的PM2.5值大小，预测值与当天实际数值大小越接近，回归分析算法的可信度越高。

与分类网络的不同之处：输出层不再包含“Sigmoid”函数

![image-20220515100655736](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/image-20220515100655736.png)

**使用二次代价函数**
$$
E=loss=\frac{1}{2}||y-\hat y^2||=\frac{1}{2}\sum^K_{j=1}(y_j-\hat y_j)^2
$$

#### 反向传播

目标：寻找使损失达到最小的神经网络权重

如何学习神经网络权重𝑊𝐿？使用梯度下降$W_L(t+1)=W_L(t)-\eta \frac{\partial E}{\partial W_L(t)}$

> 其中$\eta$为学习率

如何得到𝑊1,…,𝑊𝐿−1层的权重？使用反向传播Backpropagation

> 重复应用微积分的链式法则
>
> 局部最小化目标函数
>
> 要求网络所有的“块”（blocks）都是可微的

假设𝑠和𝑡是两个同样维度的向量，使用𝑠∘𝑡(或𝑠⊙𝑡)来表示按元素的乘积: (𝑠⊙𝑡)𝑗= 𝑠𝑗𝑡𝑗
$$
\begin{bmatrix}
1\\2
\end{bmatrix}
\odot
\begin{bmatrix}
3\\4
\end{bmatrix}
=\begin{bmatrix}
1*3\\2*4
\end{bmatrix}=
\begin{bmatrix}
3\\8
\end{bmatrix}
$$

#### 前向传播与反向传播

![image-20220320115938109](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/qshell/image-20220320115938109.png)

**前向传播加反向传播**

| 传播方向 | 前向传播                                           | 反向传播                                |
| -------- | -------------------------------------------------- | --------------------------------------- |
| 步骤     | 1、给定一个初始的权重W                             | 1、计算误差损失$E(y,\hat y)$            |
|          | 2、输入x                                           | 2、通过链式法则（复合函数求导）计算梯度 |
|          | 3、通过复合函数$\hat y=f_3(f_2(f_1(x)))$计算预测值 |                                         |
| 更新内容 | 已知边，更新圆圈内容                               | 已知圆圈内容，更新边（权重）            |

重复迭代前向和反向步骤，直至算法收敛。

#### 反向传播的一般情形

以上通过链式法则求梯度的办法过于复杂，当隐藏层很多的时候就不适用了

![image-20220320164555551](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/qshell/image-20220320164555551.png)

![<imgsrc=""alt="image-20220320162636891"style="zoom:67%;"/>](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/qshell/image-20220320162636891.png)

第𝑙层第𝑗个神经元和第𝑙−1层神经元之间关系

![image-20220320163831160](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/qshell/image-20220320163831160.png)

k是上一层神经元的坐标

在第𝑙层第𝑗个神经元的误差如下：
$$
\delta_j^l\equiv \frac{\partial E }{\partial z_j^l}
$$

#### 反向传播方程

四个反向传播方程如下：

![image-20220320165103232](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/qshell/image-20220320165103232.png)

> 第一个反向传播方程是在计算输出层的误差
>
> 第二个反向传播方程是用L层的误差推导L-1层的误差，建立了两层之间的递推关系
>
> 第三个反向传播方程是损失函数关于任意一层偏差b的导数
>
> 第四个反向传播方程是损失函数关于任意一层权重W的导数
>
> 都是使用误差来表示![](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/qshell/image-20220320165611911.png)

#### 反向传播算法

:one:**输入𝒙**：为输入层设置对应的激活值ℎ1

**:two:前向传播**：∀𝑙=2,…,𝐿，计算$z^l=(W^{l-1})^Th^{l-1}+b^{l-1}和h^l=\sigma(z^l)$

:three:输出层误差$\delta^L$和反向误差传播$\delta^l(l=L−1,…,2)$：(BP1)和(BP2)

:four:**输出**：误差函数的梯度由(BP3)和(BP4)给出

##### 方程证明

![image-20220322213324959](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/qshell/image-20220322213324959.png)

##### BP1的证明

![](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/qshell/image-20220322212157473.png)

**粗略证明**

![](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/qshell/image-20220322210508905.png)

**对每个元素来看**

第𝑙层第𝑗个神经元和第𝑙−1层神经元之间的关系：

![image-20220322210735944](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/qshell/image-20220322210735944.png)

中间变量$\delta^l_j\equiv\frac{\partial E}{\partial z^l_j}$,称为在第𝑙层第𝑗个神经元的误差。

输出层误差的方程,$\delta^L_j(BP1)$：

![image-20220322211058990](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/qshell/image-20220322211058990.png)

<mark>输出层误差的向量表达形式,$\delta^L(BP1)$</mark>
$$
\delta^L=(h^L-y)\odot\sigma'(z^L)
$$

##### BP2的证明

![image-20220322212157473](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/qshell/image-20220322212157473.png)

使用下一层的误差$\delta^{l+1}$表示当前层误差$𝛿^𝑙(BP2)$
$$
\delta^l=\sigma'(z^l)\odot(W^l\delta^{l+1})
$$
![](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/qshell/image-20220322212630345.png)



![image-20220322213016755](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/qshell/image-20220322213016755.png)

##### BP3的证明

代价函数关于偏置的偏导$\frac{\partial E}{\partial b^{l-1}}(BP3)$：
$$
\frac{\partial E}{\partial b^{l-1}}(BP3)=\delta^l
$$
![image-20220322213630177](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/qshell/image-20220322213630177.png)

##### BP4的证明

代价函数关于权重的偏导$\frac{\partial E}{\partial W_{jk}^{l-1}}(BP4)$：
$$
\frac{\partial E}{\partial W_{jk}^{l-1}}=h^{l-1}_k\delta^l_j\\
\frac{\partial E}{\partial W^{l-1}}=h^{l-1}(\delta^l)^T
$$
![image-20220322213925444](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/qshell/image-20220322213925444.png)

#### 神经网络模型参数求解步骤

> 遍历所有数据算一次损失函数，然后计算梯度，更新梯度。每更新一次都需要将数据集中的所有样本遍历一遍，计算量大

$$
E=\frac{1}{2n}\sum_x||y^x-h^{x,L}||^2
$$



:one:输入训练样本$\{x_i,i=1,...,n\}$的集合

:two:对每个训练样本x：设置对应的激活值$h^{x,1}$

> :one:前向传播：∀𝑙=2,…,𝐿，计算$z^{x,l}=(W^{l-1})^Th^{x,l-1}+b^{x,l-1}和h^{x,l}=\sigma(z^{x,l)}$
>
> :two:输出层误差$\delta^{x,L}=(h^{x,L}-y^x)\odot\sigma'(z^{x,L})$
>
> :three:反向传播误差$\delta^{x,l}(l=L-1,...,2)=\sigma'(z^{x,l})\odot(W^l\delta^{x,l+1})$

:three:梯度下降:对每个$l=L-1,...,2$
$$
\ W^{l-1}\rightarrow W^{l-1}-\frac{\eta}{n}\sum_x h^{x,l-1}(\delta^{x,l})^T\\ b^{l-1}\rightarrow b^{l-1}-\frac{\eta}{n}\sum_x \delta^{x,l}
$$


:four:重复步骤2中的（1-3）和步骤3，直至收敛。

#### mini-batch

> 将数据分为若干个批，按照批次来更新参数，这样一个批中的一组数据共同决定了本次梯度的方向，计算量也少了许多

:one:输入训练样本$\{x_i,i=1,...,n\}$的集合

:two:For i=1 :n/m

:three:对批量数据中的每个训练样本x：设置对应的激活值$h^{x,l}$

> :one:前向传播：∀𝑙=2,…,𝐿，计算$z^{x,l}=(W^{l-1})^Th^{x,l-1}+b^{x,l-1}和h^{x,l}=\sigma(z^{x,l)}$
>
> :two:输出层误差$\delta^{x,L}=(h^{x,L}-y^x)\odot\sigma'(z^{x,L})$
>
> :three:反向传播误差$\delta^{x,l}(l=L-1,...,2)=\sigma'(z^{x,l})\odot(W^l\delta^{x,l+1})$

:four:梯度下降:对每个$l=L-1,...,2$
$$
\begin{align}
\ W^{l-1}\rightarrow W^{l-1}-\frac{\eta}{m}\sum_x h^{x,l-1}(\delta^{x,l})^T\\ b^{l-1}\rightarrow b^{l-1}-\frac{\eta}{m}\sum_x \delta^{x,l}
\end{align}
$$


:five:End i(一个epoch，迭代期)

:six:进行多个epoch循环，直至收敛重复步骤2中的（1-3）和步骤3，直至收敛。

> 首先，如果训练集较小，直接使用batch梯度下降法，样本集较小就没必要使用minibatch梯度下降法，你可以快速处理整个训练集，所以使用batch梯度下降法也很好，这里的少是说小于2000个样本，这样比较适合使用batch梯度下降法。不然，样本数目较大的话，一般的mini-batch大小为64到512，考虑到电脑内存设置和使用的方式，如果minibatch大小是2的次方，代码会运行地快一些，64就是2的6次方，以此类推，128是2的7次方，256是2的8次方，512是2的9次方。所以我经常把mini-batch大小设成2的次方。在上一个视频里，我的mini-batch大小设为了1000，建议可以试一下1024，也就是2的10次方。也有mini-batch的大小为1024，不过比较少见，64到512的mini-batch比较常见。

 

### 神经网络模型改进

#### 改进损失函数：对数似然

以逻辑回归为例

|          | 二次代价                                                     | 交叉熵                                                       |
| -------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| 损失函数 | ![image-20220322215447198](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/qshell/image-20220322215447198.png) | ![image-20220322215502698](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/qshell/image-20220322215502698.png) |
|          | ![image-20220322215518396](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/qshell/image-20220322215518396.png) | ![](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/qshell/image-20220322215518396.png) |
|          | ![image-20220322215530747](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/qshell/image-20220322215530747.png) | ![](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/qshell/image-20220322215530747.png) |
|          | ![image-20220322215545265](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/qshell/image-20220322215545265.png) | ![image-20220322215557449](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/qshell/image-20220322215557449.png) |
|          | ![image-20220322215606112](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/qshell/image-20220322215606112.png) | ![image-20220322215613491](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/qshell/image-20220322215613491.png) |

#### 示例

**任务：让输入1转化为0**

:one:二次代价：初始权重和偏置设置为2.0，此时初始输出为0.98，学习率为0.15

![image-20220322220029739](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/qshell/image-20220322220029739.png)

> 刚开始学习的速度比较缓慢，对于前150左右的学习次数，权重和偏置没有发生太大变化。随后当预测输出值远离1时，学习速度加快。
>
> :apple:注意到:apple:
> $\sigma'(z)=\sigma(z)(1-\sigma(z))$因此，当预测输出接近1的时候𝜎'(𝑧)很小，导致梯度很小，因而学习缓慢。当预测输出远离1时，学习速度加快。]
>
> ![](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/qshell/image-20220322220346472.png)
>
> 

:two:交叉熵代价：初始权重和偏置设置为2.0，此时初始输出为0.98，学习率为0.005

![image-20220322220607288](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/qshell/image-20220322220607288.png)

> 刚开始学习的速度相当快，与期待一样，当严重错误时能以最快速度学习；当预测输出接近正确输出时，学习速度变慢，也符合预期。
>
> 通过梯度公式可以看出，当预测输出与实际输出之间的差异越大时，梯度越大，因此学习速度也更快，相反当二者之间的差异较小时，学习速度变慢。因此交叉代价函数在分类问题应用更加广泛。

#### 反向传播方程

以包含两个隐藏层的三分问题为例，假设样本属于第二类i=2，使用softmax函数代替sigmoid函数

> 通常softmax会被用在网络的最后一层，用来进行最后的分类和归一化
>
> sigmoid通常用于二分类，softmax用于多分类

![image-20220322223709750](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/qshell/image-20220322223709750.png)

![image-20220322222701534](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/qshell/image-20220322222701534.png)

#### 权重初始化

随机初始化：使用Numpy的 np.random.randn函数生成均值为0，标准差为1的高斯分布

对于第一个隐层的神经元

![image-20220322224027152](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/qshell/image-20220322224027152.png)

若假设有一半的$h_k^1=1$，另一半为0，则$z_j^2$服从均值为0，标准差为$\sqrt{\frac{p}{2}+1}$的**高斯分布**

![image-20220322224228098](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/qshell/image-20220322224228098.png)

$\sigma(z_j^2)$接近于1或0，梯度很小，导致学习速度很慢

:watermelon:**改进:watermelon: **：对于任意𝑙层，使用均值为0，标准差为$\frac{1}{\sqrt{n_{in}+1}}$的高斯分布随机分布初始化权重参数$W^{l-1},b^{l-1}$。此时中间变量$z_j^l$服从均值为0，标准差为1的高斯分布：

![image-20220322224550228](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/qshell/image-20220322224550228.png)

大部分神经元的值$\sigma(z_j^l)$离0或1较远，从而学习速度快

#### 减少过拟合：dropout

随机地删除网络中的一半的隐藏神经元，同时让输入层和输出层的神经元保持不变

![image-20220322224731645](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/qshell/image-20220322224731645.png)

把输入x通过修改后的网络前向传播，然后把得到的损失结果通过修改的网络反向传播。在mini-batch 上执行完这个过程后，在<font color="red">**没有被删除**</font>的神经元上更新对应的参数（w，b）

**Dropout**

:one: 继续重复上述过程

:two: 恢复被删掉的神经元（此时被删除的神经元保持原样，而没有被删除的神经元已经有所更新）

:three: 从隐藏层神经元中随机选择一个一半大小的子集临时删除掉（备份被删除神经元的参数）。

:four: 对一小批训练样本，先前向传播然后反向传播损失并更新参数（w，b）（没有被删除的那一部分参数得到更新，删除的神经元参数保持被删除前的结果）。

#### 缓解梯度消失：ReLU

#### 梯度消失问题

对于特定的分类问题，神经网络不是层数越多越好。

假设每层的神经元个数都为1，则

![image-20220322224949592](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/qshell/image-20220322224949592.png)

若 |𝑾𝑙 |小于1，则当层数很多以后，容易导致梯度消失

#### 使用ReLU进行缓解

ReLU激活函数

![image-20220322225054711](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/qshell/image-20220322225054711.png)

当𝑧是负数的时候，梯度为0，神经元停止学习（类似于 dropout作用，减少过拟合）；当𝑧大于0时，梯度为1，可 以缓解下溢问题

## 第四课 决策树

![image-20220321164811143](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/qshell/image-20220321164811143.png)

决策树发现数据模式和规则的核心是采用**递归分割的贪婪算法**

### 决策树基本流程

:one:收集待分类的数据，这些数据的所有属性应该是完全标注的。

:two:设计分类原则，即数据的哪些属性可以被用来分类，以及如何将该属性量化。

:three:分类原则的选择，即在众多分类准则中，每一步选择哪一准则使最终的树更令人满意。

:four:设计分类停止条件，实际应用中数据的属性很多，真正有分类意义的属性往往是有限几个，因此在必要的时候应该停止数据集分裂：

> 该节点包含的数据太少不足以分裂，
>
> 继续分裂数据集对树生成的目标(例如ID3中的熵下降准则)没有贡献，
>
> 树的深度过大不宜再分。

### ID3算法

针对第三个步骤，ID3算法在决策树各个节点上使用<mark>**信息增益**</mark>准则选择特征（属性）进行数据划分，从而递归地构建决策树。

具体方法

> :one:从根节点（rootnode）开始，对节点计算所有可能的特征的信息增益，选择**信息增益最大的特征**作为节点的特征。
>
> :two:由该特征的**不同取值建立子节点**。
>
> :three:再对子节点**递归**的调用以上方法，构建决策树，直到**所有特征的信息增益均很小或没有特征**可以选择为止，最后得到一个决策树。

#### 信息熵

信息熵使信息得以量化

> 1948年，香农(ClaudeShannon)在他著名的论文“通信的数学原理”中指出：“信息是用来消除随机不确定性的东西”，并提出了“信息熵”的概念（借用了热力学中熵的概念），来解决信息的度量问题。

一条信息的<font color="red">**信息量和它的不确定性**</font>有着直接的关系

比如，要搞清楚一件非常不确定的事，或是我们一无所知的事情，就需要了解大量信息。相反，如果我们对某件事已经有了较多了解，那么不需要太多信息就能把它搞清楚

<mark>信息熵</mark>是消除不确定性所需信息量的度量，也即未知事件可能含有的信息量。需要引入消除不确定性的信息量越多，则信息熵越高，反之则越低。

例如“中国男足进军2022年世界杯决赛圈”，这个因为确定性很高，几乎不需要引入信息，因此信息熵很低。

Shannon定义的信息熵的计算公式如下：

![image-20220317113414164](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/qshell/image-20220317113414164.png)

其中X表示随机变量，随机变量的取值为{𝑥1,𝑥2,…,𝑥𝑛}，𝑃(𝑥𝑖)表示事件𝑥𝑖发生的概率，且有$\sum𝑃(𝑥𝑖)=1$。**信息熵的单位为比特（bit）**

<mark>熵越小表示概率分布的纯度越高，反之，熵越大表示概率分布的纯度越低。</mark>

##### 数据集的信息熵

设数据集D中有m个不同的类C1,C2,C3,...,Cm

设Di是数据集D中Ci类的样本的集合,|D|和|Di|分别是D和Di中的样本个数

数据集D的信息熵
$$
Info(D)=-\sum^m_{i=1}p_i\log_2p_i
$$
其中𝑝𝑖是数据集D中任意样本属于类Ci的概率，用$\frac{|D_i|}{|D|}$估计

##### 使用熵衡量数据纯度

假设有一个数据集合D，其中只有两个类，一个是正例类，一个是负例类。计算D中正例类和负例类在三种不同的组分下熵的变化情况。
（1）D中包含有50%的正例和50%的负例。
$$
Info(D) = -0.5 * \log_20.5 - 0.5 * \log_20.5 = 1
$$
（2）D中包含有20%的正例和80%的负例。
$$
Info(D) = -0.2 * \log_20.2 - 0.8 * \log_20.8 = 0.722
$$
（3）D中包含有100%的正例和0%的负例。
$$
Info(D) = -1 * \log_21 - 0 * \log_20 =0
$$
<mark>当数据变得越来越“纯”时，熵的值变得越来越小：当D中正反例比例相同时，熵取最大值；当D中所有数据属于一个类时，熵取最小值</mark>。因此，熵可以作为数据**纯净度**的衡量指标。

##### 信息增益

信息增益可以衡量划分数据集前后数据纯度提升程度。信息增益=原数据信息熵−数据划分之后的信息熵

![image-20220317113905578](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/qshell/image-20220317113905578.png)

其中，离散属性𝑎有𝐾个可能的取值{𝑎1,𝑎2,…,𝑎𝐾}，其中第𝑘个分支节点包含了𝐷中所有在属性𝑎上取值为$𝑎^𝑘$的样本，记为$𝐷^𝑘$。

![image-20220317113946167](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/qshell/image-20220317113946167.png)

#### 选择划分属性示例

以买电脑为例进行决策树划分说明

| 年龄  | 收入 | 学生 | 信用 | 买了电脑 |
| ----- | ---- | ---- | ---- | -------- |
| <30   | 高   | 否   | 一般 | 否       |
| <30   | 高   | 否   | 好   | 否       |
| 30-40 | 高   | 否   | 一般 | 是       |
| >40   | 中等 | 否   | 一般 | 是       |
| >40   | 低   | 是   | 一般 | 是       |
| >40   | 低   | 是   | 好   | 否       |
| 30-40 | 低   | 是   | 好   | 是       |
| <30   | 中   | 否   | 一般 | 否       |
| <30   | 低   | 是   | 一般 | 是       |
| >40   | 中   | 是   | 一般 | 是       |
| <30   | 中   | 是   | 好   | 是       |
| 30-40 | 中   | 否   | 好   | 是       |
| 30-40 | 高   | 是   | 一般 | 是       |
| >40   | 中   | 否   | 好   | 否       |

:one: 确立初始的信息熵

> |D|=14，|D1|=5，|D2|=9，即不买的有5个人，买的有9个人
>
> 信息熵如下：
> $$
> Info(D)=-\frac{5}{14}\log_2\frac{5}{14}-\frac{9}{14}\log_2\frac{9}{14}=0.940
> $$

:two: 确立第一次分裂的属性

:apple: 如果按照年龄划分

> 年龄<30的有5个,其中3个为“否”
>
> 年龄30-40的有4个,其中0个为“否”
>
> 年龄>40的有5个,其中2个为“否”
>
> ![image-20220321160449588](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/qshell/image-20220321160449588.png)

:banana: 如果按照收入划分

> 收入=高的有4个,其中2个为“否”
>
> 收入=中的有6个,其中2个为“否”
>
> 收入=低的有4个,其中1个为“否”
>
> ![image-20220321161051149](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/qshell/image-20220321161051149.png)

:peach: 如果按照学生划分

> 是学生的有7个,其中1个为“否”
>
> 不是学生的有7个,其中4个为“否”
>
> ![image-20220321161817228](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/qshell/image-20220321161817228.png)

:kiwi_fruit: 如果按照信用划分

> 信用好的有6个,其中3个为“否”
>
> 信用一般的有8个,其中2个为“否”
>
> ![image-20220321161851804](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/qshell/image-20220321161851804.png)

综上，“年龄”属性具有最高信息增益，成为分裂属性

:three: 确立第二次分裂的属性

![image-20220321162200499](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/qshell/image-20220321162200499.png)

按照上述方法，可以确定第二次分裂的属性为学生

:four: 划分到不可划分为止

![image-20220515154059097](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/image-20220515154059097.png)

#### 算法总结

**算法流程**

> 自上而下贪婪搜索
>
> 遍历所有的属性，按照信息增益最大的属性进行分裂
>
> 根据分裂属性划分样本
>
> 重复上述流程，直至满足条件结束

**优点**

> 分类过程和领域知识无关，几乎所有模式和场景都可以得到结果

**缺点**

> ID3算法倾向于<mark>选择属性值较多</mark>的属性，有些时候不能提供有价值的信息
>
> <mark>不适用于连续变量</mark>
>
> 只能用于分类
>
> 一次只用一个特征值进行划分
>
> 在样本量较小时，可能会导致过度分类
>
> 对<mark>属性值缺失</mark>的情况无法处理

### C4.5

**改进1：用信息增益率代替信息增益来选择属性，克服了用信息增益选择属性时偏向选择取值多的属性的不足**

**改进2：能够完成对连续值属性的离散化处理**

**改进3：能处理属性值缺失的情况**

**改进4：在决策树构造完成之后进行剪枝，一定程度上解决了过拟合问题**

#### 改进1：信息增益率

ID3采用信息增益度量。它**优先选择较多属性值的feature**，因为属性值多的feature会有相对较大的信息增益。

极端例子：对ID的分裂将产生大量划分（与样本个数一样多），每个分类只包含一个样本，且每个划分都是纯的。

![image-20220317115051893](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/qshell/image-20220317115051893.png)

C4.5使用增益率将信息增益规范化,选择**具有最大信息增益率**的属性作为分裂属性

![image-20220406222442056](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/qshell/image-20220406222442056.png)

当K越大时，则SplitInfo (𝐷,𝑎)越大，从而 GainRatio(D,a)越小，计算举例如下

| 年龄  | 收入 | 学生 | 信用 | 买了电脑 |
| ----- | ---- | ---- | ---- | -------- |
| <30   | 高   | 否   | 一般 | 否       |
| <30   | 高   | 否   | 好   | 否       |
| 30-40 | 高   | 否   | 一般 | 是       |
| >40   | 中等 | 否   | 一般 | 是       |
| >40   | 低   | 是   | 一般 | 是       |
| >40   | 低   | 是   | 好   | 否       |
| 30-40 | 低   | 是   | 好   | 是       |
| <30   | 中   | 否   | 一般 | 否       |
| <30   | 低   | 是   | 一般 | 是       |
| >40   | 中   | 是   | 一般 | 是       |
| <30   | 中   | 是   | 好   | 是       |
| 30-40 | 中   | 否   | 好   | 是       |
| 30-40 | 高   | 是   | 一般 | 是       |
| >40   | 中   | 否   | 好   | 否       |

![image-20220406222824521](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/qshell/image-20220406222824521.png)

#### 改进2：处理连续值属性

采用**二分法对连续属性进行处理**

假定连续属性a在D上出现了n个不同取值，将这些值从小到大进行排序，记为𝑎1,𝑎2,…,𝑎𝑛
取每对相邻值的中点作为可能的分裂点
$$
T_a=\{\frac{a^i+a^{i+1}}{2}|1\le i \le n-1\}
$$
基于划分点𝑡∈𝑇𝑎,将D分成两个子集D1:a≤𝑡和D2:a>𝑡（二叉树）**，选择信息增益比率最大的划分**
$$
GainRatio(D,a)=\underset{t\in T_a}{max}\frac{gain(D,a,t)}{SplitInfo(D,a,t)}
$$

举例说明：

![image-20220406223006829](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/qshell/image-20220406223006829.png)

对属性“密度”，其候选划分点集合包含16个候选值：{(0.243+0.245)/2,(0.245+0.343)/2,…，(0.774+0.719)/2}

逐一计算每个二划分对应的信息增益率，选择可以使信息增益率最大的划分

#### 改进3：处理缺失值

在某些情况下，可供使用的数据可能缺少某些属性的值，例如：

| **天气**                          | **湿度** | **有风** | **去玩** |
| --------------------------------- | -------- | -------- | -------- |
| 晴                                | 70       | 有       | 玩       |
| 晴                                | 90       | 有       | 不玩     |
| 晴                                | 85       | 无       | 不玩     |
| 晴                                | 95       | 无       | 不玩     |
| 晴                                | 70       | 无       | 玩       |
| <font color="red">**缺失**</font> | 90       | 有       | 玩       |
| 多云                              | 78       | 无       | 玩       |
| 多云                              | 65       | 有       | 玩       |
| 多云                              | 75       | 无       | 玩       |
| 雨                                | 80       | 有       | 不玩     |
| 雨                                | 70       | 有       | 不玩     |
| 雨                                | 80       | 无       | 玩       |
| 雨                                | 80       | 无       | 玩       |
| 雨                                | 96       | 无       | 玩       |

针对缺失值问题，在决策树的建模过程中，需要解决两个问题：

> :one:如何在属性值缺失的情况下进行划分属性选择？
>
> :two:给定划分属性，若样本在该属性上的值缺失，如何对样本进行划分？

$$
Gain(D,a)=F_r(Info(D)-Info_A(D))
$$

其中𝐹𝑟为属性值未缺失的实例所占比例；计算 Info(D)和 InfoA(D)时忽略属性值缺失的实例
$$
\begin{align}
Info(D)&= -8/13×log(8/13) - 5/13×log(5/13)\\&= 0.961 bits\\
Info(D)_{天气}
&= 5/13×(-2/5log(2/5) - 3/5×log(3/5))\\
&+ 3/13×(-3/3log(3/3) - 0/3×log(0/3))\\
&+ 5/13×(-3/5log(3/5) - 2/5×log(2/5))\\
&= 0.747 bits\\
Gain(D,天气)&= 13/14 ×  (0.961 - 0.747) = 0.199 bits
\end{align}
$$

计算 SplitInfo 时，将缺失的属性值当作一个正常值进行计算。本例中，当作天气有四个值，分别是晴，多云，雨，?
$$
\begin{align}
\text{SplitInfo}_{天气}（D)&=-\frac{5}{14}\times\log(\frac{5}{14})\Rightarrow\ 晴\\
&=-\frac{3}{14}\times\log(\frac{3}{14})\Rightarrow\ 多云\\
&=-\frac{5}{14}\times\log(\frac{5}{14})\Rightarrow\ 雨\\
&=-\frac{1}{14}\times\log(\frac{1}{14})\Rightarrow\ 缺失\\
&=1.809\ bits\\

\end{align}
$$

$$
GainRatio(D,天气)=Gain(D,天气)/SplitInfo_天气(D)=0.199/1.809
$$

分裂时，将属性值缺失的实例分配给所有分支，但是带一个权重

![image-20220417093315344](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/qshell/image-20220417093315344.png)

本例14个实例中共13个实例天气属性值未缺失：5个实例的天气属性为“晴”，3个实例的天气属性为“多云”，5个实例的天气属性为“雨”。

因此针对天气属性值缺失的第6个实例：估计天气是晴的概率是5/13，是多云的概率是3/13，是雨的概率是5/13

![image-20220417093619291](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/qshell/image-20220417093619291.png)

叶节点以(N/E)的形式定义，其中 N 为到达该叶节点的实例数， E 为其中属于其它分类的实例数。例如，不玩(3.4/0.4)表示3.4个实例到达“不玩”节点，其中0.4个实例不属于“不玩”

**待分类实例有缺失值，如何测试该实例属于哪个分支？**

> 对于任一实例
>
> 湿度<=75 的可能性是2.0/(2.0 +3.4)，湿度>75 的可能性是3.4/(2.0 +3.4)
>
> 当湿度<=75 时，
>
> 分类为玩的可能性= 100%|
> 分类为不玩的可能性= 0 
>
> 当湿度>75 时，
>
> 分类为玩的可能性= 0.4/3.4=12%
> 分类为不玩的可能性= 3/3.4=88%
>
> 最终分类的概率分布为：
> 玩= 2.0/5.4×100%+3.4/5.4×12%= 44%
> 不玩= 2.0/5.4×0%+3.4/5.4×88%= 56%
>
> <img src="https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/qshell/image-20220417093850315.png" alt="image-20220417093850315" style="zoom:67%;"/>

#### 改进4：通过剪枝降低过拟合

<mark>过拟合：可以完美地拟合训练数据，但是不能很好地拟合训练数据外的数据</mark>

实际应用中，当训练样本中**有噪声或训练样例的数量太少**时或缺乏代表性样本时，都容易导致机器学习模型出现过拟合问题。

上述的决策树算法增长树的每一个分支的深度，直到恰好能对训练样例比较完美地分类，会造成**决策树分支过多**，从而极有可能导致过拟合问题。

所以我们需要**通过剪枝减少过拟合**

> :one: 预剪枝（pre-pruning）：在完全正确分类训练集之前就停止树的生长
>
> 在构造决策树的同时进行剪枝。决策树通常是在无法进一步降低熵的情况下才会停止创建分枝，为了避免过拟合，可以设定一个阈值，熵减小的数量小于这个阈值，停止继续创建分枝。但是这种方法实际中的效果并不好。
>
> :two: 后剪枝（post-pruning）：由“完全生长”的树剪去子树。
>
> 剪枝的过程是对拥有同样父节点的一组节点进行检查，判断如果将其合并，熵的增加量是否小于某一阈值。如果小于阈值，则**这一组节点可以合并成一个节点**，赋予该节点关联的训练数据的最常见分类。后剪枝是**目前最普遍的做法**。

训练过程中允许对数据的过度拟合，然后再利用验证集对树进行修剪

使用留出法定义一个验证集，后剪枝的目标是通过剪枝使得在验证集上的误差率降低。

> 1. 自底向上的遍历每一个非叶节点(除了根节点)，将当前的非叶节点从树中剪去，其下所有的叶节点合并成一个节点，代替原来被剪掉的节点。
> 2. 计算剪去节点前后的分类损失，如果剪去节点之后分类损失变小，则说明该节点是可以剪去的，并将其剪去；如果发现损失并没有减少，说明该节点不可剪去，则将树还原成未剪去之前的状态。
> 3. 重复上述过程，直到所有的非叶节点(除了根节点)都被尝试。

#### C4.5总结

优点

> 通过信息增益率选择分裂属性，克服了ID3算法中通过信息增益倾向于选择拥有多个属性值的属性作为分裂属性的不足
>
> 通过将连续性属性进行离散化处理，克服ID3算法不能处理连续型数据缺陷，C4.5算法能够处理离散型和连续型的两种属性类型
>
> 能够处理具有缺失属性值的数据
>
> 构造决策树之后进行剪枝操作，解决ID3算法中可能会出现的过拟合问题

缺点

> 在构造树的过程中，需要对数据集进行多次的顺序扫描和排序，因而导致算法的低效
>
> 针对含有连续属性值的训练样本时，计算效率较低
>
> 算法在选择分类属性时没有考虑到条件属性间的相关性，只计算数据集中的每一条件属性与决策属性之间的期望信息，有可能影响到属性选择的正确性

### CART

#### Gini指标

**分类回归树（CART: Classification and Regression Tree）**

使用Gini指标度量数据集的纯度或者不确定性

在分类问题中，假设有m个类，样本点属于第i类的概率为$p_i$，使用$\frac{|D_i|}{|D|}$估计，则数据集D的纯度可以用Gini值度量：
$$
\text{Gini}(D)=1-\sum_{i=1}^{m}p_i^2
$$
例：在是否买了电脑数据集中，9个样本属于“购买电脑”，5 个样本属于“未购买电脑”
$$
\text{Gini(D)}=1-(\frac{9}{14})^2-(\frac{5}{14})^2
$$
如果D根据某个属性a被分割为K个子集𝐷1 ，𝐷2 ,…,𝐷𝐾，那么属性 a 的基尼指数(Gini Index)定义为：
$$
\text{Gini(D,a)}=|\frac{D^1}{D}|\text{Gini}{(D^1)}+……+|\frac{D^K}{D}|\text{Gini}{(D^K)}
$$
基尼指数Gini(D,a)表示基于属性a划分后的数据的不确定性。 Gini指数值越大，**不确定性**也就越大，这一点与熵的概念比较类似。Gini指数最小，划分越纯。选择具有最小Gini指数(或最大∆Gini)的属性作为分裂属性
$$
\Delta \text{Gini(D,a)}=\text{Gini(D)}-\text{Gini(D,a)}
$$
基于以上理论，通过Gini指数来确定某个特征的最优切分点(也即只需要确保切分后某点的Gini指数值最小)，这就是决策树CART算法中类别变量切分的关键所在。

#### CART算法

**CART关键点**：

:one: 决策树生成：基于训练数据集生成决策树。 CART假设决策树是**二叉树**，内部节点特征的取值为“是”和“否”，左分枝为“是”的分支，右分枝是取值为“否”的分支。 CART决策树的生成就是递归的构建二叉决策树的过程。

:two: 决策树后剪枝：用**验证数据集对已生成的树进行剪**枝并选择最优子树。

:three: CART决策树既可以用于分类也可以用于回归。

**具体步骤**

根据训练数据集，从根结点开始，递归地对每个结点进行以下操作，构建二叉决策树：

:one: 设结点的训练数据集为D，计算现有特征对该数据集的Gini指标。对每一个特征a，对其可能取的每个值$a^k $，根据“是”或“否”将D分割成$D^1$和$D^2$两部分，计算Gini指标。

:two: 在所有可能的特征a以及它们所有可能的切分点$a^k $中，选择Gini 指标最小的特征及其对应的切分点**作为最优特征与最优切分点**。依最优特征与最优切分点，从现结点生成两个子结点，将训练数据集依特征分配到两个子结点中去。

:three: 对两个子结点**递归地调用步骤1~2**，直至满足停止条件。
算法停止计算的条件是结点中的样本个数小于预定阈值，或样本集的Gini指数小于预定阈值（样本基本属于同一类）。

##### 举例说明

| 序号 | 是否有房 | 婚姻状况 | 年收入 | 是否拖欠贷款 |
| ---- | -------- | -------- | ------ | ------------ |
| 1    | yes      | single   | 125K   | no           |
| 2    | no       | married  | 100K   | no           |
| 3    | no       | single   | 70K    | no           |
| 4    | yes      | married  | 120K   | no           |
| 5    | no       | divorced | 95K    | yes          |
| 6    | no       | married  | 60K    | no           |
| 7    | yes      | divorced | 220K   | no           |
| 8    | no       | single   | 85K    | yes          |
| 9    | no       | married  | 75K    | no           |
| 10   | no       | single   | 90K    | yes          |

#### <mark>第一步，生成决策树</mark>。

对数据集属性{是否有房，婚姻状况，年收入}分别计算它们的Gini指数增益，取Gini指数增益值最大的属性作为决策树的根节点属性。

首先，使用Gini值计算数据集D的纯度：
$$
Gini(是否拖欠贷款)=1−(3/10)^2−(7/10)^2=0.42
$$
**处理离散值属性：以婚姻状况为例**，列举所有可能的子集：{Single}，{Married，Divorced}; {Divorced}，{Single, Married}; {Married}，{Single，Divorced}

考虑所有可能的二元划分，并计算划分前后的Gini指标，选择能产生最小Gini指标的子集作为分裂子集

![image-20220417100217785](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/qshell/image-20220417100217785.png)

![image-20220417100315426](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/qshell/image-20220417100315426.png)

:one:当分组为{married}|{single, divorced}时：
$$
\Delta(married)=0.42-\frac{4}{10}\times 0-\frac{6}{10}\times(1-(\frac{3}{6})^2-(\frac{3}{6})^2)=0.12
$$
:two: 当分组为{single}|{married, divorced}时:
$$
\Delta(single)=0.42-\frac{4}{10}\times 0.5-\frac{6}{10}\times(1-(\frac{1}{6})^2-(\frac{5}{6})^2)=0.053
$$
:three: 当分组为{divorced}|{single, married}时:
$$
\Delta(divorced)=0.42-\frac{2}{10}\times 0.5-\frac{8}{10}\times(1-(\frac{2}{8})^2-(\frac{6}{8})^2)=0.02
$$
对比计算结果，根据婚姻状况属性来划分根节点时取Gini**指数增益最大**的分组作为划分结果，也就是{married}|{single, divorced}。

当根据是否有房来进行划分时，Gini 系数增益计算过程为:

<img src="https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/qshell/image-20220417102602869.png" alt="image-20220417102602869" style="zoom:50%;" />
$$
Gini(无房)=1-(\frac{3}{7})^2-(\frac{4}{7})^2=0.4898\\
Gini(有房)=1-(\frac{0}{3})^2-(\frac{3}{3})^2=0\\
\Delta(是否有房)=0.42-\frac{7}{10}\times 0.4898-\frac{3}{10}\times 0=0.077
$$
**处理连续值属性**：年收入属性是一个连续的属性。需要对数据 按升序排序，然后依次用相邻值的中间值作为分隔将样本划分 为两组。例如当面对年收入为60和70这两个值时，算得中间值 为65。以中间值65作为分割点，于是则得Gini系数增益为:
$$
\Delta(年收入)=0.42-\frac{1}{10}\times (1-1)-\frac{9}{10}\times(1-(\frac{6}{9})^2-(\frac{3}{9})^2)=0.02
$$
其他值的计算同理可得，列出结果如下（最终取使得增益最大 化的那个二分准则来作为构建二叉树的准则）：

![image-20220417103116583](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/qshell/image-20220417103116583.png)

划分依据：**基尼指数增益最大**。根据计算知道，三个属性划分 根节点的增益最大的有两个： 年收入和婚姻状况，增益都为 0.12。此时，随机选择一个属性作为第一次划分。例如选择婚 姻状况进行第一次划分。

接下来，采用同样的方法，分别计算剩下属性，其中根节点的Gini指数为 （此时是否拖欠贷款的各有3个记录）：
$$
Gini(是否有拖欠贷款)=1-(\frac{3}{6})^2-(\frac{3}{6})^2=0.5
$$
与前面的计算过程类似，对于是否有房属性，可得
$$
\Delta(是否有房)=0.5-\frac{2}{6}\times 0-\frac{4}{6}\times(1-(\frac{3}{4})^2-(\frac{1}{4})^2)=0.25
$$
对于年收入 属性则有：

![image-20220417103349549](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/qshell/image-20220417103349549.png)

最后构建的CART如下图所示：

![image-20220417103415448](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/qshell/image-20220417103415448.png)

#### CART总结

| 算法 | 支持模型   | 树结构 | 特征选择   | 连续值处理 | 缺失值处理 | 剪枝   |
| ---- | ---------- | ------ | ---------- | ---------- | ---------- | ------ |
| ID3  | 分类       | 多叉树 | 信息增益   | 不支持     | 不支持     | 不支持 |
| C4.5 | 分类       | 多叉树 | 信息增益率 | 支持       | 支持       | 支持   |
| CART | 分类，回归 | 二叉树 | 基尼指数   | 支持       | 支持       | 支持   |

**CART的缺点**

1）无论是ID3, C4.5还是CART,在做特征选择的时候都是选择最优的一个特征来做分类决策，但是大多数，分类决策不应该是由某一个特征决定的，而是应该由一组特征决定的。这样决策得到的决策树更加准确。

2）如果样本发生一点点的改动，就会导致树结构的剧烈改变。这个可以通过集成学习里面的随机森林之类的方法解决。

### 决策树总结

##### 优点

简单直观，生成的决策树很直观。

基本不需要预处理，不需要提前归一化，处理缺失值。

既可以处理离散值也可以处理连续值。

可以处理多维度输出的分类问题。

相比于神经网络之类的黑盒分类模型，决策树在逻辑上可以得到很好的解释。

推理过程容易理解，决策推理过程可以表示成If Then形式

##### 缺点

决策树算法非常容易过拟合，导致泛化能力不强。可以通过设置节点最少样本数量和限制决策树深度来改进。

决策树会因为样本发生一点点的改动，就会导致树结构的剧烈改变。可以通过集成学习之类的方法解决。

寻找最优的决策树是一个NP难的问题，启发式方法容易陷入局部最优。可以通过集成学习之类的方法来改善。

有些比较复杂的关系，决策树很难学习，比如异或。可以换神经网络分类方法来解决。

## 第五课 特征选择

维数灾难：**当维数增大时，空间数据会变得更稀疏，这将导致bias和variance的增加，最后影响模型的预测效果。**

### 特征选择方法概述

:one: 任务无关方法（过滤式方法）：先过滤再训练模型

![image-20220323153224504](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/qshell/image-20220323153224504.png)

:two: 任务相关方法（包裹式方法）：从众多特征中选取子集，然后使用模型评估特征子集

![image-20220323153420531](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/qshell/image-20220323153420531.png)

:three: 自动化方法（嵌入式方法）：选择与学习融为一体，模型中暗含了子集选择

![image-20220323153610725](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/qshell/image-20220323153610725.png)

### 过滤式方法

计算代价通常很低，计算速度较快

是特征选择的general框架，与学习器无关（learner-agnostic）

是一个预处理步骤

![image-20220323155021834](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/qshell/image-20220323155021834.png)

#### 单变量过滤

**单变量**（Univariate）过滤方法：**Signal-to-noise ratio （S2N）**

一个**“好”**的特征：需要有明显的划分度——均值之间有明显的gap

![image-20220323153849565](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/qshell/image-20220323153849565.png)
$$
S2N=\frac{|\mu^+-\mu^-|}{\sigma^++\sigma^-}
$$
**单变量过滤方法一些情况下可能失效**：需要综合多个特征

![image-20220323154238320](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/qshell/image-20220323154238320.png)

#### 多变量过滤

**多变量**（ Multivariate ）过滤方法：Relief

> 给定训练集$\{(x_1,y_1),...,(x_n,y_n)\}$
>
> :one: 对每个样本xi，在同类样本中赵最近邻$x_{i,hit}$；在异类样本中寻找最近邻$x_{i,miss}$
>
> :two: 计算对应于属性j的统计量
> $$
> \delta^j = \sum_i-diff(x_i^j,x^j_{i,hit})^2+diff(x_i^j,x^j_{i,miss})^2
> $$
> :three: 若$\delta^j$大于阈值$\tau$，选择属性j；或者指定一个k值，选择统计量最大的前k 个特征
>
> 距离是结合多维的特征
>
> ![image-20220323154929587](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/qshell/image-20220323154929587.png)

### 包裹式选择（Wrapper method）

Wrapper  methods：使用学习器的性能作为评价准则

特征选择结果因学习器不同而不同，选择的特征子集是为学习器“量身定做”

从学习器性能来看，包裹式特征选择方法比过滤式方法好；但是特征选择过程中需多次训练学习器，因此**计算开销大**。

两个关键问题：

> :a:  Search 搜索特征子集的方法;
>
> :b:  Assessment 评估特征子集优劣的方法

寻找最优特征子集是一个NP难问题

:one: 使用启发式方法寻找近似候选最优子集

> - 前向选择（Forward Selection）：从空集开始，每次**增加一个最优**特征
>
>   ![image-20220323155931127](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/qshell/image-20220323155931127.png)
>
>   算法复杂度：$p+(p-1)+...+1=\frac{p(p+1)}{2}$ ， 𝑝 为属性的个数
>
> - 后向消除(Backward Elimination)：从全集开始，每次**去掉一个最差**特征
>
>   ![image-20220323160056186](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/qshell/image-20220323160056186.png)
>
>   算法复杂度：$p+(p-1)+...+1=\frac{p(p+1)}{2}$ ， 𝑝 为属性的个数

:two: 使用验证集或交叉验证方法选择最优子集。

> 1. 将数据划分成训练、验证和测试三部分
> 2. 对每一个特征子集，使用训练集训练模型
> 3. 挑选在验证集上表现最好的特征子集
> 4. 在测试集上进行测试

### :apple: 嵌入式选择（Embedded method）:apple: 

#### 正则化

在训练过程中隐式进行特征选择，模型只训练一次

只对权重正则化，不对偏置正则化

最常见的嵌入式方法：L1-正则化，  L2-正则化，以及L1和L2 混合正则化

> :one: L1-正则化
>
> ![image-20220323160758429](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/qshell/image-20220323160758429.png)
>
> L1范数（L1 norm）是指向量中各个元素绝对值之和，也有个美称叫“稀疏规则算子”（Lasso regularization）。比如 向量$A=[1\ -1\ 3]$ ， 那么A的L1范数为
>
> $|1|+|-1|+|3|=5$
>
> 
>
> :two: L2-正则化
>
> ![image-20220323160805774](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/qshell/image-20220323160805774.png)
>
> L2范数：所有元素平方和的开方
>
> :three: L1-L2混合正则化
>
> ![image-20220323160812011](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/qshell/image-20220323160812011.png)

#### 线性回归模型说明

给定训练数据集$\{(x_1,y_1),..,(x_n,y_n)\}$，并假设$\sum^n_{i=1}x_i=0$（做了中心化处理），考虑最简单的线性回归模型

> 中心化：$\overline{x}=\frac{1}{n}\sum^n_{i=1}x_i\qquad x_i'=x_i-\overline{x}$

![image-20220323161244515](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/qshell/image-20220323161244515.png)

其中$\pmb\beta 为权重向量，\beta_0为偏置向量$

![image-20220323174420570](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/qshell/image-20220323174420570.png)

当使用正规方程求解时，得到

![image-20220323161751063](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/qshell/image-20220323161751063.png)

证明如下：
$$
\begin{aligned}
&设C=\sum_{i=1}^n(y_i-\beta^Tx_i)^2-2\beta_0\sum_{i=1}^n(y_i-\beta^Tx_i)+n\beta_0^2&&\\
&则\frac{\partial C}{\partial \beta_0}=-2\sum^n_{i=1}y_i+2\pmb{\beta}\sum_{i=1}^nx_i+2n\beta_0=0\\
&由于x_i已经中心化处理过，所以\beta_0=\frac{1}{n}\sum_{i=1}^ny_i\\
&由于\pmb{X}=\begin{bmatrix}
\pmb x_1^T\\...\\
\pmb x_n^T
\end{bmatrix}\\
&\frac{\partial C}{\partial \pmb{\beta}}=\frac{\partial ||y-\pmb X\pmb \beta||^2}{\partial \pmb{\beta}}\\
&用之前求解正规方程的方法即可得到解
\end{aligned}
$$


当$n\ll p$时，很容易导致解病态、陷入过拟合

为了缓解过拟合，为误差函数增加惩罚项

![image-20220323164602681](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/qshell/image-20220323164602681.png)

##### <mark>L2正则化——岭回归</mark>

当q=2时，即使用L2范数正则化（称为ridge regression，岭回归）
$$
C=||X\pmb \beta -y||^2+\lambda||\pmb \beta||^2
$$
（正规方程法）解为
$$
\pmb{\beta}=(\pmb X^T\pmb X+\lambda \pmb I)^{-1}\pmb X^Ty
$$

> $$
> \begin{aligned}
> &设C=\sum_{i=1}^n(y_i-\pmb\beta^T\pmb x_i)^2-2\beta_0\sum_{i=1}^n(y_i-\pmb\beta^T\pmb x_i)+n\beta_0^2+\lambda||\pmb \beta||^2\\
> &则\frac{\partial C}{\partial \beta_0}=-2\sum^n_{i=1}y_i+2\pmb{\beta}^T\sum_{i=1}^nx_i+2n\beta_0=0\\
> &由于x_i已经中心化处理过，\sum_{i=1}^nx_i=0,所以\beta_0=\frac{1}{n}\sum_{i=1}^ny_i\\
> &由于\pmb{X}=\begin{bmatrix}
> \pmb x_1^T\\...\\
> \pmb x_n^T
> \end{bmatrix}\\
> &\frac{\partial C}{\partial \pmb{\beta}}=\frac{\partial (||\pmb X\pmb \beta-y||^2+\lambda||\pmb \beta||^2)}{\partial \pmb{\beta}}=\frac{\partial(\pmb\beta^T\pmb X^T \pmb X\pmb \beta-y^T\pmb X\pmb \beta -\pmb\beta^T\pmb X^Ty+y^Ty+\lambda\pmb \beta^T\beta)}{\partial \pmb{\beta}}\\
> &利用矩阵求导公式\\
> &\frac{\partial x^TBx}{\partial x}=(B+B^T)x\\
> &\frac{\partial x^Ta}{\partial x}=\frac{\partial a^Tx}{\partial x}=a\\
> &可得到\frac{\partial C}{\partial\pmb \beta}=2\pmb X^T\pmb X\beta-2\pmb X^Ty+2\lambda I\pmb \beta\\
> &由梯度下降法可知\frac{\partial C}{\partial \pmb \beta}=0\\
> &可得到\pmb\beta=(\pmb X^T\pmb X+\lambda I)^{-1}\pmb X^Ty
> \end{aligned}
> $$

梯度下降法如下：
$$
\begin{aligned}
&设J(\pmb\beta,\beta_0)=\sum_{i=1}^n(y_i-\pmb\beta^T\pmb x_i-\beta_0)^2-\lambda||\pmb \beta||^2\\
&\qquad\qquad\ \ =\sum_{i=1}^n(y_i-\pmb\beta^T\pmb x_i)^2-2\beta_0\sum_{i=1}^n(y_i-\pmb\beta^T\pmb x_i)+n\beta_0^2+\lambda||\pmb \beta||^2\\
&则有\\
&\nabla_{\beta_0}J=-2\sum^n_{i=1}y_i+2\pmb{\beta}^T\sum_{i=1}^nx_i+2n\beta_0\\
&假设x_i已经中心化处理过，\sum_{i=1}^nx_i=0(没有这个条件也只是影响到\beta_0)\\
&所以\nabla_{\beta_0}J=-2\sum^n_{i=1}y_i+2n\beta_0\\
&由于\pmb{X}=\begin{bmatrix}
\pmb x_1^T\\...\\
\pmb x_n^T
\end{bmatrix}\\
&也有\\
&\nabla_{\pmb \beta}J=2\pmb X^T\pmb X\pmb\beta-2\pmb X^Ty+2\lambda\pmb \beta\\
&设步长(学习率)为\alpha,收敛精度为ε，按照如下步骤进行梯度下降\\
&①\ 初始化𝜃(随机初始化)\\
&②\ 利用如下公式更新\pmb\beta和\beta_0\\
&\qquad\pmb\beta^{(t+1)}=\pmb\beta^{(t)}-\alpha(2\pmb X^T\pmb X\pmb\beta-2\pmb X^Ty+2\lambda\pmb \beta)\\
&\qquad\beta_0^{(t+1)}=\beta_0^{(t)}-\alpha(-2\sum^n_{i=1}y_i+2n\beta_0)\\
&③\ 如果新的\beta_0和\pmb\beta能使𝐽(\beta_0)和J(\pmb\beta)继续减少，继续利用上述步骤更新\pmb\beta和\beta_0，否则收敛，停止迭代。\\
&\qquad收敛条件判断如下\\
&\qquad||\nabla_{\pmb\beta}J||< ε\\
&\qquad||\nabla_{\beta_0}J||< ε
\end{aligned}
$$


岭回归等价于

![image-20220323164822746](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/qshell/image-20220323164822746.png)

上式更加明显的表示出了𝜷的约束条件。可以证明 𝑡  和𝜆  之间 存在一一对应关系

直观对比线性回归和岭回归，可以看到<mark>参数进行了缩减</mark>

> 这样能使噪声Δx对实际的β影响较小
> $$
> \beta(x+\Delta x)=\beta x+\beta\Delta x
> $$

![image-20220323203625787](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/qshell/image-20220323203625787.png)

q 取不同值时，正则化项的等值线

![image-20220323204213598](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/qshell/image-20220323204213598.png)

##### L1正则化——Lasso回归

L1- 正则化（Least Absolute  Shrinkage and  Selection  Operator, Lasso回归）

![image-20220323204333074](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/qshell/image-20220323204333074.png)

与其等价的拉格朗日表达形式

![image-20220323204404123](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/qshell/image-20220323204404123.png)

L1约束使得解关于 𝒚  非线性，而且不能像岭回归那样可以得到封闭解。

Lasso回归:

以一个特例$X^TX=I$进行说明

<img src="https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/qshell/image-20220323210833285.png" alt="image-20220323210833285" style="zoom:80%;float:left" />

<img src="https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/qshell/image-20220323210953272.png" alt="image-20220323210953272" style="zoom:80%;float:left" />

> 比如，如果原本线性回归的$\beta_j=\frac{\lambda}{4}$，则利用L1正则化使$\beta_j=0$
>
> 比如，如果原本线性回归的$\beta_j=\lambda$，则利用L1正则化使$\beta_j=\frac{\lambda}{2}$
>
> 比如，如果原本线性回归的$\beta_j=-\lambda$，则利用L1正则化使$\beta_j=-\frac{\lambda}{2}$
>
> 向中间的0靠拢
>
> 这样能使权重矩阵中在$(-\frac{\lambda}{2},\frac{\lambda}{2})$的$\beta$置为0，从而达到<mark>特征选择的作用</mark>
>
> 以上说明，L1正则化模型是一种嵌入式特征选择方法，将学习器和特征选择融为一体

#### 总结

𝑳𝒒正则化是实现嵌入式特征选择的常用方法

<img src="https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/qshell/image-20220323212527957.png" alt="image-20220323212527957" style="zoom:67%;" />

对于一般的学习任务，不希望权重过大

> 如果权重过大，导致微小的特征变化引起较大的预测变化
>
> 倾向于零权重，从而消除与任务无关的特征

## 第六课 降维

降维dimensionality reduction：**通过某种数学变换$z_i=g_{\theta}(x_i)$将原始高维属性空间转变为一个低维空间。**

![image-20220514151305487](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/image-20220514151305487.png)

降维可以增加计算效率，提高计算精度。在数据压缩，噪音消除、图像识别等领域有着广泛的应用。

事实上，很多高维数据都处在一个低维超平面上，其本质维数都非常低！！！

**线性降维方法**

> **PCA(Principal Component Analysis)：主成分分析**
>
> FA(Factor Analysis) 因子分析
>
> CCA(Canonical correlation analysis)典型相关性分析

**非线性降维方法**

> Kernel PCA
>
> LLE(Locally Linear Embedding) 
>
> Isomap (Isometric mapping)
>
> t-SNE：画图降维

 数据居中处理：$\pmb{x_i}=\pmb{x_i-\overline{x}\qquad\overline{x}}=\frac{1}{m}\sum_{i=1}^mx_i$

> 中心化以后:$\sum_i\pmb x_i=0$

:two: 选择一组**长度为1的正交基** (𝒘𝟏, ⋯ , 𝒘𝒌)——<mark>k<p 时，就是降维处理</mark>
$$
W=\begin{bmatrix}w_1,w_2,\cdots,w_k\end{bmatrix}
$$

$$
W=\begin{bmatrix}1&0&\cdots&0\\
0&1&\cdots&0\\
\vdots&\vdots&\ddots&\vdots\\
0&0&\cdots&1
\end{bmatrix}\in R^{p\times k}
$$

:three: 点在新坐标系下(新的基向量)的坐标通过点乘表示
$$
\pmb{z_i}=(z_{1i},...,z_{ki})^T,\ z_{ji}=\pmb{w_j^Tx_i}\\
例如z_{1i}就是x_i在w_1下的坐标，z_{2i}就是x_i在w_2下的坐标\\
Z=\begin{bmatrix}z_1,\cdots,z_m\end{bmatrix}\in R^{k\times m}
$$
:four: 在新基下的数据表示（重构）
$$
\hat x_i=\overline{x}+\sum_{j=1}^kz_{ji}w_j=\overline{x}+Wz_i=\overline{x}+WW^Tx_i
$$

![image-20220514154753995](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/image-20220514154753995.png)

举例说明：
$$
\begin{align}
x&=(x^Tu_1)u_1+(x^Tu_2)u_2+\cdots+(x^Tu_p)u_p\\
&=(x^Tv_1)v_1+(x^Tv_2)v_2+\cdots+(x^Tv_p)v_p\\
&\approx(x^Tv_1)v_1+\cdots+(x^Tv_k)v_k\\
\end{align}
$$

### PCA

PCA基本思想：**构造原始特征的一系列线性组合形成的线性无关低维特征，以去除数据的相关性，并使降维后的数据最大程度地保持原始高维数据的<mark>方差信息</mark>**。

第k个主成分的目标函数$w_k$的最大值为第k大特征值对应的特征向量

所以综合k个主成分，可以得到最终的<mark>目标函数</mark>：
$$
\underset{w\in R^{p\times k}}{\max}tr(W^T(\frac{1}{m}XX^T)W),W^TW=I_k
$$

> 矩阵的迹，数学、线性代数名词，在线性代数中，一个n×n矩阵A的**主对角线（从左上方至右下方的对角线）上各个元素的总和**被称为矩阵A的迹（或迹数），一般记作tr(A)。

PCA主方向 = 数据协方差矩阵的特征向量

更大特征值对应的特征向量更加重要

**降维的结果**为：$Z=W^TX\Rightarrow R^{k\times p}\times R^{p\times m}\Rightarrow R^{k\times m}$

**重构的结果**为：$\hat X=WZ=WW^TX\Rightarrow R^{p\times k}\times R^{k\times m}\Rightarrow R^{p\times m}$

#### <mark>PCA算法步骤</mark>

目标：计算数组k个主方向

:one: 数据居中

:two: 计算居中数据的协方差矩阵

:three: 计算协方差矩阵最大k个特征值对应的特征向量，组成矩阵$W\in R^{p\times k}$

输出降维结果：$Z=W^TX$

降维后，信息有所损失。但是如果特征值非常小，损 失的信息很少

#### <mark>举例说明</mark>

![image-20220514165533119](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/image-20220514165533119.png)

$c_1$归一化后得到W即$[1/\sqrt2,1/\sqrt2]^T$

重构后：
$$
X=WY=\begin{bmatrix}-\frac{3}{2}&-\frac{1}{2}&0&\frac{3}{2}&-\frac{1}{2}\\-\frac{3}{2}&-\frac{3}{2}&0&\frac{3}{2}&-\frac{1}{2}\end{bmatrix}
$$

### 最小投影距离

目标函数：最小化$x_i$与$\hat x_i$之间的距离
$$
\frac{1}{m}\sum_{i=1}^m||x_i-\sum_{j=1}^kz_{ji}w_j||^2
$$
最小原数据与投影数据间距离与最大投影方差等价

### SVD分解

$XX^T$非常大，通常大于10000

通过使用SVD进行加速

> 加速原理：通过计算$X^TX$的特征值和特征向量，得到$XX^T$的特征向量，即特征脸。

奇异值分解：

对于任意一个p×m的矩阵A，不妨假设p>m（维度大于个数）， 它可以被分解为
$$
A=U\Sigma V^T
$$
<img src="https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/image-20220514172533278.png" alt="image-20220514172533278" style="zoom:80%;float:left" />

![image-20220514172633306](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/image-20220514172633306.png)

奇异值分解得到的矩阵U,Σ,V 满足如下性质:

> U是$AA^T$所有特征向量组成的矩阵
>
> ∑中对角线元素的值是矩阵$A^TA$特征的平方根 
>
> V是$A^TA$所有特征向量组成的矩阵

<img src="https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/image-20220514172954571.png" alt="image-20220514172954571" style="zoom:80%;float:left" />

奇异值分解可以理解为在空间内找到一组正交基𝑣𝑖 ，通 过矩阵乘法将这组正交基映射到像空间中，其中奇异值对应伸缩系数。

![image-20220514172932220](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/image-20220514172932220.png)

#### 基于SVD——奇异值分解

$X^TX$是$m\times m$维的，比$XX^T$小得多

:one: 输入数据矩阵X (p行，m列, p > m);

:two: 对𝑿进行居中

:three: 计算$X^TX$的前k个特征值𝝀𝟏, ⋯ , 𝝀𝒌和特征向量矩阵𝑽

:four: 计算投影矩阵(𝑼𝟏, 𝑼𝟐, … 𝑼𝒌 ), 其中$U_i=XV_i/\sqrt \lambda_i$

:five: 降维结果为$Z=U^TX$

![image-20220514174403312](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/image-20220514174403312.png)

所以，当p<m时使用原本的PCA算法就行，当p>m(维度大于样本数)时使用基于SVD的PCA算法比较好

### 数据预处理

使用PCA，可以同时去除变量之间的线性关系以及对数据进行归一化

:one: 假设数据$\{x_1,x_2,...,x_m\}$的协方差矩阵为$S=\frac{1}{m}\sum_{i=1}^m(x_i-\overline x)(x_i-\overline x)^T$

:two: 利用$W^TSW=\Lambda$，定义一个变换
$$
y_i=\Lambda^{-\frac{1}{2}}W^T(x_i-\overline x)
$$

$$
\Lambda=\begin{bmatrix}\lambda_1&\cdots &0
\\\vdots&\ddots&\vdots\\\
0 &\dots& \lambda_p\end{bmatrix}
$$

:three:则$\{y_1,y_2,...,y_m\}$的均值为0，y的协方差为单位矩阵。

该操作称为数据白化（whitening）操作

再用一下之前的例子：

#### <mark>举例说明</mark>

![image-20220514165533119](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/image-20220514165533119.png)
$$
y=\Lambda^{-\frac{1}{2}}W^TX=
\begin{bmatrix}
\frac{1}{\sqrt 2} & 0
\\0&\frac{1}{\sqrt{2/5}}
\end{bmatrix}
\begin{bmatrix}
\frac{1}{\sqrt 2} & -\frac{1}{\sqrt 2}
\\\frac{1}{\sqrt 2}&\frac{1}{\sqrt 2}
\end{bmatrix}^TX
$$
<mark>矩阵的能量是指</mark>
$$
\frac{\lambda_1+...+\lambda_k}{\lambda_1+...+\lambda_p}
$$
通常保留矩阵 80% ～ 90% 的能量，就可以得到重要的特征并去除噪声。

## 第七课 支持向量机

### 线性可分SVM

## 第八课 贝叶斯分类器

