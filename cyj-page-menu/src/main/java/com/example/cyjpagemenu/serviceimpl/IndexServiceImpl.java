package com.example.cyjpagemenu.serviceimpl;

import com.example.cyjpagemenu.entity.DataItemPO;
import com.example.cyjpagemenu.entity.QDataFormPO;
import com.example.cyjpagemenu.entity.QDataItemPO;
import com.example.cyjpagemenu.service.IndexService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2020-09-13
 */
@Service
public class IndexServiceImpl extends BaseService implements IndexService {

    @Override
    public List<DataItemPO> findDataFormByName(String name) {
        QDataFormPO qDataFormPO = QDataFormPO.dataFormPO;
        QDataItemPO qDataItemPO = QDataItemPO.dataItemPO;
        return queryFactory.selectFrom(qDataItemPO)
                .innerJoin(qDataFormPO)
                .on(qDataItemPO.pid.eq(qDataFormPO.id))
                .where(qDataFormPO.dataFormName.eq(name)
                        .and(qDataFormPO.dataFormType.eq("表单")))
                .orderBy(qDataItemPO.sortCode.asc()).fetch();
    }

    @Override
    public List<DataItemPO> findDataTableByName(String name) {
        QDataFormPO qDataFormPO = QDataFormPO.dataFormPO;
        QDataItemPO qDataItemPO = QDataItemPO.dataItemPO;
        return queryFactory.selectFrom(qDataItemPO)
                .innerJoin(qDataFormPO)
                .on(qDataItemPO.pid.eq(qDataFormPO.id))
                .where(qDataFormPO.dataFormName.eq(name)
                        .and(qDataFormPO.dataFormType.eq("表格")))
                .orderBy(qDataItemPO.sortCode.asc()).fetch();
    }
}
