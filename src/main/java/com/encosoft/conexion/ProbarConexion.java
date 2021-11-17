/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.encosoft.conexion;

/**
 *
 * @author Saul
 */
public class ProbarConexion {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Conexion c = new Conexion();
        if (c.getConexion() != null) {
            System.out.println("conexion es correcta");
        } else {
            System.out.println("conexion erronea");
        }
    }
}