package com.cyber.controladores;

import com.cyber.dao.ReportesDAO;

public class ReportesControlador {

    private ReportesDAO dao = new ReportesDAO();

    public void listarClientes() {
        dao.listarClientes();
    }

    public void listarSesiones() {
        dao.listarSesiones();
    }

    public void clienteMasSesiones() {
        dao.clienteMasSesiones();
    }

    public void obtenerComputadoras() {
        dao.obtenerComputadoras();
    }

    public void estadoComputadoras() {
        dao.estadoComputadoras();
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
     
    public void productosPocoStock () {
        
    }
    
    
    
    
} 
