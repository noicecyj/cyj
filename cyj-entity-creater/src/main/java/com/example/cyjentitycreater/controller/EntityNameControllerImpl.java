package com.example.cyjentitycreater.controller;

import com.example.cyjentitycreater.entity.*;
import com.example.cyjentitycreater.serviceimpl.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2020-09-13
 */
@RestController
@RequestMapping(value = "entityCreateApi")
public class EntityNameControllerImpl implements EntityNameController {

    private EntityNameServiceImpl entityNameService;

    @Autowired
    public void setEntityNameService(EntityNameServiceImpl entityNameService) {
        this.entityNameService = entityNameService;
    }

    @Override
    public ResultVO entityNameFindAll(Integer pageNumber, Integer pageSize, String sortCode) {
        return ResultVO.success(entityNameService.findAll(pageNumber - 1, pageSize, sortCode));
    }

    @Override
    public ResultVO entityNameSave(EntityNamePO po) {
        if (po.getId() == null) {
            return ResultVO.success(entityNameService.addOne(po));
        }
        return ResultVO.success(entityNameService.updateOne(po));
    }

    @Override
    public void entityNameDelete(String id) {
        entityNameService.deleteOne(id);
    }

}
