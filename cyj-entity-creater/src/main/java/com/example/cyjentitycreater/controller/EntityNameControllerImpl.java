package com.example.cyjentitycreater.controller;

import com.example.cyjcommon.utils.ResultVO;
import com.example.cyjcommon.utils.VoPoConverter;
import com.example.cyjentitycreater.entity.*;
import com.example.cyjentitycreater.serviceimpl.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2020-11-17
 */
@RestController
@RequestMapping(value = "entityCreateApi")
public class EntityNameControllerImpl implements EntityNameController {

    private EntityNameServiceImpl entityNameService;
    private EntityServiceImpl entityService;

    @Autowired
    public void setEntityNameService(EntityNameServiceImpl entityNameService) {
        this.entityNameService = entityNameService;
    }

    @Autowired
    public void setEntityService(EntityServiceImpl entityService) {
        this.entityService = entityService;
    }

    @Override
    public ResultVO entityNameFindAll(Integer pageNumber, Integer pageSize, String sortCode) {
        return ResultVO.success(entityNameService.findAll(pageNumber - 1, pageSize, sortCode));
    }

    @Override
    public ResultVO entityNameSave(Map<String, Object> vo) {
        EntityNamePO po = new EntityNamePO();
        VoPoConverter.copyProperties(vo, po);
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
