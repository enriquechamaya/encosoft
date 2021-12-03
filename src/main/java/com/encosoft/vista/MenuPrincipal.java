/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.encosoft.vista;

import com.encosoft.controlador.ControlReportes;
import com.encosoft.modelo.Usuario;
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
    int idRol;

    public MenuPrincipal(String usuario, String agencia, String rol, int idRol) {
        initComponents();
        this.setExtendedState(this.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        lblUsuario.setText(usuario);
        lblRol.setText(rol);
        lblAgencia.setText(agencia);
        this.idRol = idRol;
        restringirMenu();
        this.setTitle("SISTEMA DE ENCOMIENDAS - ENCOSOFT (TRABAJO FINAL INTEGRADOR I)");
    }

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

    void permisosAdministrador() {
        moduloClientes.setEnabled(true);
        moduloUsuarios.setEnabled(true);
        moduloAgencias.setEnabled(true);
        moduloProductos.setEnabled(true);
        moduloTipoDocumento.setEnabled(true);
        moduloCategorias.setEnabled(true);
        moduloRol.setEnabled(true);

        reporteClientes.setEnabled(true);
        reporteUsuarios.setEnabled(true);
        reporteAgencias.setEnabled(true);
        reporteProductos.setEnabled(true);
        reporteTipoDocumento.setEnabled(true);
        reporteCategorias.setEnabled(true);
        reporteRol.setEnabled(true);
    }

    void permisosOperador() {
        moduloClientes.setEnabled(true);
        moduloUsuarios.setEnabled(false);
        moduloAgencias.setEnabled(false);
        moduloProductos.setEnabled(true);
        moduloTipoDocumento.setEnabled(false);
        moduloCategorias.setEnabled(true);
        moduloRol.setEnabled(false);

        reporteClientes.setEnabled(true);
        reporteUsuarios.setEnabled(false);
        reporteAgencias.setEnabled(false);
        reporteProductos.setEnabled(true);
        reporteTipoDocumento.setEnabled(false);
        reporteCategorias.setEnabled(true);
        reporteRol.setEnabled(false);
    }

    void restringirMenu() {
        final int ADMINISTRADOR = 1;
        if (idRol == ADMINISTRADOR) {
            permisosAdministrador();
        } else {
            permisosOperador();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDesktopPane2 = new javax.swing.JDesktopPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lblUsuario = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblRol = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblAgencia = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jmenu2 = new javax.swing.JMenu();
        moduloClientes = new javax.swing.JMenuItem();
        moduloUsuarios = new javax.swing.JMenuItem();
        moduloAgencias = new javax.swing.JMenuItem();
        moduloProductos = new javax.swing.JMenuItem();
        moduloTipoDocumento = new javax.swing.JMenuItem();
        moduloCategorias = new javax.swing.JMenuItem();
        moduloRol = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuAddEncomienda = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        reporteClientes = new javax.swing.JMenuItem();
        reporteUsuarios = new javax.swing.JMenuItem();
        reporteAgencias = new javax.swing.JMenuItem();
        reporteEncomiendas = new javax.swing.JMenuItem();
        reporteProductos = new javax.swing.JMenuItem();
        reporteCategorias = new javax.swing.JMenuItem();
        reporteRol = new javax.swing.JMenuItem();
        reporteTipoDocumento = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 0, 102));

        jLabel1.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("USUARIO:");

        lblUsuario.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        lblUsuario.setForeground(new java.awt.Color(255, 255, 255));
        lblUsuario.setText("USUARIO");

        jLabel3.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("ROL:");

        lblRol.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        lblRol.setForeground(new java.awt.Color(255, 255, 255));
        lblRol.setText("ROL");

        jLabel4.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("AGENCIA:");

        lblAgencia.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        lblAgencia.setForeground(new java.awt.Color(255, 255, 255));
        lblAgencia.setText("AGENCIA");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblRol, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblAgencia, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(lblAgencia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(lblRol, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(lblUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, 18, Short.MAX_VALUE))))
        );

        jDesktopPane2.setLayer(jPanel1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane2Layout = new javax.swing.GroupLayout(jDesktopPane2);
        jDesktopPane2.setLayout(jDesktopPane2Layout);
        jDesktopPane2Layout.setHorizontalGroup(
            jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jDesktopPane2Layout.setVerticalGroup(
            jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDesktopPane2Layout.createSequentialGroup()
                .addGap(0, 294, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        getContentPane().add(jDesktopPane2, java.awt.BorderLayout.CENTER);

        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/logo.png"))); // NOI18N
        jMenu1.setText("Encosoft");
        jMenuBar1.add(jMenu1);

        jmenu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/mantenimientos.png"))); // NOI18N
        jmenu2.setText("Mantenimientos");
        jmenu2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jmenu2MouseClicked(evt);
            }
        });
        jmenu2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmenu2ActionPerformed(evt);
            }
        });

        moduloClientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/cliente.png"))); // NOI18N
        moduloClientes.setText("Clientes");
        moduloClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                moduloClientesMouseClicked(evt);
            }
        });
        moduloClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                moduloClientesActionPerformed(evt);
            }
        });
        jmenu2.add(moduloClientes);

        moduloUsuarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/user2.png"))); // NOI18N
        moduloUsuarios.setText("Usuarios");
        moduloUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                moduloUsuariosActionPerformed(evt);
            }
        });
        jmenu2.add(moduloUsuarios);

        moduloAgencias.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/agencia.png"))); // NOI18N
        moduloAgencias.setText("Agencia");
        moduloAgencias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                moduloAgenciasActionPerformed(evt);
            }
        });
        jmenu2.add(moduloAgencias);

        moduloProductos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/producto.png"))); // NOI18N
        moduloProductos.setText("Productos");
        moduloProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                moduloProductosActionPerformed(evt);
            }
        });
        jmenu2.add(moduloProductos);

        moduloTipoDocumento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/documento.png"))); // NOI18N
        moduloTipoDocumento.setText("Tipo de Documento");
        moduloTipoDocumento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                moduloTipoDocumentoActionPerformed(evt);
            }
        });
        jmenu2.add(moduloTipoDocumento);

        moduloCategorias.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/categoria.png"))); // NOI18N
        moduloCategorias.setText("Categorias");
        moduloCategorias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                moduloCategoriasActionPerformed(evt);
            }
        });
        jmenu2.add(moduloCategorias);

        moduloRol.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/rol.png"))); // NOI18N
        moduloRol.setText("Rol");
        moduloRol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                moduloRolActionPerformed(evt);
            }
        });
        jmenu2.add(moduloRol);

        jMenuBar1.add(jmenu2);

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

        reporteClientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/cliente.png"))); // NOI18N
        reporteClientes.setText("Reporte Clientes");
        reporteClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reporteClientesActionPerformed(evt);
            }
        });
        jMenu4.add(reporteClientes);

        reporteUsuarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/user2.png"))); // NOI18N
        reporteUsuarios.setText("Reporte Usuarios");
        reporteUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reporteUsuariosActionPerformed(evt);
            }
        });
        jMenu4.add(reporteUsuarios);

        reporteAgencias.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/agencia.png"))); // NOI18N
        reporteAgencias.setText("Reporte  Agencias");
        reporteAgencias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reporteAgenciasActionPerformed(evt);
            }
        });
        jMenu4.add(reporteAgencias);

        reporteEncomiendas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/encom.png"))); // NOI18N
        reporteEncomiendas.setText("Reporte Encomiendas");
        reporteEncomiendas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reporteEncomiendasActionPerformed(evt);
            }
        });
        jMenu4.add(reporteEncomiendas);

        reporteProductos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/reporte_productos.png"))); // NOI18N
        reporteProductos.setText("Reporte Productos");
        reporteProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reporteProductosActionPerformed(evt);
            }
        });
        jMenu4.add(reporteProductos);

        reporteCategorias.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/reporte_categorias.png"))); // NOI18N
        reporteCategorias.setText("Reporte Categorias");
        reporteCategorias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reporteCategoriasActionPerformed(evt);
            }
        });
        jMenu4.add(reporteCategorias);

        reporteRol.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/reporte_roles.png"))); // NOI18N
        reporteRol.setText("Reporte Rol");
        reporteRol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reporteRolActionPerformed(evt);
            }
        });
        jMenu4.add(reporteRol);

        reporteTipoDocumento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/reporte_tipo_documentos.png"))); // NOI18N
        reporteTipoDocumento.setText("Reporte Tipo documentos");
        reporteTipoDocumento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reporteTipoDocumentoActionPerformed(evt);
            }
        });
        jMenu4.add(reporteTipoDocumento);

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

    private void jmenu2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmenu2ActionPerformed

    }//GEN-LAST:event_jmenu2ActionPerformed

    private void jmenu2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jmenu2MouseClicked

    }//GEN-LAST:event_jmenu2MouseClicked

    private void moduloClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_moduloClientesMouseClicked

    }//GEN-LAST:event_moduloClientesMouseClicked

    private void moduloClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moduloClientesActionPerformed
        RegistroClientes internalFrame = new RegistroClientes();
        jDesktopPane2.add(internalFrame);
        internalFrame.show();
        centrarFormulario(internalFrame);
    }//GEN-LAST:event_moduloClientesActionPerformed

    private void moduloUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moduloUsuariosActionPerformed
        RegistroUsuarios internalFrame = new RegistroUsuarios();
        jDesktopPane2.add(internalFrame);
        internalFrame.show();
        centrarFormulario(internalFrame);
    }//GEN-LAST:event_moduloUsuariosActionPerformed

    private void moduloProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moduloProductosActionPerformed
        RegistroProductos internalFrame = new RegistroProductos();
        jDesktopPane2.add(internalFrame);
        internalFrame.show();
        centrarFormulario(internalFrame);
    }//GEN-LAST:event_moduloProductosActionPerformed

    private void moduloAgenciasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moduloAgenciasActionPerformed
        RegistroAgencia internalFrame = new RegistroAgencia();
        jDesktopPane2.add(internalFrame);
        internalFrame.show();
        centrarFormulario(internalFrame);
    }//GEN-LAST:event_moduloAgenciasActionPerformed

    private void moduloTipoDocumentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moduloTipoDocumentoActionPerformed
        RegistroTipoDocumento internalFrame = new RegistroTipoDocumento();
        jDesktopPane2.add(internalFrame);
        internalFrame.show();
        centrarFormulario(internalFrame);
    }//GEN-LAST:event_moduloTipoDocumentoActionPerformed

    private void jMenuAddEncomiendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuAddEncomiendaActionPerformed
        RegistroEncomiendas internalFrame = new RegistroEncomiendas();
        jDesktopPane2.add(internalFrame);
        internalFrame.show();
        centrarFormulario(internalFrame);
    }//GEN-LAST:event_jMenuAddEncomiendaActionPerformed

    private void moduloCategoriasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moduloCategoriasActionPerformed
        RegistroCategorias internalFrame = new RegistroCategorias();
        jDesktopPane2.add(internalFrame);
        internalFrame.show();
        centrarFormulario(internalFrame);
    }//GEN-LAST:event_moduloCategoriasActionPerformed

    private void moduloRolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moduloRolActionPerformed
        RegistroRol internalFrame = new RegistroRol();
        jDesktopPane2.add(internalFrame);
        internalFrame.show();
        centrarFormulario(internalFrame);
    }//GEN-LAST:event_moduloRolActionPerformed

    private void jMenu5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu5MouseClicked
        this.dispose();
        Login login = new Login();
        login.show();
    }//GEN-LAST:event_jMenu5MouseClicked

    private void reporteClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reporteClientesActionPerformed
        try {
            controlReportes.exportarClientes();
            Utilitario.MensajeExitoReporteExportado();
        } catch (FileNotFoundException | DocumentException ex) {
            Logger.getLogger(MenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_reporteClientesActionPerformed

    private void reporteEncomiendasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reporteEncomiendasActionPerformed
        try {
            controlReportes.exportarEncomiendas();
        } catch (FileNotFoundException | DocumentException ex) {
            Logger.getLogger(MenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_reporteEncomiendasActionPerformed

    private void reporteUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reporteUsuariosActionPerformed
        try {
            controlReportes.exportarUsuarios();
            Utilitario.MensajeExitoReporteExportado();
        } catch (FileNotFoundException | DocumentException ex) {
            Logger.getLogger(MenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_reporteUsuariosActionPerformed

    private void reporteAgenciasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reporteAgenciasActionPerformed
        try {
            controlReportes.exportarAgencias();
            Utilitario.MensajeExitoReporteExportado();
        } catch (FileNotFoundException | DocumentException ex) {
            Logger.getLogger(MenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_reporteAgenciasActionPerformed

    private void reporteProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reporteProductosActionPerformed
        try {
            controlReportes.exportarProductos();
            Utilitario.MensajeExitoReporteExportado();
        } catch (FileNotFoundException | DocumentException ex) {
            Logger.getLogger(MenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_reporteProductosActionPerformed

    private void reporteCategoriasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reporteCategoriasActionPerformed
        try {
            controlReportes.exportarCategorias();
            Utilitario.MensajeExitoReporteExportado();
        } catch (FileNotFoundException | DocumentException ex) {
            Logger.getLogger(MenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_reporteCategoriasActionPerformed

    private void reporteRolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reporteRolActionPerformed
        try {
            controlReportes.exportarRol();
            Utilitario.MensajeExitoReporteExportado();
        } catch (FileNotFoundException | DocumentException ex) {
            Logger.getLogger(MenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_reporteRolActionPerformed

    private void reporteTipoDocumentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reporteTipoDocumentoActionPerformed
        try {
            controlReportes.exportarTipoDocumentos();
            Utilitario.MensajeExitoReporteExportado();
        } catch (FileNotFoundException | DocumentException ex) {
            Logger.getLogger(MenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_reporteTipoDocumentoActionPerformed

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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuItem jMenuAddEncomienda;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JMenu jmenu2;
    private javax.swing.JLabel lblAgencia;
    private javax.swing.JLabel lblRol;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JMenuItem moduloAgencias;
    private javax.swing.JMenuItem moduloCategorias;
    private javax.swing.JMenuItem moduloClientes;
    private javax.swing.JMenuItem moduloProductos;
    private javax.swing.JMenuItem moduloRol;
    private javax.swing.JMenuItem moduloTipoDocumento;
    private javax.swing.JMenuItem moduloUsuarios;
    private javax.swing.JMenuItem reporteAgencias;
    private javax.swing.JMenuItem reporteCategorias;
    private javax.swing.JMenuItem reporteClientes;
    private javax.swing.JMenuItem reporteEncomiendas;
    private javax.swing.JMenuItem reporteProductos;
    private javax.swing.JMenuItem reporteRol;
    private javax.swing.JMenuItem reporteTipoDocumento;
    private javax.swing.JMenuItem reporteUsuarios;
    // End of variables declaration//GEN-END:variables
}
