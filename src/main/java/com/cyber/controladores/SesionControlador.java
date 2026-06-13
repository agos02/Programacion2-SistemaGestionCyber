package com.cyber.controladores;

import com.cyber.dao.ComputadoraDAO;
import com.cyber.dao.SesionDAO;
import com.cyber.modelos.Sesion;
import com.cyber.vistas.CobroVista;
import java.time.Duration;
import java.time.LocalDateTime;

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

    public void iniciarSesion(int idCliente, int numero_pc)
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
    }
    
    public void finalizarSesion(int idSesion)
    {
        this.sesionDAO.finalizarSesion(idSesion);
        Sesion sesion = sesionDAO.buscarPorId(idSesion);

        if (sesion == null)
        {
            System.out.println("No existe la sesión");
            return;
        }

        LocalDateTime ahora = LocalDateTime.now();
        
        sesion.setFechaFin(ahora);
                
        Duration duracion = Duration.between(sesion.getFechaInicio(),ahora);

        long minutos = duracion.toMinutes();

        double tarifaHora = 1000;

        double montoSesion = (minutos / 60.0) * tarifaHora;
        
        double montoProductos = sesionDAO.obtenerMontoProductos(idSesion);

        double total = montoSesion + montoProductos;

        System.out.println("Tiempo utilizado: " + minutos + " minutos");

        System.out.println("Monto a cobrar: $" + montoSesion);
        
        System.out.println("Monto productos: $" + montoProductos);

        System.out.println("TOTAL A COBRAR: $" + total);
        
        // Abrir ventana de cobro
        /*CobroVista cobroVista = new CobroVista(idSesion, montoSesion, montoProductos);

        cobroVista.setLocationRelativeTo(null);
        cobroVista.setVisible(true);*/
    }
    
    public int obtenerSesionActivaPorPc(int numeroPc)
    {
        return this.sesionDAO.obtenerSesionActivaPorPc(numeroPc);
    }
}
