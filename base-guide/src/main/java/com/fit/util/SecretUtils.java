package com.fit.util;

/**
 * @author Stone (300482)
 * @version 1.0
 * @since 2020-07-13
 */
import org.apache.commons.lang.StringUtils;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class SecretUtils {

    public static final String DES = "DES";
    public static final String AES = "AES";

    private static Map<String, Integer> keySize = new HashMap<>();

    static {
        keySize.put(DES, 56);
        keySize.put(AES, 128);
    }

    public static String encrypt(String data, String key, String algorithm) {
        try {
            if (StringUtils.isBlank(data) || StringUtils.isBlank(key)) {
                return null;
            }
            byte[] content = data.getBytes("UTF-8");
            KeyGenerator kgen = KeyGenerator.getInstance(algorithm);
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(key.getBytes());
            kgen.init(keySize.get(algorithm), secureRandom);
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec keySpec = new SecretKeySpec(enCodeFormat, algorithm);
            Cipher cipher = Cipher.getInstance(algorithm);// 创建密码器
            cipher.init(Cipher.ENCRYPT_MODE, keySpec);// 初始化
            byte[] result = cipher.doFinal(content);
            return parseByte2HexStr(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String decrypt(String data, String key, String algorithm) {
        try {
            if (StringUtils.isBlank(data) || StringUtils.isBlank(key)) {
                return null;
            }
            byte[] content = parseHexStr2Byte(data);
            KeyGenerator kgen = KeyGenerator.getInstance(algorithm);
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(key.getBytes());
            kgen.init(keySize.get(algorithm), secureRandom);
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec keySpec = new SecretKeySpec(enCodeFormat, algorithm);
            Cipher cipher = Cipher.getInstance(algorithm);// 创建密码器
            cipher.init(Cipher.DECRYPT_MODE, keySpec);// 初始化
            byte[] result = cipher.doFinal(content);
            return new String(result, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将二进制转换成16进制
     *
     * @param buf
     * @return
     */
    private static String parseByte2HexStr(byte buf[]) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    private static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1) {
            return null;
        }
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }


    public static void main(String[]args){
//        String json = "{/'a/':2,/'b/:10'}";
//        String enstring = SecretUtils.encrypt(json, "zhy", "AES");
//        System.out.println(enstring);
        String destring = SecretUtils.decrypt("ju8pKg9TlmE7UZMS3luWzfovDxgFiFCItRt7kdsUJLY=", "zhy", "AES");
        System.out.println(destring);
        destring = SecretUtils.decrypt("ju8pKg9TlmE7UZMS3luWzfovDxgFiFCItRt7kdsUJLY=", "zhy", "DES");
        System.out.println(destring);
    }

}
