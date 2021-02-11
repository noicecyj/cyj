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
 * @date 2021-02-11
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ApiServiceImpl extends BaseService implements ApiService {

    private ApiDao apiDao;

    @Autowired
    public void setApiDao(ApiDao apiDao) {
        this.apiDao = apiDao;
    }

    @Override
    public ApiPO addOne(ApiPO po) {
        return apiDao.save(po);
    }

    @Override
    public void deleteOne(String id) {
        apiDao.deleteById(id);
    }

    @Override
    public ApiPO updateOne(ApiPO po) {
        return apiDao.saveAndFlush(po);
    }

    @Override
    public Page<ApiPO> findAll(Integer pageNumber, Integer pageSize, String sortCode) {
        Sort sort = Sort.by(sortCode);
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        return apiDao.findAll(pageable);
    }


    @Override
    public ApiPO findOneById(String id) {
        if (apiDao.findById(id).isPresent()) {
            return apiDao.findById(id).get();
        }
        return null;
    }

}
