package com.example.cyjentitycreater.controller;


import com.example.cyjentitycreater.BeanUtils;
import com.example.cyjentitycreater.entity.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class IndexController {

    private final BeanUtils beanUtils;

    @Autowired
    public IndexController(BeanUtils beanUtils) {
        this.beanUtils = beanUtils;
    }

    @RequestMapping(value = "entity")
    public String[] entity(@RequestBody List<Entity> entityList,@RequestParam("tableName") String tableName){
        return beanUtils.beanUtils(entityList,tableName);
    }
}
