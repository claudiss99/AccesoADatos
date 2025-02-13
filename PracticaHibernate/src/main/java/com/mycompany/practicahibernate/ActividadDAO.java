/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.practicahibernate;

import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Claudia
 */
 /*
    Añadir actividad: Se solicitará nombre, fecha, ubicación, plazas y el cif del proveedor. 
    Una vez creada se mostrará el ID de la actividad.

    */
public class ActividadDAO {
    public static void addActivity(Actividad actividad, String cif){
        Session session = Conexion.getSession();
        Transaction transaction = null;
        
        try{
            transaction= session.beginTransaction();
            //Cogemos el objeto proveedor
            Proveedor proveedor = session.createQuery("FROM Proveedor WHERE cif=:cif", Proveedor.class)
                    .setParameter("cif", cif)
                    .getSingleResult();
            
            //Seteamos el proveedor a la actividad
            actividad.setIdProveedor(proveedor);
            System.out.println(actividad.toString());
            session.merge(actividad);
            transaction.commit();
            int id = actividad.getId();
            System.out.println("Se ha añadido la actividad correctamente. Su id es "+id);
        }catch(Exception e){
            System.err.println("Error al añadir actividad "+e.getLocalizedMessage());
            if (transaction != null){
                transaction.rollback();
            }
        }finally{
            session.close();
        }
    }
    
     /*
    Al borrarse una actividad se borrarán todas las compras de esta actividad.

    */
    public static void deleteActivity(int id){
        Session session = Conexion.getSession();
        Transaction transaction = null;
        
        try{
            transaction= session.beginTransaction();
            //Obtengo actividad 
            Actividad actividad = ActividadDAO.getById(id);
            session.remove(actividad);
            transaction.commit();
            System.out.println("Actividad eliminada con éxito");
        }catch(Exception e){
            System.err.println("Error al borrar actividad: "+e.getLocalizedMessage());
            if(transaction!=null){
                transaction.rollback();
            }
        }finally{
            session.close();
        }         
    }
    
    public static Actividad getById(int id){
        Session session = Conexion.getSession();;
        return (Actividad) session.createQuery("FROM Actividad WHERE id=:id", Actividad.class).setParameter("id", id).getSingleResult();
    }
    
    
}
