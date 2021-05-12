package com.example.CyjUser.config;

import com.example.CyjUser.serviceimpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2020/1/21 14:46
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;
    private UserServiceImpl userService;

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Autowired
    public void setUserService(UserServiceImpl userService) {
        this.userService = userService;
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints.userDetailsService(userService)
                .authenticationManager(authenticationManager);
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                //应用ID
                .withClient("cyj-entity-creater")
                //应用密码
                .secret(passwordEncoder.encode("123456"))
                //应用权限
                .scopes("read", "write")
                //token的失效时间
                .accessTokenValiditySeconds(1800)
                //允许访问的服务ID
                .resourceIds("order-server")
                //授权方式
                .authorizedGrantTypes("password");
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        //暴露验证服务/oauth/token_key   permitAll()为关闭
        security.tokenKeyAccess("isAuthenticated()")
                //暴露验证服务/oauth/check_token
                .checkTokenAccess("isAuthenticated()");
    }
}
