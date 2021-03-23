package com.example.cyjapi.dao;

import com.example.cyjapi.entity.AuthorityPO;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2021-03-20
 */
public interface AuthorityDao extends JpaRepository<AuthorityPO, String> {
}
