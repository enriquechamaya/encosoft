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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.encosoft.util.ReusableValidacion;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Saul
 */
public class ControlRol extends ReusableValidacion implements IRol {

    private static PreparedStatement ps;
    private static ResultSet rs;
    private static Conexion con;

    public ControlRol() {
        con = Conexion.nuevaConexionDB();
    }

    @Override
    public Boolean insertar(Rol t) {
        Boolean resultado = false;

        final String query = "INSERT INTO rol (descripcion,estado) VALUES (?,?)";
        try {
            ps = con.obtenerConexion().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, t.getDescripcion());
            ps.setInt(2, Constantes.ESTADO_ACTIVO);
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
        final String query = "update rol set descripcion=?,estado=? where id=?";
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
        final String query = "update rol set estado= 0 where id=?";
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
    public List<Rol> obtenerPorId(Integer id) {
        List<Rol> lis = new ArrayList<>();

        final String query = "select  *from rol where id=?";
        try {
            ps = con.obtenerConexion().prepareStatement(query);

            ps.setInt(1, id);

            rs = ps.executeQuery();

            while (rs.next()) {

                Rol u = new Rol();
                u.setId(rs.getInt(1));
                u.setDescripcion(rs.getString(2));
                u.setEstado(rs.getInt(3));

                lis.add(u);

            }
       } catch (SQLException e) {
            System.out.println("error obtenerPorId rol: " + e.getMessage());
            e.printStackTrace();
        } finally {
            cerrarConexiones(rs, ps, con);
        }
        return lis;
    }


    @Override
    public List<Rol> listar() {
        List<Rol> lis = new ArrayList<>();
        final String query = "select * from rol where estado = 1;";

        try {

            ps = con.obtenerConexion().prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                Rol u = new Rol();
                u.setId(rs.getInt(1));
                u.setDescripcion(rs.getString(2));
                u.setEstado(rs.getInt(3));

                lis.add(u);
            }
        } catch (SQLException e) {
            System.out.println("error listar rol: " + e.getMessage());
            e.printStackTrace();
        } finally {
            cerrarConexiones(rs, ps, con);
        }
        return lis;
    }

    @Override
    public Rol obtenerPorId(Object id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Rol> listarTodos() {
        List<Rol> lis = new ArrayList<>();

        final String query = "select id,descripcion,estado from rol";

        try {

            ps = con.obtenerConexion().prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                Rol u = new Rol();
                u.setId(rs.getInt(1));
                u.setDescripcion(rs.getString(2));
                u.setEstado(rs.getInt(3));

                lis.add(u);
            }
        } catch (SQLException e) {
            System.out.println("error listar roles: " + e.getMessage());
            e.printStackTrace();
        } finally {
            cerrarConexiones(rs, ps, con);
        }
        return lis;
    }

}
