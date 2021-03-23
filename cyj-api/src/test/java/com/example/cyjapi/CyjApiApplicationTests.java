package com.example.cyjapi;

import com.example.cyjapi.entity.AuthorityPO;
import com.example.cyjapi.entity.RolePO;
import com.example.cyjapi.entity.UserPO;
import com.example.cyjapi.serviceimpl.AuthorityServiceImpl;
import com.example.cyjapi.serviceimpl.RoleServiceImpl;
import com.example.cyjapi.serviceimpl.UserServiceImpl;
import com.google.common.collect.Sets;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2020-12-27
 */
@SpringBootTest
public class CyjApiApplicationTests {

    private UserServiceImpl userService;
    private AuthorityServiceImpl authorityService;
    private RoleServiceImpl roleService;

    @Autowired
    public void setUserService(UserServiceImpl userService) {
        this.userService = userService;
    }

    @Autowired
    public void setAuthorityService(AuthorityServiceImpl authorityService) {
        this.authorityService = authorityService;
    }

    @Autowired
    public void setRoleService(RoleServiceImpl roleService) {
        this.roleService = roleService;
    }

    @Test
    void contextLoads() {

        //权限
        AuthorityPO authority = new AuthorityPO();
        authority.setName("查询");
        authority.setValue("query");
        authorityService.addOne(authority);

        //角色
        RolePO admin = new RolePO();
        admin.setName("管理员");
        admin.setValue("ROLE_ADMIN");
        admin.setAuthorities(Sets.newHashSet(authorityService.findOneById(authority.getId())));
        roleService.addOne(admin);

        RolePO role = new RolePO();
        role.setName("普通用户");
        role.setValue("ROLE_USER");
        roleService.addOne(role);

//用户
        UserPO fpf = new UserPO();
        fpf.setUserName("234235");
        fpf.setPassword("fpf");
        fpf.setRoles(Sets.newHashSet(roleService.findOneById(role.getId())));
        userService.addOne(fpf);
    }

}
