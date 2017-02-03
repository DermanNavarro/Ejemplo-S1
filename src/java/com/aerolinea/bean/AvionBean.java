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
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

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
    
    public AvionBean() {
    }

    public List<Avion> getAviones() {
        aviones = controlAvion.getAllAviones();
        return aviones;
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
    
}
