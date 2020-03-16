package com.peactor.ifancms.beans.entity;

import lombok.Data;

/**
 * @Description: Banner轮播图的Item实例
 * @Author: Ifan
 * date: 2020-03-16
 **/
@Data
public class BannerItem extends BaseEntity {

    private Integer bannerId;

    private String name;

    private String keyword;

    private String type;

    private String img;

}
