package com.peactor.ifancms.beans.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.List;

/**
 * @Description: Banner轮播图实体类
 * @Author: Ifan
 * date: 2020-03-16
 **/
@Data
@TableName("banner")
public class Banner extends BaseEntity {

    private String name;

    private String img;

    private String title;

    private String description;

    @TableField(exist = false)
    private List<BannerItem> items;
}
