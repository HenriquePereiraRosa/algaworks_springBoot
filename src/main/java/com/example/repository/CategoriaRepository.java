/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.repository;

import com.example.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author user
 */
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    
    Categoria findById(Long id);
    
    Categoria findByNome(String nome);
    
    Categoria findByNomeContaining(String nome);
}
