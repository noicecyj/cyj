package com.example.cyjentitycreater.controller;


import com.example.cyjentitycreater.service.BeanServiceImpl;
import com.example.cyjentitycreater.entity.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "entityCreateApi")
public class IndexController {

    private final BeanServiceImpl beanServiceImpl;

    @Autowired
    public IndexController(BeanServiceImpl beanServiceImpl) {
        this.beanServiceImpl = beanServiceImpl;
    }

    @RequestMapping(value = "entity")
    public String[] entity(@RequestBody List<Entity> entityList,@RequestParam("tableName") String tableName,@RequestParam("method") String method){
        return beanServiceImpl.entityGenerate(entityList,tableName,method);
    }
}
