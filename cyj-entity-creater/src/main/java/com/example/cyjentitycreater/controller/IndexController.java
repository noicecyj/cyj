package com.example.cyjentitycreater.controller;


import com.example.cyjentitycreater.entity.CreateVO;
import com.example.cyjentitycreater.service.BeanServiceImpl;
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

    private BeanServiceImpl beanServiceImpl;

    @Autowired
    public void setBeanServiceImpl(BeanServiceImpl beanServiceImpl) {
        this.beanServiceImpl = beanServiceImpl;
    }

    @RequestMapping(value = "entity")
    public String[] entity(@RequestBody CreateVO createVO) {
        return beanServiceImpl.entityGenerate(createVO);
    }

}
