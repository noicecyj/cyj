package com.example.cyjpagemenu.controller;

import com.example.cyjcommon.utils.ResultVO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2021-02-02
 */
public interface DataFormController {

    /**
     * 查询所有对象
     *
     * @param pageNumber 页码
     * @param pageSize   条目
     * @param sortCode   排序列
     * @return 返回结果
     */
    @PostMapping(value = "dataFormPage")
    ResultVO dataFormFindAll(@RequestParam("pageNumber") Integer pageNumber,
                             @RequestParam("pageSize") Integer pageSize,
                             @RequestParam("sortCode") String sortCode);

    /**
     * 保存对象
     *
     * @param vo 对象
     * @return 返回结果
     */
    @PostMapping(value = "dataFormSave")
    ResultVO dataFormSave(@RequestBody Map<String, Object> vo);

    /**
     * 删除对象
     *
     * @param id 对象ID
     */
    @PostMapping(value = "dataFormDelete")
    void dataFormDelete(@RequestParam("id") String id);

    /**
     * 根据ID查询
     *
     * @param id 对象ID
     * @return 返回结果
     */
    @PostMapping(value = "findDataFormById")
    ResultVO findDataFormById(@RequestParam("id") String id);

}
