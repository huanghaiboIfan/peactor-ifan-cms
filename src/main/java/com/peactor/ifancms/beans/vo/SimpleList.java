package com.peactor.ifancms.beans.vo;

import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @Description: 配合前端Ajax传输json文件的接收对象
 * @Author: Ifan
 * date: 2020-03-25
 **/
@Data
@ToString
public class SimpleList<T> {

    private List<T> themes;

}
