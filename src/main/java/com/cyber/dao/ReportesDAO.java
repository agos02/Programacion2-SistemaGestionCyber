package com.cyber.dao;

import com.cyber.conexion.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ReportesDAO {

    //clientes
    public ResultSet listarClientes() {

    try {
        Connection con = ConexionBD.conectar();

        String sql = "SELECT * FROM clientes";

        PreparedStatement ps = con.prepareStatement(sql);

        return ps.executeQuery();

    } catch (Exception e) {
        e.printStackTrace();
    }

    return null;
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

    public ResultSet top3Clientes () {
        
    try {
        Connection con = ConexionBD.conectar();

        String sql =
        "SELECT c.id_cliente, c.nombre, " +
        "COUNT(s.id_sesiones) AS cantidad_sesiones " +
        "FROM clientes c " +
        "INNER JOIN sesiones s ON c.id_cliente = s.id_cliente " +
        "GROUP BY c.id_cliente, c.nombre " +
        "ORDER BY cantidad_sesiones DESC " +
        "LIMIT 3";


        PreparedStatement ps = con.prepareStatement(sql);

        return ps.executeQuery();

    } catch (Exception e) {
        e.printStackTrace();
    }

    return null;
}
    
    //sesiones
    public ResultSet listarSesiones() {
        try {
        Connection con = ConexionBD.conectar();

        String sql = 
            "SELECT * FROM sesiones";

        PreparedStatement ps = con.prepareStatement(sql);

        return ps.executeQuery();

    } catch (Exception e) {
        e.printStackTrace();
    }

    return null;
}
        
    
    public int cantidadSesiones(){
        
        int cantidad = 0;

        try {
            Connection con = ConexionBD.conectar();

            String sql = 
           "SELECT COUNT(*) AS cantidad_sesiones " +
           "FROM sesiones;";

            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                cantidad = rs.getInt("cantidad_sesiones");
            }

            ConexionBD.cerrar(con);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return cantidad;
    }

    //computadoras
    public ResultSet obtenerComputadoras() {
        
    try {
        Connection con = ConexionBD.conectar();

        String sql = "SELECT * FROM computadoras";

        PreparedStatement ps = con.prepareStatement(sql);

        return ps.executeQuery();

    } catch (Exception e) {
        e.printStackTrace();
    }

    return null;
}
        

    public ResultSet estadoComputadoras() {
 
        try {
        Connection con = ConexionBD.conectar();

        String sql =
            "SELECT numero_pc, estado " +
            "FROM computadoras";

        PreparedStatement ps = con.prepareStatement(sql);

        return ps.executeQuery();

    } catch (Exception e) {
        e.printStackTrace();
    }

    return null;
        

    }
    
    public ResultSet computadorasDisponibles() {

    try {

        Connection con = ConexionBD.conectar();

        String sql =
            "SELECT * " +
            "FROM computadoras " +
            "WHERE estado = 'Libre'";

        PreparedStatement ps = con.prepareStatement(sql);

        return ps.executeQuery();

    } catch (Exception e) {
        e.printStackTrace();
    }

    return null;
}
    
    public ResultSet cantidadPorEstado() {
         try {
        Connection con = ConexionBD.conectar();

         String sql =
            "SELECT estado, COUNT(*) AS cantidad " +
            "FROM computadoras " +
            "GROUP BY estado";

        PreparedStatement ps = con.prepareStatement(sql);

        return ps.executeQuery();

    } catch (Exception e) {
        e.printStackTrace();
    }

    return null;
       
    }
    
    //cobros
    public ResultSet obtenerCobros(){
         try {
        Connection con = ConexionBD.conectar();

        String sql =
            "SELECT * FROM cobros ";
        
        PreparedStatement ps = con.prepareStatement(sql);

        return ps.executeQuery();

    } catch (Exception e) {
        e.printStackTrace();
    }

    return null;
        
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
    
    
    
    public ResultSet ingresosFormaPago() {
         try {
        Connection con = ConexionBD.conectar();

        String sql =
            "SELECT forma_pago, " +
            "SUM(monto_total) AS ingresos_totales " +
            "FROM cobros " +
            "GROUP BY forma_pago " +
            "ORDER BY ingresos_totales DESC";

        PreparedStatement ps = con.prepareStatement(sql);

        return ps.executeQuery();

    } catch (Exception e) {
        e.printStackTrace();
    }

    return null;
        
    }
    
    //productos
    public ResultSet obtenerProductos() {
         try {
        Connection con = ConexionBD.conectar();

        String sql =
             "SELECT * From productos";

        PreparedStatement ps = con.prepareStatement(sql);

        return ps.executeQuery();

    } catch (Exception e) {
        e.printStackTrace();
    }

    return null;
    
    }
    
    public ResultSet productoPrecio() {
         try {
        Connection con = ConexionBD.conectar();

        String sql = 
              "SELECT nombre, precio " +
              "FROM productos " +
              "ORDER BY precio ASC"; 

        PreparedStatement ps = con.prepareStatement(sql);

        return ps.executeQuery();

    } catch (Exception e) {
        e.printStackTrace();
    }

    return null;
               
    }
    
    public ResultSet masVendido() {
         try {
        Connection con = ConexionBD.conectar();

        String sql =
            "SELECT p.nombre, SUM(d.cantidad) AS total_vendido " +
            "FROM productos p " +
            "INNER JOIN detalle_cobros d ON p.id_producto = d.id_producto " +
            "GROUP BY p.id_producto, p.nombre " +
            "ORDER BY total_vendido DESC " +
            "LIMIT 1";

        PreparedStatement ps = con.prepareStatement(sql);

        return ps.executeQuery();

    } catch (Exception e) {
        e.printStackTrace();
    }

    return null;
        
    }
    
    public ResultSet pocoStock() {
         try {
        Connection con = ConexionBD.conectar();

        String sql =
             "SELECT nombre, stock " +
             "FROM productos " +
             "WHERE stock < 5 " +
             "ORDER BY stock ASC ";

        PreparedStatement ps = con.prepareStatement(sql);

        return ps.executeQuery();

    } catch (Exception e) {
        e.printStackTrace();
    }

    return null;
         
    }
    
    public ResultSet productoMasCaro() {
         try {
        Connection con = ConexionBD.conectar();

        String sql =
            "SELECT nombre, precio " +
            "FROM productos " +
            "ORDER BY precio DESC " +
            "LIMIT 1";

        PreparedStatement ps = con.prepareStatement(sql);

        return ps.executeQuery();

    } catch (Exception e) {
        e.printStackTrace();
    }

    return null;
        
    }
    
    public ResultSet productoMasBarato() {
         try {
        Connection con = ConexionBD.conectar();

        String sql =
            "SELECT nombre, precio " +
            "FROM productos " +
            "ORDER BY precio ASC " +
            "LIMIT 1";

        PreparedStatement ps = con.prepareStatement(sql);

        return ps.executeQuery();

    } catch (Exception e) {
        e.printStackTrace();
    }

    return null;
        
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

