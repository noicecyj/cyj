package com.example.cyjpagemenu.dao;

import com.example.cyjpagemenu.entity.MenuPagePO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2020/1/21 14:46
 */
public interface MenuPageDao extends JpaRepository<MenuPagePO,String> {

    /**
     * 查找路由组件
     *
     * @return 路由组件
     */
    List<MenuPagePO> findAllByComponentNameIsNotNull();
}
