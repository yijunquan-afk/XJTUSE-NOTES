package cn.edu.xjtu.algorithm.chapter05;

public class Loading {
    // 船最大装载问题
    private int n;// 集装箱数
    private int[] x;// 当前解
    private int[] bestx;// 当前最优解
    private int[] w;// 集装箱重量数组
    private int c;// 第一艘船的载重量
    private int cw;// 当前载重量
    private int bestw;// 当前最优载重量
    private int r;// 剩余集装箱重量

    void backTrack(int i)// 搜索第i层结点
    {
        if (i > n) {
            // 到达叶结点
            if (cw > bestw) {
                for (int j = 1; j <= n; j++) {
                    bestx[j] = x[j];
                }
                bestw = cw;
            }
            return;
        }
        r -= w[i];
        // 剩余集装箱重量
        if (cw + w[i] <= c) {
            // 进入左子树
            x[i] = 1;
            // 装第i个集装箱
            cw += w[i];
            backTrack(i + 1);
            // 进入下一层
            cw -= w[i];
            // 退出左子树
        }
        if (cw + r > bestw) {
            // 进入右子树
            x[i] = 0;
            // 不装第i个集装箱
            backTrack(i + 1);
        }
        r += w[i];
    }

    public Loading(int[] w, int c, int n, int[] bestx) {
        this.w = w;
        this.c = c;
        this.n = n;
        this.bestx = bestx;
        this.bestw = 0;
        this.cw = 0;
        for (int i = 1; i <= n; i++) {
            this.r += w[i];
        }
        this.x = new int[n + 1];
    }// 构造器

    public static void main(String[] args) {
        int n = 5;
        int c = 10;
        int w[] = {0, 7, 2, 6, 5, 4};// 下标从1开始
        int bestx[] = new int[n + 1];
        Loading test = new Loading(w, c, n, bestx);
        test.backTrack(1);
        for (int i = 1; i <= n; i++) {
            System.out.print(bestx[i] + " ");
        }
        System.out.println();
        System.out.println(test.bestw);
        return;
    }
}