package com.example.cyjdictionary.controller;

import com.example.cyjdictionary.entity.DictionaryCatalogPO;
import com.example.cyjdictionary.entity.DictionaryPO;
import com.example.cyjdictionary.entity.ResultVO;
import com.example.cyjdictionary.service.DictionaryCatalogService;
import com.example.cyjdictionary.service.DictionaryService;
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
public class IndexController implements DictionaryController {

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

    @Override
    public ResultVO catalogFindAll(@RequestParam("pageNumber") Integer pageNumber,
                                   @RequestParam("pageSize") Integer pageSize,
                                   @RequestParam("sortCode") String sortCode) {
        Page<DictionaryCatalogPO> pos = dictionaryCatalogService.findAll(pageNumber - 1, pageSize, sortCode);
        return ResultVO.success(pos);
    }

    @Override
    public ResultVO saveCatalog(@RequestBody DictionaryCatalogPO po) {
        if (po.getId() == null) {
            return ResultVO.success(dictionaryCatalogService.addOne(po));
        }
        return ResultVO.success(dictionaryCatalogService.updateOne(po));
    }

    @Override
    public List<DictionaryPO> findCatalogByName(@RequestParam("name") String name) {
        return dictionaryService.findCatalogByName(name);
    }

    @Override
    public List<DictionaryPO> findCatalogByValue(@RequestParam("value") String value) {
        return dictionaryService.findCatalogByValue(value);
    }

    @Override
    public void catalogDeleteOne(@RequestParam("id") String id) {
        dictionaryCatalogService.deleteOne(id);
    }

    @Override
    public ResultVO findAllByCatalogNameContainsOrCatalogValueContains(@RequestParam("catalogName") String catalogName,
                                                                       @RequestParam("catalogValue") String catalogValue,
                                                                       @RequestParam("pageNumber") Integer pageNumber,
                                                                       @RequestParam("pageSize") Integer pageSize,
                                                                       @RequestParam("sortCode") String sortCode) {
        return ResultVO.success(dictionaryCatalogService
                .findAllByCatalogNameContainsOrCatalogValueContains(catalogName, catalogValue, pageNumber - 1, pageSize, sortCode));
    }

    @Override
    public ResultVO dictionaryFindAll(@RequestParam("id") String id,
                                      @RequestParam("pageNumber") Integer pageNumber,
                                      @RequestParam("pageSize") Integer pageSize,
                                      @RequestParam("sortCode") String sortCode) {
        return ResultVO.success(dictionaryService.findAll(id, pageNumber, pageSize, sortCode));
    }

    @Override
    public ResultVO saveDictionary(@RequestBody DictionaryPO po) {
        if (po.getId() == null) {
            return ResultVO.success(dictionaryService.addOne(po));
        }
        return ResultVO.success(dictionaryService.updateOne(po));
    }

    @Override
    public void dictionaryDeleteOne(@RequestParam("id") String id) {
        dictionaryService.deleteOne(id);
    }
}
