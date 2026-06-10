/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cyber.modelos;

/**
 *
 * @author rosch
 */
public class Computadora {
    int id_computadora;
    int numero_pc;
    String estado;
    
    public Computadora(int id_computadora, int numero_pc, String estado) {
        this.id_computadora = id_computadora;
        this.numero_pc = numero_pc;
        this.estado = estado;
    }
    
    public int getIdComputadora(){
        return this.id_computadora;
    }
    
    public int getNumeroPC(){
        return this.numero_pc;
    }
    
    public String getEstado(){
        return this.estado;
    }
    
    public void setIdComputadora(int id_computadora){
        this.id_computadora = id_computadora;
    }
    
    public void setNumeroPC(int numero_pc){
        this.numero_pc = numero_pc;
    }
    
    public void setEstado(String Esatdo){
        this.estado = estado;
    }
}
