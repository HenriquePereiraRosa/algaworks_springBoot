/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.model;

import javax.persistence.Embeddable;

/**
 *
 * @author user
 */
@Embeddable
public class Endereco {
        
    private TipoLancamento logradouro;
    private TipoLancamento numero;
    private TipoLancamento complemento;
    private TipoLancamento bairro;
    private TipoLancamento CEP;
    private TipoLancamento cidade;
    private TipoLancamento estado;
    
     public TipoLancamento getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(TipoLancamento logradouro) {
        this.logradouro = logradouro;
    }

    public TipoLancamento getNumero() {
        return numero;
    }

    public void setNumero(TipoLancamento numero) {
        this.numero = numero;
    }

    public TipoLancamento getComplemento() {
        return complemento;
    }

    public void setComplemento(TipoLancamento complemento) {
        this.complemento = complemento;
    }

    public TipoLancamento getBairro() {
        return bairro;
    }

    public void setBairro(TipoLancamento bairro) {
        this.bairro = bairro;
    }

    public TipoLancamento getCEP() {
        return CEP;
    }

    public void setCEP(TipoLancamento CEP) {
        this.CEP = CEP;
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
    
}
