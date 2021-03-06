package com.example.cyjentitycreater.controller;

import com.example.cyjcommon.utils.ResultVO;
import com.example.cyjcommon.utils.VoPoConverter;
import com.example.cyjentitycreater.entity.*;
import com.example.cyjentitycreater.serviceimpl.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2021-03-06
 */
@RestController
@RequestMapping(value = "entityCreateApi")
public class AppServiceControllerImpl implements AppServiceController {

    private AppServiceServiceImpl appServiceService;

    @Autowired
    public void setAppServiceService(AppServiceServiceImpl appServiceService) {
        this.appServiceService = appServiceService;
    }

    @Override
    public ResultVO appServiceFindAll(Integer pageNumber, Integer pageSize, String sortCode) {
        return ResultVO.success(appServiceService.findAll(pageNumber - 1, pageSize, sortCode));
    }

    @Override
    public ResultVO appServiceSave(Map<String, Object> vo) {
        AppServicePO po = new AppServicePO();
        VoPoConverter.copyProperties(vo, po);
        if (po.getId() == null) {
            return ResultVO.success(appServiceService.addOne(po));
        }
        return ResultVO.success(appServiceService.updateOne(po));
    }

    @Override
    public void appServiceDelete(String id) {
        appServiceService.deleteOne(id);
    }

    @Override
    public ResultVO findAppServiceById(String id) {
        return ResultVO.success(appServiceService.findOneById(id));
    }

}
