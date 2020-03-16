package com.peactor.ifancms.beans.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Description: entity的基类，将id、createTime和updateTime置入该类中
 * @Author: Ifan
 * date: 2020-03-12
 **/
@Data
@ToString
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class BaseEntity<T extends Model> extends Model {

    @TableId(type = IdType.AUTO)
    private Integer id;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date createTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date updateTime;
}
