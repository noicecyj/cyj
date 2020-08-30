package com.example.cyjquery.serviceimpl;

import com.example.cyjquery.dao.SqlDao;
import com.example.cyjquery.entity.SqlPO;
import com.example.cyjquery.service.SqlService;
import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Map;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2020-08-30
 */
@Service
public class SqlServiceImpl extends BaseService implements SqlService {

    @PersistenceContext
    EntityManager em;

    private SqlDao sqlDao;

    @Autowired
    public void setSqlDao(SqlDao sqlDao) {
        this.sqlDao = sqlDao;
    }

    @Override
    public SqlPO addOne(SqlPO po) {
        return sqlDao.save(po);
    }

    @Override
    public void deleteOne(String id) {
        sqlDao.deleteById(id);
    }

    @Override
    public SqlPO updateOne(SqlPO po) {
        return sqlDao.saveAndFlush(po);
    }

    @Override
    public Page<SqlPO> findAll(Integer pageNumber, Integer pageSize, String sortCode) {
        Sort sort = Sort.by(sortCode);
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        return sqlDao.findAll(pageable);
    }

    @Override
    public List<Map<String, Object>> queryBySql(String sql) {
        Query query = em.createNativeQuery(sql);
        @SuppressWarnings("unchecked")
        List<Map<String,Object>> list = query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
        return list;
    }

    @Override
    public void excuteSql(String sql) {
        Query query = em.createNativeQuery(sql);
        query.executeUpdate();
    }

}
