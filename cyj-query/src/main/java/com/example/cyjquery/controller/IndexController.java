package com.example.cyjquery.controller;

import com.example.cyjcommon.utils.ResultVO;
import com.example.cyjquery.serviceimpl.IndexServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2020-08-30
 */
@RestController
@RequestMapping(value = "sqlApi")
public class IndexController {

    private IndexServiceImpl indexService;

    @Autowired
    public void setIndexService(IndexServiceImpl indexService) {
        this.indexService = indexService;
    }

    /**
     * 通过sql查询
     *
     * @param sqlStr  sql语句
     * @param sqlType sql类型
     * @return 返回值
     */
    @PostMapping(value = "doSql")
    public ResultVO doSql(@RequestParam("sqlStr") String sqlStr,
                          @RequestParam("sqlType") String sqlType) {
        String query = "查询";
        try {
            if (query.equals(sqlType)) {
                return ResultVO.success(indexService.queryBySql(sqlStr));
            } else {
                indexService.excuteSql(sqlStr);
                return ResultVO.success();
            }
        } catch (Exception e) {
            return ResultVO.failure(e.getCause().getCause());
        }
    }

    /**
     * 通过sql查询
     *
     * @param tableName 表名
     * @return 返回值
     */
    @PostMapping(value = "doFindAllSql")
    public ResultVO doFindAllSql(@RequestParam("tableName") String tableName) {
        return ResultVO.success(indexService.findAllSql(tableName));
    }

}
