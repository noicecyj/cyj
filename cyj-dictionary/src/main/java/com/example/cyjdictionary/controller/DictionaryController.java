package com.example.cyjdictionary.controller;

import com.example.cyjdictionary.entity.DictionaryPO;
import com.example.cyjdictionary.entity.ResultVO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2020/1/21 14:46
 */
public interface DictionaryController {

    /**
     * 查询所有字典
     *
     * @param id         目录ID
     * @param pageNumber 页码
     * @param pageSize   条目
     * @param sortCode   排序列
     * @return 返回结果
     */
    @PostMapping(value = "dictionaryPage")
    ResultVO dictionaryFindAll(@RequestParam("id") String id,
                               @RequestParam("pageNumber") Integer pageNumber,
                               @RequestParam("pageSize") Integer pageSize,
                               @RequestParam("sortCode") String sortCode);

    /**
     * 保存字典
     *
     * @param po 字典
     * @return 返回结果
     */
    @PostMapping(value = "saveDictionary")
    ResultVO saveDictionary(@RequestBody DictionaryPO po);

    /**
     * 删除字典
     *
     * @param id 字典ID
     */
    @PostMapping(value = "dictionaryDelete")
    void dictionaryDeleteOne(@RequestParam("id") String id);
}
