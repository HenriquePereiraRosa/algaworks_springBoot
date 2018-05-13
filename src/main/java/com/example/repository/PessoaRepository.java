/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.repository;

import com.example.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 *
 * @author user
 */
public interface PessoaRepository extends JpaRepository< Pessoa, Long > {
    
    Pessoa findByNome(String nome);
    
    Pessoa findByNomeContaining(String nome);
    
    Pessoa findByAtivo(boolean ativo);
    
    Pessoa findByEndereco(String endereco);
  
}
