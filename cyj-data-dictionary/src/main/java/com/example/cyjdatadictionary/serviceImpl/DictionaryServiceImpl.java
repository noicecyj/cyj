package com.example.cyjdatadictionary.serviceImpl;

import com.example.cyjcommon.entity.Dictionary;
import com.example.cyjcommon.entity.QDictionary;
import com.example.cyjcommon.service.baseService;
import com.example.cyjdatadictionary.dao.DictionaryDao;
import com.example.cyjdatadictionary.service.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DictionaryServiceImpl extends baseService implements DictionaryService  {

    final DictionaryDao dictionaryDao;

    @Autowired
    public DictionaryServiceImpl(DictionaryDao dictionaryDao) {
        this.dictionaryDao = dictionaryDao;
    }

    @Override
    public List<Dictionary> findAll() {
        return dictionaryDao.findAll();
    }
}
