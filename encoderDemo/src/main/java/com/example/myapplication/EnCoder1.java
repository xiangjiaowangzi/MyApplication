package com.example.myapplication;

import android.util.Base64;

import com.example.myapplication.Utils.Base64Decode;
import com.example.myapplication.Utils.Base64Encoder;

import java.security.MessageDigest;
import java.util.concurrent.Future;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by Lbin on 2017/10/9.
 */

public class EnCoder1 {

    public static final String KEY_SHA = "SHA" ;
    public static final String KEY_MD5 = "MD5" ;

    /**
     *  MAC 算法可选一下多种算法
     *
     *  <pre>
     *  HmacMD5
     *  HmacSHA1
     *  HmacSHA256
     *  HmacSHA384
     *  HmacSHA512
     *  <pre/>
     *
     * */
    public static final String KEY_MAC = "HmacMD5" ;

    /**
     * BASE64 解密
     * */
    public static byte[] decryptBASE64(String key) throws Exception{
        return Base64Decode.decode(key);
    }

    /**
     * BASE64加密
     * */
    public static String encryptBASE64(byte[] key) throws Exception{
        return Base64Encoder.encode(key);
    }


    /**
     * SHA加密
     * */
    public static byte[] encryptSHA(byte[] data) throws Exception{
        MessageDigest digest = MessageDigest.getInstance(KEY_SHA);
        digest.update(data);
        return digest.digest();
    }

    /**
     * MD5加密
     * */
    public static byte[] encryptMD5(byte[] data) throws Exception{
        MessageDigest digest = MessageDigest.getInstance(KEY_MD5);
        digest.update(data);
        return digest.digest();
    }

    /**
     * 初始化HMAC 密钥
     * */
    public static String iniMacKey() throws Exception{
        KeyGenerator keyGenerator = KeyGenerator.getInstance(KEY_MAC);

        SecretKey secretKey = keyGenerator.generateKey();
        return encryptBASE64(secretKey.getEncoded());
    }

    /**
     * HMAC 加密
     * */
    public static byte[] encryptHMAC(byte[] data , String key) throws Exception{

        SecretKey secretKey = new SecretKeySpec(decryptBASE64(key) , KEY_MAC);
        Mac mac = Mac.getInstance(secretKey.getAlgorithm());
        mac.init(secretKey);
        return mac.doFinal(data);
    }

}
