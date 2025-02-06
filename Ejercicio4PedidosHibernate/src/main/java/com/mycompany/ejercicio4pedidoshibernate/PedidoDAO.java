/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ejercicio4pedidoshibernate;

import java.util.ArrayList;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Claudia
 */
public class PedidoDAO {
    public static void addPedido(Pedido pedido){
        Session session = Conexion.getSession();
        Transaction transaction = null;
        
        try{
            transaction = session.beginTransaction();
            session.persist(pedido);
            transaction.commit();
            System.out.println("Pedido añadido correctamente");
        }catch(Exception e){
            if(transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        }finally{
            Conexion.close();
        }
    }
    
    public static ArrayList<Pedido> listPedido(){
        Session session = Conexion.getSession();
        return (ArrayList<Pedido>) session.createQuery("FROM pedido", Pedido.class);
        
    }
    
    public static void listPedidoByDetalles(int id){
        Session session = Conexion.getSession();
        
    }
}
