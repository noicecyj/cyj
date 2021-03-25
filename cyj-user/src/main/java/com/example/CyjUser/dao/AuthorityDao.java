package com.example.CyjUser.dao;

import com.example.CyjUser.entity.AuthorityPO;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2021-03-20
 */
public interface AuthorityDao extends JpaRepository<AuthorityPO, String> {
}
