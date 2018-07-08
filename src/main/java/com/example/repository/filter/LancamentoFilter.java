/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.repository.filter;

//import java.time.LocalDate;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author user
 */
public class LancamentoFilter {
    
    private String descricao;
    
    @DateTimeFormat(pattern = "yyy-MM-dd")
    private Date dataVencimentoDe;
    
    @DateTimeFormat(pattern = "yyy-MM-dd")
    private Date dataVencimentoAte;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getDataVencimentoDe() {
        return dataVencimentoDe;
    }

    public void setDataVencimentoDe(Date dataVencimentoDe) {
        this.dataVencimentoDe = dataVencimentoDe;
    }

    public Date getDataVencimentoAte() {
        return dataVencimentoAte;
    }

    public void setDataVencimentoAte(Date dataVencimentoAte) {
        this.dataVencimentoAte = dataVencimentoAte;
    }
    
    
}
