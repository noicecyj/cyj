package com.example.cyjentitycreater.entity;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2020/7/07 14:46
 */
public enum EntityType {
    //值对象
    PO("PO"),
    //持久层对象
    VO("VO"),
    //数据传输对象
    DTO("DTO"),
    //业务对象
    BO("BO");

    private final String type;

    EntityType(String type){
        this.type = type;
    }

    public String getType(){
        return type;
    }
}
