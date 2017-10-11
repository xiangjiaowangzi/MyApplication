package com.example.myapplication;

import java.math.BigInteger;

/**
 * Created by Lbin on 2017/10/9.
 */

public class TestEnCoder {

    public static String test(String inputStr) throws Exception{
        StringBuilder builder = new StringBuilder();
//        String inputStr = "简单加密";
        System.err.println("原文:\n" + inputStr);
        builder.append("原文:\n" + inputStr);

        byte[] inputData = inputStr.getBytes();
        String code = EnCoder1.encryptBASE64(inputData);
        System.err.println("BASE64加密后:\n" + code);
        builder.append("BASE64加密后:\n" + code);

        byte[] output = EnCoder1.decryptBASE64(code);
        String outputStr = new String(output);
        System.err.println("BASE64解密后:\n" + outputStr);
        builder.append("BASE64解密后:\n" + outputStr);

        String key = EnCoder1.iniMacKey(); // 生成钥匙;
        System.err.println("Mac密钥:\n" + key);
        builder.append("Mac密钥:\n" + key);

        BigInteger md5 = new BigInteger(EnCoder1.encryptMD5(inputData));
        System.err.println("MD5:\n" + md5.toString(16));
        builder.append("Mac密钥:\n" + key);

        BigInteger sha = new BigInteger(EnCoder1.encryptSHA(inputData));
        System.err.println("SHA:\n" + sha.toString(32));
        builder.append("Mac密钥:\n" + key);

        BigInteger mac = new BigInteger(EnCoder1.encryptHMAC(inputData, inputStr));
        System.err.println("HMAC:\n" + mac.toString(16));
        builder.append("Mac密钥:\n" + key);

        return builder.toString();

    }
}
