```java
/***
* 利用Apache的工具类实现SHA-256加密
* @return str 加密后的报文
*/
public static String getSHA256Str(String str) {
    MessageDigest messageDigest;
    String encodeSir = str;
    try {
        messageDigest = MessageDigest.getInstance("SHA-256");
        byte[] hash = messageDigest.digest(str.getBytes(StandardCharsets.UTF_8));
        encodeSir = Hex.encodeHexString(hash);
    } catch (NoSuchAlgorithmException e) {
        e.printStackTrace();
    }
    return encodeSir;
}

/**
* 通过该方法将密码加密（实际上并没有）
*/
public static String encodePwd(String u_pwd) {
    // 密码通过此方法解密并再加密
    return getSHA256Str(u_pwd);
}
```



前端加密

```js
import { sha256 } from 'js-sha256';

/**
 * 加密方法
 */
export function PASSWORD(str) {
    let encodedStr = str;
    encodedStr = sha256(encodedStr);
    return encodedStr;
}
const login = () => {
  post("/user/login", {
    u_name: u_name.value,
    u_pwd: PASSWORD(u_pwd.value),
  })
    .then((res) => {
      tip.success(res.message);
      let token = res.data;
      setLocalToken(token);
      router.push({ name: "Room", query: { usr: u_name.value } });
    })
    .catch((err) => {
      tip.error("账号密码错误！");
    });
};
```





token

```java
private static String sign(String userId,String password){
    Algorithm algorithm = Algorithm.HMAC256(password);
    String token = JWT.create()
            .withClaim(CLAIM_USERID_NAME,userId)
            .withExpiresAt(new Date(System.currentTimeMillis()+EXPIRED_TIME/2))
            .sign(algorithm);
    return token;
}

/**
* 生成一个登录token
* @param userId
* @param password
* @return
*/
public static String loginSign(String userId,String password){
    String token = sign(userId,password);
    cache.putToken(token,token);
    return token;
}
```





```java
   /**
     * 验证客户端传来token是否有效
     * 验证逻辑顺序如下：
     * 1. token是否为空
     * 2. token中账号是否存在
     * 3. 根据token中账号从数据库中获取真实密码等用户信息，并验证用户信息是否有效
     */
    public static void verifyToken(String clientToken, stu.software.chatroom.common.CommonService commonService){
        if(!StringUtils.hasText(clientToken)){
            //token为空
            throw new RuntimeException("无登录令牌！");
        }

        //从客户端登录令牌中获取当前用户账号
        String userId = JWT.decode(clientToken).getClaim(CLAIM_USERID_NAME).asString();
        if(!StringUtils.hasText(userId)){
            //token中账号不存在
            throw new RuntimeException("登录令牌失效！");
        }

        //取出缓存中的登录令牌
        String cacheToken = cache.getToken(clientToken);
        if(!StringUtils.hasText(cacheToken)){
            //缓存中没有登录令牌
            throw new RuntimeException("登录令牌失效！");
        }

        User user = commonService.getUserById(userId);
        if(user==null){
            //用户不存在
            throw new RuntimeException("用户不存在！");
        }

        //验证Token有效性
        try{
            Algorithm algorithm = Algorithm.HMAC256(user.getU_pwd());
            JWTVerifier jwtVerifier = JWT.require(algorithm).withClaim(CLAIM_USERID_NAME,userId).build();//构建验证器
            jwtVerifier.verify(cacheToken);
        }catch(TokenExpiredException e){
            //令牌过期，刷新令牌
            String newToken = sign(userId,user.getU_pwd());
            cache.putToken(clientToken,newToken);
        }catch(Exception e){
            e.printStackTrace();
            //令牌验证未通过
            throw new RuntimeException("令牌错误！请登录。");
        }
```





```shell
C:\Users>keytool -genkeypair -alias yjq
输入密钥库口令:
再次输入新口令:
您的名字与姓氏是什么?
  [Unknown]:  易俊泉
您的组织单位名称是什么?
  [Unknown]:  西安交通大学
您的组织名称是什么?
  [Unknown]:  软件学院
您所在的城市或区域名称是什么?
  [Unknown]:  西安
您所在的省/市/自治区名称是什么?
  [Unknown]:  陕西
该单位的双字母国家/地区代码是什么?
  [Unknown]:  国家
CN=易俊泉, OU=西安交通大学, O=软件学院, L=西安, ST=陕西, C=国家是否正确?
  [否]:  是

输入 <yjq> 的密钥口令
        (如果和密钥库口令相同, 按回车):

C:\Users>keytool -list
输入密钥库口令:
密钥库类型: JKS
密钥库提供方: SUN

您的密钥库包含 1 个条目

yjq, 2022-6-9, PrivateKeyEntry,
证书指纹 (SHA1): 21:53:EF:02:17:DF:35:F0:C0:53:F4:A4:E8:52:2D:34:A3:41:29:10
```

