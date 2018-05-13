/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.service;

import com.example.model.Pessoa;
import com.example.repository.PessoaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

/**
 *
 * @author User
 */
@Service
public class PessoaService {
    
    @Autowired
    PessoaRepository pessoaRepository;
    
    public Pessoa atualizar(Long id, Pessoa pessoa){
        Pessoa pessoaInDB = buscarPessoaPorId(id);
        BeanUtils.copyProperties(pessoa, pessoaInDB, "id");
        return pessoaRepository.save(pessoaInDB);
    }

    public void atualizarPropriedadeAtivo(Long id, Boolean ativo) {
        Pessoa pessoaInDB = buscarPessoaPorId(id);
        pessoaInDB.setAtivo(ativo);
        pessoaRepository.save(pessoaInDB);
    }

    private Pessoa buscarPessoaPorId(Long id) {
        Pessoa pessoa = pessoaRepository.findOne(id);
        if(pessoa == null)
            throw new EmptyResultDataAccessException(1);
        return pessoa;
    }
    
    
}
