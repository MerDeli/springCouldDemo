package com.lyy.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import java.util.concurrent.TimeUnit;

/**
 * TODO 简要描述
 *
 * @author luyong 1406557342@qq.com
 * @version 20191220000
 */
@EnableAuthorizationServer
public class ClientDetailsService extends AuthorizationServerConfigurerAdapter {
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.tokenKeyAccess("isAuthenticated()");
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()  //使用in_memory存储(内存)
                .withClient("client_1") //client_id
                .secret(new BCryptPasswordEncoder().encode("client_1_admin123"))    //client_secret
                .authorizedGrantTypes("authorization_code","password","refresh_token","implicit","client_credentials")  //该client_id允许的授权模式
                .scopes("all")  //允许的授权范围
                .autoApprove(false)
                .redirectUris("http://localhost:9007/login") //验证回调地址
                .and()
                .withClient("client_2") //client_id
                .secret(new BCryptPasswordEncoder().encode("client_2_admin123"))    //client_secret
                .authorizedGrantTypes("authorization_code","password","refresh_token","implicit","client_credentials")  //该client_id允许的授权模式
                .scopes("all")  //允许的授权范围
                .autoApprove(false)
                .redirectUris("http://localhost:9006/login"); //验证回调地址
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        DefaultTokenServices tokenServices = (DefaultTokenServices) endpoints.getDefaultAuthorizationServerTokenServices();
        tokenServices.setTokenStore(jwtTokenStore());
        tokenServices.setSupportRefreshToken(true);
        // 获取ClientDetailsService
        tokenServices.setClientDetailsService(endpoints.getClientDetailsService());
        tokenServices.setTokenEnhancer(jwtAccessTokenConverter());
        //一天有效期
        tokenServices.setAccessTokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(1));
        endpoints.tokenServices(tokenServices);
    }

    @Bean
    public TokenStore jwtTokenStore(){
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter(){
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey("securityKey");
        return converter;
    }
}
