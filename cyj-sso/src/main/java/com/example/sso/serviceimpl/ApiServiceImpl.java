package com.example.sso.serviceimpl;

import com.example.sso.dao.ApiDao;
import com.example.sso.entity.ApiPO;
import com.example.sso.service.ApiService;
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

}
