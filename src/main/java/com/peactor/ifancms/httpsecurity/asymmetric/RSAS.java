package com.peactor.ifancms.httpsecurity.asymmetric;

import org.apache.commons.lang.StringUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;

/**
 * @Auther: Crazyang
 * @Date: 2018-12-8 11:23
 * @Description: RSA 服务器使用秘钥进行加密解密
 */
public class RSAS extends RSA {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * 私钥
     */
    private byte[] priKey;

    public RSAS(String base64PriKey) {
        this.priKey = Base64.decodeBase64(base64PriKey);
    }

    public RSAS(byte[] priKey) {
        this.priKey = priKey;
    }

    public byte[] encrypt(byte[] data) throws Exception {
        return encrypt(data, priKey);
    }

    public byte[] decrypt(byte[] data) throws Exception {
        return decrypt(data, priKey);
    }

    @Override
    public byte[] encrypt(byte[] data, byte[] password) throws Exception {
        try {
            //取得私钥
            PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(password);
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
            //生成私钥
            PrivateKey privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
            //数据加密
            Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
            cipher.init(Cipher.ENCRYPT_MODE, privateKey);
            return cipher.doFinal(data);
        } catch (Exception e) {
            log.error("RSA加密出错", e);
        }

        return null;
    }

    @Override
    public byte[] decrypt(byte[] data, byte[] password) throws Exception {
        //取得私钥
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(password);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        //生成私钥
        PrivateKey privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
        //数据解密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
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

        byte[] result = encrypt(data.getBytes(), priKey);
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
        byte[] reslut = decrypt(data, priKey);
        return new String(reslut);
    }
}
