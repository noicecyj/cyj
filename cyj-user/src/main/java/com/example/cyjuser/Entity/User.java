package com.example.cyjuser.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
public class User {

    public User() {
    }

    public User(Long id, String userName, String name, Integer age, BigDecimal balance) {
        this.id = id;
        this.userName = userName;
        this.name = name;
        this.age = age;
        this.balance = balance;
    }

    @Id
    private Long id;

    @Column
    private String userName;

    @Column
    private String name;

    @Column
    private Integer age;

    @Column
    private BigDecimal balance;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
