
package com.cyber.modelos;

import java.sql.Timestamp;

public class Cobro {
    
    //Atributos.
    private int idTicket;
    private int idSesion;
    private double montoSesion;
    private double montoProductos;
    private double montoTotal;
    private String formaPago;
    private Timestamp fechaPago;

    //Constructor Vacío.
    public Cobro() {
    }

    //Constructor Completo con todos los parámetros
    public Cobro(int idTicket, int idSesion, double montoSesion, double montoProductos, double montoTotal, String formaPago, Timestamp fechaPago) {
        this.idTicket = idTicket;
        this.idSesion = idSesion;
        this.montoSesion = montoSesion;
        this.montoProductos = montoProductos;
        this.montoTotal = montoTotal;
        this.formaPago = formaPago;
        this.fechaPago = fechaPago;
    }

    //Getters y Setters (para que el DAO y la Vista puedan acceder a los datos)
    public int getIdTicket() {return idTicket;}

    public void setIdTicket(int idTicket) {this.idTicket = idTicket;}

    public int getIdSesion() {return idSesion;}

    public void setIdSesion(int idSesion) {this.idSesion = idSesion;}

    public double getMontoSesion() {return montoSesion;}

    public void setMontoSesion(double montoSesion) {this.montoSesion = montoSesion;}

    public double getMontoProductos() {return montoProductos;}

    public void setMontoProductos(double montoProductos) {this.montoProductos = montoProductos;}

    public double getMontoTotal() {return montoTotal;}

    public void setMontoTotal(double montoTotal) {this.montoTotal = montoTotal;}

    public String getFormaPago() {return formaPago;}

    public void setFormaPago(String formaPago) {this.formaPago = formaPago;}

    public Timestamp getFechaPago() {return fechaPago;}

    public void setFechaPago(Timestamp fechaPago) {this.fechaPago = fechaPago;}
}