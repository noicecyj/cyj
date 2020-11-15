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

}
