package com.encosoft.controlador;

import com.encosoft.dtos.ListarDetalleEncomiendasDTO;
import com.encosoft.dtos.ListarEncomiendasDTO;
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
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
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
    private final Font estiloTitulo = FontFactory.getFont(FontFactory.COURIER, 14, Font.BOLD, BaseColor.BLACK);
    private final Font estiloTituloDetalle = FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLD, BaseColor.BLACK);

    private final Font estiloDato = FontFactory.getFont(FontFactory.COURIER, 14, Font.NORMAL, BaseColor.BLACK);
    private final Font estiloDatoDetalle = FontFactory.getFont(FontFactory.COURIER, 10, Font.NORMAL, BaseColor.BLACK);

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

    private void inicioReporte(String nombreArchivo) throws FileNotFoundException, DocumentException {
        documento = new Document();
        rutaDescarga = Utilitario.obtenerRutaReporte(nombreArchivo);
        FileOutputStream ficheroPDF = new FileOutputStream(rutaDescarga);
        PdfWriter.getInstance(documento, ficheroPDF);
        documento.open();

        Paragraph titulo = new Paragraph(Constantes.TITULO_REPORTE_DESCARGA_TIPO_DOCUMENTOS + " \n\n",
                FontFactory.getFont("Courier",
                        22,
                        Font.BOLD,
                        BaseColor.BLACK
                ));
        titulo.setAlignment(Element.ALIGN_CENTER);
        documento.add(titulo);
    }

    private void cabeceraReporteDescarga(String... cabeceras) {
        tabla = new PdfPTable(cabeceras.length);
        tabla.setWidthPercentage(100);
        for (String cabecera : cabeceras) {
            tabla.addCell(cabecera);
        }
    }

    private void finReporte() throws DocumentException, IOException {
        documento.add(tabla);
        documento.close();
        Desktop.getDesktop().open(new File(rutaDescarga));
    }

    private void finReporteDescarga() throws DocumentException, IOException {
        documento.close();
        Desktop.getDesktop().open(new File(rutaDescarga));
    }

    void agregarFila(PdfPTable tabla, String titulo, String dato) {
        PdfPCell celda1 = new PdfPCell(new Phrase(titulo, estiloTitulo));
        PdfPCell celda2 = new PdfPCell(new Phrase(dato, estiloDato));
        celda1.setBorder(Rectangle.NO_BORDER);
        celda2.setBorder(Rectangle.NO_BORDER);
        tabla.addCell(celda1);
        tabla.addCell(celda2);
    }

    public void exportarClientes() throws FileNotFoundException, DocumentException, IOException {
        inicioReporte(Constantes.REPORTE_CLIENTES, Constantes.TITULO_REPORTE_CLIENTES);
        cabeceraReporteDescarga("ID", "CLIENTE", "NRO DOC", "EMAIL", "ESTADO");

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
        cabeceraReporteDescarga("ID", "RECEPTOR", "FECHA", "PRECIO TOTAL", "ESTADO");

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
        cabeceraReporteDescarga("ID", "USUARIO", "ESTADO");

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
        cabeceraReporteDescarga("ID", "DESCRIPCIÓN", "ESTADO");

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
        cabeceraReporteDescarga("ID", "DESCRIPCIÓN", "ESTADO");

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
        cabeceraReporteDescarga("ID", "DESCRIPCIÓN", "ESTADO");

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
        cabeceraReporteDescarga("ID", "DESCRIPCIÓN", "ESTADO");

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
        cabeceraReporteDescarga("ID", "DESCRIPCIÓN", "ESTADO");

        ControlTipoDocumento control = new ControlTipoDocumento();
        List<TipoDocumento> lista = control.listar();
        for (TipoDocumento x : lista) {
            tabla.addCell("" + x.getId());
            tabla.addCell(x.getDescripcion());
            tabla.addCell(Utilitario.obtenerEstadoActivoInactivo(x.getEstado()));
        }

        finReporte();
    }

    public void descargarEncomienda(int idEncomienda) throws FileNotFoundException, DocumentException, IOException {
        // obtiene encomienda
        ControlEncomienda controlEncomienda = new ControlEncomienda();
        List<ListarEncomiendasDTO> listaEncomienda = controlEncomienda.listarEncomiendasPersonalizado("", "", String.valueOf(idEncomienda));
        ListarEncomiendasDTO encomienda = new ListarEncomiendasDTO();
        for (ListarEncomiendasDTO e : listaEncomienda) {
            encomienda = e;
        }

        // obtiene detalle encomienda
        ControlDetalleEncomienda controlDetalleEncomienda = new ControlDetalleEncomienda();
        List<ListarDetalleEncomiendasDTO> listaDetalle = controlDetalleEncomienda.listarDetalleEncomiendaPersonalizado(idEncomienda);

        // Armar PDF
        inicioReporte(Constantes.REPORTE_DESCARGA_ENCOMIENDA);

        PdfPTable tablaInformacion = new PdfPTable(2);
        agregarFila(tablaInformacion, "NÚMERO", String.valueOf(encomienda.getId()));
        agregarFila(tablaInformacion, "AGENCIA", encomienda.getAgencia().toUpperCase());
        agregarFila(tablaInformacion, "CLIENTE", encomienda.getCliente().toUpperCase());
        agregarFila(tablaInformacion, "RECEPTOR", encomienda.getReceptor().toUpperCase());
        agregarFila(tablaInformacion, "FECHA", encomienda.getFecha());
        agregarFila(tablaInformacion, "PRECIO TOTAL", "S/. " + encomienda.getPrecio());
        agregarFila(tablaInformacion, "ESTADO", encomienda.getEstado());
        documento.add(tablaInformacion);

        Paragraph espacio = new Paragraph("\n\n");
        documento.add(espacio);

        PdfPTable tablaDetalle = new PdfPTable(6);
        String[] cabeceras = {"CATEGORIA", "PRODUCTO", "PESO", "CANTIDAD", "PRECIO UNITARIO", "TOTAL"};
        tablaDetalle = new PdfPTable(cabeceras.length);
        tablaDetalle.setWidthPercentage(100);
        for (String cabecera : cabeceras) {
            PdfPCell titulo = new PdfPCell(new Phrase(cabecera, estiloTituloDetalle));
            tablaDetalle.addCell(titulo);
        }

        for (ListarDetalleEncomiendasDTO d : listaDetalle) {
            PdfPCell categoria = new PdfPCell(new Phrase(d.getCategoria(), estiloDatoDetalle));
            PdfPCell producto = new PdfPCell(new Phrase(d.getProducto(), estiloDatoDetalle));
            PdfPCell peso = new PdfPCell(new Phrase(d.getPeso(), estiloDatoDetalle));
            PdfPCell cantidad = new PdfPCell(new Phrase(d.getCantidad(), estiloDatoDetalle));
            PdfPCell precioUnit = new PdfPCell(new Phrase(d.getPrecioUnitario(), estiloDatoDetalle));
            PdfPCell total = new PdfPCell(new Phrase(d.getTotal(), estiloDatoDetalle));
            tablaDetalle.addCell(categoria);
            tablaDetalle.addCell(producto);
            tablaDetalle.addCell(peso);
            tablaDetalle.addCell(cantidad);
            tablaDetalle.addCell(precioUnit);
            tablaDetalle.addCell(total);
        }

        documento.add(tablaDetalle);
        finReporteDescarga();
    }

}
