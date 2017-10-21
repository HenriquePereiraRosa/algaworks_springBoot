/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.resource;

import com.example.model.Personagem;
import com.example.repository.PersonagemRepository;
import static com.fasterxml.jackson.databind.jsonFormatVisitors.JsonValueFormat.URI;
import java.net.URI;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
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
 * @author User
 */

@RestController
@RequestMapping("/personagem")
public class PersonagemResource {
    
    @Autowired
    private PersonagemRepository personagemRepository;
    
    @GetMapping
    public ResponseEntity<?> listar(){
        List<Personagem> personagens = personagemRepository.findAll();
        return personagens.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok( personagens );
    }
        
    @PostMapping
    public ResponseEntity<Personagem> saveOnDb( @RequestBody Personagem personagem, HttpServletResponse response){
        Personagem personagemSaved = personagemRepository.save( personagem );
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}")
                .buildAndExpand( personagemSaved.getCodigo() ).toUri();
        response.setHeader( "Location", uri.toASCIIString() );
        return ResponseEntity.created( uri ).body( personagemSaved );
    }
    
    @GetMapping( "/{codigo}")
    public ResponseEntity<Personagem> serchByCode( @PathVariable Long codigo ) {
        Personagem personagem = personagemRepository.findOne( codigo );
        return ( personagem == null )? ResponseEntity.notFound().build() : ResponseEntity.ok( personagem );
    }
    
}
