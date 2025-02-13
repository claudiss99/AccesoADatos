/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.practicahibernate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.dialect.function.TransactSQLStrFunction;

/**
 *
 * @author Claudia
 */
public class CompraDAO {
    
    /*

    Se notificará al cliente si la compra se ha podido realizar (hay plazas disponibles y la actividad debe ser futura) 
    o si no ha sido posible. Cuando se compra una actividad se debe reducir el número de plazas disponibles.

    */
    public static void buyActivity(int idActivity, int idClient){
        Session session = Conexion.getSession();
        Transaction transaction = null;
        
        try{
            transaction = session.beginTransaction();
            //Comprobar que actividad y cliente existe
            Cliente cliente = ClienteDAO.getById(idClient);
            Actividad actividad = ActividadDAO.getById(idClient);
            LocalDate hoy = LocalDate.now();
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            String fechaFormateada = hoy.format(formato);
            if(cliente !=null && actividad!=null){
                //Ver si la actividad tiene plazas disponibles y es futura
                List<Actividad> actividades = session.createQuery("FROM Actividad WHERE id=:idActivity AND plazasDisponibles>0 AND fecha>:fechaFormateada", Actividad.class)
                        .setParameter("id", idActivity)
                        .setParameter("fechaFormateda", fechaFormateada)
                        .getResultList();
                 //Si hay actividades optimas --> Compramos
                if(!actividades.isEmpty()){
                    Compra compra = new Compra(null, fechaFormateada, cliente, actividad);
                    session.persist(compra);
                    //Reducimos el numero de plazas en actividad 
                    actividad.setPlazasDisponibles(actividad.getPlazasDisponibles()-1);
                    session.merge(actividad);
                    transaction.commit();
                    System.out.println("Se ha realizado la compra correctamente");
                }else{
                    System.out.println("No ha sido posible realizar la compra");
                }
            }
           
        }catch(Exception e){
            System.err.println("Error al comprar actividad: "+e.getLocalizedMessage());
            if(transaction!=null){
                transaction.rollback();
            }
        }finally{
            session.close();
        }
    }
}
