package com.example.cyjentitycreater.controller;


import com.example.cyjentitycreater.entity.CreateVO;
import com.example.cyjentitycreater.entity.EntityNamePO;
import com.example.cyjentitycreater.entity.EntityPO;
import com.example.cyjentitycreater.entity.ResultVO;
import com.example.cyjentitycreater.service.EntityFactory;
import com.example.cyjentitycreater.serviceimpl.EntityNameServiceImpl;
import com.example.cyjentitycreater.serviceimpl.EntityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2020/1/21 14:46
 */
@RestController
@RequestMapping(value = "entityCreateApi")
public class IndexController implements EntityController, EntityNameController {

    private EntityFactory entityFactory;
    private EntityServiceImpl entityService;
    private EntityNameServiceImpl entityNameService;

    @Autowired
    public void setEntityFactory(EntityFactory entityFactory) {
        this.entityFactory = entityFactory;
    }

    @Autowired
    public void setEntityService(EntityServiceImpl entityService) {
        this.entityService = entityService;
    }

    @Autowired
    public void setEntityNameService(EntityNameServiceImpl entityNameService) {
        this.entityNameService = entityNameService;
    }

    @Override
    public ResultVO entityFindAll(@RequestParam("pageNumber") Integer pageNumber,
                                  @RequestParam("pageSize") Integer pageSize,
                                  @RequestParam("sortCode") String sortCode) {
        Page<EntityPO> pos = entityService.findAll(pageNumber - 1, pageSize, sortCode);
        return ResultVO.success(pos);
    }

    @Override
    public ResultVO entityNameFindAll(@RequestParam("pageNumber") Integer pageNumber,
                                      @RequestParam("pageSize") Integer pageSize,
                                      @RequestParam("sortCode") String sortCode) {
        Page<EntityNamePO> pos = entityNameService.findAll(pageNumber - 1, pageSize, sortCode);
        return ResultVO.success(pos);
    }

    @Override
    public ResultVO saveEntity(@RequestBody EntityPO po) {
        if (po.getId() == null) {
            return ResultVO.success(entityService.addOne(po));
        }
        return ResultVO.success(entityService.updateOne(po));
    }

    @Override
    public ResultVO saveEntityName(@RequestBody EntityNamePO po) {
        if (po.getId() == null) {
            return ResultVO.success(entityNameService.addOne(po));
        }
        return ResultVO.success(entityNameService.updateOne(po));
    }

    @Override
    public void entityDeleteOne(@RequestParam("id") String id) {
        entityService.deleteOne(id);
    }

    @Override
    public void entityNameDeleteOne(@RequestParam("id") String id) {
        entityNameService.deleteOne(id);
    }

    /**
     * 生成实体代码
     * @param createVO 实体参数
     * @return 返回值
     */
    @RequestMapping(value = "entity")
    public ResultVO entity(@RequestBody CreateVO createVO) {
        return ResultVO.success(entityFactory.entity(createVO));
    }

    /**
     * 生成实体类文件
     * @param createVO 实体参数
     * @return 返回值
     */
    @RequestMapping(value = "createEntity")
    public ResultVO createEntity(@RequestBody CreateVO createVO) {
        return ResultVO.success(entityFactory.createEntity(createVO));
    }
}