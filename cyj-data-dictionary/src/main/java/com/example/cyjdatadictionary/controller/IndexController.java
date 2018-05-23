package com.example.cyjdatadictionary.controller;


import com.example.cyjdatadictionary.entity.Dictionary;
import com.example.cyjdatadictionary.entity.DictionaryCatalog;
import com.example.cyjdatadictionary.service.DictionaryCatalogService;
import com.example.cyjdatadictionary.service.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@RestController
public class IndexController {

    private final DictionaryCatalogService dictionaryCatalogService;
    private final DictionaryService dictionaryService;

    @Autowired
    public IndexController(DictionaryCatalogService dictionaryCatalogService, DictionaryService dictionaryService) {
        this.dictionaryCatalogService = dictionaryCatalogService;
        this.dictionaryService = dictionaryService;
    }


    @GetMapping("/index")
    public ModelAndView hello() {
        return new ModelAndView("index");
    }

    @GetMapping("/data")
    public List<DictionaryCatalog> data() {
        return dictionaryCatalogService.findAll();
    }

    @GetMapping("/dictionary")
    public List<Dictionary> findDictionaryByDictionaryCatalog_Id(@RequestParam("pid") Integer pid){
        return this.dictionaryService.findDictionaryByDictionaryCatalog_Id(pid);
    }

    @GetMapping("/saveDictionaryCatalog")
    public DictionaryCatalog saveDictionaryCatalog(@RequestParam("catalogName") String catalogName,
                                                   @RequestParam("catalogValue") String catalogValue,
                                                   @RequestParam("description") String description){
        DictionaryCatalog dictionaryCatalog = new DictionaryCatalog();
        dictionaryCatalog.setCatalogName(catalogName);
        dictionaryCatalog.setCatalogValue(catalogValue);
        dictionaryCatalog.setDescription(description);
        return this.dictionaryCatalogService.save(dictionaryCatalog);
    }

    @GetMapping("/updateDictionaryCatalog")
    public DictionaryCatalog updateDictionaryCatalog(@RequestParam("editCatalogId") Integer id,
                                                     @RequestParam("editCatalogName") String catalogName,
                                                     @RequestParam("editCatalogValue") String catalogValue,
                                                     @RequestParam("editDescription") String description){
        DictionaryCatalog dictionaryCatalog = new DictionaryCatalog();
        dictionaryCatalog.setId(id);
        dictionaryCatalog.setCatalogName(catalogName);
        dictionaryCatalog.setCatalogValue(catalogValue);
        dictionaryCatalog.setDescription(description);
        return this.dictionaryCatalogService.saveAndFlush(dictionaryCatalog);
    }

    @GetMapping("/saveDictionary")
    public Dictionary saveDictionary(@RequestParam("catalogId") Integer catalogId,
                                     @RequestParam("dictionaryName") String dictionaryName,
                                     @RequestParam("dictionaryValue") String dictionaryValue){
        DictionaryCatalog dictionaryCatalog =dictionaryCatalogService.findDictionaryCatalogById(catalogId);
        Dictionary dictionary = new Dictionary();
        dictionary.setDictionaryCatalog(dictionaryCatalog);
        dictionary.setDictionaryName(dictionaryName);
        dictionary.setDictionaryValue(dictionaryValue);
        return this.dictionaryService.save(dictionary);
    }

    @GetMapping("/updateDictionary")
    public Dictionary updateDictionary(@RequestParam("editDictionaryId") Integer id,
                                       @RequestParam("editCatalogId") Integer catalogId,
                                       @RequestParam("editDictionaryName") String dictionaryName,
                                       @RequestParam("editDictionaryValue") String dictionaryValue){
        DictionaryCatalog dictionaryCatalog =dictionaryCatalogService.findDictionaryCatalogById(catalogId);
        Dictionary dictionary = new Dictionary();
        dictionary.setId(id);
        dictionary.setDictionaryCatalog(dictionaryCatalog);
        dictionary.setDictionaryName(dictionaryName);
        dictionary.setDictionaryValue(dictionaryValue);
        return this.dictionaryService.saveAndFlush(dictionary);
    }

    @GetMapping("/deleteCatalog")
    public void deleteCatalog(@RequestParam("catalogId") Integer id){
        dictionaryCatalogService.delete(id);
    }

    @GetMapping("/deleteDictionary")
    public void deleteDictionary(@RequestParam("dictionaryId") Integer id){
        dictionaryService.delete(id);
    }

    @GetMapping("/catalogValue")
    public List<Dictionary> findDictionaryByDictionaryCatalog_CatalogValue(@RequestParam("CatalogValue") String catalogValue){
        return dictionaryService.findDictionaryByDictionaryCatalog_CatalogValue(catalogValue);
    }
}
