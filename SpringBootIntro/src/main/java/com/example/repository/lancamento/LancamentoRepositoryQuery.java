/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.repository.lancamento;

import com.example.model.Lancamento;
import com.example.repository.filter.LancamentoFilter;
import com.example.repository.projection.ResumoLancamento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author user
 */
public interface LancamentoRepositoryQuery {
    
    public Page<Lancamento> search(LancamentoFilter lancamentoFilter, Pageable pageable);
    
    public Page<ResumoLancamento> resume(LancamentoFilter lancamentoFilter, Pageable pageable);
}
