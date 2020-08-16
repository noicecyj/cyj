package com.example.cyjdictionary.serviceimpl;

import com.example.cyjdictionary.dao.DictionaryCatalogDao;
import com.example.cyjdictionary.entity.DictionaryCatalogPO;
import com.example.cyjdictionary.service.DictionaryCatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

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
    public Page<DictionaryCatalogPO> findAll(Integer pageNumber, Integer pageSize, String sortCode) {
        Sort sort = Sort.by(sortCode);
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        return dictionaryCatalogDao.findAll(pageable);
    }

    @Override
    public DictionaryCatalogPO addOne(DictionaryCatalogPO po) {
        return dictionaryCatalogDao.save(po);
    }

    @Override
    public void deleteOne(String id) {
        dictionaryCatalogDao.deleteById(id);
    }

    @Override
    public DictionaryCatalogPO updateOne(DictionaryCatalogPO po) {
        return dictionaryCatalogDao.saveAndFlush(po);
    }

    @Override
    public Page<DictionaryCatalogPO> findAllByCatalogNameContainsOrCatalogValueContains(String catalogName,
                                                                                        String catalogValue,
                                                                                        Integer pageNumber,
                                                                                        Integer pageSize,
                                                                                        String sortCode) {
        Sort sort = Sort.by(sortCode);
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        return dictionaryCatalogDao.findAllByCatalogNameContainsOrCatalogValueContains(catalogName, catalogValue, pageable);
    }
}