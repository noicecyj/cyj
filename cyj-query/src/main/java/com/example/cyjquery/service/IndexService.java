package com.example.cyjquery.service;

import java.util.List;
import java.util.Map;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2020-09-13
 */
public interface IndexService {

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
