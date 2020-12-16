package com.example.sso.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2020-12-13
 */
@Entity
@Table(name = RolePO.T_ROLE)
@Data
@GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
public class RolePO implements Serializable {

    static final String T_ROLE = "t_role";

    @Id
    @GeneratedValue(generator = "uuid2")
    @Column(name = "id", length = 36)
    private String id;

}