package com.peactor.ifancms.httpsecurity;

import com.peactor.ifancms.exception.CommonException;
import com.peactor.ifancms.httpsecurity.constants.ProfileConstants;
import com.peactor.ifancms.httpsecurity.inter.NetSecurity;
import com.peactor.ifancms.httpsecurity.utils.SpringContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author: Ifan
 * date: 2020-03-19
 **/
public abstract class BaseNetSecurity implements NetSecurity {

    private final Logger logger = LoggerFactory.getLogger(BaseNetSecurity.class);

    /* ------------------ Fields ------------------ */

    private final String commonErrr = "Sorry, you are not authorized to access this.";

    /* ------------------ Methods ------------------ */

    @Override
    public String proccessKey() {
        preCheck();

        return getKey();
    }

    @Override
    public String proccessData() {
//        preCheck();
        check();
        return getData();
    }

    private void preCheck() {

        String profile = null;
        if(SpringContextUtil.applicationContext != null) {
            profile = SpringContextUtil.getEnvironment().getActiveProfiles()[0];
        }

        try {
            check();
        } catch (Exception e) {
            if(ProfileConstants.PRO.getKey().equals(profile)) {
                throw new CommonException(commonErrr);
            } else {
                throw e;
            }
        }
    }

    protected abstract void check();

    public abstract String getKey();

    public abstract String getData();
}
