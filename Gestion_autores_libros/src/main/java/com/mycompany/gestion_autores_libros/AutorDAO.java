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
public class AutorDAO {
    
    public static ArrayList<Autor> listarAutores(){
       ArrayList<Autor> autores = new ArrayList<>();
       
       String query = "SELECT * FROM autor";
       
       try{
           PreparedStatement stmt = Conexion.getPreparedStatement(query);
           
           ResultSet rs = stmt.executeQuery();
           while(rs.next()){
               int id = rs.getInt("id");
               String nombre = rs.getString("nombre");
               String fechaNacim = rs.getString("fecha_nacimiento");
               String nacionalidad = rs.getString("nacionalidad");
               int obras = rs.getInt("numero_obras");
               String biografia = rs.getString("biografia");
               
               Autor autor = new Autor(id, nombre, fechaNacim, nacionalidad, obras, biografia);
               autores.add(autor);
           }
       }catch(SQLException e){
           System.err.println("Error al listar los autores");
       }
       
       return autores;
    }
    
    public static void addAutor(Autor autor){
        String query = "INSERT INTO autor (nombre, fecha_nacimiento, nacionalidad, numero_obras, biografia) VALUES (?,?,?,?,?)";
        
        try{
            PreparedStatement stmt = Conexion.getPreparedStatement(query);
            stmt.setString(1, autor.getNombre());
            stmt.setString(2, autor.getFechaNacim());
            stmt.setString(3, autor.getNacionalidad());
            stmt.setInt(4, autor.getObrasPubli());
            stmt.setString(5, autor.getBiografia());
            
            stmt.executeUpdate();
            System.out.println("Autor añadido con exito");
        }catch(SQLException e){
            System.err.println("Error al añadir autor");
        }
    }
    
    public static void deleteAutor(int id){
        //Primero hay que borrar todos los libros cuyo autor sea el que queremos eliminar
        String deleteLibro = "DELETE FROM libro WHERE id_autor=?";
        
        try{
            Conexion.startTransaction();
            PreparedStatement stmtLibro = Conexion.getPreparedStatement(deleteLibro);
            stmtLibro.setInt(1, id);
            stmtLibro.executeUpdate();
            
            String deleteAutor = "DELETE FROM autor WHERE id=?";
            PreparedStatement stmtAutor = Conexion.getPreparedStatement(deleteAutor);
            stmtAutor.setInt(1, id);
            stmtAutor.executeUpdate();
            
            Conexion.commit();
            System.out.println("El autor ha sido borrado con exito");
        }catch(SQLException e){
            try {
                Conexion.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(AutorDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.err.println("Error al borrar autor");
        }
    }
    
    public static void updateAutor(Autor autor){
        String update = "UPDATE autor SET nombre=?, fecha_nacimiento=?, nacionalidad=?, numero_obras=?, biografia=? WHERE id=?";
        
        try{
            PreparedStatement stmt = Conexion.getPreparedStatement(update);
            stmt.setString(1, autor.getNombre());
            stmt.setString(2, autor.getFechaNacim());
            stmt.setString(3, autor.getNacionalidad());
            stmt.setInt(4, autor.getObrasPubli());
            stmt.setString(5, autor.getBiografia());
            stmt.setInt(6, autor.getId());
            
            stmt.executeUpdate();
            System.out.println("Autor actualizado con exito");
        }catch(SQLException e){
            System.err.println("Error al actualizar autor");
        }
    }
}
