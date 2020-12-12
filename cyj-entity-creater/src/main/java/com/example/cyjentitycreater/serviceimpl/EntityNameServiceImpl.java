package com.example.cyjentitycreater.serviceimpl;

import com.example.cyjentitycreater.entity.*;
import com.example.cyjentitycreater.dao.*;
import com.example.cyjentitycreater.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2020-11-17
 */
@Service
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
        EntityPO sortCodeEntityPO = new EntityPO();
        sortCodeEntityPO.setPid(entityNamePO.getId());
        sortCodeEntityPO.setEntityName("sort_code");
        sortCodeEntityPO.setEntityProperty("String");
        sortCodeEntityPO.setSortCode("0100");
        entityDao.save(sortCodeEntityPO);
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
                EntityPO pidEntityPO = new EntityPO();
                pidEntityPO.setPid(relEntity);
                pidEntityPO.setEntityName("pid");
                pidEntityPO.setEntityProperty("String");
                pidEntityPO.setSortCode("0020");
                entityDao.save(pidEntityPO);
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
        if (entityNameDao.findById(id).isPresent()){
            return entityNameDao.findById(id).get();
        }
        return null;
    }

}
