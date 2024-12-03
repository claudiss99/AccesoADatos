/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ejercicio9;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author ClaudissPerez
 */
public class LibroDAO {
    private static Connection conn;
    
    public LibroDAO(Connection conn){
        this.conn = conn;
    }
    
    public ArrayList<Libro> listarLibros(){
        ArrayList<Libro> listaLibros = new ArrayList<>();
        String query = "SELECT * FROM libro";
        try{
            PreparedStatement stmt = ConexionDB.getPreparedStatement(query);
            
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                int idLibro = rs.getInt("id");
                String tituloLibro = rs.getString("titulo");
                String autorLibro= rs.getString("autor");
                int anioPublicacionLibro = rs.getInt("anio_publicacion");
                int cantidadDisponibleLibro = rs.getInt("cantidad_disponible");
                String categoriaLibro = rs.getString("categoria");
                
                Libro libro = new Libro(idLibro, tituloLibro, autorLibro, anioPublicacionLibro, cantidadDisponibleLibro, categoriaLibro);
                
                listaLibros.add(libro);
            }
        }catch (SQLException e){
            System.out.println("Error al listar libros");
        }
        return listaLibros;
        
    }
}
