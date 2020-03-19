package com.peactor.ifancms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.peactor.ifancms.beans.entity.CategoryGrid;
import com.peactor.ifancms.mapper.CategoryGridMapper;
import com.peactor.ifancms.service.CategoryGridService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description: TODO
 * @Author: Ifan
 * date: 2020-03-17
 **/
@Service
public class CategoryGridServiceImpl extends ServiceImpl<CategoryGridMapper, CategoryGrid> implements CategoryGridService {

    @Resource
    private CategoryGridMapper categoryGridMapper;

    @Override
    public List<CategoryGrid> getAll() {
        return categoryGridMapper.selectList(null);
    }
}
