package com.example.cyjapi.dao;

import com.example.cyjapi.entity.RolePO;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2021-03-07
 */
public interface RoleDao extends JpaRepository<RolePO, String> {
}
