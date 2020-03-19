package com.peactor.ifancms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.peactor.ifancms.beans.entity.CategoryGrid;

import java.util.List;

/**
 * @Author: Ifan
 * date: 2020-03-17
 **/
public interface CategoryGridService extends IService<CategoryGrid> {

    List<CategoryGrid> getAll();
}
