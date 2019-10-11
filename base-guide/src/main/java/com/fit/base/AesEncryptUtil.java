package com.fit.base;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author Stone (300482)
 * @version 1.0
 * @since 2019-10-11
 */
public class AesEncryptUtil {
//    //使用AES-128-CBC加密模式，key需要为16位,key和iv可以相同！
//    private static String KEY ="whshenke20180606";
//
//    private static String IV ="whshenke20180606";
//    /**
//     * 加密方法
//     * @param data  要加密的数据
//     * @param key 加密key
//     * @param iv 加密iv
//     * @return 加密的结果
//     * @throws Exception
//     */
//    public static String encrypt(String data, String key, String iv) throws Exception {
//        try {
//
//            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");//"算法/模式/补码方式"
//            int blockSize = cipher.getBlockSize();
//
//            byte[] dataBytes = data.getBytes();
//            int plaintextLength = dataBytes.length;
//            if (plaintextLength % blockSize != 0) {
//                plaintextLength = plaintextLength + (blockSize - (plaintextLength % blockSize));
//            }
//
//            byte[] plaintext = new byte[plaintextLength];
//            System.arraycopy(dataBytes, 0, plaintext, 0, dataBytes.length);
//
//            SecretKeySpec keyspec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
//            IvParameterSpec ivspec = new IvParameterSpec(iv.getBytes());
//
//            cipher.init(Cipher.ENCRYPT_MODE, keyspec, ivspec);
//            byte[] encrypted = cipher.doFinal(plaintext);
//
//            return Base64Util.encode(encrypted);
//
//
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//    /**
//     * 解密方法
//     * @param data 要解密的数据
//     * @param key  解密key
//     * @param iv 解密iv
//     * @return 解密的结果
//     * @throws Exception whshenke20180606
//     */
//    public static String desEncrypt(String data, String key, String iv)  {
//        try {
//            byte[] encrypted1 = Base64Util.decode(data);
//
//            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
//            SecretKeySpec keyspec = new SecretKeySpec(key.getBytes(), "AES");
//            IvParameterSpec ivspec = new IvParameterSpec(iv.getBytes());
//
//            cipher.init(Cipher.DECRYPT_MODE, keyspec, ivspec);
//
//            byte[] original = cipher.doFinal(encrypted1);
//            String originalString = new String(original, "UTF-8");
//            return originalString;
//        } catch (Exception e) {
//            //e.printStackTrace();
//            return null;
//        }
//
//
//    }
//
//    /**
//     * 使用默认的key和iv加密
//     * @param data
//     * @return
//     * @throws Exception
//     */
//    public static String encrypt(String data) throws Exception {
//        return encrypt(data, KEY, IV);
//    }
//
//    /**
//     * 使用默认的key和iv解密
//     * @param data
//     * @return
//     * @throws Exception
//     */
//    public static String desEncrypt(String data){
//        return desEncrypt(data, KEY, IV);
//    }
//
//
//
//    /**
//     * 测试
//     */
//    public static void main(String args[]) throws Exception {
//        //KNnAbiCvFxispeG+wS01jA==
//        //KNnAbiCvFxispeG+wS01jA==
//        //KNnAbiCvFxispeG+wS01jA==
//        String test = "测试";
//
//        String data = null;
//        String key = "dufy20170329java";
//        String iv = "dufy20170329java";
//
//        //    data = encrypt(test, key, iv);
////KNnAbiCvFxispeG+wS01jA==
//
//        System.out.println(data);
//        System.out.println(desEncrypt("8XguKV2aprVwg4UqEie7f9gkr7xDaSKrGZZD7x1J3uISWkvQAtpq8hpkskwi V+8bJmRcmQWIZ4sRom37GB7w7O4AH+HjH5ixViJ7k6bx+oEFVWSmIqKEj4e5 lMcyXgcXpTB8IGnt1WKS2p7Qnn+cjA==", key, iv));
//    }
}
