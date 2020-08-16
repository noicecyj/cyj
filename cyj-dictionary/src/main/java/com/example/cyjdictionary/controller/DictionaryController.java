package com.example.cyjdictionary.controller;

import com.example.cyjdictionary.entity.DictionaryCatalogPO;
import com.example.cyjdictionary.entity.DictionaryPO;
import com.example.cyjdictionary.entity.ResultVO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2020/1/21 14:46
 */
public interface DictionaryController {

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
     * 根据目录名称查询字典
     *
     * @param name 目录名称
     * @return 返回结果
     */
    @PostMapping(value = "findCatalogByName")
    List<DictionaryPO> findCatalogByName(@RequestParam("name") String name);

    /**
     * 根据目录值查询字典
     *
     * @param value 目录值
     * @return 返回结果
     */
    @PostMapping(value = "findCatalogByValue")
    List<DictionaryPO> findCatalogByValue(@RequestParam("value") String value);

    /**
     * 删除目录
     *
     * @param id 目录ID
     */
    @PostMapping(value = "catalogDelete")
    void catalogDeleteOne(@RequestParam("id") String id);

    /**
     * 根据目录名称或者值模糊查询
     *
     * @param catalogName  目录名称
     * @param catalogValue 目录值
     * @param pageNumber   页码
     * @param pageSize     条目
     * @param sortCode     排序列
     * @return 返回结果
     */
    @PostMapping(value = "findAllByCatalogNameContainsOrCatalogValueContains")
    ResultVO findAllByCatalogNameContainsOrCatalogValueContains(@RequestParam("catalogName") String catalogName,
                                                                @RequestParam("catalogValue") String catalogValue,
                                                                @RequestParam("pageNumber") Integer pageNumber,
                                                                @RequestParam("pageSize") Integer pageSize,
                                                                @RequestParam("sortCode") String sortCode);

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
