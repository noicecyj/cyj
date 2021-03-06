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
 * @date 2021-02-02
 */
@CrossOrigin
@RestController
@RequestMapping(value = "pageMenuApi")
public class DataFormItemControllerImpl implements DataFormItemController {

    private DataFormItemServiceImpl dataFormItemService;

    @Autowired
    public void setDataFormItemService(DataFormItemServiceImpl dataFormItemService) {
        this.dataFormItemService = dataFormItemService;
    }

    @Override
    public ResultVO dataFormItemFindAll(String id, Integer pageNumber, Integer pageSize, String sortCode) {
        return ResultVO.success(dataFormItemService.findAll(id, pageNumber, pageSize, sortCode));
    }

    @Override
    public ResultVO dataFormItemSave(Map<String, Object> vo) {
        DataFormItemPO po = new DataFormItemPO();
        VoPoConverter.copyProperties(vo, po);
        if (po.getId() == null) {
            return ResultVO.success(dataFormItemService.addOne(po));
        }
        return ResultVO.success(dataFormItemService.updateOne(po));
    }

    @Override
    public void dataFormItemDelete(String id) {
        dataFormItemService.deleteOne(id);
    }

    @Override
    public ResultVO findDataFormItemById(String id) {
        return ResultVO.success(dataFormItemService.findOneById(id));
    }

}
