package com.cyber;

import com.cyber.conexion.ConexionBD;
import com.cyber.controladores.ComputadoraControlador;
import com.cyber.dao.ComputadoraDAO;
import com.cyber.modelos.Computadora;
import com.cyber.vistas.ComputadoraVista;

import java.sql.Connection;

public class SistemaGestionCyber {

    public static void main(String args[]) {

        ComputadoraVista vista =
            new ComputadoraVista();
        
        ComputadoraDAO dao =
            new ComputadoraDAO();

        ComputadoraControlador controlador =
            new ComputadoraControlador(vista, dao);

        vista.setVisible(true);
    }
}
