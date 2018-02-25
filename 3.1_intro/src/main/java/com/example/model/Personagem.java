/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author User
 */

@Entity
@Table(name = "personagem")
public class Personagem {
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPersonagem;
    
    @NotNull
    @Size( min = 3, max = 20 )
    private String nome;

    public Long getCodigo() {
        return idPersonagem;
    }

    public void setCodigo(Long codigo) {
        this.idPersonagem = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    @Override
    public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((idPersonagem == null) ? 0 : idPersonagem.hashCode());
            return result;
    }

    @Override
    public boolean equals(Object obj) {
            if (this == obj)
                    return true;
            if (obj == null)
                    return false;
            if (getClass() != obj.getClass())
                    return false;
            Personagem other = (Personagem) obj;
            if (idPersonagem == null) {
                    if (other.idPersonagem != null)
                            return false;
            } else if (!idPersonagem.equals(other.idPersonagem))
                    return false;
            return true;
    }
    
}
