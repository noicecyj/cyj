package com.example.cyjapi.config;

import com.example.cyjapi.granter.MobileCodeTokenGranter;
import com.example.cyjapi.serviceimpl.ClientDetailsServiceImpl;
import com.example.cyjapi.serviceimpl.UserServiceImpl;
import com.example.cyjapi.vo.UserDetailImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.CompositeTokenGranter;
import org.springframework.security.oauth2.provider.TokenGranter;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeTokenGranter;
import org.springframework.security.oauth2.provider.implicit.ImplicitTokenGranter;
import org.springframework.security.oauth2.provider.password.ResourceOwnerPasswordTokenGranter;
import org.springframework.security.oauth2.provider.refresh.RefreshTokenGranter;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2020/1/21 14:46
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationConfig extends AuthorizationServerConfigurerAdapter {

    private AuthenticationManager authenticationManager;
    private UserServiceImpl userService;
    private ClientDetailsServiceImpl clientDetailsService;
    private RedisConnectionFactory redisConnectionFactory;
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Autowired
    public void setUserService(UserServiceImpl userService) {
        this.userService = userService;
    }

    @Autowired
    public void setClientDetailsService(ClientDetailsServiceImpl clientDetailsService) {
        this.clientDetailsService = clientDetailsService;
    }

    @Autowired
    public void setRedisConnectionFactory(RedisConnectionFactory redisConnectionFactory) {
        this.redisConnectionFactory = redisConnectionFactory;
    }

    @Autowired
    public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * 配置token存储，这个配置token存到redis中
     * @return clients
     */
    @Bean
    public TokenStore tokenStore() {
        return new RedisTokenStore(redisConnectionFactory);
    }

    /**
     * 配置授权码模式授权码服务,不配置默认为内存模式
     * @return clients
     */
    @Primary
    @Bean
    public AuthorizationCodeServices authorizationCodeServices() {
        return new RedisAuthorizationCodeServices(redisConnectionFactory);
    }

    /**
     * 配置客户端详情
     * @param clients clients
     * @throws Exception clients
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(clientDetailsService);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        // 采用token转jwt，并添加一些自定义信息（token增强）（有默认非必须）
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(
                Arrays.asList(jwtAccessTokenConverter(),tokenEnhancer()));
        endpoints.tokenEnhancer(tokenEnhancerChain)
                // 配置token存储，一般配置redis存储
                .tokenStore(tokenStore())
                // 配置认证管理器
                .authenticationManager(authenticationManager)
                // 配置用户详情server，密码模式必须
                .userDetailsService(userService)
                // 配置授权码模式授权码服务,不配置默认为内存模式
                .authorizationCodeServices(authorizationCodeServices())
                // 配置grant_type模式，如果不配置则默认使用密码模式、简化模式、验证码模式以及刷新token模式，如果配置了只使用配置中，默认配置失效
                // 具体可以查询AuthorizationServerEndpointsConfigurer中的getDefaultTokenGranters方法
                .tokenGranter(tokenGranter(endpoints));
        // 配置TokenServices参数
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setTokenStore(endpoints.getTokenStore());
        // 是否支持刷新Token
        tokenServices.setSupportRefreshToken(true);
        tokenServices.setReuseRefreshToken(true);
        tokenServices.setClientDetailsService(endpoints.getClientDetailsService());
        tokenServices.setTokenEnhancer(endpoints.getTokenEnhancer());
        // 设置accessToken和refreshToken的默认超时时间(如果clientDetails的为null就取默认的，如果clientDetails的不为null取clientDetails中的)
        tokenServices.setAccessTokenValiditySeconds((int) TimeUnit.HOURS.toSeconds(2));
        tokenServices.setRefreshTokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(30));
        endpoints.tokenServices(tokenServices);

    }



    /**
     * jwt格式封装token
     * @return clients
     */
    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        // 设置jwt加解密秘钥，不设置会随机一个
        jwtAccessTokenConverter.setSigningKey("noice");
        return jwtAccessTokenConverter;
    }

    /**
     * token增强,添加一些元信息
     *
     * @return TokenEnhancer
     */
    @Bean
    public TokenEnhancer tokenEnhancer() {
        return (accessToken, authentication) -> {
            final Map<String, Object> additionalInfo = new HashMap<>(2);
            additionalInfo.put("license", "noice");
            UserDetailImpl user = (UserDetailImpl) authentication.getUserAuthentication().getPrincipal();
            if (user != null) {
                additionalInfo.put("username", user.getUsername());
            }
            ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
            return accessToken;
        };
    }


    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) {
        security
                .allowFormAuthenticationForClients()
                .tokenKeyAccess("isAuthenticated()")
                .checkTokenAccess("permitAll()");
    }

    /**
     * 创建grant_type列表
     * @param endpoints endpoints
     * @return endpoints
     */
    private TokenGranter tokenGranter(AuthorizationServerEndpointsConfigurer endpoints) {
        List<TokenGranter> list = new ArrayList<>();
        // 这里配置密码模式、刷新token模式、自定义手机号验证码模式、授权码模式、简化模式
        list.add(new ResourceOwnerPasswordTokenGranter(authenticationManager, endpoints.getTokenServices(), endpoints.getClientDetailsService(), endpoints.getOAuth2RequestFactory()));
        list.add(new RefreshTokenGranter(endpoints.getTokenServices(), endpoints.getClientDetailsService(), endpoints.getOAuth2RequestFactory()));
        list.add(new MobileCodeTokenGranter(authenticationManager,endpoints.getTokenServices(), endpoints.getClientDetailsService(), endpoints.getOAuth2RequestFactory()));
        list.add(new AuthorizationCodeTokenGranter(endpoints.getTokenServices(),endpoints.getAuthorizationCodeServices(), endpoints.getClientDetailsService(), endpoints.getOAuth2RequestFactory()));
        list.add(new ImplicitTokenGranter(endpoints.getTokenServices(),endpoints.getClientDetailsService(),endpoints.getOAuth2RequestFactory()));
        return new CompositeTokenGranter(list);
    }
}
