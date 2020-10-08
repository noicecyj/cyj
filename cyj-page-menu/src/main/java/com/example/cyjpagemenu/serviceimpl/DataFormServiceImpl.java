package com.example.cyjpagemenu.serviceimpl;

import com.example.cyjpagemenu.entity.DataFormPO;
import com.example.cyjpagemenu.dao.DataFormDao;
import com.example.cyjpagemenu.service.DataFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2020-10-08
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

}
