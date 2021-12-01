/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.encosoft.vista;

import com.encosoft.controlador.ControlClientes;
import com.encosoft.controlador.ControlEncomienda;
import com.encosoft.controlador.ControlProductos;
import com.encosoft.controlador.ControlTipoDocumento;
import com.encosoft.modelo.Cliente;
import com.encosoft.modelo.DetalleEncomienda;
import com.encosoft.modelo.Encomienda;
import com.encosoft.modelo.Productos;
import com.encosoft.modelo.TipoDocumento;
import com.encosoft.util.Constantes;
import com.encosoft.util.Utilitario;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author echamaya
 */
public final class RegistroEncomiendas extends javax.swing.JInternalFrame {

    ControlClientes controlClientes = new ControlClientes();
    ControlProductos controlProductos = new ControlProductos();
    int idCliente, idProducto;
    DefaultTableModel modeloDetalle = new DefaultTableModel();
    Map<Integer, String> tipoDocumentoMap = new HashMap<>();
    List<DetalleEncomienda> detalleEncomiendasLista = new ArrayList<>();

    boolean tieneClienteSeleccionado = false, tieneProductoSeleccionado = false;

    /**
     * Creates new form RegistroEncomiendas
     */
    public RegistroEncomiendas() {
        initComponents();
        listarTipoDocumento();
        listarClientes();
        listarProductos();
        definirColumnasDetalle();
    }

    void definirColumnasDetalle() {
        tblDetalleEncomienda.setModel(modeloDetalle);
        modeloDetalle.addColumn("ID");
        modeloDetalle.addColumn("PRODUCTO");
        modeloDetalle.addColumn("PESO");
        modeloDetalle.addColumn("CANTIDAD");
        modeloDetalle.addColumn("PRECIO UNITARIO");
        modeloDetalle.addColumn("TOTAL");
        modeloDetalle.addColumn("DESCRIPCION");
        Utilitario.ocultarColumnas(tblDetalleEncomienda, 0);
        Utilitario.ocultarColumnas(tblDetalleEncomienda, 6);
    }

    void listarClientes() {
        DefaultTableModel modelo = new DefaultTableModel();
        tblClientes.setModel(modelo);
        modelo.addColumn("ID");
        modelo.addColumn("CLIENTE");
        modelo.addColumn("TIPO DOC");
        modelo.addColumn("NRO DOC");

        List<Cliente> listaClientes = controlClientes.listarPorCliente(txtBuscarClientePorApellido.getText());
        modelo.setRowCount(0);
        for (Cliente c : listaClientes) {
            Object data[] = {
                c.getId(),
                Utilitario.concatenar(c.getApepat() + " " + c.getApemat() + " " + c.getNombre()),
                c.getNrodocumento(),
                c.getEmail()
            };
            modelo.addRow(data);
        }
        Utilitario.ocultarColumnas(tblClientes, 0);
    }

    void listarProductos() {
        DefaultTableModel modelo = new DefaultTableModel();
        tblProductos.setModel(modelo);
        modelo.addColumn("ID");
        modelo.addColumn("DESCRIPCION");

        List<Productos> listaProductos = controlProductos.listarPorDescripcion(txtBuscarPorDescripcion.getText());
        modelo.setRowCount(0);
        for (Productos p : listaProductos) {
            Object data[] = {
                p.getId(),
                p.getDescripcion()
            };
            modelo.addRow(data);
        }
    }

    void mostrarDatosClientes() {
        int fila = tblClientes.getSelectedRow();
        idCliente = Integer.parseInt(tblClientes.getValueAt(fila, 0).toString());
        lblCliente.setText(tblClientes.getValueAt(fila, 1).toString());
        lblNroDocumento.setText(tblClientes.getValueAt(fila, 2).toString());
        lblEmail.setText(tblClientes.getValueAt(fila, 3).toString());
        limpiarDialogFormularioClientes();
        tieneClienteSeleccionado = true;
    }

