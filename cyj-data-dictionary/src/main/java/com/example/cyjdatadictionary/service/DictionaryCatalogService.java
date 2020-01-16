package com.example.cyjdatadictionary.service;

import com.example.cyjcommon.entity.DictionaryCatalog;

import java.util.List;

public interface DictionaryCatalogService {
    List<DictionaryCatalog> findAll();
}