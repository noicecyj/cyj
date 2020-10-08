package com.example.cyjentitycreater.service;

import com.example.cyjentitycreater.entity.EntityNamePO;

import java.util.List;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2020-09-13
 */
public interface IndexService {

    /**
     * 上移属性
     *
     * @param id id
     */
    void upEntity(String id);

    /**
     * 下移属性
     *
     * @param id id
     */
    void downEntity(String id);

    /**
     * 选择所有实体
     *
     * @return 实体列表
     */
    List<EntityNamePO> findAll();
}
