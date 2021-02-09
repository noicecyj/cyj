package com.example.sso.serviceimpl;

import com.example.sso.entity.*;
import com.example.sso.dao.*;
import com.example.sso.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2021-02-09
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl extends BaseService implements UserService {

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

}
