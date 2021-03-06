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
 * @date 2021-02-02
 */
@CrossOrigin
@RestController
@RequestMapping(value = "entityCreateApi")
public class EntityControllerImpl implements EntityController {

    private EntityServiceImpl entityService;

    @Autowired
    public void setEntityService(EntityServiceImpl entityService) {
        this.entityService = entityService;
    }

    @Override
    public ResultVO entityFindAll(String id, Integer pageNumber, Integer pageSize, String sortCode) {
        return ResultVO.success(entityService.findAll(id, pageNumber, pageSize, sortCode));
    }

    @Override
    public ResultVO entitySave(Map<String, Object> vo) {
        EntityPO po = new EntityPO();
        VoPoConverter.copyProperties(vo, po);
        if (po.getId() == null) {
            return ResultVO.success(entityService.addOne(po));
        }
        return ResultVO.success(entityService.updateOne(po));
    }

    @Override
    public void entityDelete(String id) {
        entityService.deleteOne(id);
    }

    @Override
    public ResultVO findEntityById(String id) {
        return ResultVO.success(entityService.findOneById(id));
    }

}
