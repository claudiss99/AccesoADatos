/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ejercicio2instituto;

import java.util.ArrayList;
import org.hibernate.Session;
import org.hibernate.Transaction;

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
    
    private static void updateProfesor(){
        
    }
    
    private static void deleteProfesor(){
        
    }
    private static ArrayList<Profesor> ListarProfesores(){
        Session session = Conexion.getSession();
        return (ArrayList<Profesor>) session.createQuery("FROM profesor", Profesor.class);
        
    }
    private static void searchProfesorByID(){
        
    }
}
