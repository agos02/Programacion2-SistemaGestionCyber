package com.cyber.dao;

import java.util.ArrayList;
import java.util.List;
import com.cyber.modelos.Sesion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.cyber.conexion.ConexionBD;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import javax.swing.table.DefaultTableModel;

public class SesionDAO {

    public void crearSesion(Sesion sesion)
    {
        int id_computadora = 0;
        String sql = "SELECT id_computadora FROM computadoras WHERE numero_pc = ?";
        String sql2 = "INSERT INTO sesiones (id_computadora, id_cliente, fecha_inicio, estado_sesion) VALUES (?, ?, ?, ?)";
        String sql3 = "UPDATE computadoras SET estado = 'Ocupada' WHERE id_computadora = ?";
        
        Connection conexion = ConexionBD.conectar();
        
        try {
            PreparedStatement ps = conexion.prepareStatement(sql);
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
            
            PreparedStatement ps = conexion.prepareStatement(sql3);
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
        ConexionBD.cerrar(conexion);
    }

    public void finalizarSesion(int idSesion)
    {
        Connection conexion = ConexionBD.conectar();

        try
        {
            // Finalizar la sesión
            String sqlSesion = "UPDATE sesiones SET fecha_fin = NOW(), estado_sesion = 'Finalizada' WHERE id_sesiones = ?";

            PreparedStatement psSesion = conexion.prepareStatement(sqlSesion);
            psSesion.setInt(1, idSesion);
            psSesion.executeUpdate();
            psSesion.close();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            ConexionBD.cerrar(conexion);
        }
    }

    public Sesion buscarPorId(int id_sesiones)
    {
        String sql = "SELECT * FROM sesiones WHERE id_sesiones = ?";
        Sesion sesion = new Sesion();

        Connection conexion = ConexionBD.conectar();
        try {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setInt(1, id_sesiones);

            ResultSet rs = ps.executeQuery();

            if (rs.next())
            {
                int id = rs.getInt("id_sesiones");
                int idCliente = rs.getInt("id_cliente");
                int idComputadora = rs.getInt("id_computadora");
                LocalDateTime fecha_inicio = rs.getTimestamp("fecha_inicio").toLocalDateTime();
                
                // Validar si fecha_fin es NULL
                Timestamp tsFechaFin = rs.getTimestamp("fecha_fin");
                LocalDateTime fecha_fin = null;

                if (tsFechaFin != null){
                    fecha_fin = tsFechaFin.toLocalDateTime();
                }

                String estado = rs.getString("estado_sesion");
                
                sesion.setIdSesion(id);
                sesion.setIdCliente(idCliente);
                sesion.setIdComputadora(idComputadora);
                sesion.setFechaInicio(fecha_inicio);
                sesion.setFechaFin(fecha_fin);
                sesion.setEstado(estado);
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
        ConexionBD.cerrar(conexion);
        return sesion;
    }

    public List<Sesion> listarSesionesActivas() 
    {
        List<Sesion> sesiones = new ArrayList<>();
        
        String sqlActivas = "SELECT * FROM sesiones WHERE estado_sesion = 'Activa'";
        
        Connection conexion = ConexionBD.conectar();
        try{
            PreparedStatement ps = conexion.prepareStatement(sqlActivas );
            ResultSet rs = ps.executeQuery();
            
            while (rs.next())
            {
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
        ConexionBD.cerrar(conexion);
        return sesiones;
    }
    
    public double obtenerMontoProductos(int id_producto)
    {
        double montoProducto = 0;
        
        String sql = "SELECT precio FROM productos WHERE id_producto = ?";
        //String sql = "SELECT SUM(dc.cantidad * p.precio) AS total FROM detalle_cobros dc INNER JOIN productos p ON dc.id_producto = p.id_producto INNER JOIN cobros c ON dc.id_ticket = c.id_ticket WHERE c.id_sesion = ?";

        Connection conexion = ConexionBD.conectar();
        try
        {
            PreparedStatement ps = conexion.prepareStatement(sql);

            ps.setInt(1, id_producto);

            ResultSet rs = ps.executeQuery();

            if(rs.next())
            {
                montoProducto = rs.getDouble("precio");
            }
            rs.close();
            ps.close();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        ConexionBD.cerrar(conexion);
        return montoProducto;
    }
    
    public void liberarComputadora(int id_sesiones)
    {
        String sql = "UPDATE computadoras c " + "INNER JOIN sesiones s " + "ON c.id_computadora = s.id_computadora " + "SET c.estado = 'Libre' " + "WHERE s.id_sesiones = ?";
        
        Connection conexion = ConexionBD.conectar();
        
        try
        {
            PreparedStatement ps = conexion.prepareStatement(sql);

            ps.setInt(1, id_sesiones);

            ps.executeUpdate();

            ps.close();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        ConexionBD.cerrar(conexion);
    }
    
    public void cambiarEstadoLibre(int idComputadora)
    {
        String sql = "UPDATE computadoras SET estado = 'Libre' WHERE id_computadora = ?";
        
        Connection conexion = ConexionBD.conectar();
        
        try
        {
            PreparedStatement ps = conexion.prepareStatement(sql);

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
        ConexionBD.cerrar(conexion);
    }
    
    public int obtenerSesionActivaPorPc(int numeroPc)
    {
        int id_sesiones = 0;

        String sql = "SELECT s.id_sesiones FROM sesiones s INNER JOIN computadoras c ON s.id_computadora = c.id_computadora WHERE c.numero_pc = ? AND s.estado_sesion = 'Activa'";
        
        Connection conexion = ConexionBD.conectar();
        try
        {
            PreparedStatement ps = conexion.prepareStatement(sql);

            ps.setInt(1, numeroPc);

            ResultSet rs = ps.executeQuery();

            if(rs.next())
            {
                id_sesiones = rs.getInt("id_sesiones");
            }

            rs.close();
            ps.close();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        ConexionBD.cerrar(conexion);
        return id_sesiones;
    }
    
    public DefaultTableModel obtenerDatosTabla()
    {
        DefaultTableModel modelo = new DefaultTableModel();
        
        modelo.addColumn("ID Sesión");
        modelo.addColumn("ID Cliente");
        modelo.addColumn("Cliente");
        modelo.addColumn("PC");
        modelo.addColumn("Inicio");
        
        String sql = "SELECT s.id_sesiones AS id_sesion, c.id_cliente AS id_cliente, c.nombre AS nombre_cliente, pc.numero_pc AS numero_pc, s.fecha_inicio AS fecha_inicio FROM sesiones s INNER JOIN clientes c ON s.id_cliente = c.id_cliente INNER JOIN computadoras pc ON s.id_computadora = pc.id_computadora WHERE s.estado_sesion = 'Activa'";
        
        Connection conexion = ConexionBD.conectar();
        try
        {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next())
            {
                Object[] fila = {
                    rs.getInt("id_sesion"),
                    rs.getInt("id_cliente"),
                    rs.getString("nombre_cliente"),
                    rs.getInt("numero_pc"),
                    rs.getTimestamp("fecha_inicio").toLocalDateTime()
                };
                modelo.addRow(fila);
            }
            rs.close();
            ps.close();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        ConexionBD.cerrar(conexion);
        return modelo;
    }
    
    public ArrayList<String> cargarClientes()
    {
        ArrayList<String> nombres = new ArrayList();
        String sql = "SELECT c.id_cliente, c.nombre FROM clientes c WHERE NOT EXISTS (SELECT 1 FROM sesiones s WHERE s.id_cliente = c.id_cliente AND s.estado_sesion = 'Activa')";
        
        Connection conexion = ConexionBD.conectar();
        try
        {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next())
            {
                String nombre = rs.getString("nombre");
                nombres.add(nombre);
            }
            rs.close();
            ps.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        ConexionBD.cerrar(conexion);
        return nombres;
    }
    
    public ArrayList<Integer> cargarPcDisponibles()
    {
        ArrayList<Integer> pc_disponibles = new ArrayList();
        String sql = "SELECT numero_pc FROM computadoras WHERE estado = 'Libre'";
        
        Connection conexion = ConexionBD.conectar();
        try 
        {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next())
            {
                int numero_pc = rs.getInt("numero_pc");
                
                pc_disponibles.add(numero_pc);
            }
            rs.close();
            ps.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        ConexionBD.cerrar(conexion);
        return pc_disponibles;
    }
    
    public int obtenerIDClientePorNombre(String nombre)
    {
        int id = 0;
        String sql = "SELECT id_cliente FROM clientes WHERE nombre = ?";

        Connection conexion = ConexionBD.conectar();
        try
        {
            PreparedStatement ps = conexion.prepareStatement(sql);

            ps.setString(1, nombre);

            ResultSet rs = ps.executeQuery();

            if(rs.next())
            {
                id = rs.getInt("id_cliente");
            }
            rs.close();
            ps.close();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        ConexionBD.cerrar(conexion);
        return id;
    }
    
    public void agregarConsumo(int idSesion,int idProducto,int cantidad)
    {
        String sql = "INSERT INTO detalle_cobros (id_sesion, id_producto, cantidad) VALUES (?, ?, ?)";

        Connection conexion = ConexionBD.conectar();

        try
        {
            PreparedStatement ps = conexion.prepareStatement(sql); ps.setInt(1, idSesion);  ps.setInt(2, idProducto); ps.setInt(3, cantidad); ps.executeUpdate(); ps.close();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        ConexionBD.cerrar(conexion);
    }
}