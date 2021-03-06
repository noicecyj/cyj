package com.example.cyjapi.serviceimpl;


import com.example.cyjapi.dao.UserDao;
import com.example.cyjapi.entity.UserPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2020/1/21 14:46
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    private UserDao userDao;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        UserPO user = userDao.findByName(userName);
        if (user == null) {
            throw new UsernameNotFoundException(userName);
        }
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
//        for (Role role : member.getRoles()) {
//            //角色必须是ROLE_开头，可以在数据库中设置
//            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.getRoleName());
//            grantedAuthorities.add(grantedAuthority);
//            //获取权限
//            for (Permission permission : role.getPermissions()) {
//                GrantedAuthority authority = new SimpleGrantedAuthority(permission.getUri());
//                grantedAuthorities.add(authority);
//            }
//        }
        return new User(user.getUserName(), user.getPassword(),
                true, true, true, true, grantedAuthorities);
    }
}
