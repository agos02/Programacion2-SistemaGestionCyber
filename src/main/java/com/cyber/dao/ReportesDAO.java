package com.cyber.dao;

import com.cyber.conexion.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ReportesDAO {

    //clientes
    public void listarClientes() {

        String sql = "SELECT * FROM clientes";

    }
    
    public int cantidadClientes() {

        int cantidad = 0;

        try {
            Connection con = ConexionBD.conectar();

            String sql =
                "SELECT COUNT(*) AS cantidad_clientes " +
                "FROM clientes";

            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                cantidad = rs.getInt("cantidad_clientes");
            }

            ConexionBD.cerrar(con);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return cantidad;
    }

    //sesiones
    public void listarSesiones() {

        String sql = 
            "SELECT * FROM sesiones";

    }
    
    public void cantidadSesiones(){
        String sql = 
           "SELECT COUNT(*) AS cantidad_sesiones " +
           "FROM sesiones;";
    }

    public void clienteMasSesiones() {

        String sql =
            "SELECT c.id, c.nombre, " +
            "COUNT(s.id) AS cantidad_sesiones " +
            "FROM clientes c " +
            "INNER JOIN sesiones s ON c.id = s.id_cliente " +
            "GROUP BY c.id, c.nombre " +
            "ORDER BY cantidad_sesiones DESC " +
            "LIMIT 3";

    }

    //computadoras
    public void obtenerComputadoras() {

        String sql = "SELECT * FROM computadoras";

    }

    public void estadoComputadoras() {

        String sql =
            "SELECT numero_pc, estado " +
            "FROM computadoras";

    }
    
    public void computadorasDisponibles() {

        String sql =
            "SELECT * " +
            "FROM computadoras " +
            "WHERE estado = 'Disponible'";
    }
    
    public void cantidadPorEstado() {

        String sql =
            "SELECT estado, COUNT(*) AS cantidad " +
            "FROM computadoras " +
            "GROUP BY estado";
    }
    
    //cobros
    public void obtenerCobros(){
        
        String sql =
            "SELECT * FROM cobros ";
    }
    
    
    public double ingresosTotales() {

    double total = 0;

    try {
        Connection con = ConexionBD.conectar();

        String sql =
            "SELECT SUM(monto_total) AS ingresos_totales " +
            "FROM cobros";

        PreparedStatement ps = con.prepareStatement(sql);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            total = rs.getDouble("ingresos_totales");
        }

        ConexionBD.cerrar(con);

    } catch (Exception e) {
        e.printStackTrace();
    }

    return total;
}
    
    
    
    public void ingresosFormaPago() {
        
        String sql =
            "SELECT forma_pago, " +
            "SUM(monto_total) AS ingresos_totales " +
            "FROM cobros " +
            "GROUP BY forma_pago " +
            "ORDER BY ingresos_totales DESC";
    }
    
    //productos
    public void obtenerProductos() {
        
        String sql =
             "SELECT * From productos";
    }
    
    public void productoPrecio() {
        
        String sql = 
              "SELECT nombre, precio " +
              "FROM productos " +
              "ORDER BY precio ASC"; 
    }
    
    public void masVendido() {

        String sql =
            "SELECT p.nombre, SUM(d.cantidad) AS total_vendido " +
            "FROM productos p " +
            "INNER JOIN detalle_cobros d ON p.id_producto = d.id_producto " +
            "GROUP BY p.id_producto, p.nombre " +
            "ORDER BY total_vendido DESC " +
            "LIMIT 1";
    }
    
    public void pocoStock() {
        
         String sql =
             "SELECT nombre, stock " +
             "FROM productos " +
             "WHERE stock < 5 " +
             "ORDER BY stock ASC ";
    }
    
    public void productoMasCaro() {

        String sql =
            "SELECT nombre, precio " +
            "FROM productos " +
            "ORDER BY precio DESC " +
            "LIMIT 1";
    }
    
    public void productoMasBarato() {

        String sql =
            "SELECT nombre, precio " +
            "FROM productos " +
            "ORDER BY precio ASC " +
            "LIMIT 1";
    }
    
    public double valorTotalStock() {

    double total = 0;

    try {
        Connection con = ConexionBD.conectar();

        String sql =
            "SELECT SUM(stock * precio) AS valor_stock " +
            "FROM productos";

        PreparedStatement ps = con.prepareStatement(sql);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            total = rs.getDouble("valor_stock");
        }

        ConexionBD.cerrar(con);

    } catch (Exception e) {
        e.printStackTrace();
    }

    return total;
}
    
    
    
}