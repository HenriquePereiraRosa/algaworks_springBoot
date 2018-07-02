/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.cors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.example.config.property.ApiProperty;

@Configuration
public class CorsConfig {
	
    @Autowired
    private ApiProperty apiProperty;

    @Bean
    public FilterRegistrationBean corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        CorsConfiguration authConfig = new CorsConfiguration();
        authConfig.setAllowCredentials(true);
        for(String origin : apiProperty.getAllowedOrigins()) {
            authConfig.addAllowedOrigin(origin); 

            // DEBUG
            System.out.println("");
            System.out.println("");
            System.out.println("");
            System.out.println("");
            System.out.println("");
            System.out.println("=================================");
            System.out.println("ORIGEM ADICIONDADA: " + origin);
            System.out.println("==================================");
            System.out.println("");
            System.out.println("");
            System.out.println("");
            System.out.println("");
            System.out.println("");
            System.out.println("");
        }
        authConfig.addAllowedHeader("Authorization");
        authConfig.addAllowedHeader("Content-Type");
        authConfig.addAllowedHeader("Accept");
        authConfig.addAllowedMethod("POST");
        authConfig.addAllowedMethod("GET");
        authConfig.addAllowedMethod("DELETE");
        authConfig.addAllowedMethod("PUT");
        authConfig.addAllowedMethod("OPTIONS");
        authConfig.setMaxAge(3600L);
        // source.registerCorsConfiguration("/oauth/token", configAutenticacao);
        source.registerCorsConfiguration("/**", authConfig); // Global para todas as URLs da aplicação

        //DEBUG
        System.out.println(authConfig.getAllowedOrigins());


        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return bean;
    }

}