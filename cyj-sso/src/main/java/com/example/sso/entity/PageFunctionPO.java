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
@Table(name = PageFunctionPO.T_PAGE_FUNCTION)
@Data
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class PageFunctionPO implements Serializable {
    static final String T_PAGE_FUNCTION = "t_page_function";
    @Id
    @GeneratedValue(generator = "jpa-uuid")
    @Column(name = "id" ,length = 32)
    private String id;

    @Column(name = "page_id" ,length = 32)
    private String pageId;

    @Column
    private String name;

    @Column
    private String keyFunction;

    @Column
    private int status;

    @Column
    private Date addTime;

    @ManyToMany(cascade = CascadeType.REMOVE,fetch = FetchType.LAZY)
    @JoinTable(name = "t_page_role_function",joinColumns = @JoinColumn(name="function_id",referencedColumnName = "id"),inverseJoinColumns = @JoinColumn(name = "role_id",referencedColumnName = "id"))
    private List<RolePO> roles;

    @ManyToMany(cascade = CascadeType.REMOVE,fetch = FetchType.LAZY)
    @JoinTable(name = "t_function_api",joinColumns = @JoinColumn(name="function_id",referencedColumnName = "id"),inverseJoinColumns = @JoinColumn(name = "api_id",referencedColumnName = "id"))
    private List<ApiPO> apis;

}