package com.example.cyjpagemenu.controller;

import com.example.cyjcommon.utils.ResultVO;
import com.example.cyjcommon.utils.VoPoConverter;
import com.example.cyjpagemenu.entity.DataTableItemPO;
import com.example.cyjpagemenu.serviceimpl.DataTableItemServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2021-02-02
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

    @Override
    public ResultVO findDataTableItemById(String id) {
        return ResultVO.success(dataTableItemService.findOneById(id));
    }

}
