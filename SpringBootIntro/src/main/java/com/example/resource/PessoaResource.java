/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.resource;

import com.example.event.RecursoCriadoEvent;
import com.example.model.Pessoa;
import com.example.repository.PessoaRepository;
import java.net.URI;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 *
 * @author user
 */
@RestController
@RequestMapping("/pessoa")
public class PessoaResource {
    
    @Autowired
    private PessoaRepository pessoaRepository;
    
    @Autowired
    private ApplicationEventPublisher publisher;
    
    @GetMapping
    public List<Pessoa> listar(){
        return pessoaRepository.findAll();
    }
    
    // Save on db method
    @PostMapping
    public ResponseEntity<Pessoa> saveOnDb( @Valid @RequestBody Pessoa pessoa, HttpServletResponse response){
        Pessoa objectSaved = pessoaRepository.save( pessoa );
        
        publisher.publishEvent( new RecursoCriadoEvent( this, response, objectSaved.getId())); // Create an event when an object is saved
        
        return ResponseEntity.status(HttpStatus.CREATED).body( objectSaved );
    }
    
    
    // SerchByName
    @GetMapping("/searchbyname/{nome}")
    public ResponseEntity<Pessoa> serchByNome( @PathVariable String nome ) {
        Pessoa pessoa = pessoaRepository.findByNomeContaining( nome );
        return ( pessoa == null )? ResponseEntity.notFound().build() : ResponseEntity.ok( pessoa );
    }
}
