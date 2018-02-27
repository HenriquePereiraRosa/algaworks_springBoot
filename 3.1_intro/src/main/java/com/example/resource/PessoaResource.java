/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.resource;

import com.example.model.Personagem;
import com.example.model.Pessoa;
import com.example.repository.PessoaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author user
 */
@RestController
@RequestMapping("/pessoa")
public class PessoaResource {
    
    @Autowired
    private PessoaRepository pessoaRepository;
    
    @GetMapping
    public List<Pessoa> listar(){
        return pessoaRepository.findAll();
    }
    
    // SerchByName
    @GetMapping("/searchbyname/{nome}")
    public ResponseEntity<Pessoa> serchByNome( @PathVariable String nome ) {
        Pessoa pessoa = pessoaRepository.findByNomeContaining( nome );
        return ( pessoa == null )? ResponseEntity.notFound().build() : ResponseEntity.ok( pessoa );
    }
}
