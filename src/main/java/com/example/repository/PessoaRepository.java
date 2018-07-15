/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.repository;

import com.example.model.Pessoa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 *
 * @author user
 */
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    
    public Pessoa findByNome(String nome);
    
    public Pessoa findByNomeContaining(String nome);
    
    public Page<Pessoa> findByNomeContaining(String nome, Pageable pageable);
    
    public Pessoa findByAtivo(boolean ativo);
    
    public Pessoa findByEndereco(String endereco);
  
}
