package com.example.cyjapi.serviceimpl;

import com.example.cyjapi.dao.UserDao;
import com.example.cyjapi.entity.UserPO;
import com.example.cyjapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2021-03-06
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

}
