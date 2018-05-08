/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.token;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@ControllerAdvice
public class RefreshTokenPostProcessor implements ResponseBodyAdvice<OAuth2AccessToken> {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return returnType.getMethod().getName().equals("postAccessToken");
    }
    

    @Override
    public OAuth2AccessToken beforeBodyWrite(OAuth2AccessToken body, MethodParameter returnType,
            MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType,
            ServerHttpRequest request, ServerHttpResponse response) {
        
        HttpServletRequest requestLocal = ((ServletServerHttpRequest) request).getServletRequest();
        HttpServletResponse responseLocal = ((ServletServerHttpResponse) request).getServletResponse();
        
        DefaultOAuth2AccessToken token = (DefaultOAuth2AccessToken) body;
        
        String refreshToken = body.getRefreshToken().getValue();
        putRefreshTokenInCookie(refreshToken, requestLocal, responseLocal);
        removeRefreshTokenfromBody(token);
        System.out.println("TOKEN:" +token);
        System.out.println("BODY: " + body);
        return body;
    }

    
    /** 
     * Method that add a refresh_token in a cookie

     *   @return void
     */
    private void putRefreshTokenInCookie(String refreshToken, HttpServletRequest requestLocal, HttpServletResponse responseLocal) {
        Cookie refreshTokenCookie = new Cookie("refreshToken", refreshToken);
        refreshTokenCookie.setHttpOnly(true);
        refreshTokenCookie.setSecure(false); // TODO: change to true in production
        refreshTokenCookie.setPath(requestLocal.getContextPath() + "/oauth/token");
        refreshTokenCookie.setMaxAge(2592000);
        
        responseLocal.addCookie(refreshTokenCookie);
    }

    
    /** 
     * Method that remove a refresh_token from a body to make this refresh_token
     * unaccessible for the client application
     * 
     *   @return void
     */
    private void removeRefreshTokenfromBody(DefaultOAuth2AccessToken token) {
        token.setRefreshToken(null);
    }
    
}
