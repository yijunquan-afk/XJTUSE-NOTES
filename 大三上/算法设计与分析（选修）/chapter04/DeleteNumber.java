package cn.edu.xjtu.algorithm.chapter04;

public class DeleteNumber {
    public static int greedyDelete(int number, int n) {
        String numString = number + "";
        String result = "";
        char[] nums = numString.toCharArray();
        while (n > 0) {
            int i = 0;
            while (nums[i] <= nums[i + 1])
                i++;
            if (i != numString.length()) {
                nums[i] = 'o';// 标记要删除
            }
        }
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] != '0')
                result += nums[j];
        }
        return Integer.parseInt(result);
    }

    public static void main(String[] args) {
        System.out.print(greedyDelete(178543, 4));
    }

}
