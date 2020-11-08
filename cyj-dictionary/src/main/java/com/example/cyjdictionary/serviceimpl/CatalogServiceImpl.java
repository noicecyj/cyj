package com.example.cyjdictionary.serviceimpl;

import com.example.cyjdictionary.entity.*;
import com.example.cyjdictionary.dao.*;
import com.example.cyjdictionary.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2020-11-09
 */
@Service
public class CatalogServiceImpl extends BaseService implements CatalogService {

    private CatalogDao catalogDao;

    @Autowired
    public void setCatalogDao(CatalogDao catalogDao) {
        this.catalogDao = catalogDao;
    }

    @Override
    public CatalogPO addOne(CatalogPO po) {
        return catalogDao.save(po);
    }

    @Override
    public void deleteOne(String id) {
        catalogDao.deleteById(id);
    }

    @Override
    public CatalogPO updateOne(CatalogPO po) {
        return catalogDao.saveAndFlush(po);
    }

    @Override
    public Page<CatalogPO> findAll(Integer pageNumber, Integer pageSize, String sortCode) {
        Sort sort = Sort.by(sortCode);
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        return catalogDao.findAll(pageable);
    }

    @Override
    public CatalogPO findOneById(String id) {
        if (catalogDao.findById(id).isPresent()){
            return catalogDao.findById(id).get();
        }
        return null;
    }

}
