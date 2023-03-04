package cn.edu.xjtu.algorithm.chapter04;

/**
 * @author yjq
 * @version 1.0
 * @date 2021/11/7 20:48
 */
public class GreedyRoom {
    public static int greedy(Activity[] activities) {
        int nums = activities.length;
        //活动数
        int count= activities.length;
        //计数器
        int free_time = 0;
        //会所可用时间
        int use_clubs = 0;
        //使用的会所数
        while (nums > 0) {
            for (int i = 0; i <count; i++) {
                if (activities[i].start > free_time && (!activities[i].flag) ){
                    //活动可安排
                    free_time = activities[i].end;
                    activities[i].flag = true;
                    nums--;
                }
            }
            free_time = 0;
            //初始化可用时间
            use_clubs++;
        }
        return use_clubs;
    }

    public static void main(String[] args) {
        Activity[] activities = new Activity[5];
        activities[0] = new Activity(1, 23);
        activities[1] = new Activity(12, 28);
        activities[2] = new Activity(25, 35);
        activities[3] = new Activity(27, 80);
        activities[4] = new Activity(36, 50);
        System.out.println(greedy(activities));

    }
}
