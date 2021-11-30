/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.encosoft.controlador;

import com.encosoft.conexion.Conexion;
import com.encosoft.interfaces.ICategoria;
import com.encosoft.modelo.Categoria;
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
public class ControlCategorias extends ReusableValidacion implements ICategoria {

    private static PreparedStatement ps;
    private static ResultSet rs;
    private static Conexion con;

    public ControlCategorias() {
        con = Conexion.nuevaConexionDB();
    }

    @Override
    public Boolean insertar(Categoria t) {
        Boolean resultado = false;
        final String query = "insert into categorias values(?,?,?);";
        try {
            ps = con.obtenerConexion().prepareStatement(query);
            ps.setInt(1, t.getId());
            ps.setString(2, t.getDescripcion());
            ps.setInt(3, Constantes.ESTADO_ACTIVO);
            resultado = ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("error insertar categorias: " + e.getMessage());
            e.printStackTrace();
        } finally {
            cerrarConexiones(rs, ps, con);
        }
        return resultado;
    }

    @Override
    public Boolean actualizar(Categoria t) {
        Boolean resultado = false;
        final String query = "update categorias set descripcion = ?, estado = ? where id = ?;";
        try {
            ps = con.obtenerConexion().prepareStatement(query);
            ps.setString(1, t.getDescripcion());
            ps.setInt(2, t.getEstado());
            ps.setInt(3, t.getId());
            resultado = ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("error actualizar categorias: " + e.getMessage());
            e.printStackTrace();
        } finally {
            cerrarConexiones(rs, ps, con);
        }
        return resultado;
    }

    @Override
    public List<Categoria> listar() {
        List<Categoria> lista = new ArrayList<>();
        final String query = "select * from categorias;";
        try {
            ps = con.obtenerConexion().prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                Categoria categoria = new Categoria();
                categoria.setId(rs.getInt(1));
                categoria.setDescripcion(rs.getString(2));
                categoria.setEstado(rs.getInt(3));
                lista.add(categoria);
            }
        } catch (SQLException e) {
            System.out.println("error listar categorias: " + e.getMessage());
            e.printStackTrace();
        } finally {
            cerrarConexiones(rs, ps, con);
        }
        return lista;
    }

    @Override
    public Boolean eliminar(Object id) {
        Boolean resultado = false;
        final String query = "update categorias set estado = 0 where id = ?;";
        try {
            ps = con.obtenerConexion().prepareStatement(query);
            ps.setInt(1, Integer.parseInt(id.toString()));
            resultado = ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("error eliminar categorias: " + e.getMessage());
            e.printStackTrace();
        } finally {
            cerrarConexiones(rs, ps, con);
        }
        return resultado;
    }

    @Override
    public Categoria obtenerPorId(Object id) {
        Categoria categoria = new Categoria();
        final String query = "select * from categorias where id = ?;";
        try {
            ps = con.obtenerConexion().prepareStatement(query);
            ps.setInt(1, Integer.parseInt(id.toString()));
            rs = ps.executeQuery();
            while (rs.next()) {
                categoria.setId(rs.getInt(1));
                categoria.setDescripcion(rs.getString(2));
                categoria.setEstado(rs.getInt(3));
            }
        } catch (SQLException e) {
            System.out.println("error obtenerPorId categoria: " + e.getMessage());
            e.printStackTrace();
        } finally {
            cerrarConexiones(rs, ps, con);
        }
        return categoria;
    }

    @Override
    public List<Categoria> listarPorDescripcion(String descripcion) {
     List<Categoria> lista = new ArrayList<>();
        final String query = "select * from categorias where descripcion like ?;";
        try {
            ps = con.obtenerConexion().prepareStatement(query);
            ps.setString(1, descripcion + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                Categoria categoria = new Categoria();
                categoria.setId(rs.getInt(1));
                categoria.setDescripcion(rs.getString(2));
                categoria.setEstado(rs.getInt(3));
                lista.add(categoria);
            }
        } catch (SQLException e) {
            System.out.println("error listar categoria: " + e.getMessage());
            e.printStackTrace();
        } finally {
            cerrarConexiones(rs, ps, con);
        }
        return lista;
    }

}
