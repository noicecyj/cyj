package com.example.sso.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2020-08-09
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

    @ManyToMany(mappedBy = "roles", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<UserPO> users;

    @ManyToMany(mappedBy = "roles", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<MenuPagePO> menuPages;

    @ManyToMany(mappedBy = "roles", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<PageFunctionPO> pageFunctions;
}