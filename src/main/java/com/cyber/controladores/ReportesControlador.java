package com.cyber.controladores;

import com.cyber.dao.ReportesDAO;
import java.sql.ResultSet;

public class ReportesControlador {

    private ReportesDAO dao = new ReportesDAO();

    
    //clientes
    public ResultSet listarClientes() {
        return dao.listarClientes();
    }
    
     public int cantidadClientes() {
         return dao.cantidadClientes();
     }
     
     public ResultSet top3Clientes(){
         return dao.top3Clientes();
     }
        
    
    //sesiones
    public ResultSet listarSesiones() {
        return dao.listarSesiones();
    }
    
    public int cantidadSesiones() {
        return dao.cantidadSesiones();
    }
    
    //computadoras
    public ResultSet obtenerComputadoras() {
        return dao.obtenerComputadoras();
    }

    public ResultSet estadoComputadoras() {
        return dao.estadoComputadoras();
    }
    
    public ResultSet computadorasDisponibles() {
        return dao.computadorasDisponibles();
    }
    
    public ResultSet cantidadPorEstado() {
        return dao.cantidadPorEstado();
    }
    
    //Cobros
    public ResultSet obtenerCobros() {
        return dao.obtenerCobros();
    }
    
    public double ingresosTotales() {
        return dao.ingresosTotales();
    }
    
    public ResultSet ingresosFormaPago() {
        return dao.ingresosFormaPago();
    }
    
    //Productos
    public ResultSet obtenerProducto() {
        return dao.obtenerProductos();
    }
    
    public ResultSet productoPrecio() {
        return dao.productoPrecio();
    }
     
    public ResultSet MasVendido() {
        return dao.masVendido();
    }
     
    public ResultSet pocoStock () {
        return dao.pocoStock();
    }
    
    public ResultSet productoMasCaro() {
        return dao.productoMasCaro();
    }
    
    public ResultSet productoMasBarato() {
        return dao.productoMasBarato();
    }
    
    public double valorTotalStock() {
        return dao.valorTotalStock();
    }
    
    
    
} 
