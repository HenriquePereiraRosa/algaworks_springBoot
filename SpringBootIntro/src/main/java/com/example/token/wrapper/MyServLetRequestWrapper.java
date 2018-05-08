/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.token.wrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.Map;
import org.apache.catalina.util.ParameterMap;
/**
 *
 * @author user
 */
public class MyServLetRequestWrapper extends HttpServletRequestWrapper {
    
    private String refreshToken;

    public MyServLetRequestWrapper(HttpServletRequest requestLocal, String refreshToken) {
        super(requestLocal);
        this.refreshToken = refreshToken;
    }
    
    @Override
    public Map<String, String[]> getParameterMap() {
        ParameterMap<String, String[]> map = new ParameterMap<>(getRequest().getParameterMap());
        map.put("refresh_token", new String[] { refreshToken });
        map.setLocked(true);
        return map;
    }
    
    
}
