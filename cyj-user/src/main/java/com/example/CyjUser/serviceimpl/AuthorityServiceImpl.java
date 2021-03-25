package com.example.CyjUser.serviceimpl;

import com.example.CyjUser.dao.AuthorityDao;
import com.example.CyjUser.entity.AuthorityPO;
import com.example.CyjUser.service.AuthorityService;
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
 * @date 2021-03-20
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AuthorityServiceImpl extends BaseService implements AuthorityService {

    private AuthorityDao authorityDao;

    @Autowired
    public void setAuthorityDao(AuthorityDao authorityDao) {
        this.authorityDao = authorityDao;
    }

    @Override
    public AuthorityPO addOne(AuthorityPO po) {
        return authorityDao.save(po);
    }

    @Override
    public void deleteOne(String id) {
        authorityDao.deleteById(id);
    }

    @Override
    public void deleteAll(String id) {
    }

    @Override
    public AuthorityPO updateOne(AuthorityPO po) {
        return authorityDao.saveAndFlush(po);
    }

    @Override
    public Page<AuthorityPO> findAll(Integer pageNumber, Integer pageSize, String sortCode) {
        Sort sort = Sort.by(sortCode);
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        return authorityDao.findAll(pageable);
    }


    @Override
    public AuthorityPO findOneById(String id) {
        if (authorityDao.findById(id).isPresent()) {
            return authorityDao.findById(id).get();
        }
        return null;
    }

}
