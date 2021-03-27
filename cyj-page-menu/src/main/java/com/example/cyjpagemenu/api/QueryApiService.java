package com.example.cyjpagemenu.api;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2020/1/21 14:46
 */
@Component
@FeignClient(name = "cyj-query")
public interface QueryApiService {

    /**
     * 通过主键sql查询
     *
     * @param tableName 表名
     * @param id 主键
     * @return 返回值
     */
    @PostMapping(value = "/sqlApi/doFindSqlById")
    Object findSqlById(@RequestParam("tableName") String tableName, @RequestParam("id") String id);

    /**
     * 通过主键sql查询
     *
     * @param tableName 表名
     * @return 返回值
     */
    @PostMapping(value = "/sqlApi/doFindAllSql")
    Object FindAllSql(@RequestParam("tableName") String tableName);

}
