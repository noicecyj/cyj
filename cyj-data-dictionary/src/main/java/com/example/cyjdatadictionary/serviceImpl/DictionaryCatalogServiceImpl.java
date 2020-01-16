package com.example.cyjdatadictionary.serviceImpl;

import com.example.cyjcommon.entity.DictionaryCatalog;
import com.example.cyjdatadictionary.dao.DictionaryCatalogDao;
import com.example.cyjdatadictionary.service.DictionaryCatalogService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DictionaryCatalogServiceImpl implements DictionaryCatalogService {

	final DictionaryCatalogDao dictionaryCatalogDao;

	@Autowired
	public DictionaryCatalogServiceImpl(DictionaryCatalogDao dictionaryCatalogDao) {
		this.dictionaryCatalogDao = dictionaryCatalogDao;
	}

	@Override
	public List<DictionaryCatalog> findAll() {
		return dictionaryCatalogDao.findAll();
	}
}