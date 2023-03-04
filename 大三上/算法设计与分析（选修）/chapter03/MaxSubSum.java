package cn.edu.xjtu.algorithm.chapter03;

/**
 * @author yjq
 * @version 1.0
 * @date 2021/11/29 21:50
 */
public class MaxSubSum {
    /**
     * 使用分治法解决
     * @param a
     * @param left
     * @param right
     * @return
     */
    public static int soveByDivide(int[] a, int left, int right) {
        int sum = 0;
        if (left == right)
            sum = a[left] > 0 ? a[left] : 0;//判断正负
        else {
            int middle = (left + right) / 2;
            int leftSum = soveByDivide(a, left, middle);
            int rightSum = soveByDivide(a, middle + 1, right);//分治
            int s1 = 0;
            int lefts = 0;
            for (int i = middle; i >= left; i--) {
                lefts += a[i];
                if (lefts > s1)
                    s1 = lefts;
            }
            int s2 = 0;
            int rights = 0;
            for (int j = middle + 1; j <= right; j++) {
                rights += a[j];
                if (rights > s2)
                    s2 = rights;
            }
            sum = s1 + s2;
            if (sum < leftSum)
                sum = leftSum;
            if (sum < rightSum)
                sum = rightSum;
        }
        return sum;
    }

    /**
     * 动态规划
     * @param a
     * @return
     */
    public static int solveByDP(int[]a){
        int sum=0,b=0;
        for (int i = 0; i <a.length ; i++) {
            if (b>0)
                b+=a[i];
            else
                b=a[i];
            if (b>sum)
                sum=b;
        }
        return sum;
    }

    /**
     * 最大子矩阵和
     * @param m
     * @param n
     * @param a
     * @return
     */
   public static int MaxSum2(int m,int n,int[][]a){
        int sum=0;
        int[]b=new int[n];
        for(int i=0;i<m;i++){  //从第i行
            for(int k=0;k<n;k++)  //初始化数组b
                b[k]=0;
            for(int j=i;j<m;j++){  //到第j行
                for(int k=0;k<n;k++)
                    b[k]+=a[j][k];//按列取值
                int max=solveByDP(b);
                if(max>sum)
                    sum=max;
            }
        }
        return sum;
    }
    public static void main(String[] args) {
        int[] a = {-2, 11, -4, 13, -5, -2};
        System.out.println(MaxSubSum.soveByDivide(a, 0, a.length - 1));
        System.out.println(MaxSubSum.solveByDP(a));
        int[][]b={{1,2,3,4},{-1,2,-1,9},{1,2,-12,0}};
        System.out.println(MaxSubSum.MaxSum2(3,4,b));

    }
}
