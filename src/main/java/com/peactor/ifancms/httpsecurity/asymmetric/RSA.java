package com.peactor.ifancms.httpsecurity.asymmetric;


import com.peactor.ifancms.httpsecurity.MiniSecurity;

/**
 * @Auther: Crazyang
 * @Date: 2018-12-8 13:33
 * @Description:
 */
public abstract class RSA implements MiniSecurity {
    /**
     * 非对称密钥算法
     */
    public static final String KEY_ALGORITHM = "RSA";

    /**
     * 密钥长度，DH算法的默认密钥长度是1024
     * 密钥长度必须是64的倍数，在512到65536位之间
     */
    private static final int KEY_SIZE = 2048;

}
