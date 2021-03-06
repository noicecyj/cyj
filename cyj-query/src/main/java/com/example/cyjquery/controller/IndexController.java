package com.example.cyjquery.controller;

import com.example.cyjcommon.utils.ResultVO;
import com.example.cyjquery.serviceimpl.IndexServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2020-08-30
 */
@CrossOrigin
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

    /**
     * 通过主键sql查询
     *
     * @param tableName 表名
     * @param id 主键
     * @return 返回值
     */
    @PostMapping(value = "doFindSqlById")
    public ResultVO doFindSqlById(@RequestParam("tableName") String tableName, @RequestParam("id") String id) {
        return ResultVO.success(indexService.findSqlById(tableName, id));
    }

}
