package com.peactor.ifancms.beans.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @Author: Ifan
 * date: 2020-03-17
 **/
@Data
public class CategoryGrid  {

    @TableId
    private Integer id;

    private String name;

    private Integer categoryId;

    private Integer rootCategoryId;

    private String title;

    private String img;

}
