package com.example.CyjUser.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2021-03-20
 */
@Entity
@Table(name = AuthorityPO.T_AUTHORITY)
@Data
@GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
public class AuthorityPO implements Serializable {

    static final String T_AUTHORITY = "t_authority";

    @Id
    @GeneratedValue(generator = "uuid2")
    @Column(name = "id", length = 36)
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "value")
    private String value;

}