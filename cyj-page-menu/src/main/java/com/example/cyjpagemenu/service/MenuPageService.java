package com.example.cyjpagemenu.service;

import com.example.cyjpagemenu.entity.MenuPagePO;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2020/1/21 14:46
 */
public interface MenuPageService {

    /**
     * 添加菜单
     *
     * @param po 菜单
     * @return 菜单
     */
    MenuPagePO addOne(MenuPagePO po);
    /**
     * 删除菜单
     *
     * @param id 菜单id
     */
    void deleteOne(String id);
    /**
     * 更新菜单
     *
     * @param po 菜单
     * @return 菜单
     */
    MenuPagePO updateOne(MenuPagePO po);
}
