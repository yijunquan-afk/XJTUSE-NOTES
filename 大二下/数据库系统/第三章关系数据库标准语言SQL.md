[TOC]

# [XJTUSE DATABASE]——第三章 关系数据库标准语言SQL

## 一、SQL概述

:one: SQL(Structured Query Language)

> 结构化查询语言，是关系数据库的标准语言
>
> SQL是一个通用的、功能极强的关系数据库语言

:two: SQL的产生与发展

> 1972：IBM开始研究System R系统，配置了数据库语言SQUARE
>
> 1974：Boyce和Chamberlin将SQUARE修改为SEQUEL，后简称为SQL (Structured Query Language)
>
> 1970s末起：主流数据库厂商纷纷支持SQL
>
> Oracle、DB2、Sybase

:three: SQL的特点

> 综合统一
>
> 高度非过程化
>
> 面向集合的操作方式
>
> 一种语法结构多种使用方式
>
> > 既是独立的语言，又是**嵌入式语言**
>
> 语言简洁易学  

![image-20220128105205441](https://raw.githubusercontent.com/yijunquan-afk/img-bed-1/main/imges3/image-20220128105205441.png)

:four: 基本表

> SQL中一个关系就对应一个基本表
>
> 本身独立存在的表
>
> 一个(或多个)基本表对应一个存储文件
>
> 一个表可以带若干索引

:five: 存储文件

> 逻辑结构组成了关系数据库的内模式
>
> 物理结构对用户是隐蔽的

## 二、数据定义

![image-20220128105506212](https://raw.githubusercontent.com/yijunquan-afk/img-bed-1/main/imges3/image-20220128105506212.png)

### 模式的定义与删除

#### 定义模式

定义模式实际上**定义了一个命名空间。**

在这个空间中可以定义该模式包含的数据库对象例如基本表、视图、索引等。

在CREATE SCHEMA中可以接受CREATE TABLE，CREATE VIEW和GRANT子句。

CREATE SCHEMA <模式名> AUTHORIZATION <用户名>[<表定义子句>|<视图定义子句>|<授权定义子句>]

示例：为用户ZHANG创建了一个模式TEST，并且在其中定义一个表TAB1

```sql
CREATE SCHEMA TEST AUTHORIZATION ZHANG
CREATE TABLE TAB1 ( COL1 SMALLINT,
					COL2 INT,
                  	COL3 CHAR(20),
                   	COL4 NUMERIC(10,3),
                   	COL5 DECIMAL(5,2)  );
```

#### 删除模式

DROP SCHEMA <模式名> <CASCADE|RESTRICT>

CASCADE(级联)

> 删除模式的同时把该模式中所有的数据库对象全部删除

RESTRICT(限制)

> 如果该模式中定义了下属的数据库对象(如表、视图等)，则拒绝该删除语句的执行。
>
> 仅当该模式中没有任何下属的对象时才能执行。

```sql
示例：
DROP SCHEMA ZHANG CASCADE
删除模式ZHANG 同时该模式中定义的表TAB1也被删除
```

### 基本表的定义、删除与修改

定义基本表

```sql
CREATE TABLE <表名>
(<列名> <数据类型>[ <列级完整性约束条件> ]
,<列名> <数据类型>[ <列级完整性约束条件>] 
…
[,<表级完整性约束条件> ] );
```

> <表名>：所要定义的基本表的名字
>
> <列名>：组成该表的各个属性(列)
>
> <列级完整性约束条件>：涉及相应属性列的完整性约束条件
>
> <表级完整性约束条件>：涉及一个或多个属性列的完整性约束条件
>
> 如果完整性约束条件**涉及到该表的多个属性列，则必须定义在表级**上，否则既可以定义在列级也可以定义在表级。

示例：学生—课程数据库

> 学生-课程模式 S-T 
>
> 学生表：Student(Sno,Sname,Ssex,Sage,Sdept)
>
> 课程表：Course(Cno,Cname,Cpno,Ccredit)
>
> 学生选课表：SC(Sno,Cno,Grade)

<img src="https://raw.githubusercontent.com/yijunquan-afk/img-bed-1/main/image-20220128110216136.png" alt="image-20220128110216136" style="zoom:67%;" />

:one: **建立“学生”表Student。学号是主码，姓名取值唯一**

```sql
CREATE TABLE Student(Sno CHAR(9) PRIMARY KEY,  /* 列级完整性约束条件,Sno是主码*/
					 Sname CHAR(20) UNIQUE, /* Sname取唯一值约束 */
					 Ssex CHAR(2),
					 Sage SMALLINT,
					 Sdept CHAR(20)
					 );
```

:two: **建立课程表Course**

```sql
CREATE TABLE Course(Cno CHAR(4) PRIMARY KEY,
                    Cname CHAR(40) NOT NULL, /*列级完整性约束，Cname不能取空值*/
                    Cpno CHAR(4),/*表示先修课*/
                    Ccredit SMALLINT
                    FOREIGN KEY (Cpno) REFERENCES Course(Cno) 
					/*表级完整性约束，Cpno是外码，被参照表是Course, 被参照列是Cno*/
                   );
```

:three: **建立选课表SC**

```sql
CREATE TABLE SC(Sno CHAR(9),
                Cno CHAR(4),
                Grade SMALLINT，
                PRIMARY KEY (Sno,Cno),/* 主码由两个属性构成，必须作为表级完整性进行定义*/
                FOREIGN KEY (Sno) REFERENCES Student(Sno),
                /* 表级完整性约束条件，Sno是外码，被参照表是Student */
                FOREIGN KEY (Cno)REFERENCES Course(Cno)
                /* 表级完整性约束条件， Cno是外码，被参照表是Course*/
               );
```

#### 数据类型

SQL中域的概念用数据类型来实现

定义表的属性时需要指明其数据类型及长度

选用哪种数据类型

> 取值范围
>
> 要做哪些运算

| 数据类型                           | 描述                                                         |
| :--------------------------------- | :----------------------------------------------------------- |
| CHARACTER(n)                       | 字符/字符串。固定长度 n。                                    |
| VARCHAR(n) 或 CHARACTER VARYING(n) | 字符/字符串。可变长度。最大长度 n。                          |
| BINARY(n)                          | 二进制串。固定长度 n。                                       |
| BOOLEAN                            | 存储 TRUE 或 FALSE 值                                        |
| VARBINARY(n) 或 BINARY VARYING(n)  | 二进制串。可变长度。最大长度 n。                             |
| INTEGER(p)                         | 整数值（没有小数点）。精度 p。                               |
| SMALLINT                           | 整数值（没有小数点）。精度 5。                               |
| INTEGER                            | 整数值（没有小数点）。精度 10。                              |
| BIGINT                             | 整数值（没有小数点）。精度 19。                              |
| DECIMAL(p,s)                       | 精确数值，精度 p，小数点后位数 s。例如：decimal(5,2) 是一个小数点前有 3 位数，小数点后有 2 位数的数字。 |
| NUMERIC(p,s)                       | 精确数值，精度 p，小数点后位数 s。（与 DECIMAL 相同）        |
| FLOAT(p)                           | 近似数值，尾数精度 p。一个采用以 10 为基数的指数计数法的浮点数。该类型的 size 参数由一个指定最小精度的单一数字组成。 |
| REAL                               | 近似数值，尾数精度 7。                                       |
| FLOAT                              | 近似数值，尾数精度 16。                                      |
| DOUBLE PRECISION                   | 近似数值，尾数精度 16。                                      |
| DATE                               | 存储年、月、日的值。                                         |
| TIME                               | 存储小时、分、秒的值。                                       |
| TIMESTAMP                          | 存储年、月、日、小时、分、秒的值。                           |
| INTERVAL                           | 由一些整数字段组成，代表一段时间，取决于区间的类型           |

#### 模式与表

每一个基本表都属于某一个模式

一个模式包含多个基本表

定义基本表所属模式

> :one: 方法一：在表名中明显地给出模式名
>
> ```sql
> Create table"S-T".Student(......); /*模式名为 S-T*/
> Create table "S-T".Cource(......);
> Create table "S-T".SC(......);
> ```
>
> :two: 方法二：在创建模式语句中同时创建表
>
> :three: 方法三：设置所属的模式

创建基本表(其他数据库对象也一样)时，若没有指定模式，系统根据搜索路径来确定该对象所属的模式

关系数据库管理系统会使用模式列表中第一个存在的模式作为数据库对象的模式名

若搜索路径中的模式名都不存在，系统将给出错误

显示当前的搜索路径： `SHOW search_path;`

搜索路径的当前默认值是：`$user， PUBLIC`

数据库管理员用户可以设置搜索路径，然后定义基本表

```sql
SET search_path TO "S-T",PUBLIC;
Create table Student(......);
```

结果建立了S-T.Student基本表。关系数据库管理系统发现搜索路径中第一个模式名S-T，就把该模式作为基本表Student所属的模式。

#### 修改基本表

```sql
ALTER TABLE <表名>
[ ADD[COLUMN] <新列名> <数据类型> [ 完整性约束 ] ]
[ ADD <表级完整性约束>]
[ DROP [ COLUMN ] <列名> [CASCADE| RESTRICT] ]
[ DROP CONSTRAINT<完整性约束名>[ RESTRICT | CASCADE ] ]
ALTER COLUMN <列名><数据类型> ] ; 
```

<表名>是要修改的基本表

:label: **ADD**子句用于增加新列、新的列级完整性约束条件和新的表级完整性约束条件

:label: **DROP COLUMN**子句用于删除表中的列

> 如果指定了CASCADE短语，则自动删除引用了该列的其他对象
>
> 如果指定了RESTRICT短语，则如果该列被其他对象引用，数据库管理系统将拒绝删除该列

:label: **DROP CONSTRAINT**子句用于删除指定的完整性约束条件

:label: **ALTER COLUMN**子句用于修改原有的列定义，包括修改列名和数据类型

:key: 示例：向Student表增加“入学时间”列，其数据类型为日期型

```sql
ALTER TABLE Student ADD S_entrance DATE;
```

不管基本表中原来是否已有数据，新增加的列一律为空值

:key: 示例：将年龄的数据类型由字符型(假设原来的数据类型是字符型)改为整数

```sql
ALTER TABLE Student ALTER COLUMN Sage INT;
```

#### 删除基本表

```sql
DROP TABLE <表名>［RESTRICT| CASCADE］;
```

> RESTRICT：删除表是有限制的。
>
> > 欲删除的基本表不能被其他表的约束所引用
> >
> > 如果存在依赖该表的对象，则此表不能被删除
>
> CASCADE：删除该表没有限制。级联删除
>
> > 在删除基本表的同时，相关的依赖对象一起删除

示例：删除Student表

```sql
DROP TABLE Student CASCADE;
```

基本表定义被删除，数据被删除,表上建立的索引、视图、触发器等一般也将被删除

### 索引的建立与删除

建立索引的目的：**加快查询速度**

关系数据库管理系统中常见索引：

> 顺序文件上的索引
>
> B+树索引
>
> 散列(hash)索引
>
> 位图索引

特点：

> B+树索引具有动态平衡的优点
>
> HASH索引具有查找速度快的特点

```sql
CREATE [UNIQUE] [CLUSTER] INDEX <索引名> ON <表名>(<列名>[<次序>][,<列名>[<次序>] ]…);
<表名>：要建索引的基本表的名字
索引：可以建立在该表的一列或多列上，各列名之间用逗号分隔
<次序>：指定索引值的排列次序，升序：ASC，降序：DESC。缺省值：ASC
UNIQUE：此索引的每一个索引值只对应唯一的数据记录
CLUSTER：表示要建立的索引是聚簇索引
```

#### 建立索引

为学生-课程数据库中的Student，Course，SC三个表建立索引。Student表按学号升序建唯一索引，Course表按课程号升序建唯一索引，SC表按学号升序和课程号降序建唯一索引

```sql
CREATE UNIQUE INDEX Stusno ON Student(Sno);
CREATE UNIQUE INDEX Coucno ON Course(Cno);
CREATE UNIQUE INDEX SCno ON SC(Sno ASC,Cno DESC);
```

#### 修改索引

```sql
ALTER INDEX <旧索引名> RENAME TO <新索引名>；
将SC表的SCno索引名改为SCSno
ALTER INDEX SCno RENAME TO SCSno; 
```

#### 删除索引

```sql
DROP INDEX <索引名>;
```

### 数据字典

数据字典是关系数据库管理系统内部的一组系统表，它记录了数据库中所有定义信息：关系模式定义、视图定义、索引定义、完整性约束定义、各类用户对数据库的操作权限、统计信息等

## 三、数据查询【重点】

```sql
SELECT [ALL|DISTINCT] <目标列表达式>[,<目标列表达式>] …
FROM <表名或视图名>[,<表名或视图名> ]…|(SELECT 语句)[AS]<别名>
[ WHERE <条件表达式> 
[ GROUP BY <列名1> [ HAVING <条件表达式> ] ]
[ ORDER BY <列名2> [ ASC|DESC ] ];
```

:label: **SELECT子句**：指定要显示的属性列

:label: **FROM子句**：指定查询对象(基本表或视图)

:label: **WHERE子句**：指定查询条件

:label: **GROUP BY子句**：对查询结果按指定列的值分组，该属性列值相等的元组为一个组。通常会在每组中作用聚集函数。

:label: **HAVING短语**：只有满足指定条件的组才予以输出

:label: **ORDER BY子句**：对查询结果表按指定列值的升序或降序排序

### 单表查询

> 学生表：Student(Sno,Sname,Ssex,Sage,Sdept)
>
> 课程表：Course(Cno,Cname,Cpno,Ccredit)
>
> 学生选课表：SC(Sno,Cno,Grade)

![image-20230503183004888](https://raw.githubusercontent.com/yijunquan-afk/img-bed-1/main/image-20230503183004888.png)

#### 1、查询指定列

**查询全体学生的学号与姓名。**

```sql
SELECT Sno,Sname
FROM Student;
```

**查询全体学生的姓名、学号、所在系。**

```sql
SELECT Sname,Sno,Sdept
FROM Student;
```

#### 2、查询全部列

> 在SELECT关键字后面列出所有列名
>
> 将<目标列表达式>指定为 *

**查询全体学生的详细记录**

```sql
SELECT Sno,Sname,Ssex,Sage,Sdept
FROM Student;
或
SELECT *
FROM Student;
```

#### 3、查询经过计算的值

SELECT子句的<目标列表达式>不仅可以为表中的属性列，也可以是表达式

**查全体学生的姓名及其出生年份。**

```sql
SELECT Sname,2014-Sage/*假设当时为2014年*/
FROM Student;
```

**查询全体学生的姓名、出生年份和所在的院系，要求用小写字母表示系名**。

```sql
SELECT Sname,'Year of Birth: ',2014-Sage,LOWER(Sdept)
FROM Student;
```

<img src="https://raw.githubusercontent.com/yijunquan-afk/img-bed-1/main/imges3/image-20220128113618839.png" alt="image-20220128113618839" style="zoom:50%;" />

#### 4、消除取值重复的行

指定**DISTINCT**关键词，去掉表中重复的行

```sql
SELECT DISTINCT Sno
FROM SC;
```

#### 5、查询满足条件的元组

<img src="https://raw.githubusercontent.com/yijunquan-afk/img-bed-1/main/imges3/image-20220128113909794.png" alt="image-20220128113909794" style="zoom:67%;" />

##### 1) 确定大小

:one: 查询软件学院全体学生的名单。

```sql
SELECT Sname
FROM Student
WHERE Sdept=‘SE’;
```

:two: 查询所有年龄在20岁以下的学生姓名及其年龄。

```sql
SELECT Sname,Sage
FROM Student
WHERE Sage < 20;
```

:three: 查询考试成绩有不及格的学生的学号。

```sql
SELECT DISTINCT Sn
FROM SC
WHERE Grade<60;
```

##### 2) 确定范围

:one: 查询年龄在20~23岁(包括20岁和23岁)之间的学生的姓名、系别和年龄

```sql
SELECT Sname, Sdept, Sage
FROM Student
WHERE Sage BETWEEN 20 AND 23;
```

:two: 查询年龄不在20~23岁之间的学生姓名、系别和年龄

```sql
SELECT Sname, Sdept, Sage
FROM Student
WHERE Sage NOT BETWEEN 20 AND 23;
```

##### 3) 确定集合

:one: 查询软件学院(SE)、数学系(MA)和信息系(IS)学生的姓名和性别。

```sql
SELECT Sname, Ssex
FROM Student
WHERE Sdept IN ('SE','MA’,'IS' );
```

:two: 查询既不是软件学院、数学系，也不是信息系的学生的姓名和性别。

```sql
SELECT Sname, Ssex
FROM Student
WHERE Sdept NOT IN ('IS','MA’,'SE' );
```



##### 4) 字符匹配

谓词： [NOT] LIKE ‘<匹配串>’ [ESCAPE ‘ <换码字符>’]

<匹配串>可以是一个完整的字符串，也可以含有通配符%和 _

% (百分号) 代表任意长度(长度可以为0)的字符串

> 例如a%b表示以a开头，以b结尾的任意长度的字符串

_(下横线) 代表任意单个字符。

> 例如a_b表示以a开头，以b结尾的长度为3的任意字符串

:one: 查询学号为201215121的学生的详细情况。

```sql
SELECT *           等价于         SELECT *
FROM Student                     FROM Student
WHERE Sno LIKE ‘201215121';      WHERE Sno = ' 201215121 ';
```

:two: 查询所有姓刘学生的姓名、学号和性别。

```sql
SELECT Sname, Sno, Ssex
FROM Student
WHERE Sname LIKE '刘%';
```

:three: 查询姓"欧阳"且全名为三个汉字的学生的姓名。

```sql
SELECT Sname
FROM Student
WHERE Sname LIKE '欧阳__';
```

:four: 查询名字中第2个字为"阳"字的学生的姓名和学号。

```sql
SELECT Sname，Sno
FROM Student
WHERE Sname LIKE '__阳%';
```

:five: 查询所有不姓刘的学生姓名、学号和性别。

```sql
SELECT Sname, Sno, Ssex
FROM Student
WHERE Sname NOT LIKE '刘%';
```

:six: 查询以"DB_"开头，且倒数第3个字符为 i的课程的详细情况。

```SQL
SELECT *
FROM Course
WHERE Cname LIKE 'DB\_%i_ _' ESCAPE '\ ' ;
/*ESCAPE '＼' 表示“ ＼” 为换码字符*/
```

##### 5) 涉及空值

谓词： IS NULL 或 IS NOT NULL

 “IS” 不能用 “=” 代替

示例：某些学生选修课程后没有参加考试，所以有选课记录，但没有考试成绩。

:one: **查询缺少成绩的学生的学号和相应的课程号。**

```SQL
SELECT Sno，Cno
FROM SC
WHERE Grade IS NULL
```

:two: **查所有有成绩的学生学号和课程号。**

```SQL
SELECT Sno，Cno
FROM SC
WHERE Grade IS NOT NULL;
```

##### 6) 多重条件查询

逻辑运算符：AND和 OR来连接多个查询条件

> AND的优先级高于OR
>
> 可以用括号改变优先级

查询计算机系年龄在20岁以下的学生姓名

```sql
SELECT Sname
FROM Student
WHERE Sdept= 'SE' AND Sage<20;
```

#### 6、ORDER BY子句

可以按一个或多个属性列排序

升序：ASC;降序：DESC;缺省值为升序

示例：查询选修了3号课程的学生的学号及其成绩，查询结果按分数降序排列

```sql
SELECT Sno, Grade
FROM SC
WHERE Cno= ' 3 '
ORDER BY Grade DESC;
```

#### 7、聚集函数

:one: 统计元组个数

```sql
COUNT(*)
```

:two: 统计一列中值的个数

```sql
COUNT([DISTINCT|ALL] <列名>)
```

:three: 计算一列值的总和(此列必须为数值型)

```sql
SUM([DISTINCT|ALL] <列名>)
```

:four: 计算一列值的平均值(此列必须为数值型

```sql
AVG([DISTINCT|ALL] <列名>)
```

:five: 求一列中的最大值和最小值

```sql
MAX([DISTINCT|ALL] <列名>)
MIN([DISTINCT|ALL] <列名>)
```

DISTINCT表示计算时要取消指定列中的重复值

 **举例**

:one: 查询学生总人数。

```sql
SELECT COUNT(*)
FROM Student;
```

:two: 查询选修了课程的学生人数。

```sql
SELECT COUNT(DISTINCT Sno)
FROM SC;
```

:three: 计算1号课程的学生平均成绩。

```sql
SELECT AVG(Grade)
FROM SC
WHERE Cno= ' 1 ';
```

:four: 查询选修1号课程的学生最高分数。

```sql
SELECT MAX(Grade)
FROM SC
WHERE Cno='1';
```

#### 8、GROUP BY 字句

细化聚集函数的作用对象

> 如果未对查询结果分组，聚集函数将作用于整个查询结果
>
> 对查询结果分组后，聚集函数将分别作用于每个组
>
> 按指定的一列或多列值分组，值相等的为一组

示例：                               

查询平均成绩大于等于90分的学生学号和平均成绩

下面的语句是不对的：

```sql
SELECT Sno, AVG(Grade)
FROM SC
WHERE AVG(Grade)>=90
GROUP BY Sno;
```

因为<font color="red">**WHERE子句中是不能用聚集函数作为条件表达式**</font>

正确的查询语句应该是：

```sql
SELECT Sno, AVG(Grade)
FROM SC
GROUP BY Sno
HAVING AVG(Grade)>=90;
```

HAVING短语与WHERE子句的区别：

作用对象不同

> WHERE子句作用于基表或视图，从中选择满足条件的元组
>
> HAVING短语作用于**组，从中选择满足条件的组**

### 连接查询

#### 1、等值连接

等值连接：连接运算符为=

查询每个学生及其选修课程的情况

```sql
SELECT Student.*, SC.*
FROM Student S, SC
WHERE S.Sno = SC.Sno;                   
```

#### 2、自然连接

对上述例子用自然连接完成

```sql
SELECT Student.Sno,Sname,Ssex,Sage,Sdept,Cno,Grade
FROM Student,SC
WHERE Student.Sno = SC.Sno；
```

一条SQL语句可以同时完成选择和连接查询，这时WHERE子句是由连接谓词和选择谓词组成的复合条件

查询选修2号课程且成绩在90分以上的所有学生的学号和姓名。

```sql
SELECT Student.Sno, Sname
FROM Student, SC
WHERE Student.Sno=SC.Sno AND
SC.Cno=' 2 ' AND SC.Grade>90;
```

执行过程:

> 先从SC中挑选出Cno='2'并且Grade>90的元组形成一个中间关系
>
> 再和Student中满足连接条件的元组进行连接得到最终的结果关系

#### 3、自连接

自身连接：一个表与其自己进行连接需要给表**起别名**以示区别

由于所有属性名都是同名属性，因此必须使用别名前缀

示例：查询每一门课的间接先修课(即先修课的先修课)

```sql
SELECT C1.Cno, C2.Cpno
FROM Course C1, Course C2
WHERE C1.Cpno = C2.Cno;
```

#### 4、外连接

```sql
SELECT Student.Sno,Sname,Ssex,Sage,Sdept,Cno,Grade
FROM Student LEFT OUT JOIN SC ON
(Student.Sno=SC.Sno);
```

<img src="https://raw.githubusercontent.com/yijunquan-afk/img-bed-1/main/imges3/image-20220128175406554.png" style="zoom:67%;" />



<img src="https://raw.githubusercontent.com/yijunquan-afk/img-bed-1/main/imges3/image-20220125121653925.png" alt="image-20220125121653925" style="zoom:67%;float:left" />

![image-20220125121748104](https://raw.githubusercontent.com/yijunquan-afk/img-bed-1/main/imges3/image-20220125121748104.png)

#### 5、多表连接

查询每个学生的学号、姓名、选修的课程名及成绩

```sql
SELECT Student.Sno, Sname, Cname, Grade
FROM Student S, SC, Course C /*多表连接*/
WHERE S.Sno = SC.Sno
AND SC.Cno = C.Cno;
```

### 嵌套查询

一个SELECT-FROM-WHERE语句称为一个查询块

将一个查询块嵌套在另一个查询块的WHERE子句或HAVING短语的条件中的查询称为嵌套查询

```sql
SELECT Sname
FROM Student
WHERE Sno IN
( SELECT Sno
FROM SC
WHERE Cno= ' 2 ');
```

<mark>子查询的限制：不能使用ORDER BY子句</mark>

#### 不相关子查询与相关子查询

不相关子查询：子查询的查询条件不依赖于父查询

> 由里向外 逐层处理。即每个子查询在上一级查询处理之前求解，子查询的结果用于建立其父查询的查找条件。

相关子查询：子查询的查询条件依赖于父查询。

> 首先取外层查询中表的第一个元组，根据它与内层查询相关的属性值处理内层查询，若WHERE子句返回值为真，则取此元组放入结果表。
>
> 然后再取外层表的下一个元组。
>
> 重复这一过程，直至外层表全部检查完为止

#### 带有IN谓词的子查询

```sql
SELECT Sno,Sname  /*3、最后在Student关系中取出Sno和Sname*/
FROM Student
WHERE Sno IN
	(SELECT Sno
	 FROM SC
	 WHERE Cno IN /*2、然后在SC关系中找出选修了3号课程的学生学号*/
		(SELECT Cno
		 FROM Course 
		 WHERE Cname= '信息系统' /*1、首先在Course关系中找出“信息系统”的课程号，为3号*/
		 )
	);
```

**也可以使用连接查询语句实现**

```sql
SELECT Sno,Sname
FROM Student,SC,Course
WHERE Student.Sno = SC.Sno AND
SC.Cno = Course.Cno AND
Course.Cname='信息系统';
```

#### 带有比较运算符的子查询

找出每个学生超过他选修课程平均成绩的课程号。

```sql
SELECT Sno, Cno
FROM SC x
WHERE Grade >=(SELECT AVG(Grade)
				FROM SC y
				WHERE y.Sno=x.Sno);
```

#### 带有ANY(SOME)或者ALL的子查询   

![image-20220130144339644](https://raw.githubusercontent.com/yijunquan-afk/img-bed-1/main/image-20220130144339644.png)

:label: 示例：查询非软件学院中比软件学院任意一个学生年龄小的学生姓名和年龄

```sql
SELECT Sname,Sage
FROM Student
WHERE Sage < ANY (SELECT Sage
				  FROM Student
				  WHERE Sdept= ' SE ')
AND Sdept <> ‘SE ' ;
```

:label: 用聚集函数实现

```sql
SELECT Sname,Sage
FROM Student
WHERE Sage < (SELECT MAX(Sage)
			  FROM Student
			  WHERE Sdept= 'SE ')
AND Sdept <> ' SE ';
```

<img src="https://raw.githubusercontent.com/yijunquan-afk/img-bed-1/main/imges3/image-20220130144621832.png" alt="image-20220130144621832" style="zoom:50%;" />

#### 带有EXISTS谓词的子查询

**EXISTS谓词**

存在量词 

带有EXISTS谓词的子查询不返回任何数据，只产生逻辑真值“true”或逻辑假值“false”。

> 若内层查询结果非空，则外层的WHERE子句返回真值
>
> 若内层查询结果为空，则外层的WHERE子句返回假值

由EXISTS引出的子查询，其目标列表达式**通常都用 *** ，因为带EXISTS的子查询只返回真值或假值，给出列名无实际意义。

**NOT EXISTS谓词**

> 若内层查询结果非空，则外层的WHERE子句返回假值
>
> 若内层查询结果为空，则外层的WHERE子句返回真值

:label: 示例：查询没有选修1号课程的学生姓名。

```sql
SELECT Sname
FROM Student
WHERE NOT EXISTS
	  (SELECT *
	   FROM SC
	   WHERE Sno = Student.Sno AND Cno='1');
```

 不同形式的查询间的替换

一些带EXISTS或NOT EXISTS谓词的子查询不能被其他形式的子查询等价替换

所有带IN谓词、比较运算符、ANY和ALL谓词的子查询都能用带EXISTS谓词的子查询等价替换

**用EXISTS/NOT EXISTS实现逻辑蕴涵(难点)**

SQL语言中没有蕴涵(Implication)逻辑运算

可以利用**谓词演算**将逻辑蕴涵谓词等价转换为: $(\forall y)p\rightarrow q=\exists y(p\and \lnot q)$

示例：查询至少选修了学生201215122选修的全部课程的学生号码。

解题思路

用逻辑蕴涵表达：查询学号为x的学生，对所有的课程y，只要201215122学生选修了课程y，则x也选修了y。形式化表示：用P表示谓词 “学生201215122选修了课程y”用q表示谓词 “学生x选修了课程y”则上述查询为: 

变换后语义：**不存在这样的课程y，学生201215122选修了y，而学生x没有选。**

用NOT EXISTS谓词表示：

```sql
SELECT DISTINCT Sno
FROM SC SCX
WHERE NOT EXISTS
	(SELECT *
	FROM SC SCY
	WHERE SCY.Sno = ' 201215122 ' AND
	NOT EXISTS
		(SELECT *
		 FROM SC SCZ
		 WHERE SCZ.Sno=SCX.Sno AND
		 SCZ.Cno=SCY.Cno)
	);
```

### 集合查询

集合操作的种类

> 并操作UNION
>
> 交操作INTERSECT
>
> 差操作EXCEPT

参加集合操作的各查询结果的列数必须相同;对应项的数据类型也必须相同

:label: 示例1：查询软件学院的学生及年龄不大于19岁的学生

```sql
SELECT *
FROM Student
WHERE Sdept= 'SE'
UNION
SELECT *
FROM Student
WHERE Sage<=19;
```

> UNION：将多个查询结果合并起来时，系统自动去掉重复元组
>
> UNION ALL：将多个查询结果合并起来时，保留重复元组

:label: 示例2：查询既选修了课程1又选修了课程2的学生。

```sql
SELECT Sno
FROM SC
WHERE Cno=' 1 '
INTERSECT
SELECT Sno
FROM SC
WHERE Cno='2 ';
```

### 基于派生表的查询

子查询不仅可以出现在WHERE子句中，还可以出现在FROM子句中，这时子查询生成的临时派生表(Derived Table)成为主查询的查询对象

示例：找出每个学生超过他自己选修课程平均成绩的课程号

```SQL
SELECT Sno, Cno
FROM SC, (SELECTSno, Avg(Grade)
		  FROM SC 
		  GROUP BY Sno) AS Avg_sc(avg_sno,avg_grade)//取别名
WHERE SC.Sno = Avg_sc.avg_sno
		AND SC.Grade >=Avg_sc.avg_grade
```

### 总结【重点】

```SQL
SELECT [ALL|DISTINCT]
<目标列表达式> [别名] [ ,<目标列表达式> [别名]] …
FROM <表名或视图名> [别名][ ,<表名或视图名> [别名]] …|(<SELECT语句>)[AS]<别名>
[WHERE <条件表达式>]
[GROUP BY <列名1>[HAVING<条件表达式>]]
[ORDER BY <列名2> [ASC|DESC]];
```

#### 目标表达式格式

(1) *

(2) <表名>.\*

(3) COUNT([DISTINCT|ALL]\* )

(4) [<表名>.]<属性列名表达式>[,<表名>.]<属性列名表达式>]…

其中<属性列名表达式>可以是由属性列、作用于属性列的聚集函数和常量的任意算术运算(+，-，*，/)组成的运算公式

#### 聚集表达式格式

<img src="https://raw.githubusercontent.com/yijunquan-afk/img-bed-1/main/imges3/image-20220130150342385.png" alt="image-20220130150342385" style="zoom:50%;" />

#### WHERE子句的条件表达式的可选格式

 ![image-20220130150726953](https://raw.githubusercontent.com/yijunquan-afk/img-bed-1/main/image-20220130150726953.png)

## 四、数据更新

### 插入数据

#### 插入元组

**语句格式**

```sql
INSERT
INTO <表名> [(<属性列1>[,<属性列2 >…)] VALUES (<常量1> [,<常量2>]… );
```

**功能**

> 将新元组插入指定表中

**INTO子句**

> 指定要插入数据的表名及属性列
>
> 属性列的顺序可与表定义中的顺序不一致
>
> 没有指定属性列：表示要插入的是一条完整的元组，且 属性列属性与表定义中的顺序一致
>
> 指定部分属性列：插入的元组在其余属性列上取空值

**VALUES子句**

提供的值必须与INTO子句匹配

> 值的个数
>
> 值的类型

:label: 示例：将一个新学生元组(学号：201215128;姓名：陈冬;性别：男;所在系：IS;年龄：18岁)插入到Student表中。

```sql
INSERT
INTO  Student (Sno,Sname,Ssex,Sdept,Sage) VALUES ('201215128','陈冬','男','IS',18); 
```

#### 插入子查询

语句格式

```sql
INSERT
INTO <表名> [(<属性列1> [,<属性列2>…   )]
子查询;
```

INTO子句

子查询

> SELECT子句目标列必须与INTO子句匹配
>
> 值的个数
>
> 值的类型

:label: 示例: **对每一个系**，求学生的平均年龄，并把结果存入数据库

:one: 第一步：建表

```sql
CREATE TABLE   Dept_age
( Sdept  CHAR(15)
  Avg_age SMALLINT);
```

:two: 第二步：插入数据

```sql
INSERT
INTO Dept_age(Sdept,Avg_age) SELECT Sdept，AVG(Sage) FROM Student
GROUP BY Sdept;
```

关系数据库管理系统在执行插入语句时会**检查所插元组是否破坏表上已定义的完整性规则**

> 实体完整性
>
> 参照完整性
>
> 用户定义的完整性
>
> > NOT NULL约束
> >
> > UNIQUE约束
> >
> > 值域约束

### 修改数据

**语句格式**

```sql
UPDATE <表名>
SET <列名>=<表达式>[,<列名>=<表达式>]… [WHERE <条件>];
```

**功能**

> 修改指定表中满足WHERE子句条件的元组
>
> SET子句给出<表达式>的值用于取代相应的属性列
>
> 如果省略WHERE子句，表示要修改表中的所有元组

#### 修改某一个元组的值

示例：将学生201215121的年龄改为22岁

```sql
UPDATE Student SET Sage=22
WHERE  Sno=' 201215121 ';
```

#### 修改多个元组的值

示例：将所有学生的年龄增加1岁

```sql
UPDATE Student
SET Sage= Sage+1;
```

#### 带子查询的修改语句

示例：将计算机科学系全体学生的成绩置零。

```sql
UPDATE SC
SET Grade=0 WHERE Sno IN
(SELETE Sno
 FROM   Student
 WHERE  Sdept= 'CS' );
```

### 删除数据

**语句格式**

```sql
DELETE FROM
<表名>
[WHERE <条件>];
```

**功能**

> 删除指定表中满足WHERE子句条件的元组
>
> WHERE子句
>
> 指定要删除的元组
>
> 缺省表示要删除表中的全部元组，表的定义仍在字 典中

#### 删除某一个元组的值

示例：删除学号为201215128的学生记录。

```sql
DELETE
FROM Student
WHERE Sno= 201215128 ';
```

#### 删除多个元组的值

示例：删除所有的学生选课记录。

```sql
DELETE
FROM SC;
```

#### 带子查询的删除语句

示例：删除计算机科学系所有学生的选课记录。

```sql
DELETE FROM  SC
WHERE  Sno IN
(SELETE Sno
 FROM   Student 
 WHERE  Sdept= 'CS') ;
```

##  五、视图【了解】 

视图的**特点**

> 是从一个或几个基本表(或视图)导出的“虚表”。
>
> 只存放视图的定义，不存放视图对应的数据。
>
> 基表中的数据发生变化，从视图中查询出的数据也 随之改变。

视图的**作用**

> 视图能够简化用户的操作
>
> 视图使用户能以多种角度看待同一数据
>
> 视图对重构数据库提供了一定程度的逻辑独立性
>
> 视图能够对机密数据提供安全保护
>
> 适当的利用视图可以更清晰的表达查询

### 建立视图

**语句格式**

```sql
CREATE VIEW
<视图名>   [(<列名> [,<列名>]…)] AS <子查询>
[WITH  CHECK OPTION];
```

> WITH CHECK OPTION
>
> > 对视图进行UPDATE，INSERT和DELETE操作时要保证更新、插入或删除的行满足视图定义中的谓词条件(即子查询中的条件表达式)
>
> 子查询可以是任意的SELECT语句，是否可以含 有ORDER BY子句和DISTINCT短语，则决定具体系统的实现。
>
> 组成视图的属性列名：全部省略或全部指定
>
> 关系数据库管理系统执行CREATE VIEW语句时 只是把视图定义存入数据字典，并不执行其中的 SELECT语句。
>
> 在对视图查询时，按视图的定义从基本表中将数 据查出。

示例：建立信息系学生的视图，并要求进行修改和插入操作时仍需保证该视图只有信息系的学生。

```sql
CREATE VIEW IS_Student AS
SELECT Sno,Sname,Sage FROM   Student
WHERE  Sdept= 'IS';
WITH CHECK OPTION;
```

:label: 定义IS_Student 视图时加上了WITH CHECK OPTION子句，对该视图进行插入、修改和删除操作时，RDBMS会自动加上Sdept='IS'的条件。

> 若一个视图是从单个基本表导出的，并且只是去掉了基本表的某些行和某些列，但保留了主码，我们称这类视图为行列子集视图。
>
> IS_Student视图就是一个行列子集视图。

**基于多个基表的视图**

示例：建立信息系选修了1号课程的学生的视图(包括学号、姓名、成绩)。

```sql
CREATE VIEW IS_S1(Sno,Sname,Grade) AS
SELECT Student.Sno,Sname,Grade FROM   Student,SC
WHERE  Sdept= 'IS' AND
Student.Sno=SC.Sno AND SC.Cno= '1';
```

**基于视图的视图**

示例：建立信息系选修了1号课程且成绩在90分以上的学生的视图。

```sql
CREATE VIEW IS_S2 AS
SELECT Sno,Sname,Grade FROM   IS_S1
WHERE  Grade>=90;
```

**带表达式的视图**

示例：定义一个反映学生出生年份的视图。 

```sql
CREATE VIEW BT_S(Sno,Sname,Sbirth) AS
SELECT Sno,Sname,2014-Sage FROM  Student;
```

**分组视图**

示例：  将学生的学号及平均成绩定义为一个视图

```sql
CREAT VIEW S_G(Sno,Gavg)
AS
SELECT Sno,AVG(Grade) FROM SC
GROUP BY Sno; 
```

### 删除视图

**语句的格式**

```sql
DROP VIEW <视图名>[CASCADE];
```

> 该语句从数据字典中删除指定的视图定义
>
> 如果该视图上还导出了其他视图，使用CASCADE级 联删除语句，把该视图和由它导出的所有视图一起删 除
>
> 删除基表时，由该基表导出的所有视图定义都必须显 式地使用DROP VIEW语句删除

#### 查询视图、更新视图

用户角度：查询与更新视图与查询更新基本表相同

## 六、嵌入式SQL

SQL语言提供了两种不同的使用方式

> 交互式(isql) 
>
> 嵌入式(esql)

为什么要引入嵌入式SQL

> SQL语言是非过程性语言
>
> 事务处理应用需要高级语言

这两种方式细节上有差别，在程序设计的环境下，SQL语句要做某些必要的扩充

### 嵌入式SQL的处理过程

**主语言**

> 嵌入式SQL是将SQL语句嵌入程序设计语言中，被嵌入的程序设计语言，如C、C++、Java，称为宿主语言，简称主语言。

**处理过程**

> 预编译方法

为了**区分SQL语句与主语言语句，所有SQL语句必须加前缀EXEC SQL，**

主语言为C语言时，语句格式：EXEC SQL <SQL语句>;

### 嵌入式SQL语句与主语言之间的通信

**数据库工作单元与源程序工作单元之间的通信**

> 向主语言传递SQL语句的执行状态信息，使主语言能够据此控制程序流程，主要用**SQL通信区（SQLCA）**实现
>
> **主语言向SQL语句提供参数，主要用主变量实现**
>
> 将SQL语句查询数据库的结果交主语言处理，主要用主变量和游标实现 

**SQLCA： SQL Communication Area**

> :label: SQLCA是一个数据结构
>
> :label: SQLCA的用途
>
> > SQL语句执行后，系统反馈给应用程序信息：描述系统当前工作状态;描述运行环境
> >
> > 这些信息将送到SQL通信区中
> >
> > 应用程序从SQL通信区中取出这些状态信息，据此决定接下来执行的语句
>
> :label: 定义SQLCA
>
> > 用EXEC SQL INCLUDE SQLCA定义
>
> :label: 使用SQLCA
>
> > SQLCA中有一个存放每次执行SQL语句后返回代码的变量SQLCODE
> >
> > 如果SQLCODE等于预定义的常量SUCCESS，则表示SQL语句成功，否则表示出错
> >
> > 应用程序每执行完一条SQL 语句之后都应该测试一下SQLCODE的值，以了解该SQL语句执行情况并做相应处理

**主变量**

> 嵌入式SQL语句中可以使用主语言的程序变量来输入或输出数据
>
> 在SQL语句中使用的主语言程序变量简称为主变量（Host Variable）

**指示变量**

> 是一个整型变量，用来“指示”**所指主变量的值或条**件
>
> 一个主变量可以附带一个指示变量（Indicator Variable）
>
> 指示变量的用途
>
> > 指示输入主变量是否为空值
> >
> > 检测输出变量是否为空值，值是否被截断

**游标**

> 游标是系统为用户开设的一个数据缓冲区，存放SQL语句的执行结果。
>
> 每个游标都有一个名字。
>
> 用户可以用SQL语句逐一从游标中获取记录，并赋给主变量，交由主语言进一步处理。

### SQL语句类别

不用游标的SQL语句的种类

> 说明性语句
>
> 数据定义语句
>
> 数据控制语句
>
> 查询结果为单记录的SELECT语句
>
> 非CURRENT形式的增删改语句

使用游标的SQL语句

> 必须使用游标的SQL语句
>
> 查询结果为多条记录的SELECT语句
>
> CURRENT形式的UPDATE语句
>
> CURRENT形式的DELETE语句

动态SQL

> 静态嵌入式SQL
>
> > 静态嵌入式SQL语句能够满足一般要求
> >
> > 无法满足要到执行时才能够确定要提交的SQL语句、查询的条件
>
> 动态嵌入式SQL
>
> > 允许在程序运行过程中临时“组装”SQL语句
> >
> > 支持动态组装SQL语句和动态参数两种形式

## 七、书本习题与解答

![image-20220130153813357](https://raw.githubusercontent.com/yijunquan-afk/img-bed-1/main/image-20220130153813357.png)

![image-20220130153819729](https://raw.githubusercontent.com/yijunquan-afk/img-bed-1/main/image-20220130153819729.png)

![image-20220130153830383](https://raw.githubusercontent.com/yijunquan-afk/img-bed-1/main/imges3/image-20220130153830383.png)

![image-20220130153839251](https://raw.githubusercontent.com/yijunquan-afk/img-bed-1/main/image-20220130153839251.png)
![image-20220130153851181](https://raw.githubusercontent.com/yijunquan-afk/img-bed-1/main/image-20220130153851181.png)

![image-20220130153901306](https://raw.githubusercontent.com/yijunquan-afk/img-bed-1/main/imges3/image-20220130153901306.png)

![image-20220130153909515](https://raw.githubusercontent.com/yijunquan-afk/img-bed-1/main/image-20220130153909515.png)

![image-20220130153922355](https://raw.githubusercontent.com/yijunquan-afk/img-bed-1/main/image-20220130153922355.png)

![image-20220130153931787](https://raw.githubusercontent.com/yijunquan-afk/img-bed-1/main/image-20220130153931787.png)

## 八、课后习题答案

![image-20220130154020830](https://raw.githubusercontent.com/yijunquan-afk/img-bed-1/main/image-20220130154020830.png)

![image-20220130154037609](https://raw.githubusercontent.com/yijunquan-afk/img-bed-1/main/image-20220130154037609.png)