```java
private int[] S;//S盒
private char[] K;//密钥
private char[] data;//数据
private char[] key_stream;//密钥流
```

```java
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
```

```java
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
```

```java
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
```

```
易俊泉 2194411245 软件92 西安交通大学。我把我的整个灵魂都给你，连同它的怪癖，耍小脾气，忽明忽暗，一千八百种坏毛病。它真讨厌，只有一点好，爱你。——from 王小波
```

```java
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
```

```
明文为：
易俊泉 2194411245 软件92 西安交通大学。我把我的整个灵魂都给你，连同它的怪癖，耍小脾气，忽明忽暗，一千八百种坏毛病。它真讨厌，只有一点好，爱你。——from 王小波
使用RC4加密后的密文：
轁亰2:V覃嬽丯邎妢孋〾戡扎拡癖敔丹炏鮭達绫佹￥輳咲家瘸悁癓；聂岇脐氖ﾷ彳曚忩昲￦乶印冺瘾禟圴殃畱ク密睾诣危￱厯枒仦炁妫＂牪佽ぷ―‚§.-3Y珧屯汞
使用RC4解密该密文得到：
易俊泉 2194411245 软件92 西安交通大学。我把我的整个灵魂都给你，连同它的怪癖，耍小脾气，忽明忽暗，一千八百种坏毛病。它真讨厌，只有一点好，爱你。——from 王小波
```

