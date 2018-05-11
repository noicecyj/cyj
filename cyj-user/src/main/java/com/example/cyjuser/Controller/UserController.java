package com.example.cyjuser.Controller;

import com.example.cyjuser.Dao.UserDao;
import com.example.cyjuser.Entity.User;
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
    public User findById(@PathVariable Long id){
        return this.userDao.findOne(id);
    }
}
