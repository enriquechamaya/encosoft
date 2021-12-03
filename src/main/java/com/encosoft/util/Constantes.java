package com.encosoft.util;

/**
 * @author echamaya
 */
public class Constantes {

    public static final int ESTADO_ACTIVO = 1;
    public static final int ESTADO_INACTIVO = 0;
    public static final String TITULO_MENSAJE = "ENCOSOFT";

    public static int ESTILO_MENSAJE_EXITO = 1;
    public static int ESTILO_MENSAJE_ERROR = 0;
    public static int ESTILO_MENSAJE_ADVERTENCIA = 2;

    // mensaje exito
    public static final String MENSAJE_REGISTRO_EXITOSO = "El registro ha sido exitoso";
    public static final String MENSAJE_ACTUALIZACION_EXITOSO = "El registro ha sido actualizado con exito";
    public static final String MENSAJE_ELIMINACION_EXITOSO = "El registro ha sido eliminado con exito";
    public static final String MENSAJE_REPORTE_EXPORTADO_EXITOSO = "El reporte ha sido exportado con éxito, revisar su carpeta de Descargas";

    // mensaje error
    public static final String MENSAJE_ERROR_GENERICO = "Ha ocurrido un error";

    // mensaje advertencia
    public static final String MENSAJE_REGISTRO_NO_SELECCIONADO = "Debe seleccionar un registro";
    public static final String MENSAJE_CAMPO_VACIO = "El campo %s está vacío";
    public static final String MENSAJE_CAMPOS_VACIOS = "Existen campos vacíos";

    // configuracion reporte
    public static final String HOME_USER = System.getProperty("user.home");
    public static final String SLASH = "/";
    public static final String GUION_BAJO = "_";
    public static final String CARPETA_POR_DEFECTO = "Downloads";
    public static final String EXTENSION_REPORTE = ".pdf";

    // nombres archivos reportes
    public static final String REPORTE_CLIENTES = "reporteClientes";
    public static final String TITULO_REPORTE_CLIENTES = "CLIENTES";
    public static final String REPORTE_ENCOMIENDAS = "reporteEncomiendas";
    public static final String TITULO_REPORTE_ENCOMIENDAS = "ENCOMIENDAS";
    public static final String REPORTE_USUARIOS = "reporteUsuarios";
    public static final String TITULO_REPORTE_USUARIOS = "USUARIOS";
    public static final String REPORTE_AGENCIAS = "reporteAgencias";
    public static final String TITULO_REPORTE_AGENCIAS = "AGENCIAS";
    public static final String REPORTE_PRODUCTOS = "reporteProductos";
    public static final String TITULO_REPORTE_PRODUCTOS = "PRODUCTOS";
    public static final String REPORTE_CATEGORIAS = "reporteCategorias";
    public static final String TITULO_REPORTE_CATEGORIAS = "CATEGORIAS";
    public static final String REPORTE_ROLES = "reporteRoles";
    public static final String TITULO_REPORTE_ROLES = "ROLES";
    public static final String REPORTE_TIPO_DOCUMENTOS = "reporteTipoDocumentos";
    public static final String TITULO_REPORTE_TIPO_DOCUMENTOS = "TIPO DE DOCUMENTOS";
    public static final String REPORTE_DESCARGA_ENCOMIENDA = "encomienda";
    public static final String TITULO_REPORTE_DESCARGA_TIPO_DOCUMENTOS = "COMPROBANTE DE PAGO DE ENCOMIENDA";

}
