package com.peactor.ifancms.httpsecurity.beans;

import com.alibaba.fastjson.JSON;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: Ifan
 * date: 2020-03-19
 **/
@Data
public class NetData implements Serializable {

    /* ------------------ Fields ------------------ */
    /** 秘钥 **/
    private String k;
    /** 密文 **/
    private String d;

    public NetData() {
    }

    public NetData(String k, String d) {
        this.k = k;
        this.d = d;
    }

    // ------------------ Methods ------------------

    public String getK() {
        return k;
    }

    public NetData setK(String k) {
        this.k = k;
        return this;
    }

    public String getD() {
        return d;
    }

    public NetData setD(String d) {
        this.d = d;
        return this;
    }

    public String toJson() {
        return JSON.toJSONString(this);
    }
}
