package com.example.cyjpagemenu.dao;

import com.example.cyjpagemenu.entity.DataFormItemPO;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2021-02-02
 */
public interface DataFormItemDao extends JpaRepository<DataFormItemPO, String> {
    /**
     * 删除实体
     *
     * @param pid 实体pid
     */
    void deleteByPid(String pid);
}