```
C:\WINDOWS\system32>keytool -exportcert -alias yjq -file yjq.cer -rfc
输入密钥库口令:
存储在文件 <yjq.cer> 中的证书

Warning:
JKS 密钥库使用专用格式。建议使用 "keytool -importkeystore -srckeystore C:\Users\26969\.keystore -destkeystore C:\Users\26969\.keystore -deststoretype pkcs12" 迁移到行业标准格式 PKCS12。

C:\WINDOWS\system32>keytool -printcert -file yjq.cer
所有者: CN=易俊泉, OU=西安交通大学, O=软件学院, L=西安, ST=陕西, C=国家
发布者: CN=易俊泉, OU=西安交通大学, O=软件学院, L=西安, ST=陕西, C=国家
序列号: 746d457a
有效期为 Thu Jun 09 11:45:18 CST 2022 至 Wed Sep 07 11:45:18 CST 2022
证书指纹:
         MD5:  E4:F3:C9:5D:BC:B4:D7:0C:B6:C2:13:31:05:6D:3C:9E
         SHA1: 21:53:EF:02:17:DF:35:F0:C0:53:F4:A4:E8:52:2D:34:A3:41:29:10
         SHA256: F7:19:AA:A8:D7:BA:AE:DA:D0:78:A3:93:DA:AF:C8:7C:F5:C9:E0:89:FA:AB:52:69:2D:6B:71:CB:F2:E4:95:E7
签名算法名称: SHA256withDSA
主体公共密钥算法: 2048 位 DSA 密钥
版本: 3

扩展:

#1: ObjectId: 2.5.29.14 Criticality=false
SubjectKeyIdentifier [
KeyIdentifier [
0000: A2 39 55 8D 63 E1 38 64   BE FB 27 C9 35 B0 FD C0  .9U.c.8d..'.5...
0010: 75 63 09 51
```



```
keytool -genkeypair -alias yjqkey -keyalg RSA -keypass 123456 -keystore yjqkeystore.jks -storepass 123456
openssl req -newkey rsa:1024 -nodes -sha256 -keyout ./yjq.key -x509 -out ./yjq.crt -subj /C=CN/ST=SC/L=SC/O=nginx/CN=CJ -days 365
C:\WINDOWS\system32>keytool -list -keystore yjqkeystore.jks
输入密钥库口令:
密钥库类型: JKS
密钥库提供方: SUN

您的密钥库包含 1 个条目

yjqkey, 2022-6-9, PrivateKeyEntry,
证书指纹 (SHA1): B1:29:CC:D2:19:2D:C8:31:55:78:E8:CD:A1:CF:F4:01:E3:6E:01:C8
C:\WINDOWS\system32>keytool -list -rfc --keystore yjqkeystore.jks | openssl x509 -inform pem -pubkey
输入密钥库口令:  123456
-----BEGIN PUBLIC KEY-----
MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAglgi+SJ03JYi39kDDjj6
koW0CSDsEhiF/lwEOzO1jMy3kpyKhvvP+xpHwRWdz7GzN9S1IC7GELRhAIIzbKOu
9sSj+cgTOKXdLvWrtzwPs3rSSvkh4RjiwmYFQQGua5LH+XkIkdK72P9p2nhzPDMt
a5M170XtdjVPMmgJYuqcO+6qSVAJ2o7kMvRo6XNL5ZXnn0B+FY2PvOmK+OA4O1Ma
WKbGz2MKnJvLRMiCsie7eDBGWovWiSSKuTllXrO8nd41lTfLbcdPMs/iBunlJrqQ
J+bQ9ubYrMChzKcDaB8+P94vkS19EiJleoTPQMUb5j9Su4VrrLzuy2nUT3WT40Zu
2QIDAQAB
-----END
Warning:
PUBLIC KEYJKS 密钥库使用专用格式。建议使用 "keytool -importkeystore -srckeystore yjqkeystore.jks -destkeystore yjqkeystore.jks -deststoretype pkcs12" 迁移到行业标准格式 PKCS12。-----

-----BEGIN CERTIFICATE-----
MIIDjTCCAnWgAwIBAgIENHLQcTANBgkqhkiG9w0BAQsFADB3MQswCQYDVQQGEwJD
TjEPMA0GA1UECAwG6ZmV6KW/MQ8wDQYDVQQHDAbopb/lrokxFTATBgNVBAoMDOi9
r+S7tuWtpumZojEbMBkGA1UECwwS6KW/5a6J5Lqk6YCa5aSn5a2mMRIwEAYDVQQD
DAnmmJPkv4rms4kwHhcNMjIwNjA5MDQwMjM0WhcNMjIwOTA3MDQwMjM0WjB3MQsw
CQYDVQQGEwJDTjEPMA0GA1UECAwG6ZmV6KW/MQ8wDQYDVQQHDAbopb/lrokxFTAT
BgNVBAoMDOi9r+S7tuWtpumZojEbMBkGA1UECwwS6KW/5a6J5Lqk6YCa5aSn5a2m
MRIwEAYDVQQDDAnmmJPkv4rms4kwggEiMA0GCSqGSIb3DQEBAQUAA4IBDwAwggEK
AoIBAQCCWCL5InTcliLf2QMOOPqShbQJIOwSGIX+XAQ7M7WMzLeSnIqG+8/7GkfB
FZ3PsbM31LUgLsYQtGEAgjNso672xKP5yBM4pd0u9au3PA+zetJK+SHhGOLCZgVB
Aa5rksf5eQiR0rvY/2naeHM8My1rkzXvRe12NU8yaAli6pw77qpJUAnajuQy9Gjp
c0vlleefQH4VjY+86Yr44Dg7UxpYpsbPYwqcm8tEyIKyJ7t4MEZai9aJJIq5OWVe
s7yd3jWVN8ttx08yz+IG6eUmupAn5tD25tiswKHMpwNoHz4/3i+RLX0SImV6hM9A
xRvmP1K7hWusvO7LadRPdZPjRm7ZAgMBAAGjITAfMB0GA1UdDgQWBBSab4ETwTUz
2Rae3QN5YlN8rlKtcjANBgkqhkiG9w0BAQsFAAOCAQEAeUVCo7KQDjM+QojbQsaK
502UjA3lIE8hiUZwlUEyIuTa4WZNM5eoyJvZhL6+pozAQhLg83j+ffa1ieVOOYY5
BL5fqF7a7IBmPt6T+enNcVKU6ll6NJJZhPWMqzahDSIKjM2yqZ9z9Z5mACPccFgl
5LSP0iNv74Z07fMHpvDMiN7rrSrPbUTtwKvoMSXSwIg8xrJ1JyH6+txqFxLywz/P
CYGNj8w3UMP98cl+TWdfNKez/KDgcIOzGfs0jeaYnwtiRJGd46EMjdEIESKTM/hV
+msue6HS4Qi2gY76tCsoJ/P/V1ilIIiWMFhUkoHETeWbKepjvaP9pQeuVThrf63I
hA==
-----END CERTIFICATE-----
将其拷贝到文本public.key文件合并为一行

```

