package com.cyber.modelos;

/**
 * Clase modelo que representa la tabla 'clientes' en código.
 * Contiene atributos, constructores, getters y setters.
 */
public class Cliente {

    // Atributos
    private int idCliente;
    private String nombre;
    private String dni;
    private String telefono;

    // Constructor vacío
    public Cliente() {}

    // Constructor con parámetros
    public Cliente(int idCliente, String nombre, String dni, String telefono) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.dni = dni;
        this.telefono = telefono;
    }

    // Getters
    public int getIdCliente() {
        return idCliente; 
    }
    public String getNombre() {
        return nombre; 
    }
    public String getDni() {
        return dni; 
    }
    public String getTelefono() { return telefono; }

    // Setters
    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente; 
    }
    public void setNombre(String nombre) { 
        this.nombre = nombre; 
    }
    public void setDni(String dni) { 
        this.dni = dni; 
    }
    public void setTelefono(String telefono) { 
        this.telefono = telefono; 
    }
}
