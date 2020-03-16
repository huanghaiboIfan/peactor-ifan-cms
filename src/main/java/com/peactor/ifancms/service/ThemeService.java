package com.peactor.ifancms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.peactor.ifancms.beans.entity.Theme;

import java.util.List;

/**
 * @Author: Ifan
 * date: 2020-03-12
 **/
public interface ThemeService extends IService<Theme> {

    List<Theme> getByName(String name);

    List<Theme> selectTest();
}
