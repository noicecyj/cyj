package com.example.cyjpagemenu.controller;

import com.example.cyjpagemenu.entity.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2020-12-05
 */
public interface DataTableItemController {

    /**
     * 查询所有对象
     *
     * @param id         id
     * @param pageNumber 页码
     * @param pageSize   条目
     * @param sortCode   排序列
     * @return 返回结果
     */
    @PostMapping(value = "dataTableItemPage")
    ResultVO dataTableItemFindAll(@RequestParam("id") String id,
                         @RequestParam("pageNumber") Integer pageNumber,
                         @RequestParam("pageSize") Integer pageSize,
                         @RequestParam("sortCode") String sortCode);

    /**
     * 保存对象
     *
     * @param vo 对象
     * @return 返回结果
     */
    @PostMapping(value = "dataTableItemSave")
    ResultVO dataTableItemSave(@RequestBody Map<String, Object> vo);

    /**
     * 删除对象
     *
     * @param id 对象ID
     */
    @PostMapping(value = "dataTableItemDelete")
    void dataTableItemDelete(@RequestParam("id") String id);

}
