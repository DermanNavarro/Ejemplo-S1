/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aerolinea.dao;

import com.aerolinea.entidad.Aeropuerto;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Derman N
 */
@Stateless
public class AeropuertoFacade extends AbstractFacade<Aeropuerto> {
    @PersistenceContext(unitName = "Ejemplo-S1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AeropuertoFacade() {
        super(Aeropuerto.class);
    }
    
    public List<Aeropuerto> consultarAeropuertos(String descripcion){
        //
        Query q = em.createQuery("SELECT a FROM Aeropuerto a WHERE a.aeropuerto like" + " :aeropuerto");
        
        q.setParameter("aeropuerto", "%"+descripcion+"%");
        return q.getResultList();
    }
    
}
