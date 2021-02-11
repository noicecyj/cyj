package com.example.sso.controller;

import com.example.cyjcommon.utils.ResultVO;
import com.example.cyjcommon.utils.VoPoConverter;
import com.example.sso.entity.*;
import com.example.sso.serviceimpl.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2021-02-11
 */
@RestController
@RequestMapping(value = "ssoApi")
public class ApiControllerImpl implements ApiController {

    private ApiServiceImpl apiService;

    @Autowired
    public void setApiService(ApiServiceImpl apiService) {
        this.apiService = apiService;
    }

    @Override
    public ResultVO apiFindAll(Integer pageNumber, Integer pageSize, String sortCode) {
        return ResultVO.success(apiService.findAll(pageNumber - 1, pageSize, sortCode));
    }

    @Override
    public ResultVO apiSave(Map<String, Object> vo) {
        ApiPO po = new ApiPO();
        VoPoConverter.copyProperties(vo, po);
        if (po.getId() == null) {
            return ResultVO.success(apiService.addOne(po));
        }
        return ResultVO.success(apiService.updateOne(po));
    }

    @Override
    public void apiDelete(String id) {
        apiService.deleteOne(id);
    }

    @Override
    public ResultVO findApiById(String id) {
        return ResultVO.success(apiService.findOneById(id));
    }

}
