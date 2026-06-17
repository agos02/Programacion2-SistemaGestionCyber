package com.cyber.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.cyber.conexion.ConexionBD;
import com.cyber.modelos.Cliente;

/**
 * DAO encargado de ejecutar las operaciones SQL sobre la tabla clientes.
 * Contiene métodos CRUD (Create, Read, Update, Delete).
 */
public class ClienteDAO {

    /**
     * Insertar un nuevo cliente
     */
    public boolean guardarCliente(Cliente cliente) {
        String sql = """
                INSERT INTO clientes(nombre,dni,telefono)
                VALUES(?,?,?)
                """;

        try (Connection conexion = ConexionBD.conectar();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getDni());
            ps.setString(3, cliente.getTelefono());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al guardar cliente");
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Listar todos los clientes
     */
    public List<Cliente> listarClientes() {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM clientes ORDER BY id_cliente";

        try (Connection conexion = ConexionBD.conectar();
             PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setIdCliente(rs.getInt("id_cliente"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setDni(rs.getString("dni"));
                cliente.setTelefono(rs.getString("telefono"));
                clientes.add(cliente);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar clientes");
            e.printStackTrace();
        }

        return clientes;
    }

    /**
     * Modificar un cliente existente
     */
    public boolean modificarCliente(Cliente cliente) {
        String sql = """
                UPDATE clientes
                SET nombre = ?,
                    dni = ?,
                    telefono = ?
                WHERE id_cliente = ?
                """;

        try (Connection conexion = ConexionBD.conectar();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getDni());
            ps.setString(3, cliente.getTelefono());
            ps.setInt(4, cliente.getIdCliente());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al modificar cliente");
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Eliminar un cliente por ID
     */
    public boolean eliminarCliente(int idCliente) {
        String sql = """
                DELETE FROM clientes
                WHERE id_cliente = ?
                """;

        try (Connection conexion = ConexionBD.conectar();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setInt(1, idCliente);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al eliminar cliente");
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Buscar clientes por DNI
     */
    public List<Cliente> buscarPorDni(String dni) {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM clientes WHERE dni LIKE ?";

        try (Connection conexion = ConexionBD.conectar();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, "%" + dni + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setIdCliente(rs.getInt("id_cliente"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setDni(rs.getString("dni"));
                cliente.setTelefono(rs.getString("telefono"));
                clientes.add(cliente);
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar cliente");
            e.printStackTrace();
        }

        return clientes;
    }
    
    /**
    * Verifica si ya existe un cliente con el DNI indicado.
    * Retorna true si el DNI se encuentra registrado y false en caso contrario.
    */
    public boolean existeDni(String dni) {
        String sql = "SELECT dni FROM clientes WHERE dni = ?";

        try (Connection conexion = ConexionBD.conectar();
            PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, dni);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
        }

        } catch (SQLException e) {
            System.out.println("Error al verificar DNI: " + e.getMessage());
        return false;
        }
    }

}
