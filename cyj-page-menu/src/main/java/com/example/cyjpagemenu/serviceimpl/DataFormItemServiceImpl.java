package com.example.cyjpagemenu.serviceimpl;

import com.example.cyjcommon.utils.CommonUtils;
import com.example.cyjpagemenu.entity.*;
import com.example.cyjpagemenu.dao.*;
import com.example.cyjpagemenu.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
/**
 * @author 曹元杰
 * @version 1.0
 * @date 2020-12-16
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class DataFormItemServiceImpl extends BaseService implements DataFormItemService {

    private DataFormItemDao dataFormItemDao;

    @Autowired
    public void setDataFormItemDao(DataFormItemDao dataFormItemDao) {
        this.dataFormItemDao = dataFormItemDao;
    }

    @Override
    public DataFormItemPO addOne(DataFormItemPO po) {
        po.setJsonData("{\"label\":\"\",\"required\":\"\",\"name\":\"\",\"type\":\"\"}");
        return dataFormItemDao.save(po);
    }

    @Override
    public void deleteOne(String id) {
        dataFormItemDao.deleteById(id);
    }

    @Override
    public DataFormItemPO updateOne(DataFormItemPO po) {
        return dataFormItemDao.saveAndFlush(po);
    }

    @Override
    public Page<DataFormItemPO> findAll(String id, Integer pageNumber, Integer pageSize, String sortCode) {
        Sort sort = Sort.by(sortCode);
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, sort);
        List<DataFormItemPO> poList = findOneById(id);
        List<DataFormItemPO> poPage = CommonUtils.page(poList, pageSize, pageNumber);
        return new PageImpl<>(poPage, pageable, poList.size());
    }

    @Override
    public List<DataFormItemPO> findOneById(String id) {
        QDataFormItemPO qDataFormItemPO = QDataFormItemPO.dataFormItemPO;
        QDataFormPO qDataFormPO = QDataFormPO.dataFormPO;
        return queryFactory.selectFrom(qDataFormItemPO)
                .innerJoin(qDataFormPO)
                .on(qDataFormItemPO.pid.eq(qDataFormPO.id))
                .where(qDataFormPO.id.eq(id))
                .orderBy(qDataFormItemPO.sortCode.asc()).fetch();
    }

}
