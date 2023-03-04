package cn.edu.xjtu.algorithm.chapter03;

/**
 * @author yjq
 * @version 1.0
 * @date 2021/10/7 16:15
 */
public class YachtCent {
    //租用游艇问题

    /**
     * 长江游艇俱乐部在长江上设置了n 个游艇出租站{1,2,…,n}。
     * 游客可在这些游艇出租站租用游艇，并在下游的任何一个游艇
     * 出租站归还游艇。游艇出租站 i 到游艇出租站 j 之间的租金为r(i,j),1≤i<j≤n。
     * 试设计一个算法，计算出从游艇出租站 1 到游艇出租站 n 所需的最少租金
     */
    void cent(int[][] m, int n, int[][] s) {
        for (int i = 1; i <=n ; i++) {
            m[i][i]=0;
        }
        for (int r = 2; r <= n; r++)
            for (int i = 1; i <= n - r + 1; i++) {
                int j = i + r - 1;
                s[i][j] = i;
                for (int k = i; k <= j; k++) {
                    int temp = m[i][k] + m[k][j];
                    if (temp < m[i][j]) {
                        m[i][j] = temp;
                        s[i][j] = k;//在第k站下
                    }
                }
            }
    }

    void traceBack(int i, int j, int[][] s) {

        if (i == j) {
            System.out.print(i);
            return;
        }
        System.out.print("[");
        traceBack(i, s[i][j], s);
        traceBack(s[i][j] + 1, j, s);
        System.out.print("]");
    }

    public static void main(String[] args) {
        int n;
        int[][] m = new int[4][4];
        int[][] s = new int[4][4];
        int[][] r = new int[][]{
                {0, 0, 0, 0}, {0, 0, 5, 15}, {0, 0, 0, 7}, {0, 0, 0, 0}
        };
        YachtCent test = new YachtCent();
        test.cent(r, 3, s);
        test.traceBack(1, 3, s);
    }

}
