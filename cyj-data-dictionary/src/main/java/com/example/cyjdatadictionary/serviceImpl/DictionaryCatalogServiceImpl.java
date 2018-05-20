package com.example.cyjdatadictionary.serviceImpl;

import java.util.List;

import com.example.cyjdatadictionary.dao.DictionaryCatalogDao;
import com.example.cyjdatadictionary.entity.DictionaryCatalog;
import com.example.cyjdatadictionary.service.DictionaryCatalogService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DictionaryCatalogServiceImpl implements DictionaryCatalogService {

    private final DictionaryCatalogDao dictionaryCatalogDao;

	@Autowired
	public DictionaryCatalogServiceImpl(DictionaryCatalogDao dictionaryCatalogDao) {
		this.dictionaryCatalogDao = dictionaryCatalogDao;
	}

	@Override
	public List<DictionaryCatalog> findAll() {
		return dictionaryCatalogDao.findAll();
	}

	@Override
	public DictionaryCatalog findDictionaryCatalogById(Integer id) {
		return dictionaryCatalogDao.findDictionaryCatalogById(id);
	}

}