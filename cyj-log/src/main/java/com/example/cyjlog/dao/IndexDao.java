package com.example.cyjlog.dao;

import com.example.cyjlog.entity.ServerPO;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2020-09-13
 */
public interface IndexDao extends JpaRepository<ServerPO, String> {

    /**
     * 根据名称查询服务
     *
     * @param name 名称
     * @return 服务
     */
    ServerPO findByServerName(String name);

}
