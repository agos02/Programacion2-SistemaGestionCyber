package com.cyber.controladores;

import com.cyber.dao.CobroDAO;
import com.cyber.modelos.Cobro;
import com.cyber.vistas.CobroVista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.util.List;
import javax.swing.JOptionPane;
import com.cyber.dao.ProductoDAO;
import com.cyber.dao.ComputadoraDAO;

public class CobroControlador {
    //Atributos (Las herramientas que el controlador necesita manejar)
    private CobroVista  vista;
    private CobroDAO dao;
    
    //Instanciamos los DAOs externos para poder usarlos.
    private ProductoDAO productoDAO = new ProductoDAO();
    private ComputadoraDAO computadoraDAO = new ComputadoraDAO();
 
    //Datos que tienen que venir sí o sí de la sesión que se va a cobrar
    private int idSesion;
    private double montoPC;
    private double montoKiosco;
    private List<Object[]> carrito; // Lista de productos [id_producto, cantidad]
    
    public CobroControlador(CobroVista vista, int idSesion, double montoPC, double montoKiosco, List<Object[]> carrito) {
        this.vista = vista;
        this.dao = new CobroDAO();
        this.idSesion = idSesion;
        this.montoPC = montoPC;
        this.montoKiosco = montoKiosco;
        this.carrito = carrito;

        // Ejecutar los procesos iniciales
        cargarMontosEnPantalla();
        configurarBotones();
    }
    
    //Suma automática y envío de montos a la Vista
    private void cargarMontosEnPantalla() {
        double total = this.montoPC + this.montoKiosco; // Suma automática
        
        // Mandamos los textos a las cajas de la vista
        vista.getTxtMontoPC().setText(String.valueOf(this.montoPC));
        vista.getTxtMontoKiosco().setText(String.valueOf(this.montoKiosco));
        vista.getTxtMontoTotal().setText(String.valueOf(total));
    }
    
    //Escuchar los clics de la interfaz
    private void configurarBotones() {
        // Escucha el botón "Confirmar Pago"
        vista.getBtnConfirmarPago().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                procesarPago();
            }
        });
        
        //Escucha el botón "Cancelar"
        vista.getBtnCancelar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vista.dispose(); // Cierra la ventana limpiamente
            }
        });
    }
    
    //La lógica principal al confirmar el pago
    private void procesarPago() {
        //Capturar la forma de pago seleccionada por el empleado en el JComboBox
        String formaPago = vista.getCbxFormaPago().getSelectedItem().toString();
        double total = this.montoPC + this.montoKiosco;

        //Crear el objeto Cobro con todos los datos recolectados
        Cobro c = new Cobro();
        c.setIdSesion(this.idSesion);
        c.setMontoSesion(this.montoPC);
        c.setMontoProductos(this.montoKiosco);
        c.setMontoTotal(total);
        c.setFormaPago(formaPago);
        c.setFechaPago(new Timestamp(System.currentTimeMillis())); // Hora y fecha actual

        //Llamar al CobroDAO para guardar en base de datos (Ticket + Tabla intermedia)
        boolean guardadoExitoso = dao.guardar(c, this.carrito);

        if (guardadoExitoso) {
            //Señales para descontar stock y liberar la computadora
            descontarStock();
            //liberarComputadora();

            //Mensaje de éxito, refrescar historial y cerrar ventana limpio
            JOptionPane.showMessageDialog(vista, "¡Cobro registrado con éxito!");
            vista.cargarHistorial(); // Refresca el JTable con los nuevos datos
        } else {
            JOptionPane.showMessageDialog(vista, "Hubo un error al procesar el pago.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    // Métodos secundarios para las señales a los otros DAOs de tus compañeros
   private void descontarStock() {
        System.out.println("Señal enviada a ProductoDAO: Descontando stock de los productos vendidos.");
        
        // Recorremos el carrito que llegó desde la pantalla anterior
        for (Object[] p : this.carrito) {
            int idProducto = (int) p[0]; // Extrae el ID del producto
            int cantidad = (int) p[1];   // Extrae la cantidad vendida
            
            //Llamamos al método del compañero de productos
            productoDAO.descontarStock(idProducto, cantidad);
        }
    }

    private void liberarComputadora() {
        System.out.println("Señal enviada a ComputadoraDAO: Liberando la PC asociada a la sesión " + this.idSesion);
        
        // Llamamos al método de la compañera de computadoras pasándole el id de la sesión actual
        computadoraDAO.cambiarEstadoALibre(this.idSesion);
    }

}

