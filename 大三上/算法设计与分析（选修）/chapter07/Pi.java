package cn.edu.xjtu.algorithm.chapter07;

import java.util.Random;

/**
 * @author yjq
 * @version 1.0
 * @date 2021/12/8 19:11
 */
public class Pi {
    static double Darts(int n){
        //用随机投点法计算Π值
        int k = 0;
        for (int i = 1; i <=n; i++) {
            double x = Math.random();
            double y = Math.random();
            if (x*x+y*y<=1)k++;
        }
        return (double)4*k/n;
    }

    public static void main(String[] args) {
//        System.out.println(Pi.Darts(10000000));
        int n = 10;
        int i = 3;
        for (int j = 0; j <100 ; j++) {
            int k= (int) (Math.random()*(n-i+1)+i);
            System.out.println(k);
        }

    }
}
