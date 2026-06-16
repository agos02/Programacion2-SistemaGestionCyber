package com.cyber;

import com.cyber.vistas.ClienteVista;
import com.cyber.vistas.productosviews;
import com.cyber.vistas.SesionVista;
import com.cyber.vistas.ReportesViews;
import com.cyber.vistas.CobroVista;
import com.cyber.vistas.menu;

import com.cyber.conexion.ConexionBD;
import java.sql.Connection;

public class SistemaGestionCyber {

    public static void main(String[] args) throws Exception{
        
        Connection conexion = ConexionBD.conectar();  //Intentamos conectarnos a la base de datos
       
        //Verificamos si la conexión funcionó
        if (conexion != null) 
        {
            System.out.println("La conexión funciona correctamente.");
            
            ConexionBD.cerrar(conexion); //Una vez probado, cerramos la conexión inmediatamente     
        } 
        else {
            System.out.println("No se pudo conectar a la base de datos.");
        }
        
        java.awt.EventQueue.invokeLater(() -> {
        new menu().setVisible(true);
        });
    }
}
  