package com.example.cyjentitycreater.controller;


import com.example.cyjentitycreater.entity.CreateVO;
import com.example.cyjentitycreater.entity.EntityPO;
import com.example.cyjentitycreater.entity.ResultVO;
import com.example.cyjentitycreater.service.EntityFactory;
import com.example.cyjentitycreater.serviceimpl.IndexServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @Autowired
    public void setEntityFactory(EntityFactory entityFactory) {
        this.entityFactory = entityFactory;
    }

    @Autowired
    public void setIndexService(IndexServiceImpl indexService) {
        this.indexService = indexService;
    }

    /**
     * 查询所有对象
     *
     * @param id id
     * @param pageNumber 页码
     * @param pageSize   条目
     * @param sortCode   排序列
     * @return 返回结果
     */
    @RequestMapping(value = "entityPage")
    ResultVO entityFindAll(@RequestParam("id") String id,
                           @RequestParam("pageNumber") Integer pageNumber,
                           @RequestParam("pageSize") Integer pageSize,
                           @RequestParam("sortCode") String sortCode){
        return ResultVO.success(indexService.findAll(id, pageNumber, pageSize, sortCode));
    }

    /**
     * 生成实体类文件
     *
     * @param createVO 实体参数
     * @return 返回值
     */
    @RequestMapping(value = "createEntity")
    public ResultVO createEntity(@RequestBody CreateVO createVO) {
        List<EntityPO> poList = indexService.findEntityById(createVO.getId());
        createVO.setPoList(poList);
        entityFactory.createEntity(createVO);
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