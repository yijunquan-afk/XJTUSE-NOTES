package cn.edu.xjtu.algorithm.chapter05;

/**
 * @author yjq
 * @version 1.0
 * @date 2021/12/8 8:05
 */
public class TSP {
    /**
     * 已给一个n个点的[完全图])，每条边都有一个长度，
     * 求总长度最短的经过每个顶点正好一次的封闭回路
     */
    private int n;//图的顶点数
    private int[] x;//当前解
    private int[] bestx;//当前最优解
    private int[][] a;//邻接矩阵
    private int cc;//当前费用
    private int bestc;//当前最优值
    private static final int NO_EDGE = Integer.MAX_VALUE;//无边标记

    public TSP(int[][] a, int[] v, int n) {
        this.a = a;
        this.n = n;
        this.bestx = v;
        this.x = new int[n + 1];
        this.bestc=NO_EDGE;
        this.cc = 0;
        this.backTrack(2);
    }

    public void backTrack(int i) {
        if (i == n) {
            //当i=n时，当前扩展结点是排列树的叶结点的父结点，此时检查图G是否存在一条从
            // 顶点x[n-1]到顶点x[n]的边和一条从顶点x[n]到顶点1的边，如果两条边都存在，
            // 则找到一条回路。再判断此回路的费用是否优于当前最优回路的费用，是则更新当前最优值和最优解。
            if (a[x[n - 1]][x[n]] != NO_EDGE && a[x[n]][1] != NO_EDGE &&
                    (cc + a[x[n - 1]][x[n]] + a[x[n]][1] < bestc || bestc == NO_EDGE)) {
                for (int j = 1; j <= n; j++) {
                    bestx[j] = x[j];
                }
                bestc = cc + a[x[n - 1]][x[n]] + a[x[n]][1];
            }
        } else {
            //当i<n时，当前扩展结点位于排列树的第i-1层。图G中存在从顶点x[i-1]到x[i]的边时，
            // 检查x[1:i]的费用是否小于当前最优值，是则进入排列树的第i层，否则剪去相应子树。
            for (int j = i; j <= n; j++) {
                if (a[x[i - 1]][x[j]] != NO_EDGE && (cc + a[x[i - 1]][x[j]] < bestc || bestc == NO_EDGE)) {
                    swap(x,i,j);
                    cc += a[x[i - 1]][x[i]];
                    backTrack(i + 1);
                    cc -= a[x[i - 1]][x[i]];
                    swap(x,i,j);
                }
            }
        }
    }

    private void swap(int[]a, int x, int y) {
        int temp = a[x];
        a[x] = a[y];
        a[y] = a[temp];
    }
}
