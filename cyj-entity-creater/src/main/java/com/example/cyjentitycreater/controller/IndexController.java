package com.example.cyjentitycreater.controller;


import com.example.cyjentitycreater.entity.CreateVO;
import com.example.cyjentitycreater.entity.ResultVO;
import com.example.cyjentitycreater.service.EntityFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.example.cyjentitycreater.entity.ResultCode.CREATE_ENTITY;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2020/1/21 14:46
 */
@RestController
@RequestMapping(value = "entityCreateApi")
public class IndexController {

    private EntityFactory entityFactory;

    @Autowired
    public void setEntityFactory(EntityFactory entityFactory) {
        this.entityFactory = entityFactory;
    }

    @RequestMapping(value = "entity")
    public ResultVO entity(@RequestBody CreateVO createVO) {
        return ResultVO.success(entityFactory.entity(createVO));
    }

    @RequestMapping(value = "createEntity")
    public ResultVO createEntity(@RequestBody CreateVO createVO) {
        if (entityFactory.createEntity(createVO)) {
            return ResultVO.success(true);
        }
        return ResultVO.failure(CREATE_ENTITY,false);
    }
}