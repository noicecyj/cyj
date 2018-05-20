package com.example.cyjdatadictionary.service;

import java.util.List;

import com.example.cyjdatadictionary.entity.DictionaryCatalog;

public interface DictionaryCatalogService {
    List<DictionaryCatalog> findAll();
    DictionaryCatalog findDictionaryCatalogById(Integer id);
    DictionaryCatalog save(DictionaryCatalog dictionaryCatalog);
    DictionaryCatalog saveAndFlush(DictionaryCatalog dictionaryCatalog);
    void delete(Integer id);
}