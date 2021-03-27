package com.example.cyjentitycreater.api;


import com.example.cyjentitycreater.entity.dto.DictionaryDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2020/1/21 14:46
 */
@Component
@FeignClient(name = "cyj-dictionary")
public interface DictionaryApiService {

    /**
     * 根据目录名称查询字典
     *
     * @param name 目录名称
     * @return 返回结果
     */
    @PostMapping(value = "/dictionaryApi/findCatalogByName")
    List<DictionaryDTO> findCatalogByName(@RequestParam("name") String name);

    /**
     * 根据目录值查询字典
     *
     * @param value 目录值
     * @return 返回结果
     */
    @PostMapping(value = "/dictionaryApi/findCatalogByValue")
    List<DictionaryDTO> findCatalogByValue(@RequestParam("value") String value);
}
