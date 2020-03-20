package com.peactor.ifancms.httpsecurity.constants;

/**
 * @author : Crazyang
 * @date : 2019/7/23 11:12
 * @description : 配置枚举
 */
public enum ProfileConstants {
    /** 本地模式 **/
    LOCAL("local", "本地模式"),
    /** 开发模式 **/
    DEV("dev", "开发模式"),
    /** 测试模式 **/
    TEST("test", "测试模式"),
    /** 生产模式 **/
    PRO("pro", "生产模式");

    private final String key;
    private final String desc;

    ProfileConstants(String key, String desc) {
        this.key = key;
        this.desc = desc;
    }

    public String getKey() {
        return key;
    }

    public String getDesc() {
        return desc;
    }

}
