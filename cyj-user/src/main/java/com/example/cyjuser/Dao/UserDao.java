package com.example.cyjuser.Dao;

import com.example.cyjuser.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User,Long> {
}
