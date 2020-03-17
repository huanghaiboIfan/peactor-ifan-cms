package com.peactor.ifancms.controller;

import com.peactor.ifancms.beans.entity.Banner;
import com.peactor.ifancms.http.HttpMsg;
import com.peactor.ifancms.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: Ifan
 * date: 2020-03-16
 **/
@RestController
@RequestMapping("/banner")
public class BannerController {

    @Autowired
    private BannerService bannerService;

    @GetMapping("/getByName/{name}")
    public ResponseEntity getByName(@PathVariable String name) {
        Banner resBanner = bannerService.getByName(name);
        return ResponseEntity.ok(resBanner);
    }

    @PostMapping("/save")
    public ResponseEntity save(@RequestBody Banner banner) {
        bannerService.saveWithItem(banner);
        return ResponseEntity.ok(HttpMsg.SUCCESS);
    }
}
