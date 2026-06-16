package com.cyber.modelos;

public class Computadora {

    int id_computadora;
    int numero_pc;
    String estado;

    // Empty constructor
    public Computadora() {

    }

    // For new computers
    public Computadora(int numero_pc, String estado) {
        this.numero_pc = numero_pc;
        this.estado = estado;
    }

    // For computers coming from database
    public Computadora(
        int id_computadora,
        int numero_pc,
        String estado
    ) {
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

    public void setEstado(String estado){
        this.estado = estado;
    }
}
