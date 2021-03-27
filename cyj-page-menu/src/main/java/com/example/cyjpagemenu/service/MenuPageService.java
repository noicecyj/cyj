package com.example.cyjpagemenu.service;

import com.example.cyjpagemenu.entity.MenuPagePO;
import com.example.cyjpagemenu.entity.vo.MenuPageVO;
import org.springframework.data.domain.Page;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2021-02-04
 */
public interface MenuPageService {

    /**
     * 添加实体
     *
     * @param po 实体
     * @return 实体
     */
    MenuPagePO addOne(MenuPagePO po);

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
    MenuPagePO updateOne(MenuPagePO po);

    /**
     * 查找所有实体(分页排序)
     *
     * @param pageNumber 页码
     * @param pageSize   条目
     * @param sortCode   排序列
     * @return 实体列表分页
     */
    Page<MenuPagePO> findAll(Integer pageNumber, Integer pageSize, String sortCode);


    /**
     * 查找实体
     *
     * @param id 实体id
     * @return 实体
     */
    MenuPagePO findOneById(String id);

    /**
     * 查找所有菜单
     *
     * @return 目录列表分页
     */
    MenuPageVO findAll();

    /**
     * 所有菜单数量
     *
     * @return 目录列表分页
     */
    long count();

}
