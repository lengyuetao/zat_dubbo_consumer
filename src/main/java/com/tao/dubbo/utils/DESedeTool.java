/*
 * Author : George <GeorgeNiceWorld@gmail.com> | <Georgeinfo@163.com>
 * Copyright (C) George (http://www.georgeinfo.com), All Rights Reserved.
 */
package com.tao.dubbo.utils;

import java.security.Key;
import java.security.Provider;
import java.security.Security;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

/**
 * 三重DES(3DES)加密、解密算法
 *
 * @author George <Georgeinfo@163.com>
 */
public class DESedeTool {

    /**
     * 3DES(三重DES)秘钥算法
     */
    public static final String KEY_ALGORITHM = "DESede";

    /**
     * 加密算法具体名称，如果是"DESedeTool/ECB/NoPadding"这种算法，则必须确保被加密的内容是8个字节的倍数
     */
    public static final String DEFAULT_CIPHER_ALGORITHM = "DESede/ECB/NoPadding"; //"DESedeTool/ECB/ISO10126Padding";

    public static final String ISO_CIPHER_ALGORITHM = "DESede/ECB/ISO10126Padding";  //DESede/ECB/ISO10126Padding    

    public static final String app_CIPHER_ALGORITHM = "DESede/ECB/PKCS5Padding";  //DESede/ECB/ISO10126Padding    

    /**
     * 加密
     *
     * @param data 待加密数据
     * @param key 密钥
     * @param cipherAlgorithm 加密算法/工作模式/填充方式
     * @return byte[] 加密数据
     * @throws Exception
     */
    public static byte[] encrypt(byte[] data, Key key, String cipherAlgorithm) throws Exception {
        //实例化  
        Cipher cipher = Cipher.getInstance(cipherAlgorithm);
        //使用密钥初始化，设置为加密模式  
        cipher.init(Cipher.ENCRYPT_MODE, key);
        //执行操作  
        return cipher.doFinal(data);
    }

    /**
     * 解密
     *
     * @param data 待解密数据
     * @param key 密钥
     * @param cipherAlgorithm 加密算法/工作模式/填充方式
     * @return byte[] 解密数据
     * @throws Exception
     */
    public static byte[] decrypt(byte[] data, Key key, String cipherAlgorithm) throws Exception {
        //实例化  
        Cipher cipher = Cipher.getInstance(cipherAlgorithm);
        //使用密钥初始化，设置为解密模式  
        cipher.init(Cipher.DECRYPT_MODE, key);
        //执行操作  
        return cipher.doFinal(data);
    }

    public static String showByteArray(byte[] data) {
        if (null == data) {
            return null;
        }
        StringBuilder sb = new StringBuilder("{");
        for (byte b : data) {
            sb.append(b).append(",");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append("}");
        return sb.toString();
    }

    public static Provider getProvider(String jceProviderClassName) {
        Provider provider = null;
        try {
            if ((jceProviderClassName == null) || (jceProviderClassName.trim().isEmpty())
                    || jceProviderClassName.trim().toLowerCase().equals("default")) {
                jceProviderClassName = "com.sun.crypto.provider.SunJCE";
            }
            provider = (Provider) Class.forName(jceProviderClassName).newInstance();
            Security.addProvider(provider);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException("Unable to load jce provider whose class name is: "
                    + jceProviderClassName);
        }

        return provider;
    }

    /**
     * 转换密钥，将秘钥明文，转换成DES指定的秘钥对象
     *
     * @param key 二进制密钥
     * @return Key 密钥
     * @throws Exception
     */
    public static Key convertKey(byte[] key) throws Exception {
        //实例化DES密钥规则  
        DESedeKeySpec dks = new DESedeKeySpec(key);
        //实例化密钥工厂  
        SecretKeyFactory skf = SecretKeyFactory.getInstance(KEY_ALGORITHM);
        //生成密钥  
        SecretKey secretKey = skf.generateSecret(dks);
        return secretKey;
    }

}
