package com.example.cyjdictionary.dao;

import com.example.cyjdictionary.entity.DictionaryCatalog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DictionaryCatalogDao extends JpaRepository<DictionaryCatalog,Integer> {
}