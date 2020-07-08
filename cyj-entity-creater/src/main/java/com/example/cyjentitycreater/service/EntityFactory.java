package com.example.cyjentitycreater.service;

import com.example.cyjentitycreater.entity.CreateVO;
import com.example.cyjentitycreater.entity.EntityType;
import com.example.cyjentitycreater.entity.ResultVO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2020/1/21 14:46
 */
@Service
public class EntityFactory {

    @Qualifier("poService")
    private PoServiceImpl poService;

    @Qualifier("voService")
    private VoServiceImpl voService;

    @Qualifier("dtoService")
    private DtoServiceImpl dtoService;

    @Qualifier("boService")
    private BoServiceImpl boService;

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
