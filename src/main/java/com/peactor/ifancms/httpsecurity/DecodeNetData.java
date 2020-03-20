package com.peactor.ifancms.httpsecurity;

import com.peactor.ifancms.exception.CommonException;
import com.peactor.ifancms.httpsecurity.asymmetric.DES;
import com.peactor.ifancms.httpsecurity.asymmetric.RSAC;
import com.peactor.ifancms.httpsecurity.asymmetric.RSAS;
import com.peactor.ifancms.httpsecurity.beans.NetData;
import com.peactor.ifancms.httpsecurity.constants.SecurityKeyConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

/**
 * @Author: Ifan
 * date: 2020-03-20
 **/
@Slf4j
public class DecodeNetData extends BaseNetSecurity {

    /** 明文数据的封装 **/
    private final NetData netData;
    /** 是否为服务端 **/
    private final boolean server;

    public DecodeNetData(NetData netData, boolean server) {
        this.netData = netData;
        this.server = server;
    }

    @Override
    protected void check() {
        if (netData == null) {
            throw new CommonException("请求体缺失");
        }

        if (StringUtils.isBlank(netData.getK())) {
            throw new CommonException("请求体中，未发现加密秘钥");
        }

        if (StringUtils.isBlank(netData.getD())) {
            throw new CommonException("请求体中，未发现加密数据");
        }
    }

    /**
     * 获取解密后的key
     * @return RSA 解密后的key
     */
    @Override
    public String getKey() {
        try {
            String result = null;

            if (server) {
                result = new RSAS(SecurityKeyConstants.RSA_PRI_KEY).decryptByBase64(netData.getK());
            } else {
                result = new RSAC(SecurityKeyConstants.RSA_PUB_KEY).decryptByBase64(netData.getK());
            }

            return result;
        } catch (Exception e) {
            log.error("解密 加密秘钥 时出现异常", e);
            throw new CommonException("解密 加密秘钥 时出现异常, k: " + netData.getK());
        }
    }

    /**
     * 获取解密后的数据
     * @return DES 解密后的数据
     */
    @Override
    public String getData() {
        MiniSecurity des = new DES(getKey());
        try {
            String result = ((DES) des).decryptByBase64(netData.getD());
            return result;
        } catch (Exception e) {
            log.error("解密 加密密文 时出现异常", e);
            throw new CommonException("解密 加密密文 时出现异常, d: " + netData.getD());
        }
    }
}
