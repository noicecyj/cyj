package com.example.cyjentitycreater.api;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2020/1/21 14:46
 */
@Component
@FeignClient(name = "cyj-query")
public interface QueryApiService {

    /**
     * 根据表名查询全部
     *
     * @param tableName 对象
     * @return map
     */
    @PostMapping(value = "/sqlApi/doFindAllSql")
    List<Map<String, Object>> doFindAllSql(@RequestParam("name") String tableName);
}
