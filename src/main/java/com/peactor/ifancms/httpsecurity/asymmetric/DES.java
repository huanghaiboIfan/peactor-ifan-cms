package com.peactor.ifancms.httpsecurity.asymmetric;

import com.peactor.ifancms.httpsecurity.MiniSecurity;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.SecureRandom;

/**
 * @Description: 对称解密
 * @Author: Ifan
 * date: 2020-03-20
 **/
public class DES implements MiniSecurity {

    /**
     * DES公共密码
     */
    public static final String DES_PUB_KEY = "95039be75c824248b5d9de240df51c89";

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * 加密类型
     */
    private final String TYPE = "DES";
    /**
     * 秘钥必须是8的倍数
     */
    private final int LENGTH = 8;

    /**
     * 字节码形式密码
     */
    private byte[] password;

    private DES() {
    }

    public DES(String password) {
        if(StringUtils.isBlank(password)) {
            throw new NullPointerException("秘钥不可为空");
        }
        if(password.length() % LENGTH != 0) {
            throw new UnsupportedOperationException("秘钥长度有误");
        }
        this.password = password.getBytes();
    }

    public DES(byte[] password) {
        if(password.length % LENGTH != 0) {
            throw new UnsupportedOperationException("秘钥长度有误");
        }
        this.password = password;
    }

    public byte[] encrypt(byte[] data) throws Exception {
        return encrypt(data, password);
    }

    public byte[] decrypt(byte[] data) throws Exception {
        return decrypt(data, password);
    }

    @Override
    public byte[] encrypt(byte[] data, byte[] password) throws Exception {
        try {
            //DES算法要求有一个可信任的随机源
            SecureRandom random = new SecureRandom();
            //创建一个DesKeySpec对象
            DESKeySpec desKeySpec = new DESKeySpec(password);
            //创建一个密钥工厂
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(TYPE);
            //将DesKeySpec对象转换成SecrectKey对象
            SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
            // Cipher对象实例化DES算法进行解密操作
            Cipher cipher = Cipher.getInstance(TYPE);
            //用密钥初始化Cipher对象
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, random);
            //开始加密返回byte字节数据
            return cipher.doFinal(data);
        } catch (Exception e) {
            log.error("DES 加密时发生错误", e);
        }
        return null;
    }

    @Override
    public byte[] decrypt(byte[] data, byte[] password) throws Exception {
        //DES算法要求有一个可信任的随机源
        SecureRandom random = new SecureRandom();
        //创建一个DesKeySpec对象
        DESKeySpec desKeySpec = new DESKeySpec(password);
        //创建一个密钥工厂
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(TYPE);
        //将DesKeySpec对象转换成SecrectKey对象
        SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
        // Cipher对象实例化DES算法进行解密操作
        Cipher cipher = Cipher.getInstance(TYPE);
        //用密钥初始化Cipher对象
        cipher.init(Cipher.DECRYPT_MODE, secretKey, random);
        //开始解密返回byte字节数据
        return cipher.doFinal(data);
    }

    /**
     * 加密为 base64 编码
     * @param data 明文
     * @return
     */
    public String encryptToBase64(String data) throws Exception {
        if(StringUtils.isBlank(data)) {
            return null;
        }
        byte[] cipher = encrypt(data.getBytes(), password);
        return Base64.encodeBase64String(cipher);
    }

    /**
     * 根据 base64 解密
     * @param base64
     * @return
     */
    public String decryptByBase64(String base64) throws Exception {
        if(StringUtils.isBlank(base64)) {
            return null;
        }

        byte[] data = Base64.decodeBase64(base64);
        byte[] text = decrypt(data, password);
        return new String(text);
    }
}
