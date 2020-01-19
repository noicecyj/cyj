package com.example.cyjdictionary.controller;


import com.example.cyjdictionary.entity.Dictionary;
import com.example.cyjdictionary.service.DictionaryCatalogService;
import com.example.cyjdictionary.service.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class IndexController {

    @Autowired
    DictionaryService dictionaryService;

    @Autowired
    DictionaryCatalogService dictionaryCatalogService;

    @RequestMapping(value = "dictionaryFindAll")
    public List<Dictionary> findAll(){
        return dictionaryService.findAll();
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
}
