package com.peactor.ifancms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.peactor.ifancms.beans.entity.Theme;
import com.peactor.ifancms.mapper.ThemeMapper;
import com.peactor.ifancms.service.ThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Ifan
 * date: 2020-03-12
 **/
@Service
public class ThemeServiceImpl extends ServiceImpl<ThemeMapper, Theme> implements ThemeService {

    @Autowired
    private ThemeMapper themeMapper;

    @Override
    public List<Theme> getByName(String name) {
        QueryWrapper<Theme> qw = new QueryWrapper<>();
        qw.like("name", name);
        return themeMapper.selectList(qw);
    }

    public List<Theme> selectTest() {
        return themeMapper.selectList(null);
    }
}

