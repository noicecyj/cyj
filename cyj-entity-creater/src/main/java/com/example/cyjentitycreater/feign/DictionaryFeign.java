package com.example.cyjentitycreater.feign;

import com.example.cyjentitycreater.entity.Dictionary;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "cyj-data-dictionary",url = "http://localhost:8030")
public interface DictionaryFeign {
    @RequestMapping(value = "/Dictionary",method = RequestMethod.GET)
    List<Dictionary> findDictionaryByDictionaryCatalog_Id(@RequestParam("pid") Integer pid);
}
