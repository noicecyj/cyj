package com.example.cyjlog.service;

import com.example.cyjlog.entity.ServerPO;
import org.springframework.data.domain.Page;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2020-09-13
 */
public interface ServerService {

    /**
     * 添加实体
     *
     * @param po 实体
     * @return 实体
     */
    ServerPO addOne(ServerPO po);

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
    ServerPO updateOne(ServerPO po);

    /**
     * 查找所有实体(分页排序)
     *
     * @param pageNumber 页码
     * @param pageSize   条目
     * @param sortCode   排序列
     * @return 实体列表分页
     */
    Page<ServerPO> findAll(Integer pageNumber, Integer pageSize, String sortCode);
}
