package com.example.cyjpagemenu.service;

import com.example.cyjpagemenu.entity.*;
import org.springframework.data.domain.Page;
/**
 * @author 曹元杰
 * @version 1.0
 * @date 2020-12-16
 */
public interface DataFormService {

    /**
     * 添加实体
     *
     * @param po 实体
     * @return 实体
     */
    DataFormPO addOne(DataFormPO po);

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
    DataFormPO updateOne(DataFormPO po);

    /**
     * 查找所有实体(分页排序)
     *
     * @param pageNumber 页码
     * @param pageSize 条目
     * @param sortCode 排序列
     * @return 实体列表分页
     */
    Page<DataFormPO> findAll(Integer pageNumber, Integer pageSize, String sortCode);

    /**
     * 查找实体
     *
     * @param id 实体id
     * @return 实体
     */
    DataFormPO findOneById(String id);

}
