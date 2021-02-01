package com.example.cyjpagemenu.dao;

import com.example.cyjpagemenu.entity.DataFormPO;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2021-02-02
 */
public interface DataFormDao extends JpaRepository<DataFormPO, String> {
}
