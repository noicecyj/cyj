package com.example.cyjdictionary.service;

import com.example.cyjdictionary.entity.CatalogPO;
import com.example.cyjdictionary.entity.DictionaryPO;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2020-09-13
 */
public interface IndexService {

    /**
     * 根据目录名称查找字典
     *
     * @param name 目录名称
     * @return 字典列表
     */
    List<DictionaryPO> findCatalogByName(String name);

    /**
     * 根据目录代号查找字典
     *
     * @param value 目录代号
     * @return 字典列表
     */
    List<DictionaryPO> findCatalogByValue(String value);

    /**
     * 根据名称和代号进行模糊查询
     *
     * @param catalogName  目录名称
     * @param catalogValue 目录代号
     * @param pageNumber   页码
     * @param pageSize     条目
     * @param sortCode     排序列
     * @return 目录列表分页
     */
    Page<CatalogPO> findAllByCatalogNameContainsOrCatalogValueContains(String catalogName,
                                                                       String catalogValue,
                                                                       Integer pageNumber,
                                                                       Integer pageSize,
                                                                       String sortCode);
}