yjq.key中的文件

```
-----BEGIN PUBLIC KEY-----
MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAglgi+SJ03JYi39kDDjj6
koW0CSDsEhiF/lwEOzO1jMy3kpyKhvvP+xpHwRWdz7GzN9S1IC7GELRhAIIzbKOu
9sSj+cgTOKXdLvWrtzwPs3rSSvkh4RjiwmYFQQGua5LH+XkIkdK72P9p2nhzPDMt
a5M170XtdjVPMmgJYuqcO+6qSVAJ2o7kMvRo6XNL5ZXnn0B+FY2PvOmK+OA4O1Ma
WKbGz2MKnJvLRMiCsie7eDBGWovWiSSKuTllXrO8nd41lTfLbcdPMs/iBunlJrqQ
J+bQ9ubYrMChzKcDaB8+P94vkS19EiJleoTPQMUb5j9Su4VrrLzuy2nUT3WT40Zu
2QIDAQAB
-----END
-----BEGIN PRIVATE KEY-----
MIIEuwIBADANBgkqhkiG9w0BAQEFAASCBKUwggShAgEAAoIBAQCwBNxW9DZThYhB
gNHIjZIzwpVv5O0yPIe4ErehkstEtmklX2EoNwo4leToz88XmTRIynY/f4s4ASPz
h6WppJk5D5mw+t3AXCFNT7dNhV0pzPGLdZ7DXjvXV4KAf6tV/+VZQQmSsIPBgUBw
Dt/LK8m2R6GUDQMmbOJYstZGHp6EdlIs3QDqZegwWfIPCZ2OrpyBpY/RFRT6lzJu
w7dJASup/sHVuIGdEVBaNR8UqB66DYo7An90Je2kjxcwZ5ftfSrKnurJxSdgC9D4
29J59seZx65qEEY6cKwLtYg7yGbRa1m2qIy4wDz6lLq7aLdt4l1fPGuF+4VzkWFm
0wde5D1NAgMBAAECgf8gw76a/WsGoQxa84kAi/7vI5nrflSxF9FMqh7CboqVRfwW
4h/vmE88LNxQcailRjgtCjiZtjKdUONaHPyeiPaqBWB7rAVdNp/KxmjaoBuxOrDc
uoIO8IHL5HPoc1dGA6ijOLwmKMn6tPWWF+RjLV5MR6o1WpGQMF3Ie7M5uSrt2/rv
UeYgGhWnGv437ttqYAWStwO1jRHjzOzatQRP9DXfYcrjJ/Bh9FH/O0WAqwFFpbix
Kx1LeAb6MyVR51iTVzBEAqfBKiq8hVhCuAWVkE+FiZ90in18zXXnAYx1gtTNnRaw
dePPfTwDUPxJoLI6e3tMS5o89vHNnmDXRrHpRhkCgYEA32gcOVItNagH8CNF9U/S
Kj3OS2mUwHjv5iAgsdEiQmYPCMrpQRc9Vuj12pciftEzu/wm4kTXKztUpF0axbRI
biqI4sljEuOTlqfiiOr2CHhBJnzkPN4f+2J557dng7Etf44NQcxHigifsxyxsazn
hKEBSGbGRSyweWRVLq6AqyUCgYEAybLk8C3xpRPBTYN9woiQXDAzP9+LvcwhVpD9
vX1mWVKaZdfrshVTbPzynXnRZoZANulzrhr5gEjm87zPKEoNlUeiLMoy7q0F5MGI
pQh/8luSUYzklexPaM/4b1vlgFlr93bjelZEjkgdgKC/3F9diTX2huPXKrxhKoMc
/5VkhQkCgYEAg+KxLsGtgMyGiyQ3q38IwfkkrO5Sa2G5te/nfTHJ+MrJO1aWZt+y
TXlPklDAg386/FH6Hn+nSpVG/ZUdNfOdArRHhSKAQ2ln+nt3x3516kAbrf3yu2Ai
H0ReVQW3rU3OX0vb1akj3oACzzk0SYCaWHi4CibqC3I7aB5sDxfjS6UCgYAb/oBX
9Yh8aOmDYKSrw1iF8wqp6tTyWsqlwSn2itkRvK1BRV6pMNyLW/Awex/dIvT2qiAs
IgJPE886NRJrfRu1hGO147p99fXDcjkM7skTUjJUr0FqrYm3+mV4z0BjOAYMKqwX
5rFNLiUqHSWD6JiK2rqErkrG266AwAHeV1XEQQKBgCWApMaT9KadhCneaOJicNKY
30KOHsaa8xz9Gquc7LkAORLZtHUSE6CYxhigsOJyIfJ0SzrwGV9q5tgSGeCpNUHM
217MZEf5QsxOW+c7+hMhm6maGovKGDv5T+AU7gLoGKGMfaysjZa0LqSl+Sd0CaXZ
3nu+rWIR4n04wZtEJUxG
-----END PRIVATE KEY-----

```

