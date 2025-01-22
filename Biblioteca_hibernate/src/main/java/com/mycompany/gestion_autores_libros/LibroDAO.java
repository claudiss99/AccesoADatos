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
       
       
       
       return libros;
    }
    
    public static void addLibro(Libro libro){
        String query = "INSERT INTO libro (titulo, fecha_publicacion, genero, isbn, editorial, id_autor) VALUES (?,?,?,?,?,?)";
        
        
    }
    
    public static void deleteLibro(int id){
        String deleteLibro = "DELETE FROM libro WHERE id=?";
        
        
    }
    
    public static void updateLibro(Libro libro){
        String update = "UPDATE libro SET titulo=?, fecha_publicacion=?, genero=?, isbn=?, editorial=?, id_autor=? WHERE id=?";
        
        
    }
}
