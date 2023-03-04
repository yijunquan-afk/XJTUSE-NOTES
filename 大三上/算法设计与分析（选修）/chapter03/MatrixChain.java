package cn.edu.xjtu.algorithm.chapter03;

/**
 * @author yjq
 * @version 1.0
 * @date 2021/10/7 13:41
 */
public class MatrixChain {
    //矩阵连乘动态规划
    void matrixMutiply(int[] p, int n, int[][] m, int[][] s) {
        for (int i = 1; i <= n; i++) {
            //单一矩阵，无需计算
            m[i][i] = 0;
        }
        for (int r = 2; r <= n; r++) {//对角线以上开始计算
            for (int i = 1; i <= n - r + 1; i++) {//从(1,2)(2,3)(3,4)...开始
                int j = r + i - 1;
                //找m[i][j]的最小值，先初始化一下，令k=i
                m[i][j] = m[i + 1][j] + p[i - 1] * p[i] * p[j];
                s[i][j] = i;//记录分割点
                //k从i+1到j-1循环找m[i][j]的最小值
                for (int k = i + 1; k < j; k++) {//k为分割点
                    int temp = m[i][k] + m[k + 1][j] + p[i - 1] * p[k] * p[j];
                    if (temp < m[i][j]) {
                        m[i][j] = temp;
                        s[i][j] = k;
                    }
                }
            }
        }
    }

    int MemoizedMatrixChain(int[] p, int n, int[][] m, int[][] s) {
        for (int i = 1; i <= n; i++)
            for (int j = i; j <= n; j++)
                m[i][j] = 0;
        //0表示相应的子问题还末被计算
        return LookupChain(1, n, p, m, s);
    }

    int LookupChain(int i, int j, int[] p, int[][] m, int[][] s) {
        if (m[i][j] > 0) //大于0表示其中存储的是所要求子问题的计算结果
            return m[i][j];  //直接返回此结果即可
        if (i == j)
            return 0;
        int u = LookupChain(i, i, p, m, s) + LookupChain(i + 1, j, p, m, s) + p[i - 1] * p[i] * p[j];
        s[i][j] = i;
        for (int k = i + 1; k < j; k++) {
            int t = LookupChain(i, k, p, m, s) + LookupChain(k + 1, j, p, m, s) + p[i - 1] * p[k] * p[j];
            if (t < u) {
                u = t;
                s[i][j] = k;
            }
        }
        m[i][j] = u;
        return u;
    }

    void traceBack(int i, int j, int[][] s) {
        if (i == j) {
            System.out.print("A" + i);
            return;
        } else {
            System.out.print("(");
            traceBack(i, s[i][j], s);
            traceBack(s[i][j] + 1, j, s);
            System.out.print(")");
        }
    }

    public static void main(String[] args) {
        int[] p = {30, 35, 15, 5, 10, 20, 25};
        int n = 6;
        int[][] m = new int[7][7];
        int[][] s = new int[7][7];
        MatrixChain test = new MatrixChain();
        test.MemoizedMatrixChain(p, n, m, s);
//        test.matrixMutiply(p,n,m,s);
        test.traceBack(1, 6, s);
        System.out.println();
        System.out.println(m[1][6]);
    }
}
