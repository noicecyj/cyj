package com.example.sso.service;

import com.example.sso.entity.ApiPO;
import org.springframework.data.domain.Page;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2020-08-09
 */
public interface ApiService {

    /**
     * 添加实体
     *
     * @param po 实体
     * @return 实体
     */
    ApiPO addOne(ApiPO po);

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
    ApiPO updateOne(ApiPO po);

    /**
     * 查找所有实体(分页排序)
     *
     * @param id ID
     * @param pageNumber 页码
     * @param pageSize 条目
     * @param sortCode 排序列
     * @return 实体列表分页
     */
    Page<ApiPO> findAll(String id, Integer pageNumber, Integer pageSize, String sortCode);
}
