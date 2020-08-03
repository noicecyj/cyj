package com.example.cyjlog.dao;

import com.example.cyjlog.entity.LogPO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2020/1/21 14:46
 */
public interface LogDao extends JpaRepository<LogPO,String> {
    /**
     * 根据端口查询日志
     *
     * @param port 端口
     * @return 日志数据
     */
    List<LogPO> findAllByAppPort(String port);
}
