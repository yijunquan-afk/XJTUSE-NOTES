import javax.crypto.Cipher;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * Java RSA 加密工具类
 * 参考： https://blog.csdn.net/qy20115549/article/details/83105736
 */
public class RSAUtils {
    /**
     * 密钥长度 于原文长度对应 以及越长速度越慢
     */
    private final static int KEY_SIZE = 1024;
    /**
     * 用于封装随机产生的公钥与私钥
     */
    private static Map<Integer, String> keyMap = new HashMap<Integer, String>();

    /**
     * 随机生成密钥对
     */
//    public static void genKeyPair() throws NoSuchAlgorithmException {
//        // KeyPairGenerator类用于生成公钥和私钥对，基于RSA算法生成对象
//        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
//        // 初始化密钥对生成器
//        keyPairGen.initialize(KEY_SIZE, new SecureRandom());
//        // 生成一个密钥对，保存在keyPair中
//        KeyPair keyPair = keyPairGen.generateKeyPair();
//        // 得到私钥
//        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
//        // 得到公钥
//        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
//        String publicKeyString = Base64.getEncoder().encodeToString(publicKey.getEncoded());
//        // 得到私钥字符串
//        String privateKeyString = Base64.getEncoder().encodeToString(privateKey.getEncoded());
//        // 将公钥和私钥保存到Map
//        //0表示公钥
//        keyMap.put(0, publicKeyString);
//        //1表示私钥
//        keyMap.put(1, privateKeyString);
//    }

    /**
     * RSA公钥加密
     *
     * @param str       加密字符串
     * @param publicKey 公钥
     * @return 密文
     * @throws Exception 加密过程中的异常信息
     */
    public static String encrypt(String str, String publicKey) throws Exception {
        //base64编码的公钥
        byte[] decoded = Base64.getMimeDecoder().decode(publicKey);
        RSAPublicKey pubKey = (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(decoded));
        System.out.println(pubKey.getAlgorithm());
        //RSA加密
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);
        String outStr = Base64.getEncoder().encodeToString(cipher.doFinal(str.getBytes("UTF-8")));
        return outStr;
    }

    /**
     * RSA私钥解密
     *
     * @param str        加密字符串
     * @param privateKey 私钥
     * @return 明文
     * @throws Exception 解密过程中的异常信息
     */
    public static String decrypt(String str, String privateKey) throws Exception {
        System.out.println(str);
        //64位解码加密后的字符串
        byte[] inputByte = Base64.getMimeDecoder().decode(str);
        //        //base64编码的私钥
        byte[] decoded = Base64.getMimeDecoder().decode(privateKey);
        RSAPrivateKey priKey = (RSAPrivateKey) KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(decoded));
        //RSA解密
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, priKey);
        String outStr = new String(cipher.doFinal(inputByte));
        return outStr;
    }


    public static void main(String[] args) throws Exception {
        long temp = System.currentTimeMillis();
        String pu = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDlRZIi5KIDNNqlRx6uaeb8ZRd4BS1OGeFAQCO0oyBJkaZu7cQ24U2dEjrllj4H1Jo/gu+WQG" +
                "9O5o5GDSJkrgnuKxyHmOh5hUmG7zLHKQKyWiFcL4OfWYUZsrYO8igXmVGcRqyTnc6bNJUbXtgDYAWs5ufocoG0jJSvfvhoDR3fQQIDAQAB";
        String pr = "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBAOVFkiLkogM02qVHHq5p5vxlF3gFLU4Z4UBAI7SjIEmRpm7txDbhTZ0SOuWWPgfUmj+C75ZAb07mjkYNImSuCe4rHIeY6HmFSYbvMscpArJaIVwvg59ZhRmytg7yKBeZUZxGrJOdzps0lRte2ANgBazm5+hygbSMlK9++GgNHd9BAgMBAAECgYEAwCIaQkWJg9hiNsmv64fyO6dYEIt+GQpVGuvlQDbIEv8NE78LhNYwnaWuV8Idf6BqFzlaIHclEGDj786H+QlkZW3l+BnucMVIffYLUzEhGdv0QetDnra7ZPLoTw3hvaUXIqjZYwFMLFS6JF38ngmPM3bUYDlyP86Rii4wDa3Q/8ECQQD44ZbKhP9K1/9Wrf7Fxm25pv40oYMzZDYELEKn0kZP6oGkrS8Mv7ti0D13G7okp4/9fKZ62YxNKOtlf5ym6gkZAkEA69Rksvf41DF6ef7cC/A3H3yoB9y+tnVokoXkXpR3gLDVANXDlAMzGi3ewfOYoqQWXgVJwwd83AODjUniaZHEaQJBAMkz5un2t0TUdjKnG47cKhyqqlv2LtE/NIQPHBv8XKeC2Cg/cggGgQEThH1kRwoXBMtTGGu8sDY43IUDs6slDRECQCINRgkNgzFof43T/gP/SIZUYpLYDfWcmF/n02TQvTyoy+jV9OqPV7ak/vA0c0qwtMYYr2Do2N+P5lPlYjrqIFkCQQC4NpzYE7GqLlFlP58KM/ev1D3Rhl5JoCAbx8tl4l5/J9JSYcpNjnYF4rfAuDQ9b3GDh55guOlTa62UKd8V8f+x";
        keyMap.put(0, pu);
        keyMap.put(1, pr);

        //加密字符串
        System.out.println("公钥:" + keyMap.get(0));
        System.out.println("私钥:" + keyMap.get(1));
        System.out.println("生成密钥消耗时间:" + (System.currentTimeMillis() - temp) / 1000.0 + "秒");


        String message = "你好呀";
        System.out.println("原文:" + message);

        temp = System.currentTimeMillis();
        String messageEn = encrypt(message, keyMap.get(0));
        System.out.println("密文:" + messageEn);
        System.out.println("加密消耗时间:" + (System.currentTimeMillis() - temp) / 1000.0 + "秒");

        temp = System.currentTimeMillis();

        String messageDe = decrypt(messageEn, keyMap.get(1));
        System.out.println("解密:" + messageDe);
        System.out.println("解密消耗时间:" + (System.currentTimeMillis() - temp) / 1000.0 + "秒");
    }
}