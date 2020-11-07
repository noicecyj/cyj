package com.example.cyjpagemenu.dao;

import com.example.cyjpagemenu.entity.DataItemPO;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2020-10-08
 */
public interface DataItemDao extends JpaRepository<DataItemPO, String> {
}
