package com.example.sample.common.utils;


import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class SecurityUtils {
    public static String alg = "AES/CBC/PKCS5Padding";
    private static final String key = "01234567890123456789012345678901";
    private final String iv = key.substring(0, 16); // 16byte
    private final String CHARTER_SET = "UTF-8";

    public String getAESEncrypt(String str) throws Exception {
        Cipher cipher = Cipher.getInstance(alg);
        SecretKeySpec keySpec = new SecretKeySpec(this.key.getBytes(), "AES");
        IvParameterSpec ivParamSpec = new IvParameterSpec(this.iv.getBytes());
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivParamSpec);

        byte[] encrypted = cipher.doFinal(str.getBytes(CHARTER_SET));
        return Base64.getEncoder().encodeToString(encrypted);
    }

    public String getAESDecrypt(String str) throws Exception {
        Cipher cipher = Cipher.getInstance(alg);
        SecretKeySpec keySpec = new SecretKeySpec(this.key.getBytes(), "AES");
        IvParameterSpec ivParamSpec = new IvParameterSpec(this.iv.getBytes());
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivParamSpec);

        byte[] encrypted = cipher.doFinal(str.getBytes(CHARTER_SET));
        return Base64.getEncoder().encodeToString(encrypted);

    }
}
