package com.example.cyjentitycreater.controller;

import com.example.cyjcommon.utils.VoPoConverter;
import com.example.cyjentitycreater.entity.EntityNamePO;
import com.example.cyjentitycreater.entity.EntityNameVO;
import com.example.cyjentitycreater.entity.ResultVO;
import com.example.cyjentitycreater.serviceimpl.EntityNameServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResultVO entityNameSave(EntityNameVO vo) {
        EntityNamePO po = new EntityNamePO();
        VoPoConverter.copyProperties(vo, po);
        String relEntity = StringUtils.join(vo.getRelEntity(), ",");
        po.setRelEntity(relEntity);
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
