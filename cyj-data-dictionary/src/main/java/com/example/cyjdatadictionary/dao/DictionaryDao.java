package com.example.cyjdatadictionary.dao;

import com.example.cyjdatadictionary.entity.Dictionary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DictionaryDao extends JpaRepository<Dictionary,Long> {
    Dictionary findDictionaryById(long id);
}