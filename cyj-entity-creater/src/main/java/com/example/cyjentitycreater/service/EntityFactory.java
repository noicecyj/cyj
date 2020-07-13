package com.example.cyjentitycreater.service;

import com.example.cyjentitycreater.entity.CreateVO;
import com.example.cyjentitycreater.entity.EntityType;
import com.example.cyjentitycreater.entity.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public ResultVO entity(CreateVO createVO) {
        if (EntityType.PO.getType().equals(createVO.getType())) {
            return poService.entityGenerate(createVO);
        } else if (EntityType.VO.getType().equals(createVO.getType()) ||
                EntityType.BO.getType().equals(createVO.getType()) ||
                EntityType.DTO.getType().equals(createVO.getType())) {
            return otherService.entityGenerate(createVO);
        } else {
            return null;
        }
    }
}
