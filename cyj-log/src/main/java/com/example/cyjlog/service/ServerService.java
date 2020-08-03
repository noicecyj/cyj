package com.example.cyjlog.service;

import com.example.cyjlog.entity.ServerPO;

import java.util.List;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2020/1/21 14:46
 */
public interface ServerService {

    /**
     * 添加服务
     *
     * @param po 字典
     * @return 字典
     */
    ServerPO addOne(ServerPO po);
    /**
     * 删除服务
     *
     * @param id 字典id
     */
    void deleteOne(String id);
    /**
     * 更新服务
     *
     * @param po 字典
     * @return 字典
     */
    ServerPO updateOne(ServerPO po);
    /**
     * 查找所有菜单
     *
     * @return 目录列表分页
     */
    List<ServerPO> findAll();
}
