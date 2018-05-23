package com.example.cyjdatadictionary.serviceImpl;

import com.example.cyjdatadictionary.dao.DictionaryDao;
import com.example.cyjdatadictionary.entity.Dictionary;
import com.example.cyjdatadictionary.service.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DictionaryServiceImpl implements DictionaryService {

    private final DictionaryDao dictionaryDao;

    @Autowired
    public DictionaryServiceImpl(DictionaryDao dictionaryDao) {
        this.dictionaryDao = dictionaryDao;
    }

    @Override
    public List<Dictionary> findAll() {
        return dictionaryDao.findAll();
    }

    @Override
    public Dictionary findDictionaryById(Integer id) {
        return dictionaryDao.findDictionaryById(id);
    }

    @Override
    public List<Dictionary> findDictionaryByDictionaryCatalog_Id(Integer pid) {
        return dictionaryDao.findDictionaryByDictionaryCatalog_Id(pid);
    }

    @Override
    public List<Dictionary> findDictionaryByDictionaryCatalog_CatalogValue(String catalogValue) {
        return dictionaryDao.findDictionaryByDictionaryCatalog_CatalogValue(catalogValue);
    }

    @Override
    public Dictionary save(Dictionary dictionary) {
        return dictionaryDao.save(dictionary);
    }

    @Override
    public Dictionary saveAndFlush(Dictionary dictionary) {
        return dictionaryDao.saveAndFlush(dictionary);
    }

    @Override
    public void delete(Integer id) {
        dictionaryDao.delete(id);
    }


}
