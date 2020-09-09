package com.example.cyjdictionary.controller;

import com.example.cyjdictionary.entity.DictionaryCatalogPO;
import com.example.cyjdictionary.entity.ResultVO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2020/1/21 14:46
 */
public interface CatalogController {

    /**
     * 查询所有目录
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
     * 保存目录
     *
     * @param po 目录
     * @return 返回结果
     */
    @PostMapping(value = "saveCatalog")
    ResultVO saveCatalog(@RequestBody DictionaryCatalogPO po);

    /**
     * 删除目录
     *
     * @param id 目录ID
     */
    @PostMapping(value = "catalogDelete")
    void catalogDeleteOne(@RequestParam("id") String id);
}
