/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.encosoft.vista;

import com.encosoft.controlador.ControlClientes;
import com.encosoft.controlador.ControlTipoDocumento;
import com.encosoft.modelo.Cliente;
import com.encosoft.modelo.TipoDocumento;
import com.encosoft.util.Utilitario;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Saul
 */
public class RegistroClientes extends javax.swing.JInternalFrame {

    ControlClientes controlClientes = new ControlClientes();
    boolean esActualizar = false;
    Map<Integer, String> tipoDocumentoMap = new HashMap<>();

    /**
     * Creates new form RegistroClientes
     */
    public RegistroClientes() {
        initComponents();
        Utilitario.LlenarComboBoxEstado(cboEstado);
        listarTipoDocumento();
        listarClientes(controlClientes.listar());
        cambiarBotonGuardar();
    }

    void listarTipoDocumento() {
        ControlTipoDocumento controlTipoDocumento = new ControlTipoDocumento();
        List<TipoDocumento> lista = controlTipoDocumento.listar();
        cboTipoDoc.addItem("SELECCIONAR");
        for (TipoDocumento tipoDocumento : lista) {
            tipoDocumentoMap.put(tipoDocumento.getId(), tipoDocumento.getDescripcion());
            cboTipoDoc.addItem(tipoDocumento.getDescripcion());
        }
    }

    void listarClientes(List<Cliente> lista) {
        DefaultTableModel modelo = new DefaultTableModel();
        tblClientes.setModel(modelo);

        modelo.addColumn("ID");
        modelo.addColumn("NOMBRE");
        modelo.addColumn("APE. PAT");
        modelo.addColumn("APE. MAT");
        modelo.addColumn("ID TIPO DOC");
        modelo.addColumn("CELULAR");
        modelo.addColumn("DIRECCION");
        modelo.addColumn("CLIENTE");
        modelo.addColumn("DOCUMENTO");
        modelo.addColumn("EMAIL");
        modelo.addColumn("ESTADO");

        modelo.setRowCount(0);
        for (Cliente c : lista) {
            Object data[] = {
                c.getId(),
                c.getNombre(),
                c.getApepat(),
                c.getApemat(),
                c.getIdtipodoc(),
                c.getCelular(),
                c.getDireccion(),
                Utilitario.concatenar(c.getApepat() + " " + c.getApemat() + " " + c.getNombre()),
                c.getNrodocumento(),
                c.getEmail(),
                (c.getEstado() == 1 ? "ACTIVO" : "INACTIVO")};
            modelo.addRow(data);
        }

        Utilitario.ocultarColumnas(tblClientes,
            1, 2, 3, 4, 5, 6);
    }

    void guardar() {
        if (esActualizar) {
            limpiar();
        } else {
            if (Utilitario.validarCamposVacios(txtNombre, txtApellidoPat, txtApellidoMat, txtNroDoc, txtEmail, txtCelular, txtDireccion)) {
                return;
            }
            if (Utilitario.validarCamposVacios(cboTipoDoc)) {
                return;
            }

            Cliente c = new Cliente();
            c.setNombre(txtNombre.getText());
            c.setApepat(txtApellidoPat.getText());
            c.setApemat(txtApellidoMat.getText());
            c.setIdtipodoc(Utilitario.obtenerIdComboBox(tipoDocumentoMap, cboTipoDoc));
            c.setNrodocumento(txtNroDoc.getText());
            c.setEmail(txtEmail.getText());
            c.setCelular(txtCelular.getText());
            c.setDireccion(txtDireccion.getText());
            c.setEstado(Utilitario.obtenerIdEstado(cboEstado));

            boolean esExito = controlClientes.insertar(c);
            if (esExito) {
                listarClientes(controlClientes.listar());
                limpiar();
                Utilitario.MensajeExitoGenerico();
            } else {
                Utilitario.MensajeErrorGenerico();
            }
        }
    }

