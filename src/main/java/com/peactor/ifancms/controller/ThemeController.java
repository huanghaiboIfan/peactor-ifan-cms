package com.peactor.ifancms.controller;

import com.peactor.ifancms.beans.entity.Theme;
import com.peactor.ifancms.http.HttpMsg;
import com.peactor.ifancms.service.ThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.util.List;

/**
 * @Author: Ifan
 * date: 2020-03-12
 **/
@RestController
@RequestMapping("/theme")
public class ThemeController {

    @Autowired
    private ThemeService themeService;

    @GetMapping("/getByNames/{name}")
    public ResponseEntity getByNames(@PathVariable String name) {
        List<Theme> themeList = themeService.getByName(name);
        return ResponseEntity.ok(themeList);
    }


    @PostMapping("/save")
    public ResponseEntity save(@RequestBody Theme theme) {
        themeService.save(theme);
        return ResponseEntity.ok(HttpMsg.SUCCESS);
    }
}
