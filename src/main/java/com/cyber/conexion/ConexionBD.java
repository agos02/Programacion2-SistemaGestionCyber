package com.cyber.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

    private static final String URL = "jdbc:mysql://localhost:3306/sistemagestioncyber";
    private static final String USER = "root";  // Usuario con el que ingresamos a MySQL
    private static final String PASSWORD = "";  // Contraseña de MySQL Workbench

    // Intenta conectarse a la base de datos y devuelve esa conexión.
    public static Connection conectar()
    {
        Connection conexion = null; 
        try {                                                    
            conexion = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexión exitosa a MySQL."); 
        } catch (SQLException e) {  
            System.out.println("Error al conectar con la base de datos.");
            e.printStackTrace();
        }
        return conexion; 
    }

    //Método para cerrar la conexión de forma segura desde cualquier DAO
    public static void cerrar(Connection conexion)
    {
        if (conexion != null)
        {
            try {
                conexion.close();
                System.out.println("Conexión cerrada correctamente.");
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexión.");
                e.printStackTrace();
            }
        }
    }
}