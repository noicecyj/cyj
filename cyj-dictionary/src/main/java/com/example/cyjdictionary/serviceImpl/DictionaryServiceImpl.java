package com.example.cyjdictionary.serviceImpl;

import com.example.cyjdictionary.entity.Dictionary;
import com.example.cyjdictionary.entity.QDictionary;
import com.example.cyjdictionary.entity.QDictionaryCatalog;
import com.example.cyjdictionary.serviceImpl.baseService;
import com.example.cyjdictionary.dao.DictionaryDao;
import com.example.cyjdictionary.service.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DictionaryServiceImpl extends baseService implements DictionaryService  {

    @Autowired
    DictionaryDao dictionaryDao;

    @Override
    public List<Dictionary> findAll() {
        return dictionaryDao.findAll();
    }

    @Override
    public Dictionary findOneById(Integer id) {
        return dictionaryDao.getOne(id);
    }

    @Override
    public Dictionary addOne(Dictionary dictionary) {
        return dictionaryDao.save(dictionary);
    }

    @Override
    public void deleteOne(Integer id) {
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
    public List<Dictionary> findCatalogById(Integer id) {
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

}
