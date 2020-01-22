package com.example.cyjdictionary.controller;


import com.example.cyjdictionary.entity.Dictionary;
import com.example.cyjdictionary.entity.DictionaryCatalog;
import com.example.cyjdictionary.service.DictionaryCatalogService;
import com.example.cyjdictionary.service.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @RequestMapping(value = "dictionaryFindAll")
    public List<Dictionary> dictionaryFindAll(){
        return dictionaryService.findAll();
    }

    @RequestMapping(value = "dictionaryCatalogFindAll")
    public List<DictionaryCatalog> dictionaryCatalogFindAll(){
        return dictionaryCatalogService.findAll();
    }

    @RequestMapping(value = "dictionaryFindById")
    public Dictionary dictionaryFindById(@RequestParam("id") Integer id) {
        return dictionaryService.findOneById(id);
    }

    @RequestMapping(value = "dictionaryAddOne")
    public Dictionary dictionaryAddOne(@RequestBody Dictionary dictionary) {
        return dictionaryService.addOne(dictionary);
    }
    @RequestMapping(value = "dictionaryDeleteOne")
    public void dictionaryDeleteOne(@RequestParam("id") Integer id) {
        dictionaryService.deleteOne(id);
    }

    @RequestMapping(value = "dictionaryUpdateOne")
    public Dictionary dictionaryUpdateOne(@RequestBody Dictionary dictionary) {
        return dictionaryService.updateOne(dictionary);
    }

    @RequestMapping(value = "dictionaryCount")
    public long dictionaryCount() {
        return dictionaryService.count();
    }

    @RequestMapping(value = "findCatalogById")
    public List<Dictionary> findCatalogById(@RequestParam("id") Integer id){
        return dictionaryService.findCatalogById(id);
    }

    @RequestMapping(value = "findCatalogByName")
    public List<Dictionary> findCatalogByName(@RequestParam("name") String name){
        return dictionaryService.findCatalogByName(name);
    }

    @RequestMapping(value = "findCatalogByValue")
    public List<Dictionary> findCatalogByValue(@RequestParam("value") String value){
        return dictionaryService.findCatalogByValue(value);
    }

    @RequestMapping(value = "dictionaryCatalogAddOne")
    public DictionaryCatalog dictionaryCatalogAddOne(@RequestBody DictionaryCatalog dictionaryCatalog) {
        return dictionaryCatalogService.addOne(dictionaryCatalog);
    }

    @RequestMapping(value = "dictionaryCatalogUpdateOne")
    public DictionaryCatalog dictionaryCatalogUpdateOne(@RequestBody DictionaryCatalog dictionaryCatalog) {
        return dictionaryCatalogService.updateOne(dictionaryCatalog);
    }
}
