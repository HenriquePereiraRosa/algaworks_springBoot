/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.resource;

import com.example.model.Personagem;
import com.example.repository.PersonagemRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
    
    @GetMapping
    public ResponseEntity<?> listar(){
        List<Personagem> personagens = personagemRepository.findAll();
        return personagens.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok( personagens );
    }
    
//    @PostMapping
//    public List<Personagem> listarPost(){
//        return personagemRepository.findAll();
//    }
    
    @PostMapping
    public String criar(){
        return personagemRepository.save( entity );
    }
    
}
