package com.encosoft.controlador;

import com.encosoft.conexion.Conexion;
import com.encosoft.config.ConfigMail;
import com.encosoft.dtos.ListarEncomiendasDTO;
import com.encosoft.interfaces.IEncomienda;
import com.encosoft.modelo.Cliente;
import com.encosoft.modelo.DetalleEncomienda;
import com.encosoft.modelo.Encomienda;
import com.encosoft.util.Constantes;
import com.encosoft.util.ReusableValidacion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author echamaya
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
                t.setId(idEncomienda);
                ControlDetalleEncomienda controlDetalleEncomienda = new ControlDetalleEncomienda();
                List<DetalleEncomienda> detalleEncomiendas = t.getDetalleEncomiendas();
                for (DetalleEncomienda detalleEncomienda : detalleEncomiendas) {
                    detalleEncomienda.setIdencomienda(idEncomienda);
                    controlDetalleEncomienda.insertar(detalleEncomienda);
                }

                // enviar correo
                ControlClientes controlClientes = new ControlClientes();
                Cliente c = controlClientes.obtenerPorId(t.getIdcliente());
                ConfigMail configMail = new ConfigMail();
                configMail.enviarCorreoRegistroEncomienda(c.getEmail(), t);
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
        Boolean resultado = false;
        final String query = "update encomienda set idagencia=?,idtipodoc=?,nrodocumento=?,receptorapepat=?,receptorapemat=?,receptornombre=?,idcliente=?,fecha=?,preciototal=?,estado=? where id=?;";
        try {
            ps = con.obtenerConexion().prepareStatement(query);
            ps.setInt(1, t.getIdagencia());
            ps.setInt(2, t.getIdtipodocumento());
            ps.setString(3, t.getNumerodocumento());
            ps.setString(4, t.getReceptorapepat());
            ps.setString(5, t.getReceptorapemat());
            ps.setString(6, t.getReceptornombres());
            ps.setInt(7, t.getIdcliente());
            ps.setString(8, t.getFecha());
            ps.setDouble(8, t.getPreciototal());
            ps.setInt(9, Constantes.ESTADO_ACTIVO);
            ps.setInt(10, t.getId());
            resultado = ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("error actualizar encomienda: " + e.getMessage());
            e.printStackTrace();
        } finally {
            cerrarConexiones(rs, ps, con);
        }
        return resultado;
    }

    @Override
    public List<Encomienda> listar() {
        List<Encomienda> lista = new ArrayList<>();
        final String query = "select * from encomienda;";
        try {
            ps = con.obtenerConexion().prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                Encomienda encomienda = new Encomienda();
                encomienda.setId(rs.getInt(1));
                encomienda.setIdagencia(rs.getInt(2));
                encomienda.setIdtipodocumento(rs.getInt(3));
                encomienda.setNumerodocumento(rs.getString(4));
                encomienda.setReceptorapepat(rs.getString(5));
                encomienda.setReceptorapemat(rs.getString(6));
                encomienda.setReceptornombres(rs.getString(7));
                encomienda.setIdcliente(rs.getInt(8));
                encomienda.setFecha(rs.getString(9));
                encomienda.setPreciototal(rs.getInt(10));
                encomienda.setEstado(rs.getInt(10));
                lista.add(encomienda);
            }
        } catch (SQLException e) {
            System.out.println("error listar encomienda: " + e.getMessage());
            e.printStackTrace();
        } finally {
            cerrarConexiones(rs, ps, con);
        }
        return lista;
    }

    @Override
    public Boolean eliminar(Object id) {
        Boolean resultado = false;
        final String query = "update encomienda set estado = 0 where id = ?;";
        try {
            ps = con.obtenerConexion().prepareStatement(query);
            ps.setInt(1, Integer.parseInt(id.toString()));
            resultado = ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("error eliminar clientes: " + e.getMessage());
            e.printStackTrace();
        } finally {
            cerrarConexiones(rs, ps, con);
        }
        return resultado;
    }

    @Override
    public Encomienda obtenerPorId(Object id) {
        Encomienda encomienda = new Encomienda();
        final String query = "select * from encomienda where id = ?;";
        try {
            ps = con.obtenerConexion().prepareStatement(query);
            ps.setInt(1, Integer.parseInt(id.toString()));
            rs = ps.executeQuery();
            while (rs.next()) {
                encomienda.setId(rs.getInt(1));
                encomienda.setIdagencia(rs.getInt(2));
                encomienda.setIdtipodocumento(rs.getInt(3));
                encomienda.setNumerodocumento(rs.getString(4));
                encomienda.setReceptorapepat(rs.getString(5));
                encomienda.setReceptorapemat(rs.getString(6));
                encomienda.setReceptornombres(rs.getString(7));
                encomienda.setIdcliente(rs.getInt(8));
                encomienda.setFecha(rs.getString(9));
                encomienda.setPreciototal(rs.getInt(10));
                encomienda.setEstado(rs.getInt(10));
            }
        } catch (SQLException e) {
            System.out.println("error obtenerPorId encomienda: " + e.getMessage());
            e.printStackTrace();
        } finally {
            cerrarConexiones(rs, ps, con);
        }
        return encomienda;
    }

    @Override
    public List<ListarEncomiendasDTO> listarEncomiendasPersonalizado(String cliente, String receptor) {
        List<ListarEncomiendasDTO> lista = new ArrayList<>();
        final String query = "SELECT e.id, a.`descripcion` agencia, CONCAT(c.`apepat`, ' ', c.`apemat`, ' ', c.`nombre`) cliente, "
                + "CONCAT(e.`receptorapepat`, ' ', e.`receptorapemat`, ' ', e.`receptornombre`) receptor, "
                + "date_format(e.`fecha`, '%d/%m/%Y %H:%m:%s') fecha, "
                + "e.`preciototal`, IF(e.`estado` = 1, 'ACTIVO', 'INACTIVO') estado "
                + "FROM encomienda e "
                + "INNER JOIN agencias a ON e.`idagencia` = a.`id` "
                + "INNER JOIN clientes c ON e.`idcliente` = c.`id` "
                + "WHERE CONCAT(c.`apepat`, ' ', c.`apemat`, ' ', c.`nombre`) LIKE ? AND "
                + "CONCAT(e.`receptorapepat`, ' ', e.`receptorapemat`, ' ', e.`receptornombre`) LIKE ? ";

        try {
            ps = con.obtenerConexion().prepareStatement(query);
            ps.setString(1, cliente + "%");
            ps.setString(2, receptor + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                ListarEncomiendasDTO encomienda = new ListarEncomiendasDTO();
                encomienda.setId(rs.getInt(1));
                encomienda.setAgencia(rs.getString(2));
                encomienda.setCliente(rs.getString(3));
                encomienda.setReceptor(rs.getString(4));
                encomienda.setFecha(rs.getString(5));
                encomienda.setPrecio(rs.getString(6));
                encomienda.setEstado(rs.getString(7));
                lista.add(encomienda);
            }
        } catch (SQLException e) {
            System.out.println("error listar encomienda: " + e.getMessage());
            e.printStackTrace();
        } finally {
            cerrarConexiones(rs, ps, con);
        }
        return lista;
    }

}
