package cn.edu.xjtu.algorithm.chapter03;

/**
 * @author yjq
 * @version 1.0
 * @date 2021/11/30 14:54
 */
public class ImageCompress {
    //图像压缩问题

    /**
     * 计算十进制数i所需的二进制位数
     *
     * @param i
     * @return
     */
    static int length(int i) {
        int k = 1;
        i = i / 2;
        while (i > 0) {
            k++;
            i = i / 2;
        }
        return k;
    }

    /**
     * @param n
     * @param l [p1:pi]的最优分段中最后1个分段的像素个数
     * @param p p[p1:pn]，像素点灰度值序列
     * @param s 像素序列[p1:pi]的最优分段所需的存储位数
     * @param b 像素p[i]所需的存储位数
     */
    public static void Compress(int n, int[] p, int[] s, int[] l, int[] b) {
        int Lmax = 255;//每个分段的长度不超过255位
        int header = 11;//分段段头所需的位数,表示一个段的附加信息
        s[0] = 0;
        for (int i = 1; i <= n; i++) //[p1:pi]
        {
            b[i] = length(p[i]);
            int bmax = b[i];
            s[i] = s[i - 1] + bmax; //k=1
            l[i] = 1;
            for (int j = 2; j <= i && j <= Lmax; j++) //最后的1个分段中有j个像素
            {
                if (bmax < b[i - j + 1])
                    bmax = b[i - j + 1];//这一段中的最大位数
                if (s[i] > s[i - j] + j * bmax) {//找到更好的分段
                    s[i] = s[i - j] + j * bmax;
                    l[i] = j;
                }
            }
            s[i] += header;//加上额外开销
        }
    }


    public static int Traceback(int n, int i, int[] s, int[] l) {
        if (n == 0)
            return i;
        i = Traceback(n - l[n], i, s, l);
        s[i++] = n - l[n];// 重新为s[]数组赋值，用来存储分段位置
        return i;
    }

    static void Output(int s[], int l[], int b[], int n) {
        System.out.println("The optimal value is " + s[n]);
        int m = 0;
        m=Traceback(n, m, s, l);  //m:分段数
        s[m] = n;  //m个分段像素的累积和，Traceback算到m-1个
        System.out.println("Decompose into " + m + " segments");
        for (int j = 1; j <= m; j++) {
            l[j] = l[s[j]]; //计算第j个分段像素个数: l[j]
            b[j] = b[s[j]];  //计算第j个分段所需的存储位数: b[j]
        }
        for (int j = 1; j <= m; j++)
            System.out.println(l[j] + " " + b[j]);
    }


    public static void main(String[] args) {
        int p[] = {0,10,12,15,255,2,1};//第一位不算
        int N=p.length;
        int s[] = new int[N];
        int l[] = new int[N];
        int b[] = new int[N];
        Compress(N-1, p, s, l, b);
        Output(s, l, b, N-1);
    }
}
