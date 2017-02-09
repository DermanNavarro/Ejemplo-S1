/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aerolinea.bean;

import com.aerolinea.entidad.Rol;
import com.aerolinea.sesion.controlRol;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Derman N
 */
@ManagedBean(name = "RolBean")
@SessionScoped
public class RolBean {
    @EJB
    private controlRol controlRol;
    private List<Rol> rols;
    private Rol rol;
    private String busqueda;

    /**
     * Creates a new instance of RolBean
     */
    public RolBean() {
        busqueda="";
    }
    
    public List<Rol> getRols() {
        rols = controlRol.consultarRols(busqueda);
        return rols;
    }

    public String preparaNuevo() {
        rol = new Rol();
        return "RolForm.xhtml?faces-redirect=true";
    }

    public String guardar() {
        if (rol.getIdrol() == null) {
            controlRol.guardarRol(rol);
        } else {
            controlRol.modificarRol(rol);
        }
        return "RolLista.xhtml?faces-redirect=true";
    }

    public String seleccionarRol(Rol a) {
        rol = a;
        return "RolForm.xhtml?faces-redirect=true";
    }

    public void eliminarRol(Rol a) {
        rol = a;
        controlRol.eliminarRol(rol);

        //generamos el mensage a mostrar al borrar un registro
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                FacesMessage.SEVERITY_INFO, "Informacion", "Datos borrados"));

        this.getRols();
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public String getBusqueda() {
        return busqueda;
    }

    public void setBusqueda(String busqueda) {
        this.busqueda = busqueda;
    }
}
