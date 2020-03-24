package com.peactor.ifancms.beans.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @Description: 优惠券活动
 * @Author: Ifan
 * date: 2020-03-24
 **/
@TableName
@Data
public class Activity {

    @TableId
    private Integer id;

    private String title;

    private String remark;

    private String entranceImg;

    private String online;

    private Date startTime;

    private Date endTime;
}
