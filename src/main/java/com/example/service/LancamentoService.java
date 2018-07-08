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
import org.springframework.beans.BeanUtils;
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
        Pessoa pessoa = pessoaRepository.getOne(lancamento.getPessoa().getId());
        if((pessoa == null) || pessoa.isInativo())
            throw new PessoaInexistenteOuInativaException();
        return lancamentoRepository.save(lancamento);
    }

        
    public Lancamento update(Long id, Lancamento lancamento) {
        Lancamento lancamentoFromDb = buscarLancamentoPorId(id);
        if (!lancamento.getPessoa().equals(lancamentoFromDb.getPessoa())) {
                doValidation(lancamento);
        }
        BeanUtils.copyProperties(lancamento, lancamentoFromDb, "id");
        return lancamentoRepository.save(lancamentoFromDb);
    }
    
    
    private void doValidation(Lancamento lancamento) {
        Pessoa pessoa = null;
        if (lancamento.getPessoa().getId() != null) {
            pessoa = pessoaRepository.getOne(lancamento.getPessoa().getId());
        }

        if (pessoa == null || pessoa.isInativo()) {
            throw new PessoaInexistenteOuInativaException();
        }
    }
    
    
    private Lancamento buscarLancamentoPorId(Long id) {
        Lancamento lancamento = lancamentoRepository.getOne(id);
        if(lancamento == null)
            throw new EmptyResultDataAccessException(1);
        return lancamento;
    }
    
}
