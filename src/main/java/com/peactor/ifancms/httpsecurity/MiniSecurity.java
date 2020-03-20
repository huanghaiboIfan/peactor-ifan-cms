package com.peactor.ifancms.httpsecurity;

/**
 * @Author: Ifan
 * date: 2020-03-20
 **/
public interface MiniSecurity {

    /**
     * 加密
     * @param data 明文
     * @param password 密码，秘钥
     * @return
     */
    byte[] encrypt(byte[] data, byte[] password) throws Exception;

    /**
     * 解密
     * @param data  密文
     * @param password 密码，秘钥
     * @return
     */
    byte[] decrypt(byte[] data, byte[] password) throws Exception;

}
