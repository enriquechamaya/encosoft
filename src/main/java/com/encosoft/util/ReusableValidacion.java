package com.encosoft.util;

import com.encosoft.conexion.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author echamaya
 */
public abstract class ReusableValidacion {

    protected void cerrarConexiones(ResultSet rs, PreparedStatement ps, Conexion con) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.cerrarConexion();
            }
        } catch (SQLException e) {
            System.out.println("error al cerrar la conexión :" + e.getMessage());
        }
    }

    protected Integer obtenerUltimoID(PreparedStatement ps, ResultSet rs) throws SQLException {
        Integer id = null;
        rs = ps.getGeneratedKeys();
        if (rs.next()) {
            id = rs.getInt(1);
        }
        return id;
    }
}
