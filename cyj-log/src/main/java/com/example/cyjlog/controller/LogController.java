package com.example.cyjlog.controller;

import com.example.cyjlog.entity.ResultVO;
import com.example.cyjlog.entity.ServerPO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2020/1/21 14:46
 */
public interface LogController {

    /**
     * 查询所有对象
     *
     * @param pageNumber 页码
     * @param pageSize   条目
     * @param sortCode   排序列
     * @return 返回结果
     */
    @PostMapping(value = "serverPage")
    ResultVO findAll(@RequestParam("pageNumber") Integer pageNumber,
                     @RequestParam("pageSize") Integer pageSize,
                     @RequestParam("sortCode") String sortCode);

    /**
     * 保存对象
     *
     * @param po 对象
     * @return 返回结果
     */
    @PostMapping(value = "saveServer")
    ResultVO saveServer(@RequestBody ServerPO po);

    /**
     * 删除对象
     *
     * @param id 对象ID
     */
    @PostMapping(value = "serverDelete")
    void serverDelete(@RequestParam("id") String id);
}
