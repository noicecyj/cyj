package com.example.cyjpagemenu.service;

import com.example.cyjpagemenu.entity.DataTablePO;
import org.springframework.data.domain.Page;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2021-02-02
 */
public interface DataTableService {

    /**
     * 添加实体
     *
     * @param po 实体
     * @return 实体
     */
    DataTablePO addOne(DataTablePO po);

    /**
     * 删除实体
     *
     * @param id 实体id
     */
    void deleteOne(String id);

    /**
     * 删除所有实体
     *
     * @param id 实体id
     */
    void deleteAll(String id);

    /**
     * 更新实体
     *
     * @param po 实体
     * @return 实体
     */
    DataTablePO updateOne(DataTablePO po);

    /**
     * 查找所有实体(分页排序)
     *
     * @param pageNumber 页码
     * @param pageSize   条目
     * @param sortCode   排序列
     * @return 实体列表分页
     */
    Page<DataTablePO> findAll(Integer pageNumber, Integer pageSize, String sortCode);


    /**
     * 查找实体
     *
     * @param id 实体id
     * @return 实体
     */
    DataTablePO findOneById(String id);

}
