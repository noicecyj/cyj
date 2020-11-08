package com.example.cyjdictionary.serviceimpl;

import com.example.cyjcommon.utils.CommonUtils;
import com.example.cyjdictionary.entity.*;
import com.example.cyjdictionary.dao.*;
import com.example.cyjdictionary.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;


import java.util.List;
/**
 * @author 曹元杰
 * @version 1.0
 * @date 2020-11-09
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
    public Page<DictionaryPO> findAll(String id, Integer pageNumber, Integer pageSize, String sortCode) {
        Sort sort = Sort.by(sortCode);
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, sort);
        List<DictionaryPO> poList = findOneById(id);
        List<DictionaryPO> poPage = CommonUtils.page(poList, pageSize, pageNumber);
        return new PageImpl<>(poPage, pageable, poList.size());
    }

    @Override
    public List<DictionaryPO> findOneById(String id) {
        QDictionaryPO qDictionaryPO = QDictionaryPO.dictionaryPO;
        QCatalogPO qCatalogPO = QCatalogPO.catalogPO;
        return queryFactory.selectFrom(qDictionaryPO)
                .innerJoin(qCatalogPO)
                .on(qDictionaryPO.pid.eq(qCatalogPO.id))
                .where(qCatalogPO.id.eq(id))
                .orderBy(qDictionaryPO.sortCode.asc()).fetch();
    }

}
