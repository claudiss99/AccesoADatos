/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ejercicio2instituto;

import java.util.ArrayList;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author Claudia
 */
public class ProfesorDAO {
    
    private static void addProfesor(Profesor profesor){
        Session session = Conexion.getSession();
        Transaction transaction = null;
        
        try{
            transaction = session.beginTransaction();
            session.persist(profesor);
            transaction.commit();
            System.out.println("Profesor guardado correctamente");
        }catch(Exception e){
            if(transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        }finally{
            Conexion.close();
        }
    }
    
    private static void updateProfesor(Profesor profesor){
        Session session = Conexion.getSession();
        Transaction transaction = null;
        
        try{
            //Iniciar transaccion 
            transaction= session.beginTransaction();
            session.merge(profesor);
            transaction.commit();
            System.out.println("Profesor actualizado con exito");
            
        }catch(Exception e){
            if (transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        }finally{
            Conexion.close();
        }
    }
    
    private static void deleteProfesor(int idProfesor){
        //Primero hay que borrar todas las asignaturas cuyo profesor sea el que queremos eliminar
        Session session = Conexion.getSession();
        
        Transaction transaction = null;
        
        try{
            //Iniciar transaccion 
            transaction = session.beginTransaction();
            Profesor profesor = searchProfesorByID(idProfesor);
            //Buscamos las asignaturas que da ese profe
            Query query = session.createQuery("FROM Asignatura WHERE profesor= :profesor ", Asignatura.class);
            query.setParameter("profesor", profesor);
            ArrayList<Asignatura> asignaturas = (ArrayList<Asignatura>) query.getResultList();
            
            for (Asignatura a: asignaturas){
                //Borramos las asignturas que imparte el profe
                session.remove(a);
            }
            
            //Borramos profe
            session.remove(profesor);
            transaction.commit();
            System.out.println("Profe borrado");
        }catch(Exception e){
            if(transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        }finally{
            Conexion.close();
        }
    }
    private static ArrayList<Profesor> ListarProfesores(){
        Session session = Conexion.getSession();
        return (ArrayList<Profesor>) session.createQuery("FROM profesor", Profesor.class);
        
    }
    private static Profesor searchProfesorByID(int id){
        Session session = Conexion.getSession();
        Query query = session.createQuery("FROM Profesor WHERE id= :id", Profesor.class);
        query.setParameter("id", id);
        Profesor profesor = (Profesor) query.uniqueResult();
        return profesor;
    }
}
