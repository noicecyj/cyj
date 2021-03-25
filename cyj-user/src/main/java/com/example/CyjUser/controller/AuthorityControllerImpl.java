package com.example.CyjUser.controller;

import com.example.CyjUser.entity.AuthorityPO;
import com.example.CyjUser.serviceimpl.AuthorityServiceImpl;
import com.example.cyjcommon.utils.ResultVO;
import com.example.cyjcommon.utils.VoPoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2021-03-20
 */
@RestController
@RequestMapping(value = "authApi")
public class AuthorityControllerImpl implements AuthorityController {

    private AuthorityServiceImpl authorityService;

    @Autowired
    public void setAuthorityService(AuthorityServiceImpl authorityService) {
        this.authorityService = authorityService;
    }

    @Override
    public ResultVO authorityFindAll(Integer pageNumber, Integer pageSize, String sortCode) {
        return ResultVO.success(authorityService.findAll(pageNumber - 1, pageSize, sortCode));
    }

    @Override
    public ResultVO authoritySave(Map<String, Object> vo) {
        AuthorityPO po = new AuthorityPO();
        VoPoConverter.copyProperties(vo, po);
        if (po.getId() == null) {
            return ResultVO.success(authorityService.addOne(po));
        }
        return ResultVO.success(authorityService.updateOne(po));
    }

    @Override
    public void authorityDelete(String id) {
        authorityService.deleteOne(id);
    }

    @Override
    public ResultVO findAuthorityById(String id) {
        return ResultVO.success(authorityService.findOneById(id));
    }

}
