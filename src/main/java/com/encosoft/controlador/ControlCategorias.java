/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.encosoft.controlador;

import com.encosoft.conexion.Conexion;
import com.encosoft.interfaces.ICategoria;
import com.encosoft.modelo.Categoria;
import com.encosoft.util.Constantes;
import com.encosoft.util.ReusableValidacion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 *
 * @author Saul
 */
public class ControlCategorias extends ReusableValidacion implements ICategoria {

    private static PreparedStatement ps;
    private static ResultSet rs;
    private static Conexion con;

    public ControlCategorias() {
        con = Conexion.nuevaConexionDB();
    }

    @Override
    public Boolean insertar(Categoria t) {
        Boolean resultado = false;
        final String query = "insert into categorias values(?,?,?);";
        try {
            ps = con.obtenerConexion().prepareStatement(query);
            ps.setInt(1, t.getId());
            ps.setString(2, t.getDescripcion());
            ps.setInt(3, Constantes.ESTADO_ACTIVO);
            resultado = ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("error insertar categorias: " + e.getMessage());
            e.printStackTrace();
        } finally {
            cerrarConexiones(rs, ps, con);
        }
        return resultado;
    }

    @Override
    public Boolean actualizar(Categoria t) {
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
    public List<Categoria> listar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
