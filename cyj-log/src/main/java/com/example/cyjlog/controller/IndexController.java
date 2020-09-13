package com.example.cyjlog.controller;

import com.example.cyjlog.entity.LogPO;
import com.example.cyjlog.serviceimpl.IndexServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2020/1/21 14:46
 */
@RestController
@RequestMapping(value = "logApi")
public class IndexController {

    private IndexServiceImpl logService;

    @Autowired
    public void setLogService(IndexServiceImpl logService) {
        this.logService = logService;
    }

    /**
     * 根据端口查询日志
     * @param port 端口
     * @return 返回值
     */
    @PostMapping(value = "findLogsByPort")
    public List<LogPO> findLogsByPort(@RequestParam("port") String port) {
        return logService.findLogsByPort(port);
    }

    /**
     *
     * @param name 服务名
     * @return 返回值
     */
    @PostMapping(value = "findLogsByName")
    public List<LogPO> findLogsByName(@RequestParam("name") String name) {
        return logService.findLogsByName(name);
    }

    /**
     *
     * @param port 端口
     * @param time1 时间1
     * @param time2 时间2
     * @return 返回值
     */
    @PostMapping(value = "findLogsByPortAndTime")
    public List<LogPO> findLogsByPortAndTime(@RequestParam("port") String port,
                                             @RequestParam("time1") String time1,
                                             @RequestParam("time2") String time2) {
        return logService.findLogsByPortAndTime(port, time1, time2);
    }

    /**
     *
     * @param port 端口
     */
    @PostMapping(value = "deleteLogsByPort")
    public void deleteLogsByPort(@RequestParam("port") String port) {
        logService.deleteLogsByPort(port);
    }
}
