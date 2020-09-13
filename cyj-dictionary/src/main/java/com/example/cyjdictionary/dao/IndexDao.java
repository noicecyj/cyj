package com.example.cyjdictionary.dao;

import com.example.cyjdictionary.entity.CatalogPO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2020-09-13
 */
public interface IndexDao extends JpaRepository<CatalogPO, String> {
    /**
     * 根据目录名称进行模糊查询
     *
     * @param catalogName  目录名称
     * @param catalogValue 目录代码
     * @param pageable     分页器
     * @return 分页结果
     */
    Page<CatalogPO> findAllByCatalogNameContainsOrCatalogValueContains(String catalogName,
                                                                       String catalogValue,
                                                                       Pageable pageable);
}
