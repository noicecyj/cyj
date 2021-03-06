package com.example.cyjentitycreater.controller;

import com.example.cyjcommon.utils.ResultVO;
import com.example.cyjcommon.utils.VoPoConverter;
import com.example.cyjentitycreater.entity.EntityNamePO;
import com.example.cyjentitycreater.serviceimpl.EntityNameServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2021-02-02
 */
@CrossOrigin
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

    @Override
    public ResultVO findEntityNameById(String id) {
        return ResultVO.success(entityNameService.findOneById(id));
    }

}
