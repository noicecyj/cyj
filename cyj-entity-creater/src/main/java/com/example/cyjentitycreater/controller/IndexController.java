package com.example.cyjentitycreater.controller;

import com.example.cyjcommon.utils.ResultVO;
import com.example.cyjcommon.utils.CommonUtils;
import com.example.cyjentitycreater.entity.CreateVO;
import com.example.cyjentitycreater.entity.DictionaryDTO;
import com.example.cyjentitycreater.entity.EntityNamePO;
import com.example.cyjentitycreater.service.DictionaryApiService;
import com.example.cyjentitycreater.service.EntityFactory;
import com.example.cyjentitycreater.serviceimpl.ComponentServiceImpl;
import com.example.cyjentitycreater.serviceimpl.EntityNameServiceImpl;
import com.example.cyjentitycreater.serviceimpl.IndexServiceImpl;
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

    /**
     * 生成实体类文件
     *
     * @param createVO 实体参数
     * @return 返回值
     */
    @RequestMapping(value = "createEntity")
    public ResultVO createEntity(@RequestBody CreateVO createVO) {
        EntityNamePO po = entityNameService.findOneById(createVO.getId());
        entityFactory.createEntity(po,createVO.getChoose());
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
            componentService.createComponentFile(mapPo.get("componentPath").getDictionaryValue(), po);
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

    /**
     * 选择所有实体
     *
     * @return 实体列表
     */
    @RequestMapping(value = "selectEntityFindAll")
    public ResultVO selectEntityFindAll() {
        return ResultVO.success(indexService.findAll());
    }
}