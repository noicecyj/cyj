package com.example.cyjentitycreater.serviceimpl;

import com.example.cyjentitycreater.dao.EntityDao;
import com.example.cyjentitycreater.dao.EntityNameDao;
import com.example.cyjentitycreater.entity.EntityNamePO;
import com.example.cyjentitycreater.entity.EntityPO;
import com.example.cyjentitycreater.entity.QEntityPO;
import com.example.cyjentitycreater.service.EntityNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
public class EntityNameServiceImpl extends BaseService implements EntityNameService {

    private EntityNameDao entityNameDao;
    private EntityDao entityDao;

    @Autowired
    public void setEntityNameDao(EntityNameDao entityNameDao) {
        this.entityNameDao = entityNameDao;
    }

    @Autowired
    public void setEntityDao(EntityDao entityDao) {
        this.entityDao = entityDao;
    }

    @Override
    public EntityNamePO addOne(EntityNamePO po) {
        EntityNamePO entityNamePO = entityNameDao.save(po);
        EntityPO idEntityPO = new EntityPO();
        idEntityPO.setPid(entityNamePO.getId());
        idEntityPO.setEntityName("id");
        idEntityPO.setEntityProperty("String");
        idEntityPO.setSortCode("0010");
        entityDao.save(idEntityPO);
        return entityNamePO;
    }

    @Override
    public void deleteOne(String id) {
        entityNameDao.deleteById(id);
        entityDao.deleteByPid(id);
    }

    @Override
    public EntityNamePO updateOne(EntityNamePO po) {
        if (po.getRelEntity() != null && !"".equals(po.getRelEntity())) {
            String str = po.getRelEntity().substring(po.getRelEntity().indexOf("[") + 1, po.getRelEntity().indexOf("]"));
            String[] relEntities = str.split(",");
            for (String relEntity : relEntities) {
                QEntityPO qEntityPO = QEntityPO.entityPO;
                List<EntityPO> pidEntityPOs = queryFactory.selectFrom(qEntityPO)
                        .where(qEntityPO.entityName.eq("pid")
                                .and(qEntityPO.pid.eq(relEntity))).fetch();
                if (pidEntityPOs.size() == 0) {
                    EntityPO pidEntityPO = new EntityPO();
                    pidEntityPO.setPid(relEntity);
                    pidEntityPO.setEntityName("pid");
                    pidEntityPO.setEntityProperty("String");
                    pidEntityPO.setSortCode("0020");
                    entityDao.save(pidEntityPO);
                }
            }
        }
        return entityNameDao.saveAndFlush(po);
    }

    @Override
    public Page<EntityNamePO> findAll(Integer pageNumber, Integer pageSize, String sortCode) {
        Sort sort = Sort.by(sortCode);
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        return entityNameDao.findAll(pageable);
    }

    @Override
    public EntityNamePO findOneById(String id) {
        if (entityNameDao.findById(id).isPresent()) {
            return entityNameDao.findById(id).get();
        }
        return null;
    }

}
