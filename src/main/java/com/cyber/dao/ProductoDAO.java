
package com.cyber.dao;

import java.sql.*;
import java.util.ArrayList;
import com.cyber.conexion.ConexionBD;
import com.cyber.modelos.Producto;

public class ProductoDAO {

    private final Connection conexion = ConexionBD.conectar();

    // INSERTAR
    public void insertar(Producto p) {

        String sql = "INSERT INTO productos(nombre, precio, stock) VALUES (?, ?, ?)";

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, p.getNombre());
            ps.setDouble(2, p.getPrecio());
            ps.setInt(3, p.getStock());

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // LISTAR
    public ArrayList<Producto> listarProducto() {

        ArrayList<Producto> lista = new ArrayList<>();

        String sql = "SELECT * FROM productos";

        try (PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Producto p = new Producto();

                p.setId_producto(rs.getInt("id_producto"));
                p.setNombre(rs.getString("nombre"));
                p.setPrecio(rs.getDouble("precio"));
                p.setStock(rs.getInt("stock"));

                lista.add(p);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    // BUSCAR
    public Producto buscarPorId(int id) {

        Producto p = null;

        String sql = "SELECT * FROM productos WHERE id_producto = ?";

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                p = new Producto();
                p.setId_producto(rs.getInt("id_producto"));
                p.setNombre(rs.getString("nombre"));
                p.setPrecio(rs.getDouble("precio"));
                p.setStock(rs.getInt("stock"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return p;
    }

    // ELIMINAR
    public void eliminar(int id) {

        String sql = "DELETE FROM productos WHERE id_producto = ?";

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // MODIFICAR
    public void modificar(Producto p) {

        String sql = "UPDATE productos SET nombre=?, precio=?, stock=? WHERE id_producto=?";

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, p.getNombre());
            ps.setDouble(2, p.getPrecio());
            ps.setInt(3, p.getStock());
            ps.setInt(4, p.getId_producto());

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // descontar stock
    public boolean descontarStock(int idProducto, int cantidad) {
    String sql = "UPDATE productos SET stock = stock - ? WHERE id_producto = ? AND stock >= ?";

    try ( PreparedStatement ps = conexion.prepareStatement(sql)) {

        ps.setInt(1, cantidad);
        ps.setInt(2, idProducto);
        ps.setInt(3, cantidad);

        int filas = ps.executeUpdate();

        return filas > 0;

    } catch (SQLException e) {
        System.out.println("Error al descontar stock");
        e.printStackTrace();
        return false;
    }
    }
}