/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.cors;

import com.example.config.property.ApiProperty;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 *
 * @author user
 */

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CorsFilter implements Filter {

    @Autowired
    private ApiProperty apiProperty;
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        
        HttpServletRequest requestLocal = (HttpServletRequest) request;
        HttpServletResponse responseLocal = (HttpServletResponse) response;
        
        responseLocal.setHeader("Access-Control-Allow-Origin", apiProperty.getAllowedOrigin());
        responseLocal.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        responseLocal.setHeader("Access-Control-Max-Age", "3600");
        responseLocal.setHeader("Access-Control-Allow-Headers", "authorization, content-type, accept");
        responseLocal.addHeader("Access-Control-Allow-Credentials", "true");
        
        if("OPTIONS".equals(requestLocal.getMethod())) {
            responseLocal.setStatus(HttpServletResponse.SC_OK);
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
        //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
