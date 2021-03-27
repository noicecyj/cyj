package com.example.cyjpagemenu.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2021-02-04
 */
@Entity
@Table(name = MenuPagePO.T_MENU_PAGE)
@Data
@GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
public class MenuPagePO implements Serializable {

    static final String T_MENU_PAGE = "t_menu_page";

    @Id
    @GeneratedValue(generator = "uuid2")
    @Column(name = "id", length = 36)
    private String id;

    @Column(name = "pid")
    private String pid;

    @Column(name = "name")
    private String name;

    @Column(name = "icon")
    private String icon;

    @Column(name = "path")
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