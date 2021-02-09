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
 * @date 2021-02-09
 */
@RestController
@RequestMapping(value = "ssoApi")
public class UserControllerImpl implements UserController {

    private UserServiceImpl userService;

    @Autowired
    public void setUserService(UserServiceImpl userService) {
        this.userService = userService;
    }

    @Override
    public ResultVO userFindAll(Integer pageNumber, Integer pageSize, String sortCode) {
        return ResultVO.success(userService.findAll(pageNumber - 1, pageSize, sortCode));
    }

    @Override
    public ResultVO userSave(Map<String, Object> vo) {
        UserPO po = new UserPO();
        VoPoConverter.copyProperties(vo, po);
        if (po.getId() == null) {
            return ResultVO.success(userService.addOne(po));
        }
        return ResultVO.success(userService.updateOne(po));
    }

    @Override
    public void userDelete(String id) {
        userService.deleteOne(id);
    }

    @Override
    public ResultVO findUserById(String id) {
        return ResultVO.success(userService.findOneById(id));
    }

}
