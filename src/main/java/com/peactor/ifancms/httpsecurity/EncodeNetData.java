package com.peactor.ifancms.httpsecurity;

import com.peactor.ifancms.exception.CommonException;
import com.peactor.ifancms.httpsecurity.asymmetric.DES;
import com.peactor.ifancms.httpsecurity.asymmetric.RSAC;
import com.peactor.ifancms.httpsecurity.asymmetric.RSAS;
import com.peactor.ifancms.httpsecurity.beans.NetData;
import com.peactor.ifancms.httpsecurity.constants.SecurityKeyConstants;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 加密 明文数据
 * @author : Crazyang
 * @date : 2020/1/9 14:44
 */
public class EncodeNetData extends BaseNetSecurity {

    private final Logger logger = LoggerFactory.getLogger(EncodeNetData.class);

    /** 明文数据的封装 **/
    private final NetData netData;
    /** 是否为服务端 **/
    private final boolean server;

    public EncodeNetData(NetData netData, boolean server) {
        this.netData = netData;
        this.server = server;
    }

    @Override
    protected void check() {
        if (netData == null) {
            throw new CommonException("请求体缺失");
        }

        if (StringUtils.isBlank(netData.getK())) {
            throw new CommonException("请求体中，未发现明文秘钥");
        }

        if (StringUtils.isBlank(netData.getD())) {
            throw new CommonException("请求体中，未发现明文数据");
        }
    }

    /**
     * 获取加密后的key
     * @return RSA加密后的key
     */
    @Override
    public String getKey() {
        try {
            String result = null;

            if (server) {
                result = new RSAS(SecurityKeyConstants.RSA_PRI_KEY).encryptToBase64(netData.getK());
            } else {
                result = new RSAC(SecurityKeyConstants.RSA_PUB_KEY).encryptToBase64(netData.getK());
            }

            return result;
        } catch (Exception e) {
            logger.error("加密秘钥 key 时出现异常");
            throw new CommonException("加密秘钥 key 时出现异常：" + e.getLocalizedMessage());
        }
    }

    /**
     * 获取加密后的数据
     * @return DES加密的数据
     */
    @Override
    public String getData() {
        MiniSecurity des = new DES(netData.getK());
        try {
            String result = ((DES) des).encryptToBase64(netData.getD());
            return result;
        } catch (Exception e) {
            logger.error("加密明文 data 时出现异常", e);
            throw new CommonException("加密明文 data 时出现异常: " + e.getLocalizedMessage());
        }
    }
}
