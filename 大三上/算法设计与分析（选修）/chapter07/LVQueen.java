package cn.edu.xjtu.algorithm.chapter07;

import java.util.Random;

/**
 * @author yjq
 * @version 1.0
 * @date 2021/12/8 20:33
 */
public class LVQueen {
    //拉斯维加斯算法加回溯法解决n后问题

    private Random rnd;  //随机数产生器
    private int n;              //皇后个数
    private int[] x;            //解向量
    private int[] y;            //最终解向量

    private void backtrack(int t) {
        if (t > n) {
            for (int i = 1; i <= n; i++)
                y[i] = x[i];
            return;
        } else
            for (int i = 1; i <= n; i++) {
                x[t] = i;
                if (place(t)) backtrack(t + 1);
            }
    }


    private boolean place(int k) {//测试皇后k置于第x[k]列的合法性
        for (int j = 1; j < k; j++)
            if (Math.abs(k - j) == Math.abs(x[j] - x[k]) || (x[j] == x[k])) {
                return false;
            }
        return true;
    }

    private boolean queensLV(int stopVegas) {//随机放置m个皇后的Las Vegas算法
        rnd = new Random();
        int k = 1;
        int count = 1;
        while ((k <= stopVegas) && (count > 0)) {
            count = 0;
            int j = 0;
            for (int i = 1; i <= n; i++) {
                x[k] = i;
                if (place(k))
                    if (rnd.nextInt(++count) == 0) j = i;  //随机位置
            }
            if (count > 0) x[k++] = j;
        }
        return (count > 0); //count>0表示放置成功
    }


    public LVQueen(int n, int stop) {//解n后问题的Las Vegas算法
        this.n = n;
        //初始化
        x = new int[n + 1];
        y = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            x[i] = 0;
            y[i] = 0;
        }
        //前stop个随机放，后面几个用回溯
        while (!queensLV(stop)) ;
        backtrack(stop + 1);
        for (int i = 1; i <= n; i++) {
            System.out.println(y[i]);
        }
    }

    public static void main(String[] args) {
        LVQueen lvQueen = new LVQueen(4, 1);
    }
}
