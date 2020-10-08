package com.example.cyjdictionary.controller;

import com.example.cyjdictionary.entity.DictionaryPO;
import com.example.cyjdictionary.entity.ResultVO;
import com.example.cyjdictionary.serviceimpl.IndexServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2020/1/21 14:46
 */
@RestController
@RequestMapping(value = "dictionaryApi")
public class IndexController {

    private IndexServiceImpl indexService;

    @Autowired
    public void setIndexService(IndexServiceImpl indexService) {
        this.indexService = indexService;
    }

    /**
     * 根据目录名称查询字典
     *
     * @param name 目录名称
     * @return 返回结果
     */
    @PostMapping(value = "findCatalogByName")
    public List<DictionaryPO> findCatalogByName(@RequestParam("name") String name) {
        return indexService.findCatalogByName(name);
    }

    /**
     * 根据目录值查询字典
     *
     * @param value 目录值
     * @return 返回结果
     */
    @PostMapping(value = "findCatalogByValue")
    public List<DictionaryPO> findCatalogByValue(@RequestParam("value") String value) {
        return indexService.findCatalogByValue(value);
    }

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
    public ResultVO findAllByCatalogNameContainsOrCatalogValueContains(@RequestParam("catalogName") String catalogName,
                                                                       @RequestParam("catalogValue") String catalogValue,
                                                                       @RequestParam("pageNumber") Integer pageNumber,
                                                                       @RequestParam("pageSize") Integer pageSize,
                                                                       @RequestParam("sortCode") String sortCode) {
        return ResultVO.success(indexService
                .findAllByCatalogNameContainsOrCatalogValueContains(catalogName, catalogValue, pageNumber - 1, pageSize, sortCode));
    }

}
