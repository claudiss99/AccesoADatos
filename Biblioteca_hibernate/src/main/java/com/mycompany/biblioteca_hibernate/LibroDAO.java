/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.biblioteca_hibernate;

import java.util.ArrayList;
import org.hibernate.Session;

/**
 *
 * @author Claudia
 */
public class LibroDAO {
    public static ArrayList<Libro> listarLibros(){
       Session session = Conexion.getSession();
       
       return (ArrayList<Libro>) session.createQuery("FROM libro", Libro.class).getResultList();
    }
    
    public static void addLibro(Libro libro){
        Session session = Conexion.getSession();
        session.persist(libro);
        System.out.println("Libro guardado correctamente");
        
        
    }
    
    public static void deleteLibro(int id){
        Session session = Conexion.getSession();
        session.remove(id);
        System.out.println("Libro borrado correctamente");
        
        
    }
    
    public static void updateLibro(Libro libro){
        Session session = Conexion.getSession();
        session.merge(libro);
        
        
    }
}
