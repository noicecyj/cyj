package com.example.cyjpagemenu.dao;

import com.example.cyjpagemenu.entity.DataTablePO;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2020-12-07
 */
public interface IndexDao extends JpaRepository<DataTablePO, String> {

}