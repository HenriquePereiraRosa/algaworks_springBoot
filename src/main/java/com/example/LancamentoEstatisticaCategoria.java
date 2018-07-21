/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example;

import com.example.model.Categoria;
import java.math.BigDecimal;

/**
 *
 * @author user
 */
public class LancamentoEstatisticaCategoria {
    
    private Categoria categoria;
    private BigDecimal total;

    public LancamentoEstatisticaCategoria(Categoria categoria, BigDecimal total) {
        this.categoria = categoria;
        this.total = total;
    }
        
    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
    
    
}
