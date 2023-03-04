package cn.edu.xjtu.algorithm.chapter02;

/**
 * @author yjq
 * @version 1.0
 * @date 2021/9/26 17:19
 */
public class QuickSort {
    public void quickSort(int[] a, int p, int r) {
        if (p < r) {
            int q = randomizedPartition(a, p, r);
            quickSort(a, p, q - 1);
            quickSort(a, q + 1, r);
        }
    }

    private int partition(int[] a, int p, int r) {

        int i = p;
        int j = r + 1;
        int x = a[p];
        //将小于x的元素交换到左边，大于x的元素交换到右边
        while (true) {
            while (a[++i] < x && i < r) ;
            while (a[--j] > x) ;
            if (i >= j) break;
            swap(a,i,j);
        }
        a[p] = a[j];
        a[j] = x;
        return j;
    }

    private void swap(int[]a,int x, int y) {
        int temp = a[x];
        a[x] = a[y];
        a[y] = temp;
    }

    int randomizedPartition(int[] a, int p, int r) {
        int i = (int) (Math.random() * (r - p + 1) + p);//生成随机数
        swap(a,p,i);
        return partition(a, p, r);
    }

    public static void main(String[] args) {
        int[] a = {6, 7, 5, 2, 5, 8};
        QuickSort sort = new QuickSort();
        sort.quickSort(a, 0, a.length - 1);
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
    }
}
