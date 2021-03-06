package com.example.cyjpagemenu.controller;

import com.example.cyjcommon.utils.ResultVO;
import com.example.cyjcommon.utils.VoPoConverter;
import com.example.cyjpagemenu.entity.*;
import com.example.cyjpagemenu.serviceimpl.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2021-02-04
 */
@CrossOrigin
@RestController
@RequestMapping(value = "pageMenuApi")
public class MenuPageControllerImpl implements MenuPageController {

    private MenuPageServiceImpl menuPageService;

    @Autowired
    public void setMenuPageService(MenuPageServiceImpl menuPageService) {
        this.menuPageService = menuPageService;
    }

    @Override
    public ResultVO menuPageFindAll(Integer pageNumber, Integer pageSize, String sortCode) {
        return ResultVO.success(menuPageService.findAll(pageNumber - 1, pageSize, sortCode));
    }

    @Override
    public ResultVO menuPageSave(Map<String, Object> vo) {
        MenuPagePO po = new MenuPagePO();
        VoPoConverter.copyProperties(vo, po);
        if (po.getId() == null) {
            return ResultVO.success(menuPageService.addOne(po));
        }
        return ResultVO.success(menuPageService.updateOne(po));
    }

    @Override
    public void menuPageDelete(String id) {
        menuPageService.deleteOne(id);
    }

    @Override
    public ResultVO findMenuPageById(String id) {
        return ResultVO.success(menuPageService.findOneById(id));
    }

    @Override
    public ResultVO findAll() {
        long totalElements = menuPageService.count();
        Object[] data = {menuPageService.findAll(), totalElements};
        return ResultVO.success(data);
    }
}
