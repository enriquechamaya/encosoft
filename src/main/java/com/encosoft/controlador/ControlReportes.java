package com.encosoft.controlador;

import com.encosoft.modelo.Agencia;
import com.encosoft.modelo.Categoria;
import com.encosoft.modelo.Cliente;
import com.encosoft.modelo.Encomienda;
import com.encosoft.modelo.Productos;
import com.encosoft.modelo.Rol;
import com.encosoft.util.Constantes;
import com.encosoft.util.Utilitario;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

/**
 * @author echamaya
 */
public class ControlReportes {

    Document documento = new Document();
    PdfPTable tabla = null;

    private void inicioReporte(String nombreArchivo, String tituloReporte) throws FileNotFoundException, DocumentException {
        FileOutputStream ficheroPDF = new FileOutputStream(Utilitario.obtenerRutaReporte(nombreArchivo));
        PdfWriter.getInstance(documento, ficheroPDF);
        documento.open();

        Paragraph titulo = new Paragraph("REPORTE DE " + tituloReporte + "\n\n",
                FontFactory.getFont("arial",
                        22,
                        Font.BOLD,
                        BaseColor.BLUE
                ));

        documento.add(titulo);
    }

    private void cabeceraReporte(String... cabeceras) {
        tabla = new PdfPTable(cabeceras.length);
        for (String cabecera : cabeceras) {
            tabla.addCell(cabecera);
        }
    }

    private void finReporte(Document documento, PdfPTable tabla) throws DocumentException {
        documento.add(tabla);
        documento.close();
    }

    public void exportarClientes() throws FileNotFoundException, DocumentException {
        inicioReporte(Constantes.REPORTE_CLIENTES, Constantes.TITULO_REPORTE_CLIENTES);
        cabeceraReporte("ID", "CLIENTE", "NRO DOC", "EMAIL", "ESTADO");

        ControlClientes control = new ControlClientes();
        List<Cliente> lista = control.listar();
        for (Cliente x : lista) {
            tabla.addCell("" + x.getId());
            tabla.addCell(Utilitario.concatenar(x.getApepat() + " " + x.getApemat() + " " + x.getNombre()));
            tabla.addCell(x.getNrodocumento());
            tabla.addCell(x.getEmail());
            tabla.addCell(Utilitario.obtenerEstadoActivoInactivo(x.getEstado()));
        }

        finReporte(documento, tabla);
    }

    public void exportarEncomiendas() throws FileNotFoundException, DocumentException {
        inicioReporte(Constantes.REPORTE_ENCOMIENDAS, Constantes.TITULO_REPORTE_ENCOMIENDAS);
        cabeceraReporte("ID", "RECEPTOR", "FECHA", "PRECIO TOTAL", "ESTADO");

        ControlEncomienda control = new ControlEncomienda();
        List<Encomienda> lista = control.listar();
        for (Encomienda x : lista) {
            tabla.addCell("" + x.getId());
            tabla.addCell(Utilitario.concatenar(x.getReceptorapepat() + " " + x.getReceptorapemat() + " " + x.getReceptornombres(), " (", x.getNumerodocumento(), ")"));
            tabla.addCell(x.getFecha());
            tabla.addCell("S/. " + x.getPreciototal());
            tabla.addCell(Utilitario.obtenerEstadoActivoInactivo(x.getEstado()));
        }

        finReporte(documento, tabla);
    }

    public void exportarUsuarios() throws FileNotFoundException, DocumentException {
        inicioReporte(Constantes.REPORTE_USUARIOS, Constantes.TITULO_REPORTE_USUARIOS);
        cabeceraReporte("ID", "USUARIO", "ESTADO");

        ControlUsuarios controlUsuarios = new ControlUsuarios();
        /*List<Usuario> lista = controlUsuarios.listar();
        for (Usuario x : lista) {
            tabla.addCell("" + x.getId());
            tabla.addCell(x.getUsuario());
            tabla.addCell(Utilitario.obtenerEstadoActivoInactivo(x.getEstado()));
        }*/

        finReporte(documento, tabla);
    }

    public void exportarAgencias() throws FileNotFoundException, DocumentException {
        inicioReporte(Constantes.REPORTE_AGENCIAS, Constantes.TITULO_REPORTE_AGENCIAS);
        cabeceraReporte("ID", "DESCRIPCIÓN", "ESTADO");

        ControlAgencia control = new ControlAgencia();
        List<Agencia> lista = control.listar();
        for (Agencia x : lista) {
            tabla.addCell("" + x.getId());
            tabla.addCell(x.getDescripcion());
            tabla.addCell(Utilitario.obtenerEstadoActivoInactivo(x.getEstado()));
        }

        finReporte(documento, tabla);
    }

    public void exportarProductos() throws FileNotFoundException, DocumentException {
        inicioReporte(Constantes.REPORTE_AGENCIAS, Constantes.TITULO_REPORTE_AGENCIAS);
        cabeceraReporte("ID", "DESCRIPCIÓN", "ESTADO");

        ControlProductos control = new ControlProductos();
        List<Productos> lista = control.listar();
        for (Productos x : lista) {
            tabla.addCell("" + x.getId());
            tabla.addCell(x.getDescripcion());
            tabla.addCell(Utilitario.obtenerEstadoActivoInactivo(x.getEstado()));
        }

        finReporte(documento, tabla);
    }

    public void exportarCategorias() throws FileNotFoundException, DocumentException {
        inicioReporte(Constantes.REPORTE_AGENCIAS, Constantes.TITULO_REPORTE_AGENCIAS);
        cabeceraReporte("ID", "DESCRIPCIÓN", "ESTADO");

        ControlCategorias control = new ControlCategorias();
        List<Categoria> lista = control.listar();
        for (Categoria x : lista) {
            tabla.addCell("" + x.getId());
            tabla.addCell(x.getDescripcion());
            tabla.addCell(Utilitario.obtenerEstadoActivoInactivo(x.getEstado()));
        }

        finReporte(documento, tabla);
    }

    public void exportarRol() throws FileNotFoundException, DocumentException {
        inicioReporte(Constantes.REPORTE_AGENCIAS, Constantes.TITULO_REPORTE_AGENCIAS);
        cabeceraReporte("ID", "DESCRIPCIÓN", "ESTADO");

        ControlRol control = new ControlRol();
        List<Rol> lista = control.listar();
        for (Rol x : lista) {
            tabla.addCell("" + x.getId());
            tabla.addCell(x.getDescripcion());
            tabla.addCell(Utilitario.obtenerEstadoActivoInactivo(x.getEstado()));
        }

        finReporte(documento, tabla);
    }

}
