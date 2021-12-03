/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.encosoft.vista;

import com.encosoft.controlador.ControlUsuarios;
import com.encosoft.controlador.ControlRol;
import com.encosoft.controlador.ControlAgencia;
import com.encosoft.modelo.Usuario;
import com.encosoft.modelo.Rol;
import com.encosoft.modelo.Agencia;
import com.encosoft.util.Utilitario;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.table.DefaultTableModel;

public class RegistroUsuarios extends javax.swing.JInternalFrame {

    ControlUsuarios controlUsuarios = new ControlUsuarios();
    boolean esActualizar = false;
    Map<Integer, String> categoriasMap = new HashMap<>();

    public RegistroUsuarios() {
        initComponents();
        Utilitario.LlenarComboBoxEstado(cboEstado);
        listarRoles();
        listarAgencias();
        listarUsuarios(controlUsuarios.listar());
        cambiarBotonGuardar();
    }

    public void listarRoles() {
        ControlRol controlRol = new ControlRol();
        List<Rol> lista = controlRol.listar();
        cboRol.addItem("SELECCIONAR");
        for (Rol rol : lista) {
            categoriasMap.put(rol.getId(), rol.getDescripcion());
            cboRol.addItem(rol.getDescripcion());
        }
    }
    ///a

    public void listarAgencias() {
        ControlAgencia controlAgencia = new ControlAgencia();
        List<Agencia> lista = controlAgencia.listar();
        cboAgencia.addItem("SELECCIONAR");
        for (Agencia agencia : lista) {
            categoriasMap.put(agencia.getId(), agencia.getDescripcion());
            cboAgencia.addItem(agencia.getDescripcion());
        }
    }

    void listarUsuarios(List<Usuario> lista) {
        DefaultTableModel modelo = new DefaultTableModel();
        tblUsuarios.setModel(modelo);

        modelo.addColumn("ID");
        modelo.addColumn("IDROL");
        modelo.addColumn("IDAGENCIA");
        modelo.addColumn("USUARIO");
        modelo.addColumn("CONTRASEÑA");
        modelo.addColumn("ESTADO");

        modelo.setRowCount(0);
        for (Usuario td : lista) {
            Object data[] = {td.getId(), td.getIdrol(), td.getIdagencia(), td.getUsuario(), td.getContrasena(), (td.getEstado() == 1 ? "ACTIVO" : "INACTIVO")};
            modelo.addRow(data);
        }
    }

    void guardar() {
        if (esActualizar) {
            limpiar();
        } else {
            if (Utilitario.validarCamposVacios(txtUsuario, txtPass)) {
                return;
            }
            if (Utilitario.validarCamposVacios(cboRol, cboAgencia)) {
                return;
            }

            Usuario c = new Usuario();

            c.setIdrol(Utilitario.obtenerIdComboBox(categoriasMap, cboRol));
            c.setIdagencia(Utilitario.obtenerIdComboBox(categoriasMap, cboAgencia));
            c.setUsuario(txtUsuario.getText());
            c.setContrasena(txtPass.getText());
            c.setEstado(Utilitario.obtenerIdEstado(cboEstado));
            boolean esExito = controlUsuarios.insertar(c);
            if (esExito) {
                listarUsuarios(controlUsuarios.listar());
                limpiar();
                Utilitario.MensajeExitoGenerico();
            } else {
                Utilitario.MensajeErrorGenerico();
            }
        }
    }

    void obtenerUsuario() {
        int fila = tblUsuarios.getSelectedRow();
        txtId.setText(tblUsuarios.getValueAt(fila, 0).toString());
        cboRol.setSelectedItem(Utilitario.obtenerDescripcionComboBox(categoriasMap, Integer.parseInt(tblUsuarios.getValueAt(fila, 1).toString())));
        cboAgencia.setSelectedItem(Utilitario.obtenerDescripcionComboBox(categoriasMap, Integer.parseInt(tblUsuarios.getValueAt(fila, 2).toString())));
        txtUsuario.setText(tblUsuarios.getValueAt(fila, 3).toString());
        txtPass.setText(tblUsuarios.getValueAt(fila, 4).toString());
        cboEstado.setSelectedItem(tblUsuarios.getValueAt(fila, 5).toString());
        esActualizar = true;
        cambiarBotonGuardar();
    }

