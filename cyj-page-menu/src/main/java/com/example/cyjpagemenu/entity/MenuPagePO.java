package com.example.cyjpagemenu.entity;

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
public class MenuPagePO implements Serializable {

    static final String T_MENU_PAGE = "t_menu_page";

    @Id
    @GeneratedValue(generator = "jpa-uuid")
    @Column(name = "id", length = 32)
    private String id;

    @Column(name = "page_code", length = 10)
    private String pageCode;

    @Column(name = "parent_code", length = 10)
    private String parentCode;

    @Column(name = "page_name")
    private String pageName;

    @Column(name = "icon")
    private String icon;

    @Column(name = "page_path")
    private String pagePath;

    @Column(name = "sort_code")
    private String sortCode;

    @ManyToOne
    @JoinColumn(name = "parent_code",insertable = false,updatable = false)
    private MenuPagePO parent;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_code")
    private Set<MenuPagePO> children = new HashSet<>();

}