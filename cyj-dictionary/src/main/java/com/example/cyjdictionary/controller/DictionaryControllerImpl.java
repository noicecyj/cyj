package com.example.cyjdictionary.controller;

import com.example.cyjdictionary.entity.DictionaryPO;
import com.example.cyjdictionary.entity.ResultVO;
import com.example.cyjdictionary.serviceimpl.DictionaryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2020-09-13
 */
@RestController
@RequestMapping(value = "dictionaryApi")
public class DictionaryControllerImpl implements DictionaryController {

    private DictionaryServiceImpl dictionaryService;

    @Autowired
    public void setDictionaryService(DictionaryServiceImpl dictionaryService) {
        this.dictionaryService = dictionaryService;
    }

    @Override
    public ResultVO dictionaryFindAll(String id, Integer pageNumber, Integer pageSize, String sortCode) {
        return ResultVO.success(dictionaryService.findAll(id, pageNumber, pageSize, sortCode));
    }

    @Override
    public ResultVO dictionarySave(DictionaryPO po) {
        if (po.getId() == null) {
            return ResultVO.success(dictionaryService.addOne(po));
        }
        return ResultVO.success(dictionaryService.updateOne(po));
    }

    @Override
    public void dictionaryDelete(String id) {
        dictionaryService.deleteOne(id);
    }

}
