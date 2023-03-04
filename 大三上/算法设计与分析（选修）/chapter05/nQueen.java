package cn.edu.xjtu.algorithm.chapter05;

/**
 * @author yjq
 * @version 1.0
 * @date 2021/12/7 21:44
 */
public class nQueen {
    //n皇后问题
    private int n;//皇后个数
    private int[] x;//当前解
    private long sum;//当前已找到可行方案数

    public nQueen(int n) {
        this.n = n;
        this.sum = 0;
        this.x = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            x[i] = 0;
        }
        this.backTrack(1);
    }

    /**
     * 放置在第k行
     *
     * @param k
     * @return 是否可行
     */
    private boolean place(int k) {
        for (int i = 1; i < k; i++) {
            if (Math.abs(k - i) == Math.abs(x[k] - x[i]) || x[i] == x[k]) {
                //在同一行或者同一斜线
                return false;
            }
        }
        return true;
    }

    /**
     * 递归回溯
     *
     * @param t
     */
    public void backTrack(int t) {
        if (t > n)
            sum++;
        else
            for (int i = 1; i <= n; i++) {//[1:n]列
                x[t] = i;//放在第i列
                if (place(t))
                    backTrack(t + 1);
            }
    }

    /**
     * 非递归回溯
     *
     * @param t
     */
    public void backTrack_o(int t) {
        x[1] = 0;
        int k = 1;
        while (k > 0) {
            x[k] += 1;  //第k行的放到下一列
            //x[k]不能放置，则放到下一列，直到可放置
            while ((x[k] <= n) && !place(k))
                x[k] += 1;
            if (x[k] <= n)  //放在n列范围内
                if (k == n)  //已放n行
                    sum++;
                else  //不足n行
                {
                    k++; //放下一行
                    x[k] = 0; //下一行又从第0列的下列开始试放
                }
            else  //第k行无法放置，则重新放置上一行（放到下一列）
                k--;
        }
    }

}

