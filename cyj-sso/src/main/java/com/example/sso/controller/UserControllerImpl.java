package com.example.sso.controller;

import com.example.cyjcommon.utils.ResultVO;
import com.example.sso.entity.UserPO;
import com.example.sso.serviceimpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2020-09-13
 */
@RestController
@RequestMapping(value = "userApi")
public class UserControllerImpl implements UserController {

    private UserServiceImpl userService;

    @Autowired
    public void setUserService(UserServiceImpl userService) {
        this.userService = userService;
    }

    @Override
    public ResultVO userFindAll(@RequestParam("pageNumber") Integer pageNumber,
                                @RequestParam("pageSize") Integer pageSize,
                                @RequestParam("sortCode") String sortCode) {
        return ResultVO.success(userService.findAll(pageNumber - 1, pageSize, sortCode));
    }

    @Override
    public ResultVO userSave(UserPO po) {
        if (po.getId() == null) {
            return ResultVO.success(userService.addOne(po));
        }
        return ResultVO.success(userService.updateOne(po));
    }

    @Override
    public void userDelete(@RequestParam("id") String id) {
        userService.deleteOne(id);
    }

}
