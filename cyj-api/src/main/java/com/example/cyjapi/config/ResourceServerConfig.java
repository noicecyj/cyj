package com.example.cyjapi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2020/1/21 14:46
 */
@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    private static final String RESOURCE_ID = "cyj-api";


    /**
     *  配置资源接口安全，http.authorizeRequests()针对的所有url，但是由于登录页面url包含在其中，这里配置会进行token校验，校验不通过返回错误json，
     *  而授权码模式获取code时需要重定向登录页面，重定向过程并不能携带token，所有不能用http.authorizeRequests()，
     *  而是用requestMatchers().antMatchers("")，这里配置的是需要资源接口拦截的url数组
     * @param http http
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http    //配置需要保护的资源接口
                .requestMatchers().antMatchers("/user","/test/need_token","/logout","/remove","/update","/test/need_admin","/test/scope")
                .and().authorizeRequests().anyRequest().authenticated();
    }

    /**
     * 这个是跟服务绑定的，注意要跟client配置一致，如果客户端没有，则不能访问
     * @param resources resources
     */
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.resourceId(RESOURCE_ID).stateless(true);
    }


}

