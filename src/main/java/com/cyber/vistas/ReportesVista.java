package com.cyber.vistas;


import com.cyber.controladores.ReportesControlador;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import javax.swing.table.DefaultTableModel;
import java.util.LinkedHashMap;
import javax.swing.DefaultListModel;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ReportesVista extends javax.swing.JFrame {
    
private ReportesControlador controlador =
        new ReportesControlador();

private DefaultListModel<String> modeloHistorial =
        new DefaultListModel<>();

private LinkedHashMap<String, DefaultTableModel> historial =
        new LinkedHashMap<>();
    
    
    
    
    
    public ReportesVista() {
        initComponents();
        listaHistorial.setModel(modeloHistorial);
        // 1. Cargamos el historial guardado apenas abre la ventana
    cargarHistorialDeArchivo();
    
    // 2. Le decimos a la ventana que guarde el archivo justo antes de cerrarse
    this.addWindowListener(new java.awt.event.WindowAdapter() {
        @Override
        public void windowClosing(java.awt.event.WindowEvent windowEvent) {
            guardarHistorialEnArchivo();
        }
    });
    }
    
    
    
     
     
     private void guardarConsulta(
        String nombre,
        DefaultTableModel modelo) {

    DefaultTableModel copia = new DefaultTableModel();

    for (int i = 0; i < modelo.getColumnCount(); i++) {
        copia.addColumn(modelo.getColumnName(i));
    }

    for (int fila = 0; fila < modelo.getRowCount(); fila++) {

        Object[] datos =
                new Object[modelo.getColumnCount()];

        for (int col = 0;
             col < modelo.getColumnCount();
             col++) {

            datos[col] =
                    modelo.getValueAt(fila, col);
        }

        copia.addRow(datos);
    }

    String nombreConHora =
            nombre + " - " +
            LocalDateTime.now().format(
                    DateTimeFormatter.ofPattern(
                            "dd/MM HH:mm:ss"));

    historial.put(nombreConHora, copia);

    modeloHistorial.addElement(nombreConHora);

    if (modeloHistorial.size() > 15) {

        String primera =
                modeloHistorial.getElementAt(0);

        modeloHistorial.remove(0);

        historial.remove(primera);
    }
}
     
     private void guardarConsulta(String nombre, String mensaje) {
    // 1. Creamos un modelo de tabla vacío
    DefaultTableModel modeloUnico = new DefaultTableModel();
    
    // 2. Le agregamos una sola columna llamada "Resultado"
    modeloUnico.addColumn("Resultado Resumen");
    
    // 3. Le agregamos una sola fila con tu mensaje (la cantidad o el ingreso)
    modeloUnico.addRow(new Object[]{mensaje});

    // 4. Reutilizamos tu método original para guardarlo en el historial
    guardarConsulta(nombre, modeloUnico);
}
     
     private void guardarHistorialEnArchivo() {
    try {
        // Crea un archivo físico en la carpeta del proyecto
        FileOutputStream fos = new FileOutputStream("historial_reportes.dat");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        
        // Escribe todo nuestro LinkedHashMap en el archivo
        oos.writeObject(historial);
        
        oos.close();
        fos.close();
    } catch (Exception e) {
        System.out.println("Error al guardar historial: " + e.getMessage());
    }
}

    @SuppressWarnings("unchecked")
    private void cargarHistorialDeArchivo() {
    File archivo = new File("historial_reportes.dat");
    
    // Si es la primera vez que abres el programa y no existe el archivo, no hace nada
    if (!archivo.exists()) {
        return; 
    }

    try {
        FileInputStream fis = new FileInputStream("historial_reportes.dat");
        ObjectInputStream ois = new ObjectInputStream(fis);
        
        // Cargamos el mapa guardado en el archivo
        historial = (LinkedHashMap<String, DefaultTableModel>) ois.readObject();
        
        ois.close();
        fis.close();
        
        // Ahora reconstruimos la lista de la derecha (modeloHistorial) 
        // usando las llaves (nombres y fechas) que cargamos del mapa
        modeloHistorial.clear();
        for (String llave : historial.keySet()) {
            modeloHistorial.addElement(llave);
        }
        
    } catch (Exception e) {
        System.out.println("Error al cargar historial: " + e.getMessage());
    }
}
     
     private void mostrarConsultaGuardada() {

    String seleccion =
            listaHistorial.getSelectedValue();

    if(seleccion == null) {
        return;
    }

    tblReportes.setModel(
            historial.get(seleccion)
    );
}
     
     

private void cargarTabla(ResultSet rs) {

    try {

        java.sql.ResultSetMetaData meta = rs.getMetaData();

        int columnas = meta.getColumnCount();

        DefaultTableModel modelo = new DefaultTableModel();

        for (int i = 1; i <= columnas; i++) {
            modelo.addColumn(meta.getColumnName(i));
        }

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
        jLabel7 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        listaHistorial = new javax.swing.JList<>();

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
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

        jLabel7.setText("HISTORIAL");

        listaHistorial.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        listaHistorial.addListSelectionListener(this::listaHistorialValueChanged);
        jScrollPane3.setViewportView(listaHistorial);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 545, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addGap(163, 163, 163))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane3))
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

    ResultSet rs;
    
    switch(opcion) {

        case "Listar clientes":

        rs = controlador.listarClientes();

        cargarTabla(rs);

        guardarConsulta(
            "Listar clientes",
            (DefaultTableModel) tblReportes.getModel()
        );

        break;
        
        case "Cantidad de clientes":
    int cantClientes = controlador.cantidadClientes();
    String msjClientes = "Cantidad de clientes registrados: " + cantClientes;
    
    JOptionPane.showMessageDialog(this, msjClientes);
    guardarConsulta("Cantidad de clientes", msjClientes);
    break;
        
        case "Top 3 clientes":

        rs = controlador.top3Clientes();

        cargarTabla(rs);

        guardarConsulta(
            "Top 3 clientes",
            (DefaultTableModel) tblReportes.getModel()
        );

        break;
    }
    }//GEN-LAST:event_btnClientesActionPerformed

    
        //SESIONES
    private void btnSesionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSesionesActionPerformed
       
        String opcion = ComboSesiones.getSelectedItem().toString();

        ResultSet rs;
        
    switch(opcion) {

        case "Cantidad de sesiones":
    // Obtenemos el dato
    int cantidad = controlador.cantidadSesiones();
    String msjSesiones = "Cantidad de sesiones: " + cantidad;
    
    // Lo mostramos en el cartelito (como ya lo tienes)
    JOptionPane.showMessageDialog(this, msjSesiones);
    
    // LO NUEVO: Lo mandamos al historial
    guardarConsulta("Cantidad de sesiones", msjSesiones);
    break;

        case "Listar sesiones":

  rs = controlador.listarSesiones();

    cargarTabla(rs);

    guardarConsulta(
        "Listar sesiones",
        (DefaultTableModel) tblReportes.getModel()
    );

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

        ResultSet rs;

    
    switch(opcion) {

        case "Listar computadoras":

    rs = controlador.obtenerComputadoras();

    cargarTabla(rs);

    guardarConsulta(
        "Listar computadoras",
        (DefaultTableModel) tblReportes.getModel()
    );

    break;

        case "Ver estados":

    rs = controlador.estadoComputadoras();

    cargarTabla(rs);

    guardarConsulta(
        "Estado computadoras",
        (DefaultTableModel) tblReportes.getModel()
    );

    break;

        case "Computadoras disponibles":

    rs = controlador.computadorasDisponibles();

    cargarTabla(rs);

    guardarConsulta(
        "Computadoras disponibles",
        (DefaultTableModel) tblReportes.getModel()
    );

    break;

        case "Cantidad por estado":

    rs = controlador.cantidadPorEstado();

    cargarTabla(rs);

    guardarConsulta(
        "Cantidad por estado",
        (DefaultTableModel) tblReportes.getModel()
    );

    break;
    }
    }//GEN-LAST:event_btnComputadorasActionPerformed
    
    
        //PRODUCTOS
    private void ComboProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboProductosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ComboProductosActionPerformed

    private void btnProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProductoActionPerformed
        
        String opcion = ComboProductos.getSelectedItem().toString();
        
        ResultSet rs;


    switch(opcion) {
        
        case "Listar productos":

    rs = controlador.obtenerProducto();

    cargarTabla(rs);

    guardarConsulta(
        "Listar productos",
        (DefaultTableModel) tblReportes.getModel()
    );

    break;

        case "Por precio ASC":

    rs = controlador.productoPrecio();

    cargarTabla(rs);

    guardarConsulta(
        "Productos por precio",
        (DefaultTableModel) tblReportes.getModel()
    );

    break;

        case "Mas vendido":

    rs = controlador.MasVendido();

    cargarTabla(rs);

    guardarConsulta(
        "Producto más vendido",
        (DefaultTableModel) tblReportes.getModel()
    );

    break;

        case "Poco stock":

    rs = controlador.pocoStock();

    cargarTabla(rs);

    guardarConsulta(
        "Productos con poco stock",
        (DefaultTableModel) tblReportes.getModel()
    );

    break;

       case "Mas caro":

    rs = controlador.productoMasCaro();

    cargarTabla(rs);

    guardarConsulta(
        "Producto más caro",
        (DefaultTableModel) tblReportes.getModel()
    );

    break;

        case "Mas barato":

    rs = controlador.productoMasBarato();

    cargarTabla(rs);

    guardarConsulta(
        "Producto más barato",
        (DefaultTableModel) tblReportes.getModel()
    );

    break;
    }
    }//GEN-LAST:event_btnProductoActionPerformed
        
        //COBROS
    private void ComboCobrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboCobrosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ComboCobrosActionPerformed

    private void btnCobrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCobrosActionPerformed
        
        String opcion = ComboCobros.getSelectedItem().toString();
        
        ResultSet rs;

    switch(opcion) {

       case "Obtener cobros":

    rs = controlador.obtenerCobros();

    cargarTabla(rs);

    guardarConsulta(
        "Obtener cobros",
        (DefaultTableModel) tblReportes.getModel()
    );

    break;
    
        case "Ingresos totales":
    // Obtenemos el dato
    double ingresos = controlador.ingresosTotales();
    String msjCobros = "Ingresos totales: $" + ingresos;
    
    // Lo mostramos en el cartelito
    JOptionPane.showMessageDialog(this, msjCobros);
    
    // LO NUEVO: Lo mandamos al historial
    guardarConsulta("Ingresos totales", msjCobros);
    break;

        case "Ingresos por forma de pago":

    rs = controlador.ingresosFormaPago();

    cargarTabla(rs);

    guardarConsulta(
        "Ingresos por forma de pago",
        (DefaultTableModel) tblReportes.getModel()
    );

    break;
    }
    }//GEN-LAST:event_btnCobrosActionPerformed

    private void listaHistorialValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_listaHistorialValueChanged

    if (!evt.getValueIsAdjusting()) {
        mostrarConsultaGuardada(); }
    }//GEN-LAST:event_listaHistorialValueChanged
    
    
    
    
    
    
        
    
    public static void main(String args[]) {
        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
           
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new ReportesVista().setVisible(true));
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
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JList<String> listaHistorial;
    private javax.swing.JTable tblReportes;
    // End of variables declaration//GEN-END:variables
}
