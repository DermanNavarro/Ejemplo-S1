/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aerolinea.bean;

import com.aerolinea.entidad.Usuario;
import com.aerolinea.sesion.controlUsuario;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Derman N
 */
@ManagedBean(name = "UsuarioBean")
@SessionScoped
public class UsuarioBean {

    /**
     * Creates a new instance of UsuarioBean
     */
    @EJB
    private controlUsuario controlUsuario;
    private List<Usuario> usuarios;
    private Usuario usuario;
    
    public UsuarioBean() {
    }

    public List<Usuario> getUsuarios() {
        usuarios = controlUsuario.getAllUsuarios();
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    
}
