package com.cyber;
import com.cyber.controladores.ClienteControlador;
import com.cyber.vistas.ClienteVista;

import com.cyber.conexion.ConexionBD;
import com.cyber.vistas.SesionVista;
import java.sql.Connection;

public class SistemaGestionCyber {

    public static void main(String[] args) throws Exception{
        
        Connection conexion = ConexionBD.conectar();  //Intentamos conectarnos a la base de datos
       
        //Verificamos si la conexión funcionó
        if (conexion != null) 
        {
            System.out.println("La conexión funciona correctamente.");
            
            SesionVista.mainSesion();
            
            ConexionBD.cerrar(conexion); //Una vez probado, cerramos la conexión inmediatamente     
        } 
        else {
            System.out.println("No se pudo conectar a la base de datos.");
        }
<<<<<<< HEAD
        
        
          java.awt.EventQueue.invokeLater(() -> {
        new ClienteVista().setVisible(true);
        });
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        

=======
>>>>>>> feature/sesiones
    }
}