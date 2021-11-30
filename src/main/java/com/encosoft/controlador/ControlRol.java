/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.encosoft.controlador;

import com.encosoft.conexion.Conexion;

import com.encosoft.interfaces.*;
import com.encosoft.modelo.Rol;
import com.encosoft.util.Constantes;
import com.encosoft.util.ReusableValidacion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Saul
 */
public class ControlRol extends ReusableValidacion   implements IRol {

    private static PreparedStatement ps;
    private static ResultSet rs;
    private static Conexion con;

    public ControlRol() {
        con = Conexion.nuevaConexionDB();
    }

    @Override
    public Boolean insertar(Rol t) {
        Boolean resultado = false;

        final String query = "INSERT INTO rol  VALUES (?,?,?)";
        try {

            ps = con.obtenerConexion().prepareStatement(query);
             ps.setInt(1, t.getId());
            ps.setString(2, t.getDescripcion());
            ps.setInt(3, Constantes.ESTADO_ACTIVO);

            ps.executeUpdate(); //ejecutar la instruccion st
            resultado = ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("error insertar rol: " + e.getMessage());
            e.printStackTrace();
        } finally {
            cerrarConexiones(rs, ps, con);
        }
        return resultado;
    }

    @Override
    public Boolean actualizar(Rol t) {
         Boolean resultado = false;
        final String query = "update rol set descripcion = ?, estado = ? where id = ?;";
        try {
            ps = con.obtenerConexion().prepareStatement(query);
            ps.setString(1, t.getDescripcion());
            ps.setInt(2, t.getEstado());
            ps.setInt(3, t.getId());
            resultado = ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("error actualizar rol: " + e.getMessage());
            e.printStackTrace();
        } finally {
            cerrarConexiones(rs, ps, con);
        }
        return resultado;
    }

    @Override
    public Boolean eliminar(Object id) {
        Boolean resultado = false;
        final String query = "update rol set estado = 0 where id = ?;";
        try {
            ps = con.obtenerConexion().prepareStatement(query);
            ps.setInt(1, Integer.parseInt(id.toString()));
            resultado = ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("error eliminar rol: " + e.getMessage());
            e.printStackTrace();
        } finally {
            cerrarConexiones(rs, ps, con);
        }
        return resultado;
    }


    @Override
    public List<Rol> listar() {
         List<Rol> lista = new ArrayList<>();
        final String query = "select * from rol;";
        try {
            ps = con.obtenerConexion().prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                Rol rol = new Rol();
                rol.setId(rs.getInt(1));
                rol.setDescripcion(rs.getString(2));
                rol.setEstado(rs.getInt(3));
                lista.add(rol);
            }
        } catch (SQLException e) {
            System.out.println("error listar rol: " + e.getMessage());
            e.printStackTrace();
        } finally {
            cerrarConexiones(rs, ps, con);
        }
        return lista;
    }


    @Override
    public Rol obtenerPorId(Object id) {
          Rol rol = new Rol();
        final String query = "select * from rol where id = ?;";
        try {
            ps = con.obtenerConexion().prepareStatement(query);
            ps.setInt(1, Integer.parseInt(id.toString()));
            rs = ps.executeQuery();
            while (rs.next()) {
                rol.setId(rs.getInt(1));
                rol.setDescripcion(rs.getString(2));
                rol.setEstado(rs.getInt(3));
            }
        } catch (SQLException e) {
            System.out.println("error obtenerPorId rol: " + e.getMessage());
            e.printStackTrace();
        } finally {
            cerrarConexiones(rs, ps, con);
        }
        return rol;
    }

    @Override
    public List<Rol> listarPorDescripcion(String descripcion) {
       List<Rol> lista = new ArrayList<>();
        final String query = "select * from rol where descripcion like ?;";
        try {
            ps = con.obtenerConexion().prepareStatement(query);
            ps.setString(1, descripcion + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                Rol rol = new Rol();
                rol.setId(rs.getInt(1));
                rol.setDescripcion(rs.getString(2));
                rol.setEstado(rs.getInt(3));
                lista.add(rol);
            }
        } catch (SQLException e) {
            System.out.println("error listar rol: " + e.getMessage());
            e.printStackTrace();
        } finally {
            cerrarConexiones(rs, ps, con);
        }
        return lista;
    }

}
