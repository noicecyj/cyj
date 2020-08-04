package com.example.cyjlog.service;

import com.example.cyjlog.entity.LogPO;

import java.util.List;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2020/1/21 14:46
 */
public interface LogService {

    /**
     * 根据端口查询日志
     *
     * @param port 端口
     * @return 日志数据
     */
    List<LogPO> findLogsByPort(String port);

    /**
     * 根据服务名称查询日志
     *
     * @param name 名称
     * @return 日志数据
     */
    List<LogPO> findLogsByName(String name);

    /**
     * 根据端口和时间查询日志
     *
     * @param name  名称
     * @param time1 时间1
     * @param time2 时间2
     * @return 日志数据
     */
    List<LogPO> findLogsByPortAndTime(String name, String time1, String time2);

    /**
     * 根据端口删除日志
     *
     * @param port 端口
     */
    void deleteLogsByPort(String port);
}
