package com.example.cyjquery.controller;

import com.example.cyjcommon.utils.ResultVO;
import com.example.cyjquery.entity.SqlPO;
import com.example.cyjquery.serviceimpl.SqlServiceImpl;
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
@RequestMapping(value = "sqlApi")
public class SqlControllerImpl implements SqlController {

    private SqlServiceImpl sqlService;

    @Autowired
    public void setSqlService(SqlServiceImpl sqlService) {
        this.sqlService = sqlService;
    }

    @Override
    public ResultVO sqlFindAll(Integer pageNumber, Integer pageSize, String sortCode) {
        return ResultVO.success(sqlService.findAll(pageNumber - 1, pageSize, sortCode));
    }

    @Override
    public ResultVO sqlSave(SqlPO po) {
        if (po.getId() == null) {
            return ResultVO.success(sqlService.addOne(po));
        }
        return ResultVO.success(sqlService.updateOne(po));
    }

    @Override
    public void sqlDelete(String id) {
        sqlService.deleteOne(id);
    }

}
