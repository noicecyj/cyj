package com.example.cyjdictionary.service;

import com.example.cyjdictionary.entity.Dictionary;

import java.util.List;

public interface DictionaryService {
    List<Dictionary> findAll();
    Dictionary findOneById(Integer id);
    Dictionary addOne(Dictionary dictionary);
    void deleteOne(Integer id);
    Dictionary updateOne(Dictionary dictionary);
    long count();
    List<Dictionary> findCatalogById(Integer id);
    List<Dictionary> findCatalogByName(String name);
    List<Dictionary> findCatalogByValue(String value);

}
