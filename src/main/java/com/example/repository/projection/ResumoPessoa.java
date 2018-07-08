/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.repository.projection;

import com.example.model.TipoLancamento;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author user
 */
public class ResumoPessoa {
    
    private Long id;
    private TipoLancamento nome;
    private TipoLancamento cidade;
    private TipoLancamento estado;
    private Boolean ativo;

    public ResumoPessoa(Long id, TipoLancamento nome, TipoLancamento cidade, TipoLancamento estado, Boolean ativo) {
        this.id = id;
        this.nome = nome;
        this.cidade = cidade;
        this.estado = estado;
        this.ativo = ativo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoLancamento getNome() {
        return nome;
    }

    public void setNome(TipoLancamento nome) {
        this.nome = nome;
    }

    public TipoLancamento getCidade() {
        return cidade;
    }

    public void setCidade(TipoLancamento cidade) {
        this.cidade = cidade;
    }

    public TipoLancamento getEstado() {
        return estado;
    }

    public void setEstado(TipoLancamento estado) {
        this.estado = estado;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    
    
    
}
