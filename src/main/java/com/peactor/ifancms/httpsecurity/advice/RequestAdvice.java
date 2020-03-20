package com.peactor.ifancms.httpsecurity.advice;

import com.alibaba.fastjson.JSON;
import com.peactor.ifancms.httpsecurity.DecodeNetData;
import com.peactor.ifancms.httpsecurity.annotation.HttpSecurity;
import com.peactor.ifancms.httpsecurity.beans.NetData;
import com.peactor.ifancms.httpsecurity.inter.NetSecurity;
import io.micrometer.core.instrument.util.IOUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;

/**
 * @Author: Ifan
 * date: 2020-03-19
 **/
@RestControllerAdvice
@Slf4j
public class RequestAdvice implements RequestBodyAdvice {

    /** 解密时长若超过该值需要警告 **/
    private final long MAX_TIME = 1000 * 3;

    @Override
    public boolean supports(MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {

        // 检查方法或所在类是否有加密注解
        boolean methodHasAnnotation = methodParameter.hasMethodAnnotation(HttpSecurity.class);
        boolean clazzHasAnnotation = methodParameter.getExecutable().getDeclaringClass().getAnnotation(HttpSecurity.class) != null;
        if(!(methodHasAnnotation || clazzHasAnnotation)) {
            return false;
        }

        // 该请求是否需要解密
        HttpSecurity hyzsHttpEncrypt = null;

        // 方法注解优先级 > 类
        if(methodHasAnnotation) {
            hyzsHttpEncrypt = methodParameter.getMethodAnnotation(HttpSecurity.class);
            if(!hyzsHttpEncrypt.requestDecode()) {
                log.debug("{}#{} 有 HyzsHttpEncrypt 注解，但不需要对其请求进行解密",
                        methodParameter.getExecutable().getDeclaringClass().getTypeName(), methodParameter.getMethod().getName());
                return false;
            }
        }

        if(clazzHasAnnotation) {
            hyzsHttpEncrypt = methodParameter.getExecutable().getDeclaringClass().getAnnotation(HttpSecurity.class);
            if(!hyzsHttpEncrypt.requestDecode()) {
                log.debug("{} 有 HyzsHttpEncrypt 注解，但不需要对其请求进行解密",
                        methodParameter.getExecutable().getDeclaringClass().getTypeName(), methodParameter.getMethod().getName());
                return false;
            }
        }

        return (methodParameter.getParameterAnnotation(RequestBody.class) != null);
    }

    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage httpInputMessage, MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) throws IOException {
        return new HttpInputMessage() {
            @Override
            public InputStream getBody() throws IOException {
                InputStream inputStream = httpInputMessage.getBody();
                String json = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
                NetData netData = JSON.parseObject(json, NetData.class);

                // 如果合法，那么进行解密
                long start = System.currentTimeMillis();
                NetSecurity netSecurity = new DecodeNetData(netData, true);
                String data = netSecurity.proccessData();
                long end = System.currentTimeMillis();
                long usedTime = end - start;

                if(usedTime > MAX_TIME) {
                    log.warn("{}#{} 解密时间过长，总时长: {}毫秒",
                            methodParameter.getExecutable().getDeclaringClass().getTypeName(),
                            methodParameter.getMethod().getName(),
                            usedTime);
                }

                return new ByteArrayInputStream(data.getBytes(StandardCharsets.UTF_8));
            }

            @Override
            public HttpHeaders getHeaders() {
                return httpInputMessage.getHeaders();
            }
        };
    }

    @Override
    public Object afterBodyRead(Object body, HttpInputMessage httpInputMessage, MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
        return body;
    }

    @Override
    public Object handleEmptyBody(Object body, HttpInputMessage httpInputMessage, MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
        return body;
    }

}

