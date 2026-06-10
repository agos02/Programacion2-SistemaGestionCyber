package com.cyber;

import com.cyber.conexion.ConexionBD;
import com.cyber.dao.ComputadoraDAO;
import com.cyber.modelos.Computadora;

import java.sql.Connection;

public class SistemaGestionCyber {

    public static void main(String[] args) {

        Connection conexion = ConexionBD.conectar();

        if (conexion != null) {

            System.out.println("La conexión funciona correctamente.");

            ConexionBD.cerrar(conexion);

            ComputadoraDAO dao = new ComputadoraDAO();

            dao.insertar(new Computadora(0, 1, "Libre"));
            dao.insertar(new Computadora(0, 2, "Libre"));
            dao.insertar(new Computadora(0, 3, "Libre"));

            System.out.println(
            "Cantidad: " + dao.listar().size()
            );

        } else {

            System.out.println("No se pudo conectar a la base de datos.");

        }
    }
}
