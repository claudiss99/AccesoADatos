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
}
