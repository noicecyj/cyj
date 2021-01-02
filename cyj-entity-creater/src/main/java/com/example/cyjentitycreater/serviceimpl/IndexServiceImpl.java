package com.example.cyjentitycreater.serviceimpl;

import com.example.cyjentitycreater.dao.EntityDao;
import com.example.cyjentitycreater.dao.EntityNameDao;
import com.example.cyjentitycreater.dao.IndexDao;
import com.example.cyjentitycreater.entity.EntityNamePO;
import com.example.cyjentitycreater.entity.EntityPO;
import com.example.cyjentitycreater.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2020-09-13
 */
@Service
public class IndexServiceImpl extends BaseService implements IndexService {

    private EntityDao entityDao;
    private EntityNameDao entityNameDao;
    private IndexDao indexDao;

    @Autowired
    public void setEntityDao(EntityDao entityDao) {
        this.entityDao = entityDao;
    }

    @Autowired
    public void setEntityNameDao(EntityNameDao entityNameDao) {
        this.entityNameDao = entityNameDao;
    }

    @Autowired
    public void setIndexDao(IndexDao indexDao) {
        this.indexDao = indexDao;
    }

    @Override
    public void upEntity(String id) {
        Optional<EntityPO> po = entityDao.findById(id);
        if (po.isPresent()){
            String sortCode1 = po.get().getSortCode();
            List<EntityPO> poList = indexDao.findByPidAndSortCodeBeforeOrderBySortCodeDesc(po.get().getPid(),sortCode1);
            exchangeSortCode(po.get(), sortCode1, poList);
        }
    }

    @Override
    public void downEntity(String id) {
        Optional<EntityPO> po = entityDao.findById(id);
        if (po.isPresent()){
            String sortCode1 = po.get().getSortCode();
            List<EntityPO> poList = indexDao.findByPidAndSortCodeAfterOrderBySortCode(po.get().getPid(),sortCode1);
            exchangeSortCode(po.get(), sortCode1, poList);
        }
    }

    @Override
    public List<EntityNamePO> findAll(String tableName) {

        return entityNameDao.findAll();
    }

    private void exchangeSortCode(EntityPO po, String sortCode1, List<EntityPO> poList) {
        if (!poList.isEmpty()){
            String sortCode2 = poList.get(0).getSortCode();
            poList.get(0).setSortCode(sortCode1);
            po.setSortCode(sortCode2);
            entityDao.saveAndFlush(poList.get(0));
            entityDao.saveAndFlush(po);
        }
    }
}
