/**
 * @author yjq
 * @version 1.0
 * @date 2022/6/8 20:02
 */
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.UnsupportedEncodingException;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * 3DES 工具类
 *
 * @author binbin.hou
 * @since 0.0.7
 */
public final class AES{
    /**
     * 算法名称
     *
     * @since 0.0.7
     */
    private static final String ALGORITHM = "AES";
//    public static String genAesSecret(){
//        try {
//            KeyGenerator kg = KeyGenerator.getInstance("AES");
//            //下面调用方法的参数决定了生成密钥的长度，可以修改为128, 192或256
//            kg.init(256);
//            SecretKey sk = kg.generateKey();
//            byte[] b = sk.getEncoded();
//            String secret = Base64.encodeBase64String(b);
//            return secret;
//        }
//        catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//            throw new RuntimeException("没有此算法");
//        }
//    }

    /**
     * 根据密钥对指定的明文plainText进行加密.
     *
     * @param plainBytes 明文
     * @param keyBytes   密码
     * @return 加密后的密文.
     * @since 0.0.8
     */
    public static byte[] encrypt(byte[] plainBytes, byte[] keyBytes) {
        try {
            SecretKey secretKey = getSecretKey(keyBytes);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return cipher.doFinal(plainBytes);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 根据密钥对指定的密文 cipherBytes 进行解密.
     *
     * @param cipherBytes 加密密文
     * @param keyBytes    秘钥
     * @return 解密后的明文.
     * @since 0.0.8
     */
    public static byte[] decrypt(byte[] cipherBytes, byte[] keyBytes) {
        try {
            SecretKey secretKey = getSecretKey(keyBytes);

            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return cipher.doFinal(cipherBytes);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取加密 key
     *
     * @param keySeed seed
     * @return 结果
     * @since 0.0.8
     */
    private static SecretKey getSecretKey(byte[] keySeed) {
        try {
            // 避免 linux 系统出现随机的问题
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(keySeed);
            KeyGenerator generator = KeyGenerator.getInstance("AES");
            generator.init(secureRandom);
            return generator.generateKey();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static void main(String[] args) throws UnsupportedEncodingException {
        String text = "我爱中华！";
        // 密钥, 256位32个字节
        //32位长
        String password = "uBdUx82vPHkDKb284d7NkjFoNcKWBuka";
        System.out.println(password.length());
        byte[] bytes = encrypt(text.getBytes(), password.getBytes());
        String encrypt = new String(bytes, "UTF-8");
        System.out.println(encrypt);
        String text2 = new String(decrypt(bytes, password.getBytes()), "UTF-8");
        System.out.println(text2);
    }

}

