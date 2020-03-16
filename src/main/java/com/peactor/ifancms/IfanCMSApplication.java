package com.peactor.ifancms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.peactor.ifancms")
@MapperScan("com.peactor.ifancms.mapper")
public class IfanCMSApplication {

    public static void main(String[] args) {
        SpringApplication.run(IfanCMSApplication.class, args);
    }

}