```
keytool -genkeypair -alias mhykey -keyalg RSA -keypass 123456 -keystore mhykeystore.jks -storepass 123456
```

```
openssl req -newkey rsa:2048 -nodes -sha256 -keyout ./mhy.key -x509 -out ./mhy.crt -subj /C=CN/ST=SC/L=SC/O=nginx/CN=CJ -days 365
```





```

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
    public static void genKeyPair() throws NoSuchAlgorithmException {
        // KeyPairGenerator类用于生成公钥和私钥对，基于RSA算法生成对象
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
        // 初始化密钥对生成器
        keyPairGen.initialize(KEY_SIZE, new SecureRandom());
        // 生成一个密钥对，保存在keyPair中
        KeyPair keyPair = keyPairGen.generateKeyPair();
        // 得到私钥
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        // 得到公钥
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        String publicKeyString = Base64.getEncoder().encodeToString(publicKey.getEncoded());
        // 得到私钥字符串
        String privateKeyString = Base64.getEncoder().encodeToString(privateKey.getEncoded());
        // 将公钥和私钥保存到Map
        //0表示公钥
        keyMap.put(0, publicKeyString);
        //1表示私钥
        keyMap.put(1, privateKeyString);
    }

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
        byte[] decoded = Base64.getDecoder().decode(publicKey);
        RSAPublicKey pubKey = (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(decoded));
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
        byte[] inputByte = Base64.getDecoder().decode(str);
        //        //base64编码的私钥
        byte[] decoded = Base64.getDecoder().decode(privateKey);
        RSAPrivateKey priKey = (RSAPrivateKey) KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(decoded));
        //RSA解密
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, priKey);
        String outStr = new String(cipher.doFinal(inputByte));
        return outStr;
    }


    public static void main(String[] args) throws Exception {
        long temp = System.currentTimeMillis();
        String pu = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAjO8/xAHBCiqlfIz1ORA1\n" +
                "EhTOa72US5zGenkM6Pd9qiCk0rhoOQrZQNzOHoLmTXhj5ylhMYXU21i9105/qst6\n" +
                "VEcxBbiQqA9eSyIlWzLfIjzTsY/vDwflIv8ktHpyY2BkPfC+gG5SbzThGX6OZEzi\n" +
                "ceganMVE4EkydmG7VhSv4lBNQOJQycWlaocxOoHt51CHOLlxUdZtaMNMylhFppCQ\n" +
                "KM602JSHOy1wFisMQqTD7GpeD0zrbjAmjZaMeKY3d2+o5M6J+R4/GAUdYV6LYn2s\n" +
                "M4lXwKu+iaLlaUrcvdr72lxkqHFSEFtWvOC460F5wBFe8EglrvQ1jK/DvUvQuKYO\n" +
                "BQIDAQAB";
        String pr = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAN8UDDo8GqUU+tRD\n" +
                "4MKq27KIZEL+L7WZTcmQC/P5m6SBEC8prlmzqB0hl6kyOZ2qGX1jyxf+f3+5DrDm\n" +
                "FfzF2vr/4whNAiNTpMDZiGBQBNCW4SvusDlQqvUTIFRSsjcHlMboW7+1nHqRecHF\n" +
                "YKCD/rvLjuW0405YfUglSAGvjMZTAgMBAAECgYEAgNzx20oREj+2TZwQYCOW2eEU\n" +
                "Geb/5GV0m/UMEpmkITk1AimPv92aRYm8NE8BHWS/aMGW9ntbZ4qdQ+8F0Ijy32t6\n" +
                "ruEtM7qK/OhuWnF+a+tzjW0IEdnnj2DFftTbQ6/AubYlEoDegED5Z83fuYMpV1n8\n" +
                "vQEMAH17MGp76nZCjwECQQD0m39bj5+ulWSgcWqbIANImqOfap6PhlxNG6/2hS4y\n" +
                "UFisRDqbyFYnN0O7KeXGmeB3Rkv/VBMzuMGrPk2JQ3HBAkEA6XfaxAFgxHxGRvGI\n" +
                "SH3D0AfNGnoLpC2U6reLClOhA6cQ/iw1JWYMJ/rV88RKqJn591S9DKmGdqi06FbW\n" +
                "59aVEwJAAVUbg38ryoKr0qfrgb7R5cJZ49Nr27JKrGzbL4eYMdP4N7ADdbfIqFwH\n" +
                "tWxMASHzdBLg9RobJKv7+hfTykygwQJAJjTkCzGCiYUg5xk9yUczlUbBjPSuFQcU\n" +
                "mLjCu0TmU7eyczX+2IjzslM9UjAsW50fKBeo/Hp9s8t2F5Omhbg7GwJBANjP5xDl\n" +
                "xGSYA8RjBw1JMGY9BeZelK3LKKj/gdrQWEHSxY/O2Elhv6EacCUIsPEIJXb/Y0vt\n" +
                "R+CQ7EUVvzoLDP0=";
        keyMap.put(0, pu);
        keyMap.put(1, pr);
        //生成公钥和私钥
//        genKeyPair();
        //加密字符串
        System.out.println("公钥:" + keyMap.get(0));
        System.out.println("私钥:" + keyMap.get(1));
        System.out.println("生成密钥消耗时间:" + (System.currentTimeMillis() - temp) / 1000.0 + "秒");


        String message = "RSA测试ABCD~!@#$";
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
```



