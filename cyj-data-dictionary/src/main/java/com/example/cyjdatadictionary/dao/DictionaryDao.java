package com.example.cyjdatadictionary.dao;

import com.example.cyjcommon.entity.Dictionary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DictionaryDao extends JpaRepository<Dictionary,Integer> {
}