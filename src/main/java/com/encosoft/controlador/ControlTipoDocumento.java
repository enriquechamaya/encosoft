/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.encosoft.controlador;

import com.encosoft.conexion.Conexion;
import com.encosoft.interfaces.ITipoDocumento;
import com.encosoft.modelo.TipoDocumento;
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
public class ControlTipoDocumento extends ReusableValidacion implements ITipoDocumento {

    private static PreparedStatement ps;
    private static ResultSet rs;
    private static Conexion con;

    public ControlTipoDocumento() {
        con = Conexion.nuevaConexionDB();
    }

    @Override
    public Boolean insertar(TipoDocumento t) {
        Boolean resultado = false;
        final String query = "insert into tipo_documento values(?,?,?);";
        try {
            ps = con.obtenerConexion().prepareStatement(query);
            ps.setInt(1, t.getId());
            ps.setString(2, t.getDescripcion());
            ps.setInt(3, Constantes.ESTADO_ACTIVO);
            resultado = ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("error insertar tipo_documento: " + e.getMessage());
            e.printStackTrace();
        } finally {
            cerrarConexiones(rs, ps, con);
        }
        return resultado;
    }

    @Override
    public Boolean actualizar(TipoDocumento t) {
        Boolean resultado = false;
        final String query = "update tipo_documento set descripcion = ?, estado = ? where id = ?;";
        try {
            ps = con.obtenerConexion().prepareStatement(query);
            ps.setString(1, t.getDescripcion());
            ps.setInt(2, t.getEstado());
            ps.setInt(3, t.getId());
            resultado = ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("error actualizar tipo_documento: " + e.getMessage());
            e.printStackTrace();
        } finally {
            cerrarConexiones(rs, ps, con);
        }
        return resultado;
    }

    @Override
    public Boolean eliminar(Object id) {
        Boolean resultado = false;
        final String query = "update tipo_documento set estado = 0 where id = ?;";
        try {
            ps = con.obtenerConexion().prepareStatement(query);
            ps.setInt(1, Integer.parseInt(id.toString()));
            resultado = ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("error eliminar tipo_documento: " + e.getMessage());
            e.printStackTrace();
        } finally {
            cerrarConexiones(rs, ps, con);
        }
        return resultado;
    }

    @Override
    public TipoDocumento obtenerPorId(Object id) {
        TipoDocumento tipoDocumento = new TipoDocumento();
        final String query = "select * from tipo_documento where id = ?;";
        try {
            ps = con.obtenerConexion().prepareStatement(query);
            ps.setInt(1, Integer.parseInt(id.toString()));
            rs = ps.executeQuery();
            while (rs.next()) {
                tipoDocumento.setId(rs.getInt(1));
                tipoDocumento.setDescripcion(rs.getString(2));
                tipoDocumento.setEstado(rs.getInt(3));
            }
        } catch (SQLException e) {
            System.out.println("error obtenerPorId tipo_documento: " + e.getMessage());
            e.printStackTrace();
        } finally {
            cerrarConexiones(rs, ps, con);
        }
        return tipoDocumento;
    }

    @Override
    public List<TipoDocumento> listar() {
        List<TipoDocumento> lista = new ArrayList<>();
        final String query = "select * from tipo_documento;";
        try {
            ps = con.obtenerConexion().prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                TipoDocumento tipoDocumento = new TipoDocumento();
                tipoDocumento.setId(rs.getInt(1));
                tipoDocumento.setDescripcion(rs.getString(2));
                tipoDocumento.setEstado(rs.getInt(3));
                lista.add(tipoDocumento);
            }
        } catch (SQLException e) {
            System.out.println("error listar tipo_documento: " + e.getMessage());
            e.printStackTrace();
        } finally {
            cerrarConexiones(rs, ps, con);
        }
        return lista;
    }

    @Override
    public List<TipoDocumento> listarPorDescripcion(String descripcion) {
        List<TipoDocumento> lista = new ArrayList<>();
        final String query = "select * from tipo_documento where descripcion like ?;";
        try {
            ps = con.obtenerConexion().prepareStatement(query);
            ps.setString(1, descripcion + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                TipoDocumento tipoDocumento = new TipoDocumento();
                tipoDocumento.setId(rs.getInt(1));
                tipoDocumento.setDescripcion(rs.getString(2));
                tipoDocumento.setEstado(rs.getInt(3));
                lista.add(tipoDocumento);
            }
        } catch (SQLException e) {
            System.out.println("error listar tipo_documento: " + e.getMessage());
            e.printStackTrace();
        } finally {
            cerrarConexiones(rs, ps, con);
        }
        return lista;
    }

}
