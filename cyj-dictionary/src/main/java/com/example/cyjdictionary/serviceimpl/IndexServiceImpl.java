package com.example.cyjdictionary.serviceimpl;

import com.example.cyjcommon.utils.CommonUtils;
import com.example.cyjdictionary.dao.IndexDao;
import com.example.cyjdictionary.entity.CatalogPO;
import com.example.cyjdictionary.entity.DictionaryPO;
import com.example.cyjdictionary.entity.QCatalogPO;
import com.example.cyjdictionary.entity.QDictionaryPO;
import com.example.cyjdictionary.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2020-09-13
 */
@Service
public class IndexServiceImpl extends BaseService implements IndexService {

    private IndexDao indexDao;

    @Autowired
    public void setIndexDao(IndexDao indexDao) {
        this.indexDao = indexDao;
    }

    @Override
    public List<DictionaryPO> findCatalogById(String id) {
        QDictionaryPO qDictionary = QDictionaryPO.dictionaryPO;
        QCatalogPO qDictionaryCatalog = QCatalogPO.catalogPO;
        return queryFactory.selectFrom(qDictionary)
                .innerJoin(qDictionaryCatalog)
                .on(qDictionary.pid.eq(qDictionaryCatalog.id))
                .where(qDictionaryCatalog.id.eq(id))
                .orderBy(qDictionary.sortCode.asc()).fetch();
    }

    @Override
    public List<DictionaryPO> findCatalogByName(String name) {
        QDictionaryPO qDictionary = QDictionaryPO.dictionaryPO;
        QCatalogPO qDictionaryCatalog = QCatalogPO.catalogPO;
        return queryFactory.selectFrom(qDictionary)
                .innerJoin(qDictionaryCatalog)
                .on(qDictionary.pid.eq(qDictionaryCatalog.id))
                .where(qDictionaryCatalog.catalogName.eq(name))
                .orderBy(qDictionary.sortCode.asc()).fetch();
    }

    @Override
    public List<DictionaryPO> findCatalogByValue(String value) {
        QDictionaryPO qDictionary = QDictionaryPO.dictionaryPO;
        QCatalogPO qDictionaryCatalog = QCatalogPO.catalogPO;
        return queryFactory.selectFrom(qDictionary)
                .innerJoin(qDictionaryCatalog)
                .on(qDictionary.pid.eq(qDictionaryCatalog.id))
                .where(qDictionaryCatalog.catalogValue.eq(value))
                .orderBy(qDictionary.sortCode.asc()).fetch();
    }

    @Override
    public Page<DictionaryPO> findAll(String id, Integer pageNumber, Integer pageSize, String sortCode) {
        Sort sort = Sort.by(sortCode);
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, sort);
        List<DictionaryPO> poList = findCatalogById(id);
        List<DictionaryPO> poPage = CommonUtils.page(poList, pageSize, pageNumber);
        return new PageImpl<>(poPage, pageable, poList.size());
    }

    @Override
    public Page<CatalogPO> findAllByCatalogNameContainsOrCatalogValueContains(String catalogName,
                                                                              String catalogValue,
                                                                              Integer pageNumber,
                                                                              Integer pageSize,
                                                                              String sortCode) {
        Sort sort = Sort.by(sortCode);
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        return indexDao.findAllByCatalogNameContainsOrCatalogValueContains(catalogName, catalogValue, pageable);
    }
}
