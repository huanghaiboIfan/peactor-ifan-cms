package com.peactor.ifancms.controller;

import com.peactor.ifancms.beans.entity.CategoryGrid;
import com.peactor.ifancms.http.HttpMsg;
import com.peactor.ifancms.service.CategoryGridService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: Ifan
 * date: 2020-03-17
 **/
@RestController
@RequestMapping("/category/grid")
public class CategoryGridController {

    @Autowired
    private CategoryGridService categoryGridService;

    @GetMapping("/all")
    public ResponseEntity getAll() {
        List<CategoryGrid> categoryGrids = categoryGridService.getAll();
        return ResponseEntity.ok(categoryGrids);
    }

    @PostMapping("/save")
    public ResponseEntity save(@RequestBody CategoryGrid categoryGrid) {
        categoryGridService.save(categoryGrid);
        return ResponseEntity.ok(HttpMsg.SUCCESS);
    }
}
