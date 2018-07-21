/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.repository;

import com.example.model.Lancamento;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.repository.lancamento.LancamentoRepositoryQuery;

/**
 *
 * @author user
 */
public interface LancamentoRepository extends JpaRepository<Lancamento, Long>, LancamentoRepositoryQuery {
    
    public Lancamento getOne(Long id);
    
}
