/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aerolinea.sesion;

import com.aerolinea.dao.AeropuertoFacade;
import com.aerolinea.entidad.Aeropuerto;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Derman N
 */
@Stateless
public class controlAeropuerto {

    @EJB
    private AeropuertoFacade aeropuertoFacade;
    
    public List<Aeropuerto> getAllAeropuertos() {
        return aeropuertoFacade.findAll();
    }

    public void guardarAeropuerto(Aeropuerto a) {
        aeropuertoFacade.create(a);
    }

    public void modificarAeropuerto(Aeropuerto a) {
        aeropuertoFacade.edit(a);
    }

    public void eliminarAeropuerto(Aeropuerto a) {
        aeropuertoFacade.remove(a);
    }
    
    public List<Aeropuerto> consultarAeropuertos(String nom) {
        return aeropuertoFacade.consultarAeropuertos(nom);
    }
}
