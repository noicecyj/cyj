package com.example.cyjdictionary.dao;

import com.example.cyjdictionary.entity.Dictionary;
import com.example.cyjdictionary.entity.DictionaryCatalog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * @author 曹元杰
 * @version 1.0
 * @date 2020/1/21 14:46
 */
public interface DictionaryDao extends JpaRepository<Dictionary,String> {
}