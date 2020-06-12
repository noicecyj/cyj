package com.example.cyjdictionary.controller;


import com.example.cyjdictionary.entity.Dictionary;
import com.example.cyjdictionary.entity.DictionaryCatalog;
import com.example.cyjdictionary.service.DictionaryCatalogService;
import com.example.cyjdictionary.service.DictionaryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2020/1/21 14:46
 */
@RestController
@RequestMapping(value = "dictionaryApi")
public class IndexController {

    private DictionaryService dictionaryService;

    private DictionaryCatalogService dictionaryCatalogService;

    @Autowired
    public void setDictionaryService(DictionaryService dictionaryService) {
        this.dictionaryService = dictionaryService;
    }

    @Autowired
    public void setDictionaryCatalogService(DictionaryCatalogService dictionaryCatalogService) {
        this.dictionaryCatalogService = dictionaryCatalogService;
    }

    @ApiOperation(value = "分页查询目录")
    @PostMapping(value = "catalogPage")
    public Page<DictionaryCatalog> catalogFindAll(@RequestParam("pageNumber") Integer pageNumber,
                                                  @RequestParam("pageSize") Integer pageSize,
                                                  @RequestParam("sortCode") String sortCode) {
        return dictionaryCatalogService.findAll(pageNumber - 1, pageSize, sortCode);
    }

    @ApiOperation(value = "保存目录")
    @PostMapping(value = "saveCatalog")
    public DictionaryCatalog saveCatalog(@RequestBody DictionaryCatalog dictionaryCatalog) {
        if (dictionaryCatalog.getId() == null) {
            return dictionaryCatalogService.addOne(dictionaryCatalog);
        }
        return dictionaryCatalogService.updateOne(dictionaryCatalog);
    }

    @ApiOperation(value = "根据名称查询目录")
    @PostMapping(value = "findCatalogByName")
    public List<Dictionary> findCatalogByName(@RequestParam("name") String name,@RequestParam("sortCode") String sortCode) {
        return dictionaryService.findCatalogByName(name,sortCode);
    }

    @ApiOperation(value = "根据代码查询目录")
    @PostMapping(value = "findCatalogByValue")
    public List<Dictionary> findCatalogByValue(@RequestParam("value") String value,@RequestParam("sortCode") String sortCode) {
        return dictionaryService.findCatalogByValue(value,sortCode);
    }

    @ApiOperation(value = "删除目录")
    @PostMapping(value = "catalogDeleteOne")
    public void catalogDeleteOne(@RequestParam("id") String id) {
        dictionaryCatalogService.deleteOne(id);
    }

    @ApiOperation(value = "根据名称和代码模糊查询")
    @PostMapping(value = "findAllByCatalogNameContainsOrCatalogValueContains")
    public Page<DictionaryCatalog> findAllByCatalogNameContainsOrCatalogValueContains(@RequestParam("catalogName") String catalogName,
                                                                                      @RequestParam("catalogValue") String catalogValue,
                                                                                      @RequestParam("pageNumber") Integer pageNumber,
                                                                                      @RequestParam("pageSize") Integer pageSize,
                                                                                      @RequestParam("sortCode") String sortCode) {
        return dictionaryCatalogService.findAllByCatalogNameContainsOrCatalogValueContains(catalogName, catalogValue, pageNumber - 1, pageSize, sortCode);
    }

    @ApiOperation(value = "分页查询字典")
    @PostMapping(value = "dictionaryPage")
    public List<Dictionary> dictionaryFindAll(@RequestParam("sortCode") String id,
                                              @RequestParam("pageNumber") Integer pageNumber,
                                              @RequestParam("pageSize") Integer pageSize,
                                              @RequestParam("sortCode") String sortCode) {
        return dictionaryService.findAll(id,pageNumber - 1, pageSize, sortCode);
    }

    @ApiOperation(value = "保存字典")
    @PostMapping(value = "saveDictionary")
    public Dictionary saveDictionary(@RequestBody Dictionary dictionary) {
        if (dictionary.getId() == null) {
            return dictionaryService.addOne(dictionary);
        }
        return dictionaryService.updateOne(dictionary);
    }

    @ApiOperation(value = "删除字典")
    @PostMapping(value = "dictionaryDeleteOne")
    public void dictionaryDeleteOne(@RequestParam("id") String id) {
        dictionaryService.deleteOne(id);
    }
}
