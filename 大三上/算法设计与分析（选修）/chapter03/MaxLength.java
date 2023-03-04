package cn.edu.xjtu.algorithm.chapter03;

/**
 * @author yjq
 * @version 1.0
 * @date 2021/11/29 20:52
 */
public class MaxLength {
    /**
     * 计算最长公共子序列
     *
     * @param x 序列数组
     * @param y 序列数组
     * @param c 存储x[1:i]和y[1:j]的最长公共子序列的长度
     * @param b 记录上面c[i][j]的值是由哪个子问题的解得到的
     */
    public static void LCSLength(char[] x, char[] y, int[][] c, int[][] b) {
        int m = x.length - 1;
        int n = y.length - 1;
        for (int i = 1; i <= m; i++) {
            c[i][0] = 0;
        }
        for (int i = 1; i <= n; i++) {
            c[0][i] = 0;
        }//初始化
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (x[i] == y[j]) {
                    c[i][j] = c[i - 1][j - 1] + 1;
                    b[i][j] = 1;
                    //表示Xi和Yi的最长公共子序列是由Xi-1和Yi-1的最长公共子序列在尾部加上xi所得到的。
                } else if (c[i - 1][j] >= c[i][j - 1]) {
                    c[i][j] = c[i - 1][j];
                    b[i][j] = 2;
                    //表示Xi和Yi的最长公共子序列与Xi-1和Yi的最长公共子序列相同
                } else {
                    c[i][j] = c[i][j - 1];
                    b[i][j] = 3;
                    //表示Xi和Yi的最长公共子序列与Xi和Yi-1的最长公共子序列相同
                }
            }
        }
    }

    public static void MyLCSLength(char[] x, char[] y, int[][] c, int[][] b) {
        int m = x.length - 1;
        int n = y.length - 1;
        for (int i = 1; i <= m; i++) {
            c[i][0] = 0;
        }
        for (int j = 1; j <= n; j++) {
            c[0][j] = 0;
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (x[i] == y[j]) {
                    c[i][j] = c[i - 1][j - 1] + 1;
                    b[i][j] = 1;
                } else if (c[i][j - 1] >= c[i - 1][j]) {
                    c[i][j] = c[i][j - 1];
                    b[i][j] = 2;
                } else {
                    c[i][j] = c[i - 1][j];
                    b[i][j] = 3;
                }
            }
        }
    }

    public static void LCS(int m, int n, char[] x, int[][] b) {
        if (m == 0 || n == 0) {
            return;
        }
        if (b[m][n] == 1) {
            LCS(m - 1, n - 1, x, b);
            System.out.print(x[m]);
        } else if (b[m][n] == 2) {
            LCS(m - 1, n, x, b);
        } else {
            LCS(m, n - 1, x, b);
        }
    }

    public static void main(String[] args) {
        char[] x = "*abdscde".toCharArray();
        char[] y = "*bcde".toCharArray();
        int m = x.length;
        int n = y.length;
        int[][] c = new int[m][n];
        int[][] b = new int[m][n];
        MyLCSLength(x, y, c, b);
        System.out.println(c[m - 1][n - 1]);
        LCS(m - 1, n - 1, x, b);
    }
}
