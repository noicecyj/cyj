package com.example.cyjpagemenu.controller;

import com.example.cyjcommon.utils.VoPoConverter;
import com.example.cyjpagemenu.entity.*;
import com.example.cyjpagemenu.serviceimpl.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2020-11-16
 */
@RestController
@RequestMapping(value = "pageMenuApi")
public class DataItemControllerImpl implements DataItemController {

    private DataItemServiceImpl dataItemService;

    @Autowired
    public void setDataItemService(DataItemServiceImpl dataItemService) {
        this.dataItemService = dataItemService;
    }

    @Override
    public ResultVO dataItemFindAll(String id, Integer pageNumber, Integer pageSize, String sortCode) {
        return ResultVO.success(dataItemService.findAll(id, pageNumber, pageSize, sortCode));
    }

    @Override
    public ResultVO dataItemSave(Map<String, Object> vo) {
        DataItemPO po = new DataItemPO();
        VoPoConverter.copyProperties(vo, po);
        if (po.getId() == null) {
            return ResultVO.success(dataItemService.addOne(po));
        }
        return ResultVO.success(dataItemService.updateOne(po));
    }

    @Override
    public void dataItemDelete(String id) {
        dataItemService.deleteOne(id);
    }

}
