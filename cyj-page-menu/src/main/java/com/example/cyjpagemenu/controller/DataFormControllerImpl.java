package com.example.cyjpagemenu.controller;

import com.example.cyjcommon.utils.VoPoConverter;
import com.example.cyjpagemenu.entity.DataFormPO;
import com.example.cyjpagemenu.entity.ResultVO;
import com.example.cyjpagemenu.serviceimpl.DataFormServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2020-11-09
 */
@RestController
@RequestMapping(value = "pageMenuApi")
public class DataFormControllerImpl implements DataFormController {

    private DataFormServiceImpl dataFormService;

    @Autowired
    public void setDataFormService(DataFormServiceImpl dataFormService) {
        this.dataFormService = dataFormService;
    }

    @Override
    public ResultVO dataFormFindAll(Integer pageNumber, Integer pageSize, String sortCode) {
        return ResultVO.success(dataFormService.findAll(pageNumber - 1, pageSize, sortCode));
    }

    @Override
    public ResultVO dataFormSave(Map<String, Object> vo) {
        DataFormPO po = new DataFormPO();
        VoPoConverter.copyProperties(vo, po);
        if (po.getId() == null) {
            return ResultVO.success(dataFormService.addOne(po));
        }
        return ResultVO.success(dataFormService.updateOne(po));
    }

    @Override
    public void dataFormDelete(String id) {
        dataFormService.deleteOne(id);
    }

}
