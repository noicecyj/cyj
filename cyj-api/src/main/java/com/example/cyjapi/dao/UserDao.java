package com.example.cyjapi.dao;

import com.example.cyjapi.entity.UserPO;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2021-03-07
 */
public interface UserDao extends JpaRepository<UserPO, String> {
    /**
     * 查询用户
     *
     * @param name 用户名
     * @return 实体
     */
    UserPO findByUserName(String name);
}
