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
public class AsignaturaDAO {
    private static void addAsignatura(Asignatura asignatura){
        Session session = Conexion.getSession();
        
        Transaction transaction = null;
        
        try{
            //Iniciar transaccion
            transaction = session.beginTransaction();
            session.persist(asignatura);
            transaction.commit();
            System.out.println("Asignatura guardada con exito");
            
        }catch(Exception e){
            if(transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        }finally{
            Conexion.close();
        }
    }
    
    private static void updateAsignatura(Asignatura asignatura){
        Session session = Conexion.getSession();
        Transaction transaction = null;
        
        try{
            transaction = session.beginTransaction();
            session.merge(asignatura);
            transaction.commit();
            System.out.println("Asignatura actualizada con éxito");
            
        }catch(Exception e){
            if(transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        }finally{
            Conexion.close();
        }
    }
    
    private static void deleteAsignatura(int id){
        Session session = Conexion.getSession();
        Transaction transaction = null;
        
        try{
            transaction = session.beginTransaction();
            Asignatura asignatura = searchAsignaturaByID(id);
            session.remove(asignatura);
            transaction.commit();
            System.out.println("Asignatura borrada correctamente");
        }catch(Exception e){
            if(transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        }finally{
            Conexion.close();
            
        }
    }
    private static ArrayList<Asignatura> ListarAsignaturas(){
        Session session = Conexion.getSession();
        return (ArrayList<Asignatura>) session.createQuery("FROM Asignatura", Asignatura.class).getResultList();
    }
    private static Asignatura searchAsignaturaByID(int id){
        Session session = Conexion.getSession();
        Query query = session.createQuery("FROM Asignatura WHERE id= :id", Asignatura.class);
        query.setParameter("id", id);
        Asignatura asinatura = (Asignatura) query.uniqueResult();
        return asinatura;
    }
}
