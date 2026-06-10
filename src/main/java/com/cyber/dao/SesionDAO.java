package com.cyber.dao;

import java.util.ArrayList;
import java.util.List;
import com.cyber.modelos.Sesion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.cyber.conexion.ConexionBD;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class SesionDAO {

    public void crearSesion(Sesion sesion)
    {
        int id_computadora = 0;
        String sql = "SELECT id_computadora FROM computadoras WHERE numero_pc = ?";
        String sql2 = "INSERT INTO sesiones (id_computadora, id_cliente, fecha_inicio, estado_sesion) VALUES (?, ?, ?, ?)";
        String sql3 = "UPDATE computadoras SET estado = 'Ocupada' WHERE id_computadora = ?";
        
        try {
            PreparedStatement ps = ConexionBD.conectar().prepareStatement(sql);
            ps.setInt(1, sesion.getIdComputadora());
            
            ResultSet rs = ps.executeQuery();

            if (rs.next())
            {
                id_computadora = rs.getInt("id_computadora");
            }
            else
            {
                System.out.println("No existe una computadora con ese ID");
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            System.out.println("Error 1");
        }
        
        try {
            PreparedStatement ps = ConexionBD.conectar().prepareStatement(sql2);
            ps.setInt(1, id_computadora);
            ps.setInt(2, sesion.getIdCliente());
            ps.setTimestamp(3, Timestamp.valueOf(sesion.getFechaInicio()));
            ps.setString(4, sesion.getEstado());
            
            int filasInsertadas = ps.executeUpdate();
            if (filasInsertadas > 0)
            {
                System.out.println("Sesion insertada correctamente.");
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            System.out.println("Error 1");
        }
        
        try {
            
            PreparedStatement ps = ConexionBD.conectar().prepareStatement(sql3);
            ps.setInt(1, id_computadora);
      
            int filasActualizadas = ps.executeUpdate();
            if (filasActualizadas > 0)
            {
                System.out.println("Computadora actualizada correctamente.");
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            System.out.println("Error 2");
        }
    }

    public void finalizarSesion(int idSesion){
        String sql = "UPDATE sesiones " + "SET fecha_fin = NOW(), " + "estado_sesion = 'Finalizada' " + "WHERE id_sesiones = ?";
        
        try
        {
            PreparedStatement ps = ConexionBD.conectar().prepareStatement(sql);

            ps.setInt(1, idSesion);

            int filas = ps.executeUpdate();

            if(filas > 0)
            {
                System.out.println("Sesión finalizada correctamente");
            }

            ps.close();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
            System.out.println("Sesión finalizada");
    }

    public Sesion buscarPorId(int idSesion)
    {
        String sql = "SELECT * FROM sesiones WHERE id_sesiones = ?";
        Sesion sesion = new Sesion();

        try {
            PreparedStatement ps = ConexionBD.conectar().prepareStatement(sql);
            ps.setInt(1, idSesion); 

            ResultSet rs = ps.executeQuery();

            if (rs.next())
            {
                int id = rs.getInt("id_sesiones");
                int idCliente = rs.getInt("id_cliente");
                int idComputadora = rs.getInt("id_computadora");
                LocalDateTime fecha_inicio = rs.getTimestamp("fecha_inicio").toLocalDateTime();
                LocalDateTime fecha_fin = rs.getTimestamp("fecha_fin").toLocalDateTime();
                String estado = rs.getString("estado_sesion");
                
                sesion.setIdSesion(id);
                sesion.setIdCliente(idCliente);
                sesion.setIdComputadora(idComputadora);
                sesion.setFechaInicio(fecha_inicio);
                sesion.setFechaFin(fecha_fin);
                sesion.setEstado(estado);
                
                System.out.println("El Id_Sesion: " + sesion.getIdSesion());
                System.out.println("El Id_Cliente: " + sesion.getIdCliente());
                System.out.println("El Numero de Pc: " + sesion.getIdComputadora());
                System.out.println("La fecha de inicio: " + sesion.getFechaInicio());
                System.out.println("La fecha de Finalizacion: " + sesion.getFechaFin());
                System.out.println("El estado es: " + sesion.getEstado());
            }
            else
            {
                System.out.println("No existe una sesión con ese ID");
            }
            rs.close();
            ps.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return sesion;
    }

    public List<Sesion> listarSesionesActivas() 
    {
        List<Sesion> sesiones = new ArrayList<>();
        
        String sqlActivas = "SELECT * FROM sesiones WHERE estado_sesion = 'Activa'";
        
        try{
            PreparedStatement ps = ConexionBD.conectar().prepareStatement(sqlActivas );
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {

                Sesion sesion = new Sesion();

                sesion.setIdSesion(rs.getInt("id_sesiones"));
                sesion.setIdCliente(rs.getInt("id_cliente"));
                sesion.setIdComputadora(rs.getInt("id_computadora"));

                sesion.setFechaInicio(rs.getTimestamp("fecha_inicio").toLocalDateTime());

                if (rs.getTimestamp("fecha_fin") != null)
                {
                   sesion.setFechaFin(rs.getTimestamp("fecha_fin").toLocalDateTime());
                }

                sesion.setEstado(rs.getString("estado_sesion"));

                sesiones.add(sesion);
            }

            rs.close();
            ps.close();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
      
        return sesiones;
    }
    
    public double obtenerMontoProductos(int idSesion)
    {
        double montoProductos = 0;

        String sql =
            "SELECT SUM(dc.cantidad * p.precio) AS total FROM detalle_cobros dc INNER JOIN productos p ON dc.id_producto = p.id_producto INNER JOIN cobros c ON dc.id_cobro = c.id_cobro WHERE c.id_sesion = ?";

        try
        {
            PreparedStatement ps = ConexionBD.conectar().prepareStatement(sql);

            ps.setInt(1, idSesion);

            ResultSet rs = ps.executeQuery();

            if(rs.next())
            {
                montoProductos = rs.getDouble("total");
            }

            rs.close();
            ps.close();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return montoProductos;
    }
    
    public void liberarComputadora(int idSesion)
    {
        String sql = "UPDATE computadoras c " + "INNER JOIN sesiones s " + "ON c.id_computadora = s.id_computadora " + "SET c.estado = 'Libre' " + "WHERE s.id_sesiones = ?";

        try
        {
            PreparedStatement ps = ConexionBD.conectar().prepareStatement(sql);

            ps.setInt(1, idSesion);

            ps.executeUpdate();

            ps.close();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }
    
    public void cambiarEstadoLibre(int idComputadora)
    {
        String sql = "UPDATE computadoras SET estado = 'Libre' WHERE id_computadora = ?";

        try
        {
            PreparedStatement ps = ConexionBD.conectar().prepareStatement(sql);

            ps.setInt(1, idComputadora);

            int filas = ps.executeUpdate();

            if(filas > 0)
            {
                System.out.println("PC liberada correctamente");
            }

            ps.close();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }
}