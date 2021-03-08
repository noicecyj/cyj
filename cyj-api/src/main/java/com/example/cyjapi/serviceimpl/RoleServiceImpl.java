package com.example.cyjapi.serviceimpl;

import com.example.cyjapi.entity.*;
import com.example.cyjapi.dao.*;
import com.example.cyjapi.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2021-03-07
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class RoleServiceImpl extends BaseService implements RoleService {

    private RoleDao roleDao;

    @Autowired
    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public RolePO addOne(RolePO po) {
        return roleDao.save(po);
    }

    @Override
    public void deleteOne(String id) {
        roleDao.deleteById(id);
    }

    @Override
    public void deleteAll(String id) {
    }

    @Override
    public RolePO updateOne(RolePO po) {
        return roleDao.saveAndFlush(po);
    }

    @Override
    public Page<RolePO> findAll(Integer pageNumber, Integer pageSize, String sortCode) {
        Sort sort = Sort.by(sortCode);
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        return roleDao.findAll(pageable);
    }


    @Override
    public RolePO findOneById(String id) {
        if (roleDao.findById(id).isPresent()) {
            return roleDao.findById(id).get();
        }
        return null;
    }

}
