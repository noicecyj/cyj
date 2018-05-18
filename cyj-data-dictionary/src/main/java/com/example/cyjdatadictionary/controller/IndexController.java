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

    @GetMapping("/{id}")
    public DictionaryCatalog findDictionaryCatalogId(@PathVariable Long id){
        return this.dictionaryCatalogService.findDictionaryCatalogById(id);
    }

    @GetMapping("/Dictionary{id}")
    public Dictionary findDictionaryId(@PathVariable Long id){
        return this.dictionaryService.findDictionaryById(id);
    }
}
