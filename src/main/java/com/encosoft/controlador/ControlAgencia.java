/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.encosoft.controlador;

import com.encosoft.conexion.Conexion;
import com.encosoft.interfaces.IAgencia;
import com.encosoft.modelo.Agencia;
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
public class ControlAgencia extends ReusableValidacion implements IAgencia {

    private static PreparedStatement ps;
    private static ResultSet rs;
    private static Conexion con;

    public ControlAgencia() {
        con = Conexion.nuevaConexionDB();
    }

    @Override
    public Boolean insertar(Agencia t) {
        Boolean resultado = false;
        final String query = "insert into agencias values(?,?,?);";
        try {
            ps = con.obtenerConexion().prepareStatement(query);
            ps.setInt(1, t.getId());
            ps.setString(2, t.getDescripcion());
            ps.setInt(3, Constantes.ESTADO_ACTIVO);
            resultado = ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("error insertar agencia: " + e.getMessage());
            e.printStackTrace();
        } finally {
            cerrarConexiones(rs, ps, con);
        }
        return resultado;
    }

    @Override
    public Boolean actualizar(Agencia t) {
        Boolean resultado = false;
        final String query = "update agencias set descripcion = ?, estado = ? where id = ?;";
        try {
            ps = con.obtenerConexion().prepareStatement(query);
            ps.setString(1, t.getDescripcion());
            ps.setInt(2, t.getEstado());
            ps.setInt(3, t.getId());
            resultado = ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("error actualizar agencia: " + e.getMessage());
            e.printStackTrace();
        } finally {
            cerrarConexiones(rs, ps, con);
        }
        return resultado;
    }

    @Override
    public Boolean eliminar(Object id) {
        Boolean resultado = false;
        final String query = "update agencias set estado = 0 where id = ?;";
        try {
            ps = con.obtenerConexion().prepareStatement(query);
            ps.setInt(1, Integer.parseInt(id.toString()));
            resultado = ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("error eliminar agencia: " + e.getMessage());
            e.printStackTrace();
        } finally {
            cerrarConexiones(rs, ps, con);
        }
        return resultado;
    }

    @Override
    public Agencia obtenerPorId(Object id) {
        Agencia agencia = new Agencia();
        final String query = "select * from agencias where id = ?;";
        try {
            ps = con.obtenerConexion().prepareStatement(query);
            ps.setInt(1, Integer.parseInt(id.toString()));
            rs = ps.executeQuery();
            while (rs.next()) {
                agencia.setId(rs.getInt(1));
                agencia.setDescripcion(rs.getString(2));
                agencia.setEstado(rs.getInt(3));
            }
        } catch (SQLException e) {
            System.out.println("error obtenerPorId agencia: " + e.getMessage());
            e.printStackTrace();
        } finally {
            cerrarConexiones(rs, ps, con);
        }
        return agencia;
    }

    @Override
    public List<Agencia> listar() {
        List<Agencia> lista = new ArrayList<>();
        final String query = "select * from agencias where estado = 1;";
        try {
            ps = con.obtenerConexion().prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                Agencia agencia = new Agencia();
                agencia.setId(rs.getInt(1));
                agencia.setDescripcion(rs.getString(2));
                agencia.setEstado(rs.getInt(3));
                lista.add(agencia);
            }
        } catch (SQLException e) {
            System.out.println("error listar agencia: " + e.getMessage());
            e.printStackTrace();
        } finally {
            cerrarConexiones(rs, ps, con);
        }
        return lista;
    }
}
