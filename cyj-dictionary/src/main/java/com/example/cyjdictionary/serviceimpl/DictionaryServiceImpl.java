package com.example.cyjdictionary.serviceimpl;

import com.example.cyjdictionary.dao.DictionaryDao;
import com.example.cyjdictionary.entity.Dictionary;
import com.example.cyjdictionary.entity.QDictionary;
import com.example.cyjdictionary.entity.QDictionaryCatalog;
import com.example.cyjdictionary.service.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2020/1/21 14:46
 */
@Service
public class DictionaryServiceImpl extends BaseService implements DictionaryService {

    private DictionaryDao dictionaryDao;

    @Autowired
    public void setDictionaryDao(DictionaryDao dictionaryDao) {
        this.dictionaryDao = dictionaryDao;
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
    public List<Dictionary> findCatalogById(String id) {
        QDictionary qDictionary = QDictionary.dictionary;
        QDictionaryCatalog qDictionaryCatalog = QDictionaryCatalog.dictionaryCatalog;
        return queryFactory.selectFrom(qDictionary)
                .innerJoin(qDictionaryCatalog)
                .on(qDictionary.pid.eq(qDictionaryCatalog.id))
                .where(qDictionaryCatalog.id.eq(id))
                .orderBy(qDictionary.sortCode.asc()).fetch();
    }

    @Override
    public List<Dictionary> findCatalogByName(String name) {
        QDictionary qDictionary = QDictionary.dictionary;
        QDictionaryCatalog qDictionaryCatalog = QDictionaryCatalog.dictionaryCatalog;
        return queryFactory.selectFrom(qDictionary)
                .innerJoin(qDictionaryCatalog)
                .on(qDictionary.pid.eq(qDictionaryCatalog.id))
                .where(qDictionaryCatalog.catalogName.eq(name))
                .orderBy(qDictionary.sortCode.asc()).fetch();
    }

    @Override
    public List<Dictionary> findCatalogByValue(String value) {
        QDictionary qDictionary = QDictionary.dictionary;
        QDictionaryCatalog qDictionaryCatalog = QDictionaryCatalog.dictionaryCatalog;
        return queryFactory.selectFrom(qDictionary)
                .innerJoin(qDictionaryCatalog)
                .on(qDictionary.pid.eq(qDictionaryCatalog.id))
                .where(qDictionaryCatalog.catalogValue.eq(value))
                .orderBy(qDictionary.sortCode.asc()).fetch();
    }

    @Override
    public Page<Dictionary> findAll(String id, Integer pageNumber, Integer pageSize, String sortCode) {
        Sort sort = Sort.by(sortCode);
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, sort);
        List<Dictionary> dictionaryList = findCatalogById(id);
        List<Dictionary> dictionaryPage = page(dictionaryList, pageSize, pageNumber);
        return new PageImpl<>(dictionaryPage, pageable, dictionaryList.size());
    }

    public static List<Dictionary> page(List<Dictionary> dataList, int pageSize, int currentPage) {
        List<Dictionary> currentPageList = new ArrayList<>();
        if (dataList != null && dataList.size() > 0) {
            int currIdx = (currentPage > 1 ? (currentPage - 1) * pageSize : 0);
            for (int i = 0; i < pageSize && i < dataList.size() - currIdx; i++) {
                Dictionary data = dataList.get(currIdx + i);
                currentPageList.add(data);
            }
        }
        return currentPageList;
    }
}
