package com.example.demo.test;

/**
 * Copyright (c) 2005-2012 springside.org.cn
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */


import com.example.demo.test.*;

import java.security.GeneralSecurityException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
/**
 * 支持HMAC-SHA1消息签名 �?DES/AES对称加密的工具类.
 *
 * 支持Hex与Base64两种编码方式.
 *
 * @author calvin
 */
public class CryptosUtil {
    private static final String AES = "AES";
    private static final String AES_CBC = "AES/CBC/PKCS5Padding";
    private static final String HMACSHA1 = "HmacSHA1";

    private static final int DEFAULT_HMACSHA1_KEYSIZE = 160; // RFC2401
    private static final int DEFAULT_AES_KEYSIZE = 128;
    private static final int DEFAULT_IVSIZE = 16;

    private static SecureRandom random = new SecureRandom();

    // -- HMAC-SHA1 funciton --//
    /**
     * 使用HMAC-SHA1进行消息签名, 返回字节数组,长度�?0字节.
     *
     * @param input
     *            原始输入字符数组
     * @param key
     *            HMAC-SHA1密钥
     */
    // public static byte[] hmacSha1(byte[] input, byte[] key) {
    // try {
    // SecretKey secretKey = new SecretKeySpec(key, HMACSHA1);
    // Mac mac = Mac.getInstance(HMACSHA1);
    // mac.init(secretKey);
    // return mac.doFinal(input);
    // } catch (GeneralSecurityException e) {
    // //throw Exceptions.unchecked(e);
    // return null;
    // }
    // }
    //
    // /**
    // * 校验HMAC-SHA1签名是否正确.
    // *
    // * @param expected 已存在的签名
    // * @param input 原始输入字符�?
    // * @param key 密钥
    // */
    // public static boolean isMacValid(byte[] expected, byte[] input, byte[]
    // key) {
    // byte[] actual = hmacSha1(input, key);
    // return Arrays.equals(expected, actual);
    // }

    /**
     * 生成HMAC-SHA1密钥,返回字节数组,长度�?60�?20字节). HMAC-SHA1算法对密钥无特殊要求,
     * RFC2401建议�?��长度�?60�?20字节).
     */
    // public static byte[] generateHmacSha1Key() {
    // try {
    // KeyGenerator keyGenerator = KeyGenerator.getInstance(HMACSHA1);
    // keyGenerator.init(DEFAULT_HMACSHA1_KEYSIZE);
    // SecretKey secretKey = keyGenerator.generateKey();
    // return secretKey.getEncoded();
    // } catch (GeneralSecurityException e) {
    // //throw Exceptions.unchecked(e);
    // return null;
    // }
    // }

    // -- AES funciton --//
    /**
     * 使用AES加密原始字符�?
     *
     * @param input
     *            原始输入字符数组
     * @param key
     *            符合AES要求的密�?
     */
    public static byte[] aesEncrypt(byte[] input, byte[] key) {
        return aes(input, key, Cipher.ENCRYPT_MODE);
    }

    /**
     * 使用AES加密原始字符�?
     *
     * @param input
     *            原始输入字符数组
     * @param key
     *            符合AES要求的密�?
     * @param iv
     *            初始向量
     */
    public static byte[] aesEncrypt(byte[] input, byte[] key, byte[] iv) {
        return aes(input, key, iv, Cipher.ENCRYPT_MODE);
    }

    /**
     * 使用AES解密字符�? 返回原始字符�?
     *
     * @param input
     *            Hex编码的加密字符串
     * @param key
     *            符合AES要求的密�?
     */
    public static String aesDecrypt(byte[] input, byte[] key) {
        byte[] decryptResult = aes(input, key, Cipher.DECRYPT_MODE);
        return new String(decryptResult);
    }

    /**
     * 使用AES解密字符�? 返回原始字符�?
     *
     * @param input
     *            Hex编码的加密字符串
     * @param key
     *            符合AES要求的密�?
     * @param iv
     *            初始向量
     */
    public static String aesDecrypt(byte[] input, byte[] key, byte[] iv) {
        byte[] decryptResult = aes(input, key, iv, Cipher.DECRYPT_MODE);
        return new String(decryptResult);
    }

    /**
     * 使用AES加密或解密无编码的原始字节数�? 返回无编码的字节数组结果.
     *
     * @param input
     *            原始字节数组
     * @param key
     *            符合AES要求的密�?
     * @param mode
     *            Cipher.ENCRYPT_MODE �?Cipher.DECRYPT_MODE
     */
    private static byte[] aes(byte[] input, byte[] key, int mode) {
        try {
            SecretKey secretKey = new SecretKeySpec(key, AES);
            Cipher cipher = Cipher.getInstance(AES);
            cipher.init(mode, secretKey);
            return cipher.doFinal(input);
        } catch (GeneralSecurityException e) {
            // throw Exceptions.unchecked(e);
            return null;
        }
    }

    /**
     * 使用AES加密或解密无编码的原始字节数�? 返回无编码的字节数组结果.
     *
     * @param input
     *            原始字节数组
     * @param key
     *            符合AES要求的密�?
     * @param iv
     *            初始向量
     * @param mode
     *            Cipher.ENCRYPT_MODE �?Cipher.DECRYPT_MODE
     */
    private static byte[] aes(byte[] input, byte[] key, byte[] iv, int mode) {
        try {
            SecretKey secretKey = new SecretKeySpec(key, AES);
            IvParameterSpec ivSpec = new IvParameterSpec(iv);
            Cipher cipher = Cipher.getInstance(AES_CBC);
            cipher.init(mode, secretKey, ivSpec);
            return cipher.doFinal(input);
        } catch (GeneralSecurityException e) {
            // throw Exceptions.unchecked(e);
            return null;
        }
    }

