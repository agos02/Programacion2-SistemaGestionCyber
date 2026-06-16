package com.cyber.modelos;

import java.time.LocalDateTime;

public class Sesion
{
    private int idSesion;
    private int idCliente;
    private int idComputadora;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
    private String estado;

    public Sesion() {/*sin parametros*/
    }

    public Sesion(int idSesion, int idCliente, int idComputadora, LocalDateTime fechaInicio, LocalDateTime fechaFin, String estado)
    {
        this.idSesion = idSesion;
        this.idCliente = idCliente;
        this.idComputadora = idComputadora;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.estado = estado;
    }

    public int getIdSesion(){
        return this.idSesion;
    }

    public void setIdSesion(int idSesion){
        this.idSesion = idSesion;
    }

    public int getIdCliente(){
        return this.idCliente;
    }

    public void setIdCliente(int idCliente){
        this.idCliente = idCliente;
    }

    public int getIdComputadora() {
        return this.idComputadora;
    }

    public void setIdComputadora(int numeroPc){
        this.idComputadora = numeroPc;
    }

    public LocalDateTime getFechaInicio(){
        return this.fechaInicio;
    }

    public void setFechaInicio(LocalDateTime fechaInicio){
        this.fechaInicio = fechaInicio;
    }

    public LocalDateTime getFechaFin(){
        return this.fechaFin;
    }

    public void setFechaFin(LocalDateTime fechaFin){
        this.fechaFin = fechaFin;
    }

    public String getEstado(){
        return this.estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
