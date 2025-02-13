/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.practicahibernate;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author Claudia
 */
public class ProveedorDAO {
    public static void addProveedor(Proveedor proveedor){
        /*Si se puede añadir se mostrará el ID del proveedor añadido.*/      
        Session session = Conexion.getSession();
        Transaction transaction = null;
        
        try{
            transaction = session.beginTransaction();
            session.persist(proveedor);
            transaction.commit();
            int id = proveedor.getId();
            System.out.println("Se ha añadido proveedor correctamente. Este es su id: "+id);
        }catch(Exception e){
            if(transaction !=null){
                transaction.rollback();
            }
            System.out.println("Error al añadir proveedor: "+e.getLocalizedMessage());
        }finally{
            Conexion.close();
        }
        
    }
    
    public static void updateProveedor(int id, String nombre, String email, String cif){
        Session session = Conexion.getSession();
        Transaction transaction = null;
        Proveedor proveedor = null;
        try{
            Query<Proveedor> query = session.createQuery("FROM Proveedor p WHERE p.id =:id", Proveedor.class);
            query.setParameter("id", id);
            proveedor = query.getSingleResult();
            transaction = session.beginTransaction();
            proveedor.setNombre(nombre);
            proveedor.setEmail(email);
            proveedor.setCif(cif);
            session.merge(proveedor);
            transaction.commit();
            System.out.println("Se ha actualizado correctamente el proveedor");
        }catch(Exception e){
            if(transaction !=null){
                transaction.rollback();
            }
            System.err.println("Error al modificar proveedor: "+e.getLocalizedMessage());
        }finally{
            session.close();
        }
                
        
    }
    
    public static void deleteProveedor(int id){
        
        Session session = Conexion.getSession();
        Transaction transaction = null;
        LocalDate hoy = LocalDate.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String fechaFormateada = hoy.format(formato);
        
        try{
            
            //Obtengo proveedor

            Proveedor proveedor = ProveedorDAO.getByID(id);
            //Comprobar si tiene actividades con fecha futura
            List<Actividad> actividades = session.createQuery("FROM Actividad WHERE idProveedor =:id AND fecha>:fechaFormateada", Actividad.class)
                .setParameter("id", proveedor)
                .setParameter("fechaFormateada", fechaFormateada)
                .getResultList();
        
            transaction = session.beginTransaction();
            if (actividades.isEmpty()){
                //Si no tiene borro
                session.remove(proveedor);
                transaction.commit();
                System.out.println("Se ha borrado proveedor con éxito");
                
            }else{
                System.out.println("No se puede borrar ya que tiene actividades pendientes en el futuro");
            }
            
        }catch(Exception e){
            System.err.println("Error al borrar proveedor: "+e.getLocalizedMessage());
            if(transaction !=null){
                transaction.rollback();
            }
        }finally{
            session.close();
        }
         
    }
    
    public static Proveedor getByID(int id){
        Session session = Conexion.getSession();
        Proveedor proveedor = null;
                
        try{
            Query<Proveedor> query = session.createQuery("FROM Proveedor p WHERE p.id = :id", Proveedor.class);
            query.setParameter("id", id);
            proveedor = query.uniqueResult();
        }catch(Exception e){
            System.err.println("Error al buscar proveedor: "+e.getLocalizedMessage());
        }
                
        
        return proveedor;
    }
    
    public static  List<Proveedor> listDetailsProveedor(int id){
        Session session = Conexion.getSession();
        List<Proveedor> proveedores = session.createQuery("FROM Proveedor WHERE id=:id", Proveedor.class)
                .setParameter("id", id)
                .getResultList();
        return proveedores;
    }
}