    void obtenerCliente() {
        int fila = tblClientes.getSelectedRow();
        txtId.setText(tblClientes.getValueAt(fila, 0).toString());
        txtNombre.setText(tblClientes.getValueAt(fila, 1).toString());
        txtApellidoPat.setText(tblClientes.getValueAt(fila, 2).toString());
        txtApellidoMat.setText(tblClientes.getValueAt(fila, 3).toString());
        cboTipoDoc.setSelectedItem(Utilitario.obtenerDescripcionComboBox(tipoDocumentoMap, Integer.parseInt(tblClientes.getValueAt(fila, 4).toString())));
        txtNroDoc.setText(tblClientes.getValueAt(fila, 8).toString());
        txtEmail.setText(tblClientes.getValueAt(fila, 9).toString());
        txtCelular.setText(tblClientes.getValueAt(fila, 5).toString());
        txtDireccion.setText(tblClientes.getValueAt(fila, 6).toString());
        cboEstado.setSelectedItem(tblClientes.getValueAt(fila, 10).toString());
        esActualizar = true;
        cambiarBotonGuardar();
    }

    void editar() {
        int fila = tblClientes.getSelectedRow();
        if (fila >= 0) {
            if (Utilitario.validarCamposVacios(txtNombre, txtApellidoPat, txtApellidoMat, txtNroDoc, txtEmail, txtCelular, txtDireccion)) {
                return;
            }
            if (Utilitario.validarCamposVacios(cboTipoDoc, cboEstado)) {
                return;
            }
            Cliente c = new Cliente();
            c.setId(Integer.parseInt(txtId.getText()));
            c.setNombre(txtNombre.getText());
            c.setApepat(txtApellidoPat.getText());
            c.setApemat(txtApellidoMat.getText());
            c.setIdtipodoc(Utilitario.obtenerIdComboBox(tipoDocumentoMap, cboTipoDoc));
            c.setNrodocumento(txtNroDoc.getText());
            c.setEmail(txtEmail.getText());
            c.setCelular(txtCelular.getText());
            c.setDireccion(txtDireccion.getText());
            c.setEstado(Utilitario.obtenerIdEstado(cboEstado));

            boolean esExito = controlClientes.actualizar(c);
            if (esExito) {
                listarClientes(controlClientes.listar());
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
        int fila = tblClientes.getSelectedRow();
        if (fila >= 0) {
            int id = Integer.parseInt(tblClientes.getValueAt(fila, 0).toString());
            boolean esExito = controlClientes.eliminar(id);
            if (esExito) {
                listarClientes(controlClientes.listar());
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
        Utilitario.limpiarTextbox(txtNombre, txtApellidoPat, txtApellidoMat, txtNroDoc, txtEmail, txtCelular, txtDireccion, txtBusqueda);
        Utilitario.limpiarCombobox(cboTipoDoc, cboEstado);
        tblClientes.clearSelection();
        esActualizar = false;
        cambiarBotonGuardar();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        txtApellidoPat = new javax.swing.JTextField();
        txtApellidoMat = new javax.swing.JTextField();
        cboTipoDoc = new javax.swing.JComboBox<>();
        txtNroDoc = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lblEstado = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        txtCelular = new javax.swing.JTextField();
        txtDireccion = new javax.swing.JTextField();
        btnGuardar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        btnListarTodo = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        txtBusqueda = new javax.swing.JTextField();
        jSeparator3 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblClientes = new javax.swing.JTable();
        jSeparator4 = new javax.swing.JSeparator();
        cboEstado = new javax.swing.JComboBox<>();
        btnBusqueda = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Registro de Clientes");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel1.setText("ID:");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 30, -1, -1));

        jLabel2.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel2.setText("Nombre:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 60, -1, -1));

        jLabel3.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel3.setText("Apellido Paterno:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 90, -1, -1));

        jLabel4.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel4.setText("Apellido Materno:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 120, -1, -1));

        jLabel5.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel5.setText("Tipo Documento:");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 150, -1, -1));

        jLabel6.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel6.setText("Nr Documento");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 180, -1, -1));

        txtId.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        txtId.setEnabled(false);
        getContentPane().add(txtId, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 30, 190, -1));

        txtNombre.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        getContentPane().add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 60, 190, -1));

        txtApellidoPat.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        getContentPane().add(txtApellidoPat, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 90, 190, -1));

