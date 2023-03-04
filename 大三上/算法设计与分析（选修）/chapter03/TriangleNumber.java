package cn.edu.xjtu.algorithm.chapter03;

import java.util.Scanner;

/**
 * @author yjq
 * @version 1.0
 * @date 2021/10/7 18:13
 */
public class TriangleNumber {
    //数字三角形算法
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        while(in.hasNext()) {
            int n=in.nextInt();
            int a[][]=new int[n][n];
            //创建一个新的数组b，用作表
            int b[][]=new int[n][n];
            for(int i=0;i<n;i++){
                for(int j=0;j<=i;j++){
                    a[i][j]=in.nextInt();
                }
            }
            //将a的最后一行直接写入b；
            for(int j=0;j<n;j++){
                b[n-1][j]=a[n-1][j];
            }
            //
            for(int i=n-2;i>=0;i--){
                for(int j=0;j<=i;j++){
                    //b[i][j]的值等于a[i][j]+此时（i,j)下面一行的正下方和右下方较大的值；
                    b[i][j]=a[i][j]+Math.max(b[i+1][j],b[i+1][j+1]);
                }
            }
            System.out.println(b[0][0]);

        }
    }

}