![image-20220609142852094](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/image-20220609142852094.png)

![image-20220609142918440](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/image-20220609142918440.png)

```
        String pu = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAjO8/xAHBCiqlfIz1ORA1" +
                "EhTOa72US5zGenkM6Pd9qiCk0rhoOQrZQNzOHoLmTXhj5ylhMYXU21i9105/qst6" +
                "VEcxBbiQqA9eSyIlWzLfIjzTsY/vDwflIv8ktHpyY2BkPfC+gG5SbzThGX6OZEzi" +
                "ceganMVE4EkydmG7VhSv4lBNQOJQycWlaocxOoHt51CHOLlxUdZtaMNMylhFppCQ" +
                "KM602JSHOy1wFisMQqTD7GpeD0zrbjAmjZaMeKY3d2+o5M6J+R4/GAUdYV6LYn2s" +
                "M4lXwKu+iaLlaUrcvdr72lxkqHFSEFtWvOC460F5wBFe8EglrvQ1jK/DvUvQuKYO" +
                "BQIDAQAB";
        String pr = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAN8UDDo8GqUU+tRD" +
                "4MKq27KIZEL+L7WZTcmQC/P5m6SBEC8prlmzqB0hl6kyOZ2qGX1jyxf+f3+5DrDm" +
                "FfzF2vr/4whNAiNTpMDZiGBQBNCW4SvusDlQqvUTIFRSsjcHlMboW7+1nHqRecHF" +
                "YKCD/rvLjuW0405YfUglSAGvjMZTAgMBAAECgYEAgNzx20oREj+2TZwQYCOW2eEU" +
                "Geb/5GV0m/UMEpmkITk1AimPv92aRYm8NE8BHWS/aMGW9ntbZ4qdQ+8F0Ijy32t6" +
                "ruEtM7qK/OhuWnF+a+tzjW0IEdnnj2DFftTbQ6/AubYlEoDegED5Z83fuYMpV1n8" +
                "vQEMAH17MGp76nZCjwECQQD0m39bj5+ulWSgcWqbIANImqOfap6PhlxNG6/2hS4y" +
                "UFisRDqbyFYnN0O7KeXGmeB3Rkv/VBMzuMGrPk2JQ3HBAkEA6XfaxAFgxHxGRvGI" +
                "SH3D0AfNGnoLpC2U6reLClOhA6cQ/iw1JWYMJ/rV88RKqJn591S9DKmGdqi06FbW" +
                "59aVEwJAAVUbg38ryoKr0qfrgb7R5cJZ49Nr27JKrGzbL4eYMdP4N7ADdbfIqFwH" +
                "tWxMASHzdBLg9RobJKv7+hfTykygwQJAJjTkCzGCiYUg5xk9yUczlUbBjPSuFQcU" +
                "mLjCu0TmU7eyczX+2IjzslM9UjAsW50fKBeo/Hp9s8t2F5Omhbg7GwJBANjP5xDl" +
                "xGSYA8RjBw1JMGY9BeZelK3LKKj/gdrQWEHSxY/O2Elhv6EacCUIsPEIJXb/Y0vt" +
                "R+CQ7EUVvzoLDP0=";
```



```java
keytool -genkeypair -storetype PKCS12 -alias yjq -keyalg EC -keysize 521 -sigalg SHA256withECDSA -dname "CN=易俊泉, OU=西安交通大学, O=软件学院, L=西安, ST=陕西, C=CN" -keystore D:\mygit\大三下笔记\网安课设\safechat-server\src\main\resources\keys-and-certs\yjq.keystore -keypass 123456 -storepass 123456 -validity 36500 -v

```

