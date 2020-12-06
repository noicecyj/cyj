package com.example.cyjpagemenu.dao;

import com.example.cyjpagemenu.entity.DataTablePO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2020-12-07
 */
public interface IndexDataTableDao extends JpaRepository<DataTablePO, String> {
    /**
     * 查找实体
     *
     * @param name 实体name
     * @return 实体
     */
    Optional<DataTablePO> findDataTablePOByDataTableName(String name);
}