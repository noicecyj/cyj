package com.example.cyjdictionary.serviceimpl;

import com.example.cyjdictionary.entity.DictionaryPO;
import com.example.cyjdictionary.dao.DictionaryDao;
import com.example.cyjdictionary.service.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2020-09-13
 */
@Service
public class DictionaryServiceImpl extends BaseService implements DictionaryService {

    private DictionaryDao dictionaryDao;

    @Autowired
    public void setDictionaryDao(DictionaryDao dictionaryDao) {
        this.dictionaryDao = dictionaryDao;
    }

    @Override
    public DictionaryPO addOne(DictionaryPO po) {
        return dictionaryDao.save(po);
    }

    @Override
    public void deleteOne(String id) {
        dictionaryDao.deleteById(id);
    }

    @Override
    public DictionaryPO updateOne(DictionaryPO po) {
        return dictionaryDao.saveAndFlush(po);
    }

    @Override
    public Page<DictionaryPO> findAll(Integer pageNumber, Integer pageSize, String sortCode) {
        Sort sort = Sort.by(sortCode);
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        return dictionaryDao.findAll(pageable);
    }

}
