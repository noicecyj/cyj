package com.example.cyjpagemenu.controller;

import com.example.cyjpagemenu.entity.MenuPagePO;
import com.example.cyjpagemenu.entity.ResultVO;
import com.example.cyjpagemenu.serviceimpl.MenuPageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2020/1/21 14:46
 */
@RestController
@RequestMapping(value = "pageMenuApi")
public class IndexController {

    private MenuPageServiceImpl menuPageService;

    @Autowired
    public void setMenuPageService(MenuPageServiceImpl menuPageService) {
        this.menuPageService = menuPageService;
    }

    @PostMapping(value = "saveMenuPage")
    public ResultVO saveMenuPage(@RequestBody MenuPagePO po) {
        if (po.getId() == null) {
            return ResultVO.success(menuPageService.addOne(po));
        }
        return ResultVO.success(menuPageService.updateOne(po));
    }

    @PostMapping(value = "asideMenuConfig")
    public ResultVO asideMenuConfig() {
        return ResultVO.success(menuPageService.findAll());
    }

    @PostMapping(value = "findAll")
    public ResultVO findAll() {
        long totalElements = menuPageService.count();
        Object[] data = {menuPageService.findAll(), totalElements};
        return ResultVO.success(data);
    }

    @PostMapping(value = "menuPageDelete")
    public void menuPageDelete(@RequestParam("id") String id) {
        menuPageService.deleteOne(id);
    }

    @PostMapping(value = "createRouteFile")
    public ResultVO createRouteFile(@RequestParam("routePath") String routePath) {
        try {
            menuPageService.createRouteFile(routePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResultVO.success();
    }
}
