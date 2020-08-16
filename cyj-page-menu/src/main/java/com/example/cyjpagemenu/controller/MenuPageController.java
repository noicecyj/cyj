package com.example.cyjpagemenu.controller;

import com.example.cyjpagemenu.entity.MenuPagePO;
import com.example.cyjpagemenu.entity.ResultVO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2020/1/21 14:46
 */
@RequestMapping(value = "pageMenuApi")
public interface MenuPageController {
    /**
     * 保存菜单
     *
     * @param po 菜单
     * @return 返回结果
     */
    @PostMapping(value = "saveMenuPage")
    ResultVO saveMenuPage(@RequestBody MenuPagePO po);

    /**
     * 生成菜单配置
     *
     * @return 返回结果
     */
    @PostMapping(value = "asideMenuConfig")
    ResultVO asideMenuConfig();

    /**
     * 查询所有页面
     *
     * @return 返回结果
     */
    @PostMapping(value = "findAll")
    ResultVO findAll();

    /**
     * 删除页面
     *
     * @param id 菜单ID
     */
    @PostMapping(value = "menuPageDelete")
    void menuPageDelete(@RequestParam("id") String id);

    /**
     * 生成路由文件
     *
     * @param routePath 路由路径
     * @return 返回结果
     */
    @PostMapping(value = "createRouteFile")
    ResultVO createRouteFile(@RequestParam("routePath") String routePath);

    /**
     * 根据目录名称查询字典
     *
     * @param name 目录名称
     * @return 返回结果
     */
    @PostMapping(value = "findCatalogByName")
    ResultVO findCatalogByName(@RequestParam("name") String name);

    /**
     * 根据目录值查询字典
     *
     * @param value 目录值
     * @return 返回结果
     */
    @PostMapping(value = "findCatalogByValue")
    ResultVO findCatalogByValue(@RequestParam("value") String value);
}
