package com.example.cyjpagemenu.dao;

import com.example.cyjpagemenu.entity.MenuPagePO;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2020/1/21 14:46
 */
public interface MenuPageDao extends JpaRepository<MenuPagePO,String> {

    /**
     * 查找所有菜单
     *
     * @param id ID
     * @return 目录列表分页
     */
    MenuPagePO getAllById(String id);
}
