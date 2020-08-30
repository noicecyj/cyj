package com.example.cyjquery.service;

import com.example.cyjquery.entity.SqlPO;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2020-08-30
 */
public interface SqlService {

    /**
     * 添加实体
     *
     * @param po 实体
     * @return 实体
     */
    SqlPO addOne(SqlPO po);

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
    SqlPO updateOne(SqlPO po);

    /**
     * 查找所有实体(分页排序)
     *
     * @param pageNumber 页码
     * @param pageSize 条目
     * @param sortCode 排序列
     * @return 实体列表分页
     */
    Page<SqlPO> findAll(Integer pageNumber, Integer pageSize, String sortCode);

    /**
     * 使用sql查询
     *
     * @param sql sql语句
     * @return 查询结果
     */
    List<Map<String,Object>> queryBySql(String sql);

    /**
     * 执行sql
     *
     * @param sql sql语句
     */
    void excuteSql(String sql);
}
