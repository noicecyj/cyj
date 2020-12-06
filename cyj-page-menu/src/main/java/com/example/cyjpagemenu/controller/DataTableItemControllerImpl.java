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
public class DataTableItemControllerImpl implements DataTableItemController {

    private DataTableItemServiceImpl dataTableItemService;

    @Autowired
    public void setDataTableItemService(DataTableItemServiceImpl dataTableItemService) {
        this.dataTableItemService = dataTableItemService;
    }

    @Override
    public ResultVO dataTableItemFindAll(String id, Integer pageNumber, Integer pageSize, String sortCode) {
        return ResultVO.success(dataTableItemService.findAll(id, pageNumber, pageSize, sortCode));
    }

    @Override
    public ResultVO dataTableItemSave(Map<String, Object> vo) {
        DataTableItemPO po = new DataTableItemPO();
        VoPoConverter.copyProperties(vo, po);
        if (po.getId() == null) {
            return ResultVO.success(dataTableItemService.addOne(po));
        }
        return ResultVO.success(dataTableItemService.updateOne(po));
    }

    @Override
    public void dataTableItemDelete(String id) {
        dataTableItemService.deleteOne(id);
    }

}
