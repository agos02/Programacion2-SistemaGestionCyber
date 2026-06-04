package com.cyber.controladores;

import com.cyber.dao.ReportesDAO;

public class ReportesControlador {

    private ReportesDAO dao = new ReportesDAO();

    
    //clientes
    public void listarClientes() {
        dao.listarClientes();
    }
    
     public int cantidadClientes() {
         return dao.cantidadClientes();
     }
    
    //sesiones
    public void listarSesiones() {
        dao.listarSesiones();
    }
    
    public void cantidadSesiones() {
        dao.cantidadSesiones();
    }
    
    public void clienteMasSesiones() {
        dao.clienteMasSesiones();
    }

    //computadoras
    public void obtenerComputadoras() {
        dao.obtenerComputadoras();
    }

    public void estadoComputadoras() {
        dao.estadoComputadoras();
    }
    
    public void computadorasDisponibles() {
        dao.computadorasDisponibles();
    }
    
    public void cantidadPorEstado() {
        dao.cantidadPorEstado();
    }
    
    //Cobros
    public void obtenerCobros() {
        dao.obtenerCobros();
    }
    
    public void ingresosTotales() {
        dao.ingresosTotales();
    }
    
    public void ingresosFormaPago() {
        dao.ingresosFormaPago();
    }
    
    //Productos
    public void obtenerProducto() {
        dao.obtenerProductos();
    }
    
    public void productoPrecio() {
        dao.productoPrecio();
    }
     
    public void MasVendido() {
        dao.masVendido();
    }
     
    public void pocoStock () {
        dao.pocoStock();
    }
    
    public void productoMasCaro() {
        dao.productoMasCaro();
    }
    
    public void productoMasBarato() {
        dao.productoMasBarato();
    }
    
    public void valorTotalStock() {
        dao.valorTotalStock();
    }
    
    
    
} 
