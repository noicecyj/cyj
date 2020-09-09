package com.example.cyjquery.controller;

import com.example.cyjquery.entity.ResultVO;
import com.example.cyjquery.entity.SqlPO;
import com.example.cyjquery.serviceimpl.SqlServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2020-08-30
 */
@RestController
@RequestMapping(value = "sqlApi")
public class IndexController implements QueryController {

    private SqlServiceImpl sqlService;

    @Autowired
    public void setSqlService(SqlServiceImpl sqlService) {
        this.sqlService = sqlService;
    }

    @Override
    public ResultVO sqlFindAll(@RequestParam("pageNumber") Integer pageNumber,
                               @RequestParam("pageSize") Integer pageSize,
                               @RequestParam("sortCode") String sortCode) {
        return ResultVO.success(sqlService.findAll(pageNumber - 1, pageSize, sortCode));
    }

    @Override
    public ResultVO saveSql(@RequestBody SqlPO po) {
        if (po.getId() == null) {
            return ResultVO.success(sqlService.addOne(po));
        }
        return ResultVO.success(sqlService.updateOne(po));
    }

    @Override
    public void sqlDeleteOne(@RequestParam("id") String id) {
        sqlService.deleteOne(id);
    }

    /**
     * 通过sql查询
     * @param sqlStr sql语句
     * @param sqlType sql类型
     * @return 返回值
     */
    @PostMapping(value = "doSql")
    public ResultVO doSql(@RequestParam("sqlStr") String sqlStr, @RequestParam("sqlType") String sqlType) {
        String query = "查询";
        try {
            if (query.equals(sqlType)) {
                return ResultVO.success(sqlService.queryBySql(sqlStr));
            } else {
                sqlService.excuteSql(sqlStr);
                return ResultVO.success();
            }
        }catch (Exception e){
            return ResultVO.failure(e.getCause().getCause());
        }

    }

}
