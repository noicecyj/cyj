package com.example.cyjdictionary.dao;

import com.example.cyjdictionary.entity.CatalogPO;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2020-11-09
 */
public interface CatalogDao extends JpaRepository<CatalogPO, String> {
}