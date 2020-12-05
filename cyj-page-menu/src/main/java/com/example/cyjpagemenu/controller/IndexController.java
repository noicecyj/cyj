package com.example.cyjpagemenu.controller;

import com.example.cyjcommon.utils.CommonUtils;
import com.example.cyjcommon.utils.ResultVO;
import com.example.cyjpagemenu.entity.DataItemPO;
import com.example.cyjpagemenu.entity.DataTableItemPO;
import com.example.cyjpagemenu.entity.DataTablePO;
import com.example.cyjpagemenu.entity.dto.DictionaryDTO;
import com.example.cyjpagemenu.service.DictionaryApiService;
import com.example.cyjpagemenu.serviceimpl.IndexServiceImpl;
import com.example.cyjpagemenu.serviceimpl.MenuPageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2020/1/21 14:46
 */
@RestController
@RequestMapping(value = "pageMenuApi")
public class IndexController {

    private IndexServiceImpl indexService;

    private MenuPageServiceImpl menuPageService;

    private DictionaryApiService dictionaryApiService;

    @Autowired
    public void setIndexService(IndexServiceImpl indexService) {
        this.indexService = indexService;
    }

    @Autowired
    public void setMenuPageService(MenuPageServiceImpl menuPageService) {
        this.menuPageService = menuPageService;
    }

    @Autowired
    public void setDictionaryApiService(DictionaryApiService dictionaryApiService) {
        this.dictionaryApiService = dictionaryApiService;
    }

    /**
     * 生成菜单配置
     *
     * @return 返回结果
     */
    @PostMapping(value = "asideMenuConfig")
    public ResultVO asideMenuConfig() {
        return ResultVO.success(menuPageService.findAll());
    }

    /**
     * 生成路由文件
     *
     * @return 返回结果
     */
    @PostMapping(value = "createRouteFile")
    public ResultVO createRouteFile() {
        List<DictionaryDTO> pos = dictionaryApiService.findCatalogByValue("FILE_PATH");
        HashMap<String, DictionaryDTO> mapPo = CommonUtils.listToMap(pos, "dictionaryName");
        try {
            menuPageService.createRouteFile(mapPo.get("routePath").getDictionaryValue());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResultVO.success();
    }

    /**
     * 根据目录名称查询字典
     *
     * @param name 目录名称
     * @return 返回结果
     */
    @PostMapping(value = "findCatalogByName")
    public ResultVO findCatalogByName(@RequestParam("name") String name) {
        List<DictionaryDTO> pos = dictionaryApiService.findCatalogByName(name);
        return ResultVO.success(pos);
    }

    /**
     * 根据目录值查询字典
     *
     * @param value 目录值
     * @return 返回结果
     */
    @PostMapping(value = "findCatalogByValue")
    public ResultVO findCatalogByValue(@RequestParam("value") String value) {
        List<DictionaryDTO> pos = dictionaryApiService.findCatalogByValue(value);
        return ResultVO.success(pos);
    }

    /**
     * 根据名称获取表单
     *
     * @param name 名称
     * @return 返回结果
     */
    @PostMapping(value = "findDataFormByName")
    public ResultVO findDataFormByName(@RequestParam("name") String name) {
        List<DataItemPO> pos = indexService.findDataFormByName(name);
        return ResultVO.success(pos);
    }

    /**
     * 根据名称获取表格
     *
     * @param name 名称
     * @return 返回结果
     */
    @PostMapping(value = "findDataTableByName")
    public ResultVO findDataTableByName(@RequestParam("name") String name) {
        DataTablePO po = indexService.findOneByName(name);
        List<DataTableItemPO> pos = indexService.findDataTableByName(name);
        return ResultVO.success();
    }
}
