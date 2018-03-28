/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.event;

import javax.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationEvent;

/**
 *
 * @author user
 */
public class RecursoCriadoEvent extends ApplicationEvent {

    private static final long serialVersionUID = 1L;
    
    private final HttpServletResponse response;
    private final Long id;
    
    public RecursoCriadoEvent(Object source, HttpServletResponse response, Long id) {
        super(source);
        this.response = response;
        this.id = id;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public Long getId() {
        return id;
    }
    
    
}
