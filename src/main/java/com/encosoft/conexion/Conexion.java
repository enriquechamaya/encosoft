/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.encosoft.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Conexion {
      Connection con;
    
    public   Connection getConexion(){
        try {                
            Class.forName("com.mysql.jdbc.Driver");
            //Verificar si su gestor de base de datos tiene una contraseña , si es asi agregarla despues de root
            con = DriverManager.getConnection("jdbc:mysql://localhost/encosoft_db", "root", "");
            System.out.println("Conexion exitosa");
        } catch (ClassNotFoundException | SQLException ex ) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error de conexion "+ex);
        }
        return con;
    }
}
