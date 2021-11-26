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
import java.sql.Statement;
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

        final String query = "INSERT INTO productos (idcategoria,descripcion,estado) VALUES (?,?,?)";
        try {
            ps = con.obtenerConexion().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
       
            ps.setInt(1, t.getIdcategoria());
            ps.setString(2, t.getDescripcion());
            ps.setInt(3, Constantes.ESTADO_ACTIVO);

            resultado = ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("error insertar producto: " + e.getMessage());
            e.printStackTrace();
        } finally {
            cerrarConexiones(rs, ps, con);
        }
        return resultado;
    }

    @Override
    public Boolean actualizar(Productos t) {
        Boolean resultado = false;

        final String query = "update productos set idcategoria=?,descripcion=?,estado=? where id=?";
        try {
            ps = con.obtenerConexion().prepareStatement(query);
            ps.setInt(1, t.getIdcategoria());
            ps.setString(2, t.getDescripcion());
            ps.setInt(3, t.getEstado());
            ps.setInt(4, t.getId());

            resultado = ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("error actualizar producto: " + e.getMessage());
            e.printStackTrace();
        } finally {
            cerrarConexiones(rs, ps, con);
        }
        return resultado;
    }

    @Override
    public Boolean eliminar(Object id) {
        Boolean resultado = false;

        final String query = "update productos set estado= 0 where id=?";
        try {
            ps = con.obtenerConexion().prepareStatement(query);
            ps.setInt(1, Integer.parseInt(id.toString()));
            resultado = ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("error eliminar producto: " + e.getMessage());
            e.printStackTrace();
        } finally {
            cerrarConexiones(rs, ps, con);
        }
        return resultado;
    }

    @Override
    public List<Productos> obtenerPorId(Integer id) {
        List<Productos> lis = new ArrayList<>();

    

            final String query =  "select  *from productos where id=?";
             try {
            ps = con.obtenerConexion().prepareStatement(query);
           
            ps.setInt(1, id);
         
            rs = ps.executeQuery();
           
            while (rs.next()) {

                Productos u = new Productos();
                u.setId(rs.getInt(1));
                u.setIdcategoria(rs.getInt(2));
                u.setDescripcion(rs.getString(3));
                u.setEstado(rs.getInt(4));

                lis.add(u);

         }
       } catch (SQLException e) {
            System.out.println("error obtenerPorId producto: " + e.getMessage());
            e.printStackTrace();
        } finally {
            cerrarConexiones(rs, ps, con);
        }
        return lis;
    }

    @Override
    public List<Productos> listar() {
        List<Productos> lis = new ArrayList<>();



     
             final String query = "select * from productos where estado = 1;";
              try {

            ps = con.obtenerConexion().prepareStatement(query);
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
        } catch (SQLException e) {
            System.out.println("error listar producto: " + e.getMessage());
            e.printStackTrace();
        } finally {
            cerrarConexiones(rs, ps, con);
        }
        return lis;
    }


    @Override
    public Productos obtenerPorId(Object id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Productos> listarTodos() {
              List<Productos> lis = new ArrayList<>();
    
               //
             final String query = "select id,idcategoria,descripcion,estado from productos";
               //final String query = "select *from categorias,productos where categorias.id = productos.idcategoria";
        try {

            ps = con.obtenerConexion().prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                Productos u = new Productos();
                u.setId(rs.getInt(1));
                u.setIdcategoria(rs.getInt(2));
                u.setDescripcion(rs.getString(3));
                u.setEstado(rs.getInt(4));

                lis.add(u);
            }
        } catch (SQLException e) {
            System.out.println("error listar productos: " + e.getMessage());
            e.printStackTrace();
        } finally {
            cerrarConexiones(rs, ps, con);
        }
        return lis;
    }

}