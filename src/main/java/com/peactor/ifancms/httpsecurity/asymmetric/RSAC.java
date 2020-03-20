package com.peactor.ifancms.httpsecurity.asymmetric;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;

import javax.crypto.Cipher;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;

/**
 * @Auther: Crazyang
 * @Date: 2018-12-8 11:23
 * @Description: RSA 客户端使用公钥加密解密
 */
public class RSAC extends RSA {

    /**
     * 私钥
     */
    private byte[] pubKey;

    public RSAC(String base64PubKey) {
        this.pubKey = Base64.decodeBase64(base64PubKey);
    }

    public RSAC(byte[] pubKey) {
        this.pubKey = pubKey;
    }

    public byte[] encrypt(byte[] data) throws Exception {
        return encrypt(data, pubKey);
    }

    public byte[] decrypt(byte[] data) throws Exception {
        return decrypt(data, pubKey);
    }

    @Override
    public byte[] encrypt(byte[] data, byte[] password) throws Exception {
        //实例化密钥工厂
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        //初始化公钥
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(password);
        //产生公钥
        PublicKey pubKey = keyFactory.generatePublic(x509KeySpec);
        //数据加密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);
        return cipher.doFinal(data);
    }

    @Override
    public byte[] decrypt(byte[] data, byte[] password) throws Exception {
        //实例化密钥工厂
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        //初始化公钥
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(password);
        //产生公钥
        PublicKey pubKey = keyFactory.generatePublic(x509KeySpec);
        //数据解密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, pubKey);
        return cipher.doFinal(data);
    }

    /**
     * 加密为base64编码
     * @param data 明文
     * @return
     */
    public String encryptToBase64(String data) throws Exception {
        if(StringUtils.isBlank(data)) {
            return null;
        }

        byte[] result = encrypt(data.getBytes(), pubKey);
        return Base64.encodeBase64String(result);
    }

    /**
     * 根据 base64 编码解密
     * @param base64 加密的base64编码
     * @return
     */
    public String decryptByBase64(String base64) throws Exception {
        if(StringUtils.isBlank(base64)) {
            return null;
        }

        byte[] data = Base64.decodeBase64(base64);
        byte[] result = decrypt(data, pubKey);
        return new String(result);
    }
}