        txtApellidoMat.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        getContentPane().add(txtApellidoMat, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 120, 190, -1));

        cboTipoDoc.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        getContentPane().add(cboTipoDoc, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 150, 190, -1));

        txtNroDoc.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        getContentPane().add(txtNroDoc, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 180, 190, -1));

        jLabel7.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel7.setText("Celular:");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 70, -1, -1));

        jLabel8.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel8.setText("Email:");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 40, -1, -1));

        jLabel9.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel9.setText("Direccion:");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 100, -1, -1));

        lblEstado.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        lblEstado.setText("Estado:");
        getContentPane().add(lblEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 130, -1, -1));

        txtEmail.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        getContentPane().add(txtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 40, 160, -1));

        txtCelular.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        getContentPane().add(txtCelular, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 70, 160, -1));

        txtDireccion.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        getContentPane().add(txtDireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 100, 160, -1));

        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/guardar.png"))); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        getContentPane().add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 230, 110, -1));

        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/editar.png"))); // NOI18N
        btnEditar.setText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });
        getContentPane().add(btnEditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 230, 110, -1));

        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/elimina.png"))); // NOI18N
        btnEliminar.setText("Elimina");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        getContentPane().add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 230, 110, -1));
        getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 510, 590, 20));
        getContentPane().add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 570, 20));

        btnListarTodo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/lupa.png"))); // NOI18N
        btnListarTodo.setText("Listar todo");
        btnListarTodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnListarTodoActionPerformed(evt);
            }
        });
        getContentPane().add(btnListarTodo, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 290, -1, -1));

        jLabel11.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel11.setText("Busca:");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 310, -1, -1));
        getContentPane().add(txtBusqueda, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 310, 190, -1));
        getContentPane().add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 280, 570, 20));

        tblClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nombre", "ApePaterno", "ApeMaterno", "Tipo Documento", "NumeroDocum", "Email", "Celular", "Direccion", "Estado"
            }
        ));
        tblClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblClientesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblClientes);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 370, 570, 130));
        getContentPane().add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 350, 570, 20));

        getContentPane().add(cboEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 130, 160, -1));

        btnBusqueda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/lupa.png"))); // NOI18N
        btnBusqueda.setText("Buscar");
        btnBusqueda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBusquedaActionPerformed(evt);
            }
        });
        getContentPane().add(btnBusqueda, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 290, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        guardar();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void tblClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblClientesMouseClicked
        obtenerCliente();
    }//GEN-LAST:event_tblClientesMouseClicked

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        editar();
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        eliminar();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnBusquedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBusquedaActionPerformed
        if (!txtBusqueda.getText().isEmpty()) {
            listarClientes(controlClientes.listarPorCliente(txtBusqueda.getText().trim()));
        } else {
            Utilitario.MensajeCampoVacio("busqueda");
        }
    }//GEN-LAST:event_btnBusquedaActionPerformed

    private void btnListarTodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListarTodoActionPerformed
        listarClientes(controlClientes.listar());
    }//GEN-LAST:event_btnListarTodoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBusqueda;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnListarTodo;
    private javax.swing.JComboBox<String> cboEstado;
    private javax.swing.JComboBox<String> cboTipoDoc;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JLabel lblEstado;
    private javax.swing.JTable tblClientes;
    private javax.swing.JTextField txtApellidoMat;
    private javax.swing.JTextField txtApellidoPat;
    private javax.swing.JTextField txtBusqueda;
    private javax.swing.JTextField txtCelular;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtNroDoc;
    // End of variables declaration//GEN-END:variables

}
