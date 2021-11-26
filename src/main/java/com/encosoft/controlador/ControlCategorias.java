/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.encosoft.controlador;

import com.encosoft.conexion.Conexion;
import com.encosoft.interfaces.ICategoria;
import com.encosoft.interfaces.ICrud;
import com.encosoft.modelo.Categoria;
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
public class ControlCategorias   extends ReusableValidacion implements ICategoria {

    private static PreparedStatement ps;
    private static ResultSet rs;
    private static Conexion con;

    public ControlCategorias() {
        con = Conexion.nuevaConexionDB();
    }

    @Override
    public Boolean insertar(Categoria t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean actualizar(Categoria t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Categoria> listar() {
       throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    
    }

    @Override
    public Boolean eliminar(Object id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Categoria obtenerPorId(Object id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Categoria> listarTodos() {
      List<Categoria> lis = new ArrayList<>();
      

               final String query = "select id,descripcion,estado from categorias";
              
        try {

            ps = con.obtenerConexion().prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                Categoria u = new Categoria();
                u.setId(rs.getInt(1));
                u.setDescripcion(rs.getString(2));
                u.setEstado(rs.getInt(3));

                lis.add(u);
            }
        } catch (SQLException e) {
            System.out.println("error listar roles: " + e.getMessage());
            e.printStackTrace();
        } finally {
            cerrarConexiones(rs, ps, con);
        }
        return lis;
    }

}


