/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.encosoft.controlador;

import com.encosoft.conexion.Conexion;
import com.encosoft.interfaces.ICrud;
import com.encosoft.modelo.Productos;
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
public class ControlProductos implements ICrud<Productos> {

    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    @Override
    public Boolean insertar(Productos t) {
        con = cn.getConexion();

        try {//?=representa un parametro que se va arelacionar con el objeto p

            String sql = "INSERT INTO productos (idcategoria,descripcion,estado) VALUES (?,?,?)";
            //preparar una instruccion para ejecutar la cadena sql
            ps = con.prepareStatement(sql);
            ps.setInt(1, t.getIdcategoria());
            ps.setString(2, t.getDescripcion());
            ps.setInt(3, t.getEstado());

            ps.executeUpdate(); //ejecutar la instruccion st
            JOptionPane.showMessageDialog(null, "Registro Guardado con Exito");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "ERROR");
            ex.printStackTrace();//muestra el error
        }
        return false;
    }

    @Override
    public Boolean actualizar(Productos t) {
        con = cn.getConexion();
        try {//?=representa un parametro que se va arelacionar con el objeto p
            String sql = "update productos set idcategoria=?,descripcion=?,estado=? where id=?";
            //preparar una instruccion para ejecutar la cadena sql
            ps = con.prepareStatement(sql);
            ps.setInt(1, t.getIdcategoria());
            ps.setString(2, t.getDescripcion());
            ps.setInt(3, t.getEstado());
            ps.setInt(4, t.getId());

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
            String sql = "delete from productos where id=?";
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
    public List<Productos> obtenerPorId(Integer id) {
        List<Productos> lis = new ArrayList<>();

        con = cn.getConexion();

        try {
            con = cn.getConexion();
            String sql = "select  *from productos where id=?";
            ps = con.prepareStatement(sql);
            //ps.setInt(1, cod + "%");
            ps.setInt(1, id);
            //Los datos son llevados a la RAM
            rs = ps.executeQuery();
            //llenar el arraylist con la clase entidad
            while (rs.next()) {

                Productos u = new Productos();
                u.setId(rs.getInt(1));
                u.setIdcategoria(rs.getInt(2));
                u.setDescripcion(rs.getString(3));
                u.setEstado(rs.getInt(4));

                lis.add(u);

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return lis;
    }

    @Override
    public List<Productos> listar() {
         List<Productos> lis = new ArrayList<>();

        try {
            con = cn.getConexion();
            String sql = "select id,idcategoria,descripcion,estado from productos";
            //? =equivale a un parametro
            ps = con.prepareStatement(sql);
            //st.setString(1,id);
            //relacionar el ? con su variable
            rs = ps.executeQuery();
            //llenar el arraylist con la clase entidad
            while (rs.next()) {
                Productos u = new Productos();
                u.setId(rs.getInt(1));
                u.setIdcategoria(rs.getInt(2));
                u.setDescripcion(rs.getString(3));
                u.setEstado(rs.getInt(4));


                lis.add(u);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return lis;
    }
}