    void mostrarProductos() {
        String productoSeleccionado, descripcion = null;
        int fila, cantidad = 0, peso = 0;
        double precioUnitario = 0;
        fila = tblProductos.getSelectedRow();
        idProducto = Integer.parseInt(tblProductos.getValueAt(fila, 0).toString());
        productoSeleccionado = tblProductos.getValueAt(fila, 1).toString();
        validarInputDialog(peso, cantidad, precioUnitario, descripcion, productoSeleccionado);
        limpiarDialogFormularioProductos();
        calcularPrecioTotal();
        tieneProductoSeleccionado = true;
    }

    void validarInputDialog(int peso, int cantidad, double precioUnitario, String descripcion, String productoSeleccionado) {
        boolean esPesoValido = false, esCantidadValida = false, esPrecioUnitValido = false, esDescripcionValida = false;
        while (!esPesoValido) {
            try {
                String _peso = JOptionPane.showInputDialog(null, "Ingrese el peso", productoSeleccionado, JOptionPane.INFORMATION_MESSAGE);
                if (_peso == null) {
                    break;
                } else {
                    peso = Integer.parseInt(_peso);
                    esPesoValido = true;
                }
            } catch (NumberFormatException e) {
                esPesoValido = false;
                JOptionPane.showMessageDialog(null, "El dato ingresado no es correcto", "Error formato", JOptionPane.WARNING_MESSAGE);
            }
        }
        while (!esCantidadValida) {
            try {
                String _cantidad = JOptionPane.showInputDialog(null, "Ingrese la cantidad", productoSeleccionado, JOptionPane.INFORMATION_MESSAGE);
                if (_cantidad == null) {
                    break;
                } else {
                    cantidad = Integer.parseInt(_cantidad);
                    esCantidadValida = true;
                }
            } catch (NumberFormatException e) {
                esCantidadValida = false;
                JOptionPane.showMessageDialog(null, "El dato ingresado no es correcto", "Error formato", JOptionPane.WARNING_MESSAGE);
            }
        }
        while (!esPrecioUnitValido) {
            try {
                String _precioUnitario = JOptionPane.showInputDialog(null, "Ingrese el precio unitario", productoSeleccionado, JOptionPane.INFORMATION_MESSAGE);
                if (_precioUnitario == null) {
                    break;
                } else {
                    precioUnitario = Double.parseDouble(_precioUnitario);
                    esPrecioUnitValido = true;
                }
            } catch (NumberFormatException e) {
                esPrecioUnitValido = false;
                JOptionPane.showMessageDialog(null, "El dato ingresado no es correcto", "Error formato", JOptionPane.WARNING_MESSAGE);
            }
        }
        while (!esDescripcionValida) {
            descripcion = JOptionPane.showInputDialog(null, "Ingrese alguna descripción", productoSeleccionado, JOptionPane.INFORMATION_MESSAGE);
            if (descripcion == null) {
                break;
            }
            esDescripcionValida = descripcion.trim().length() > 0;
            if (!esDescripcionValida) {
                JOptionPane.showMessageDialog(null, "Debe ingresar una descripción", "Error formato", JOptionPane.WARNING_MESSAGE);
            }
        }

        if (!esPesoValido || !esCantidadValida || !esPrecioUnitValido || !esDescripcionValida) {
            return;
        }

        enviarDatosProductoAtablaDetalle(productoSeleccionado, peso, cantidad, precioUnitario, descripcion);
        guardarDetalleEncomienda();
    }

    boolean existeProducto() {
        int cantidadFilas = tblDetalleEncomienda.getRowCount();
        if (cantidadFilas > 0) {
            for (int i = 0; i < cantidadFilas; i++) {
                int id = Integer.parseInt(tblDetalleEncomienda.getValueAt(i, 0).toString());
                if (id == idProducto) return true;
            }
        }
        return false;
    }

