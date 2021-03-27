package com.example.cyjentitycreater.serviceimpl;

import com.example.cyjentitycreater.dao.AppServiceDao;
import com.example.cyjentitycreater.entity.AppServicePO;
import com.example.cyjentitycreater.service.AppServiceService;
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
public class AppServiceServiceImpl extends BaseService implements AppServiceService {

    private AppServiceDao appServiceDao;

    @Autowired
    public void setAppServiceDao(AppServiceDao appServiceDao) {
        this.appServiceDao = appServiceDao;
    }

    @Override
    public AppServicePO addOne(AppServicePO po) {
        return appServiceDao.save(po);
    }

    @Override
    public void deleteOne(String id) {
        appServiceDao.deleteById(id);
    }

    @Override
    public AppServicePO updateOne(AppServicePO po) {
        return appServiceDao.saveAndFlush(po);
    }

    @Override
    public Page<AppServicePO> findAll(Integer pageNumber, Integer pageSize, String sortCode) {
        Sort sort = Sort.by(sortCode);
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        return appServiceDao.findAll(pageable);
    }


    @Override
    public AppServicePO findOneById(String id) {
        if (appServiceDao.findById(id).isPresent()) {
            return appServiceDao.findById(id).get();
        }
        return null;
    }

}
