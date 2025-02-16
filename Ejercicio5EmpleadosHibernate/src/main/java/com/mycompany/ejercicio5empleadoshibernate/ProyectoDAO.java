/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ejercicio5empleadoshibernate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author Claudia
 */
public class ProyectoDAO {
    public static void addProyect(Proyecto proyecto){
        Session session = Conexion.getSession();
        Transaction transaction = null;
        
        try{
            transaction = session.beginTransaction();
            session.persist(proyecto);
            transaction.commit();
            //Si todo correcto --> quiero ID
            int id = proyecto.getId();
            System.out.println("Proyecto añadido correctamente. Tiene el id: "+id);
        }catch(Exception e){
            if(transaction != null){
                transaction.rollback();
            }
            System.err.println("Error al añadir proyecto: "+e.getLocalizedMessage());
        }finally{
            Conexion.close();
        }
    }
    
    public static Proyecto getByID(int id){
        Session session = Conexion.getSession();
        Proyecto proyecto = null;
                
        try{
            Query<Proyecto> query = session.createQuery("FROM Proyecto e WHERE e.id = :id", Proyecto.class);
            query.setParameter("id", id);
            proyecto = query.uniqueResult();
        }catch(Exception e){
            System.err.println("Error al buscar proyecto: "+e.getLocalizedMessage());
        }
                
        return proyecto;
    }
    
    public static void updateProyect(int id, String nombre, String fechaInicio, String fechaFin){
        Session session = Conexion.getSession();
        Transaction transaction = null;
        
        try{
            transaction = session.beginTransaction();
            //Buscamos por id para obtener el proyecto
            Proyecto proyecto = ProyectoDAO.getByID(id);
            //Modificamos los atributos
            proyecto.setNombre(nombre);
            proyecto.setFechaInicio(fechaInicio);
            proyecto.setFechaFin(fechaFin);
            // Mergeamos lo que tenemos
            session.merge(proyecto);
            transaction.commit();
            System.out.println("Proyecto modificado con éxito");
        }catch(Exception e){
            if(transaction != null){
                transaction.rollback();
            }
            System.err.println("Error al añadir proyecto: "+e.getLocalizedMessage());
        }finally{
            Conexion.close();
        }
    }
    
    public static List<Proyecto> listFutureProyect(){
        Session session = Conexion.getSession();
        LocalDate hoy = LocalDate.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String fechaFormateada = hoy.format(formato);
        return (List<Proyecto>) session.createQuery("FROM Proyecto p WHERE p.fechaInicio>:fechaFormateada").setParameter("fechaFormateada", hoy).getResultList();
    }
    public static List<Proyecto> listPastProyect(){
        Session session = Conexion.getSession();
        LocalDate hoy = LocalDate.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String fechaFormateada = hoy.format(formato);
        return (List<Proyecto>) session.createQuery("FROM Proyecto p WHERE p.fechaInicio<:fechaFormateada").setParameter("fechaFormateada", hoy).getResultList();
    }
    
    public static List<Proyecto> listActiveProyect(){
        Session session = Conexion.getSession();
        LocalDate hoy = LocalDate.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String fechaFormateada = hoy.format(formato);
        return (List<Proyecto>) session.createQuery("FROM Proyecto p WHERE  :fechaFormateada BETWEEN p.fechaInicio AND fechaFin").setParameter("fechaFormateada", hoy).getResultList();
    }
}
