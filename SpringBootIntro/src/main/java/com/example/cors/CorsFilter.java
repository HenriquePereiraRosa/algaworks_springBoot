/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.cors;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    private String allowedOrigin = "http://localhost:8000"; //TODO: Configure to different environments.
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        
        HttpServletRequest requestLocal = (HttpServletRequest) request;
        HttpServletResponse responseLocal = (HttpServletResponse) response;
        
        if("OPTIONS".equals(requestLocal.getMethod()) && allowedOrigin.equals(requestLocal.getHeader("Origin"))) {
            responseLocal.setHeader("Access-Control-Allow-Methods", "POST, GET, DELETE, PUT, OPTIONS");
            responseLocal.setHeader("Access-Control-Max-Age", "3600");
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
