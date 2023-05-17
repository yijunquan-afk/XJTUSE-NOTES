@[toc]
# Linux实践——CPU调度算法实现

<font color="red" size=5>**仅供参考，谢绝抄袭**</font>

## 一、实验目的

熟练掌握 Linux 操作系统的使用，掌握 Linux 的各项系统管理功能，掌握 Linux 下各类网络服务的安装、配置以及使用，并能用 shell 脚本实现简单的管理 任务。

## 二、实验内容

### ③  CPU 调度算法实现

在 Linux 环境下编写 C 或 C++程序实现几种 CPU 调度算法：FCFS、SJF 和 优先权调度。 在 Linux 下进行编译和运行， 可使用 Makefile 文件实现程序的编 译、安装和卸载。 并比较这几种 CPU 调度算法的性能，给出等待时间、周转时 间及其平均值。（报告中给出源代码、Makefile 文件、make 运行结果以及程序运 行结果）。

实验所用测试数据如下表（算法均默认为非抢占）

![image-20220328212718291](https://img-blog.csdnimg.cn/img_convert/4826568f174078e5ae79d0952809627b.png)                               

## 三、   题目分析及基本设计过程分析

### ③  CPU 调度算法实现

#### 1)   程序编译

**gcc命令** 使用GNU推出的基于C/C++的编译器，是开放源代码领域应用最广泛的编译器，具有功能强大，编译代码支持性能优化等特点

**语法**

```shell
gcc(选项)(参数)
```

**选项**

```shell
-o：指定生成的输出文件；
-E：仅执行编译预处理；
-S：将C代码转换为汇编代码；
-wall：显示警告信息；
-c：仅执行编译操作，不进行连接操作。
```

#### 2) Makefile文件格式

一个Makefile文件主要有一系列规则，每条规则都必须包含以下内容：

> 一个目标（target），即make最终需要创建的文件，如可执行文件或目标文件，目标也可以是要执行的动作，如clean
>
> 一个或多个依赖文件（dependency）列表，通常是编译目标文件所需要的其他文件
>
> 一系列命令（command），是make执行的动作，通常是把指定的相关文件编译成目标文件的编译命令，每个命令占一行，并且每个命令的起始字符必须为Tab字符。

#### 3) make命令

**m**ake命令 是GNU的工程化编译工具，用于编译众多相互关联的源代码文件，以实现工程化的管理，提高开发效率

**语法**

```shell
make(选项)(参数)
```

**选项**

```shell
-f：指定“makefile”文件；
-i：忽略命令执行返回的出错信息；
-s：沉默模式，在执行之前不输出相应的命令行信息；
-r：禁止使用build-in规则；
-n：非执行模式，输出所有执行命令，但并不执行；
-t：更新目标文件；
-q：make操作将根据目标文件是否已经更新返回"0"或非"0"的状态信息；
-p：输出所有宏定义和目标文件描述；
-d：Debug模式，输出有关文件和检测时间的详细信息。
```

#### 4）算法设计说明

**定义结构体表示一个进程** 

```c
typedef struct pro {  
    int id;//作业ID  
    int arrive_time;//到达时间  
    int excute_time;//执行时长  
    int priority;//优先级  
    int begin_time;//开始执行时间  
    int waiting_time;//等待时间  
    int turnaround_time;//周转时间  
} process; 

```

**定义其他变量**

```c
process test_data[NUM] = {  
    {1, 800, 50, 0}, {2, 815, 30, 1},  
    {3, 830, 25, 2}, {4, 835, 20, 2},  
    {5, 845, 15, 2}, {6, 700, 10, 1},  
    {7, 820, 5, 0}  
};//测试数据  
process Priority_output[NUM];//Priority的输出  
process SJF_output[NUM];//SJF的输出  
process ready_process[NUM];//就绪进程 

```

**用冒泡排序对进程按照到达时间从先到后排序**

```c
void sort(process test[NUM]) {  
    int i, j;  
    for (i = 0; i < NUM; i++) {  
        for (j = NUM - 1; j > i; j--) {  
            if (test[j].arrive_time < test[j - 1].arrive_time) {  
                process temp = test[j];  
                test[j] = test[j - 1];  
                test[j - 1] = temp;  
            }  
        }  
    }  
}  
```

**FCFS调度算法** 

按照作业到达顺序进行调度

```c
void FCFS(process test[NUM]) {  
    sort(test);  
    int current_time = 0;  
    int i;  
    for (i = 0; i < NUM; i++) {  
        if (test[i].arrive_time > current_time) {  
            current_time = test[i].arrive_time; //更新当前时间  
        } else { //如果到达时间比当前时间早，需要等待  
            test[i].waiting_time = current_time - test[i].arrive_time;  
        }  
        test[i].begin_time = current_time;//开始时间为当前时间  
        current_time += test[i].excute_time;//执行进程以后的当前时间  
        test[i].turnaround_time = current_time - test[i].arrive_time;//周转时间  
  
    }  
} 

```

**SJF调度算法** 

这里我用到了**就绪进程组**来存已经到达但是无法执行的进程，当CPU资源空出时，则利用find_shortest函数找出执行时间最短的进程，为其分配CPU资源，同时就绪进程组中的进程数减一。

```c
/** 
 * @brief 找到执行时长最短的就绪进程  
 *  
 * @param test 就绪进程组  
 * @param index 最后一个就绪进程的下标指针   
 * @return  执行时长最短的就绪进程  
 **/  
process find_shortest(process test[NUM], int *index) {  
    process shortest = test[0];  
    int shortest_index = 0;  
    int i, j;  
    for (i = 1; i < *index; i++) {  
        if (shortest.excute_time > test[i].excute_time) {  
            shortest = test[i];  
            shortest_index = i;  
        }  
    }//找到最短的执行时间  
    for (j = shortest_index; j < *index; j++) {  
        test[j] = test[j + 1]; //将最短的进程调出  
    }  
    (*index)--;  
    return shortest;  
}  
/** 
 * @brief 使用短作业有限的调度算法 
 * 
 * @param test 待调度的进程 
 **/  
void SJF(process test[NUM]) {  
    sort(test);  
    int current_time = test[0].arrive_time;//最早到达的时间  
    int ready_index = 0;  
    int k = 0;//已经调度的进程数  
    int i, j;  
    for ( i = 0; i < NUM; i++) {  
        for (j = k; j < NUM; j++) {  
            if (current_time >= test[j].arrive_time) {  
                //test[j]的到达时间比上一个进程执行结束的时间早  
                //则进入就绪数组  
                ready_process[ready_index] = test[j];  
                ready_index++;  
                k++;  
            } else {  
                break;//跳出判断  
            }  
        }  
        if (ready_index == 0) { //没有就绪进程，则下一个进程为就绪进程  
            ready_process[ready_index] = test[k];  
            current_time = test[k].arrive_time;  
            ready_index++;  
            k++;  
        }  
        int * index = &ready_index;//注意index也要改变  
        process shortest = find_shortest(ready_process, index);  
        shortest.begin_time = current_time; //开始时间  
        shortest.waiting_time = current_time - shortest.arrive_time; //等待时间  
        current_time += shortest.excute_time;  
        shortest.turnaround_time = current_time - shortest.arrive_time; //周转时间  
        SJF_output[i] = shortest;  
  
    }  
}  

```

**非抢占优先级调度算法** 

该算法的程序实现与SJF相似，都用到了就绪进程组，只是find_shortest函数改成了find_priority，找出优先级最小的进程，其余的都跟SJF一样，不再赘述

## 四、   配置文件关键修改处的说明及运行情况

### ③  CPU 调度算法实现

#### 0)   Makefile文件内容

    ![image-20220328212618180](https://img-blog.csdnimg.cn/img_convert/9ad0d33e2ad8dfd5e786c44b3c23fa26.png)                           

#### 1)   make执行结果

 ![image-20220328212627271](https://img-blog.csdnimg.cn/img_convert/8eb196091d662e970ccc4385a694b300.png)

#### 2)   程序运行结果

 ![image-20220328212645123](https://img-blog.csdnimg.cn/img_convert/ffa1cadf4e521619812f8d1877933e64.png)

 

 