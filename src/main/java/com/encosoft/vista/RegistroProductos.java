/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.encosoft.vista;

import com.encosoft.controlador.ControlProductos;
import com.encosoft.controlador.ControlCategorias;
import com.encosoft.modelo.Productos;
import com.encosoft.modelo.Categoria;
import com.encosoft.util.Utilitario;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.table.DefaultTableModel;

public class RegistroProductos extends javax.swing.JInternalFrame {

    ControlProductos controlProductos = new ControlProductos();
    boolean esActualizar = false;
    Map<Integer, String> categoriasMap = new HashMap<>();

    public RegistroProductos() {
        initComponents();
        Utilitario.LlenarComboBoxEstado(cboEstado);
        listarCategorias();
        listarProductos(controlProductos.listar());
        cambiarBotonGuardar();

    }

   public void listarCategorias() {
        ControlCategorias controlCategorias = new ControlCategorias();
        List<Categoria> lista = controlCategorias.listar();
        cboCategoria.addItem("SELECCIONAR");
        for (Categoria categoria : lista) {
            categoriasMap.put(categoria.getId(), categoria.getDescripcion());
            cboCategoria.addItem(categoria.getDescripcion());
        }
    }

    void listarProductos(List<Productos> lista) {
        DefaultTableModel modelo = new DefaultTableModel();
        tblProductos.setModel(modelo);

        modelo.addColumn("ID");
        modelo.addColumn("CATEGORIA");
        modelo.addColumn("DESCRIPCION");
        modelo.addColumn("ESTADO");

        modelo.setRowCount(0);
        for (Productos td : lista) {
            Object data[] = {td.getId(),td.getIdcategoria(), td.getDescripcion(), (td.getEstado() == 1 ? "ACTIVO" : "INACTIVO")};
            modelo.addRow(data);
        }
    }
    
  

    void guardar() {
        if (esActualizar) {
            limpiar();
        } else {
            if (Utilitario.validarCamposVacios(txtDescripcion)) {
                return;
            }
            if (Utilitario.validarCamposVacios(cboCategoria)) {
                return;
            }

            Productos c = new Productos();
            c.setDescripcion(txtDescripcion.getText());
            c.setIdcategoria(Utilitario.obtenerIdComboBox(categoriasMap, cboCategoria));
            c.setEstado(Utilitario.obtenerIdEstado(cboEstado));
            boolean esExito = controlProductos.insertar(c);
            if (esExito) {
                listarProductos(controlProductos.listar());
                limpiar();
                Utilitario.MensajeExitoGenerico();
            } else {
                Utilitario.MensajeErrorGenerico();
            }
        }
    }

    void obtenerProducto() {
        int fila = tblProductos.getSelectedRow();
        txtId.setText(tblProductos.getValueAt(fila, 0).toString());
        cboCategoria.setSelectedItem(Utilitario.obtenerDescripcionComboBox(categoriasMap, Integer.parseInt(tblProductos.getValueAt(fila, 1).toString())));
        txtDescripcion.setText(tblProductos.getValueAt(fila, 2).toString());
        cboEstado.setSelectedItem(tblProductos.getValueAt(fila, 3).toString());
        esActualizar = true;
        cambiarBotonGuardar();
    }

    void editar() {
        int fila = tblProductos.getSelectedRow();
        if (fila >= 0) {
            if (Utilitario.validarCamposVacios(txtDescripcion)) {
                return;
            }
            if (Utilitario.validarCamposVacios(cboEstado, cboCategoria)) {
                return;
            }
            Productos td = new Productos();
            td.setId(Integer.parseInt(txtId.getText()));
            td.setIdcategoria(Utilitario.obtenerIdComboBox(categoriasMap, cboCategoria));
            td.setDescripcion(txtDescripcion.getText());
            td.setEstado(Utilitario.obtenerIdEstado(cboEstado));

            boolean esExito = controlProductos.actualizar(td);
            if (esExito) {
                listarProductos(controlProductos.listar());
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
        int fila = tblProductos.getSelectedRow();
        if (fila >= 0) {
            int id = Integer.parseInt(tblProductos.getValueAt(fila, 0).toString());
            boolean esExito = controlProductos.eliminar(id);
            if (esExito) {
                listarProductos(controlProductos.listar());
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
        Utilitario.limpiarCombobox(cboEstado, cboCategoria);
        tblProductos.clearSelection();
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
        tblProductos = new javax.swing.JTable();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        cboCategoria = new javax.swing.JComboBox<>();
        btnListarTodo = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Registro Productos");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("ID:");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(39, 27, -1, -1));

        lblEstado.setText("Estado");
        getContentPane().add(lblEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, -1, -1));

        txtId.setEnabled(false);
        getContentPane().add(txtId, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 30, 120, -1));
        getContentPane().add(txtDescripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 60, 120, -1));

        getContentPane().add(cboEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 90, 120, -1));

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

        btnBusqueda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/lupa.png"))); // NOI18N
        btnBusqueda.setText("Mostrar Producto");
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

        tblProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "IDCategoria", "Descripcion", "Estado"
            }
        ));
        tblProductos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProductosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblProductos);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 290, 520, 130));
        getContentPane().add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 270, 570, 20));

        jLabel4.setText("Categoria:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 30, -1, -1));

        getContentPane().add(cboCategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 30, 150, -1));

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

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
 guardar();

    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
    editar();
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
    eliminar();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnBusquedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBusquedaActionPerformed
   if (!txtBusqueda.getText().isEmpty()) {
            listarProductos(controlProductos.listarPorDescripcion(txtBusqueda.getText().trim()));
        } else {
            Utilitario.MensajeCampoVacio("busqueda");
        }

    }//GEN-LAST:event_btnBusquedaActionPerformed

    private void tblProductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProductosMouseClicked
  obtenerProducto();

    }//GEN-LAST:event_tblProductosMouseClicked

    private void btnListarTodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListarTodoActionPerformed
   listarProductos(controlProductos.listar());
    }//GEN-LAST:event_btnListarTodoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBusqueda;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnListarTodo;
    private javax.swing.JComboBox<String> cboCategoria;
    private javax.swing.JComboBox<String> cboEstado;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JLabel lblEstado;
    private javax.swing.JTable tblProductos;
    private javax.swing.JTextField txtBusqueda;
    private javax.swing.JTextField txtDescripcion;
    private javax.swing.JTextField txtId;
    // End of variables declaration//GEN-END:variables
}
