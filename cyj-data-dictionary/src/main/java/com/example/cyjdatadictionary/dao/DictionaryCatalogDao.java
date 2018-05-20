package com.example.cyjdatadictionary.dao;

import com.example.cyjdatadictionary.entity.DictionaryCatalog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DictionaryCatalogDao extends JpaRepository<DictionaryCatalog,Integer> {
    DictionaryCatalog findDictionaryCatalogById(Integer id);
}