package com.peactor.ifancms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.peactor.ifancms.beans.entity.Banner;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Author: Ifan
 * date: 2020-03-16
 **/
@Mapper
public interface BannerMapper extends BaseMapper<Banner> {

    Banner selectByName(@Param("name") String name);
}
