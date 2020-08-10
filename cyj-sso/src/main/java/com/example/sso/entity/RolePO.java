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
@Table(name = RolePO.T_ROLE)
@Data
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class RolePO implements Serializable {
    static final String T_ROLE = "t_role";
    @Id
    @GeneratedValue(generator = "jpa-uuid")
    @Column(name = "id", length = 32)
    private String id;

    @Column
    private String name;

    @Column
    private int status;

    @Column
    private String description;

    @Column
    private Date addTime;

    @Column
    private String sortCode;

}