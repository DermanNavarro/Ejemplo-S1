/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aerolinea.sesion;

import com.aerolinea.dao.UsuarioFacade;
import com.aerolinea.entidad.Usuario;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Derman N
 */
@Stateless
public class controlUsuario {

    @EJB
    private UsuarioFacade usuarioFacade;
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    public List<Usuario> getAllUsuarios(){
        return usuarioFacade.findAll();
    }
}
