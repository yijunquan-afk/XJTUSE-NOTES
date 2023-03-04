# 基于面向对象与UML的学籍管理系统设计

## 一、  题目描述

你作为一名软件系统分析员，在某一个高校的学生管理系统中负责系统的分析与设计工作，为了更快地将客户的需求进行建模，你采用了DFD的方法建立了两层数据流。

模型如下:                   ![image-20211220162453724](https://gitee.com/yi-junquan/image_gitee/raw/master/images/image-20211220162453724.png)

但是在与客户经理以及开发人员进行沟通交流时大家认为这种描述方法已过时，希望能够采用面向对象的方法来进行业务需求的建模与分析,迫于用户和开发人员的要求，你准备对现有的建模方法进行调整。

请利用UML的建模方法将该模型转换成等效的功能模型(USE CASE图，并简要描述事件流)、动态模型(活动图与分析时序图)、以及静态模型(分析类图)，数据库ER模型，注意说明并解释模型之间存在的关系，且可以根据需要进行扩展，尽量完整和细化。

## 二、  系统数据流图分析

由学籍管理系统的数据流图关联图和顶层图可以得到如下信息：

**外部实体**：学生、系办、教师

**数据处理**：1注册、2资格管理、3成绩管理、4奖励管理

**数据存储：** D1学生名册   D2 学生成绩档案 

**数据流**：  

F1注册申请：由学生流向注册 

> F2.1注册证件：由注册流向学生 
> F2.2学籍资格变动通知：由资格管理流向学生 
> F2.3课表与成绩：由成绩管理流向学生
> F2.4奖励通知：由奖励管理流向学生

> F3.1新生名单：由系办流向注册 
> F3.2审理意见：由系办流向资格管理
> F3.3课表安排：由学生成绩档案流向奖励管理 
> F3.4奖励凭证：由奖励管理流向系办

> F4.1注册统计：由注册流向系办 
> F4.2处理结果统计：由资格管理流向系办
> F4.3修课情况与成绩统计：由成绩管理流向系办
> F4.4奖励统计：由奖励管理流向系办 

F5教学安排：由成绩管理流向教师

F6学生修课成绩：由教师流向成绩管理

## 三、  系统用例分析与设计

### 1) 用例图

由数据流图可画如下用例图：

 ![](https://gitee.com/yi-junquan/image_gitee/raw/master/images/学籍用例图.png)

对于数据统计和消息统计，为了便于其扩展和使用，我将其抽象出来。数据统计包括注册统计、修课统计、成绩统计、奖励统计和资格处理统计，消息通知包括学籍资格变动通知和奖励通知。若系统此后的维护过程中需要增加新的数据统计和消息通知，也只需要在此基础上进行继承扩展即可。

### 2) 用例描述（事件流分析）

对于该学籍管理系统，我挑选了四个代表用例进行了具体的描述：注册、资格管理、上传学生成绩以及奖励管理。用例描述表如下：

![image-20211220162932310](https://gitee.com/yi-junquan/image_gitee/raw/master/images/image-20211220162932310.png)

 

 ![image-20211220162944487](https://gitee.com/yi-junquan/image_gitee/raw/master/images/image-20211220162944487.png)

 ![image-20211220163015029](https://gitee.com/yi-junquan/image_gitee/raw/master/images/image-20211220163015029.png)

![image-20211220163026369](https://gitee.com/yi-junquan/image_gitee/raw/master/images/image-20211220163026369.png)

## 四、  系统活动图分析与设计

**由数据流图可以知道系统的外部实体与数据的流动方向，而由具体的用例描述可知道用例的活动状态**，基于此，我们可以分析与构建该学籍管理系统的活动图。

对于学生的注册申请，其活动如下：

![](https://gitee.com/yi-junquan/image_gitee/raw/master/images/活动图1.png)

对于系办资格管理，活动图如下：

 ![](https://gitee.com/yi-junquan/image_gitee/raw/master/images/活动图2.png)

对于教师上传学生成绩，活动图如下：

![](https://gitee.com/yi-junquan/image_gitee/raw/master/images/活动图3.png)

对于奖励管理，活动图如下：

 ![](https://gitee.com/yi-junquan/image_gitee/raw/master/images/活动图4.png)



## 五、  系统时序图分析与设计

顺序图用来表示用例中的行为顺序。当执行一个用例行为时，顺序图中的每条消息对应了一个类操作或状态机中引起转换的事件。

顺序图展示对象之间的交互，这些交互是指在场景或用例的事件流中发生的。 顺序图属于动态建模。顺序图的重点在消息序列上，也就是说，描述消息是如何在对象间发送和接 收的。表示了对象之间传送消息的时间顺序。

由用例分析中得到的**事件流与对象**，我们可以方便地利用其进行顺序图地分析与设计。

 ![](https://gitee.com/yi-junquan/image_gitee/raw/master/images/时序图1.png)

 ![](https://gitee.com/yi-junquan/image_gitee/raw/master/images/时序图2.png)



## 六、  系统ER图设计

分析系统中存在的实体，以及其具有的属性，可以得到如下的ER图模型。

 ![](https://gitee.com/yi-junquan/image_gitee/raw/master/images/ER图UML.png)



## 七、  系统类图分析与设计

基于用例图的事件流以及ER模型的实体属性，可以分析得到如下的实体类图。

 ![](https://gitee.com/yi-junquan/image_gitee/raw/master/images/实体类图.png)

该系统的边界类图如下：

![image-20211220163717925](https://gitee.com/yi-junquan/image_gitee/raw/master/images/image-20211220163717925.png)

## 八、  参考资料

[1]吴建,郑潮,汪杰. UML基础与Rose建模案例[M].人民邮电出版社:, 201207.324.

[2]Kenneth E. Kendall,Julie E. Kendall. [M]. 系统分析与设计,第九版. 机械工业出版社,     2019.

[3]郑智红.基于UML的网络学习系统的分析和建模[J].科学技术创新,2021(21):100-101.