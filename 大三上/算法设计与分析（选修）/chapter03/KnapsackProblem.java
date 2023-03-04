package cn.edu.xjtu.algorithm.chapter03;

import cn.edu.xjtu.data_structure.chapter03_tree_and_binary_tree.btree.BinaryTreePtr;

/**
 * @author yjq
 * @version 1.0
 * @date 2021/11/30 16:59
 */
public class KnapsackProblem {
    //0-1背包问题

    /**
     *
     * @param v v[1:n]，物品i的价值
     * @param w w[1:n]，物品i的重量
     * @param c 背包容量
     * @param n
     * @param m m[i][j]，背包容量为j，可选物品为[i:n]时，0-1背包问题的最优值
     */
    public static void Knapsack(int[]v,int[]w,int c,int n,int[][]m){
        int jMax = Math.min(w[n]-1,c);
        for (int j = 0; j <=jMax; j++) {
            m[n][j]=0;//j<=c&&j<w[n]，物品n无法放入背包
        }
        for (int j = w[n]; j <=c; j++) {
            m[n][j]=v[n];//w[n]<=j<=c，物品n可以放入背包
        }//画边界，从后往前看
        for (int i = n-1; i >1 ; i--) {
            jMax = Math.min(w[i]-1,c);
            for (int j = 0; j <=jMax; j++) {
                m[i][j]=m[i+1][j];//物品i无法放入背包
            }
            for (int j = w[i]; j <=c ; j++) {//物品i可放入背包
                m[i][j]=Math.max(m[i+1][j],m[i+1][j-w[i]]+v[i]);
            }
        }
        m[1][c]=m[2][c];
        if (c>=w[1]){
            m[1][c]=Math.max(m[1][c],m[2][c-w[1]]+v[1]);
        }
    }

    /**
     * 求解
     * @param m
     * @param w
     * @param c
     * @param n
     * @param x 具体的解
     */
    public static void TraceBack(int[][]m, int[]w,int c,int n,int[]x){
        for(int i=1;i<n;i++)
            if(m[i][c]==m[i+1][c])
                x[i]=0;
            else
            {
                x[i]=1;
                c-=w[i];
            }
        x[n]=(m[n][c]>0)?1:0;
    }

    public static void main(String[] args) {
        int[]v={0,1,13,8,4,5,6,7};
        int[]w={0,2,3,1,4,1,5,1};
        int c = 10;
        int n = v.length;
        int[] x = new int[n];
        int[][]m=new int[n][c+1];
        Knapsack(v,w,c,n-1,m);
        TraceBack(m,w,c,n-1,x);
        for (int i = 1; i < n; i++) {
            System.out.println(x[i]+" ");
        }
    }
}
