package com.example.cyjdictionary.controller;

import com.example.cyjcommon.utils.VoPoConverter;
import com.example.cyjdictionary.entity.CatalogPO;
import com.example.cyjdictionary.entity.ResultVO;
import com.example.cyjdictionary.serviceimpl.CatalogServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2020-11-09
 */
@RestController
@RequestMapping(value = "dictionaryApi")
public class CatalogControllerImpl implements CatalogController {

    private CatalogServiceImpl catalogService;

    @Autowired
    public void setCatalogService(CatalogServiceImpl catalogService) {
        this.catalogService = catalogService;
    }

    @Override
    public ResultVO catalogFindAll(Integer pageNumber, Integer pageSize, String sortCode) {
        return ResultVO.success(catalogService.findAll(pageNumber - 1, pageSize, sortCode));
    }

    @Override
    public ResultVO catalogSave(Map<String, Object> vo) {
        CatalogPO po = new CatalogPO();
        VoPoConverter.copyProperties(vo, po);
        if (po.getId() == null) {
            return ResultVO.success(catalogService.addOne(po));
        }
        return ResultVO.success(catalogService.updateOne(po));
    }

    @Override
    public void catalogDelete(String id) {
        catalogService.deleteOne(id);
    }

}
