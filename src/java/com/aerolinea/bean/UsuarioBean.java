/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aerolinea.bean;

import com.aerolinea.dao.PaisFacade;
import com.aerolinea.dao.RolFacade;
import com.aerolinea.entidad.Pais;
import com.aerolinea.entidad.Rol;
import com.aerolinea.entidad.Usuario;
import com.aerolinea.sesion.controlUsuario;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Derman N
 */
@ManagedBean(name = "UsuarioBean")
@SessionScoped
public class UsuarioBean {

    @EJB
    private PaisFacade paisFacade;
    @EJB
    private RolFacade rolFacade;

    /**
     * Creates a new instance of UsuarioBean
     */
    @EJB
    private controlUsuario controlUsuario;
    private List<Usuario> usuarios;
    private List<Rol> rols;
    private List<Pais> paises;
    private Usuario usuario;
    private Rol rol;
    private Pais pais;
    private String busqueda;

    @PostConstruct
    public void init() {
        usuario = new Usuario();
        rol = new Rol();
        pais = new Pais();
    }

    public UsuarioBean() {
        busqueda = "";
    }

    public String preparaNuevo() {
        usuario = new Usuario();
        rol = new Rol();
        pais = new Pais();
        return "UsuarioForm.xhtml?faces-redirect=true";
    }

    public String guardar() {
        rol = new Rol(usuario.getIdrol().getIdrol());
        pais = new Pais(usuario.getIdpais().getIdpais());
        if (usuario.getIdusuario() == null) {
            usuario.setIdrol(rol);
            usuario.setIdpais(pais);
            controlUsuario.guardarUsuario(usuario);
        } else {
            usuario.setIdrol(rol);
            usuario.setIdpais(pais);
            controlUsuario.modificarUsuario(usuario);
        }
        return "UsuarioLista.xhtml?faces-redirect=true";
    }

    public String seleccionarUsuario(Usuario a) {
        usuario = a;
        return "UsuarioForm.xhtml?faces-redirect=true";
    }

    public void eliminarUsuario(Usuario a) {
        usuario = a;
        controlUsuario.eliminarUsuario(usuario);

        //generamos el mensage a mostrar al borrar un registro
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                FacesMessage.SEVERITY_INFO, "Informacion", "Datos borrados"));

        this.getUsuarios();
    }

    public List<Usuario> getUsuarios() {
        usuarios = controlUsuario.consultarUsuarios(busqueda);
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

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    public String getBusqueda() {
        return busqueda;
    }

    public void setBusqueda(String busqueda) {
        this.busqueda = busqueda;
    }

    public List<Rol> getRols() {
        rols = rolFacade.findAll();
        return rols;
    }

    public void setRols(List<Rol> rols) {
        this.rols = rols;
    }

    public List<Pais> getPaises() {
        paises = paisFacade.findAll();
        return paises;
    }

    public void setPaises(List<Pais> paises) {
        this.paises = paises;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }
}
