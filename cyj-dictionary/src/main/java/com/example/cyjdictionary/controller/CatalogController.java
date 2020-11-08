package com.example.cyjdictionary.controller;

import com.example.cyjdictionary.entity.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2020-11-09
 */
public interface CatalogController {

    /**
     * 查询所有对象
     *
     * @param pageNumber 页码
     * @param pageSize   条目
     * @param sortCode   排序列
     * @return 返回结果
     */
    @PostMapping(value = "catalogPage")
    ResultVO catalogFindAll(@RequestParam("pageNumber") Integer pageNumber,
                         @RequestParam("pageSize") Integer pageSize,
                         @RequestParam("sortCode") String sortCode);

    /**
     * 保存对象
     *
     * @param vo 对象
     * @return 返回结果
     */
    @PostMapping(value = "catalogSave")
    ResultVO catalogSave(@RequestBody Map<String, Object> vo);

    /**
     * 删除对象
     *
     * @param id 对象ID
     */
    @PostMapping(value = "catalogDelete")
    void catalogDelete(@RequestParam("id") String id);

}
