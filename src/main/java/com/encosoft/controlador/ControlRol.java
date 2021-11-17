/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.encosoft.controlador;

import com.encosoft.conexion.Conexion;
import com.encosoft.interfaces.ICrud;
import com.encosoft.modelo.Rol;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Saul
 */
public class ControlRol implements ICrud<Rol> {

    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    @Override
    public Boolean insertar(Rol t) {
        con = cn.getConexion();

        try {//?=representa un parametro que se va arelacionar con el objeto p

            String sql = "INSERT INTO rol (descripcion,estado) VALUES (?,?)";
            //preparar una instruccion para ejecutar la cadena sql
            ps = con.prepareStatement(sql);
            ps.setString(1, t.getDescripcion());
            ps.setInt(2, t.getEstado());

            ps.executeUpdate(); //ejecutar la instruccion st
            JOptionPane.showMessageDialog(null, "Registro Guardado con Exito");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "ERROR");
            ex.printStackTrace();//muestra el error
        }
        return false;
    }

    @Override
    public Boolean actualizar(Rol t) {
        con = cn.getConexion();
        try {//?=representa un parametro que se va arelacionar con el objeto p
            String sql = "update rol set descripcion=?,estado=? where id=?";
            //preparar una instruccion para ejecutar la cadena sql
            ps = con.prepareStatement(sql);
            ps.setString(1, t.getDescripcion());
            ps.setInt(2, t.getEstado());
            ps.setInt(3, t.getId());

            ps.executeUpdate(); //ejecutar la instruccion st

        } catch (Exception ex) {

            ex.printStackTrace();//muestra el error
        }
        return false;
    }

    @Override
    public Boolean eliminar(int id) {
        con = cn.getConexion();
        try {//?=representa un parametro que se va arelacionar con el objeto p
            String sql = "delete from rol where id=?";
            //preparar una instruccion para ejecutar la cadena sql
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate(); //ejecutar la instruccion st
        } catch (Exception ex) {
            ex.printStackTrace();//muestra el error
        }
        return false;
    }

    @Override
    public List<Rol> obtenerPorId(Integer id) {
        List<Rol> lis = new ArrayList<>();
       
        con = cn.getConexion();

        try {
            con = cn.getConexion();
            String sql = "select  *from rol where id=?";
            ps = con.prepareStatement(sql);
            //ps.setInt(1, cod + "%");
            ps.setInt(1, id);
            //Los datos son llevados a la RAM
            rs = ps.executeQuery();
            //llenar el arraylist con la clase entidad
            while (rs.next()) {

                Rol u = new Rol();
                u.setId(rs.getInt(1));
                u.setDescripcion(rs.getString(2));
                u.setEstado(rs.getInt(3));
                
                lis.add(u);

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return  lis ;
    }

    @Override
    public List<Rol> listar() {
        List<Rol> lis = new ArrayList<>();

        try {
            con = cn.getConexion();
            String sql = "select id,descripcion,estado from rol";
            //? =equivale a un parametro
            ps = con.prepareStatement(sql);
            //st.setString(1,id);
            //relacionar el ? con su variable
            rs = ps.executeQuery();
            //llenar el arraylist con la clase entidad
            while (rs.next()) {
                Rol u = new Rol();
                u.setId(rs.getInt(1));
                u.setDescripcion(rs.getString(2));
                u.setEstado(rs.getInt(3));

                lis.add(u);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return lis;
    }

}