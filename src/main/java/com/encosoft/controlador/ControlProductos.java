/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.encosoft.controlador;

import com.encosoft.conexion.Conexion;
import com.encosoft.interfaces.IProductos;
import com.encosoft.modelo.Productos;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class ControlProductos implements IProductos {

    private static PreparedStatement ps;
    private static ResultSet rs;
    private static Conexion con;

    public ControlProductos() {
        con = Conexion.nuevaConexionDB();
    }

    @Override
    public Boolean insertar(Productos t) {

        try {//?=representa un parametro que se va arelacionar con el objeto p

            String sql = "INSERT INTO productos (idcategoria,descripcion,estado) VALUES (?,?,?)";
            //preparar una instruccion para ejecutar la cadena sql
            ps = con.obtenerConexion().prepareStatement(sql);
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

        try {//?=representa un parametro que se va arelacionar con el objeto p
            String sql = "update productos set idcategoria=?,descripcion=?,estado=? where id=?";
            //preparar una instruccion para ejecutar la cadena sql
            ps = con.obtenerConexion().prepareStatement(sql);
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
    public Boolean eliminar(Object id) {

        try {//?=representa un parametro que se va arelacionar con el objeto p
            String sql = "delete from productos where id=?";
            //preparar una instruccion para ejecutar la cadena sql
            ps = con.obtenerConexion().prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(id.toString()));
            ps.executeUpdate(); //ejecutar la instruccion st
        } catch (Exception ex) {
            ex.printStackTrace();//muestra el error
        }
        return false;
    }

    @Override
    public List<Productos> obtenerPorId(Integer id) {
        List<Productos> lis = new ArrayList<>();

        try {

            String sql = "select  *from productos where id=?";
            ps = con.obtenerConexion().prepareStatement(sql);
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

            String sql = "select id,idcategoria,descripcion,estado from productos";
            //? =equivale a un parametro
            ps = con.obtenerConexion().prepareStatement(sql);
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

    @Override
    public Productos obtenerPorId(Object id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
