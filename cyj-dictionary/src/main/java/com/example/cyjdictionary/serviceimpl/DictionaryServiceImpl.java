package com.example.cyjdictionary.serviceimpl;

import com.example.cyjdictionary.entity.Dictionary;
import com.example.cyjdictionary.entity.QDictionary;
import com.example.cyjdictionary.entity.QDictionaryCatalog;
import com.example.cyjdictionary.dao.DictionaryDao;
import com.example.cyjdictionary.service.DictionaryService;
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
public class DictionaryServiceImpl extends BaseService implements DictionaryService  {

    private DictionaryDao dictionaryDao;

    @Autowired
    public void setDictionaryDao(DictionaryDao dictionaryDao) {
        this.dictionaryDao = dictionaryDao;
    }

    @Override
    public List<Dictionary> findAll() {
        return dictionaryDao.findAll();
    }

    @Override
    public Dictionary findOneById(String id) {
        return dictionaryDao.getOne(id);
    }

    @Override
    public Dictionary addOne(Dictionary dictionary) {
        return dictionaryDao.save(dictionary);
    }

    @Override
    public void deleteOne(String id) {
         dictionaryDao.deleteById(id);
    }

    @Override
    public Dictionary updateOne(Dictionary dictionary) {
        return dictionaryDao.saveAndFlush(dictionary);
    }

    @Override
    public long count() {
        return dictionaryDao.count();
    }

    @Override
    public List<Dictionary> findCatalogById(String id) {
        QDictionary qDictionary = QDictionary.dictionary;
        QDictionaryCatalog qDictionaryCatalog = QDictionaryCatalog.dictionaryCatalog;
        return queryFactory.selectFrom(qDictionary)
                .innerJoin(qDictionaryCatalog)
                .on(qDictionary.pid.eq(qDictionaryCatalog.id))
                .where(qDictionaryCatalog.id.eq(id)).fetch();
    }

    @Override
    public List<Dictionary> findCatalogByName(String name) {
        QDictionary qDictionary = QDictionary.dictionary;
        QDictionaryCatalog qDictionaryCatalog = QDictionaryCatalog.dictionaryCatalog;
        return queryFactory.selectFrom(qDictionary)
                .innerJoin(qDictionaryCatalog)
                .on(qDictionary.pid.eq(qDictionaryCatalog.id))
                .where(qDictionaryCatalog.catalogName.eq(name)).fetch();
    }

    @Override
    public List<Dictionary> findCatalogByValue(String value) {
        QDictionary qDictionary = QDictionary.dictionary;
        QDictionaryCatalog qDictionaryCatalog = QDictionaryCatalog.dictionaryCatalog;
        return queryFactory.selectFrom(qDictionary)
                .innerJoin(qDictionaryCatalog)
                .on(qDictionary.pid.eq(qDictionaryCatalog.id))
                .where(qDictionaryCatalog.catalogValue.eq(value)).fetch();
    }

    @Override
    public Page<Dictionary> findAll(Integer pageNumber, Integer pageSize, String sortCode) {
        Sort sort = Sort.by(sortCode);
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        return dictionaryDao.findAll(pageable);
    }

}
