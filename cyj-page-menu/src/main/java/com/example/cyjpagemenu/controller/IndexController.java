package com.example.cyjpagemenu.controller;

import com.example.cyjpagemenu.entity.DictionaryPO;
import com.example.cyjpagemenu.entity.MenuPagePO;
import com.example.cyjpagemenu.entity.ResultVO;
import com.example.cyjpagemenu.service.DictionaryApiService;
import com.example.cyjpagemenu.serviceimpl.MenuPageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2020/1/21 14:46
 */
@RestController
public class IndexController implements MenuPageController {

    private MenuPageServiceImpl menuPageService;

    private DictionaryApiService dictionaryApiService;

    @Autowired
    public void setMenuPageService(MenuPageServiceImpl menuPageService) {
        this.menuPageService = menuPageService;
    }

    @Autowired
    public void setDictionaryApiService(DictionaryApiService dictionaryApiService) {
        this.dictionaryApiService = dictionaryApiService;
    }

    @Override
    public ResultVO saveMenuPage(@RequestBody MenuPagePO po) {
        if (po.getId() == null) {
            return ResultVO.success(menuPageService.addOne(po));
        }
        return ResultVO.success(menuPageService.updateOne(po));
    }

    @Override
    public ResultVO asideMenuConfig() {
        return ResultVO.success(menuPageService.findAll());
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

    @Override
    public ResultVO createRouteFile() {
        List<DictionaryPO> pos = dictionaryApiService.findCatalogByValue("ROUTH_PATH");
        try {
            menuPageService.createRouteFile(pos.get(0).getDictionaryValue());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResultVO.success();
    }

    @Override
    public ResultVO findCatalogByName(@RequestParam("name") String name) {
        List<DictionaryPO> pos = dictionaryApiService.findCatalogByName(name);
        return ResultVO.success(pos);
    }

    @Override
    public ResultVO findCatalogByValue(@RequestParam("value") String value) {
        List<DictionaryPO> pos = dictionaryApiService.findCatalogByValue(value);
        return ResultVO.success(pos);
    }
}
