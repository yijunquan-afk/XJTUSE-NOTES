package cn.edu.xjtu.algorithm.chapter02;

import java.util.Scanner;

/**
 * @author yjq
 * @version 1.0
 * @date 2021/9/19 10:42
 */
public class Split {
    //整数划分
    public static void main(String[] args) {
        System.out.println("请输入的整数:");
        int input = new Scanner(System.in).nextInt();
        int num = split(input, input, input + "=");
        System.out.println(num == 0 ? "请您输入大于0的整数" : "您输入的整数是" + input);
        System.out.println(num == 0 ? "" : "该整数共有" + num + "种划分");
    }

    /**
     *
     * @param m->用户输入的整数
     * @param n->最大加数
     * @param string->用于打印结果临时存储
     * @return
     */
    public static int split(int m, int n, String string) {
        if (m <= 0 || n <= 0) {
            return 0;
        } else if (m == 1 || n == 1) {
            System.out.print(string);
            for (int i = 1; i < m; i++) {
                System.out.print("1+");
            }
            System.out.println("1");
            return 1;
        } else if (n == m) {
            System.out.println(string + m);
            return 1 + split(m, n - 1, string);
        } else if (m > n) {
            int n1 = split(m - n, n, string + n + "+");
            int n2 = split(m, n - 1, string);
            return n1 + n2;
        } else {
            return split(m, m, string);
        }
    }
}
