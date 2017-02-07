/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aerolinea.dao;

import com.aerolinea.entidad.Avion;
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
public class AvionFacade extends AbstractFacade<Avion> {
    @PersistenceContext(unitName = "Ejemplo-S1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AvionFacade() {
        super(Avion.class);
    }
    public List<Avion> consultarAviones(String descripcion){
        //
        Query q = em.createQuery("SELECT a FROM Avion a WHERE a.descripcion like" + " :descripcion");
        //SQL nativo
        Query q1 = em.createNativeQuery("SELECT * FORM Avion a WHERE a.descripcion like :descripcion");
        //Uso de Name query
        Query q2 = em.createNamedQuery("Avion.findByDescripcion");
        
        q.setParameter("descripcion", "%"+descripcion+"%");
        return q.getResultList();
    }
}
