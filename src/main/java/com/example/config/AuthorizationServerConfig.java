/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.config;

import com.example.config.token.CustomTokenEnhancer;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.expression.OAuth2MethodSecurityExpressionHandler;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Profile("oauth")
@Configuration
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private UserDetailsService userDetailsService;
    
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
            .withClient("angular")
            .secret("$2a$10$Y1Irb3xtvkJS6Lmlkcp8SO82qYSF1fKfmLWorCf7N0EXdooE4C0hq")  // password of the client application: angular
            .scopes("read", "write") // Limits the client app access
            .authorizedGrantTypes("password", "refresh_token")
            .accessTokenValiditySeconds(2000) // TODO: Reconfigure this timing
            .refreshTokenValiditySeconds(3600 * 24)  // One day
        .and()
            .withClient("mobile")
            .secret("$2a$10$hokxf3JbWij5Fndcvzd/R.1eGliAUQ/oc7Ayx9r5cdLPO2wg.zWuS")  // password of the client application: mobile
            .scopes("read") // Limits the client app access
            .authorizedGrantTypes("password", "refresh_token")
            .accessTokenValiditySeconds(2000) // TODO: Reconfigure this timing
            .refreshTokenValiditySeconds(3600 * 24);  // One day
    }
    
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        TokenEnhancerChain tokenAnhacerChain = new TokenEnhancerChain();
        tokenAnhacerChain.setTokenEnhancers(Arrays.asList(tokenEnhancer(), accessTokenConverter()));
        
        endpoints.
                tokenStore(tokenStore())
                .tokenEnhancer(tokenAnhacerChain)
                .reuseRefreshTokens(false)
                .userDetailsService(userDetailsService)
                .authenticationManager(authenticationManager);
    }
    
    @Bean            
    public TokenStore tokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter accessTokenConverter = new JwtAccessTokenConverter();
        accessTokenConverter.setSigningKey("admin");
        return accessTokenConverter;
    }
    
    @Bean
    public MethodSecurityExpressionHandler createExpressionHandler() {
        return new OAuth2MethodSecurityExpressionHandler();
    }
    
    @Bean
    public TokenEnhancer tokenEnhancer() {
        return new CustomTokenEnhancer();
    }

}

    