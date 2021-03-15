package com.example.cyjapi.config;

import com.example.cyjapi.provider.MobileCodeAuthenticationProvider;
import com.example.cyjapi.serviceimpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.LogoutFilter;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2020/1/21 14:46
 */
@Configuration
@EnableWebSecurity
public class SecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

    private UserServiceImpl userService;
    private StringRedisTemplate stringRedisTemplate;
    private IgnoreLogoutFilter ignoreLogoutFilter;

    @Autowired
    public void setUserService(UserServiceImpl userService) {
        this.userService = userService;
    }

    @Autowired
    public void setStringRedisTemplate(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Autowired
    public void setIgnoreLogoutFilter(IgnoreLogoutFilter ignoreLogoutFilter) {
        this.ignoreLogoutFilter = ignoreLogoutFilter;
    }

    /**
     * 配置认证管理器
     *
     * @return AuthenticationManager
     * @throws Exception Exception
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    /**
     * 配置密码加密对象（解密时会用到PasswordEncoder的matches判断是否正确）
     * 用户的password和客户端clientSecret用到，所以存的时候存该bean encode过的密码
     *
     * @return PasswordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 这里是对认证管理器的添加配置
     *
     * @param auth auth
     * @throws Exception Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(provider())
                .userDetailsService(userService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/css/**","/static/**");
    }

    /**
     *  安全请求配置,这里配置的是security的部分，这里配置全部通过，安全拦截在资源服务的配置文件中配置，
     *  要不然访问未验证的接口将重定向到登录页面，前后端分离的情况下这样并不友好，无权访问接口返回相关错误信息即可
     * @param http http
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .formLogin().loginPage("/login")
                .permitAll()
                .and().authorizeRequests().anyRequest().permitAll()
                .and().csrf().disable().cors()
                .and().addFilterAt(ignoreLogoutFilter, LogoutFilter.class);
    }


    /**
     * 自定义手机验证码认证提供者
     *
     * @return provider
     */
    @Bean
    public MobileCodeAuthenticationProvider provider() {
        MobileCodeAuthenticationProvider provider = new MobileCodeAuthenticationProvider();
        provider.setStringRedisTemplate(stringRedisTemplate);
        provider.setHideUserNotFoundExceptions(false);
        provider.setUserService(userService);
        return provider;
    }

}
