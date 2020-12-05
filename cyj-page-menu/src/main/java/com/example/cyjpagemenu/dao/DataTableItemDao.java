package com.example.cyjpagemenu.dao;

import com.example.cyjpagemenu.entity.DataTableItemPO;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2020-12-05
 */
public interface DataTableItemDao extends JpaRepository<DataTableItemPO, String> {
    /**
     * 删除实体
     *
     * @param pid 实体pid
     */
    void deleteByPid(String pid);
}
