package com.peactor.ifancms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.peactor.ifancms.beans.entity.Theme;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: Ifan
 * date: 2020-03-12
 **/
@Mapper
public interface ThemeMapper extends BaseMapper<Theme> {

    List<Theme> selectTest();
}
