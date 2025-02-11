/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ejercicio5empleadoshibernate;

import java.util.ArrayList;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author Claudia
 */
public class EmpleadoDAO {
    public static void addEmpleado(Empleado empleado){
        Session session = Conexion.getSession();
        Transaction transaction = null;
        
        //Comprobamos si el empleado existe(Buscar por id)
        if(EmpleadoDAO.getByID(empleado.getId()) == null){
            //Si no existe --> se añade
            try{
                transaction = session.beginTransaction();
                session.persist(empleado);
                transaction.commit();
                //Si todo correcto --> quiero ID
                int id = empleado.getId();
                System.out.println("Empleado añadido correctamente. Tiene el id: "+id);
            }catch(Exception e){
                if(transaction != null){
                    transaction.rollback();
                }
                System.err.println("Error al añadir empleado: "+e.getLocalizedMessage());
            }finally{
                Conexion.close();
            }
              
        }else{
            //Si ya existia --> update empleado y activo(fecha_finalizacion == NULL)
            try{
                transaction = session.beginTransaction();
                //Ponemos la fecha de finalizacion a null
                empleado.setFechaFinalizacion(null);
                // Mergeamos lo que tenemos
                session.merge(empleado);
            }catch(Exception e){
                if(transaction != null){
                    transaction.rollback();
                }
                System.err.println("Error al añadir empleado: "+e.getLocalizedMessage());
            }finally{
                Conexion.close();
            }   
        }
    }
    
    public static Empleado getByID(int id){
        Session session = Conexion.getSession();
        Empleado empleado = null;
                
        try{
            Query<Empleado> query = session.createQuery("FROM empleado e WHERE e.id = :id", Empleado.class);
            query.setParameter("id", id);
            empleado = query.uniqueResult();
        }catch(Exception e){
            System.err.println("Error al buscar empleado: "+e.getLocalizedMessage());
        }
                
        return empleado;
    }
    
    public static void updateEmpleado(String nombre, String departamento, double sueldo, int id){
        Session session = Conexion.getSession();
        Transaction transaction = null;
        
        try{
            transaction = session.beginTransaction();
            //Buscamos por id para obtener el empleado
            Empleado empleado = EmpleadoDAO.getByID(id);
            //Modificamos los atributos
            empleado.setNombre(nombre);
            empleado.setDepartamento(departamento);
            empleado.setSueldo(sueldo);
            // Mergeamos lo que tenemos
            session.merge(empleado);
            transaction.commit();
            System.out.println("Empleado modificado con éxito");
        }catch(Exception e){
            if(transaction != null){
                transaction.rollback();
            }
            System.err.println("Error al añadir empleado: "+e.getLocalizedMessage());
        }finally{
            Conexion.close();
        }
    }
    
    public static void deleteEmpleado(int id, String fechaFinalizacion){
        //Se entiende por las tablas que se puede despedir sin tener en cuenta si está en un proyecto...
        
        Session session = Conexion.getSession();
        Transaction transaction = null;
        
        try{
            transaction = session.beginTransaction();
            //Buscamos empleado por id
            Empleado empleado = EmpleadoDAO.getByID(id);
            empleado.setFechaFinalizacion(fechaFinalizacion);
            session.merge(empleado);
            System.out.println("Empleado modificado con éxito");
        }catch(Exception e){
            if(transaction != null){
                transaction.rollback();
            }
            System.err.println("Error al añadir empleado: "+e.getLocalizedMessage());
        }finally{
            Conexion.close();
        }
    }
    
    public static ArrayList<Empleado> listEmpleadoActive(){
        Session session = Conexion.getSession();
        
        return (ArrayList<Empleado>) session.createQuery("FROM empleado e WHERE e.fecha_finalizacion IS NULL", Empleado.class);
    }
    
    public static ArrayList<Empleado> listEmpleadoDes(){
        Session session = Conexion.getSession();
        
        return (ArrayList<Empleado>) session.createQuery("FROM empleado e WHERE e.fecha_finalizacion NOT NULL", Empleado.class);
    }
    
    
}
