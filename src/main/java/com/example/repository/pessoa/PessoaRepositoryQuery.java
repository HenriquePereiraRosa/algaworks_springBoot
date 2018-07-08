/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.repository.pessoa;

import com.example.model.Pessoa;
import com.example.repository.filter.PessoaFilter;
import com.example.repository.projection.ResumoPessoa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author user
 */
public interface PessoaRepositoryQuery {
    
    public Page<Pessoa> search(PessoaFilter filter, Pageable pageable);
    
    public Page<ResumoPessoa> resume(PessoaFilter filter, Pageable pageable);
}
