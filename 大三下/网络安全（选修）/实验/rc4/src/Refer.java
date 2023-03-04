import java.util.Arrays;

/**
 * @author yjq
 * @version 1.0
 * @date 2022/6/4 21:57
 */
public class Refer {
    private Integer[] S;
    private char[] K;
    private char[] data;//数据
    private char[] key_stream;//密钥流
    
    public String encrypt(final String plaintext, final String key) {
        Integer[] S = new Integer[256]; // S盒
        char[] keySchedul = new char[plaintext.length()]; // 生成的密钥流
        StringBuffer ciphertext = new StringBuffer();

        ksa(S, key);
        rpga(S, keySchedul, plaintext.length());

        for (int i = 0; i < plaintext.length(); i++) {
            ciphertext.append((char) (plaintext.charAt(i) ^ keySchedul[i]));
        }

        return ciphertext.toString();
    }

    //  KSA--密钥调度算法--利用key来对S盒做一个置换，也就是对S盒重新排列
    public void ksa(Integer[] s, String key) {
        for (int i = 0; i < 256; i++) {
            s[i] = i;
        }

        int j = 0;
        for (int i = 0; i < 256; i++) {
            j = (j + s[i] + key.charAt(i % key.length())) % 256;
            swap(s, i, j);
        }
    }

    //  RPGA--伪随机生成算法--利用上面重新排列的S盒来产生任意长度的密钥流
    public void rpga(Integer[] s, char[] keySchedul, int plaintextLength) {
        System.out.println(Arrays.toString(s));
        int i = 0, j = 0;
        for (int k = 0; k < plaintextLength; k++) {
            i = (i + 1) % 256;
            j = (j + s[i]) % 256;
            swap(s, i, j);
            keySchedul[k] = (char) (s[(s[i] + s[j]) % 256]).intValue();
        }
        System.out.println(Arrays.toString(keySchedul));
    }

    //  置换
    public void swap(Integer[] s, int i, int j) {
        Integer mTemp = s[i];
        s[i] = s[j];
        s[j] = mTemp;
    }

    public static void main(String[] args) {
        Refer test = new Refer();
        System.out.println(test.encrypt(test.encrypt("me", "love"),"love"));
    }
}
