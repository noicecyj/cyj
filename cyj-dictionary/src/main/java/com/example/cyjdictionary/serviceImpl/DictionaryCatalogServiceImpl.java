package com.example.cyjdictionary.serviceImpl;

import com.example.cyjdictionary.entity.DictionaryCatalog;
import com.example.cyjdictionary.dao.DictionaryCatalogDao;
import com.example.cyjdictionary.service.DictionaryCatalogService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DictionaryCatalogServiceImpl extends baseService implements DictionaryCatalogService {

	@Autowired
	DictionaryCatalogDao dictionaryCatalogDao;

	@Override
	public List<DictionaryCatalog> findAll() {
		return dictionaryCatalogDao.findAll();
	}

	@Override
	public DictionaryCatalog findOneById(Integer id) {
		return dictionaryCatalogDao.getOne(id);
	}

	@Override
	public DictionaryCatalog addOne(DictionaryCatalog dictionaryCatalog) {
		return dictionaryCatalogDao.save(dictionaryCatalog);
	}

	@Override
	public void deleteOne(Integer id) {
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
}