    void editar() {
        int fila = tblUsuarios.getSelectedRow();
        if (fila >= 0) {
            if (Utilitario.validarCamposVacios(txtUsuario, txtPass)) {
                return;
            }
            if (Utilitario.validarCamposVacios(cboRol, cboAgencia, cboEstado)) {
                return;
            }
            Usuario td = new Usuario();
            td.setId(Integer.parseInt(txtId.getText()));
            td.setIdrol(Utilitario.obtenerIdComboBox(categoriasMap, cboRol));
            td.setIdagencia(Utilitario.obtenerIdComboBox(categoriasMap, cboAgencia));
            td.setUsuario(txtUsuario.getText());
            td.setContrasena(txtPass.getText());
            td.setEstado(Utilitario.obtenerIdEstado(cboEstado));

            boolean esExito = controlUsuarios.actualizar(td);
            if (esExito) {
                listarUsuarios(controlUsuarios.listar());
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
        int fila = tblUsuarios.getSelectedRow();
        if (fila >= 0) {
            int id = Integer.parseInt(tblUsuarios.getValueAt(fila, 0).toString());
            boolean esExito = controlUsuarios.eliminar(id);
            if (esExito) {
                listarUsuarios(controlUsuarios.listar());
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
        Utilitario.limpiarTextbox(txtUsuario, txtPass);
        Utilitario.limpiarCombobox(cboEstado, cboAgencia,cboRol);
        tblUsuarios.clearSelection();
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
        txtId = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        cboRol = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        cboAgencia = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtPass = new javax.swing.JPasswordField();
        lblEstado = new javax.swing.JLabel();
        cboEstado = new javax.swing.JComboBox<>();
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
        tblUsuarios = new javax.swing.JTable();
        jSeparator4 = new javax.swing.JSeparator();
        btnListarTodo = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Registro Usuarios");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("ID:");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 30, -1, -1));

        txtId.setEnabled(false);
        getContentPane().add(txtId, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 30, 120, -1));

        jLabel2.setText("Rol:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 60, -1, -1));

        getContentPane().add(cboRol, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 60, 120, -1));

        jLabel3.setText("Agencia:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 100, -1, -1));

        getContentPane().add(cboAgencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 100, 120, -1));

        jLabel4.setText("Usuario:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 30, -1, -1));
        getContentPane().add(txtUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 30, 130, -1));

        jLabel5.setText("Contraseña:");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 70, -1, -1));
        getContentPane().add(txtPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 70, 130, -1));

        lblEstado.setText("Estado:");
        getContentPane().add(lblEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 100, -1, -1));

        getContentPane().add(cboEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 100, 130, -1));

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
        btnBusqueda.setText("Mostrar Usuarios");
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

        tblUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Rol", "Agencia", "Usuario", "Contraseña", "Estado"
            }
        ));
        tblUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblUsuariosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblUsuarios);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 290, 520, 130));
        getContentPane().add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 270, 570, 20));

        btnListarTodo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/lupa.png"))); // NOI18N
        btnListarTodo.setText("Listar todo");
        btnListarTodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnListarTodoActionPerformed(evt);
            }
        });
        getContentPane().add(btnListarTodo, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 220, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnListarTodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListarTodoActionPerformed
listarUsuarios(controlUsuarios.listar());
    }//GEN-LAST:event_btnListarTodoActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
         eliminar();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
      guardar();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
     editar();
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnBusquedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBusquedaActionPerformed
     if (!txtBusqueda.getText().isEmpty()) {
            listarUsuarios(controlUsuarios.listarPorUsuario(txtBusqueda.getText().trim()));
        } else {
            Utilitario.MensajeCampoVacio("busqueda");
        }

    }//GEN-LAST:event_btnBusquedaActionPerformed

    private void tblUsuariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblUsuariosMouseClicked
     obtenerUsuario();
    }//GEN-LAST:event_tblUsuariosMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBusqueda;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnListarTodo;
    private javax.swing.JComboBox<String> cboAgencia;
    private javax.swing.JComboBox<String> cboEstado;
    private javax.swing.JComboBox<String> cboRol;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JLabel lblEstado;
    private javax.swing.JTable tblUsuarios;
    private javax.swing.JTextField txtBusqueda;
    private javax.swing.JTextField txtId;
    private javax.swing.JPasswordField txtPass;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
