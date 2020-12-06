package com.example.cyjpagemenu.serviceimpl;

import com.example.cyjpagemenu.dao.DataTableDao;
import com.example.cyjpagemenu.entity.*;
import com.example.cyjpagemenu.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2020-09-13
 */
@Service
public class IndexServiceImpl extends BaseService implements IndexService {

    private DataTableDao dataTableDao;

    @Autowired
    public void setDataTableDao(DataTableDao dataTableDao) {
        this.dataTableDao = dataTableDao;
    }

    @Override
    public List<DataItemPO> findDataFormByName(String name) {
        QDataFormPO qDataFormPO = QDataFormPO.dataFormPO;
        QDataItemPO qDataItemPO = QDataItemPO.dataItemPO;
        return queryFactory.selectFrom(qDataItemPO)
                .innerJoin(qDataFormPO)
                .on(qDataItemPO.pid.eq(qDataFormPO.id))
                .where(qDataFormPO.dataFormName.eq(name))
                .orderBy(qDataItemPO.sortCode.asc()).fetch();
    }

    @Override
    public List<DataTableItemPO> findDataTableByName(String name) {
        QDataTablePO qDataTablePO = QDataTablePO.dataTablePO;
        QDataTableItemPO qDataTableItemPO = QDataTableItemPO.dataTableItemPO;
        return queryFactory.selectFrom(qDataTableItemPO)
                .innerJoin(qDataTablePO)
                .on(qDataTableItemPO.pid.eq(qDataTablePO.id))
                .where(qDataTablePO.dataTableName.eq(name))
                .orderBy(qDataTableItemPO.sortCode.asc()).fetch();
    }

    @Override
    public DataTablePO findOneByName(String name) {
        if (dataTableDao.findDataTablePOByDataTableName(name).isPresent()){
            return dataTableDao.findDataTablePOByDataTableName(name).get();
        }
        return null;
    }
}