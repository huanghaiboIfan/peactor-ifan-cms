package com.peactor.ifancms.beans.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

/**
 * @Author: Ifan
 * date: 2020-03-12
 **/
@Data
@ToString
@TableName("theme")
public class Theme extends BaseEntity<Theme> {

    /**
     * theme name
     */
    private String name;

    /**
     * tpl名称
     */
    private String tplName;

    /**
     * 描述
     */
    private String description;

    /**
     * 入口图
     */
    private String entranceImg;

    /**
     * 延伸
     */
    private String extend;

    /**
     * 内部顶图
     */
    private String internalTopImg;

    /**
     * 线上
     */
    private String online;

    /**
     * 标题
     */
    private String title;

    /**
     * 标题图
     */
    private String titleImg;

}
