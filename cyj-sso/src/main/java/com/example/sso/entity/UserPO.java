package com.example.sso.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2020-08-09
 */
@Entity
@Table(name = UserPO.T_USER)
@Data
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class UserPO implements Serializable {
    static final String T_USER = "t_user";
    @Id
    @GeneratedValue(generator = "jpa-uuid")
    @Column(name = "id", length = 32)
    private String id;

    @Column
    private String username;

    @Column
    private String password;

    @Column
    private String name;

    @Column
    private String phone;

    @Column
    private String sortCode;

    @ManyToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @JoinTable(name = "t_user_role", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private List<RolePO> roles;

}