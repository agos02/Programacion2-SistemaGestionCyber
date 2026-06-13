package com.cyber.controladores;

import java.util.List;
import com.cyber.dao.ClienteDAO;
import com.cyber.modelos.Cliente;

/**
 * Controlador que actúa como puente entre la Vista y el DAO.
 * Recibe datos de la Vista, los transforma en objetos Cliente
 * y delega las operaciones al DAO.
 */
public class ClienteControlador {

    // DAO encargado de la comunicación con la BD
    private ClienteDAO clienteDAO;

    // Constructor: inicializa el DAO
    public ClienteControlador() {
        clienteDAO = new ClienteDAO();
    }

    /**
     * Guardar un nuevo cliente en la BD
     */
    public boolean guardarCliente(String nombre, String dni, String telefono) {
        Cliente cliente = new Cliente();
        cliente.setNombre(nombre);
        cliente.setDni(dni);
        cliente.setTelefono(telefono);

        return clienteDAO.guardarCliente(cliente);
    }

    /**
     * Listar todos los clientes
     */
    public List<Cliente> listarClientes() {
        return clienteDAO.listarClientes();
    }

    /**
     * Modificar un cliente existente
     */
    public boolean modificarCliente(int idCliente, String nombre, String dni, String telefono) {
        Cliente cliente = new Cliente();
        cliente.setIdCliente(idCliente);
        cliente.setNombre(nombre);
        cliente.setDni(dni);
        cliente.setTelefono(telefono);

        return clienteDAO.modificarCliente(cliente);
    }

    /**
     * Eliminar un cliente por ID
     */
    public boolean eliminarCliente(int idCliente) {
        return clienteDAO.eliminarCliente(idCliente);
    }

    /**
     * Buscar clientes por DNI
     */
    public List<Cliente> buscarPorDni(String dni) {
        return clienteDAO.buscarPorDni(dni);
    }
}
