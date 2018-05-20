package com.example.cyjuser.controller;

import com.example.cyjuser.dao.UserDao;
import com.example.cyjuser.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserDao userDao;

    @Autowired
    public UserController(UserDao userDao) {
        this.userDao = userDao;
    }

    @GetMapping("/{id}")
    public User findById(@PathVariable Integer id){
        return this.userDao.findOne(id);
    }
}
