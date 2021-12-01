package com.encosoft.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 * @author echamaya
 */
public class Utilitario {

    public static void MensajeExitoGenerico() {
        JOptionPane.showMessageDialog(null, Constantes.MENSAJE_REGISTRO_EXITOSO,
                Constantes.TITULO_MENSAJE, Constantes.ESTILO_MENSAJE_EXITO);
    }

    public static void MensajeActualizacionExitoGenerico() {
        JOptionPane.showMessageDialog(null, Constantes.MENSAJE_ACTUALIZACION_EXITOSO,
                Constantes.TITULO_MENSAJE, Constantes.ESTILO_MENSAJE_EXITO);
    }

    public static void MensajeEliminacionExitoGenerico() {
        JOptionPane.showMessageDialog(null, Constantes.MENSAJE_ELIMINACION_EXITOSO,
                Constantes.TITULO_MENSAJE, Constantes.ESTILO_MENSAJE_EXITO);
    }

    public static void MensajeErrorGenerico() {
        JOptionPane.showMessageDialog(null, Constantes.MENSAJE_ERROR_GENERICO,
                Constantes.TITULO_MENSAJE, Constantes.ESTILO_MENSAJE_ERROR);
    }

    public static void MensajeSeleccionarRegistro() {
        JOptionPane.showMessageDialog(null, Constantes.MENSAJE_REGISTRO_NO_SELECCIONADO,
                Constantes.TITULO_MENSAJE, Constantes.ESTILO_MENSAJE_ADVERTENCIA);
    }

    public static void MensajeCampoVacio(String nombreCampo) {
        JOptionPane.showMessageDialog(null, String.format(Constantes.MENSAJE_CAMPO_VACIO, nombreCampo),
                Constantes.TITULO_MENSAJE, Constantes.ESTILO_MENSAJE_ADVERTENCIA);
    }

    public static void MensajeCamposVacios() {
        JOptionPane.showMessageDialog(null, String.format(Constantes.MENSAJE_CAMPOS_VACIOS),
                Constantes.TITULO_MENSAJE, Constantes.ESTILO_MENSAJE_ADVERTENCIA);
    }

    public static void MostrarMensajeAdvertencia(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje,
                Constantes.TITULO_MENSAJE, Constantes.ESTILO_MENSAJE_ADVERTENCIA);
    }

    public static Map<Integer, String> estadosLista() {
        Map<Integer, String> estados = new HashMap<>();
        estados.put(EstadoEnum.ACTIVO.getId(), EstadoEnum.ACTIVO.getDescripcion());
        estados.put(EstadoEnum.INACTIVO.getId(), EstadoEnum.INACTIVO.getDescripcion());
        return estados;
    }

    public static void LlenarComboBoxEstado(JComboBox combo) {
        combo.addItem("SELECCIONAR");
        estadosLista().forEach((k, v) -> {
            combo.addItem(v);
        });
    }

    public static int obtenerIdEstado(JComboBox combo) {
        for (Map.Entry<Integer, String> estados : estadosLista().entrySet()) {
            if (combo.getSelectedItem().equals(estados.getValue())) {
                return estados.getKey();
            }
        }
        return -1;
    }

    public static void setearDescripcionEstado(JComboBox combo, Object valor) {
        for (Map.Entry<Integer, String> estados : estadosLista().entrySet()) {
            if (estados.getKey().intValue() == Integer.parseInt(valor.toString())) {
                combo.setSelectedItem(estados.getValue());
            }
        }
    }

    public static boolean validarCamposVacios(JTextField... textboxList) {
        if (textboxList.length > 0) {
            for (JTextField texto : textboxList) {
                if (texto.getText().trim().isEmpty()) {
                    MensajeCamposVacios();
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean validarCamposVacios(JComboBox... combos) {
        if (combos.length > 0) {
            for (JComboBox combo : combos) {
                if (combo.getSelectedIndex() == 0) {
                    MensajeCamposVacios();
                    return true;
                }
            }
        }
        return false;
    }

    public static void limpiarLabels(JLabel... labelList) {
        for (JLabel label : labelList) {
            label.setText("");
        }
    }

    public static void limpiarTextbox(JTextField... textboxList) {
        for (JTextField texto : textboxList) {
            texto.setText("");
        }
    }

    public static void limpiarCombobox(JComboBox... combos) {
        for (JComboBox combo : combos) {
            combo.setSelectedIndex(0);
        }
    }

    public static int obtenerIdComboBox(Map<Integer, String> map, JComboBox combo) {
        for (Map.Entry<Integer, String> obj : map.entrySet()) {
            if (combo.getSelectedItem().equals(obj.getValue())) {
                return obj.getKey();
            }
        }
        return -1;
    }

    public static String obtenerDescripcionComboBox(Map<Integer, String> map, int id) {
        for (Map.Entry<Integer, String> obj : map.entrySet()) {
            if (obj.getKey() == id) {
                return obj.getValue();
            }
        }
        return null;
    }

    public static String concatenar(String... cadenas) {
        StringBuilder concatenado = new StringBuilder();
        for (String cadena : cadenas) {
            concatenado.append(cadena);
        }
        return concatenado.toString();
    }

    public static void ocultarColumnas(JTable table, int... indicesColumnas) {
        for (int indicesColumna : indicesColumnas) {
            table.getColumnModel().getColumn(indicesColumna).setWidth(0);
            table.getColumnModel().getColumn(indicesColumna).setMinWidth(0);
            table.getColumnModel().getColumn(indicesColumna).setMaxWidth(0);
        }
    }

    public static String obtenerFechaDesdeDate(Date fechaDate) {
        String patron = "yyyy-MM-dd hh:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(patron);
        String date = sdf.format(fechaDate);
        return date;
    }

    public static String obtenerRutaReporte(String nombreArchivo) {
        return concatenar(Constantes.HOME_USER,
                Constantes.SLASH,
                Constantes.CARPETA_POR_DEFECTO,
                Constantes.SLASH,
                nombreArchivo,
                Constantes.EXTENSION_REPORTE);
    }

    public static String obtenerEstadoActivoInactivo(int estado) {
        return estado == 1 ? "ACTIVO" : "INACTIVO";
    }

    public static void MensajeExitoReporteExportado() {
        JOptionPane.showMessageDialog(null, Constantes.MENSAJE_REPORTE_EXPORTADO_EXITOSO,
                Constantes.TITULO_MENSAJE, Constantes.ESTILO_MENSAJE_EXITO);
    }
}
