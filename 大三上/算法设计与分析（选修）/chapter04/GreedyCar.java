package cn.edu.xjtu.algorithm.chapter04;

/**
 * @author yjq
 * @version 1.0
 * @date 2021/11/7 21:43
 */
public class GreedyCar {
    public static int greedyRefuel(int[]distance,int n){
        int minRefuel=0;
        int count = distance.length;
        for (int i = 0; i <count ; i++) {
            if (distance[i]>n){
                System.out.println("No Solution!");
                return -1;
            }
        }//判断各个加油站距离是否大于n
        for (int j = 0,temp=0; j < count; j++) {
            temp+=distance[j];
            //往后看，是否还可以行驶
            if (temp>n){
                minRefuel++;
                temp=distance[j];
                //在第j-1个加油站加油
            }
        }
        return minRefuel;
    }

    public static void main(String[] args) {
        int[] distance =new int[]{1,2,3,4,5,1,6,6};
        System.out.println(greedyRefuel(distance,7));
    }
}