    void actualizarProductoExistente(String producto, int peso, int cantidad, double precioUnitario, String descripcion) {
        for (int i = 0; i < tblDetalleEncomienda.getRowCount(); i++) {
            int id = Integer.parseInt(tblDetalleEncomienda.getValueAt(i, 0).toString());
            if (id == idProducto) {
                int pesoAnterior = Integer.parseInt(tblDetalleEncomienda.getValueAt(i, 2).toString());
                int pesoTotal = pesoAnterior + peso;
                tblDetalleEncomienda.setValueAt(pesoTotal, i, 2);

                int cantidadAnterior = Integer.parseInt(tblDetalleEncomienda.getValueAt(i, 3).toString());
                int cantidadTotal = cantidadAnterior + cantidad;
                tblDetalleEncomienda.setValueAt(cantidadTotal, i, 3);

                double precioUnitarioAnterior = Double.parseDouble(tblDetalleEncomienda.getValueAt(i, 4).toString());
                double precioUnitarioTotal = precioUnitarioAnterior + precioUnitario;
                tblDetalleEncomienda.setValueAt(precioUnitarioTotal, i, 4);

                double nuevoTotal = cantidadTotal * precioUnitarioTotal;
                tblDetalleEncomienda.setValueAt(nuevoTotal, i, 5);
            }
        }
        calcularPrecioTotal();
    }

    void enviarDatosProductoAtablaDetalle(String producto, int peso, int cantidad, double precioUnitario, String descripcion) {
        if (existeProducto()) {
            actualizarProductoExistente(producto, peso, cantidad, precioUnitario, descripcion);
        } else {
            double total = cantidad * precioUnitario;
            Object data[] = {idProducto, producto, peso, cantidad, precioUnitario, total, descripcion};
            modeloDetalle.addRow(data);
        }
    }

    void calcularPrecioTotal() {
        double total = 0;
        for (int i = 0; i < tblDetalleEncomienda.getRowCount(); i++) {
            total += Double.parseDouble(tblDetalleEncomienda.getValueAt(i, 5).toString());
        }
        lblPrecioTotal.setText(String.valueOf(total));
    }

    void validarCantidadProducto(int cantidad, String productoSeleccionado) {
        boolean esCantidadValida = false;
        while (!esCantidadValida) {
            try {
                cantidad = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese la cantidad", productoSeleccionado, JOptionPane.INFORMATION_MESSAGE));
                esCantidadValida = true;
            } catch (NumberFormatException e) {
                esCantidadValida = false;
            }
        }
    }

    void limpiarDialogFormularioClientes() {
        dialogCliente.setVisible(false);
        tblClientes.clearSelection();
        txtBuscarClientePorApellido.setText("");
        listarClientes();
    }

    void limpiarDialogFormularioProductos() {
        dialogProductos.setVisible(false);
        tblProductos.clearSelection();
        txtBuscarPorDescripcion.setText("");
    }

    void mostrarDialog(JDialog dialog) {
        Dimension desktopSize = MenuPrincipal.jDesktopPane2.getSize();
        Dimension internalFrameSize = dialog.getSize();
        int width = (desktopSize.width - internalFrameSize.width) / 2;
        int height = (desktopSize.height - internalFrameSize.height) / 2;
        dialog.setLocation(width, height);
        dialog.setVisible(true);
    }

    void listarTipoDocumento() {
        ControlTipoDocumento controlTipoDocumento = new ControlTipoDocumento();
        List<TipoDocumento> lista = controlTipoDocumento.listar();
        cboTipoDocumento.addItem("SELECCIONAR");
        for (TipoDocumento tipoDocumento : lista) {
            tipoDocumentoMap.put(tipoDocumento.getId(), tipoDocumento.getDescripcion());
            cboTipoDocumento.addItem(tipoDocumento.getDescripcion());
        }
    }

