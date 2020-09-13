package com.example.cyjquery.controller;

import com.example.cyjquery.entity.ResultVO;
import com.example.cyjquery.entity.SqlPO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2020-09-13
 */
public interface SqlController {

    /**
     * 查询所有对象
     *
     * @param pageNumber 页码
     * @param pageSize   条目
     * @param sortCode   排序列
     * @return 返回结果
     */
    @PostMapping(value = "sqlPage")
    ResultVO sqlFindAll(@RequestParam("pageNumber") Integer pageNumber,
                        @RequestParam("pageSize") Integer pageSize,
                        @RequestParam("sortCode") String sortCode);

    /**
     * 保存对象
     *
     * @param po 对象
     * @return 返回结果
     */
    @PostMapping(value = "sqlSave")
    ResultVO sqlSave(@RequestBody SqlPO po);

    /**
     * 删除对象
     *
     * @param id 对象ID
     */
    @PostMapping(value = "sqlDelete")
    void sqlDelete(@RequestParam("id") String id);

}
