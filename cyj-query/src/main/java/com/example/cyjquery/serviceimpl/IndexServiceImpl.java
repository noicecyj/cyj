package com.example.cyjquery.serviceimpl;

import com.example.cyjquery.service.IndexService;
import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Map;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2020-09-13
 */
@Service
public class IndexServiceImpl implements IndexService {

    @PersistenceContext
    EntityManager em;

    @Override
    public List<Map<String, Object>> queryBySql(String sql) {
        Query query = em.createNativeQuery(sql);
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> list = query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
        return list;
    }

    @Override
    public void excuteSql(String sql) {
        Query query = em.createNativeQuery(sql);
        query.executeUpdate();
    }

    @Override
    public List<Map<String, Object>> findAllSql(String tableName) {
        String sql = "select * from " + tableName + " where 1=1";
        return queryBySql(sql);
    }

    @Override
    public List<Map<String, Object>> findSqlById(String tableName, String id) {
        String sql = String.format("select * from %s where id = ?", tableName);
        Query query = em.createNativeQuery(sql);
        query.setParameter(1, id);
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> list = query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
        return list;
    }
}
