package com.example.cyjdictionary.controller;

import com.example.cyjdictionary.entity.DictionaryPO;
import com.example.cyjdictionary.entity.DictionaryCatalogPO;
import com.example.cyjdictionary.entity.ResultVO;
import com.example.cyjdictionary.service.DictionaryCatalogService;
import com.example.cyjdictionary.service.DictionaryService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public ResultVO catalogFindAll(@RequestParam("pageNumber") Integer pageNumber,
                                   @RequestParam("pageSize") Integer pageSize,
                                   @RequestParam("sortCode") String sortCode) {
        return ResultVO.success(dictionaryCatalogService.findAll(pageNumber - 1, pageSize, sortCode));
    }

    @ApiOperation(value = "保存目录")
    @PostMapping(value = "saveCatalog")
    public ResultVO saveCatalog(@RequestBody DictionaryCatalogPO dictionaryCatalogPO) {
        if (dictionaryCatalogPO.getId() == null) {
            return ResultVO.success(dictionaryCatalogService.addOne(dictionaryCatalogPO));
        }
        return ResultVO.success(dictionaryCatalogService.updateOne(dictionaryCatalogPO));
    }

    @ApiOperation(value = "根据名称查询目录")
    @PostMapping(value = "findCatalogByName")
    public ResultVO findCatalogByName(@RequestParam("name") String name) {
        return ResultVO.success(dictionaryService.findCatalogByName(name));
    }

    @ApiOperation(value = "根据代码查询目录")
    @PostMapping(value = "findCatalogByValue")
    public ResultVO findCatalogByValue(@RequestParam("value") String value) {
        return ResultVO.success(dictionaryService.findCatalogByValue(value));
    }

    @ApiOperation(value = "删除目录")
    @PostMapping(value = "catalogDelete")
    public void catalogDeleteOne(@RequestParam("id") String id) {
        dictionaryCatalogService.deleteOne(id);
    }

    @ApiOperation(value = "根据名称和代码模糊查询")
    @PostMapping(value = "findAllByCatalogNameContainsOrCatalogValueContains")
    public ResultVO findAllByCatalogNameContainsOrCatalogValueContains(@RequestParam("catalogName") String catalogName,
                                                                       @RequestParam("catalogValue") String catalogValue,
                                                                       @RequestParam("pageNumber") Integer pageNumber,
                                                                       @RequestParam("pageSize") Integer pageSize,
                                                                       @RequestParam("sortCode") String sortCode) {
        return ResultVO.success(dictionaryCatalogService
                .findAllByCatalogNameContainsOrCatalogValueContains(catalogName, catalogValue, pageNumber - 1, pageSize, sortCode));
    }

    @ApiOperation(value = "分页查询字典")
    @PostMapping(value = "dictionaryPage")
    public ResultVO dictionaryFindAll(@RequestParam("id") String id,
                                      @RequestParam("pageNumber") Integer pageNumber,
                                      @RequestParam("pageSize") Integer pageSize,
                                      @RequestParam("sortCode") String sortCode) {
        return ResultVO.success(dictionaryService.findAll(id, pageNumber, pageSize, sortCode));
    }

    @ApiOperation(value = "保存字典")
    @PostMapping(value = "saveDictionary")
    public ResultVO saveDictionary(@RequestBody DictionaryPO dictionaryPO) {
        if (dictionaryPO.getId() == null) {
            return ResultVO.success(dictionaryService.addOne(dictionaryPO));
        }
        return ResultVO.success(dictionaryService.updateOne(dictionaryPO));
    }

    @ApiOperation(value = "删除字典")
    @PostMapping(value = "dictionaryDelete")
    public void dictionaryDeleteOne(@RequestParam("id") String id) {
        dictionaryService.deleteOne(id);
    }
}
