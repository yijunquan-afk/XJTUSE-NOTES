package cn.edu.xjtu.algorithm.chapter02;

/**
 * @author yjq
 * @version 1.0
 * @date 2021/9/19 9:32
 */
//设计一个递归算法生成n个元素{r1,r2,…,rn}的全排列。
public class Perm<T> {
    void Perm(T[] array, int k, int m) {
        if (k == m) {
            for (int i = 0; i < m; i++) {
                System.out.print(array[i]);
            }//边界条件
            System.out.println();
        } else {
            for (int i = k; i < m; i++) {
                swap(array, k, i);//使前缀不断变化
                Perm(array, k + 1, m);//递归
                swap(array,k,i);//还原
            }
        }
    }

    void swap(T[] array, int i, int j) {//交换元素
        T temp;
        temp = array[i];
        array[i] = array[j];
        array[j] = temp;

    }

    public static void main(String[] args) {
        Perm<Integer> test = new Perm<>();
        Integer[] list = {1, 2, 3};
        test.Perm(list,0,3);
    }
}
