/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aerolinea.bean;

import com.aerolinea.entidad.Avion;
import com.aerolinea.sesion.controlAvion;
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
@ManagedBean(name = "AvionBean")
@SessionScoped
public class AvionBean {

    /**
     * Creates a new instance of AvionBean
     */
    @EJB
    private controlAvion controlAvion;
    private List<Avion> aviones;
    private Avion avion;
    private String busqueda;

    public AvionBean() {
        busqueda="";
    }

    public List<Avion> getAviones() {
        //aviones = controlAvion.getAllAviones();
        aviones = controlAvion.consultarAviones(busqueda);
        return aviones;
    }
    
    public void consultar(){
        this.getAviones();
    }

    public String preparaNuevo() {
        avion = new Avion();
        return "AvionForm.xhtml?faces-redirect=true";
    }

    public String guardar() {
        if (avion.getIdavion() == null) {
            controlAvion.guardarAvion(avion);
        } else {
            controlAvion.modificarAvion(avion);
        }
        return "AvionLista.xhtml?faces-redirect=true";
    }

    public String seleccionarAvion(Avion a) {
        avion = a;
        return "AvionForm.xhtml?faces-redirect=true";
    }

    public void eliminarAvion(Avion a) {
        avion = a;
        controlAvion.eliminarAvion(avion);

        //generamos el mensage a mostrar al borrar un registro
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                FacesMessage.SEVERITY_INFO, "Informacion", "Datos borrados"));

        this.getAviones();
        //return "AvionLista.xhtml?faces-redirect=true";
    }

    public void setAviones(List<Avion> aviones) {
        this.aviones = aviones;
    }

    public Avion getAvion() {
        return avion;
    }

    public void setAvion(Avion avion) {
        this.avion = avion;
    }

    public String getBusqueda() {
        return busqueda;
    }

    public void setBusqueda(String busqueda) {
        this.busqueda = busqueda;
    }
}
