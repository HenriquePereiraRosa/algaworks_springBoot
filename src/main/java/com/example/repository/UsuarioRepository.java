/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.repository;

import com.example.model.Usuario;
import java.io.Serializable;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author user
 */
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
    public Optional<Usuario> findByEmail(String email);
    
}
