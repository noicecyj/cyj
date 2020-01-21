package com.example.cyjdictionary.service;

import com.example.cyjdictionary.entity.Dictionary;
import com.example.cyjdictionary.entity.DictionaryCatalog;

import java.util.List;
/**
 * @author 曹元杰
 * @version 1.0
 * @date 2020/1/21 14:46
 */
public interface DictionaryCatalogService {
    /**
     * 查找所有目录
     *
     * @return 目录列表
     */
    List<DictionaryCatalog> findAll();

    /**
     * 根据id查找目录
     *
     * @param id 目录id
     * @return 目录
     */
    DictionaryCatalog findOneById(Integer id);
    /**
     * 添加目录
     *
     * @param dictionaryCatalog 目录
     * @return 目录
     */
    DictionaryCatalog addOne(DictionaryCatalog dictionaryCatalog);
    /**
     * 删除目录
     *
     * @param id 目录id
     */
    void deleteOne(Integer id);
    /**
     * 更新目录
     *
     * @param dictionaryCatalog 目录
     * @return 目录
     */
    DictionaryCatalog updateOne(DictionaryCatalog dictionaryCatalog);
    /**
     * 获取目录数量
     *
     * @return 目录数量
     */
    long count();
}