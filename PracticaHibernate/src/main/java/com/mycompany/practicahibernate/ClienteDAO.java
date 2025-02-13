/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.practicahibernate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Claudia
 */
public class ClienteDAO {
    public static void addClient(Cliente cliente){
        Session session = Conexion.getSession();
        Transaction transaction = null;
        
        try{
            transaction= session.beginTransaction();
            session.persist(cliente );
            transaction.commit();
            
            System.out.println("Cliente añadido con exito. Su id es: "+cliente.getId());
        }catch(Exception e){
            System.err.println("Error al añadir cliente: "+e.getLocalizedMessage());
            if(transaction!=null){
                transaction.rollback();
            }
        }finally{
            session.close();
        }
                  
    }
    
    public static void updateClient(int id, String nombre, String email){
        Session session = Conexion.getSession();
        Transaction transaction = null;
        
        try{
            transaction= session.beginTransaction();
            //Obtengo cliente
            Cliente cliente = ClienteDAO.getById(id);
            cliente.setNombre(nombre);
            cliente.setEmail(email);
            session.merge(cliente);
            transaction.commit();
            System.out.println("Se ha modificado el cliente con exito");
        }catch(Exception e){
            System.err.println("Error al modificar cliente: "+e.getLocalizedMessage());
            if(transaction!=null){
                transaction.rollback();
            }
        }finally{
            session.close();
        }
                
    }
    
    public static Cliente getById(int id){
        Session session = Conexion.getSession();
        return (Cliente) session.createQuery("FROM Cliente WHERE id=:id", Cliente.class).setParameter("id", id).getSingleResult();
    }
    
    
    /*
    
    Borrar cliente: Se pedirá el ID del cliente que se quiera borrar. 
    Al borrarse un cliente se borrarán todas las compras que haya realizado. 
    Un cliente no podrá borrarse si ha realizado una compra de una actividad 
    que aún no haya realizado (con fecha futura).
*/
    
    public static void deleteClient(int id){
        Session session = Conexion.getSession();
        Transaction transaction = null;
        
        try{
            transaction = session.beginTransaction();
            //Obtengo el cliente
            Cliente cliente = ClienteDAO.getById(id);
            //Ver las compras
            List<Compra> compras = (List<Compra>) session.createQuery("FROM Compra c WHERE c.idCliente=:cliente", Compra.class).setParameter("cliente", cliente).getResultList();
            //Ver las actividades futuras
            List<Actividad> actividades = new ArrayList<>();
            LocalDate hoy = LocalDate.now();
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            String fechaFormateada = hoy.format(formato);
            for (Compra c:compras){
                Actividad actividad = c.getIdActividad();
                int idActivity = actividad.getId();
                List<Actividad> ac = session.createQuery("FROM Actividad WHERE id=:idActivity AND fecha>:fechaFormateada", Actividad.class)
                        .setParameter("idActivity", idActivity)
                        .setParameter("fechaFormateada", fechaFormateada)
                        .getResultList();
                if (!ac.isEmpty()){
                    for (Actividad a: ac){
                        actividades.add(a);
                    }   
                }
            }
            //Si actividades futuras esta vacia
            if(actividades.isEmpty()){
                //Borramos
                session.remove(cliente);
                transaction.commit();
                System.out.println("Cliente borrado con éxito");
            }else{
                System.out.println("Cliente no se puede borrar tiene una actividad futura");
            }
        }catch(Exception e){
            System.err.println("Error al borrar cliente: "+e.getLocalizedMessage());
            if(transaction!=null){
                transaction.rollback();
            }
        }finally{
            session.close();
        }
    }
}
