package com.example.cyjpagemenu.dao;

import com.example.cyjpagemenu.entity.DataTablePO;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2021-02-02
 */
public interface DataTableDao extends JpaRepository<DataTablePO, String> {
}
