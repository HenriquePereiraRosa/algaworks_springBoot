/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.resource;

import com.example.event.RecursoCriadoEvent;
import com.example.model.Pessoa;
import com.example.repository.PessoaRepository;
import com.example.service.PessoaService;
import java.util.Optional;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author user
 */

@RestController
@RequestMapping("/pessoas")
public class PessoaResource {
    
    @Autowired
    private PessoaRepository pessoaRepository;
    
    @Autowired
    private PessoaService pessoaService;
    
    @Autowired
    private ApplicationEventPublisher publisher;
    
    
    // Get Page of pessoas method
    @GetMapping
    @CrossOrigin
    @PreAuthorize("hasAuthority('ROLE_PESQUISAR_PESSOA')")
    public Page<Pessoa> search(@RequestParam(required = false, defaultValue = "%") String nome, Pageable pageable) {
        return pessoaRepository.findByNomeContaining(nome, pageable);
    } 
    
    // SerchById
    @GetMapping("/{id}")
    @CrossOrigin
    @PreAuthorize("hasAuthority('ROLE_PESQUISAR_PESSOA') and #oauth2.hasScope('read')")
    public ResponseEntity<Pessoa> findById(@PathVariable Long id) {
        Optional<Pessoa> pessoa = pessoaRepository.findById(id);
        return pessoa.isPresent() ? ResponseEntity.ok(pessoa.get()) : ResponseEntity.notFound().build();
    }
        
    // SerchByName
    @GetMapping("/searchbyname/{nome}")
    @CrossOrigin
    public ResponseEntity<Pessoa> searchByNome( @PathVariable String nome ) {
        Pessoa pessoa = pessoaRepository.findByNomeContaining( nome );
        return ( pessoa == null )? ResponseEntity.notFound().build() : ResponseEntity.ok( pessoa );
    }
    
    
    // Save on db method
    @PostMapping
    @CrossOrigin
    public ResponseEntity<Pessoa> saveOnDb( @Valid @RequestBody Pessoa pessoa, HttpServletResponse response){
        Pessoa objectSaved = pessoaRepository.save( pessoa );        
        publisher.publishEvent( new RecursoCriadoEvent( this, response, objectSaved.getId())); // Create an event when an object is saved        
        return ResponseEntity.status(HttpStatus.CREATED).body( objectSaved );
    }
    
    // Deletion By Id
    @DeleteMapping("/{id}")
    @CrossOrigin
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long id){
        pessoaRepository.deleteById(id);
    }
    
    // Deletion By Name
    @DeleteMapping("/deletebyname/{nome}")
    @CrossOrigin
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable String nome){
        Pessoa pessoa = pessoaRepository.findByNome(nome);
        pessoaRepository.delete(pessoa);
    }
    
    // Update a entire resourse and get it back to confirm
    @PutMapping("/{id}")
    @CrossOrigin
    public ResponseEntity<Pessoa> update(@PathVariable Long id, @Valid @RequestBody Pessoa pessoa){
        Pessoa pessoaInDB = pessoaRepository.getOne(id);
        BeanUtils.copyProperties(pessoa, pessoaInDB, "id");
        pessoaRepository.save(pessoaInDB);
        return ResponseEntity.ok(pessoaInDB);        
    }
    
    // Update partially ativo property
    @PutMapping("/{id}/ativo")
    @CrossOrigin
    public ResponseEntity<Pessoa> updatePartiallyAtivo(@PathVariable Long id, @RequestBody Boolean ativo){
        pessoaService.atualizarPropriedadeAtivo(id, ativo);
        Pessoa pessoaInDB = pessoaRepository.getOne(id);
        return ResponseEntity.ok(pessoaInDB);
    }

    // Update partially a resource. ToDo: Not working!!!
    @PatchMapping("/{id}")
    @CrossOrigin
    public ResponseEntity<Pessoa> updatePartially(@PathVariable Long id, @Valid @RequestBody Pessoa pessoa){
        Pessoa pessoaInDB = pessoaService.atualizar(id, pessoa);
        return ResponseEntity.ok(pessoaInDB);        
    }
}
