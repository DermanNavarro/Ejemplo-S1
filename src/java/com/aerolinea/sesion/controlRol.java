/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aerolinea.sesion;

import com.aerolinea.dao.RolFacade;
import com.aerolinea.entidad.Rol;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Derman N
 */
@Stateless
public class controlRol {
    @EJB
    private RolFacade rolFacade;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    public List<Rol> getAllRols() {
        return rolFacade.findAll();
    }

    public void guardarRol(Rol rol) {
        rolFacade.create(rol);
    }

    public void modificarRol(Rol rol) {
        rolFacade.edit(rol);
    }

    public void eliminarRol(Rol rol) {
        rolFacade.remove(rol);
    }

    public List<Rol> consultarRols(String desc) {
        return rolFacade.consultarRols(desc);
    }
}
