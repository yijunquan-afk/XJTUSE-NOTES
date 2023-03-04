package cn.edu.xjtu.algorithm.chapter02;

/**
 * @author yjq
 * @version 1.0
 * @date 2021/9/25 20:38
 */
public class BinarySearch {
    //二分搜索算法
    int BinarySearch(int[] nums,int target){
        //找到target时返回其在数组中的位置，否则返回-1
        int left =0,right=nums.length-1;
        while(left<=right){
            int middle= left + (right - left) / 2; // 防止计算时溢出
            if(nums[middle]<target){
                left=middle+1;
            }else if(nums[middle]>target){
                right=middle-1;
            }else{
                return middle;
            }
        }
        return -1;
    }

}
