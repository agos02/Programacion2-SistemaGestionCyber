package com.cyber.vistas;

import com.cyber.dao.CobroDAO;
import com.cyber.modelos.Cobro;
import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class CobroVista extends JFrame {

    // Componentes del formulario (Atributos de la clase)
    private JTextField txtMontoPC;
    private JTextField txtMontoKiosco;
    private JTextField txtMontoTotal;
    private JComboBox<String> cbxFormaPago;
    private JButton btnConfirmarPago;
    private JButton btnImprimirTicket;
    private JButton btnCancelar;
    private JTable tblHistorialCobros;
    private DefaultTableModel modeloTabla;
    
    // Instancia del DAO para cargar los datos
    private CobroDAO cobroDAO;

    //Constructor de la ventana
    public CobroVista() {
        cobroDAO = new CobroDAO();
        configurarVentana();
        inicializarComponentes();
        cargarHistorial(); // Llena la tabla apenas se abre la pantalla
    }

    // Configuración básica del JFrame
    private void configurarVentana() {
        setTitle("Gestión de Cobros - Caja Cyber");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); // Centra la ventana en la pantalla
        setLayout(new BorderLayout(10, 10)); // Margen entre secciones
    }

    private void inicializarComponentes() {
        // --- SECCIÓN SUPERIOR Y CENTRAL: Formulario de Cobro ---
        JPanel pnlFormulario = new JPanel(new GridLayout(4, 2, 10, 10));
        pnlFormulario.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // 1. Monto PC (Bloqueado)
        pnlFormulario.add(new JLabel("Monto por PC:"));
        txtMontoPC = new JTextField("0.00");
        txtMontoPC.setEditable(false); // No editable como pide Trello
        pnlFormulario.add(txtMontoPC);

        // 2. Monto Kiosco (Bloqueado)
        pnlFormulario.add(new JLabel("Monto por Kiosco:"));
        txtMontoKiosco = new JTextField("0.00");
        txtMontoKiosco.setEditable(false); // No editable
        pnlFormulario.add(txtMontoKiosco);

        // 3. Monto Total (Resaltado)
        pnlFormulario.add(new JLabel("TOTAL A PAGAR:"));
        txtMontoTotal = new JTextField("0.00");
        txtMontoTotal.setEditable(false);
        txtMontoTotal.setFont(new Font("Arial", Font.BOLD, 18)); // Texto resaltado y grande
        txtMontoTotal.setForeground(new Color(0, 128, 0)); // Color verde oscuro para el dinero
        pnlFormulario.add(txtMontoTotal);

        // 4. Forma de Pago (Selector JComboBox)
        pnlFormulario.add(new JLabel("Forma de Pago:"));
        String[] opcionesPago = {"Efectivo", "Tarjeta de Débito", "Tarjeta de Crédito", "Transferencia / QR"};
        cbxFormaPago = new JComboBox<>(opcionesPago);
        pnlFormulario.add(cbxFormaPago);

        // --- SECCIÓN CENTRAL: Botones de Acción ---
        JPanel pnlBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        btnConfirmarPago = new JButton("Confirmar Pago");
        btnImprimirTicket = new JButton("Imprimir Ticket");
        btnCancelar = new JButton("Cancelar Operación");

        pnlBotones.add(btnConfirmarPago);
        pnlBotones.add(btnImprimirTicket);
        pnlBotones.add(btnCancelar);

        // Unimos el formulario y los botones en un panel contenedor para el norte/centro
        JPanel pnlNorteContainer = new JPanel(new BorderLayout());
        pnlNorteContainer.add(pnlFormulario, BorderLayout.CENTER);
        pnlNorteContainer.add(pnlBotones, BorderLayout.SOUTH);

        add(pnlNorteContainer, BorderLayout.NORTH);

        // --- SECCIÓN INFERIOR: Historial (JTable) ---
        String[] columnas = {"ID Ticket", "ID Sesión", "Monto PC", "Monto Kiosco", "Total", "Forma Pago", "Fecha"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Evita que el empleado edite el historial haciendo doble clic
            }
        };
        
        tblHistorialCobros = new JTable(modeloTabla);
        JScrollPane scrollTabla = new JScrollPane(tblHistorialCobros);
        scrollTabla.setBorder(BorderFactory.createTitledBorder("Historial de los últimos cobros del día"));

        add(scrollTabla, BorderLayout.CENTER);
    }

    // Método para conectar la tabla con el código del CobroDAO que hicimos antes
    public void cargarHistorial() {
        modeloTabla.setRowCount(0); // Vacía la tabla por si tenía datos viejos
        List<Cobro> historial = cobroDAO.listar(); //Llama al DAO
        
        // Recorre la lista de la base de datos y mete las filas en la JTable
        for (Cobro c : historial) {
            Object[] fila = {
                c.getIdTicket(),
                c.getIdSesion(),
                c.getMontoSesion(),
                c.getMontoProductos(),
                c.getMontoTotal(),
                c.getFormaPago(),
                c.getFechaPago()
            };
            modeloTabla.addRow(fila); // Agrega la fila visualmente
        }
    }
    
    //AGREGAR ESTO AL FINAL DE VISTACOBROS.JAVA:
    public javax.swing.JTextField getTxtMontoPC() { return txtMontoPC; }
    public javax.swing.JTextField getTxtMontoKiosco() { return txtMontoKiosco; }
    public javax.swing.JTextField getTxtMontoTotal() { return txtMontoTotal; }
    public javax.swing.JComboBox<String> getCbxFormaPago() { return cbxFormaPago; }
    public javax.swing.JButton getBtnConfirmarPago() { return btnConfirmarPago; }
    public javax.swing.JButton getBtnCancelar() { return btnCancelar; }
}