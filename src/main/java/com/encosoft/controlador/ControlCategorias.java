/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.encosoft.controlador;

import com.encosoft.conexion.Conexion;
import com.encosoft.interfaces.ICrud;
import com.encosoft.modelo.Categoria;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Saul
 */
public class ControlCategorias implements ICrud<Categoria>{
    
       Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    @Override
    public Boolean insertar(Categoria t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean actualizar(Categoria t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean eliminar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Categoria> obtenerPorId(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Categoria> listar() {
      List<Categoria> lis = new ArrayList<>();

        try {
            con = cn.getConexion();
            String sql = "select id,descripcion,estado from categorias";
            //? =equivale a un parametro
            ps = con.prepareStatement(sql);
            //st.setString(1,id);
            //relacionar el ? con su variable
            rs = ps.executeQuery();
            //llenar el arraylist con la clase entidad
            while (rs.next()) {
                Categoria u = new Categoria();
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
