package com.example.cyjpagemenu.entity.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2020/1/21 14:46
 */
@Data
public class DataSourceVO implements Serializable {

    private Object dataSource;
    private Object dataTableItemList;

}
