package com.peactor.ifancms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.peactor.ifancms.beans.entity.Activity;

/**
 * @Author: Ifan
 * date: 2020-03-24
 **/
public interface ActivityService extends IService<Activity> {

    Activity getByTitle(String title);
}
