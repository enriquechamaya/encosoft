/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.encosoft.controlador;

import com.encosoft.conexion.Conexion;
import com.encosoft.interfaces.IProductos;
import com.encosoft.modelo.Productos;
import com.encosoft.util.Constantes;
import com.encosoft.util.ReusableValidacion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ControlProductos extends ReusableValidacion implements IProductos {

    private static PreparedStatement ps;
    private static ResultSet rs;
    private static Conexion con;

    public ControlProductos() {
        con = Conexion.nuevaConexionDB();
    }

    @Override
    public Boolean insertar(Productos t) {
        Boolean resultado = false;

        final String query = "INSERT INTO productos  VALUES (?,?,?,?);";
        try {
            ps = con.obtenerConexion().prepareStatement(query);
            ps.setInt(1, t.getId());
            ps.setInt(2, t.getIdcategoria());
            ps.setString(3, t.getDescripcion());
            ps.setInt(4, Constantes.ESTADO_ACTIVO);
            resultado = ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("error insertar productos: " + e.getMessage());
            e.printStackTrace();
        } finally {
            cerrarConexiones(rs, ps, con);
        }
        return resultado;
    }

    @Override
    public Boolean actualizar(Productos t) {
        Boolean resultado = false;
        final String query = "update productos set idcategoria = ?,descripcion = ?, estado = ? where id = ?;";
        try {
            ps = con.obtenerConexion().prepareStatement(query);
            ps.setInt(1, t.getIdcategoria());
            ps.setString(2, t.getDescripcion());
              ps.setInt(3, t.getEstado());
            ps.setInt(4, t.getId());
            resultado = ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("error actualizar productos: " + e.getMessage());
            e.printStackTrace();
        } finally {
            cerrarConexiones(rs, ps, con);
        }
        return resultado;
    }

    @Override
    public Boolean eliminar(Object id) {
        Boolean resultado = false;
        final String query = "update productos set estado = 0 where id = ?;";
        try {
            ps = con.obtenerConexion().prepareStatement(query);
            ps.setInt(1, Integer.parseInt(id.toString()));
            resultado = ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("error eliminar productos: " + e.getMessage());
            e.printStackTrace();
        } finally {
            cerrarConexiones(rs, ps, con);
        }
        return resultado;
    }

    @Override
    public List<Productos> listar() {
        List<Productos> lista = new ArrayList<>();
        final String query = "select * from productos;";
        try {
            ps = con.obtenerConexion().prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                Productos productos = new Productos();
                productos.setId(rs.getInt(1));
                productos.setIdcategoria(rs.getInt(2));
                productos.setDescripcion(rs.getString(3));
                productos.setEstado(rs.getInt(4));
                lista.add(productos);
            }
        } catch (SQLException e) {
            System.out.println("error listar productos: " + e.getMessage());
            e.printStackTrace();
        } finally {
            cerrarConexiones(rs, ps, con);
        }
        return lista;
    }

    @Override
    public Productos obtenerPorId(Object id) {
        Productos productos = new Productos();
        final String query = "select * from productos where id = ?;";
        try {
            ps = con.obtenerConexion().prepareStatement(query);
            ps.setInt(1, Integer.parseInt(id.toString()));
            rs = ps.executeQuery();
            while (rs.next()) {
                productos.setId(rs.getInt(1));
                productos.setIdcategoria(rs.getInt(2));
                productos.setDescripcion(rs.getString(3));
                productos.setEstado(rs.getInt(4));
            }
        } catch (SQLException e) {
            System.out.println("error obtenerPorId productos: " + e.getMessage());
            e.printStackTrace();
        } finally {
            cerrarConexiones(rs, ps, con);
        }
        return productos;
    }

    @Override
    public List<Productos> listarPorDescripcion(String descripcion) {
        List<Productos> lista = new ArrayList<>();
        final String query = "select * from productos where descripcion like ?;";
        try {
            ps = con.obtenerConexion().prepareStatement(query);
            ps.setString(1, descripcion + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                Productos productos = new Productos();
                productos.setId(rs.getInt(1));
                productos.setIdcategoria(rs.getInt(2));
                productos.setDescripcion(rs.getString(3));
                productos.setEstado(rs.getInt(4));
                lista.add(productos);
            }
        } catch (SQLException e) {
            System.out.println("error listar producto: " + e.getMessage());
            e.printStackTrace();
        } finally {
            cerrarConexiones(rs, ps, con);
        }
        return lista;
    }
}
