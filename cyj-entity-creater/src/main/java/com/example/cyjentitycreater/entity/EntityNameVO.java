package com.example.cyjentitycreater.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2020-09-13
 */
@Data
public class EntityNameVO implements Serializable {

    private String id;
    private String name;
    private String type;
    private String path;
    private String api;
    private String[] relEntity;
    private String sortCode;

}