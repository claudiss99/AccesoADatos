/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.biblioteca_hibernate;

import java.util.ArrayList;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

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
        
        Transaction transaction = null;
        
        try{
             //Iniciar transaccion
            transaction = session.beginTransaction();
            session.persist(autor);
            transaction.commit();
            System.out.println("Autor guardado correctamente");
        }catch(Exception e){
            if(transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        }finally{
            Conexion.close();
        }
        
        
        
    }
    
    public static void deleteAutor(int idAutor){
        //Primero hay que borrar todos los libros cuyo autor sea el que queremos eliminar
        Session session = Conexion.getSession();
        
        Transaction transaction = null;
        
        try{
            //Iniciar transaccion
            transaction = session.beginTransaction();
            
            //Buscamos los libros que tienen ese autor
            Query query = session.createQuery("FROM Libro WHERE idAutor= :idAutor", Libro.class);
            query.setParameter("idAutor", idAutor);
            ArrayList<Libro> libros= (ArrayList<Libro>) query.getResultList();

            //Borramos los libros que tiene ese autor
            session.remove(libros);

            //Borramos el autor
            session.remove(idAutor);
            transaction.commit();
            
            System.out.println("Autor borrado correctamente");
        }catch(Exception e){
            if(transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        }finally{
            Conexion.close();
        }
        
        
    }
    
    public static void updateAutor(Autor autor){
        
        Session session = Conexion.getSession();
        Transaction transaction = null;
        
        try{
             //Iniciar transaccion
            transaction = session.beginTransaction();
            session.merge(autor);
            transaction.commit();
            System.out.println("Autor actualizado con exito");
        }catch(Exception e){
            if(transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        }finally{
            Conexion.close();
        }
       
        
    }
    
    public static Autor searchAutorById(int id){
        Session session = Conexion.getSession();
        Query query = session.createQuery("FROM Autor WHERE id= :id", Autor.class);
        query.setParameter("id", id);
        Autor autor = (Autor)query.uniqueResult();
        return autor;
        
    }
    
    public static Autor searhAutorByName(String name){
        Session session = Conexion.getSession();
        Query query = session.createQuery("FROM Autor LIKE :nombre", Autor.class);
        query.setParameter("nombre", "%"+name+"%");
        Autor autor = (Autor) query.uniqueResult();
        return autor;
        
    }
}