![image-20220609154130165](https://note-image-1307786938.cos.ap-beijing.myqcloud.com/typora/image-20220609154130165.png)

```
keytool -genkeypair -storetype PKCS12 -alias yjq -keyalg RSA -sigalg SHA256withRSA -keysize 1024  -dname "CN=易俊泉, OU=西安交通大学, O=软件学院, L=西安, ST=陕西, C=CN" -keystore D:\mygit\大三下笔记\网安课设\safechat-server\src\main\resources\keys-and-certs\yjq.keystore -keypass 123456 -storepass 123456 -validity 36500 -v
```

```
package stu.software.chatroom.common;

import org.springframework.core.io.ClassPathResource;

import javax.crypto.Cipher;
import java.io.*;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yjq
 * @version 1.0
 * @date 2022/6/9 14:54
 */
public class jksAndCerGenerator {
    public static void main(String[] args) throws IOException, InterruptedException, CertificateException {

        try {
            //从keystore提取公钥和私钥
            test();
//            exportKeysAndCertsFromKeyStore();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Map<String, String> keyPairString = new HashMap<String, String>();

    public static Map<String, String> getKeyPair() {
        return keyPairString;
    }

    public static void setKeyPair(Map<String, String> keyPairString) {
        jksAndCerGenerator.keyPairString = keyPairString;
    }

    public static void test() throws Exception {
        //以 PKCS12 规格，创建 KeyStore
        KeyStore keyStore = KeyStore.getInstance("PKCS12");
        //载入 jks 和该 jks 的密码 到 KeyStore 内
        keyStore.load(new FileInputStream(new ClassPathResource("keys-and-certs/yjq.keystore").getFile()), "123456".toCharArray());

        // 要获取 key，需要提供 KeyStore 的别名 和该 KeyStore 的密码
        // 获取 keyStore 内所有别名 alias
        Enumeration<String> aliases = keyStore.aliases();
        String alias = null;
        alias = aliases.nextElement();

        System.out.println("jks文件别名是：" + alias);
        char[] keyPassword = "123456".toCharArray();

        String msg = "RaviJun.东风广场.sig";
        keyPairString = null;

        PrivateKey privateKey = (PrivateKey) keyStore.getKey(alias, keyPassword);
        keyPairString.put("PR", new String(Base64.getEncoder().encode(privateKey.getEncoded())));
        ;
        Certificate certificate = keyStore.getCertificate(alias);
        PublicKey publicKey = certificate.getPublicKey();
        keyPairString.put("PU", new String(Base64.getEncoder().encode(publicKey.getEncoded())));
        System.out.println("===============privateKey==================\n" + keyPairString.get("PR"));
        System.out.println("==============publicKey===============\n" + keyPairString.get("PU"));

        //私钥签名
        byte[] signOne = sign(msg, privateKey);
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>签名后>>>>>>>>>>>>\n" + new String(Base64.getEncoder().encode(signOne)));

        //公钥验签
        boolean verifySign = verify(msg, signOne, publicKey);
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>验签结果>>>>>>>>>>>>>>>>\n" + verifySign);
        //公钥加密
        String jiami = encrypt(msg, keyPairString.get("PU"));
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>加密后>>>>>>>>>>>>\n" + jiami);

        //私钥解密
        String jiemi = decrypt(msg, keyPairString.get("PR"));
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>解密>>>>>>>>>>>>>>>>\n" + jiemi);

    }

    //私钥签名
    public static byte[] sign(String content, PrivateKey priKey) throws Exception {
        Signature signature = Signature.getInstance("RSA");
        signature.initSign(priKey);
        signature.update(content.getBytes());
        return signature.sign();
    }

    //公钥验签
    public static boolean verify(String content, byte[] sign, PublicKey pubKey) throws Exception {

        Signature signature = Signature.getInstance("RSA");
        signature.initVerify(pubKey);
        signature.update(content.getBytes());
        return signature.verify(sign);
    }

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

    /**
     * 导出证书、公钥、私钥
     */
    public static void exportKeysAndCertsFromKeyStore() throws Exception {

        //以 PKCS12 规格，创建 KeyStore
        KeyStore keyStore = KeyStore.getInstance("PKCS12");
        //载入 jks 和该 jks 的密码 到 KeyStore 内
        keyStore.load(new FileInputStream(new ClassPathResource("keys-and-certs/yjq.keystore").getFile()), "123456".toCharArray());

        // 要获取 key，需要提供 KeyStore 的别名 和该 KeyStore 的密码
        // 获取 keyStore 内所有别名 alias
        Enumeration<String> aliases = keyStore.aliases();
        String alias = null;

        //文档写入格式换行+Base64
        final String LINE_SEPARATOR = System.getProperty("line.separator");
        final Base64.Encoder encoder = Base64.getMimeEncoder(64, LINE_SEPARATOR.getBytes());

        while (aliases.hasMoreElements()) {
            alias = aliases.nextElement();
            System.out.println("jks文件别名是：" + alias);
            char[] keyPassword = "123456".toCharArray();

            //私钥
            String keyContent = "-----BEGIN PRIVATE KEY-----" + LINE_SEPARATOR + new String(encoder.encode(keyStore.getKey(alias, keyPassword).getEncoded())) + LINE_SEPARATOR + "-----END PRIVATE KEY-----";
            keyPairString.put("PR", new String(encoder.encode(keyStore.getKey(alias, keyPassword).getEncoded())));
            writeKeyOrCertToFile("./src/main/resources/keys-and-certs/" + alias + ".key", keyContent);

            Certificate certificate = keyStore.getCertificate(alias);

            String certificateContent = "-----BEGIN CERTIFICATE-----" + LINE_SEPARATOR + new String(encoder.encode(certificate.getEncoded())) + LINE_SEPARATOR + "-----END CERTIFICATE-----";
            //证书
            writeKeyOrCertToFile("./src/main/resources/keys-and-certs/" + alias + ".cer", certificateContent);

            PublicKey publicKey = certificate.getPublicKey();
            String cerContent = "-----BEGIN PUBLIC KEY-----" + LINE_SEPARATOR + new String(encoder.encode(publicKey.getEncoded())) + LINE_SEPARATOR + "-----END PUBLIC KEY-----";
            keyPairString.put("PU", new String(encoder.encode(publicKey.getEncoded())));
            //公钥
            writeKeyOrCertToFile("./src/main/resources/keys-and-certs/" + alias + ".pub", cerContent);

        }
        System.out.println(keyPairString.get("PU"));
        System.out.println(keyPairString.get("PR"));

    }

    /**
     * 创建文档流
     */
    public static void writeKeyOrCertToFile(String filePathAndName, String fileContent) throws IOException {

        FileOutputStream fos = new FileOutputStream(filePathAndName);
        OutputStreamWriter osw = new OutputStreamWriter(fos);
        BufferedWriter bw = new BufferedWriter(osw);
        bw.write(fileContent);
        bw.close();
    }


}

```

```
keytool -genkeypair -storetype PKCS12 -alias mhy -keyalg RSA -sigalg SHA256withRSA -keysize 1024  -dname "CN=麦海莹, OU=西安交通大学, O=软件学院, L=西安, ST=陕西, C=CN" -keystore D:\mygit\大三下笔记\网安课设\safechat-server\src\main\resources\keys-and-certs\mhy.keystore -keypass 123456 -storepass 123456 -validity 36500 -v
```



、

```
package stu.software.chatroom.websocket.impl;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import stu.software.chatroom.common.CommonService;
import stu.software.chatroom.common.Constants;
import stu.software.chatroom.common.RSAUtils;
import stu.software.chatroom.common.Utils;
import stu.software.chatroom.common.model.Message;
import stu.software.chatroom.websocket.SocketIOService;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import java.security.PublicKey;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Service
public class SocketIOServiceImpl implements SocketIOService {

    // 存放已连接的客户端
    private static Map<String, SocketIOClient> clientMap = new ConcurrentHashMap<>();
    // 存放用户的公钥
    private static Map<String, String> publicKeyStringMap = new ConcurrentHashMap<>();
    // 存放用户的公钥
    private static Map<String, PublicKey> publicKeyMap = new ConcurrentHashMap<>();
    //存访AES的密钥
    private static String AesKey;


    @Autowired
    private SocketIOServer socketIOServer;

    @Resource
    private CommonService commonService;

    /**
     * Spring IoC 容器创建之后, 在加载 SocketIOServiceImpl Bean 之后启动
     */
    @PostConstruct
    private void autoStartup() {
        start();
    }

    /**
     * Spring IoC 容器在销毁 SocketIOServiceImpl Bean 之前关闭
     * 避免重启项目服务端口占用问题
     */
    @PreDestroy
    private void autoStop() {
        stop();
    }

    @Override
    public void start() {
// 监听客户端连接
        socketIOServer.addConnectListener(client -> {
            String u_name = getParamsByClient(client, "u_name");
            clientMap.put(u_name, client);
            log.info("新建客户端连接: " + Utils.getIpByClient(client) + ", 用户名 :" + u_name);
            try {
                RSAUtils.genKeyPair(u_name);
            } catch (Exception e) {
                e.printStackTrace();
            }
            String publicKey = RSAUtils.getKeyPair().get("PU");
            log.info("密钥获取成功: " + publicKey);
            publicKeyStringMap.put(u_name, publicKey);
            publicKeyMap.put(u_name, RSAUtils.getPublicKey());
            log.info("目前公钥库中有: " + publicKeyMap.size() + " 个公钥");
        });

        // 监听客户端断开连接
        socketIOServer.addDisconnectListener(client -> {
            String u_name = getParamsByClient(client, "u_name");
            clientMap.remove(u_name);
            log.info("用户 " + u_name + " 已经断开连接");
        });


        // 监听客户端发送消息
        socketIOServer.addEventListener(Constants.EVENT_MESSAGE_TO_SERVER, String.class, (client, data, ackSender) -> {
            String sender_name = getParamsByClient(client, "u_name");
            ObjectMapper mapper = new ObjectMapper();
            Message message = mapper.readValue(data, Message.class);
            String receiver_name = message.getReceiver_name();
            String sign = new String(RSAUtils.sign(message.getContent(), RSAUtils.getPrivateKey()), "ISO-8859-1");
            message.setSign(sign);
            log.info("用户" + sender_name + "已经使用私钥对消息进行签名");
            String result = RSAUtils.encrypt(message.getContent(), publicKeyStringMap.get(receiver_name));
            log.info("使用用户" + receiver_name + "的公钥对消息进行加密：" + result);
            message.setContent(result);
            sendMessageToFriend(message.getReceiver_name(), message);
            System.out.println("发完了");
        });
        //
        //GBK,  GB2312，UTF-8等一些编码方式为多字节或者可变长编码，原来的字节数组就被改变了，再转回原来的byte[]数组就会发生错误了。
        //ISO-8859-1通常叫做Latin-1，Latin-1包括了书写所有西方欧洲语言不可缺少的附加字符，其中 0~127的字符与ASCII码相同，
        // 它是单字节的编码方式，在来回切换时不会出现错误。
        // 监听客户端发送消息
        socketIOServer.addEventListener("receive_triger", String.class, (client, data, ackSender) -> {

            ObjectMapper mapper = new ObjectMapper();
            Message message = mapper.readValue(data, Message.class);
            String sender_name = message.getSender_name();
            String receiver_name = message.getReceiver_name();
            log.info("收到来自" + sender_name + "发送给" + message.getReceiver_name() + "的消息: " + message.getContent());
            log.info("用户" + receiver_name + "使用用户" + sender_name + "的公钥对消息进行验证签名");
            Boolean sign = RSAUtils.verify(message.getContent(), message.getSign().getBytes("ISO-8859-1"), publicKeyMap.get(sender_name));
            System.out.println(sign);
            if (!sign) {
                throw new Exception("签名错误！");
            }
            String result = RSAUtils.decrypt(message.getContent(), RSAUtils.getKeyPair().get("PR"));
            log.info("用户" + receiver_name + "使用用户" + receiver_name + "的私钥对消息进行解密：" + result);
            receiveMessageFromFriend(message.getReceiver_name(), message);
        });
        // 启动服务
        socketIOServer.start();
    }


    @Override
    public void stop() {
        if (socketIOServer != null) {
            socketIOServer.stop();
            socketIOServer = null;
        }
    }

    /**
     * 发送信息给指定用户
     *
     * @param receiver_name
     * @param msg
     */
    private static void sendMessageToFriend(String receiver_name, Message msg) {
        SocketIOClient receiver_client = clientMap.get(receiver_name);
        System.out.println("sasa");
        if (receiver_client != null) {
            System.out.println("sasa");
            receiver_client.sendEvent(Constants.EVENT_MESSAGE_SEND, msg);
        }
    }

    /**
     * 发送信息给指定用户
     *
     * @param receiver_name
     * @param msg
     */
    private static void receiveMessageFromFriend(String receiver_name, Message msg) {
        SocketIOClient receiver_client = clientMap.get(receiver_name);
        if (receiver_client != null) {
            receiver_client.sendEvent(Constants.EVENT_MESSAGE_SEND, msg);
            log.info("saaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        }
    }

    /**
     * 获取客户端 url 中的参数
     *
     * @param client: 客户端
     * @return: 获取的参数
     */
    private String getParamsByClient(SocketIOClient client, String key) {
        Map<String, List<String>> params = client.getHandshakeData().getUrlParams();

        System.out.println(params.toString());

        List<String> userIdList = params.get(key);
        if (!CollectionUtils.isEmpty(userIdList)) {
            return userIdList.get(0);
        }
        return null;
    }

}

```





```
package stu.software.chatroom.common; /**
 * @author yjq
 * @version 1.0
 * @date 2022/6/8 20:02
 */


import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * 3DES 工具类
 *
 * @author binbin.hou
 * @since 0.0.7
 */
public final class AESUtils{
    /**
     * 算法名称
     *
     * @since 0.0.7
     */
    private static final String ALGORITHM = "AES";
    public static String genAesSecret(){
        try {
            KeyGenerator kg = KeyGenerator.getInstance("AES");
            //下面调用方法的参数决定了生成密钥的长度，可以修改为128, 192或256
            kg.init(256);
            SecretKey sk = kg.generateKey();
            byte[] b = sk.getEncoded();
            String secret = Base64.encodeBase64String(b);
            return secret;
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new RuntimeException("没有此算法");
        }
    }
    /**
     * 根据密钥对指定的明文plainText进行加密.
     *
     * @param plainText 明文
     * @param key  密码
     * @return 加密后的密文.
     * @since 0.0.8
     */
    public static String encrypt(String plainText, String key) {
        try {
            byte[] keyBytes = key.getBytes("ISO-8859-1");
            byte[] plainBytes = plainText.getBytes( "ISO-8859-1") ;
            SecretKey secretKey = getSecretKey(keyBytes);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return new String(cipher.doFinal(plainBytes),"ISO-8859-1");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 根据密钥对指定的密文 cipherBytes 进行解密.
     *
     * @param cipherText 加密密文
     * @param key    秘钥
     * @return 解密后的明文.
     * @since 0.0.8
     */
    public static String decrypt(String cipherText,String key ) {
        try {
            byte[] keyBytes = key.getBytes("ISO-8859-1");
            byte[] cipherBytes = cipherText.getBytes("ISO-8859-1");
            SecretKey secretKey = getSecretKey(keyBytes);

            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(cipherBytes),"ISO-8859-1");
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
        String password = genAesSecret();
        String encrypt = encrypt(text,password);
        System.out.println(encrypt);
        String text2 = decrypt(encrypt, password);
        System.out.println(text2);
    }
}


```

