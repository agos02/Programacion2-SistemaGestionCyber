
package com.cyber.dao;

import java.util.List;
import java.util.ArrayList;
import com.cyber.modelos.Computadora;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.cyber.conexion.ConexionBD;

public class ComputadoraDAO {
    
    public List<Computadora> listar() {

    List<Computadora> computadoras = new ArrayList<>();

    String sql = "SELECT * FROM computadoras";

    Connection con = null;

    try {

        con = ConexionBD.conectar();

        PreparedStatement ps =
                con.prepareStatement(sql);

        ResultSet rs =
                ps.executeQuery();

        while(rs.next()) {

            while(rs.next()) {

    System.out.println(
        rs.getInt("id_computadora")
        + " | "
        + rs.getInt("numero_pc")
        + " | "
        + rs.getString("estado")
    );

    Computadora pc =
        new Computadora(
            rs.getInt("id_computadora"),
            rs.getInt("numero_pc"),
            rs.getString("estado")
        );

    computadoras.add(pc);
}
        }

    } catch(SQLException e) {
        e.printStackTrace();
    } finally {
        ConexionBD.cerrar(con);
    }

    return computadoras;
    
    }
    
    public void insertar(Computadora pc) {

        String sql =
            "INSERT INTO computadoras(numero_pc, estado) VALUES (?, ?)";

        Connection con = null;

        try {

            con = ConexionBD.conectar();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, pc.getNumeroPC());
            ps.setString(2, pc.getEstado());

            ps.executeUpdate();

        } catch(SQLException e) {
        e.printStackTrace();
        } finally {
        ConexionBD.cerrar(con);
        }
    }
    
    public void actualizar(Computadora pc) {

        String sql =
            "UPDATE computadoras SET numero_pc = ?, estado = ? WHERE id_computadora = ?";

        Connection con = null;

        try {

            con = ConexionBD.conectar();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, pc.getNumeroPC());
            ps.setString(2, pc.getEstado());
            ps.setInt(3, pc.getIdComputadora());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConexionBD.cerrar(con);
        }
    }
    
    public void eliminar(int idComputadora) {

        String sql =
            "DELETE FROM computadoras WHERE id_computadora = ?";

        Connection con = null;

        try {

            con = ConexionBD.conectar();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, idComputadora);

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConexionBD.cerrar(con);
        }
    }
    
}
