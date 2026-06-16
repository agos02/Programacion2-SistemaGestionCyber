package com.cyber.vistas;

import com.cyber.controladores.ReportesControlador;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import javax.swing.table.DefaultTableModel;
import javax.swing.DefaultListModel;
import java.util.LinkedList;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReportesViews extends javax.swing.JFrame {
    
   private ReportesControlador controlador = new ReportesControlador();
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(ReportesViews.class.getName());
    
    // === NUEVOS CAMPOS PARA EL HISTORIAL Y SU PERSISTENCIA ===
    private LinkedList<String> historial = new LinkedList<>();
    private DefaultListModel<String> modeloHistorial = new DefaultListModel<>();
    private static final String ARCHIVO_HISTORIAL = "historial_consultas.dat";
    
   
    
    public ReportesViews() {
        initComponents();
        inicializarHistorial();
    }
    
  

// === FUNCIÓN PARA ENLAZAR EL MODELO Y CARGAR EL ARCHIVO AUTOMÁTICAMENTE ===
    private void inicializarHistorial() {
        listHistorial.setModel(modeloHistorial); // Reemplaza el modelo dummy de NetBeans por el nuestro
        cargarHistorialDesdeArchivo();           // Intenta leer el archivo .dat si ya existe
        
        // Evento de escucha: Al presionar una consulta del historial, se muestra en la tabla
        listHistorial.addListSelectionListener(evt -> {
            if (!evt.getValueIsAdjusting()) {
                String seleccionado = listHistorial.getSelectedValue();
                if (seleccionado != null) {
                    mostrarConsultaSeleccionadaEnTabla(seleccionado);
                }
            }
        });
    }

    private void cargarTabla(ResultSet rs) {
        try {
            DefaultTableModel modelo = new DefaultTableModel();
            ResultSetMetaData meta = rs.getMetaData();
            int columnas = meta.getColumnCount();

            // Agregar nombres de columnas
            for (int i = 1; i <= columnas; i++) {
                modelo.addColumn(meta.getColumnName(i));
            }

            // Agregar filas
            while (rs.next()) {
                Object[] fila = new Object[columnas];
                for (int i = 0; i < columnas; i++) {
                    fila[i] = rs.getObject(i + 1);
                }
                modelo.addRow(fila);
            }
            tblReportes.setModel(modelo);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
            
    // === TU FUNCIÓN MODIFICADA CON LÍMITE DE 15 Y GUARDADO AUTOMÁTICO ===
    private void agregarHistorial(String consulta) {
        historial.addFirst(consulta);

        if (historial.size() > 15) {
            historial.removeLast();
        }

        modeloHistorial.clear();
        for (String item : historial) {
            modeloHistorial.addElement(item);
        }
        
        guardarHistorialEnArchivo(); // Se guarda solo en cada nueva consulta
    }
    
    // === MÉTODOS DE SERIALIZACIÓN (EL ARCHIVO SE CREA SOLO) ===
    private void guardarHistorialEnArchivo() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARCHIVO_HISTORIAL))) {
            oos.writeObject(historial);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private void cargarHistorialDesdeArchivo() {
        File archivo = new File(ARCHIVO_HISTORIAL);
        if (!archivo.exists()) {
            return; // Si no existe, no hace nada (se creará solo al guardar)
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            historial = (LinkedList<String>) ois.readObject();
            modeloHistorial.clear();
            for (String item : historial) {
                modeloHistorial.addElement(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    // === MUESTRA EXCLUSIVAMENTE LAS CONSULTAS DE CANTIDAD EN LA JTABLE ===
    private void mostrarCantidadEnTabla(String concepto, Object cantidad) {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Concepto deseado");
        modelo.addColumn("Cantidad");
        modelo.addRow(new Object[]{concepto, cantidad});
        tblReportes.setModel(modelo);
    }
    
    // === PARSEADOR PARA CUANDO SE SELECCIONA EL HISTORIAL ===
    private void mostrarConsultaSeleccionadaEnTabla(String seleccionado) {
        try {
            if (seleccionado.contains("Cantidad de clientes:")) {
                String[] partes = seleccionado.split("Cantidad de clientes: ");
                mostrarCantidadEnTabla("Clientes", partes[1].trim());
            } else if (seleccionado.contains("Cantidad de sesiones:")) {
                String[] partes = seleccionado.split("Cantidad de sesiones: ");
                mostrarCantidadEnTabla("Sesiones", partes[1].trim());
            } else if (seleccionado.contains("Ingresos totales: $")) {
                String[] partes = seleccionado.split("Ingresos totales: \\$");
                mostrarCantidadEnTabla("Ingresos Totales", "$" + partes[1].trim());
            } else {
                // Para consultas de listados extensos informamos qué tipo de consulta era
                DefaultTableModel modelo = new DefaultTableModel();
                modelo.addColumn("Historial");
                modelo.addColumn("Detalle de Consulta Realizada");
                String limpio = seleccionado.substring(seleccionado.indexOf("]") + 1).trim();
                modelo.addRow(new Object[]{"Listado", limpio});
                tblReportes.setModel(modelo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    // === FUNCIÓN AUXILIAR PARA GENERAR LA FECHA Y HORA CON MINUTOS Y SEGUNDOS ===
    private String obtenerFechaHoraActual() {
        LocalDateTime ahora = LocalDateTime.now();
        DateTimeFormatter formateador = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return ahora.format(formateador);
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton7 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        ComboClientes = new javax.swing.JComboBox<>();
        ComboComputadoras = new javax.swing.JComboBox<>();
        ComboSesiones = new javax.swing.JComboBox<>();
        ComboProductos = new javax.swing.JComboBox<>();
        btnClientes = new javax.swing.JButton();
        btnComputadoras = new javax.swing.JButton();
        btnSesiones = new javax.swing.JButton();
        btnProducto = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        ComboCobros = new javax.swing.JComboBox<>();
        btnCobros = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblReportes = new javax.swing.JTable();
        btnMenu = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        listHistorial = new javax.swing.JList<>();
        jLabel7 = new javax.swing.JLabel();

        jButton7.setText("jButton7");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("REPORTES");

        ComboClientes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione...", "Listar clientes", "Cantidad de clientes", "Top 3 clientes" }));
        ComboClientes.addActionListener(this::ComboClientesActionPerformed);

        ComboComputadoras.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione...", "Listar computadoras", "Ver estados", "Computadoras disponibles", "Cantidad por estado", " " }));
        ComboComputadoras.addActionListener(this::ComboComputadorasActionPerformed);

        ComboSesiones.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione...", "Listar sesiones", "Cantidad de sesiones" }));
        ComboSesiones.addActionListener(this::ComboSesionesActionPerformed);

        ComboProductos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione...", "Listar productos", "Por precio ASC", "Mas vendido", "Poco stock", "Mas caro", "Mas barato" }));
        ComboProductos.addActionListener(this::ComboProductosActionPerformed);

        btnClientes.setText("Mostrar");
        btnClientes.addActionListener(this::btnClientesActionPerformed);

        btnComputadoras.setText("Mostrar");
        btnComputadoras.addActionListener(this::btnComputadorasActionPerformed);

        btnSesiones.setText("Mostrar");
        btnSesiones.addActionListener(this::btnSesionesActionPerformed);

        btnProducto.setText("Mostrar");
        btnProducto.addActionListener(this::btnProductoActionPerformed);

        jLabel2.setText("Clientes");

        jLabel3.setText("Computadoras");

        jLabel4.setText("Sesiones");

        jLabel5.setText("Productos");

        ComboCobros.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione...", "Obtener cobros", "Ingresos totales", "Ingresos por forma de pago" }));
        ComboCobros.addActionListener(this::ComboCobrosActionPerformed);

        btnCobros.setText("Mostrar");
        btnCobros.addActionListener(this::btnCobrosActionPerformed);

        jLabel6.setText("Cobros");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 470, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(ComboCobros, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnCobros))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(ComboSesiones, javax.swing.GroupLayout.Alignment.TRAILING, 0, 1, Short.MAX_VALUE)
                                    .addComponent(ComboComputadoras, javax.swing.GroupLayout.Alignment.TRAILING, 0, 1, Short.MAX_VALUE)
                                    .addComponent(ComboClientes, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(ComboProductos, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(52, 52, 52)
                                                .addComponent(btnComputadoras))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                .addGap(22, 22, 22)
                                                .addComponent(btnSesiones)))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(btnProducto)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnClientes)))))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ComboClientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnClientes)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ComboComputadoras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnComputadoras)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ComboSesiones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSesiones)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ComboProductos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnProducto)
                    .addComponent(jLabel5))
                .addGap(13, 13, 13)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ComboCobros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCobros)
                    .addComponent(jLabel6)))
        );

        tblReportes.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tblReportes);

        btnMenu.setText("Volver al Menú Principal");
        btnMenu.addActionListener(this::btnMenuActionPerformed);

        listHistorial.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(listHistorial);

        jLabel7.setText("HISTORIAL");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnMenu)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addGap(356, 356, 356))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48)
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
        //CLIENTES
    private void ComboClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboClientesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ComboClientesActionPerformed

    private void btnClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClientesActionPerformed

    String opcion = ComboClientes.getSelectedItem().toString();
        String tiempo = obtenerFechaHoraActual();

        switch(opcion) {
            case "Cantidad de clientes":
                int cantClientes = controlador.cantidadClientes();
                mostrarCantidadEnTabla("Clientes", cantClientes);
                agregarHistorial("[" + tiempo + "] Cantidad de clientes: " + cantClientes);
                break;

            case "Listar clientes":
                cargarTabla(controlador.listarClientes());
                agregarHistorial("[" + tiempo + "] Listar clientes");
                break;

            case "Top 3 clientes":
                cargarTabla(controlador.top3Clientes());
                agregarHistorial("[" + tiempo + "] Top 3 clientes");
                break;
        }
    
    }//GEN-LAST:event_btnClientesActionPerformed

    
        //SESIONES
    private void btnSesionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSesionesActionPerformed
       
        String opcion = ComboSesiones.getSelectedItem().toString();
        String tiempo = obtenerFechaHoraActual();

        switch(opcion) {
            case "Cantidad de sesiones":
                int cantSesiones = controlador.cantidadSesiones();
                mostrarCantidadEnTabla("Sesiones", cantSesiones);
                agregarHistorial("[" + tiempo + "] Cantidad de sesiones: " + cantSesiones);
                break;

            case "Listar sesiones":
                cargarTabla(controlador.listarSesiones());
                agregarHistorial("[" + tiempo + "] Listar sesiones");
                break;
        }
    }//GEN-LAST:event_btnSesionesActionPerformed

    private void ComboSesionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboSesionesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ComboSesionesActionPerformed

    
        //COMPUTADORAS
    private void ComboComputadorasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboComputadorasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ComboComputadorasActionPerformed

    private void btnComputadorasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnComputadorasActionPerformed
        String opcion = ComboComputadoras.getSelectedItem().toString();
        String tiempo = obtenerFechaHoraActual();
        
    switch(opcion) {
            case "Listar computadoras":
                cargarTabla(controlador.obtenerComputadoras());
                agregarHistorial("[" + tiempo + "] Listar computadoras");
                break;

            case "Ver estados":
                cargarTabla(controlador.estadoComputadoras());
                agregarHistorial("[" + tiempo + "] Ver estados");
                break;

            case "Computadoras disponibles":
                cargarTabla(controlador.computadorasDisponibles());
                agregarHistorial("[" + tiempo + "] Computadoras disponibles");
                break;

            case "Cantidad por estado":
                cargarTabla(controlador.cantidadPorEstado());
                agregarHistorial("[" + tiempo + "] Cantidad por estado");
                break;
        }

    }//GEN-LAST:event_btnComputadorasActionPerformed
    
    
        //PRODUCTOS
    private void ComboProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboProductosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ComboProductosActionPerformed

    private void btnProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProductoActionPerformed
        
        String opcion = ComboProductos.getSelectedItem().toString();
        String tiempo = obtenerFechaHoraActual();

        switch(opcion) {
            case "Listar productos":
                cargarTabla(controlador.obtenerProducto());
                agregarHistorial("[" + tiempo + "] Listar productos");
                break;

            case "Por precio ASC":
                cargarTabla(controlador.productoPrecio());
                agregarHistorial("[" + tiempo + "] Por precio ASC");
                break;

            case "Mas vendido":
                cargarTabla(controlador.MasVendido());
                agregarHistorial("[" + tiempo + "] Mas vendido");
                break;

            case "Poco stock":
                cargarTabla(controlador.pocoStock());
                agregarHistorial("[" + tiempo + "] Poco stock");
                break;

            case "Mas caro":
                cargarTabla(controlador.productoMasCaro());
                agregarHistorial("[" + tiempo + "] Mas caro");
                break;

            case "Mas barato":
                cargarTabla(controlador.productoMasBarato());
                agregarHistorial("[" + tiempo + "] Mas barato");
                break;
        }
    }//GEN-LAST:event_btnProductoActionPerformed
        
        //COBROS
    private void ComboCobrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboCobrosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ComboCobrosActionPerformed

    private void btnCobrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCobrosActionPerformed
        
        String opcion = ComboCobros.getSelectedItem().toString();
        String tiempo = obtenerFechaHoraActual();

        switch(opcion) {
            case "Obtener cobros":
                cargarTabla(controlador.obtenerCobros());
                agregarHistorial("[" + tiempo + "] Obtener cobros");
                break;

            case "Ingresos totales":
                double ingresos = controlador.ingresosTotales();
                mostrarCantidadEnTabla("Ingresos Totales", "$" + ingresos);
                agregarHistorial("[" + tiempo + "] Ingresos totales: $" + ingresos);
                break;

            case "Ingresos por forma de pago":
                cargarTabla(controlador.ingresosFormaPago());
                agregarHistorial("[" + tiempo + "] Ingresos por forma de pago");
                break;
        }
    }//GEN-LAST:event_btnCobrosActionPerformed

    
    //Botón que te lleva al menú principal de la aplicación
    private void btnMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuActionPerformed
        menu ventanaMenu = new menu();
        ventanaMenu.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnMenuActionPerformed
    
    
    
    
    
    
    
    
    
    public static void main(String args[]) {
        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new ReportesViews().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> ComboClientes;
    private javax.swing.JComboBox<String> ComboCobros;
    private javax.swing.JComboBox<String> ComboComputadoras;
    private javax.swing.JComboBox<String> ComboProductos;
    private javax.swing.JComboBox<String> ComboSesiones;
    private javax.swing.JButton btnClientes;
    private javax.swing.JButton btnCobros;
    private javax.swing.JButton btnComputadoras;
    private javax.swing.JButton btnMenu;
    private javax.swing.JButton btnProducto;
    private javax.swing.JButton btnSesiones;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JList<String> listHistorial;
    private javax.swing.JTable tblReportes;
    // End of variables declaration//GEN-END:variables
}
