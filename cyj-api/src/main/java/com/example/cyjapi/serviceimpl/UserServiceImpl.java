package com.example.cyjapi.serviceimpl;

import com.example.cyjapi.dao.UserDao;
import com.example.cyjapi.entity.SysAuthority;
import com.example.cyjapi.entity.UserPO;
import com.example.cyjapi.service.UserService;
import com.example.cyjapi.vo.UserDetailImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2021-03-07
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl extends BaseService implements UserService, UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private UserDao userDao;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserPO addOne(UserPO po) {
        return userDao.save(po);
    }

    @Override
    public void deleteOne(String id) {
        userDao.deleteById(id);
    }

    @Override
    public void deleteAll(String id) {
    }

    @Override
    public UserPO updateOne(UserPO po) {
        return userDao.saveAndFlush(po);
    }

    @Override
    public Page<UserPO> findAll(Integer pageNumber, Integer pageSize, String sortCode) {
        Sort sort = Sort.by(sortCode);
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        return userDao.findAll(pageable);
    }


    @Override
    public UserPO findOneById(String id) {
        if (userDao.findById(id).isPresent()) {
            return userDao.findById(id).get();
        }
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        logger.info("密码模式查询用户信息");
        UserPO po = userDao.findByName(s);
        if (po == null) {
            throw new UsernameNotFoundException("not found user:" + s);
        }
        UserDetailImpl userDetail = new UserDetailImpl();
        userDetail.setEnable(true);
        BeanUtils.copyProperties(po, userDetail);
        //这里权限列表,这个为方便直接下（实际开发中查询用户时连表查询出权限）
        Set<SysAuthority> authoritySet = new HashSet<>();
        authoritySet.add(new SysAuthority("admin", "管理员权限"));
        userDetail.setAuthorities(authoritySet);
        return userDetail;
    }

    /**
     * 这里模拟根据手机号查询用户
     *
     * @param mobile mobile
     * @return UserDetails
     * @throws UsernameNotFoundException UsernameNotFoundException
     */
    public UserDetails loadUserByMobile(String mobile) throws UsernameNotFoundException {
        logger.info("手机号模式查询用户信息");
        UserPO po = userDao.findByPhone(mobile);
        if (po == null) {
            throw new UsernameNotFoundException("not found mobile user:" + mobile);
        }
        UserDetailImpl userDetail = new UserDetailImpl();
        BeanUtils.copyProperties(po, userDetail);
        userDetail.setAuthorities(new ArrayList<>());
        userDetail.setEnable(true);
        return userDetail;
    }
}
