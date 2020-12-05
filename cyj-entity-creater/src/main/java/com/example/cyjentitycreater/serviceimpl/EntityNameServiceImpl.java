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

    @Autowired
    public void setEntityNameDao(EntityNameDao entityNameDao) {
        this.entityNameDao = entityNameDao;
    }

    @Override
    public EntityNamePO addOne(EntityNamePO po) {
        return entityNameDao.save(po);
    }

    @Override
    public void deleteOne(String id) {
        entityNameDao.deleteById(id);
    }

    @Override
    public EntityNamePO updateOne(EntityNamePO po) {
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
