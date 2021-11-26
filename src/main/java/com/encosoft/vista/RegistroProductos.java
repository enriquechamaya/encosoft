/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.encosoft.vista;

import com.encosoft.controlador.ControlProductos;
import com.encosoft.controlador.ControlCategorias;
import com.encosoft.modelo.Categoria;
import com.encosoft.modelo.Productos;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Saul
 */
public class RegistroProductos extends javax.swing.JInternalFrame {

    ControlProductos obj = new ControlProductos();
    ControlCategorias obj2 = new ControlCategorias();

    public RegistroProductos() {
        initComponents();
        muestra();
        llenaCombo();
    }

    void muestra() {
        DefaultTableModel dt = (DefaultTableModel) TblProductos.getModel();
        dt.setRowCount(0);
        for (Productos x : obj.listarTodos()) {
            Object v[] = {x.getId(), x.getIdcategoria(), x.getDescripcion(), x.getEstado()};
            dt.addRow(v);
        }
    }

    void muestraactivos() {
        DefaultTableModel dt = (DefaultTableModel) TblProductos.getModel();
        dt.setRowCount(0);
        for (Productos x : obj.listar()) {
            Object v[] = {x.getId(), x.getIdcategoria(), x.getDescripcion(), x.getEstado()};
            dt.addRow(v);
        }
    }

    void muestraXid(int id) {

        DefaultTableModel dt = (DefaultTableModel) TblProductos.getModel();
        //Acumulador de total y contador

        dt.setRowCount(0);
        for (Productos x : obj.obtenerPorId(id)) {
            Object v[] = {x.getId(), x.getIdcategoria(), x.getDescripcion(), x.getEstado()};
            dt.addRow(v);

        }

    }

    //MÉTODO PARA LLENAR EL COMBO DE CATEGORIAS
    void llenaCombo() {

        
        for (Categoria x : obj2.listarTodos()) {
            cbocategoria.addItem(x.getDescripcion());
        }
    }

    void limpiar() {
        txtid.setText("");
        txtDescri.setText("");
        cboEstado.setSelectedIndex(0);
        cbocategoria.setSelectedIndex(0);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtid = new javax.swing.JTextField();
        txtDescri = new javax.swing.JTextField();
        cboEstado = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        btnGuardar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        btnMostrar = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        txtBusca = new javax.swing.JTextField();
        jSeparator3 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        TblProductos = new javax.swing.JTable();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        cbocategoria = new javax.swing.JComboBox<>();
        btnListar = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Registro Productos");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("ID:");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(39, 27, -1, -1));

        jLabel2.setText("Estado");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, -1, -1));

        txtid.setEnabled(false);
        getContentPane().add(txtid, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 30, 100, -1));
        getContentPane().add(txtDescri, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 60, 100, -1));

        cboEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Inactivo", "Activo" }));
        getContentPane().add(cboEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 90, 100, -1));

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
        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        getContentPane().add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 150, 110, -1));
        getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 430, 590, 20));
        getContentPane().add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 570, 20));

        btnMostrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/lupa.png"))); // NOI18N
        btnMostrar.setText("Mostrar Producto");
        btnMostrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMostrarActionPerformed(evt);
            }
        });
        getContentPane().add(btnMostrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 220, -1, -1));

        jLabel11.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel11.setText("Busca:");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 230, -1, -1));
        getContentPane().add(txtBusca, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 230, 90, -1));
        getContentPane().add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, 570, 20));

        TblProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "IDCategoria", "Descripcion", "Estado"
            }
        ));
        TblProductos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TblProductosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TblProductos);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 290, 520, 130));
        getContentPane().add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 270, 570, 20));

        jLabel4.setText("Categoria:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 30, -1, -1));

        cbocategoria.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione Categoria" }));
        getContentPane().add(cbocategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 30, 150, -1));

        btnListar.setText("Listar Todos");
        btnListar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnListarActionPerformed(evt);
            }
        });
        getContentPane().add(btnListar, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 210, 100, 20));

        jButton1.setText("Listar Activos");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 240, 100, 20));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnListarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListarActionPerformed
        muestra();
    }//GEN-LAST:event_btnListarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed

        Productos u = new Productos();
        
        u.setIdcategoria(cbocategoria.getSelectedIndex());
        u.setDescripcion(txtDescri.getText());
        u.setEstado(cboEstado.getSelectedIndex());
        obj.insertar(u);
        muestra();
        limpiar();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        Productos u = new Productos();
        u.setIdcategoria(cbocategoria.getSelectedIndex());
        u.setDescripcion(txtDescri.getText());
        u.setEstado(cboEstado.getSelectedIndex());
        u.setId(Integer.parseInt(txtid.getText()));
        obj.actualizar(u);
        muestra();
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        obj.eliminar(Integer.parseInt(txtid.getText()));
        muestra();
        limpiar();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnMostrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMostrarActionPerformed

        Productos u = new Productos();
        Integer id = Integer.parseInt(txtBusca.getText());

        obj.obtenerPorId(id);

        muestraXid(id);

    }//GEN-LAST:event_btnMostrarActionPerformed

    private void TblProductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TblProductosMouseClicked
        // MODIFICAR DATOS CON MOUSE CLICK - el código debe estar de forma global

        int fila = TblProductos.getSelectedRow();//Devuelve la fila seleccionada

        txtid.setText(TblProductos.getValueAt(fila, 0).toString());
        cbocategoria.setSelectedIndex(Integer.parseInt(TblProductos.getValueAt(fila, 1).toString()));
        txtDescri.setText(TblProductos.getValueAt(fila, 2).toString());
        cboEstado.setSelectedIndex(Integer.parseInt(TblProductos.getValueAt(fila, 3).toString()));


    }//GEN-LAST:event_TblProductosMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        muestraactivos();
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TblProductos;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnListar;
    private javax.swing.JButton btnMostrar;
    private javax.swing.JComboBox<String> cboEstado;
    private javax.swing.JComboBox<String> cbocategoria;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JTextField txtBusca;
    private javax.swing.JTextField txtDescri;
    private javax.swing.JTextField txtid;
    // End of variables declaration//GEN-END:variables
}
