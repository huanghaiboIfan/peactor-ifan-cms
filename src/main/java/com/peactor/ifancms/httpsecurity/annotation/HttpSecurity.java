package com.peactor.ifancms.httpsecurity.annotation;

import java.lang.annotation.*;

/**
 * @Description: http传输安全注解,通讯加密解密,在Controller方法上加入该注解,默认加密解密都是需要的
 * @Author: Ifan
 * date: 2020-03-19
 **/
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface HttpSecurity {

    /**
     * 请求数据是否需要解密
     * @return true: 需要解密请求数据; false: 反之;
     */
    boolean requestDecode() default true;

    /**
     * 响应数据是否需要加密
     * @return true: 加密返回的数据; false: 反之；
     */
    boolean responseEncode() default true;
}
