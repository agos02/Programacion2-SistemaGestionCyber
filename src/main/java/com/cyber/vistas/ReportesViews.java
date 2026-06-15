package com.cyber.vistas;

import com.cyber.controladores.ReportesControlador;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import javax.swing.table.DefaultTableModel;

public class ReportesViews extends javax.swing.JFrame {
    
    private ReportesControlador controlador =
            new ReportesControlador();
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(ReportesViews.class.getName());
    
    
    

    
    
    
    
    
    public ReportesViews() {
        initComponents();
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
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 760, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 118, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
        //CLIENTES
    private void ComboClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboClientesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ComboClientesActionPerformed

    private void btnClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClientesActionPerformed

    String opcion = ComboClientes.getSelectedItem().toString();

    switch(opcion) {

        case "Cantidad de clientes":
            JOptionPane.showMessageDialog(
                this,
                "Cantidad de clientes: " +
                controlador.cantidadClientes()
            );
            break;

        case "Listar clientes":
            cargarTabla(controlador.listarClientes());
            break;

        case "Top 3 clientes":
            cargarTabla(controlador.top3Clientes());
            break;
    }

    }//GEN-LAST:event_btnClientesActionPerformed

    
        //SESIONES
    private void btnSesionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSesionesActionPerformed
       
        String opcion = ComboSesiones.getSelectedItem().toString();

    switch(opcion) {

        case "Cantidad de sesiones":
            JOptionPane.showMessageDialog(
                this,
                "Cantidad de sesiones: " +
                controlador.cantidadSesiones()
            );
            break;

        case "Listar sesiones":
            cargarTabla(controlador.listarSesiones());
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

    switch(opcion) {

        case "Listar computadoras":
            cargarTabla(controlador.obtenerComputadoras());
            break;

        case "Ver estados":
            cargarTabla(controlador.estadoComputadoras());
            break;

        case "Computadoras disponibles":
            cargarTabla(controlador.computadorasDisponibles());
            break;

        case "Cantidad por estado":
            cargarTabla(controlador.cantidadPorEstado());
            break;
    }

    }//GEN-LAST:event_btnComputadorasActionPerformed
    
    
        //PRODUCTOS
    private void ComboProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboProductosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ComboProductosActionPerformed

    private void btnProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProductoActionPerformed
        
        String opcion = ComboProductos.getSelectedItem().toString();

    switch(opcion) {

        case "Listar productos":
            cargarTabla(controlador.obtenerProducto());
            break;

        case "Por precio ASC":
            cargarTabla(controlador.productoPrecio());
            break;

        case "Mas vendido":
            cargarTabla(controlador.MasVendido());
            break;

        case "Poco stock":
            cargarTabla(controlador.pocoStock());
            break;

        case "Mas caro":
            cargarTabla(controlador.productoMasCaro());
            break;

        case "Mas barato":
            cargarTabla(controlador.productoMasBarato());
            break;
    }
    }//GEN-LAST:event_btnProductoActionPerformed
        
        //COBROS
    private void ComboCobrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboCobrosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ComboCobrosActionPerformed

    private void btnCobrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCobrosActionPerformed
        
        String opcion = ComboCobros.getSelectedItem().toString();

    switch(opcion) {

        case "Obtener cobros":
            cargarTabla(controlador.obtenerCobros());
            break;

        case "Ingresos totalles":
            JOptionPane.showMessageDialog(
                this,
                "Ingresos totales: $" +
                controlador.ingresosTotales()
            );
            break;

        case "Ingresos por forma de pago":
            cargarTabla(controlador.ingresosFormaPago());
            break;
    }
    }//GEN-LAST:event_btnCobrosActionPerformed
    
    
    
    
    
    
    
    
    
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
    private javax.swing.JButton btnProducto;
    private javax.swing.JButton btnSesiones;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblReportes;
    // End of variables declaration//GEN-END:variables
}
