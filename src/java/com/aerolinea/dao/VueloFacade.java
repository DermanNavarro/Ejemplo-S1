/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aerolinea.dao;

import com.aerolinea.entidad.Vuelo;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Derman N
 */
@Stateless
public class VueloFacade extends AbstractFacade<Vuelo> {
    @PersistenceContext(unitName = "Ejemplo-S1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public VueloFacade() {
        super(Vuelo.class);
    }
    
}
