package com.example.cyjpagemenu.controller;

import com.example.cyjpagemenu.entity.MenuPagePO;
import com.example.cyjpagemenu.entity.ResultVO;
import com.example.cyjpagemenu.serviceimpl.MenuPageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2020-11-12
 */
@RestController
@RequestMapping(value = "pageMenuApi")
public class MenuPageControllerImpl implements MenuPageController {

    private MenuPageServiceImpl menuPageService;

    @Autowired
    public void setMenuPageService(MenuPageServiceImpl menuPageService) {
        this.menuPageService = menuPageService;
    }

    @Override
    public ResultVO saveMenuPage(@RequestBody MenuPagePO po) {
        if (po.getId() == null) {
            return ResultVO.success(menuPageService.addOne(po));
        }
        return ResultVO.success(menuPageService.updateOne(po));
    }

    @Override
    public ResultVO findAll() {
        long totalElements = menuPageService.count();
        Object[] data = {menuPageService.findAll(), totalElements};
        return ResultVO.success(data);
    }

    @Override
    public void menuPageDelete(@RequestParam("id") String id) {
        menuPageService.deleteOne(id);
    }

}
