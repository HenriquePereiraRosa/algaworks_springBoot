/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.security;

import com.example.model.Usuario;
import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

/**
 *
 * @author user
 */
public class SystemUser extends User {
    
    private static final long serialVersionUID = 1L;
    private Usuario usuario;
    
    public SystemUser(Usuario usuario, Collection<? extends GrantedAuthority> authorities) {
        super(usuario.getEmail(), usuario.getSenha(), authorities);
        this.usuario = usuario;
    }
    
    public Usuario getUsuario() {
        return this.usuario;
    }
}
