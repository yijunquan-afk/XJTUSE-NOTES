// package cn.edu.xjtu.algorithm.chapter03;

// import java.io.*;

// /**
// * @author yjq
// * @version 1.0
// * @date 2021/11/4 1:05
// */
// public class MiGong {
// private static final char WALL_CHAR = '��';
// private static final char ROAD_CHAR = '��';//�ַ�����
// public static void main(String[] args) throws IOException {
// File file = new File("src/maze1.txt");
// Reader reader = null;
// int[] number = new int[5];
// StreamTokenizer stk = new StreamTokenizer(new FileReader("src/maze1.txt"));
// int nextToken = stk.nextToken();
// for (int i = 0; i < 5; i++) {
// if (stk.ttype == StreamTokenizer.TT_NUMBER) {
// number[i] = (int) stk.nval;
// }
// nextToken = stk.nextToken();
// }//��ȡ�ļ��е�ǰ�������

// int mazeSize = number[0];//�Թ���С
// int startX = number[1];
// int startY = number[2];
// int endX = number[3];
// int endY = number[4];
// int[][] maze = new int[mazeSize][mazeSize];
// try {
// reader = new InputStreamReader(new FileInputStream(file));
// int tempchar;
// int i = -3, j = 0;
// while ((tempchar = reader.read()) != -1) {
// if ((char) tempchar == WALL_CHAR) {
// maze[i][j] = 1;
// j++;
// } else if ((char) tempchar == ROAD_CHAR) {
// maze[i][j] = 0;
// j++;
// } else if ((char) tempchar == '\n') {
// i++;
// j = 0;
// }
// }//ת��Ϊ0��1����
// reader.close();
// } catch (Exception e) {
// e.printStackTrace();
// }
// for (int i = 0; i < mazeSize; i++) {
// for (int j = 0; j < mazeSize; j++) {
// System.out.print(maze[i][j] + " ");
// }
// System.out.println();
// }
// System.out.println();
// setWay2(maze,startY,startX);

// }

// // ʹ�õݹ��������С����·
// /*
// * ˵�� 1.map : ��ͼ
// * 2.i,j : �ӵ�ͼ���Ǹ�λ�ÿ�ʼ
// * 3.���С���ܵ�map[6][5]λ��,��˵��ͨ·�ҵ�
// * 4.Լ�� :��mapp[i][j]
// * Ϊ 0 ��ʾ�õ�û���߹�,
// * Ϊ1��ʾǽ;
// * 2 ��ʾͨ·������;
// * 3:��λ���Ѿ��߹�
// * 5.�����Թ�ʱ,��Ҫȷ��һ������(����)
// * ��-> �� -> �� --> ��,����õ��߲�ͨ,�ٻ���
// */
// /**
// * @param map
// * ��ʾ��ͼ
// * @param i
// * ���ĸ�λ�ÿ�ʼ��
// * @param j
// * @return �ҵ�ͨ·��������򷵻ؼ�
// */
// // �޸���·�Ĳ��� , �ĳ� �� --> �� --> �� -->��
// public static boolean setWay2(int[][] map, int i, int j) {
// if (i<0||j<0||i==16||j==16)return false;
// if (map[15][15] == 2) { // ͨ·�Ѿ��ҵ� ok
// return true;
// } else {
// if (map[i][j] == 0) { // �����ǰ�ĵ㻹û���߹�
// // ���ղ��� �� --> �� --> �� -->��
// map[i][j] = 2; // �ٶ��õ��ǿ�����ͨ��
// if (setWay2(map, i - 1, j)) { // ������
// System.out.println("("+j+","+i+")");
// return true;
// } else if (setWay2(map, i, j + 1)) { // ��
// System.out.println("("+j+","+i+")");
// return true;
// } else if (setWay2(map, i + 1, j)) { // ��
// System.out.println("("+j+","+i+")");
// return true;
// } else if (setWay2(map, i, j - 1)) { // ��
// System.out.println("("+j+","+i+")");
// return true;
// } else {
// // ˵���õ����߲�ͨ��,����·
// map[i][j] = 3;
// return false;
// }
// } else { // map[i][j] ==0,������1,2,3
// return false;
// }
// }
// }
// }
