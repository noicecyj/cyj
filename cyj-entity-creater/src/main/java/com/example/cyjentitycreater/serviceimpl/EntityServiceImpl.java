package com.example.cyjentitycreater.serviceimpl;

import com.example.cyjcommon.utils.CommonUtils;
import com.example.cyjentitycreater.dao.EntityDao;
import com.example.cyjentitycreater.entity.EntityPO;
import com.example.cyjentitycreater.entity.QEntityNamePO;
import com.example.cyjentitycreater.entity.QEntityPO;
import com.example.cyjentitycreater.service.EntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
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
public class EntityServiceImpl extends BaseService implements EntityService {

    private EntityDao entityDao;

    @Autowired
    public void setEntityDao(EntityDao entityDao) {
        this.entityDao = entityDao;
    }

    @Override
    public EntityPO addOne(EntityPO po) {
        return entityDao.save(po);
    }

    @Override
    public void deleteOne(String id) {
        entityDao.deleteById(id);
    }

    @Override
    public EntityPO updateOne(EntityPO po) {
        return entityDao.saveAndFlush(po);
    }

    @Override
    public Page<EntityPO> findAll(String id, Integer pageNumber, Integer pageSize, String sortCode) {
        Sort sort = Sort.by(sortCode);
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, sort);
        List<EntityPO> poList = findListById(id);
        List<EntityPO> poPage = CommonUtils.page(poList, pageSize, pageNumber);
        return new PageImpl<>(poPage, pageable, poList.size());
    }

    @Override
    public List<EntityPO> findListById(String id) {
        QEntityPO qEntityPO = QEntityPO.entityPO;
        QEntityNamePO qEntityNamePO = QEntityNamePO.entityNamePO;
        return queryFactory.selectFrom(qEntityPO)
                .innerJoin(qEntityNamePO)
                .on(qEntityPO.pid.eq(qEntityNamePO.id))
                .where(qEntityNamePO.id.eq(id))
                .orderBy(qEntityPO.sortCode.asc()).fetch();
    }

    @Override
    public EntityPO findOneById(String id) {
        if (entityDao.findById(id).isPresent()) {
            return entityDao.findById(id).get();
        }
        return null;
    }

}
