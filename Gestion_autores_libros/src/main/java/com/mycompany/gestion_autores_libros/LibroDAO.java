/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestion_autores_libros;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Claudia
 */
public class LibroDAO {
    public static ArrayList<Libro> listarLibros(){
       ArrayList<Libro> libros = new ArrayList<>();
       
       String query = "SELECT * FROM libro";
       
       try{
           PreparedStatement stmt = Conexion.getPreparedStatement(query);
           
           ResultSet rs = stmt.executeQuery();
           while(rs.next()){
               int id = rs.getInt("id");
               String titulo = rs.getString("titulo");
               String fechaPubli = rs.getString("fecha_publicacion");
               String genero = rs.getString("genero");
               String isbn = rs.getString("isbn");
               String editorial = rs.getString("editorial");
               int idAutor = rs.getInt("id_autor");
               
               Libro libro = new Libro(id, titulo, fechaPubli, genero, isbn, editorial, idAutor);
               libros.add(libro);
           }
       }catch(SQLException e){
           System.err.println("Error al listar los libros");
       }
       
       return libros;
    }
    
    public static void addLibro(Libro libro){
        String query = "INSERT INTO libro (titulo, fecha_publicacion, genero, isbn, editorial, id_autor) VALUES (?,?,?,?,?,?)";
        
        try{
            PreparedStatement stmt = Conexion.getPreparedStatement(query);
            stmt.setString(1, libro.getTitulo());
            stmt.setString(2, libro.getFechaPubli());
            stmt.setString(3, libro.getGenero());
            stmt.setString(4, libro.getIsbn());
            stmt.setString(5, libro.getEditorial());
            stmt.setInt(6, libro.getIdAutor());
            
            stmt.executeUpdate();
            System.out.println("Libro añadido con exito");
        }catch(SQLException e){
            System.err.println("Error al añadir libro: "+e.getLocalizedMessage());
        }
    }
    
    public static void deleteLibro(int id){
        String deleteLibro = "DELETE FROM libro WHERE id=?";
        
        try{
            PreparedStatement stmtLibro = Conexion.getPreparedStatement(deleteLibro);
            stmtLibro.setInt(1, id);
            stmtLibro.executeUpdate();
            
            
            System.out.println("El libro ha sido borrado con exito");
        }catch(SQLException e){
            System.err.println("Error al borrar libro");
        }
    }
    
    public static void updateLibro(Libro libro){
        String update = "UPDATE libro SET titulo=?, fecha_publicacion=?, genero=?, isbn=?, editorial=?, id_autor=? WHERE id=?";
        
        try{
            PreparedStatement stmt = Conexion.getPreparedStatement(update);
            stmt.setString(1, libro.getTitulo());
            stmt.setString(2, libro.getFechaPubli());
            stmt.setString(3, libro.getGenero());
            stmt.setString(4, libro.getIsbn());
            stmt.setString(5, libro.getEditorial());
            stmt.setInt(6, libro.getIdAutor());
            stmt.setInt(7, libro.getId());
            
            stmt.executeUpdate();
            System.out.println("Libro actualizado con exito");
        }catch(SQLException e){
            System.err.println("Error al actualizar libro");
        }
    }
}
