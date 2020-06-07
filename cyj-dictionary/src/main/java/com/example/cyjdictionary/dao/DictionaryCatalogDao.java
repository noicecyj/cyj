package com.example.cyjdictionary.dao;

import com.example.cyjdictionary.entity.DictionaryCatalog;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Pageable;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2020/1/21 14:46
 */
public interface DictionaryCatalogDao extends JpaRepository<DictionaryCatalog,String> {
    Page<DictionaryCatalog> findAllByCatalogNameContainsOrCatalogValueContains(String catalogName,
                                                                                    String catalogValue,
                                                                                    Pageable pageable);
}