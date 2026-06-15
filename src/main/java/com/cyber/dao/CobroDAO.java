package com.cyber.dao;

import com.cyber.conexion.ConexionBD;
import com.cyber.modelos.Cobro;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CobroDAO {

    //REGISTRAR EL COBRO COMPLETO (Ticket + Productos vendidos)
    public boolean guardar(Cobro c, List<Object[]> productos) {
        String sqlTicket = "INSERT INTO cobros (id_sesion, monto_sesion, monto_productos, monto_total, forma_pago, fecha_pago) VALUES (?, ?, ?, ?, ?, ?)";
        String sqlDetalle = "INSERT INTO detalle_cobros (id_ticket, id_producto, cantidad) VALUES (?, ?, ?)";
        
        Connection con = null;
        try {
            con = ConexionBD.conectar();
            //VALIDACIÓN DE SEGURIDAD: Si no hay conexión, frena acá
            if (con == null) {
                System.out.println("No se pudo realizar el cobro porque la base de datos está inaccesible.");
                return false; // Retorna falso limpiamente para que el controlador muestre el cartel de error
            }
            con.setAutoCommit(false); //Pausa el guardado automático (Inicia Transacción)

            int idTicket = -1;
            
            //Guardamos el ticket principal y pedimos el ID generado
            try (PreparedStatement ps1 = con.prepareStatement(sqlTicket, Statement.RETURN_GENERATED_KEYS)) {
                ps1.setInt(1, c.getIdSesion());
                ps1.setDouble(2, c.getMontoSesion());
                ps1.setDouble(3, c.getMontoProductos());
                ps1.setDouble(4, c.getMontoTotal());
                ps1.setString(5, c.getFormaPago());
                ps1.setTimestamp(6, c.getFechaPago());
                
                ps1.executeUpdate();
                
                //Leemos el ID que creó MySQL
                try (ResultSet rs = ps1.getGeneratedKeys()) {
                    if (rs.next()) {
                        idTicket = rs.getInt(1); 
                    }
                }
            }

            //Si obtuvimos el ID, guardamos la lista de productos comprados
            if (idTicket != -1 && productos != null) {
                try (PreparedStatement ps2 = con.prepareStatement(sqlDetalle)) {
                    for (Object[] p : productos) {
                        // p[0] = id_producto, p[1] = cantidad
                        ps2.setInt(1, idTicket); //el ID generado en cobros
                        ps2.setInt(2, (int) p[0]); //id_producto
                        ps2.setInt(3, (int) p[1]); //cantidad
                        ps2.addBatch(); //Acumula el producto en la lista
                    }
                    ps2.executeBatch(); //Guarda todos los productos juntos en un viaje
                }
            }

            con.commit(); //Todo salió bien, se guarda TODO definitivamente
            return true;

        } catch (SQLException e) {
            if (con != null) {
                try { con.rollback(); } catch (SQLException err) { err.printStackTrace(); } //Cancela todo si algo falló
            }
            System.out.println("Error al guardar el cobro.");
            e.printStackTrace();
            return false;
        } finally {
            if (con != null) {
                try { con.close(); } catch (SQLException e) { e.printStackTrace(); } //Cierra la conexión siempre
            }
        }
    }

    //OBTENER EL HISTORIAL (Para cargar la tabla)
    public List<Cobro> listar() {
        List<Cobro> lista = new ArrayList<>();
        String sql = "SELECT id_ticket, id_sesion, monto_sesion, monto_productos, monto_total, forma_pago, fecha_pago FROM cobros ORDER BY id_ticket DESC";
    
        Connection con = ConexionBD.conectar(); // Obtenemos la conexión primero
    
        // Validamos si la conexión falló, para cortar el método limpiamente
        if (con == null) {
            System.out.println("No se pudo listar el historial porque no hay conexión a la BD.");
            return lista; // Devuelve la lista vacía en lugar de romper el programa
        }

        //Si hay conexión, procedemos con el bloque try-with-resources seguro
        try (PreparedStatement ps = con.prepareStatement(sql);
           ResultSet rs = ps.executeQuery()) {
        
            while (rs.next()) {
                Cobro c = new Cobro();
                c.setIdTicket(rs.getInt("id_ticket"));
                c.setIdSesion(rs.getInt("id_sesion"));
                c.setMontoSesion(rs.getDouble("monto_sesion"));
                c.setMontoProductos(rs.getDouble("monto_productos"));
                c.setMontoTotal(rs.getDouble("monto_total"));
                c.setFormaPago(rs.getString("forma_pago"));
                c.setFechaPago(rs.getTimestamp("fecha_pago"));
            
                lista.add(c);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar cobros.");
            e.printStackTrace();
            } finally {
            ConexionBD.cerrar(con); // Nos aseguramos de cerrarla siempre
        }
        return lista;
    }

    //ELIMINAR UN COBRO POR ID
    public boolean eliminar(int id) {
        String sqlDetalle = "DELETE FROM detalle_cobros WHERE id_ticket = ?";
        String sqlTicket = "DELETE FROM cobros WHERE id_ticket = ?";
        
        Connection con = null;
        try {
            con = ConexionBD.conectar();
            con.setAutoCommit(false); //Inicia Transacción

            //Primero borramos los detalles (hijos)
            try (PreparedStatement ps1 = con.prepareStatement(sqlDetalle)) {
                ps1.setInt(1, id);
                ps1.executeUpdate();
            }

            //Después borramos el ticket principal (padre)
            int filas = 0;
            try (PreparedStatement ps2 = con.prepareStatement(sqlTicket)) {
                ps2.setInt(1, id);
                filas = ps2.executeUpdate();
            }

            con.commit(); //Confirma la eliminación total
            return filas > 0;

        } catch (SQLException e) {
            if (con != null) {
                try { con.rollback(); } catch (SQLException err) { err.printStackTrace(); } //Cancela si falla
            }
            System.out.println("Error al eliminar.");
            e.printStackTrace();
            return false;
        } finally {
            if (con != null) {
                try { con.close(); } catch (SQLException e) { e.printStackTrace(); }
            }
        }
    }
}