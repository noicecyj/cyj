package com.example.sso.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2020-07-16
 */
@Entity
@Table(name = MenuPagePO.T_MENU_PAGE)
@Data
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class MenuPagePO implements Serializable {

    static final String T_MENU_PAGE = "t_menu_page";

    @Id
    @Column(name = "id", length = 32)
    private String id;

    @Column(name = "pid", length = 32)
    private String pid;

    @Column(name = "page_name")
    private String name;

    @Column(name = "icon")
    private String icon;

    @Column(name = "page_path")
    private String path;

    @Column(name = "component_name")
    private String componentName;

    @Column(name = "sort_code")
    private String sortCode;

    @ManyToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @JoinTable(name = "t_page_role", joinColumns = @JoinColumn(name = "page_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private List<RolePO> roles;

    @ManyToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @JoinTable(name = "t_page_api", joinColumns = @JoinColumn(name = "page_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "api_id", referencedColumnName = "id"))
    private List<ApiPO> apis;

}