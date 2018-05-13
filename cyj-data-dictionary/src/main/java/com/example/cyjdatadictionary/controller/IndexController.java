package com.example.cyjdatadictionary.controller;


import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@RestController
public class IndexController {


    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public ModelAndView hello() {
        return new ModelAndView("index");
    }

}
