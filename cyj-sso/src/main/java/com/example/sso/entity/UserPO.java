package com.example.sso.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2021-02-09
 */
@Entity
@Table(name = UserPO.T_USER)
@Data
@GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
public class UserPO implements Serializable {

    static final String T_USER = "t_user";

    @Id
    @GeneratedValue(generator = "uuid2")
    @Column(name = "id", length = 36)
    private String id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "name")
    private String name;

    @Column(name = "phone")
    private String phone;

    @Column(name = "state")
    private String state;

    @Column(name = "sort_code")
    private String sortCode;

}