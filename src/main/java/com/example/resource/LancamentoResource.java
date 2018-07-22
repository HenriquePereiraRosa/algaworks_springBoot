/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.resource;

import com.example.dto.LancamentoEstatisticaCategoria;
import com.example.dto.LancamentoEstatisticaDia;
import com.example.event.RecursoCriadoEvent;
import com.example.model.Lancamento;
import com.example.repository.LancamentoRepository;
import com.example.repository.filter.LancamentoFilter;
import com.example.repository.projection.ResumoLancamento;
import com.example.service.LancamentoService;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
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
@RequestMapping("/lancamentos")
public class LancamentoResource {
    
    @Autowired
    private LancamentoRepository lancamentoRepository;
    
    @Autowired
    private LancamentoService lancamentoService;
        
    @Autowired
    private ApplicationEventPublisher publisher;
    
    @GetMapping
    @CrossOrigin
    public Page<Lancamento> search(LancamentoFilter filter, Pageable pageable) {
        return lancamentoRepository.search(filter, pageable);
    } 
    
    
    @GetMapping(params = "resumo") // If there is an param called "resumo" in the requisition, then call this method.
    @CrossOrigin
    public Page<ResumoLancamento> resume(LancamentoFilter filter, Pageable pageable) {
        return lancamentoRepository.resume(filter, pageable);
    }
    
    @GetMapping("estatisticas/por-categoria")
    @CrossOrigin
    public List<LancamentoEstatisticaCategoria> porCategoria() {
        return this.lancamentoRepository.porCategoria(LocalDate.of(2018, 6, 29)); // LocalDate.now());
    }
    
    // TODO: To implement & do Tests!!!!
    @GetMapping("estatisticas/por-categoria/{localDate}")
    @CrossOrigin
    public List<LancamentoEstatisticaCategoria> porCategoria(@PathVariable String stringDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
        return this.lancamentoRepository.porCategoria(LocalDate.parse(stringDate, formatter));
    }
    
    @GetMapping("estatisticas/por-dia")
    @CrossOrigin
    public List<LancamentoEstatisticaDia> porDia() {
        return this.lancamentoRepository.porDia(LocalDate.of(2018, 3, 1)); // LocalDate.now());
    }
    
    // Save on db method
    @PostMapping
    @CrossOrigin
    public ResponseEntity<Lancamento> saveOnDb( @Valid @RequestBody Lancamento lancamento, HttpServletResponse response){
        Lancamento objectSaved = lancamentoRepository.save(lancamento);
        
        publisher.publishEvent(new RecursoCriadoEvent(this, response, objectSaved.getId())); // Create an event when an object is saved
        
        return ResponseEntity.status(HttpStatus.CREATED).body(objectSaved);
    }
    
    // SerchById
    @GetMapping("/{id}")
    @CrossOrigin
    public ResponseEntity<? extends Object> searchById( @PathVariable Long id ) {
        Optional<Lancamento> lancamento = lancamentoRepository.findById(id);
        return lancamento.isPresent() ? ResponseEntity.ok(lancamento) : ResponseEntity.notFound().build();
    }
    
    
     // Deletion By Name
    @DeleteMapping("/{id}")
    @CrossOrigin
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long id){
        lancamentoRepository.deleteById(id);
    }
    
    // Update resourse
    @PutMapping("/{id}")
    @CrossOrigin
    @PreAuthorize("hasAuthority('ROLE_CADASTRAR_LANCAMENTO')")
    public ResponseEntity<Lancamento> atualizar(@PathVariable Long id, @Valid @RequestBody Lancamento lancamento) {
        try {
            Lancamento lancamentoFromDb = lancamentoService.update(id, lancamento);
            return ResponseEntity.ok(lancamentoFromDb);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.notFound().build();
        }
    }
    
}
