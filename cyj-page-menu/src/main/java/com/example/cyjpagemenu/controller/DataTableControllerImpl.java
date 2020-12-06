package com.example.cyjpagemenu.controller;

import com.example.cyjcommon.utils.ResultVO;
import com.example.cyjcommon.utils.VoPoConverter;
import com.example.cyjpagemenu.entity.*;
import com.example.cyjpagemenu.serviceimpl.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2020-12-07
 */
@RestController
@RequestMapping(value = "pageMenuApi")
public class DataTableControllerImpl implements DataTableController {

    private DataTableServiceImpl dataTableService;

    @Autowired
    public void setDataTableService(DataTableServiceImpl dataTableService) {
        this.dataTableService = dataTableService;
    }

    @Override
    public ResultVO dataTableFindAll(Integer pageNumber, Integer pageSize, String sortCode) {
        return ResultVO.success(dataTableService.findAll(pageNumber - 1, pageSize, sortCode));
    }

    @Override
    public ResultVO dataTableSave(Map<String, Object> vo) {
        DataTablePO po = new DataTablePO();
        VoPoConverter.copyProperties(vo, po);
        if (po.getId() == null) {
            return ResultVO.success(dataTableService.addOne(po));
        }
        return ResultVO.success(dataTableService.updateOne(po));
    }

    @Override
    public void dataTableDelete(String id) {
        dataTableService.deleteOne(id);
    }

}
