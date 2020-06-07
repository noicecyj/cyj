package com.example.cyjdictionary.serviceimpl;

import com.example.cyjdictionary.entity.DictionaryCatalog;
import com.example.cyjdictionary.dao.DictionaryCatalogDao;
import com.example.cyjdictionary.service.DictionaryCatalogService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2020/1/21 14:46
 */
@Service
public class DictionaryCatalogServiceImpl extends BaseService implements DictionaryCatalogService {

    private DictionaryCatalogDao dictionaryCatalogDao;

    @Autowired
    public void setDictionaryCatalogDao(DictionaryCatalogDao dictionaryCatalogDao) {
        this.dictionaryCatalogDao = dictionaryCatalogDao;
    }

    @Override
    public List<DictionaryCatalog> findAll() {
        return dictionaryCatalogDao.findAll();
    }

    @Override
    public Page<DictionaryCatalog> findAll(Integer pageNumber, Integer pageSize, String sortCode) {
        Sort sort = Sort.by(sortCode);
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        return dictionaryCatalogDao.findAll(pageable);
    }

    @Override
    public DictionaryCatalog findOneById(String id) {
        return dictionaryCatalogDao.getOne(id);
    }

    @Override
    public DictionaryCatalog addOne(DictionaryCatalog dictionaryCatalog) {
        return dictionaryCatalogDao.save(dictionaryCatalog);
    }

    @Override
    public void deleteOne(String id) {
        dictionaryCatalogDao.deleteById(id);
    }

    @Override
    public DictionaryCatalog updateOne(DictionaryCatalog dictionaryCatalog) {
        return dictionaryCatalogDao.saveAndFlush(dictionaryCatalog);
    }

    @Override
    public long count() {
        return dictionaryCatalogDao.count();
    }

    @Override
    public Page<DictionaryCatalog> findAllByCatalogNameContainsOrCatalogValueContains(String catalogName,
                                                                                           String catalogValue,
                                                                                           Integer pageNumber,
                                                                                           Integer pageSize,
                                                                                           String sortCode) {
        Sort sort = Sort.by(sortCode);
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        return dictionaryCatalogDao.findAllByCatalogNameContainsOrCatalogValueContains(catalogName,catalogValue,pageable);
    }
}