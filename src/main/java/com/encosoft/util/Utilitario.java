package com.encosoft.util;

import java.util.HashMap;
import java.util.Map;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

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

    public static Map<Integer, String> estadosLista() {
        Map<Integer, String> estados = new HashMap<>();
        estados.put(EstadoEnum.SELECCIONAR.getId(), EstadoEnum.SELECCIONAR.getDescripcion());
        estados.put(EstadoEnum.ACTIVO.getId(), EstadoEnum.ACTIVO.getDescripcion());
        estados.put(EstadoEnum.INACTIVO.getId(), EstadoEnum.INACTIVO.getDescripcion());
        return estados;
    }

    public static void LlenarComboBoxEstado(JComboBox combo) {
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
        return -2;
    }

    public static void setearDescripcionEstado(JComboBox combo, Object valor) {
        for (Map.Entry<Integer, String> estados : estadosLista().entrySet()) {
            if (estados.getKey().intValue() == Integer.parseInt(valor.toString())) {
                combo.setSelectedItem(estados.getValue());
            }
        }
    }
}
