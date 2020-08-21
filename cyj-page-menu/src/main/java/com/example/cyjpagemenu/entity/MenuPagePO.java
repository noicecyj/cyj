package com.example.cyjpagemenu.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2020-07-16
 */
@Entity
@Table(name = MenuPagePO.T_MENU_PAGE)
@Data
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"})
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

    @Column(name = "api_path")
    private String apiPath;

    @Column(name = "sort_code")
    private String sortCode;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "pid")
    @OrderBy("sortCode")
    private Set<MenuPagePO> children = new HashSet<>();
}