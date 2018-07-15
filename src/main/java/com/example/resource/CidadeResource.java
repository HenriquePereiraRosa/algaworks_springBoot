/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.resource;

import com.example.model.Cidade;
import com.example.repository.CidadeRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author user
 */
        
@RestController
@RequestMapping("/cidades")
public class CidadeResource {
    
    @Autowired
    private CidadeRepository cidadeRepository;
    
//    @Autowired
//    private LancamentoService lancamentoService;
        
    @Autowired
    private ApplicationEventPublisher publisher;
    
    @GetMapping
    @CrossOrigin
    public List<Cidade> search(@RequestParam Long estado) {
        return cidadeRepository.findByEstadoId(estado);
    } 
    
    // SerchById
    @GetMapping("/{id}")
    @CrossOrigin
    public ResponseEntity<? extends Object> searchById( @PathVariable Long id ) {
        Cidade cidade = cidadeRepository.getOne(id);
        return (cidade == null)? ResponseEntity.notFound().build() : ResponseEntity.ok(cidade);
    }
    
        
}
