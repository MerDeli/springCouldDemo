package com.lyy.security.config;

import com.lyy.security.service.MyUserDetailSerive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    /**认证管理器**/
    @Autowired
    private AuthenticationManager authenticationManager;
    /**redis链接工厂**/
    @Autowired
    private RedisConnectionFactory connectionFactory;
    /**密码加密**/
    @Autowired
    private PasswordEncoder passwordEncoder;
    /**获取用户信息业务类**/
    @Autowired
    private MyUserDetailSerive userDetailsService;
    /**数据源**/
    @Autowired
    private DataSource dataSource;


    /**
     * 认证客户端类
     * @return
     */
    @Bean
    public ClientDetailsService clientDetails(){return new JdbcClientDetailsService(dataSource); }

    /**
     * redis 认证token存储类
     * @return
     */
    @Bean
    public RedisTokenStore tokenStore(){return new RedisTokenStore(connectionFactory);}


    /**
     * 客户端配置
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(clientDetails());
    }

    /**
     * 端点配置
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService) //若无，refresh_token会有UserDetailsService is require错误
                .tokenStore(tokenStore());
    }


    /**
     * 安全配置
     * @param security
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        /**
         * 配置oauth2服务跨域
         */
        CorsConfigurationSource source = new CorsConfigurationSource() {
            @Override
            public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                CorsConfiguration corsConfiguration = new CorsConfiguration();
                corsConfiguration.addAllowedHeader("*");
                corsConfiguration.addAllowedOrigin(request.getHeader(HttpHeaders.ORIGIN));
                corsConfiguration.addAllowedMethod("*");
                corsConfiguration.setAllowCredentials(true);
                corsConfiguration.setMaxAge(3000L);
                return corsConfiguration;
            }
        };
        security.passwordEncoder(passwordEncoder);
        security.allowFormAuthenticationForClients()
                .checkTokenAccess("isAuthenticated()")
                .addTokenEndpointAuthenticationFilter(new CorsFilter(source));
    }




}
