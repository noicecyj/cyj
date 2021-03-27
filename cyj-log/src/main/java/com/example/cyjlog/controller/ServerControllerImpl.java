package com.example.cyjlog.controller;

import com.example.cyjcommon.utils.ResultVO;
import com.example.cyjlog.entity.ServerPO;
import com.example.cyjlog.serviceimpl.ServerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2020-09-13
 */
@CrossOrigin
@RestController
@RequestMapping(value = "logApi")
public class ServerControllerImpl implements ServerController {

    private ServerServiceImpl serverService;

    @Autowired
    public void setServerService(ServerServiceImpl serverService) {
        this.serverService = serverService;
    }

    @Override
    public ResultVO serverFindAll(Integer pageNumber, Integer pageSize, String sortCode) {
        return ResultVO.success(serverService.findAll(pageNumber - 1, pageSize, sortCode));
    }

    @Override
    public ResultVO serverSave(ServerPO po) {
        if (po.getId() == null) {
            return ResultVO.success(serverService.addOne(po));
        }
        return ResultVO.success(serverService.updateOne(po));
    }

    @Override
    public void serverDelete(String id) {
        serverService.deleteOne(id);
    }

}
