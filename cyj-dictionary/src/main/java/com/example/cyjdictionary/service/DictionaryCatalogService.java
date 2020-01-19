package com.example.cyjdictionary.service;

import com.example.cyjdictionary.entity.Dictionary;
import com.example.cyjdictionary.entity.DictionaryCatalog;

import java.util.List;

public interface DictionaryCatalogService {
    List<DictionaryCatalog> findAll();
    DictionaryCatalog findOneById(Integer id);
    DictionaryCatalog addOne(DictionaryCatalog dictionaryCatalog);
    void deleteOne(Integer id);
    DictionaryCatalog updateOne(DictionaryCatalog dictionaryCatalog);
    long count();
}