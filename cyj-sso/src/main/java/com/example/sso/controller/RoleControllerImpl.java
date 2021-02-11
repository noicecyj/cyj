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
public class RoleControllerImpl implements RoleController {

    private RoleServiceImpl roleService;

    @Autowired
    public void setRoleService(RoleServiceImpl roleService) {
        this.roleService = roleService;
    }

    @Override
    public ResultVO roleFindAll(Integer pageNumber, Integer pageSize, String sortCode) {
        return ResultVO.success(roleService.findAll(pageNumber - 1, pageSize, sortCode));
    }

    @Override
    public ResultVO roleSave(Map<String, Object> vo) {
        RolePO po = new RolePO();
        VoPoConverter.copyProperties(vo, po);
        if (po.getId() == null) {
            return ResultVO.success(roleService.addOne(po));
        }
        return ResultVO.success(roleService.updateOne(po));
    }

    @Override
    public void roleDelete(String id) {
        roleService.deleteOne(id);
    }

    @Override
    public ResultVO findRoleById(String id) {
        return ResultVO.success(roleService.findOneById(id));
    }

}
