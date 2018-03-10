/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.resource;

import com.example.event.RecursoCriadoEvent;
import com.example.model.Personagem;
import com.example.repository.PersonagemRepository;
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
/**
 *
 * @author User
 */

@RestController
@RequestMapping("/personagem")
public class PersonagemResource {
    
    @Autowired
    private PersonagemRepository personagemRepository;
    
    @Autowired
    private ApplicationEventPublisher publisher;
    
    @GetMapping
    public ResponseEntity<?> listar(){
        List<Personagem> personagens = personagemRepository.findAll();
        return personagens.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok( personagens );
    }
        
    @PostMapping
    public ResponseEntity<Personagem> saveOnDb( @Valid @RequestBody Personagem personagem, HttpServletResponse response){
        Personagem objectSaved = personagemRepository.save( personagem );
        
        publisher.publishEvent( new RecursoCriadoEvent( this, response, objectSaved.getId())); // Create an event when an object is saved
        
        return ResponseEntity.status(HttpStatus.CREATED).body( objectSaved );
    }
    
    //SerchByCod
    @GetMapping("/{id}")
    public ResponseEntity<Personagem> serchByCode( @PathVariable Long id ) {
        Personagem personagem = personagemRepository.findOne( id );
        return ( personagem == null )? ResponseEntity.notFound().build() : ResponseEntity.ok( personagem );
    }
    
    // SerchByName
    @GetMapping("/searchbyname/{nome}")
    public ResponseEntity<Personagem> serchByNome( @PathVariable String nome ) {
        Personagem personagem = personagemRepository.findByNome( nome );
        return ( personagem == null )? ResponseEntity.notFound().build() : ResponseEntity.ok( personagem );
    }
    
}
