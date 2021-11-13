/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.encosoft.conexion;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Saul
 */
public class Conexion {

    private static Conexion instancia = null;
    private static Connection con;
    private static final String URL = "jdbc:mysql://localhost:3306/encosoft_db";
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String USER = "tcipos";
    private static final String PASS = "12345";

    private Conexion() {
        try {
            Class.forName(DRIVER).newInstance();
            con = DriverManager.getConnection(URL, USER, PASS);
            System.out.println("conectado a la bd");
        } catch (Exception e) {
            System.out.println("error al establecer la conexión");
            e.printStackTrace();
        }
    }

    public synchronized static Conexion nuevaConexionDB() {
        if (instancia == null) {
            instancia = new Conexion();
        }
        return instancia;
    }

    public Connection obtenerConexion() {
        return con;
    }

    public void cerrarConexion() {
        instancia = null;
    }
}
