/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.encosoft.controlador;

import com.encosoft.conexion.Conexion;
import com.encosoft.interfaces.IUsuario;
import com.encosoft.modelo.Usuario;
import com.encosoft.util.Constantes;
import com.encosoft.util.ReusableValidacion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Saul
 */
public class ControlUsuarios extends ReusableValidacion implements IUsuario {

    private static PreparedStatement ps;
    private static ResultSet rs;
    private static Conexion con;

    public ControlUsuarios() {
        con = Conexion.nuevaConexionDB();
    }

    @Override
    public List<Usuario> listarPorUsuario(String descripcion) {
        List<Usuario> lista = new ArrayList<>();
        final String query = "select * from usuarios where usuario like ?;";
        try {
            ps = con.obtenerConexion().prepareStatement(query);
            ps.setString(1, descripcion + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt(1));
                usuario.setIdrol(rs.getInt(2));
                usuario.setIdagencia(rs.getInt(3));
                usuario.setUsuario(rs.getString(4));
                usuario.setContrasena(rs.getString(5));
                usuario.setEstado(rs.getInt(6));
                lista.add(usuario);
            }
        } catch (SQLException e) {
            System.out.println("error al listar usuarios: " + e.getMessage());
            e.printStackTrace();
        } finally {
            cerrarConexiones(rs, ps, con);
        }
        return lista;
    }

    @Override
    public Boolean insertar(Usuario t) {
        Boolean resultado = false;
        final String query = "insert into usuarios values(?,?,?,?,?,?);";
        try {
            ps = con.obtenerConexion().prepareStatement(query);
            ps.setInt(1, t.getId());
            ps.setInt(2, t.getIdrol());
            ps.setInt(3, t.getIdagencia());
            ps.setString(4, t.getUsuario());
            ps.setString(5, t.getContrasena());
            ps.setInt(6, Constantes.ESTADO_ACTIVO);
            resultado = ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("error al insertar usuario: " + e.getMessage());
            e.printStackTrace();
        } finally {
            cerrarConexiones(rs, ps, con);
        }
        return resultado;
    }

    @Override
    public Boolean actualizar(Usuario t) {
        Boolean resultado = false;
        final String query = "update usuarios set idrol = ?,idagencia = ?, usuario = ?,contrasena = ?,estado = ? where id = ?;";
        try {
            ps = con.obtenerConexion().prepareStatement(query);
            ps.setInt(1, t.getIdrol());
            ps.setInt(2, t.getIdagencia());
            ps.setString(3, t.getUsuario());
            ps.setString(4, t.getContrasena());
            ps.setInt(5, t.getEstado());
            ps.setInt(6, t.getId());
            resultado = ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("error al actualizar usuario: " + e.getMessage());
            e.printStackTrace();
        } finally {
            cerrarConexiones(rs, ps, con);
        }
        return resultado;
    }

    @Override
    public Boolean eliminar(Object id) {
        Boolean resultado = false;
        final String query = "update usuarios set estado = 0 where id = ?;";
        try {
            ps = con.obtenerConexion().prepareStatement(query);
            ps.setInt(1, Integer.parseInt(id.toString()));
            resultado = ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("error al eliminar usuarios: " + e.getMessage());
            e.printStackTrace();
        } finally {
            cerrarConexiones(rs, ps, con);
        }
        return resultado;
    }

    @Override
    public Usuario obtenerPorId(Object id) {
        Usuario usuario = new Usuario();
        final String query = "select * from usuarios where id = ?;";
        try {
            ps = con.obtenerConexion().prepareStatement(query);
            ps.setInt(1, Integer.parseInt(id.toString()));
            rs = ps.executeQuery();
            while (rs.next()) {
                usuario.setId(rs.getInt(1));
                usuario.setIdrol(rs.getInt(2));
                usuario.setIdagencia(rs.getInt(3));
                usuario.setUsuario(rs.getString(4));
                usuario.setContrasena(rs.getString(5));
                usuario.setEstado(rs.getInt(6));
            }
        } catch (SQLException e) {
            System.out.println("error al obtenerPorId usuarios: " + e.getMessage());
            e.printStackTrace();
        } finally {
            cerrarConexiones(rs, ps, con);
        }
        return usuario;
    }

    @Override
    public List<Usuario> listar() {
        List<Usuario> lista = new ArrayList<>();
        final String query = "select * from usuarios;";
        try {
            ps = con.obtenerConexion().prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt(1));
                usuario.setIdrol(rs.getInt(2));
                usuario.setIdagencia(rs.getInt(3));
                usuario.setUsuario(rs.getString(4));
                usuario.setContrasena(rs.getString(5));
                usuario.setEstado(rs.getInt(6));
                lista.add(usuario);
            }
        } catch (SQLException e) {
            System.out.println("error al listar usuarios: " + e.getMessage());
            e.printStackTrace();
        } finally {
            cerrarConexiones(rs, ps, con);
        }
        return lista;
    }
}

