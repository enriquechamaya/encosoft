/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.encosoft.vista;

import com.encosoft.controlador.ControlAgencia;
import com.encosoft.modelo.Agencia;
import com.encosoft.util.Utilitario;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Saul
 */
public class RegistroAgencia extends javax.swing.JInternalFrame {

    /**
     * Creates new form RegistrarAgencia
     */
    ControlAgencia controlAgencia = new ControlAgencia();
    boolean esActualizar = false;

    public RegistroAgencia() {
        initComponents();
        listarAgencia(controlAgencia.listar());
        Utilitario.LlenarComboBoxEstado(cboEstado);
        cambiarBotonGuardar();
    }

    void listarAgencia(List<Agencia> lista) {
        DefaultTableModel modelo = new DefaultTableModel();
        tblAgencias.setModel(modelo);

        modelo.addColumn("ID");
        modelo.addColumn("DESCRIPCION");
        modelo.addColumn("ESTADO");

        modelo.setRowCount(0);
        for (Agencia td : lista) {
            Object data[] = {td.getId(), td.getDescripcion(), (td.getEstado() == 1 ? "ACTIVO" : "INACTIVO")};
            modelo.addRow(data);
        }
    }

    void registrar() {
        if (esActualizar) {
            limpiar();
        } else {
            if (Utilitario.validarCamposVacios(txtDescripcion)) {
                return;
            }
            Agencia td = new Agencia();
            td.setDescripcion(txtDescripcion.getText());

            boolean esExito = controlAgencia.insertar(td);
            if (esExito) {
                listarAgencia(controlAgencia.listar());
                limpiar();
                Utilitario.MensajeExitoGenerico();
            } else {
                Utilitario.MensajeErrorGenerico();
            }
        }
    }

    void obtenerAgencia() {
        int fila = tblAgencias.getSelectedRow();
        txtId.setText(tblAgencias.getValueAt(fila, 0).toString());
        txtDescripcion.setText(tblAgencias.getValueAt(fila, 1).toString());
        cboEstado.setSelectedItem(tblAgencias.getValueAt(fila, 2).toString());
        esActualizar = true;
        cambiarBotonGuardar();
    }

    void editar() {
        int fila = tblAgencias.getSelectedRow();
        if (fila >= 0) {
            if (Utilitario.validarCamposVacios(txtDescripcion)) {
                return;
            }
            if (Utilitario.validarCamposVacios(cboEstado)) {
                return;
            }
            Agencia td = new Agencia();
            td.setId(Integer.parseInt(txtId.getText()));
            td.setDescripcion(txtDescripcion.getText());
            td.setEstado(Utilitario.obtenerIdEstado(cboEstado));

            boolean esExito = controlAgencia.actualizar(td);
            if (esExito) {
                listarAgencia(controlAgencia.listar());
                limpiar();
                Utilitario.MensajeActualizacionExitoGenerico();
            } else {
                Utilitario.MensajeErrorGenerico();
            }
        } else {
            Utilitario.MensajeSeleccionarRegistro();
        }
    }

    void cambiarBotonGuardar() {
        if (esActualizar) {
            btnGuardar.setText("Nuevo");
            lblEstado.setVisible(true);
            cboEstado.setVisible(true);
        } else {
            txtId.setText("AUTOGENERADO");
            btnGuardar.setText("Guardar");
            lblEstado.setVisible(false);
            cboEstado.setVisible(false);
        }
    }

    void eliminar() {
        int fila = tblAgencias.getSelectedRow();
        if (fila >= 0) {
            int id = Integer.parseInt(tblAgencias.getValueAt(fila, 0).toString());
            boolean esExito = controlAgencia.eliminar(id);
            if (esExito) {
                listarAgencia(controlAgencia.listar());
                limpiar();
                Utilitario.MensajeEliminacionExitoGenerico();
            } else {
                Utilitario.MensajeErrorGenerico();
            }
        } else {
            Utilitario.MensajeSeleccionarRegistro();
        }
    }

    void limpiar() {
        Utilitario.limpiarTextbox(txtDescripcion, txtBusqueda);
        Utilitario.limpiarCombobox(cboEstado);
        tblAgencias.clearSelection();
        esActualizar = false;
        cambiarBotonGuardar();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        lblEstado = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        txtDescripcion = new javax.swing.JTextField();
        cboEstado = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        btnGuardar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        btnBusqueda = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        txtBusqueda = new javax.swing.JTextField();
        jSeparator3 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblAgencias = new javax.swing.JTable();
        jSeparator4 = new javax.swing.JSeparator();
        btnListarTodo = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Registrar Agencia\n");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("ID:");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(39, 27, -1, -1));

        lblEstado.setText("Estado");
        getContentPane().add(lblEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, -1, -1));

        txtId.setEnabled(false);
        getContentPane().add(txtId, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 30, 140, -1));
        getContentPane().add(txtDescripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 60, 140, -1));

        getContentPane().add(cboEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 90, 140, -1));

        jLabel3.setText("Decripcion:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, -1, -1));

        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/guardar.png"))); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        getContentPane().add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 150, 110, -1));

        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/editar.png"))); // NOI18N
        btnEditar.setText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });
        getContentPane().add(btnEditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 150, 110, -1));

        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/elimina.png"))); // NOI18N
        btnEliminar.setText("Elimina");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        getContentPane().add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 150, 110, -1));
        getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 430, 590, 20));
        getContentPane().add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 570, 20));

        btnBusqueda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/lupa.png"))); // NOI18N
        btnBusqueda.setText("Mostrar Agencia");
        btnBusqueda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBusquedaActionPerformed(evt);
            }
        });
        getContentPane().add(btnBusqueda, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 220, -1, -1));

        jLabel11.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel11.setText("Busca:");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 230, -1, -1));
        getContentPane().add(txtBusqueda, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 230, 90, -1));
        getContentPane().add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, 570, 20));

        tblAgencias.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Descripcion", "Estado"
            }
        ));
        tblAgencias.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblAgenciasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblAgencias);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 290, 520, 130));
        getContentPane().add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 270, 570, 20));

        btnListarTodo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/lupa.png"))); // NOI18N
        btnListarTodo.setText("Listar todo");
        btnListarTodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnListarTodoActionPerformed(evt);
            }
        });
        getContentPane().add(btnListarTodo, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 220, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnListarTodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListarTodoActionPerformed
        listarAgencia(controlAgencia.listar());
    }//GEN-LAST:event_btnListarTodoActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        registrar();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        editar();
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        eliminar();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void tblAgenciasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblAgenciasMouseClicked
        obtenerAgencia();
    }//GEN-LAST:event_tblAgenciasMouseClicked

    private void btnBusquedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBusquedaActionPerformed
        if (!txtBusqueda.getText().isEmpty()) {
            listarAgencia(controlAgencia.listarPorDescripcion(txtBusqueda.getText().trim()));
        } else {
            Utilitario.MensajeCampoVacio("busqueda");
        }

    }//GEN-LAST:event_btnBusquedaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBusqueda;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnListarTodo;
    private javax.swing.JComboBox<String> cboEstado;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JLabel lblEstado;
    private javax.swing.JTable tblAgencias;
    private javax.swing.JTextField txtBusqueda;
    private javax.swing.JTextField txtDescripcion;
    private javax.swing.JTextField txtId;
    // End of variables declaration//GEN-END:variables
}
