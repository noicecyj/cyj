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
@Table(name = ApiPO.T_API)
@Data
@GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
public class ApiPO implements Serializable {
    static final String T_API = "t_api";
    @Id
    @GeneratedValue(generator = "uuid2")
    @Column(name = "id", length = 36)
    private String id;

    @Column
    private String name;

    @Column
    private String path;

    @Column
    private int status;

    @Column
    private Date addTime;

    @Column
    private String sortCode;

    @ManyToMany(mappedBy = "apis", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<PageFunctionPO> pageFunctions;

    @ManyToMany(mappedBy = "apis", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<MenuPagePO> menuPages;

}