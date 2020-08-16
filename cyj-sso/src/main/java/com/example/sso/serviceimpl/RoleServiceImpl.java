package com.example.sso.serviceimpl;

import com.example.sso.dao.RoleDao;
import com.example.sso.entity.RolePO;
import com.example.sso.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2020-08-09
 */
@Service
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
    public RolePO updateOne(RolePO po) {
        return roleDao.saveAndFlush(po);
    }

    @Override
    public Page<RolePO> findAll(String id, Integer pageNumber, Integer pageSize, String sortCode) {
        Sort sort = Sort.by(sortCode);
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        return roleDao.findAll(pageable);
    }

}
