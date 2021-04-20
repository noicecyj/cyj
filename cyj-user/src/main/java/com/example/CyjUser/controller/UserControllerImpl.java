package com.example.CyjUser.controller;

import com.example.CyjUser.entity.UserPO;
import com.example.CyjUser.serviceimpl.UserServiceImpl;
import com.example.cyjcommon.utils.ResultVO;
import com.example.cyjcommon.utils.VoPoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2021-03-07
 */
@RestController
@RequestMapping(value = "authApi")
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

    @GetMapping("/getCurrentUser")
    public Object getCurrentUser(Authentication authentication) {
        return authentication.getPrincipal();
    }

}
