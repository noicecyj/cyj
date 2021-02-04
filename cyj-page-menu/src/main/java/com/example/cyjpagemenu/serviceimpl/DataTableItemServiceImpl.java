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
 * @date 2021-02-02
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class DataTableItemServiceImpl extends BaseService implements DataTableItemService {

    private DataTableItemDao dataTableItemDao;

    @Autowired
    public void setDataTableItemDao(DataTableItemDao dataTableItemDao) {
        this.dataTableItemDao = dataTableItemDao;
    }

    @Override
    public DataTableItemPO addOne(DataTableItemPO po) {
        po.setJsonData("{\"title\":\"\",\"dataIndex\":\"\"}");
        return dataTableItemDao.save(po);
    }

    @Override
    public void deleteOne(String id) {
        dataTableItemDao.deleteById(id);
    }

    @Override
    public DataTableItemPO updateOne(DataTableItemPO po) {
        return dataTableItemDao.saveAndFlush(po);
    }

    @Override
    public Page<DataTableItemPO> findAll(String id, Integer pageNumber, Integer pageSize, String sortCode) {
        Sort sort = Sort.by(sortCode);
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, sort);
        List<DataTableItemPO> poList = findListById(id);
        List<DataTableItemPO> poPage = CommonUtils.page(poList, pageSize, pageNumber);
        return new PageImpl<>(poPage, pageable, poList.size());
    }

    @Override
    public List<DataTableItemPO> findListById(String id) {
        QDataTableItemPO qDataTableItemPO = QDataTableItemPO.dataTableItemPO;
        QDataTablePO qDataTablePO = QDataTablePO.dataTablePO;
        return queryFactory.selectFrom(qDataTableItemPO)
                .innerJoin(qDataTablePO)
                .on(qDataTableItemPO.pid.eq(qDataTablePO.id))
                .where(qDataTablePO.id.eq(id))
                .orderBy(qDataTableItemPO.sortCode.asc()).fetch();
    }

    @Override
    public DataTableItemPO findOneById(String id) {
        if (dataTableItemDao.findById(id).isPresent()) {
            return dataTableItemDao.findById(id).get();
        }
        return null;
    }

}
