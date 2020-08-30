package com.example.cyjquery.dao;

import com.example.cyjquery.entity.SqlPO;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2020-08-30
 */
public interface SqlDao extends JpaRepository<SqlPO,String> {
}
