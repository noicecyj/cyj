package com.example.cyjdatadictionary.controller;


import com.example.cyjdatadictionary.entity.Dictionary;
import com.example.cyjdatadictionary.service.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class IndexController {

    final DictionaryService dictionaryService;

    @Autowired
    public IndexController(DictionaryService dictionaryService) {
        this.dictionaryService = dictionaryService;
    }

    @RequestMapping(value = "dictionaryFindAll")
    public List<Dictionary> findAll(){
        return dictionaryService.findAll();
    }
}
