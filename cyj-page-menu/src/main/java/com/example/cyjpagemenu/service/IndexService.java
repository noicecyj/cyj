package com.example.cyjpagemenu.service;

import com.example.cyjpagemenu.entity.DataItemPO;

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
    List<DataItemPO> findDataFormByName(String name);

    /**
     * 根据名称获取表格
     *
     * @param name 名称
     * @return 返回结果
     */
    List<DataItemPO> findDataTableByName(String name);
}
