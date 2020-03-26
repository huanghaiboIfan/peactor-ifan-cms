package com.peactor.ifancms.beans.vo;

import lombok.Data;
import lombok.ToString;

/**
 * @Description: 配合前端传输的单一数据对象包装类
 * @Author: Ifan
 * date: 2020-03-25
 **/
@Data
@ToString
public class SimpleData<T> {

    private T record;
}
