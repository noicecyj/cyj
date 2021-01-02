package com.example.cyjentitycreater.controller;

import com.example.cyjcommon.utils.CommonUtils;
import com.example.cyjcommon.utils.ResultVO;
import com.example.cyjentitycreater.api.DictionaryApiService;
import com.example.cyjentitycreater.entity.AppServicePO;
import com.example.cyjentitycreater.entity.CreateVO;
import com.example.cyjentitycreater.entity.EntityNamePO;
import com.example.cyjentitycreater.entity.dto.DictionaryDTO;
import com.example.cyjentitycreater.service.EntityFactory;
import com.example.cyjentitycreater.serviceimpl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
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
@RequestMapping(value = "entityCreateApi")
public class IndexController {

    private EntityFactory entityFactory;
    private IndexServiceImpl indexService;
    private EntityNameServiceImpl entityNameService;
    private ComponentServiceImpl componentService;
    private DictionaryApiService dictionaryApiService;
    private AppServiceServiceImpl appServiceService;
    private AppServiceImpl appService;

    @Autowired
    public void setEntityFactory(EntityFactory entityFactory) {
        this.entityFactory = entityFactory;
    }

    @Autowired
    public void setIndexService(IndexServiceImpl indexService) {
        this.indexService = indexService;
    }

    @Autowired
    public void setEntityNameService(EntityNameServiceImpl entityNameService) {
        this.entityNameService = entityNameService;
    }

    @Autowired
    public void setComponentService(ComponentServiceImpl componentService) {
        this.componentService = componentService;
    }

    @Autowired
    public void setDictionaryApiService(DictionaryApiService dictionaryApiService) {
        this.dictionaryApiService = dictionaryApiService;
    }

    @Autowired
    public void setAppServiceService(AppServiceServiceImpl appServiceService) {
        this.appServiceService = appServiceService;
    }

    @Autowired
    public void setAppService(AppServiceImpl appService) {
        this.appService = appService;
    }

    /**
     * 生成实体类文件
     *
     * @param createVO 实体参数
     * @return 返回值
     */
    @RequestMapping(value = "createEntity")
    public ResultVO createEntity(@RequestBody CreateVO createVO) {
        EntityNamePO po = entityNameService.findOneById(createVO.getId());
        entityFactory.createEntity(po, createVO.getChoose());
        return ResultVO.success();
    }

    /**
     * 生成组件文件
     *
     * @param createVO 实体参数
     * @return 返回值
     */
    @RequestMapping(value = "createComponentFile")
    public ResultVO createComponentFile(@RequestBody CreateVO createVO) {
        EntityNamePO po = entityNameService.findOneById(createVO.getId());
        List<DictionaryDTO> pos = dictionaryApiService.findCatalogByValue("FILE_PATH");
        HashMap<String, DictionaryDTO> mapPo = CommonUtils.listToMap(pos, "dictionaryName");
        try {
            componentService.createComponentFile(mapPo.get("componentPath").getDictionaryValue(), po, createVO.getChoose());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResultVO.success();
    }

    /**
     * 生成组件文件
     *
     * @param createVO 实体参数
     * @return 返回值
     */
    @RequestMapping(value = "createAppFile")
    public ResultVO createAppFile(@RequestBody CreateVO createVO) {
        AppServicePO po = appServiceService.findOneById(createVO.getId());
        try {
            appService.createJavaFile(po);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResultVO.success();
    }

    /**
     * 上移属性
     *
     * @param id id
     */
    @RequestMapping(value = "upEntity")
    public void upEntity(@RequestParam("id") String id) {
        indexService.upEntity(id);
    }

    /**
     * 下移属性
     *
     * @param id id
     */
    @RequestMapping(value = "downEntity")
    public void downEntity(@RequestParam("id") String id) {
        indexService.downEntity(id);
    }
}