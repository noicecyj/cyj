package com.example.cyjdictionary.service;

import com.example.cyjdictionary.entity.DictionaryPO;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2020/1/21 14:46
 */
public interface DictionaryService {

    /**
     * 添加字典
     *
     * @param po 字典
     * @return 字典
     */
    DictionaryPO addOne(DictionaryPO po);

    /**
     * 删除字典
     *
     * @param id 字典id
     */
    void deleteOne(String id);

    /**
     * 更新字典
     *
     * @param po 字典
     * @return 字典
     */
    DictionaryPO updateOne(DictionaryPO po);

    /**
     * 根据目录id查找字典
     *
     * @param id 目录id
     * @return 字典列表
     */
    List<DictionaryPO> findCatalogById(String id);

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
     * 查找所有字典(分页排序)
     *
     * @param id         ID
     * @param pageNumber 页码
     * @param pageSize   条目
     * @param sortCode   排序列
     * @return 目录列表分页
     */
    Page<DictionaryPO> findAll(String id, Integer pageNumber, Integer pageSize, String sortCode);

}
