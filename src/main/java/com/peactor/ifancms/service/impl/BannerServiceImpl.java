package com.peactor.ifancms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.peactor.ifancms.beans.entity.Banner;
import com.peactor.ifancms.beans.entity.BannerItem;
import com.peactor.ifancms.mapper.BannerItemMapper;
import com.peactor.ifancms.mapper.BannerMapper;
import com.peactor.ifancms.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: Ifan
 * date: 2020-03-16
 **/
@Service
public class BannerServiceImpl extends ServiceImpl<BannerMapper, Banner> implements BannerService {

    @Autowired
    private BannerItemService bannerItemService;

    @Resource
    private BannerMapper bannerMapper;

    @Resource
    private BannerItemMapper bannerItemMapper;

    @Override
    public Banner getByName(String name) {
        Banner banner = bannerMapper.selectByName(name);
        return banner;
    }

    /**
     * 保存banner图片，并且保存banner下面的item列表数据，进行关联
     * @param banner
     */
    @Override
    public void saveWithItem(Banner banner) {
        save(banner);
        List<BannerItem> items = banner.getItems();
        Integer bannerId = banner.getId();
        items.forEach(item -> {
            item.setBannerId(bannerId);
        });
        bannerItemService.saveBatch(items);
    }

    @Service
    class BannerItemServiceImpl extends ServiceImpl<BannerItemMapper, BannerItem> implements BannerItemService {



    }
}
