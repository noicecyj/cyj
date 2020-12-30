package com.example.cyjpagemenu.serviceimpl;

import com.example.cyjpagemenu.dao.IndexDataTableDao;
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

    private IndexDataTableDao indexDataTableDao;
    private DataFormServiceImpl dataFormService;
    private DataFormItemServiceImpl dataFormItemService;
    private DataTableServiceImpl dataTableService;
    private DataTableItemServiceImpl dataTableItemService;

    @Autowired
    public void setIndexDataTableDao(IndexDataTableDao indexDataTableDao) {
        this.indexDataTableDao = indexDataTableDao;
    }

    @Autowired
    public void setDataFormService(DataFormServiceImpl dataFormService) {
        this.dataFormService = dataFormService;
    }

    @Autowired
    public void setDataFormItemService(DataFormItemServiceImpl dataFormItemService) {
        this.dataFormItemService = dataFormItemService;
    }

    @Autowired
    public void setDataTableService(DataTableServiceImpl dataTableService) {
        this.dataTableService = dataTableService;
    }

    @Autowired
    public void setDataTableItemService(DataTableItemServiceImpl dataTableItemService) {
        this.dataTableItemService = dataTableItemService;
    }

    @Override
    public List<DataFormItemPO> findDataFormByName(String name) {
        QDataFormPO qDataFormPO = QDataFormPO.dataFormPO;
        QDataFormItemPO qDataFormItemPO = QDataFormItemPO.dataFormItemPO;
        return queryFactory.selectFrom(qDataFormItemPO)
                .innerJoin(qDataFormPO)
                .on(qDataFormItemPO.pid.eq(qDataFormPO.id))
                .where(qDataFormPO.dataFormName.eq(name))
                .orderBy(qDataFormItemPO.sortCode.asc()).fetch();
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
        if (indexDataTableDao.findDataTablePOByDataTableName(name).isPresent()) {
            return indexDataTableDao.findDataTablePOByDataTableName(name).get();
        }
        return null;
    }

    @Override
    public void formAndTableGenerate(String name) {
        DataFormPO dataFormPO = new DataFormPO();
        dataFormPO.setDataFormName(name + "Form");
        dataFormPO = dataFormService.addOne(dataFormPO);
        DataFormItemPO dataFormItemPO = new DataFormItemPO();
        dataFormItemPO.setPid(dataFormPO.getId());
        dataFormItemPO.setJsonData("{\"label\":\"排序代码\",\"required\":\"true\",\"name\":\"sortCode\",\"type\":\"Input\"}");
        dataFormItemService.addOne(dataFormItemPO);
        DataTablePO dataTablePO = new DataTablePO();
        dataTablePO.setDataTableName(name + "Table");
        dataTablePO = dataTableService.addOne(dataTablePO);
        DataTableItemPO dataTableItemPO = new DataTableItemPO();
        dataFormItemPO.setPid(dataTablePO.getId());
        dataFormItemPO.setJsonData("{\"title\":\"排序代码\",\"dataIndex\":\"sortCode\"}");
        dataTableItemService.addOne(dataTableItemPO);
    }
}
