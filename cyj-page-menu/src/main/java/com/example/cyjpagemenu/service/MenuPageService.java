package com.example.cyjpagemenu.service;

import com.example.cyjpagemenu.entity.MenuPagePO;

import java.io.IOException;

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

    /**
     * 查找所有菜单
     *
     * @return 目录列表分页
     */
    MenuPagePO findAll();

    /**
     * 所有菜单数量
     *
     * @return 目录列表分页
     */
    long count();

    /**
     * 生成路由文件
     *
     * @param routePath 生成路径
     */
    void createRouteFile(String routePath) throws IOException;
}
