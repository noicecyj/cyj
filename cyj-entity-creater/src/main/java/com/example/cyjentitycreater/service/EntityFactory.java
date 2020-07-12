package com.example.cyjentitycreater.service;

import com.example.cyjentitycreater.entity.CreateVO;
import com.example.cyjentitycreater.entity.EntityType;
import com.example.cyjentitycreater.entity.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2020/1/21 14:46
 */
@Service
public class EntityFactory {

    private PoServiceImpl poService;
    private VoServiceImpl voService;
    private DtoServiceImpl dtoService;
    private BoServiceImpl boService;

    @Autowired
    public void setPoService(PoServiceImpl poService) {
        this.poService = poService;
    }
    @Autowired
    public void setVoService(VoServiceImpl voService) {
        this.voService = voService;
    }
    @Autowired
    public void setDtoService(DtoServiceImpl dtoService) {
        this.dtoService = dtoService;
    }
    @Autowired
    public void setBoService(BoServiceImpl boService) {
        this.boService = boService;
    }

    public ResultVO entity(CreateVO createVO) {
        if (EntityType.PO.getType().equals(createVO.getType())) {
            return poService.entityGenerate(createVO);
        } else if (EntityType.VO.getType().equals(createVO.getType())) {
            return voService.entityGenerate(createVO);
        } else if (EntityType.DTO.getType().equals(createVO.getType())) {
            return dtoService.entityGenerate(createVO);
        } else if (EntityType.BO.getType().equals(createVO.getType())) {
            return boService.entityGenerate(createVO);
        }else {
            return null;
        }
    }
}
