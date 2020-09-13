package com.example.sso.dao;

import com.example.sso.entity.UserPO;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2020-09-13
 */
public interface UserDao extends JpaRepository<UserPO,String> {
}
