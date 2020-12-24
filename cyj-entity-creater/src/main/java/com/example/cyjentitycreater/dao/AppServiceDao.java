package com.example.cyjentitycreater.dao;

import com.example.cyjentitycreater.entity.AppServicePO;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2020-12-22
 */
public interface AppServiceDao extends JpaRepository<AppServicePO, String> {
}
