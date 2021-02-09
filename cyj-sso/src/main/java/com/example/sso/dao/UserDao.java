package com.example.sso.dao;

import com.example.sso.entity.UserPO;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2021-02-09
 */
public interface UserDao extends JpaRepository<UserPO, String> {
}
