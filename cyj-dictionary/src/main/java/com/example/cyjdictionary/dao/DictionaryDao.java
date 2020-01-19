package com.example.cyjdictionary.dao;

import com.example.cyjdictionary.entity.Dictionary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DictionaryDao extends JpaRepository<Dictionary,Integer> {
}