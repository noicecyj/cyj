package com.example.cyjentitycreater.controller;


import com.example.cyjentitycreater.entity.CreateVO;
import com.example.cyjentitycreater.entity.ResultVO;
import com.example.cyjentitycreater.service.EntityFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        return entityFactory.entity(createVO);
    }
}