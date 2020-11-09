package com.example.cyjdictionary.service;

import com.example.cyjdictionary.entity.DictionaryPO;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2020-11-09
 */
public interface DictionaryService {

    /**
     * 添加实体
     *
     * @param po 实体
     * @return 实体
     */
    DictionaryPO addOne(DictionaryPO po);

    /**
     * 删除实体
     *
     * @param id 实体id
     */
    void deleteOne(String id);

    /**
     * 更新实体
     *
     * @param po 实体
     * @return 实体
     */
    DictionaryPO updateOne(DictionaryPO po);

    /**
     * 查找所有实体(分页排序)
     *
     * @param id         id
     * @param pageNumber 页码
     * @param pageSize   条目
     * @param sortCode   排序列
     * @return 实体列表分页
     */
    Page<DictionaryPO> findAll(String id, Integer pageNumber, Integer pageSize, String sortCode);

    /**
     * 查找实体
     *
     * @param id 实体id
     * @return 实体
     */
    List<DictionaryPO> findOneById(String id);

}
