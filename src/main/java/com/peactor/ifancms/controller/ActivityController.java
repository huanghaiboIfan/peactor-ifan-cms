package com.peactor.ifancms.controller;

import com.peactor.ifancms.beans.entity.Activity;
import com.peactor.ifancms.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Ifan
 * date: 2020-03-24
 **/
@RestController
@RequestMapping("/activity")
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    @GetMapping("/get/{title}")
    public ResponseEntity get(@PathVariable String title) {
        Activity resultActivity = activityService.getByTitle(title);
        return ResponseEntity.ok(resultActivity);
    }
}
