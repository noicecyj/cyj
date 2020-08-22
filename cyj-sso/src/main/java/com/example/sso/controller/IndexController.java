package com.example.sso.controller;

import com.example.sso.entity.*;
import com.example.sso.serviceimpl.ApiServiceImpl;
import com.example.sso.serviceimpl.PageFunctionServiceImpl;
import com.example.sso.serviceimpl.RoleServiceImpl;
import com.example.sso.serviceimpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2020-08-09
 */
@RestController
@RequestMapping(value = "ssoApi")
public class IndexController {

    private UserServiceImpl userService;
    private RoleServiceImpl roleService;
    private PageFunctionServiceImpl pageFunctionService;
    private ApiServiceImpl apiService;

    @Autowired
    public void setUserService(UserServiceImpl userService) {
        this.userService = userService;
    }

    @Autowired
    public void setRoleService(RoleServiceImpl roleService) {
        this.roleService = roleService;
    }

    @Autowired
    public void setPageFunctionService(PageFunctionServiceImpl pageFunctionService) {
        this.pageFunctionService = pageFunctionService;
    }

    @Autowired
    public void setApiService(ApiServiceImpl apiService) {
        this.apiService = apiService;
    }

    @PostMapping(value = "userPage")
    public ResultVO userFindAll(@RequestParam("pageNumber") Integer pageNumber,
                                @RequestParam("pageSize") Integer pageSize,
                                @RequestParam("sortCode") String sortCode) {
        return ResultVO.success(userService.findAll(pageNumber, pageSize, sortCode));
    }

    @PostMapping(value = "userSave")
    public ResultVO saveUser(@RequestBody UserPO po) {
        if (po.getId() == null) {
            return ResultVO.success(userService.addOne(po));
        }
        return ResultVO.success(userService.updateOne(po));
    }

    @PostMapping(value = "userDelete")
    public void userDeleteOne(@RequestParam("id") String id) {
        userService.deleteOne(id);
    }

    @PostMapping(value = "rolePage")
    public ResultVO roleFindAll(@RequestParam("pageNumber") Integer pageNumber,
                                @RequestParam("pageSize") Integer pageSize,
                                @RequestParam("sortCode") String sortCode) {
        return ResultVO.success(roleService.findAll(pageNumber, pageSize, sortCode));
    }

    @PostMapping(value = "roleSave")
    public ResultVO saveRole(@RequestBody RolePO po) {
        if (po.getId() == null) {
            return ResultVO.success(roleService.addOne(po));
        }
        return ResultVO.success(roleService.updateOne(po));
    }

    @PostMapping(value = "roleDelete")
    public void roleDeleteOne(@RequestParam("id") String id) {
        roleService.deleteOne(id);
    }

    @PostMapping(value = "pageFunctionPage")
    public ResultVO pageFunctionFindAll(@RequestParam("pageNumber") Integer pageNumber,
                                        @RequestParam("pageSize") Integer pageSize,
                                        @RequestParam("sortCode") String sortCode) {
        return ResultVO.success(pageFunctionService.findAll(pageNumber, pageSize, sortCode));
    }

    @PostMapping(value = "pageFunctionSave")
    public ResultVO savePageFunction(@RequestBody PageFunctionPO po) {
        if (po.getId() == null) {
            return ResultVO.success(pageFunctionService.addOne(po));
        }
        return ResultVO.success(pageFunctionService.updateOne(po));
    }

    @PostMapping(value = "pageFunctionDelete")
    public void pageFunctionDeleteOne(@RequestParam("id") String id) {
        pageFunctionService.deleteOne(id);
    }

    @PostMapping(value = "apiPage")
    public ResultVO apiFindAll(@RequestParam("pageNumber") Integer pageNumber,
                               @RequestParam("pageSize") Integer pageSize,
                               @RequestParam("sortCode") String sortCode) {
        return ResultVO.success(apiService.findAll(pageNumber, pageSize, sortCode));
    }

    @PostMapping(value = "apiSave")
    public ResultVO saveApi(@RequestBody ApiPO po) {
        if (po.getId() == null) {
            return ResultVO.success(apiService.addOne(po));
        }
        return ResultVO.success(apiService.updateOne(po));
    }

    @PostMapping(value = "apiDelete")
    public void apiDeleteOne(@RequestParam("id") String id) {
        apiService.deleteOne(id);
    }

}
