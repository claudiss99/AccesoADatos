/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.biblioteca_hibernate;

import java.util.ArrayList;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Claudia
 */
public class LibroDAO {
    public static ArrayList<Libro> listarLibros(){
       Session session = Conexion.getSession();
       
       return (ArrayList<Libro>) session.createQuery("FROM Libro", Libro.class).getResultList();
    }
    
    public static void addLibro(Libro libro){
        Session session = Conexion.getSession();
        Transaction transaction = null;
        
        try{
             //Iniciar transaccion
            transaction = session.beginTransaction();
            session.persist(libro);
            transaction.commit();
            System.out.println("Libro guardado correctamente");
        }catch(Exception e){
            if(transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        }finally{
            Conexion.close();
        }
        
       
        
        
    }
    
    public static void deleteLibro(int id){
        Session session = Conexion.getSession();
        Transaction transaction = null;
        
        try{
             //Iniciar transaccion
            transaction = session.beginTransaction();
            session.remove(id);
            transaction.commit();
            System.out.println("Libro borrado correctamente");
        }catch(Exception e){
            if(transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        }finally{
            Conexion.close();
        }
        
        
        
        
    }
    
    public static void updateLibro(Libro libro){
        Session session = Conexion.getSession();
        Transaction transaction = null;
        
        try{
             //Iniciar transaccion
            transaction = session.beginTransaction();
            session.merge(libro);
            transaction.commit();
            System.out.println("Libro actualizado con exito");
        }catch(Exception e){
            if(transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        }finally{
            Conexion.close();
        }
        
        
        
    }
}
