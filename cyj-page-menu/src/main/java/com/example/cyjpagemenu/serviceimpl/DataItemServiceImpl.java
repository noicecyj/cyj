package com.example.cyjpagemenu.serviceimpl;

import com.example.cyjcommon.utils.CommonUtils;
import com.example.cyjpagemenu.dao.DataItemDao;
import com.example.cyjpagemenu.entity.DataItemPO;
import com.example.cyjpagemenu.entity.QDataFormPO;
import com.example.cyjpagemenu.entity.QDataItemPO;
import com.example.cyjpagemenu.service.DataItemService;
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
public class DataItemServiceImpl extends BaseService implements DataItemService {

    private DataItemDao dataItemDao;

    @Autowired
    public void setDataItemDao(DataItemDao dataItemDao) {
        this.dataItemDao = dataItemDao;
    }

    @Override
    public DataItemPO addOne(DataItemPO po) {
        return dataItemDao.save(po);
    }

    @Override
    public void deleteOne(String id) {
        dataItemDao.deleteById(id);
    }

    @Override
    public DataItemPO updateOne(DataItemPO po) {
        return dataItemDao.saveAndFlush(po);
    }

    @Override
    public Page<DataItemPO> findAll(String id, Integer pageNumber, Integer pageSize, String sortCode) {
        Sort sort = Sort.by(sortCode);
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, sort);
        List<DataItemPO> poList = findOneById(id);
        List<DataItemPO> poPage = CommonUtils.page(poList, pageSize, pageNumber);
        return new PageImpl<>(poPage, pageable, poList.size());
    }

    @Override
    public List<DataItemPO> findOneById(String id) {
        QDataItemPO qDataItemPO = QDataItemPO.dataItemPO;
        QDataFormPO qDataFormPO = QDataFormPO.dataFormPO;
        return queryFactory.selectFrom(qDataItemPO)
                .innerJoin(qDataFormPO)
                .on(qDataItemPO.pid.eq(qDataFormPO.id))
                .where(qDataFormPO.id.eq(id))
                .orderBy(qDataItemPO.sortCode.asc()).fetch();
    }

}
