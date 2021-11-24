/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.encosoft.controlador;

import com.encosoft.conexion.Conexion;
import com.encosoft.interfaces.ICliente;
import com.encosoft.modelo.Cliente;
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
public class ControlClientes extends ReusableValidacion implements ICliente {

    private static PreparedStatement ps;
    private static ResultSet rs;
    private static Conexion con;

    public ControlClientes() {
        con = Conexion.nuevaConexionDB();
    }

    @Override
    public Boolean insertar(Cliente t) {
        Boolean resultado = false;
        final String query = "insert into clientes values(?,?,?,?,?,?,?,?,?,?);";
        try {
            ps = con.obtenerConexion().prepareStatement(query);
            ps.setInt(1, t.getId());
            ps.setString(2, t.getNombre());
            ps.setString(3, t.getApepat());
            ps.setString(4, t.getApemat());
            ps.setInt(5, t.getIdtipodoc());
            ps.setString(6, t.getNrodocumento());
            ps.setString(7, t.getEmail());
            ps.setString(8, t.getCelular());
            ps.setString(9, t.getDireccion());
            ps.setInt(10, Constantes.ESTADO_ACTIVO);
            resultado = ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("error insertar clientes: " + e.getMessage());
            e.printStackTrace();
        } finally {
            cerrarConexiones(rs, ps, con);
        }
        return resultado;
    }

    @Override
    public Boolean actualizar(Cliente t) {
        Boolean resultado = false;
        final String query = "update clientes set nombre=?,apepat=?,apemat=?,idtipodoc=?,nrodocumento=?,email=?,celular=?,direccion=?,estado=?, where id=?;";
        try {
            ps = con.obtenerConexion().prepareStatement(query);
            ps.setString(1, t.getNombre());
            ps.setString(2, t.getApepat());
            ps.setString(3, t.getApemat());
            ps.setInt(4, t.getIdtipodoc());
            ps.setString(5, t.getNrodocumento());
            ps.setString(6, t.getEmail());
            ps.setString(7, t.getCelular());
            ps.setString(8, t.getDireccion());
            ps.setInt(9, Constantes.ESTADO_ACTIVO);
            ps.setInt(10, t.getId());
            resultado = ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("error actualizar clientes: " + e.getMessage());
            e.printStackTrace();
        } finally {
            cerrarConexiones(rs, ps, con);
        }
        return resultado;
    }

    @Override
    public Boolean eliminar(Object id) {
        Boolean resultado = false;
        final String query = "update clientes set estado = 0 where id = ?;";
        try {
            ps = con.obtenerConexion().prepareStatement(query);
            ps.setInt(1, Integer.parseInt(id.toString()));
            resultado = ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("error eliminar clientes: " + e.getMessage());
            e.printStackTrace();
        } finally {
            cerrarConexiones(rs, ps, con);
        }
        return resultado;
    }

    @Override
    public Cliente obtenerPorId(Object id) {
        Cliente cliente = new Cliente();
        final String query = "select * from clientes where id = ?;";
        try {
            ps = con.obtenerConexion().prepareStatement(query);
            ps.setInt(1, Integer.parseInt(id.toString()));
            rs = ps.executeQuery();
            while (rs.next()) {
                cliente.setId(rs.getInt(1));
                cliente.setNombre(rs.getString(2));
                cliente.setApepat(rs.getString(3));
                cliente.setApemat(rs.getString(4));
                cliente.setIdtipodoc(rs.getInt(5));
                cliente.setNrodocumento(rs.getString(6));
                cliente.setEmail(rs.getString(7));
                cliente.setCelular(rs.getString(8));
                cliente.setDireccion(rs.getString(9));
                cliente.setEstado(rs.getInt(10));
            }
        } catch (SQLException e) {
            System.out.println("error obtenerPorId clientes: " + e.getMessage());
            e.printStackTrace();
        } finally {
            cerrarConexiones(rs, ps, con);
        }
        return cliente;
    }

    @Override
    public List<Cliente> listar() {
        List<Cliente> lista = new ArrayList<>();
        final String query = "select * from clientes;";
        try {
            ps = con.obtenerConexion().prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setId(rs.getInt(1));
                cliente.setNombre(rs.getString(2));
                cliente.setApepat(rs.getString(3));
                cliente.setApemat(rs.getString(4));
                cliente.setIdtipodoc(rs.getInt(5));
                cliente.setNrodocumento(rs.getString(6));
                cliente.setEmail(rs.getString(7));
                cliente.setCelular(rs.getString(8));
                cliente.setDireccion(rs.getString(9));
                cliente.setEstado(rs.getInt(10));
                lista.add(cliente);
            }
        } catch (SQLException e) {
            System.out.println("error listar Clientes: " + e.getMessage());
            e.printStackTrace();
        } finally {
            cerrarConexiones(rs, ps, con);
        }
        return lista;
    }

}
