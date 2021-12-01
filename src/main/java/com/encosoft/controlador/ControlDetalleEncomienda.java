package com.encosoft.controlador;

import com.encosoft.conexion.Conexion;
import com.encosoft.dtos.ListarDetalleEncomiendasDTO;
import com.encosoft.interfaces.IDetalleEncomienda;
import com.encosoft.modelo.DetalleEncomienda;
import com.encosoft.util.Constantes;
import com.encosoft.util.ReusableValidacion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
            System.out.println("error insertar detalle_encomienda: " + e.getMessage());
            e.printStackTrace();
        } finally {
            cerrarConexiones(rs, ps, con);
        }
        return resultado;
    }

    @Override
    public Boolean actualizar(DetalleEncomienda t) {
        Boolean resultado = false;
        final String query = "update detalle_encomienda set idencomienda=?,idproducto=?,descripcion=?,cantidad=?,preciounitario=?,peso=?,estado=? where id=?;";
        try {
            ps = con.obtenerConexion().prepareStatement(query);
            ps.setInt(1, t.getIdencomienda());
            ps.setInt(2, t.getIdproducto());
            ps.setString(3, t.getDescripcion());
            ps.setInt(4, t.getCantidad());
            ps.setDouble(5, t.getPreciounitario());
            ps.setInt(6, t.getPeso());
            ps.setInt(9, Constantes.ESTADO_ACTIVO);
            ps.setInt(10, t.getId());
            resultado = ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("error actualizar detalle_encomienda: " + e.getMessage());
            e.printStackTrace();
        } finally {
            cerrarConexiones(rs, ps, con);
        }
        return resultado;
    }

    @Override
    public List<DetalleEncomienda> listar() {
        List<DetalleEncomienda> lista = new ArrayList<>();
        final String query = "select * from detalle_encomienda;";
        try {
            ps = con.obtenerConexion().prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                DetalleEncomienda detalleEncomienda = new DetalleEncomienda();
                detalleEncomienda.setId(rs.getInt(1));
                detalleEncomienda.setIdencomienda(rs.getInt(2));
                detalleEncomienda.setIdproducto(rs.getInt(3));
                detalleEncomienda.setDescripcion(rs.getString(4));
                detalleEncomienda.setCantidad(rs.getInt(5));
                detalleEncomienda.setPreciounitario(rs.getDouble(6));
                detalleEncomienda.setPeso(rs.getInt(7));
                detalleEncomienda.setEstado(rs.getInt(8));
                lista.add(detalleEncomienda);
            }
        } catch (SQLException e) {
            System.out.println("error listar detalleEncomienda: " + e.getMessage());
            e.printStackTrace();
        } finally {
            cerrarConexiones(rs, ps, con);
        }
        return lista;
    }

    @Override
    public Boolean eliminar(Object id) {
        Boolean resultado = false;
        final String query = "update detalle_encomienda set estado = 0 where id = ?;";
        try {
            ps = con.obtenerConexion().prepareStatement(query);
            ps.setInt(1, Integer.parseInt(id.toString()));
            resultado = ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("error eliminar detalle_encomienda: " + e.getMessage());
            e.printStackTrace();
        } finally {
            cerrarConexiones(rs, ps, con);
        }
        return resultado;
    }

    @Override
    public DetalleEncomienda obtenerPorId(Object id) {
        DetalleEncomienda detalleEncomienda = new DetalleEncomienda();
        final String query = "select * from detalle_encomienda where id = ?;";
        try {
            ps = con.obtenerConexion().prepareStatement(query);
            ps.setInt(1, Integer.parseInt(id.toString()));
            rs = ps.executeQuery();
            while (rs.next()) {
                detalleEncomienda.setId(rs.getInt(1));
                detalleEncomienda.setIdencomienda(rs.getInt(2));
                detalleEncomienda.setIdproducto(rs.getInt(3));
                detalleEncomienda.setDescripcion(rs.getString(4));
                detalleEncomienda.setCantidad(rs.getInt(5));
                detalleEncomienda.setPreciounitario(rs.getDouble(6));
                detalleEncomienda.setPeso(rs.getInt(7));
                detalleEncomienda.setEstado(rs.getInt(8));
            }
        } catch (SQLException e) {
            System.out.println("error obtenerPorId detalleEncomienda: " + e.getMessage());
            e.printStackTrace();
        } finally {
            cerrarConexiones(rs, ps, con);
        }
        return detalleEncomienda;
    }

    @Override
    public List<ListarDetalleEncomiendasDTO> listarDetalleEncomiendaPersonalizado(int idEncomienda) {
        List<ListarDetalleEncomiendasDTO> lista = new ArrayList<>();
        final String query = "SELECT c.`descripcion` categoria, p.`descripcion` producto, "
                + "de.`peso`, de.`cantidad`, de.`preciounitario`, "
                + "de.`cantidad` * de.preciounitario total "
                + "FROM detalle_encomienda de "
                + "INNER JOIN productos p ON de.`idproducto` = p.`id` "
                + "INNER JOIN categorias c ON p.`idcategoria` = c.`id` "
                + "WHERE de.`idencomienda` = ?;";
        try {
            ps = con.obtenerConexion().prepareStatement(query);
            ps.setInt(1, idEncomienda);
            rs = ps.executeQuery();
            while (rs.next()) {
                ListarDetalleEncomiendasDTO detalleEncomienda = new ListarDetalleEncomiendasDTO();
                detalleEncomienda.setCategoria(rs.getString(1));
                detalleEncomienda.setProducto(rs.getString(2));
                detalleEncomienda.setPeso(rs.getString(3));
                detalleEncomienda.setCantidad(rs.getString(4));
                detalleEncomienda.setPrecioUnitario(rs.getString(5));
                detalleEncomienda.setTotal(rs.getString(6));
                lista.add(detalleEncomienda);
            }
        } catch (SQLException e) {
            System.out.println("error listar detalleEncomienda: " + e.getMessage());
            e.printStackTrace();
        } finally {
            cerrarConexiones(rs, ps, con);
        }
        return lista;
    }

}
