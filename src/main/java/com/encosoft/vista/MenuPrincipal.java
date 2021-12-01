/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.encosoft.vista;

import com.encosoft.controlador.ControlReportes;
import com.encosoft.util.Utilitario;
import com.itextpdf.text.DocumentException;
import java.awt.Dimension;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;

/**
 *
 * @author Saul
 */
public class MenuPrincipal extends javax.swing.JFrame {

    ControlReportes controlReportes = new ControlReportes();

    /**
     * Creates new form MenuPrincipalFrame
     */
    public MenuPrincipal() {
        initComponents();
        this.setExtendedState(this.getExtendedState() | JFrame.MAXIMIZED_BOTH);
    }

    void centrarFormulario(JInternalFrame internalFrame) {
        Dimension desktopSize = jDesktopPane2.getSize();
        Dimension internalFrameSize = internalFrame.getSize();

        int width = (desktopSize.width - internalFrameSize.width) / 2;
        int height = (desktopSize.height - internalFrameSize.height) / 2;
        internalFrame.setLocation(width, height);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDesktopPane2 = new javax.swing.JDesktopPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenuAddClientes = new javax.swing.JMenuItem();
        jMenuAddUsuaios = new javax.swing.JMenuItem();
        jMenuAddAgencia = new javax.swing.JMenuItem();
        jMenuAddProductos = new javax.swing.JMenuItem();
        jMenuAddDocumentoTipo = new javax.swing.JMenuItem();
        jMenuAddCategoria = new javax.swing.JMenuItem();
        jMenuAddRol = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuAddEncomienda = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout jDesktopPane2Layout = new javax.swing.GroupLayout(jDesktopPane2);
        jDesktopPane2.setLayout(jDesktopPane2Layout);
        jDesktopPane2Layout.setHorizontalGroup(
            jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 627, Short.MAX_VALUE)
        );
        jDesktopPane2Layout.setVerticalGroup(
            jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 312, Short.MAX_VALUE)
        );

        getContentPane().add(jDesktopPane2, java.awt.BorderLayout.CENTER);

        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/logo.png"))); // NOI18N
        jMenu1.setText("Encosoft");
        jMenuBar1.add(jMenu1);

        jMenu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/mantenimientos.png"))); // NOI18N
        jMenu2.setText("Mantenimientos");
        jMenu2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu2MouseClicked(evt);
            }
        });
        jMenu2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu2ActionPerformed(evt);
            }
        });

        jMenuAddClientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/cliente.png"))); // NOI18N
        jMenuAddClientes.setText("Clientes");
        jMenuAddClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuAddClientesMouseClicked(evt);
            }
        });
        jMenuAddClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuAddClientesActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuAddClientes);

        jMenuAddUsuaios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/user2.png"))); // NOI18N
        jMenuAddUsuaios.setText("Usuarios");
        jMenuAddUsuaios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuAddUsuaiosActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuAddUsuaios);

        jMenuAddAgencia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/agencia.png"))); // NOI18N
        jMenuAddAgencia.setText("Agencia");
        jMenuAddAgencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuAddAgenciaActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuAddAgencia);

        jMenuAddProductos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/producto.png"))); // NOI18N
        jMenuAddProductos.setText("Productos");
        jMenuAddProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuAddProductosActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuAddProductos);

        jMenuAddDocumentoTipo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/documento.png"))); // NOI18N
        jMenuAddDocumentoTipo.setText("Tipo de Documento");
        jMenuAddDocumentoTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuAddDocumentoTipoActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuAddDocumentoTipo);

        jMenuAddCategoria.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/categoria.png"))); // NOI18N
        jMenuAddCategoria.setText("Categorias");
        jMenuAddCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuAddCategoriaActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuAddCategoria);

        jMenuAddRol.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/rol.png"))); // NOI18N
        jMenuAddRol.setText("Rol");
        jMenuAddRol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuAddRolActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuAddRol);

        jMenuBar1.add(jMenu2);

        jMenu3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/registrado.png"))); // NOI18N
        jMenu3.setText("Registrar Encomienda");

        jMenuAddEncomienda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/encom.png"))); // NOI18N
        jMenuAddEncomienda.setText("Encomienda");
        jMenuAddEncomienda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuAddEncomiendaActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuAddEncomienda);

        jMenuItem7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/listado-encomienda.png"))); // NOI18N
        jMenuItem7.setText("Listado de encomienda");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem7);

        jMenuBar1.add(jMenu3);

        jMenu4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/reporte.png"))); // NOI18N
        jMenu4.setText("Reportes");

        jMenuItem4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/cliente.png"))); // NOI18N
        jMenuItem4.setText("Reporte Clientes");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem4);

        jMenuItem9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/user2.png"))); // NOI18N
        jMenuItem9.setText("Reporte Usuarios");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem9);

        jMenuItem10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/agencia.png"))); // NOI18N
        jMenuItem10.setText("Reporte  Agencias");
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem10);

        jMenuItem5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/encom.png"))); // NOI18N
        jMenuItem5.setText("Reporte Encomiendas");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem5);

        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/reporte_productos.png"))); // NOI18N
        jMenuItem1.setText("Reporte Productos");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem1);

        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/reporte_categorias.png"))); // NOI18N
        jMenuItem2.setText("Reporte Categorias");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem2);

        jMenuItem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/reporte_roles.png"))); // NOI18N
        jMenuItem3.setText("Reporte Rol");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem3);

        jMenuItem6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/reporte_tipo_documentos.png"))); // NOI18N
        jMenuItem6.setText("Reporte Tipo documentos");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem6);

        jMenuBar1.add(jMenu4);

        jMenu5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/salir.png"))); // NOI18N
        jMenu5.setText("Salir");
        jMenu5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu5MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu5);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenu2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu2ActionPerformed

    }//GEN-LAST:event_jMenu2ActionPerformed

    private void jMenu2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu2MouseClicked

    }//GEN-LAST:event_jMenu2MouseClicked

    private void jMenuAddClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuAddClientesMouseClicked

    }//GEN-LAST:event_jMenuAddClientesMouseClicked

    private void jMenuAddClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuAddClientesActionPerformed
        RegistroClientes internalFrame = new RegistroClientes();
        jDesktopPane2.add(internalFrame);
        internalFrame.show();
        centrarFormulario(internalFrame);
    }//GEN-LAST:event_jMenuAddClientesActionPerformed

    private void jMenuAddUsuaiosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuAddUsuaiosActionPerformed
        RegistroUsuarios internalFrame = new RegistroUsuarios();
        jDesktopPane2.add(internalFrame);
        internalFrame.show();
        centrarFormulario(internalFrame);
    }//GEN-LAST:event_jMenuAddUsuaiosActionPerformed

    private void jMenuAddProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuAddProductosActionPerformed
        RegistroProductos internalFrame = new RegistroProductos();
        jDesktopPane2.add(internalFrame);
        internalFrame.show();
        centrarFormulario(internalFrame);
    }//GEN-LAST:event_jMenuAddProductosActionPerformed

    private void jMenuAddAgenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuAddAgenciaActionPerformed
        RegistroAgencia internalFrame = new RegistroAgencia();
        jDesktopPane2.add(internalFrame);
        internalFrame.show();
        centrarFormulario(internalFrame);
    }//GEN-LAST:event_jMenuAddAgenciaActionPerformed

    private void jMenuAddDocumentoTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuAddDocumentoTipoActionPerformed
        RegistroTipoDocumento internalFrame = new RegistroTipoDocumento();
        jDesktopPane2.add(internalFrame);
        internalFrame.show();
        centrarFormulario(internalFrame);
    }//GEN-LAST:event_jMenuAddDocumentoTipoActionPerformed

    private void jMenuAddEncomiendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuAddEncomiendaActionPerformed
        RegistroEncomiendas internalFrame = new RegistroEncomiendas();
        jDesktopPane2.add(internalFrame);
        internalFrame.show();
        centrarFormulario(internalFrame);
    }//GEN-LAST:event_jMenuAddEncomiendaActionPerformed

    private void jMenuAddCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuAddCategoriaActionPerformed
        RegistroCategorias internalFrame = new RegistroCategorias();
        jDesktopPane2.add(internalFrame);
        internalFrame.show();
        centrarFormulario(internalFrame);
    }//GEN-LAST:event_jMenuAddCategoriaActionPerformed

    private void jMenuAddRolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuAddRolActionPerformed
        RegistroRol internalFrame = new RegistroRol();
        jDesktopPane2.add(internalFrame);
        internalFrame.show();
        centrarFormulario(internalFrame);
    }//GEN-LAST:event_jMenuAddRolActionPerformed

    private void jMenu5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu5MouseClicked
        System.exit(0);
    }//GEN-LAST:event_jMenu5MouseClicked

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        try {
            controlReportes.exportarClientes();
            Utilitario.MensajeExitoReporteExportado();
        } catch (FileNotFoundException | DocumentException ex) {
            Logger.getLogger(MenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        try {
            controlReportes.exportarEncomiendas();
        } catch (FileNotFoundException | DocumentException ex) {
            Logger.getLogger(MenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        try {
            controlReportes.exportarUsuarios();
            Utilitario.MensajeExitoReporteExportado();
        } catch (FileNotFoundException | DocumentException ex) {
            Logger.getLogger(MenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
        try {
            controlReportes.exportarAgencias();
            Utilitario.MensajeExitoReporteExportado();
        } catch (FileNotFoundException | DocumentException ex) {
            Logger.getLogger(MenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItem10ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        try {
            controlReportes.exportarProductos();
            Utilitario.MensajeExitoReporteExportado();
        } catch (FileNotFoundException | DocumentException ex) {
            Logger.getLogger(MenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        try {
            controlReportes.exportarCategorias();
            Utilitario.MensajeExitoReporteExportado();
        } catch (FileNotFoundException | DocumentException ex) {
            Logger.getLogger(MenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        try {
            controlReportes.exportarRol();
            Utilitario.MensajeExitoReporteExportado();
        } catch (FileNotFoundException | DocumentException ex) {
            Logger.getLogger(MenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        try {
            controlReportes.exportarTipoDocumentos();
            Utilitario.MensajeExitoReporteExportado();
        } catch (FileNotFoundException | DocumentException ex) {
            Logger.getLogger(MenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        ListarEncomiendas internalFrame = new ListarEncomiendas();
        jDesktopPane2.add(internalFrame);
        internalFrame.show();
        centrarFormulario(internalFrame);
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MenuPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JDesktopPane jDesktopPane2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuItem jMenuAddAgencia;
    private javax.swing.JMenuItem jMenuAddCategoria;
    private javax.swing.JMenuItem jMenuAddClientes;
    private javax.swing.JMenuItem jMenuAddDocumentoTipo;
    private javax.swing.JMenuItem jMenuAddEncomienda;
    private javax.swing.JMenuItem jMenuAddProductos;
    private javax.swing.JMenuItem jMenuAddRol;
    private javax.swing.JMenuItem jMenuAddUsuaios;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem9;
    // End of variables declaration//GEN-END:variables
}
