package com.example.cyjdatadictionary.service;

import com.example.cyjdatadictionary.entity.Dictionary;
import java.util.List;

public interface DictionaryService {
    List<Dictionary> findAll();
    Dictionary findDictionaryById(Integer id);
    List<Dictionary> findDictionaryByDictionaryCatalog_Id(Integer pid);
    List<Dictionary> findDictionaryByDictionaryCatalog_CatalogValue(String  catalogValue);
    Dictionary save(Dictionary dictionary);
    Dictionary saveAndFlush(Dictionary dictionary);
    void delete(Integer id);
}
