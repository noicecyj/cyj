package com.example.cyjentitycreater.entity;

import lombok.Data;

import java.util.List;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2020/7/03 14:46
 */
@Data
public class CreateVO {
    private List<Entity> entityData;
    private String type;
    private String name;
    private String[] method;
    private String lombok;
}
