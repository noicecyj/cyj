package com.example.cyjdictionary.serviceimpl;

import com.example.cyjdictionary.dao.CatalogDao;
import com.example.cyjdictionary.dao.DictionaryDao;
import com.example.cyjdictionary.entity.CatalogPO;
import com.example.cyjdictionary.service.CatalogService;
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
public class CatalogServiceImpl extends BaseService implements CatalogService {

    private CatalogDao catalogDao;
    private DictionaryDao dictionaryDao;

    @Autowired
    public void setCatalogDao(CatalogDao catalogDao) {
        this.catalogDao = catalogDao;
    }

    @Autowired
    public void setDictionaryDao(DictionaryDao dictionaryDao) {
        this.dictionaryDao = dictionaryDao;
    }

    @Override
    public CatalogPO addOne(CatalogPO po) {
        return catalogDao.save(po);
    }

    @Override
    public void deleteOne(String id) {
        catalogDao.deleteById(id);
        dictionaryDao.deleteByPid(id);
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
        if (catalogDao.findById(id).isPresent()) {
            return catalogDao.findById(id).get();
        }
        return null;
    }

}
