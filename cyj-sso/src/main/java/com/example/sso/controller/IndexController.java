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
public class IndexController implements UserController, RoleController, PageFunctionController, ApiController {

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

    @Override
    public ResultVO userFindAll(@RequestParam("pageNumber") Integer pageNumber,
                                @RequestParam("pageSize") Integer pageSize,
                                @RequestParam("sortCode") String sortCode) {
        return ResultVO.success(userService.findAll(pageNumber - 1, pageSize, sortCode));
    }

    @Override
    public ResultVO saveUser(@RequestBody UserPO po) {
        if (po.getId() == null) {
            return ResultVO.success(userService.addOne(po));
        }
        return ResultVO.success(userService.updateOne(po));
    }

    @Override
    public void userDeleteOne(@RequestParam("id") String id) {
        userService.deleteOne(id);
    }

    @Override
    public ResultVO roleFindAll(@RequestParam("pageNumber") Integer pageNumber,
                                @RequestParam("pageSize") Integer pageSize,
                                @RequestParam("sortCode") String sortCode) {
        return ResultVO.success(roleService.findAll(pageNumber - 1, pageSize, sortCode));
    }

    @Override
    public ResultVO saveRole(@RequestBody RolePO po) {
        if (po.getId() == null) {
            return ResultVO.success(roleService.addOne(po));
        }
        return ResultVO.success(roleService.updateOne(po));
    }

    @Override
    public void roleDeleteOne(@RequestParam("id") String id) {
        roleService.deleteOne(id);
    }

    @Override
    public ResultVO pageFunctionFindAll(@RequestParam("pageNumber") Integer pageNumber,
                                        @RequestParam("pageSize") Integer pageSize,
                                        @RequestParam("sortCode") String sortCode) {
        return ResultVO.success(pageFunctionService.findAll(pageNumber - 1, pageSize, sortCode));
    }

    @Override
    public ResultVO savePageFunction(@RequestBody PageFunctionPO po) {
        if (po.getId() == null) {
            return ResultVO.success(pageFunctionService.addOne(po));
        }
        return ResultVO.success(pageFunctionService.updateOne(po));
    }

    @Override
    public void pageFunctionDeleteOne(@RequestParam("id") String id) {
        pageFunctionService.deleteOne(id);
    }

    @Override
    public ResultVO apiFindAll(@RequestParam("pageNumber") Integer pageNumber,
                               @RequestParam("pageSize") Integer pageSize,
                               @RequestParam("sortCode") String sortCode) {
        return ResultVO.success(apiService.findAll(pageNumber - 1, pageSize, sortCode));
    }

    @Override
    public ResultVO saveApi(@RequestBody ApiPO po) {
        if (po.getId() == null) {
            return ResultVO.success(apiService.addOne(po));
        }
        return ResultVO.success(apiService.updateOne(po));
    }

    @Override
    public void apiDeleteOne(@RequestParam("id") String id) {
        apiService.deleteOne(id);
    }

}
