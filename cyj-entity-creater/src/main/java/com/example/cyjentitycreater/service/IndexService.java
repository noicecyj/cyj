package com.example.cyjentitycreater.service;

import com.example.cyjentitycreater.entity.EntityPO;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2020-09-13
 */
public interface IndexService {

    /**
     * 查找所有实体(分页排序)
     *
     * @param id id
     * @param pageNumber 页码
     * @param pageSize 条目
     * @param sortCode 排序列
     * @return 实体列表分页
     */
    Page<EntityPO> findAll(String id, Integer pageNumber, Integer pageSize, String sortCode);

    /**
     * 根据目录id查找字典
     *
     * @param id 目录id
     * @return 字典列表
     */
    List<EntityPO> findEntityById(String id);

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
}
