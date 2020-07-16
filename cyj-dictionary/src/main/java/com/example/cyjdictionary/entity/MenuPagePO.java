package com.example.cyjdictionary.entity;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.io.Serializable;

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
    @Column(name = "id",length = 32)
    private String id;

    @Column(name = "pid", length = 32)
    private String pid;

    @Column(name = "name")
    private String name;

    @Column(name = "icon")
    private String icon;

    @Column(name = "path")
    private String path;

    @Column(name = "sort_code")
    private String sortCode;

}