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
import org.hibernate.Session;

/**
 *
 * @author Claudia
 */
public class AutorDAO {
    
    public static ArrayList<Autor> listarAutores(){
       Session session = Conexion.getSession();

       return (ArrayList<Autor>) session.createQuery("FROM Autor", Autor.class).getResultList();
    }
    
    public static void addAutor(Autor autor){
        Session session = Conexion.getSession();
        session.persist(autor);
        System.out.println("Autor guardado correctamente");
        
    }
    
    public static void deleteAutor(int id){
        //Primero hay que borrar todos los libros cuyo autor sea el que queremos eliminar
        String deleteLibro = "DELETE FROM libro WHERE id_autor=?";
        
        
            String deleteAutor = "DELETE FROM autor WHERE id=?";
        
    }
    
    public static void updateAutor(Autor autor){
        String update = "UPDATE autor SET nombre=?, fecha_nacimiento=?, nacionalidad=?, numero_obras=?, biografia=? WHERE id=?";
        
    }
}
