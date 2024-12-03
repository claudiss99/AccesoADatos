/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ejercicio9;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author ClaudissPerez
 */
public class PrestamoDAO {
    private static Connection conn;

    public PrestamoDAO(Connection conn) {
        this.conn = conn;
    }
    
    public void addPrestamo(Prestamo prestamo){
        String query = "INSERT INTO prestamo (id_libro, lector, fecha_prestamo) VALUES (?,?,?)";
        try{
            PreparedStatement stmt = ConexionDB.getPreparedStatement(query);
            stmt.setInt(1, prestamo.getId_libro());
            stmt.setString(2, prestamo.getLector());
            stmt.setDate(3, prestamo.getFecha_prestamo());
            
            stmt.executeUpdate();
            System.out.println("Prestamo agregado con exito");
        }catch (SQLException e){
            System.out.println("Error al agregar prestamo");
            e.printStackTrace();
        }
    }
    
    public ArrayList<Prestamo> listarPrestamos(){
        ArrayList<Prestamo> listaPrestamos = new ArrayList<>();
        String query = "SELECT * FROM prestamo";
        
        try{
            PreparedStatement stmt = ConexionDB.getPreparedStatement(query);
            
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                int idPrestamo = rs.getInt("id");
                int id_libro = rs.getInt("id_libro");
                String lector = rs.getString("lector");
                Date fecha_prestamo = rs.getDate("fecha_prestamo");
                String estado = rs.getString("estado");
                
                Prestamo prestamo = new Prestamo(idPrestamo, id_libro, lector, fecha_prestamo, estado);
                listaPrestamos.add(prestamo);
                
            }
        }catch (SQLException e){
            System.err.println("Error al listar productos");
        }
        return listaPrestamos;
    }
    
    public ArrayList<Prestamo> listarPrestamosPendientes() {
        ArrayList<Prestamo> listaPrestamos = new ArrayList<>();
        String query = """
            SELECT p.id, p.fecha_prestamo, l.titulo, l.autor, p.lector, p.estado
            FROM prestamo p
            JOIN libro l ON p.id_libro = l.id
            WHERE p.estado = 'Pendiente'
        """;

        try {
            PreparedStatement stmt = ConexionDB.getPreparedStatement(query);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String titulo = rs.getString("titulo");
                String autor = rs.getString("autor");
                String lector = rs.getString("lector");
                Date fechaPrestamo = rs.getDate("fecha_prestamo");
                String estado = rs.getString("estado");

                System.out.printf("ID: %d, Fecha: %s, Título: %s, Autor: %s, Lector: %s, Estado: %s%n",
                        id, fechaPrestamo, titulo, autor, lector, estado);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar préstamos pendientes: " + e.getMessage());
        }

        return listaPrestamos;
    }


    public void devolverPrestamo(int prestamoID) {
        String query = "UPDATE prestamo SET estado = 'Devuelto' WHERE id = ?";
        try {
            PreparedStatement stmt = ConexionDB.getPreparedStatement(query);

            stmt.setInt(1, prestamoID);
            int filasActualizadas = stmt.executeUpdate();

            if (filasActualizadas > 0) {
                System.out.println("El préstamo con ID " + prestamoID + " se ha devuelto con éxito.");
            } else {
                System.out.println("No se encontró ningún préstamo con el ID indicado.");
            }
        } catch (SQLException e) {
            System.err.println("Error al devolver préstamo: " + e.getMessage());
        }
    }

}
