package com.example.cyjdictionary.dao;

import com.example.cyjdictionary.entity.DictionaryPO;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2020/1/21 14:46
 */
public interface DictionaryDao extends JpaRepository<DictionaryPO, String> {
}