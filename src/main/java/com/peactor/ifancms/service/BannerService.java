package com.peactor.ifancms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.peactor.ifancms.beans.entity.Banner;
import com.peactor.ifancms.beans.entity.BannerItem;

import java.io.Serializable;

/**
 * @Author: Ifan
 * date: 2020-03-16
 **/
public interface BannerService extends IService<Banner> {

    Banner getByName(String name);

    void saveWithItem(Banner banner);

    /**
     * Banner的Item Service内部接口继承IService以获取Mp方法
     */
    interface BannerItemService extends IService<BannerItem> {

    }
}