    /**
     * 生成AES密钥,返回字节数组, 默认长度�?28�?16字节).
     */
    public static byte[] generateAesKey() {
        return generateAesKey(DEFAULT_AES_KEYSIZE);
    }

    /**
     * 生成AES密钥,可�?长度�?28,192,256�?
     */
    public static byte[] generateAesKey(int keysize) {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance(AES);
            keyGenerator.init(keysize);
            SecretKey secretKey = keyGenerator.generateKey();
            return secretKey.getEncoded();
        } catch (GeneralSecurityException e) {
            // throw Exceptions.unchecked(e);
            return null;
        }
    }

    /**
     * 生成随机向量,默认大小为cipher.getBlockSize(), 16字节.
     */
    public static byte[] generateIV() {
        byte[] bytes = new byte[DEFAULT_IVSIZE];
        random.nextBytes(bytes);
        return bytes;
    }

    /**
     * 随机向量：1a42eb4565be8628a807403d67dce78d 密钥：f6b0d3f905bf02939b4f6d29f257c2ab
     */
//	public static void main(String[] args) {
//	    //加密
//		System.out.println(EncodeUtils.hexEncode(aesEncrypt(
//				"18610676365".getBytes(),
//				EncodeUtils.hexDecode("f6b0d3f905bf02939b4f6d29f257c2ab"),
//				EncodeUtils.hexDecode("1a42eb4565be8628a807403d67dce78d"))));
//	    //解密
//	  //  System.out.println(aesEncrypt("18610676365".getBytes(), "f6b0d3f905bf02939b4f6d29f257c2ab".getBytes(), "1a42eb4565be8628a807403d67dce78d".getBytes()));
//		System.out.println(aesDecrypt(
//				EncodeUtils.hexDecode("62542ddfbf62702cdac6c27c67a423af"),
//				EncodeUtils.hexDecode("f6b0d3f905bf02939b4f6d29f257c2ab"),
//				EncodeUtils.hexDecode("1a42eb4565be8628a807403d67dce78d")));
//
////		System.out.println(EncodeUtils.hexEncode(aesEncrypt(
////				"连上6天班".getBytes(),
////				EncodeUtils.hexDecode("f6b0d3f905bf02939b4f6d29f257c2ab"),
////				EncodeUtils.hexDecode("1a42eb4565be8628a807403d67dce78d"))));
//	}

//    public static String getAesKey() {
//        return bytesToHexString(generateAesKey());
//    }

    public static byte[] aesEncryptByKey(byte[] input, byte[] key) {
        return aes(input, key, EncodeUtils
                        .hexDecode("1a52eb4565be8628a807403d67dce79d"),
                Cipher.ENCRYPT_MODE);
    }

    public static String aesDecryptByKey(byte[] input, byte[] key) {
        byte[] decryptResult = aes(input, key, EncodeUtils
                        .hexDecode("1a52eb4565be8628a807403d67dce79d"),
                Cipher.DECRYPT_MODE);
        return new String(decryptResult);

    }

    public static String infoEncrypted (String info){
        String hexEncode = EncodeUtils.hexEncode(aesEncrypt(
                info.getBytes(),
                EncodeUtils.hexDecode("f6b0d3f905bf02939b4f6d29f257c2ab"),
                EncodeUtils.hexDecode("1a42eb4565be8628a807403d67dce78d")));
        return hexEncode;
    }

    public static String infoDecrypt (String info){
        String hexEncode = aesDecrypt(
                EncodeUtils.hexDecode(info),
                EncodeUtils.hexDecode("f6b0d3f905bf02939b4f6d29f257c2ab"),
                EncodeUtils.hexDecode("1a42eb4565be8628a807403d67dce78d"));
        return hexEncode;
    }

    public static String usernumberEncrypted (String usernumber){
        String hexEncode = EncodeUtils.hexEncode(aesEncrypt(
                usernumber.getBytes(),
                EncodeUtils.hexDecode("f6b0d3f905bf02939b4f6d29f257c2ab"),
                EncodeUtils.hexDecode("1a42eb4565be8628a807403d67dce78d")));
        return hexEncode;
    }

    public static String usernumberDecrypt(String usernumber){
        String hexEncode = aesDecrypt(
                EncodeUtils.hexDecode(usernumber),
                EncodeUtils.hexDecode("f6b0d3f905bf02939b4f6d29f257c2ab"),
                EncodeUtils.hexDecode("1a42eb4565be8628a807403d67dce78d"));
        return hexEncode;
    }



    public static void main(String[] args) throws Exception {
        String key="426f615a743e6706856803f85108cfd0";

        /*String str = EncodeUtils.hexEncode(aesEncryptByKey(
                "11111".getBytes(), EncodeUtils
                        .hexDecode(key)));
        System.out.println(str);*/
        String ss = CryptosUtil.aesDecryptByKey(
                EncodeUtils.hexDecode("42be9dac498b6c04094cd2f16c5e98f5"), EncodeUtils
                        .hexDecode(key));
        System.out.println(ss);
        System.out.println(aesDecryptByKey(EncodeUtils.hexDecode("42be9dac498b6c04094cd2f16c5e98f5"), EncodeUtils
                .hexDecode(key)));


        String s1 = CryptosUtil.usernumberEncrypted("15513283764");
        String s3 = CryptosUtil.usernumberDecrypt(s1);
        System.out.println(s1);
        System.out.println(s3);

    }
}
