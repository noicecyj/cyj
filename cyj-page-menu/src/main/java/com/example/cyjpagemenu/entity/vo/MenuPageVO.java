package com.example.cyjpagemenu.entity.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2020/1/21 14:46
 */
@Data
public class MenuPageVO implements Serializable {

    private String id;
    private String pid;
    private String name;
    private String icon;
    private String path;
    private String componentName;
    private String apiPath;
    private String sortCode;
    private List<MenuPageVO> children;

}
