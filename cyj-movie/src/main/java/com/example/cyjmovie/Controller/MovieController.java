package com.example.cyjmovie.Controller;

import com.example.cyjmovie.Entity.User;
import com.example.cyjmovie.Feign.UserFeign;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class MovieController {
    @Resource
    private UserFeign userFeign;

    @GetMapping("/user/{id}")
    public User findById(@PathVariable Long id){
        return this.userFeign.findById(id);
    }
}
