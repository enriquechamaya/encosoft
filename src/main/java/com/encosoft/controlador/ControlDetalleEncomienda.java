package com.encosoft.controlador;

import com.encosoft.conexion.Conexion;
import com.encosoft.interfaces.IDetalleEncomienda;
import com.encosoft.modelo.DetalleEncomienda;
import com.encosoft.util.Constantes;
import com.encosoft.util.ReusableValidacion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author echamaya
 */
public class ControlDetalleEncomienda extends ReusableValidacion implements IDetalleEncomienda {

    private static PreparedStatement ps;
    private static ResultSet rs;
    private static Conexion con;

    public ControlDetalleEncomienda() {
        con = Conexion.nuevaConexionDB();
    }

    @Override
    public Boolean insertar(DetalleEncomienda t) {
        Boolean resultado = false;
        final String query = "insert into detalle_encomienda values(?,?,?,?,?,?,?,?);";
        try {
            ps = con.obtenerConexion().prepareStatement(query);
            ps.setInt(1, t.getId());
            ps.setInt(2, t.getIdencomienda());
            ps.setInt(3, t.getIdproducto());
            ps.setString(4, t.getDescripcion());
            ps.setInt(5, t.getCantidad());
            ps.setDouble(6, t.getPreciounitario());
            ps.setInt(7, t.getPeso());
            ps.setInt(8, Constantes.ESTADO_ACTIVO);
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
    public Boolean actualizar(DetalleEncomienda t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean eliminar(Object id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DetalleEncomienda obtenerPorId(Object id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<DetalleEncomienda> listar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
