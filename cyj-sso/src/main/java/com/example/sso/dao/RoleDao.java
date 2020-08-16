package com.example.sso.dao;

import com.example.sso.entity.RolePO;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2020-08-09
 */
public interface RoleDao extends JpaRepository<RolePO, String> {
}
