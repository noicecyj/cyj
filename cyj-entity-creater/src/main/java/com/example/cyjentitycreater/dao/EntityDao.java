package com.example.cyjentitycreater.dao;

import com.example.cyjentitycreater.entity.EntityPO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2020/1/21 14:46
 */
public interface EntityDao extends JpaRepository<EntityPO, String> {

    /**
     * 查询上移属性
     *
     * @param pid 实体id
     * @param sortCode 排序代码
     * @return 属性列
     */
    List<EntityPO> findByPidAndSortCodeBeforeOrderBySortCodeDesc(String pid, String sortCode);

    /**
     * 查询下移属性
     *
     * @param pid 实体id
     * @param sortCode 排序代码
     * @return 属性列
     */
    List<EntityPO> findByPidAndSortCodeAfterOrderBySortCode(String pid, String sortCode);
}
