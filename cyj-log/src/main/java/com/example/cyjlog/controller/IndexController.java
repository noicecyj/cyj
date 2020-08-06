package com.example.cyjlog.controller;

import com.example.cyjlog.entity.LogPO;
import com.example.cyjlog.entity.ResultVO;
import com.example.cyjlog.entity.ServerPO;
import com.example.cyjlog.serviceimpl.LogServiceImpl;
import com.example.cyjlog.serviceimpl.ServerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2020/1/21 14:46
 */
@RestController
@RequestMapping(value = "logApi")
public class IndexController {

    private ServerServiceImpl serverService;
    private LogServiceImpl logService;

    @Autowired
    public void setServerService(ServerServiceImpl serverService) {
        this.serverService = serverService;
    }

    @Autowired
    public void setLogService(LogServiceImpl logService) {
        this.logService = logService;
    }

    @PostMapping(value = "saveServer")
    public ResultVO saveServer(@RequestBody ServerPO po) {
        if (po.getId() == null) {
            return ResultVO.success(serverService.addOne(po));
        }
        return ResultVO.success(serverService.updateOne(po));
    }

    @PostMapping(value = "findAll")
    public ResultVO findAll(@RequestParam("pageNumber") Integer pageNumber,
                            @RequestParam("pageSize") Integer pageSize,
                            @RequestParam("sortCode") String sortCode) {
        Page<ServerPO> pos = serverService.findAll(pageNumber - 1, pageSize, sortCode);
        return ResultVO.success(pos);
    }

    @PostMapping(value = "serverDelete")
    public void serverDelete(@RequestParam("id") String id) {
        serverService.deleteOne(id);
    }

    @PostMapping(value = "findLogsByPort")
    public List<LogPO> findLogsByPort(@RequestParam("port") String port) {
        return logService.findLogsByPort(port);
    }

    @PostMapping(value = "findLogsByName")
    public List<LogPO> findLogsByName(@RequestParam("name") String name) {
        return logService.findLogsByName(name);
    }

    @PostMapping(value = "findLogsByPortAndTime")
    public List<LogPO> findLogsByPortAndTime(@RequestParam("port") String port,
                                             @RequestParam("time1") String time1,
                                             @RequestParam("time2") String time2) {
        return logService.findLogsByPortAndTime(port, time1, time2);
    }

    @PostMapping(value = "deleteLogsByPort")
    public void deleteLogsByPort(@RequestParam("port") String port) {
        logService.deleteLogsByPort(port);
    }
}
