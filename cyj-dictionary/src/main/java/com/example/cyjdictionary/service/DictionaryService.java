package com.example.cyjdictionary.service;

import com.example.cyjdictionary.entity.Dictionary;
import com.example.cyjdictionary.entity.DictionaryCatalog;
import org.springframework.data.domain.Page;

import java.util.List;
/**
 * @author 曹元杰
 * @version 1.0
 * @date 2020/1/21 14:46
 */
public interface DictionaryService {
    /**
     * 查找所有字典
     *
     * @return 字典列表
     */
    List<Dictionary> findAll();
    /**
     * 根据id查找字典
     *
     * @param id 字典id
     * @return 字典
     */
    Dictionary findOneById(String id);
    /**
     * 添加字典
     *
     * @param dictionary 字典
     * @return 字典
     */
    Dictionary addOne(Dictionary dictionary);
    /**
     * 删除字典
     *
     * @param id 字典id
     */
    void deleteOne(String id);
    /**
     * 更新字典
     *
     * @param dictionary 字典
     * @return 字典
     */
    Dictionary updateOne(Dictionary dictionary);
    /**
     * 获取字典数量
     *
     * @return 字典数量
     */
    long count();
    /**
     * 根据目录id查找字典
     *
     * @param id 目录id
     * @return 字典列表
     */
    List<Dictionary> findCatalogById(String id);
    /**
     * 根据目录名称查找字典
     *
     * @param name 目录名称
     * @return 字典列表
     */
    List<Dictionary> findCatalogByName(String name);
    /**
     * 根据目录代号查找字典
     *
     * @param value 目录代号
     * @return 字典列表
     */
    List<Dictionary> findCatalogByValue(String value);

    /**
     * 查找所有字典(分页排序)
     *
     * @param pageNumber 页码
     * @param pageSize 条目
     * @param sortCode 排序列
     * @return 目录列表分页
     */
    Page<Dictionary> findAll(Integer pageNumber, Integer pageSize, String sortCode);

}
