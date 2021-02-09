package com.example.cyjpagemenu.controller;

import com.alibaba.fastjson.JSONArray;
import com.example.cyjcommon.utils.ResultVO;
import com.example.cyjpagemenu.api.DictionaryApiService;
import com.example.cyjpagemenu.entity.DataFormItemPO;
import com.example.cyjpagemenu.entity.DataTableItemPO;
import com.example.cyjpagemenu.entity.dto.DictionaryDTO;
import com.example.cyjpagemenu.entity.vo.DataSourceVO;
import com.example.cyjpagemenu.serviceimpl.IndexServiceImpl;
import com.example.cyjpagemenu.serviceimpl.MenuPageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        List<DataFormItemPO> pos = indexService.findDataFormByName(name);
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
        List<DataTableItemPO> pos = indexService.findDataTableByName(name);
        return ResultVO.success(pos);
    }

    /**
     * 自动生成基础表单和基础表格
     *
     * @param name 对象
     * @return 返回结果
     */
    @PostMapping(value = "formAndTableGenerate")
    public ResultVO formAndTableGenerate(@RequestParam("name") String name, @RequestBody JSONArray jsonArray) {
        indexService.formAndTableGenerate(name, jsonArray);
        return ResultVO.success();
    }

    /**
     * 处理数据
     *
     * @param dataSourceVO 数据
     * @return 返回结果
     */
    @PostMapping(value = "transformData")
    public ResultVO transformData(@RequestBody DataSourceVO dataSourceVO) {
        Object dataSource = indexService.transformData(dataSourceVO);
        return ResultVO.success(dataSource);
    }
}
