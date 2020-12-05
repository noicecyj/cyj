package com.example.cyjentitycreater.service;

import com.example.cyjentitycreater.entity.EntityNamePO;
import com.example.cyjentitycreater.entity.EntityType;
import com.example.cyjentitycreater.serviceimpl.OtherServiceImpl;
import com.example.cyjentitycreater.serviceimpl.PoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2020/1/21 14:46
 */
@Service
public class EntityFactory {

    private PoServiceImpl poService;
    private OtherServiceImpl otherService;

    @Autowired
    public void setPoService(PoServiceImpl poService) {
        this.poService = poService;
    }

    @Autowired
    public void setVoService(OtherServiceImpl voService) {
        this.otherService = voService;
    }

    public void createEntity(EntityNamePO po) {
        try {
            if (EntityType.PO.getType().equals(po.getType())) {
                poService.createJavaFile(po);
            } else if (EntityType.VO.getType().equals(po.getType()) ||
                    EntityType.BO.getType().equals(po.getType()) ||
                    EntityType.DTO.getType().equals(po.getType())) {
                otherService.createJavaFile(po);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
