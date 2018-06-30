/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.token;

import com.example.token.wrapper.MyServLetRequestWrapper;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RefreshTokenCookiePreProcessorFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        
        HttpServletRequest requestLocal = (HttpServletRequest) request;
        
        if("/oauth/token".equalsIgnoreCase(requestLocal.getRequestURI())
                && "refresh_token".equals(requestLocal.getParameter("grant_type"))
                && (requestLocal.getCookies() != null)) {
            for(Cookie cookie : requestLocal.getCookies()){
                String refreshToken = cookie.getValue();
                requestLocal = new MyServLetRequestWrapper(requestLocal, refreshToken);
            }            
        }
        
        chain.doFilter(requestLocal, response);
    }
    

    @Override
    public void destroy() {
        //To change body of generated methods, choose Tools | Templates.
    }

   
    
}
