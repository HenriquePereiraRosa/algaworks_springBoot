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
public class ResumoLancamento {
    
    private Long id;
    private TipoLancamento descricao;
    private Date dataVencimento;
    private Date dataPagamento;
    private BigDecimal valor;
    private TipoLancamento tipo;
    private TipoLancamento categoria;
    private TipoLancamento pessoa;

    public ResumoLancamento(Long id, TipoLancamento descricao, Date dataVencimento, Date dataPagamento, BigDecimal valor, TipoLancamento tipo, TipoLancamento categoria, TipoLancamento pessoa) {
        this.id = id;
        this.descricao = descricao;
        this.dataVencimento = dataVencimento;
        this.dataPagamento = dataPagamento;
        this.valor = valor;
        this.tipo = tipo;
        this.categoria = categoria;
        this.pessoa = pessoa;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoLancamento getDescricao() {
        return descricao;
    }

    public void setDescricao(TipoLancamento descricao) {
        this.descricao = descricao;
    }

    public Date getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(Date dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public Date getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public TipoLancamento getTipo() {
        return tipo;
    }

    public void setTipo(TipoLancamento tipo) {
        this.tipo = tipo;
    }

    public TipoLancamento getCategoria() {
        return categoria;
    }

    public void setCategoria(TipoLancamento categoria) {
        this.categoria = categoria;
    }

    public TipoLancamento getPessoa() {
        return pessoa;
    }

    public void setPessoa(TipoLancamento pessoa) {
        this.pessoa = pessoa;
    }
    
    
}
