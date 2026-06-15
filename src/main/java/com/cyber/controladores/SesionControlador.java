package com.cyber.controladores;

import com.cyber.dao.ComputadoraDAO;
import com.cyber.dao.SesionDAO;
import com.cyber.modelos.Sesion;
import com.cyber.vistas.CobroVista;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class SesionControlador{

    private SesionDAO sesionDAO;
    private ComputadoraDAO computadoraDAO;
    
    public SesionControlador() {
        this.sesionDAO = new SesionDAO();
        this.computadoraDAO = new ComputadoraDAO();
    }

    public void crearSesion(Sesion sesion){
        this.sesionDAO.crearSesion(sesion);
    }

    public void iniciarSesion(int idCliente, int numero_pc, JTable tabla)
    {
        if (idCliente <= 0) {
            System.out.println("Debe seleccionar un cliente");
            return;
        }

        if (numero_pc <= 0) {
            System.out.println("Debe seleccionar una computadora");
            return;
        }

        Sesion sesion = new Sesion();

        sesion.setIdCliente(idCliente);
        sesion.setIdComputadora(numero_pc);
        sesion.setFechaInicio(LocalDateTime.now());
        sesion.setEstado("Activa");
        
        this.sesionDAO.crearSesion(sesion);
        actualizarTabla(tabla);
    }
    
    public void finalizarSesion(JTable tabla, JComboBox combo1, JComboBox combo2)
    {
        int id_sesiones;
        int fila = tabla.getSelectedRow();

        if (fila == -1)
        {
            System.out.println("No se selecciono ninguna fila");
            return;
        }
        id_sesiones = Integer.parseInt(tabla.getValueAt(fila, 0).toString()); // Obtener la ID de la sesión seleecionada de la tabla
        this.sesionDAO.finalizarSesion(id_sesiones); // Finalizar esa sesión elegida en la BD
        Sesion sesion = this.sesionDAO.buscarPorId(id_sesiones);
        
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel(); // Obtener el modelo de la tabla actual
        modelo.removeRow(fila); // Eliminar la fila de la tabla

        combo1.removeAllItems();
        for (int i = 0; i < cargarClientes().size(); i++)
        {
            combo1.addItem(cargarClientes().get(i));
        }
        
        combo2.removeAllItems(); 
        for (int i = 0; i < cargarPCDisponibles().size(); i++)
        {
            combo2.addItem("" + cargarPCDisponibles().get(i));
        }
        if (sesion == null)
        {
            return;
        }
        LocalDateTime ahora = LocalDateTime.now();
        
        sesion.setFechaFin(ahora);
                
        Duration duracion = Duration.between(sesion.getFechaInicio(), ahora);

        long minutos = duracion.toMinutes();

        double tarifaHora = 1000;

        double montoSesion = (minutos / 60.0) * tarifaHora;
        
        double montoProductos = this.sesionDAO.obtenerMontoProductos(id_sesiones);

        double total = montoSesion + montoProductos;

        System.out.println("Tiempo utilizado: " + minutos + " minutos");

        System.out.println("Monto a cobrar: $" + montoSesion);
        
        System.out.println("Monto productos: $" + montoProductos);

        System.out.println("TOTAL A COBRAR: $" + total);
        
        // Abrir ventana de cobro
        CobroVista cobroVista = new CobroVista(id_sesiones, montoSesion, montoProductos);

        cobroVista.setLocationRelativeTo(null);
        cobroVista.setVisible(true);
    }
    
    public int obtenerSesionActivaPorPc(int numeroPc)
    {
        return this.sesionDAO.obtenerSesionActivaPorPc(numeroPc);
    }
    
    public void actualizarTabla(JTable tabla)
    {
        tabla.setModel(this.sesionDAO.obtenerDatosTabla());
    }
    
    public ArrayList<String> cargarClientes()
    {
        return this.sesionDAO.cargarClientes();
    }
    
    public ArrayList<Integer> cargarPCDisponibles()
    {
        return this.sesionDAO.cargarPcDisponibles();
    }
    
    public int obtenerIDPorNombre(String nombre)
    {
        return this.sesionDAO.obtenerIDClientePorNombre(nombre);
    }
}
