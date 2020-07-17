package com.example.cyjdictionary.controller;

import com.example.cyjdictionary.entity.DictionaryPO;
import com.example.cyjdictionary.entity.DictionaryCatalogPO;
import com.example.cyjdictionary.entity.ResultVO;
import com.example.cyjdictionary.service.DictionaryCatalogService;
import com.example.cyjdictionary.service.DictionaryService;
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

    @PostMapping(value = "catalogPage")
    public ResultVO catalogFindAll(@RequestParam("pageNumber") Integer pageNumber,
                                   @RequestParam("pageSize") Integer pageSize,
                                   @RequestParam("sortCode") String sortCode) {
        return ResultVO.success(dictionaryCatalogService.findAll(pageNumber - 1, pageSize, sortCode));
    }

    @PostMapping(value = "saveCatalog")
    public ResultVO saveCatalog(@RequestBody DictionaryCatalogPO pd) {
        if (pd.getId() == null) {
            return ResultVO.success(dictionaryCatalogService.addOne(pd));
        }
        return ResultVO.success(dictionaryCatalogService.updateOne(pd));
    }

    @PostMapping(value = "findCatalogByName")
    public ResultVO findCatalogByName(@RequestParam("name") String name) {
        return ResultVO.success(dictionaryService.findCatalogByName(name));
    }

    @PostMapping(value = "findCatalogByValue")
    public ResultVO findCatalogByValue(@RequestParam("value") String value) {
        return ResultVO.success(dictionaryService.findCatalogByValue(value));
    }

    @PostMapping(value = "catalogDelete")
    public void catalogDeleteOne(@RequestParam("id") String id) {
        dictionaryCatalogService.deleteOne(id);
    }

    @PostMapping(value = "findAllByCatalogNameContainsOrCatalogValueContains")
    public ResultVO findAllByCatalogNameContainsOrCatalogValueContains(@RequestParam("catalogName") String catalogName,
                                                                       @RequestParam("catalogValue") String catalogValue,
                                                                       @RequestParam("pageNumber") Integer pageNumber,
                                                                       @RequestParam("pageSize") Integer pageSize,
                                                                       @RequestParam("sortCode") String sortCode) {
        return ResultVO.success(dictionaryCatalogService
                .findAllByCatalogNameContainsOrCatalogValueContains(catalogName, catalogValue, pageNumber - 1, pageSize, sortCode));
    }

//    @PostMapping(value = "dictionaryPage")
//    public ResultVO dictionaryFindAll(@RequestParam("id") String id,
//                                      @RequestParam("pageNumber") Integer pageNumber,
//                                      @RequestParam("pageSize") Integer pageSize,
//                                      @RequestParam("sortCode") String sortCode) {
//        return ResultVO.success(dictionaryService.findAll(id, pageNumber, pageSize, sortCode));
//    }

    @PostMapping(value = "saveDictionary")
    public ResultVO saveDictionary(@RequestBody DictionaryPO pd) {
        if (pd.getId() == null) {
            return ResultVO.success(dictionaryService.addOne(pd));
        }
        return ResultVO.success(dictionaryService.updateOne(pd));
    }

    @PostMapping(value = "dictionaryDelete")
    public void dictionaryDeleteOne(@RequestParam("id") String id) {
        dictionaryService.deleteOne(id);
    }
}
