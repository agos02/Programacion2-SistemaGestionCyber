package com.cyber;                                                                 

import com.cyber.conexion.ConexionBD;
import java.sql.Connection;
import views.productosviews;

public class SistemaGestionCyber {

    public static void main(String[] args) {
        
        Connection conexion = ConexionBD.conectar();  //Intentamos conectarnos a la base de datos

        if (conexion != null) {     //Verificamos si la conexión funcionó
            System.out.println("La conexión funciona correctamente.");
            
            productosviews.mainProductos();
            
            ConexionBD.cerrar(conexion); //Una vez probado cerramos la conexión inmediatamente
            
        } else {
            System.out.println("No se pudo conectar a la base de datos.");
        }

    }
}