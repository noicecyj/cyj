package com.example.cyjdatadictionary.service;

import com.example.cyjdatadictionary.entity.Dictionary;
import java.util.List;

public interface DictionaryService {
    List<Dictionary> findAll();
    Dictionary findDictionaryById(Integer id);
    List<Dictionary> findDictionaryByDictionaryCatalog_Id(Integer pid);
    Dictionary save(Dictionary dictionary);
    Dictionary saveAndFlush(Dictionary dictionary);
    void delete(Integer id);
}
