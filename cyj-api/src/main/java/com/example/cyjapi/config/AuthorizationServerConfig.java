package com.example.cyjapi.config;

import com.example.cyjapi.serviceimpl.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2020/1/21 14:46
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    private AuthenticationManager authenticationManager;
    private RedisConnectionFactory connectionFactory;
    private UserDetailServiceImpl userDetailService;

    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Autowired
    public void setConnectionFactory(RedisConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    @Autowired
    public void setUserDetailService(UserDetailServiceImpl userDetailService) {
        this.userDetailService = userDetailService;
    }

    /**
     * @Description 使用TokenStore操作Token
     **/
    @Bean
    public TokenStore tokenStore() {
        return new RedisTokenStore(connectionFactory);
    }

    /**
     * 用来配置令牌端点(Token Endpoint)的安全约束.
     **/
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()").allowFormAuthenticationForClients();
    }

    /**
     * 配置客户端详情服务
     * 可以把客户端详情信息写死在这里或者是通过数据库来存储调取详情信息
     **/
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        /*clients.inMemory() // 使用in-memory存储
                .withClient("root") // client_id   android
                .scopes("read")
                .secret("123@.com")  // client_secret   android
                .authorizedGrantTypes("password", "authorization_code", "refresh_token") // 该client允许的授权类型
                .and()
                .withClient("webapp") // client_id
                .scopes("read")
                //.secret("webapp")  // client_secret
                .authorizedGrantTypes("implicit")// 该client允许的授权类型
                .and()
                .withClient("browser")
                .authorizedGrantTypes("refresh_token", "password")
                .scopes("read");*/
        //         //客户端信息通过Redis去取得验证
//        final RedisClientDetailsServiceBuilder builder = new RedisClientDetailsServiceBuilder();
//        clients.setBuilder(builder);
        //通过JDBC去查询数据库oauth_client_details表验证clientId信息
//        clients.jdbc(this.dataSource).clients(this.clientDetails());
//        clients.withClientDetails(clientDetails());
    }

    /**
     * 用来配置授权（authorization）以及令牌（token）的访问端点和令牌服务(token services)。
     **/
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints.tokenStore(tokenStore())
                .userDetailsService(userDetailService)
                .authenticationManager(authenticationManager);
        endpoints.tokenServices(defaultTokenServices());
    }

    /**
     * <p>注意，自定义TokenServices的时候，需要设置@Primary，否则报错，</p>
     * 自定义的token
     * 认证的token是存到redis里的
     *
     * @return
     */
    @Primary
    @Bean
    public DefaultTokenServices defaultTokenServices() {
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setTokenStore(tokenStore());
        tokenServices.setSupportRefreshToken(true);
        // token有效期自定义设置，默认12小时
        tokenServices.setAccessTokenValiditySeconds(60 * 60 * 12);
        // refresh_token默认30天
        tokenServices.setRefreshTokenValiditySeconds(60 * 60 * 24 * 7);
        return tokenServices;
    }
}
