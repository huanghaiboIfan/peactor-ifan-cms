package com.peactor.ifancms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.peactor.ifancms.beans.entity.Activity;
import com.peactor.ifancms.mapper.ActivityMapper;
import com.peactor.ifancms.service.ActivityService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: Ifan
 * date: 2020-03-24
 **/
@Service
public class ActivityServiceImpl extends ServiceImpl<ActivityMapper, Activity> implements ActivityService {

    @Resource
    private ActivityMapper activityMapper;

    @Override
    public Activity getByTitle(String title) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("title", title);
        return activityMapper.selectOne(queryWrapper);
    }
}
