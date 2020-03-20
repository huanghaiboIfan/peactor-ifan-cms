package com.peactor.ifancms.httpsecurity.inter;

/**
 * 通讯安全接口, 处理NetData数据
 * @Author: Ifan
 * date: 2020-03-19
 **/
public interface NetSecurity {

    /**
     * 处理key
     * @return
     */
    String proccessKey();

    /**
     * 处理数据
     */
    String proccessData();

}
