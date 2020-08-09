package com.example.sso.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2020-08-09
 */
@Entity
@Table(name = ApiPO.T_API)
@Data
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class ApiPO implements Serializable {
    static final String T_API = "t_api";
    @Id
    @GeneratedValue(generator = "jpa-uuid")
    @Column(name = "id", length = 32)
    private String id;

    @Column
    private String name;

    @Column
    private String path;

    @Column
    private Integer status;

    @Column
    private Date addTime;

}