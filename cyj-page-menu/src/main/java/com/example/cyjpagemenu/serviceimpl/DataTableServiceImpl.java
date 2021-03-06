package com.example.cyjpagemenu.serviceimpl;

import com.example.cyjpagemenu.dao.DataTableDao;
import com.example.cyjpagemenu.dao.DataTableItemDao;
import com.example.cyjpagemenu.entity.DataTablePO;
import com.example.cyjpagemenu.service.DataTableService;
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
 * @date 2021-02-02
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class DataTableServiceImpl extends BaseService implements DataTableService {

    private DataTableDao dataTableDao;
    private DataTableItemDao dataTableItemDao;

    @Autowired
    public void setDataTableDao(DataTableDao dataTableDao) {
        this.dataTableDao = dataTableDao;
    }

    @Autowired
    public void setDataTableItemDao(DataTableItemDao dataTableItemDao) {
        this.dataTableItemDao = dataTableItemDao;
    }

    @Override
    public DataTablePO addOne(DataTablePO po) {
        return dataTableDao.save(po);
    }

    @Override
    public void deleteOne(String id) {
        dataTableDao.deleteById(id);
        dataTableItemDao.deleteByPid(id);
    }

    @Override
    public void deleteAll(String id) {
        dataTableItemDao.deleteByPid(id);
    }

    @Override
    public DataTablePO updateOne(DataTablePO po) {
        return dataTableDao.saveAndFlush(po);
    }

    @Override
    public Page<DataTablePO> findAll(Integer pageNumber, Integer pageSize, String sortCode) {
        Sort sort = Sort.by(sortCode);
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        return dataTableDao.findAll(pageable);
    }

    @Override
    public DataTablePO findOneById(String id) {
        if (dataTableDao.findById(id).isPresent()) {
            return dataTableDao.findById(id).get();
        }
        return null;
    }

}
