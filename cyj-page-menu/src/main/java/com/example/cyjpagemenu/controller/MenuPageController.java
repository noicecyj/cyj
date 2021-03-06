package com.example.cyjpagemenu.controller;

import com.example.cyjcommon.utils.ResultVO;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2021-02-04
 */
public interface MenuPageController {

    /**
     * 查询所有对象
     *
     * @param pageNumber 页码
     * @param pageSize   条目
     * @param sortCode   排序列
     * @return 返回结果
     */
    @PostMapping(value = "menuPagePage")
    ResultVO menuPageFindAll(@RequestParam("pageNumber") Integer pageNumber,
                         @RequestParam("pageSize") Integer pageSize,
                         @RequestParam("sortCode") String sortCode);

    /**
     * 保存对象
     *
     * @param vo 对象
     * @return 返回结果
     */
    @PostMapping(value = "menuPageSave")
    ResultVO menuPageSave(@RequestBody Map<String, Object> vo);

    /**
     * 删除对象
     *
     * @param id 对象ID
     */
    @PostMapping(value = "menuPageDelete")
    void menuPageDelete(@RequestParam("id") String id);

    /**
     * 根据ID查询
     *
     * @param id 对象ID
     * @return 返回结果
     */
    @PostMapping(value = "findMenuPageById")
    ResultVO findMenuPageById(@RequestParam("id") String id);

    /**
     * 查询所有页面
     *
     * @return 返回结果
     */
    @PostMapping(value = "findAll")
    ResultVO findAll();
}
