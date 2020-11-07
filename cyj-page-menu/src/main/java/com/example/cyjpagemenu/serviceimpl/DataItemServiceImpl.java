package com.example.cyjpagemenu.serviceimpl;

import com.example.cyjpagemenu.entity.DataItemPO;
import com.example.cyjpagemenu.dao.DataItemDao;
import com.example.cyjpagemenu.service.DataItemService;
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
public class DataItemServiceImpl extends BaseService implements DataItemService {

    private DataItemDao dataItemDao;

    @Autowired
    public void setDataItemDao(DataItemDao dataItemDao) {
        this.dataItemDao = dataItemDao;
    }

    @Override
    public DataItemPO addOne(DataItemPO po) {
        return dataItemDao.save(po);
    }

    @Override
    public void deleteOne(String id) {
        dataItemDao.deleteById(id);
    }

    @Override
    public DataItemPO updateOne(DataItemPO po) {
        return dataItemDao.saveAndFlush(po);
    }

    @Override
    public Page<DataItemPO> findAll(Integer pageNumber, Integer pageSize, String sortCode) {
        Sort sort = Sort.by(sortCode);
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        return dataItemDao.findAll(pageable);
    }

}
