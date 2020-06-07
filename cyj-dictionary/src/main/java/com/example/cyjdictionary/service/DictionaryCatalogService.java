package com.example.cyjdictionary.service;

import com.example.cyjdictionary.entity.DictionaryCatalog;
import org.springframework.data.domain.Page;

import java.awt.print.Pageable;
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
     * 查找所有目录(分页排序)
     *
     * @param pageNumber 页码
     * @param pageSize 条目
     * @param sortCode 排序列
     * @return 目录列表分页
     */
    Page<DictionaryCatalog> findAll(Integer pageNumber, Integer pageSize, String sortCode);

    /**
     * 根据id查找目录
     *
     * @param id 目录id
     * @return 目录
     */
    DictionaryCatalog findOneById(String id);

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
    void deleteOne(String id);

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

    /**
     * 根据名称和代号进行模糊查询
     *
     * @param catalogName 目录名称
     * @param catalogValue 目录代号
     * @param pageNumber 页码
     * @param pageSize 条目
     * @param sortCode 排序列
     * @return 目录列表分页
     */
    Page<DictionaryCatalog> findAllByCatalogNameContainsOrCatalogValueContains(String catalogName,
                                                                                    String catalogValue,
                                                                                    Integer pageNumber,
                                                                                    Integer pageSize,
                                                                                    String sortCode);
}