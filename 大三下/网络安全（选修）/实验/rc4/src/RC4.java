import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author yjq
 * @version 1.0
 * @date 2022/6/4 21:14
 */
public class RC4 {

    private int[] S;//S盒
    private char[] K;//密钥
    private char[] data;//数据
    private char[] key_stream;//密钥流

    /**
     * 加密
     * @param key       密钥
     * @param plaintext 明文
     * @return 密文
     */
    public String encrypt(String key, String plaintext) {
        return useRC4(key, plaintext);
    }

    /**
     * 解密
     * @param key        密钥
     * @param ciphertext 密文
     * @return 明文
     */
    public String decrypt(String key, String ciphertext) {
        return useRC4(key, ciphertext);
    }


    /**
     * 使用RC4
     * @param key    密钥
     * @param string 明文/密文
     * @return 密文/明文
     */
    private String useRC4(String key, String string) {
        this.data = string.toCharArray();
        this.key_stream = new char[data.length];
        this.K = key.toCharArray();
        //初始化S
        initS();
        //产生密钥流
        generateKeyStream();
        StringBuffer result = new StringBuffer();
        //逐字加密数据
        for (int i = 0; i < data.length; i++) {
            result.append((char) (string.charAt(i) ^ key_stream[i]));
        }
        //输出结果
        return result.toString();
    }

    /**
     * 交换S[i]和S[j]的值
     */
    private void swap(int[] S, int i, int j) {
        int temp = S[i];
        S[i] = S[j];
        S[j] = temp;
    }

    /**
     * 初始化S
     */
    public void initS() {
        S = new int[256];
        int[] T = new int[256];
        //初始化
        for (int i = 0; i < 256; i++) {
            S[i] = i;
            T[i] = K[i % K.length];
        }
        //S的初始置换
        int j = 0;
        for (int i = 0; i < 256; i++) {
            j = (j + S[i] + T[i]) % 256;
            swap(S, i, j);
        }
    }

    /**
     * 产生密钥流
     */
    public void generateKeyStream() {
        int i = 0, j = 0;
        for (int q = 0; q < data.length; q++) {
            i = (i + 1) % 256;
            j = (j + S[i]) % 256;
            swap(S, i, j);
            int t = (S[i] + S[j]) % 256;
            key_stream[q] = (char) (S[t]);
        }
    }

    private static String readFile(String file) {
        StringBuilder stringBuilder = new StringBuilder();

        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String tempString;
            while ((tempString = bufferedReader.readLine()) != null) {// 直接返回读取的字符串
                stringBuilder.append(tempString);
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();

    }

    public static void main(String[] args) {
        String key = "love";
        String plaintext = readFile("src/input.txt");
        RC4 test = new RC4();
        System.out.println("明文为：");
        System.out.println(plaintext);
        System.out.println("使用RC4加密后的密文：");
        String ciphertext = test.encrypt(key, plaintext);
        System.out.println(ciphertext);
        System.out.println("使用RC4解密该密文得到：");
        String plaintext_rc4 = test.decrypt(key, ciphertext);
        System.out.println(plaintext_rc4);
    }


}
