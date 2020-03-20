package com.peactor.ifancms.controller;

import com.peactor.ifancms.beans.vo.LoginBean;
import com.peactor.ifancms.httpsecurity.annotation.HttpSecurity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Ifan
 * date: 2020-03-19
 **/
@RestController
@RequestMapping("/login")
public class LoginController {


    @PostMapping("/doLogin")
    @HttpSecurity(responseEncode = false)
    public ResponseEntity doLogin(@RequestBody LoginBean loginBean) {
        System.out.println(loginBean);
        return ResponseEntity.ok(loginBean);
    }
}