    void quitarProducto() {
        int fila = tblDetalleEncomienda.getSelectedRow();
        if (fila >= 0) {
            int resultado = JOptionPane.showConfirmDialog(null, "¿Está seguro de eliminar el producto seleccionado?", Constantes.TITULO_MENSAJE, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (resultado == JOptionPane.YES_OPTION) {
                modeloDetalle.removeRow(tblDetalleEncomienda.getSelectedRow());
                calcularPrecioTotal();
                guardarDetalleEncomienda();
            } else {
                tblDetalleEncomienda.clearSelection();
            }
        } else {
            Utilitario.MensajeSeleccionarRegistro();
        }
    }

    boolean esValidoRegistrarEncomienda() {
        if (!tieneClienteSeleccionado) {
            Utilitario.MostrarMensajeAdvertencia("Debe seleccionar un cliente");
            return false;
        }

        if (Utilitario.validarCamposVacios(/*cboAgencia,*/cboTipoDocumento)) {
            return false;
        }
        if (Utilitario.validarCamposVacios(txtNroDocumento, txtApellidoPat, txtApellidoMat, txtNombres)) {
            return false;
        }

        if (!tieneProductoSeleccionado) {
            Utilitario.MostrarMensajeAdvertencia("Debe seleccionar por lo menos un producto");
            return false;
        }

        return true;
    }

    void guardarDetalleEncomienda() {
        detalleEncomiendasLista.clear();
        for (int i = 0; i < tblDetalleEncomienda.getRowCount(); i++) {
            DetalleEncomienda de = new DetalleEncomienda();

            de.setIdproducto(Integer.parseInt(tblDetalleEncomienda.getValueAt(i, 0).toString()));
            de.setPeso(Integer.parseInt(tblDetalleEncomienda.getValueAt(i, 2).toString()));
            de.setCantidad(Integer.parseInt(tblDetalleEncomienda.getValueAt(i, 3).toString()));
            de.setPreciounitario(Double.parseDouble(tblDetalleEncomienda.getValueAt(i, 4).toString()));
            de.setDescripcion(tblDetalleEncomienda.getValueAt(i, 6).toString());

            detalleEncomiendasLista.add(de);
        }
    }

    void registrarEncomienda() {
        if (!esValidoRegistrarEncomienda()) {
            return;
        }

        Encomienda encomienda = new Encomienda();
        encomienda.setIdagencia(1);
        encomienda.setIdtipodocumento(Utilitario.obtenerIdComboBox(tipoDocumentoMap, cboTipoDocumento));
        encomienda.setNumerodocumento(txtNroDocumento.getText());
        encomienda.setReceptorapepat(txtApellidoPat.getText());
        encomienda.setReceptorapemat(txtApellidoMat.getText());
        encomienda.setReceptornombres(txtNombres.getText());
        encomienda.setIdcliente(idCliente);
        encomienda.setFecha(Utilitario.obtenerFechaDesdeDate(new Date()));
        encomienda.setPreciototal(Double.parseDouble(lblPrecioTotal.getText()));
        encomienda.setDetalleEncomiendas(detalleEncomiendasLista);

        ControlEncomienda controlEncomienda = new ControlEncomienda();

        int resultado = JOptionPane.showConfirmDialog(null, "¿Desea registrar la encomienda?", Constantes.TITULO_MENSAJE, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (resultado == JOptionPane.YES_OPTION) {
            boolean esExito = controlEncomienda.insertar(encomienda);
            if (esExito) {
                limpiar();
                Utilitario.MensajeExitoGenerico();
            } else {
                Utilitario.MensajeErrorGenerico();
            }
        }
    }

    void limpiarTablaDetalle() {
        modeloDetalle.getDataVector().removeAllElements();
        modeloDetalle.fireTableDataChanged();
    }

    void limpiar() {
        tieneClienteSeleccionado = false;

        Utilitario.limpiarLabels(lblCliente, lblNroDocumento, lblEmail, lblPrecioTotal);
        Utilitario.limpiarCombobox(cboAgencia, cboTipoDocumento);
        Utilitario.limpiarTextbox(txtNroDocumento, txtApellidoPat, txtApellidoMat, txtNombres);
        tieneProductoSeleccionado = false;
        lblPrecioTotal.setText("0.00");
        limpiarTablaDetalle();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dialogCliente = new javax.swing.JDialog();
        jPanel4 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblClientes = new javax.swing.JTable();
        jLabel15 = new javax.swing.JLabel();
        txtBuscarClientePorApellido = new javax.swing.JTextField();
        dialogProductos = new javax.swing.JDialog();
        jPanel5 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblProductos = new javax.swing.JTable();
        jLabel17 = new javax.swing.JLabel();
        txtBuscarPorDescripcion = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        btnSeleccionarCliente = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        lblCliente = new javax.swing.JLabel();
        lblNroDocumento = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblEmail = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        cboAgencia = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        cboTipoDocumento = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        txtNroDocumento = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtApellidoPat = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtApellidoMat = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtNombres = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        btnSeleccionarProductos = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDetalleEncomienda = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        lblPrecioTotal = new javax.swing.JLabel();
        btnQuitarProducto = new javax.swing.JButton();
        btnGuardarEncomienda = new javax.swing.JButton();

        dialogCliente.setTitle("CLIENTES");
        dialogCliente.setResizable(false);
        dialogCliente.setSize(new java.awt.Dimension(500, 250));
        dialogCliente.setType(java.awt.Window.Type.POPUP);

        jPanel4.setBackground(new java.awt.Color(0, 0, 153));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("SELECCIONAR CLIENTE");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        tblClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblClientesMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblClientes);

        jLabel15.setText("Buscar cliente por apellidos:");

        txtBuscarClientePorApellido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBuscarClientePorApellidoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout dialogClienteLayout = new javax.swing.GroupLayout(dialogCliente.getContentPane());
        dialogCliente.getContentPane().setLayout(dialogClienteLayout);
        dialogClienteLayout.setHorizontalGroup(
            dialogClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 564, Short.MAX_VALUE)
            .addGroup(dialogClienteLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel15)
                .addGap(18, 18, 18)
                .addComponent(txtBuscarClientePorApellido)
                .addContainerGap())
        );
        dialogClienteLayout.setVerticalGroup(
            dialogClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dialogClienteLayout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(dialogClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(txtBuscarClientePorApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        dialogProductos.setTitle("PRODUCTOS");
        dialogProductos.setResizable(false);
        dialogProductos.setSize(new java.awt.Dimension(500, 250));
        dialogProductos.setType(java.awt.Window.Type.POPUP);

        jPanel5.setBackground(new java.awt.Color(0, 0, 153));

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("SELECCIONAR PRODUCTOS");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        tblProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblProductos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProductosMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblProductos);

        jLabel17.setText("Buscar por descripción");

        txtBuscarPorDescripcion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBuscarPorDescripcionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout dialogProductosLayout = new javax.swing.GroupLayout(dialogProductos.getContentPane());
        dialogProductos.getContentPane().setLayout(dialogProductosLayout);
        dialogProductosLayout.setHorizontalGroup(
            dialogProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 564, Short.MAX_VALUE)
            .addGroup(dialogProductosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel17)
                .addGap(18, 18, 18)
                .addComponent(txtBuscarPorDescripcion)
                .addContainerGap())
        );
        dialogProductosLayout.setVerticalGroup(
            dialogProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dialogProductosLayout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(dialogProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(txtBuscarPorDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setClosable(true);
        setTitle("REGISTRO DE ENCOMIENDAS");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Información cliente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnSeleccionarCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/lupa-pequena.png"))); // NOI18N
        btnSeleccionarCliente.setText("SELECCIONAR CLIENTE");
        btnSeleccionarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionarClienteActionPerformed(evt);
            }
        });
        jPanel1.add(btnSeleccionarCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 27, -1, -1));

        jLabel1.setText("Cliente:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 20, 230, -1));
        jPanel1.add(lblCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 40, 210, 20));
        jPanel1.add(lblNroDocumento, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 40, 90, 20));

        jLabel4.setText("Nro documento:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 20, 90, -1));

        jLabel5.setText("Correo electrónico:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 20, 170, -1));
        jPanel1.add(lblEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 40, 170, 20));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Información del receptor", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jLabel8.setText("Agencia:");

        cboAgencia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "AGENCIA A" }));

        jLabel9.setText("Tipo documento:");

        jLabel10.setText("Número documento:");

        jLabel11.setText("Apellido paterno:");

        jLabel12.setText("Apellido materno:");

        jLabel13.setText("Nombres:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cboAgencia, 0, 200, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtApellidoPat, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cboTipoDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtApellidoMat, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtNroDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtNombres, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboAgencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboTipoDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNroDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtApellidoPat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtApellidoMat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNombres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Información encomienda", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        btnSeleccionarProductos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/agregar-productos.png"))); // NOI18N
        btnSeleccionarProductos.setText("SELECCIONAR PRODUCTOS");
        btnSeleccionarProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionarProductosActionPerformed(evt);
            }
        });

        tblDetalleEncomienda.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblDetalleEncomienda);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Precio total:");

        lblPrecioTotal.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblPrecioTotal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPrecioTotal.setText("0.00");

        btnQuitarProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/quitar-producto.png"))); // NOI18N
        btnQuitarProducto.setText("QUITAR PRODUCTO");
        btnQuitarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuitarProductoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblPrecioTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(btnQuitarProducto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSeleccionarProductos)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSeleccionarProductos)
                    .addComponent(btnQuitarProducto))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(lblPrecioTotal))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnGuardarEncomienda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/guardar-encomienda.png"))); // NOI18N
        btnGuardarEncomienda.setText("GUARDAR ENCOMIENDA");
        btnGuardarEncomienda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarEncomiendaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnGuardarEncomienda)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnGuardarEncomienda, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSeleccionarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionarClienteActionPerformed
        mostrarDialog(dialogCliente);
    }//GEN-LAST:event_btnSeleccionarClienteActionPerformed

    private void txtBuscarClientePorApellidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarClientePorApellidoActionPerformed
        listarClientes();
    }//GEN-LAST:event_txtBuscarClientePorApellidoActionPerformed

    private void tblClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblClientesMouseClicked
        mostrarDatosClientes();
    }//GEN-LAST:event_tblClientesMouseClicked

    private void tblProductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProductosMouseClicked
        mostrarProductos();
    }//GEN-LAST:event_tblProductosMouseClicked

    private void txtBuscarPorDescripcionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarPorDescripcionActionPerformed
        listarProductos();
    }//GEN-LAST:event_txtBuscarPorDescripcionActionPerformed

    private void btnSeleccionarProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionarProductosActionPerformed
        mostrarDialog(dialogProductos);
    }//GEN-LAST:event_btnSeleccionarProductosActionPerformed

    private void btnQuitarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuitarProductoActionPerformed
        quitarProducto();
    }//GEN-LAST:event_btnQuitarProductoActionPerformed

    private void btnGuardarEncomiendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarEncomiendaActionPerformed
        registrarEncomienda();
    }//GEN-LAST:event_btnGuardarEncomiendaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGuardarEncomienda;
    private javax.swing.JButton btnQuitarProducto;
    private javax.swing.JButton btnSeleccionarCliente;
    private javax.swing.JButton btnSeleccionarProductos;
    private javax.swing.JComboBox<String> cboAgencia;
    private javax.swing.JComboBox<String> cboTipoDocumento;
    private javax.swing.JDialog dialogCliente;
    private javax.swing.JDialog dialogProductos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblCliente;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblNroDocumento;
    private javax.swing.JLabel lblPrecioTotal;
    private javax.swing.JTable tblClientes;
    private javax.swing.JTable tblDetalleEncomienda;
    private javax.swing.JTable tblProductos;
    private javax.swing.JTextField txtApellidoMat;
    private javax.swing.JTextField txtApellidoPat;
    private javax.swing.JTextField txtBuscarClientePorApellido;
    private javax.swing.JTextField txtBuscarPorDescripcion;
    private javax.swing.JTextField txtNombres;
    private javax.swing.JTextField txtNroDocumento;
    // End of variables declaration//GEN-END:variables
}
