package com.example.sso.controller;

import com.example.sso.entity.PageFunctionPO;
import com.example.sso.entity.ResultVO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2020-08-09
 */
public interface PageFunctionController {

    /**
     * 查询所有对象
     *
     * @param pageNumber 页码
     * @param pageSize   条目
     * @param sortCode   排序列
     * @return 返回结果
     */
    @PostMapping(value = "pageFunctionPage")
    ResultVO pageFunctionFindAll(@RequestParam("pageNumber") Integer pageNumber,
                                 @RequestParam("pageSize") Integer pageSize,
                                 @RequestParam("sortCode") String sortCode);

    /**
     * 保存对象
     *
     * @param po 对象
     * @return 返回结果
     */
    @PostMapping(value = "pageFunctionSave")
    ResultVO savePageFunction(@RequestBody PageFunctionPO po);

    /**
     * 删除对象
     *
     * @param id 对象ID
     */
    @PostMapping(value = "pageFunctionDelete")
    void pageFunctionDeleteOne(@RequestParam("id") String id);
}
