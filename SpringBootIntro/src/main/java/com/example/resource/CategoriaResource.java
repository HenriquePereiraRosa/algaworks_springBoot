/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.resource;

import com.example.event.RecursoCriadoEvent;
import com.example.model.Categoria;
import com.example.repository.CategoriaRepository;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author user
 */
@RestController
@RequestMapping("/categoria")
public class CategoriaResource {
    
    @Autowired
    private CategoriaRepository categoriaRepository;
    
    @Autowired
    private ApplicationEventPublisher publisher;
    
    @GetMapping
    public List<Categoria> listar() {
        return categoriaRepository.findAll();
    }
    
    // Save on db method
    @PostMapping
    public ResponseEntity<Categoria> saveOnDb( @Valid @RequestBody Categoria categoria, HttpServletResponse response){
        Categoria objectSaved = categoriaRepository.save( categoria );
        
        publisher.publishEvent( new RecursoCriadoEvent( this, response, objectSaved.getId())); // Create an event when an object is saved
        
        return ResponseEntity.status(HttpStatus.CREATED).body( objectSaved );
    }
}
