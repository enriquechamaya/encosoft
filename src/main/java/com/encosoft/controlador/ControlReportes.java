package com.encosoft.controlador;

import com.encosoft.modelo.Agencia;
import com.encosoft.modelo.Categoria;
import com.encosoft.modelo.Cliente;
import com.encosoft.modelo.Encomienda;
import com.encosoft.modelo.Productos;
import com.encosoft.modelo.Rol;
import com.encosoft.modelo.TipoDocumento;
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
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * @author echamaya
 */
public class ControlReportes {

    Document documento = null;
    PdfPTable tabla = null;
    String rutaDescarga = "";

    private void inicioReporte(String nombreArchivo, String tituloReporte) throws FileNotFoundException, DocumentException {
        documento = new Document();
        rutaDescarga = Utilitario.obtenerRutaReporte(nombreArchivo);
        FileOutputStream ficheroPDF = new FileOutputStream(rutaDescarga);
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

    private void finReporte() throws DocumentException, IOException {
        documento.add(tabla);
        documento.close();
        Desktop.getDesktop().open(new File(rutaDescarga));
    }

    public void exportarClientes() throws FileNotFoundException, DocumentException, IOException {
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

        finReporte();
    }

    public void exportarEncomiendas() throws FileNotFoundException, DocumentException, IOException {
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

        finReporte();
    }

    public void exportarUsuarios() throws FileNotFoundException, DocumentException, IOException {
        inicioReporte(Constantes.REPORTE_USUARIOS, Constantes.TITULO_REPORTE_USUARIOS);
        cabeceraReporte("ID", "USUARIO", "ESTADO");

        ControlUsuarios controlUsuarios = new ControlUsuarios();
        /*List<Usuario> lista = controlUsuarios.listar();
        for (Usuario x : lista) {
            tabla.addCell("" + x.getId());
            tabla.addCell(x.getUsuario());
            tabla.addCell(Utilitario.obtenerEstadoActivoInactivo(x.getEstado()));
        }*/

        finReporte();
    }

    public void exportarAgencias() throws FileNotFoundException, DocumentException, IOException {
        inicioReporte(Constantes.REPORTE_AGENCIAS, Constantes.TITULO_REPORTE_AGENCIAS);
        cabeceraReporte("ID", "DESCRIPCIÓN", "ESTADO");

        ControlAgencia control = new ControlAgencia();
        List<Agencia> lista = control.listar();
        for (Agencia x : lista) {
            tabla.addCell("" + x.getId());
            tabla.addCell(x.getDescripcion());
            tabla.addCell(Utilitario.obtenerEstadoActivoInactivo(x.getEstado()));
        }

        finReporte();
    }

    public void exportarProductos() throws FileNotFoundException, DocumentException, IOException {
        inicioReporte(Constantes.REPORTE_PRODUCTOS, Constantes.TITULO_REPORTE_PRODUCTOS);
        cabeceraReporte("ID", "DESCRIPCIÓN", "ESTADO");

        ControlProductos control = new ControlProductos();
        List<Productos> lista = control.listar();
        for (Productos x : lista) {
            tabla.addCell("" + x.getId());
            tabla.addCell(x.getDescripcion());
            tabla.addCell(Utilitario.obtenerEstadoActivoInactivo(x.getEstado()));
        }

        finReporte();
    }

    public void exportarCategorias() throws FileNotFoundException, DocumentException, IOException {
        inicioReporte(Constantes.REPORTE_CATEGORIAS, Constantes.TITULO_REPORTE_CATEGORIAS);
        cabeceraReporte("ID", "DESCRIPCIÓN", "ESTADO");

        ControlCategorias control = new ControlCategorias();
        List<Categoria> lista = control.listar();
        for (Categoria x : lista) {
            tabla.addCell("" + x.getId());
            tabla.addCell(x.getDescripcion());
            tabla.addCell(Utilitario.obtenerEstadoActivoInactivo(x.getEstado()));
        }

        finReporte();
    }

    public void exportarRol() throws FileNotFoundException, DocumentException, IOException {
        inicioReporte(Constantes.REPORTE_ROLES, Constantes.TITULO_REPORTE_ROLES);
        cabeceraReporte("ID", "DESCRIPCIÓN", "ESTADO");

        ControlRol control = new ControlRol();
        List<Rol> lista = control.listar();
        for (Rol x : lista) {
            tabla.addCell("" + x.getId());
            tabla.addCell(x.getDescripcion());
            tabla.addCell(Utilitario.obtenerEstadoActivoInactivo(x.getEstado()));
        }

        finReporte();
    }

    public void exportarTipoDocumentos() throws FileNotFoundException, DocumentException, IOException {
        inicioReporte(Constantes.REPORTE_TIPO_DOCUMENTOS, Constantes.TITULO_REPORTE_TIPO_DOCUMENTOS);
        cabeceraReporte("ID", "DESCRIPCIÓN", "ESTADO");

        ControlTipoDocumento control = new ControlTipoDocumento();
        List<TipoDocumento> lista = control.listar();
        for (TipoDocumento x : lista) {
            tabla.addCell("" + x.getId());
            tabla.addCell(x.getDescripcion());
            tabla.addCell(Utilitario.obtenerEstadoActivoInactivo(x.getEstado()));
        }

        finReporte();
    }

}
