package com.example.cyjentitycreater.dao;

import com.example.cyjentitycreater.entity.EntityPO;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2020/1/21 14:46
 */
public interface EntityDao extends JpaRepository<EntityPO, String> {
}
