package com.example.cyjdatadictionary.dao;

import com.example.cyjdatadictionary.entity.Dictionary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DictionaryDao extends JpaRepository<Dictionary,Integer> {
}