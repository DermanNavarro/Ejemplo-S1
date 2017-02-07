/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aerolinea.bean;

import com.aerolinea.dao.PaisFacade;
import com.aerolinea.entidad.Aeropuerto;
import com.aerolinea.entidad.Pais;
import com.aerolinea.sesion.controlAeropuerto;
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
@ManagedBean(name = "AeropuertoBean")
@SessionScoped
public class AeropuertoBean {

    @EJB
    private controlAeropuerto controlAeropuerto;
    @EJB
    private PaisFacade paisFacade;

    private List<Aeropuerto> aeropuertos;
    private List<Pais> paises;
    private Aeropuerto aeropuerto;
    private Pais pais;
    private String busqueda;

    @PostConstruct
    public void init() {
        aeropuerto = new Aeropuerto();
        pais = new Pais();
    }

    public AeropuertoBean() {
        busqueda = "";
    }

    public String preparaNuevo() {
        aeropuerto = new Aeropuerto();
        pais = new Pais();
        return "AeropuertoForm.xhtml?faces-redirect=true";
    }

    public String guardar() {
        if (aeropuerto.getIdaeropuerto() == null) {
            aeropuerto.setIdpais(pais);
            controlAeropuerto.guardarAeropuerto(aeropuerto);
        } else {
            pais = new Pais(aeropuerto.getIdpais().getIdpais());
            aeropuerto.setIdpais(pais);
            controlAeropuerto.modificarAeropuerto(aeropuerto);
        }
        return "AeropuertoLista.xhtml?faces-redirect=true";
    }

    public String seleccionarAeropuerto(Aeropuerto a) {
        aeropuerto = a;
        return "AeropuertoForm.xhtml?faces-redirect=true";
    }

    public void eliminarAeropuerto(Aeropuerto a) {
        aeropuerto = a;
        controlAeropuerto.eliminarAeropuerto(aeropuerto);

        //generamos el mensage a mostrar al borrar un registro
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                FacesMessage.SEVERITY_INFO, "Informacion", "Datos borrados"));

        this.getAeropuertos();
        //return "AvionLista.xhtml?faces-redirect=true";
    }

    public List<Aeropuerto> getAeropuertos() {
        //aeropuertos = controlAeropuerto.getAllAeropuertos();
        aeropuertos = controlAeropuerto.consultarAeropuertos(busqueda);
        return aeropuertos;
    }

    public void setAeropuertos(List<Aeropuerto> aeropuertos) {
        this.aeropuertos = aeropuertos;
    }

    /*public void consultar() {
        this.getAeropuertos();
    }*/

    public List<Pais> getPaises() {
        paises = paisFacade.findAll();
        return paises;
    }

    public void setPaises(List<Pais> paises) {
        this.paises = paises;
    }

    public Aeropuerto getAeropuerto() {
        return aeropuerto;
    }

    public void setAeropuerto(Aeropuerto aeropuerto) {
        this.aeropuerto = aeropuerto;
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

}
