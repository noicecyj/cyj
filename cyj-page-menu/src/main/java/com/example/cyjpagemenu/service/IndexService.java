package com.example.cyjpagemenu.service;

import com.example.cyjpagemenu.entity.DataFormItemPO;
import com.example.cyjpagemenu.entity.DataTableItemPO;
import com.example.cyjpagemenu.entity.DataTablePO;

import java.util.List;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2020-09-13
 */
public interface IndexService {

    /**
     * 根据名称获取表单
     *
     * @param name 名称
     * @return 返回结果
     */
    List<DataFormItemPO> findDataFormByName(String name);

    /**
     * 根据名称获取表格
     *
     * @param name 名称
     * @return 返回结果
     */
    List<DataTableItemPO> findDataTableByName(String name);

    /**
     * 查找实体
     *
     * @param name 实体name
     * @return 实体
     */
    DataTablePO findOneByName(String name);
}
