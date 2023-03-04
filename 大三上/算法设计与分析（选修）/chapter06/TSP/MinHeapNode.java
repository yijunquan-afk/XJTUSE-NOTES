package cn.edu.xjtu.algorithm.chapter06.TSP;

/**
 * @author yjq
 * @version 1.0
 * @date 2021/11/10 19:02
 */
public class MinHeapNode {
    private int lcost;//子树费用的下界
    private int cc;//当前费用
    private int rcost;//x[s:n-1]中顶点最小出边费用和
    private int s;//根结点到当前结点的路径为x[0:s]
    private int[] x;//需要进一步搜索的顶点为x[s+1:n-1]
}
