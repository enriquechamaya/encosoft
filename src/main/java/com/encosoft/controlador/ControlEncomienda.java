/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.encosoft.controlador;

import com.encosoft.conexion.Conexion;
import com.encosoft.interfaces.IEncomienda;
import com.encosoft.modelo.DetalleEncomienda;
import com.encosoft.modelo.Encomienda;
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
public class ControlEncomienda extends ReusableValidacion implements IEncomienda {

    private static PreparedStatement ps;
    private static ResultSet rs;
    private static Conexion con;

    public ControlEncomienda() {
        con = Conexion.nuevaConexionDB();
    }

    @Override
    public Boolean insertar(Encomienda t) {
        Boolean resultado = false;
        final String query = "insert into encomienda values(?,?,?,?,?,?,?,?,?,?,?);";
        try {
            ps = con.obtenerConexion().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, t.getId());
            ps.setInt(2, t.getIdagencia());
            ps.setInt(3, t.getIdtipodocumento());
            ps.setString(4, t.getNumerodocumento());
            ps.setString(5, t.getReceptorapepat());
            ps.setString(6, t.getReceptorapemat());
            ps.setString(7, t.getReceptornombres());
            ps.setInt(8, t.getIdcliente());
            ps.setString(9, t.getFecha());
            ps.setDouble(10, t.getPreciototal());
            ps.setInt(11, Constantes.ESTADO_ACTIVO);
            resultado = ps.executeUpdate() > 0;
            if (resultado) {
                Integer idEncomienda = obtenerUltimoID(ps, rs);
                ControlDetalleEncomienda controlDetalleEncomienda = new ControlDetalleEncomienda();
                List<DetalleEncomienda> detalleEncomiendas = t.getDetalleEncomiendas();
                for (DetalleEncomienda detalleEncomienda : detalleEncomiendas) {
                    detalleEncomienda.setIdencomienda(idEncomienda);
                    controlDetalleEncomienda.insertar(detalleEncomienda);
                }
            }
        } catch (SQLException e) {
            System.out.println("error insertar encomienda: " + e.getMessage());
            e.printStackTrace();
        } finally {
            cerrarConexiones(rs, ps, con);
        }
        return resultado;
    }

    @Override
    public Boolean actualizar(Encomienda t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean eliminar(Object id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Encomienda obtenerPorId(Object id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Encomienda> listar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
