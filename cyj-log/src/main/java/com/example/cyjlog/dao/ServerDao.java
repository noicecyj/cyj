package com.example.cyjlog.dao;

import com.example.cyjlog.entity.ServerPO;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2020-09-13
 */
public interface ServerDao extends JpaRepository<ServerPO, String> {
}
