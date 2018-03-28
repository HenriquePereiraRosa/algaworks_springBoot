/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.service;

import com.example.model.Lancamento;
import com.example.model.Pessoa;
import com.example.repository.LancamentoRepository;
import com.example.repository.PessoaRepository;
import com.example.service.exception.PessoaInexistenteOuInativaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

/**
 *
 * @author User
 */
@Service
public class LancamentoService {
    
    @Autowired
    PessoaRepository pessoaRepository;
    
    @Autowired
    private LancamentoRepository lancamentoRepository;
    
    public Lancamento salvar(Lancamento lancamento) {
        Pessoa pessoa = pessoaRepository.findOne(lancamento.getPessoa().getId());
        if((pessoa == null) || pessoa.isInativo())
            throw new PessoaInexistenteOuInativaException();
        return lancamentoRepository.save(lancamento);
    }

    private Lancamento buscarLancamentoPorId(Long id) {
        Lancamento lancamento = lancamentoRepository.findOne(id);
        if(lancamento == null)
            throw new EmptyResultDataAccessException(1);
        return lancamento;
    }
    
    
}
