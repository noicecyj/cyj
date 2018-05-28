package com.example.cyjentitycreater.feign;

import com.example.cyjentitycreater.entity.Dictionary;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("cyj-data-dictionary")
public interface DictionaryFeign {
    @GetMapping("/dictionary")
    List<Dictionary> findDictionaryByDictionaryCatalog_Id(@RequestParam("pid") Integer pid);
}
