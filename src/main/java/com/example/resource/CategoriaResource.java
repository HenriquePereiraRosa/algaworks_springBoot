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
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
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
    
//    @Autowired
//    private CategoriaService categoriaService; ToDo
    
    @Autowired
    private ApplicationEventPublisher publisher;
    
    @GetMapping
    public List<Categoria> listar(){
        return categoriaRepository.findAll();
    }
    
    // Save on db method
    @CrossOrigin
    @PostMapping
    // Verify the user ROLE acess and appClient scopes 
    @PreAuthorize("hasAuthority('ROLE_CADASTRAR_CATEGORIA') and #oauth2.hasScope('write')")
    public ResponseEntity<Categoria> saveOnDb( @Valid @RequestBody Categoria categoria, HttpServletResponse response){
        Categoria objectSaved = categoriaRepository.save( categoria );
        
        // Create an event when an object is saved
        publisher.publishEvent( new RecursoCriadoEvent( this, response, objectSaved.getId())); 
        
        return ResponseEntity.status(HttpStatus.CREATED).body( objectSaved );
    }
    
    // SerchById
    @CrossOrigin
    @GetMapping("/{id}")
    public ResponseEntity<Categoria> searchByNome( @PathVariable Long id ) {
        Categoria categoria = categoriaRepository.getOne(id);
        return ( categoria == null )? ResponseEntity.notFound().build() : ResponseEntity.ok( categoria );
    }
    
    // SerchByName
    @CrossOrigin
    @GetMapping("/searchbyname/{nome}")
    public ResponseEntity<Categoria> searchByNome( @PathVariable String nome ) {
        Categoria categoria = categoriaRepository.findByNomeContaining( nome );
        return ( categoria == null )? ResponseEntity.notFound().build() : ResponseEntity.ok( categoria );
    }
    
    // Deletion By Id
    @CrossOrigin
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long id){
        categoriaRepository.deleteById(id);
    }
    
    // Deletion By Name
    @CrossOrigin
    @DeleteMapping("/deletebyname/{nome}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable String nome){
        Categoria pessoa = categoriaRepository.findByNome(nome);
        categoriaRepository.delete(pessoa);
    }
    
    // Update a entire resourse and get it back to confirm
    @CrossOrigin
    @PutMapping("/{id}")
    public ResponseEntity<Categoria> update(@PathVariable Long id, @Valid @RequestBody Categoria categoria){
        Categoria categoriaSavedInDB = categoriaRepository.getOne(id);
        BeanUtils.copyProperties(categoria, categoriaSavedInDB, "id");
        categoriaRepository.save(categoriaSavedInDB);
        return ResponseEntity.ok(categoriaSavedInDB);        
    }
}
