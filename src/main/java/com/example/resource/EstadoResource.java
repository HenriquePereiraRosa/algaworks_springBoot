/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.resource;

import com.example.model.Estado;
import com.example.repository.EstadoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author user
 */
        
@RestController
@RequestMapping("/estados")
public class EstadoResource {
    
    @Autowired
    private EstadoRepository estadoRepository;
    
//    @Autowired
//    private LancamentoService lancamentoService;
        
    @Autowired
    private ApplicationEventPublisher publisher;
    
    @GetMapping
    @CrossOrigin
    @PreAuthorize("isAuthenticated()")
    public List<Estado> search() {
        return estadoRepository.findAll();
    } 
    
    // SerchById
    @GetMapping("/{id}")
    @CrossOrigin
    public ResponseEntity<? extends Object> searchById( @PathVariable Long id ) {
        Estado estado = estadoRepository.getOne(id);
        return (estado == null)? ResponseEntity.notFound().build() : ResponseEntity.ok(estado);
    }
    
        
}
