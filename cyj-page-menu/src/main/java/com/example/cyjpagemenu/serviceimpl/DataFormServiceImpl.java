package com.example.cyjpagemenu.serviceimpl;

import com.example.cyjpagemenu.entity.*;
import com.example.cyjpagemenu.dao.*;
import com.example.cyjpagemenu.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2020-11-09
 */
@Service
public class DataFormServiceImpl extends BaseService implements DataFormService {

    private DataFormDao dataFormDao;

    @Autowired
    public void setDataFormDao(DataFormDao dataFormDao) {
        this.dataFormDao = dataFormDao;
    }

    @Override
    public DataFormPO addOne(DataFormPO po) {
        return dataFormDao.save(po);
    }

    @Override
    public void deleteOne(String id) {
        dataFormDao.deleteById(id);
    }

    @Override
    public DataFormPO updateOne(DataFormPO po) {
        return dataFormDao.saveAndFlush(po);
    }

    @Override
    public Page<DataFormPO> findAll(Integer pageNumber, Integer pageSize, String sortCode) {
        Sort sort = Sort.by(sortCode);
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        return dataFormDao.findAll(pageable);
    }

    @Override
    public DataFormPO findOneById(String id) {
        if (dataFormDao.findById(id).isPresent()){
            return dataFormDao.findById(id).get();
        }
        return null;
    }

}