package cn.edu.xjtu.algorithm.chapter07;

import java.util.Random;

/**
 * @author yjq
 * @version 1.0
 * @date 2021/11/11 10:57
 */
public class OrderList<Type> {
    private int n;//当前集合中的元素个数
    private int maxLength;//集合中的最大元素个数
    private Type[] value;//存储集合元素的数组
    private int[] link;//指针数组
    private Random random;//随机数生成
    private Type small;//集合元素中的下界
    private Type tailKey;//集合元素中的上界

    public int complare(Type a, Type b) {
        return 0;
    }

    public OrderList(int maxLength, Type small, Type tailKey) {
        this.maxLength = maxLength;
        this.small = small;
        this.tailKey = tailKey;
        this.value = (Type[]) new Object[this.maxLength + 1];
        this.link = new int[this.maxLength + 1];
        this.n = 0;
        this.link[0] = 0;
        this.value[0] = this.tailKey;
    }

    public int search(Type x) {
        //搜索集合中指定元素
        int index = -1;
        Type max = small;
        int m = (int) Math.floor(Math.sqrt(n));
        for (int i = 1; i <= m; i++) {
            int j = random.nextInt(n) + 1;//随机产生数组元素位置
            Type y = value[j];
            if (complare(max, y) < 0 && complare(y, x) < 0) {
                max = y;
                index = j;
            }
        }
        while (complare(value[link[index]],x)<0){
            index=link[index];
        }
        return index;
    }
    public void insert(Type k){
        if (n==maxLength||(complare(k,tailKey)>0))return;
        int index=search(k);
        if (index==-1){
            value[++n]=k;
            link[n]=link[index];
            link[index]=n;
        }
    }
}
