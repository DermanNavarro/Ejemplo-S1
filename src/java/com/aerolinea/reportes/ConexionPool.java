/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aerolinea.reportes;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConexionPool {
    private Connection conn;
    
    public ConexionPool() {
        conn = null;
    }        
    
    public void conectar(){
        try {            
            Context initCtx;     
            initCtx = new InitialContext();
            DataSource ds = (DataSource)initCtx.lookup("jdbc/aerolinea");  
            conn=ds.getConnection();
            
        }catch(NamingException | SQLException e){
            System.out.println("db: " + e.getMessage());                         
        }         
    }   
    
    
    public Connection getConexion(){
        return conn;
    }
    

    public void desconectar(){
        try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConexionPool.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
