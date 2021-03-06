package com.example.cyjdictionary.service;

import com.example.cyjdictionary.entity.CatalogPO;
import org.springframework.data.domain.Page;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2021-02-02
 */
public interface CatalogService {

    /**
     * 添加实体
     *
     * @param po 实体
     * @return 实体
     */
    CatalogPO addOne(CatalogPO po);

    /**
     * 删除实体
     *
     * @param id 实体id
     */
    void deleteOne(String id);

    /**
     * 更新实体
     *
     * @param po 实体
     * @return 实体
     */
    CatalogPO updateOne(CatalogPO po);

    /**
     * 查找所有实体(分页排序)
     *
     * @param pageNumber 页码
     * @param pageSize   条目
     * @param sortCode   排序列
     * @return 实体列表分页
     */
    Page<CatalogPO> findAll(Integer pageNumber, Integer pageSize, String sortCode);


    /**
     * 查找实体
     *
     * @param id 实体id
     * @return 实体
     */
    CatalogPO findOneById(String id);

}
