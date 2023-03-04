package cn.edu.xjtu.algorithm.chapter03;

import java.util.Scanner;

/**
 * @author yjq
 * @version 1.0
 * @date 2021/10/9 20:11
 */
public class MaxKProduct {
    void cal(int I, int[][] num) {
        //将整数I的i到j位形成的整数存储在num数组中
        for (int i = num.length-1; i > 0; i--) {
            num[i][i] = I % 10;
            I = I / 10;
        }//先计算第i位的数值
        for (int i = 1; i <= num.length-1; i++) {
            for (int j = i + 1; j <= num.length-1; j++) {
                num[i][j] = num[i][j - 1] * 10 + num[j][j];
            }//不全num数组
        }
    }

    void fun(int[][] f, int[][] num) {
        for (int i = 1; i <= num.length-1; i++) {
            for (int j = 1; j <= i; j++) {//遍历分段数
                if (j == 1) {//初始子问题
                    f[i][1] = num[1][i];
                    continue;
                }
                for (int p = 1; p < i; p++)//自顶向上递归
                    f[i][j] = Math.max(f[i][j], f[p][j - 1] * num[p + 1][i]);
            }
        }
    }

    public static void main(String[] args) {
        MaxKProduct test = new MaxKProduct();
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();
        int I = in.nextInt();
        int[][] f = new int[n+1][n+1];
        int[][] num = new int[n+1][n+1];
        test.cal(I,num);
        test.fun(f,num);
        System.out.println(f[n][1]);
    }
}
