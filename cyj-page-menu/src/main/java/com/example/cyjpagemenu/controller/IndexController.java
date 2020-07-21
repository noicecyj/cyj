package com.example.cyjpagemenu.controller;

import com.example.cyjpagemenu.entity.MenuPagePO;
import com.example.cyjpagemenu.entity.ResultVO;
import com.example.cyjpagemenu.serviceimpl.MenuPageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping(value = "MenuPageDelete")
    public void MenuPageDeleteOne(@RequestParam("id") String id) {
        menuPageService.deleteOne(id);
    }

    @PostMapping(value = "findAll")
    public ResultVO findAll(@RequestParam("id") String id) {
        return ResultVO.success(menuPageService.findAll(id));
    }
}
