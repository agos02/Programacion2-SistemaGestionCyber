package com.cyber.dao;

import com.cyber.modelos.Producto;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.cyber.conexion.ConexionBD;

public class ProductoDAO {

    Connection conexion;

    public ProductoDAO() {
           
        //  conexión ya creada
   
        conexion = ConexionBD.getconectar();
    }

    //  INSERTAR
    public void insertar(Producto p) {

        String sql = "INSERT INTO productos(nombre, precio, stock) VALUES (?, ?, ?)";

        try {
            PreparedStatement ps = conexion.prepareStatement(sql);

            ps.setString(1, p.getNombre());
            ps.setDouble(2, p.getPrecio());
            ps.setInt(3, p.getStock());

            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error insertar: " + e.getMessage());
        }
    }

    //  LISTAR (para JTable)
    public List<Producto> listar() {

        List<Producto> lista = new ArrayList<>();

        String sql = "SELECT * FROM productos";

        try {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Producto p = new Producto();

                p.setId(rs.getInt("id"));
                p.setNombre(rs.getString("nombre"));
                p.setPrecio(rs.getDouble("precio"));
                p.setStock(rs.getInt("stock"));

                lista.add(p);
            }

        } catch (Exception e) {
            System.out.println("Error listar: " + e.getMessage());
        }

        return lista;
    }

    //  BUSCAR POR ID
    public Producto buscarPorId(int id) {

        Producto p = null;

        String sql = "SELECT * FROM productos WHERE id = ?";

        try {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                p = new Producto();

                p.setId(rs.getInt("id"));
                p.setNombre(rs.getString("nombre"));
                p.setPrecio(rs.getDouble("precio"));
                p.setStock(rs.getInt("stock"));
            }

        } catch (Exception e) {
            System.out.println("Error buscar: " + e.getMessage());
        }

        return p;
    }

    //  ELIMINAR
    public void eliminar(int id) {

        String sql = "DELETE FROM productos WHERE id = ?";

        try {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setInt(1, id);

            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error eliminar: " + e.getMessage());
        }
    }

    //  MODIFICAR
    public void modificar(Producto p) {

        String sql = "UPDATE productos SET nombre=?, precio=?, stock=? WHERE id=?";

        try {
            PreparedStatement ps = conexion.prepareStatement(sql);

            ps.setString(1, p.getNombre());
            ps.setDouble(2, p.getPrecio());
            ps.setInt(3, p.getStock());
            ps.setInt(4, p.getId());

            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error modificar: " + e.getMessage());
        }
    }
}