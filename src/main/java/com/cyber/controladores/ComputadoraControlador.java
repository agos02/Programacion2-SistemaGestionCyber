package com.cyber.controladores;

import com.cyber.dao.ComputadoraDAO;
import com.cyber.modelos.Computadora;
import com.cyber.vistas.ComputadoraVista;

import java.util.List;
import javax.swing.table.DefaultTableModel;


public class ComputadoraControlador {

    private ComputadoraVista vista;
    private ComputadoraDAO dao;


    public ComputadoraControlador(
        ComputadoraVista vista,
        ComputadoraDAO dao
        ) 
    
        {
        this.vista = vista;
        this.dao = dao;

        cargarTabla();
        iniciarEventos();
    }


    private void cargarTabla(){

        List<Computadora> lista = dao.listar();


        DefaultTableModel modelo = new DefaultTableModel(
            new Object[]{"ID","Numero PC","Estado"},
            0
        );


        for(Computadora pc : lista){

            modelo.addRow(new Object[]{
                pc.getIdComputadora(),
                pc.getNumeroPC(),
                pc.getEstado()
            });
        }

        vista.getTablaCompus()
        .setModel(modelo);
    }
    
    private void iniciarEventos(){
        
        vista.agregarListener(e -> {

            int numero = Integer.parseInt(vista.getNumeroPC());

            if(dao.buscarPorNumero(numero) != null){

                javax.swing.JOptionPane.showMessageDialog( 
                vista,
                "Ya existe una PC con ese número"
            );
                
            return;
        }

        Computadora pc = new Computadora(
            0,
            numero,
            "Libre"
        );

        dao.insertar(pc);
        cargarTabla();
    });

    vista.modificarListener(e -> {

        int numero;

        try {
            numero = Integer.parseInt(vista.getNumeroPC());
        }
        
        catch(NumberFormatException ex) {

            javax.swing.JOptionPane.showMessageDialog(
            vista,
            "Ingrese un número válido"
            );

            return;
        }

        Computadora pc = dao.buscarPorNumero(numero);

        if(pc == null) {

            javax.swing.JOptionPane.showMessageDialog(
            vista,
            "No existe una PC con ese número"
            );

            return;
        }

        pc.setEstado(
            vista.getEstado()
        );

        dao.actualizar(pc);
        cargarTabla();

        javax.swing.JOptionPane.showMessageDialog(
        vista,
        "Estado actualizado correctamente"
        );
    });

    vista.eliminarListener(e -> {

        int numero = Integer.parseInt(vista.getNumeroPC());

        Computadora pc = dao.buscarPorNumero(numero);

        if(pc == null){

            javax.swing.JOptionPane.showMessageDialog(
            vista,
            "No existe esa PC"
            );

            return;
        }

        dao.eliminar(
            pc.getIdComputadora()
        );

        cargarTabla();
        });
    }
}
