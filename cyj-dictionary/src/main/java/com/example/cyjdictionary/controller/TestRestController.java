package com.example.cyjdictionary.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2020/1/21 14:46
 */
@RestController
public class TestRestController {


    /**
     * 不需要token访问测试
     * @return no_need_token
     */
    @GetMapping("/test/no_need_token")
    public @ResponseBody
    String test() {
        return "no_need_token";
    }

    /**
     * 需要需要token访问接口测试
     * @return need_token
     */
    @GetMapping("/test/need_token")
    public @ResponseBody
    String test2(Authentication authentication) {
        // 由于自定义的principal返回的是包含全部user字段的map
        Object principal = authentication.getPrincipal();
        return "need_token";
    }

    /**
     * 需要需要管理员权限
     * @return need_admin
     */
    @PreAuthorize("hasAuthority('admin')")
    @GetMapping("/test/need_admin")
    public @ResponseBody
    String admin() {
        return "need_admin";
    }

}
