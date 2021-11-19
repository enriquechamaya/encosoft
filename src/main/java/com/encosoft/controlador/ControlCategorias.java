/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.encosoft.controlador;

import com.encosoft.conexion.Conexion;
import com.encosoft.interfaces.ICrud;
import com.encosoft.modelo.Categoria;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Saul
 */
public class ControlCategorias implements ICrud<Categoria> {

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
        List<Categoria> lis = new ArrayList<>();

        try {

            String sql = "select id,descripcion,estado from categorias";
            //? =equivale a un parametro
            ps = con.obtenerConexion().prepareStatement(sql);
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

    @Override
    public Boolean eliminar(Object id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Categoria obtenerPorId(Object id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
