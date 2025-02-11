/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ejercicio4pedidoshibernate;

import java.util.ArrayList;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author Claudia
 */
public class ProductoDAO {
    public static void addProducto(Producto producto){
        Session session = Conexion.getSession();
        Transaction transaction = null;
        
        try{
            transaction = session.beginTransaction();
            session.persist(producto);
            transaction.commit();
            System.out.println("Producto guardado correctamente");
        }catch(Exception e){
            if(transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        }finally{
            Conexion.close();
        }
    }
    
    public static ArrayList<Producto> listProducto(){
        Session session = Conexion.getSession();
        return (ArrayList<Producto>) session.createQuery("FROM producto", Producto.class);
    }
    
    public static Producto listProductByStock(int stock){
        Session session = Conexion.getSession();
        Producto producto = null;

        try {
            Query<Producto> query = session.createQuery(
                "FROM Producto p WHERE p.stock < :stock", 
                Producto.class);
            query.setParameter("stock", stock);
            producto = query.uniqueResult();
        } catch (Exception e) {
            System.out.println("Error al buscar producto: " + e.getMessage());
        } 

        return producto;
    }
    
    public static Producto getProductoById(int id){
        Session session = Conexion.getSession();
        Producto producto = null;

        try {
            //Se usa el fetch para acceder directamente a asignaturas en la query sino se pone, despues no se puede hacer fuera profesor.getasignaturas
            Query<Producto> query = session.createQuery(
                "FROM Producto p WHERE p.id = :id", 
                Producto.class);
            query.setParameter("id", id);
            producto = query.uniqueResult();
        } catch (Exception e) {
            System.out.println("Error al buscar producto: " + e.getMessage());
        } 

        return producto;
    }
    
    
}
