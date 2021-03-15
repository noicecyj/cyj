package com.example.cyjapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2020/1/21 14:46
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "authority")
public class SysAuthority implements GrantedAuthority {

    /**
     * 权限
     */
    private String authority;

    /**
     * 权限描述
     */
    private String desc;
}
