/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ejercicio4pedidoshibernate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author Claudia
 */
public class PedidoDAO {
        public static void addPedido(Pedido pedido,HashMap<Integer, Integer> cantidades){
        Session session = Conexion.getSession();
        Transaction transaction = null;
        
        try{
            transaction = session.beginTransaction();
            session.persist(pedido);
            //Actualizar el stock de productos
            for (Map.Entry<Integer, Integer> cantidad:cantidades.entrySet()){
                Producto producto = ProductoDAO.getProductoById(cantidad.getKey());
                producto.setStock(cantidad.getValue());
                double subtotal = producto.getPrecio() * cantidad.getValue();
                //Persist de detalles de pedido por cada producto
                DetallePedido detalle = new DetallePedido(cantidad.getValue(), subtotal);
                detalle.setIdPedido(pedido);
                detalle.setIdProducto(producto);
                session.persist(detalle);
            }
            
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
    
    public static Pedido getPedidoById(int id){
        Session session = Conexion.getSession();
        Pedido pedido = null;

        try {
            //Se usa el fetch para acceder directamente a asignaturas en la query sino se pone, despues no se puede hacer fuera profesor.getasignaturas
            Query<Pedido> query = session.createQuery(
                "FROM Pedido p WHERE p.id = :id", 
                Pedido.class);
            query.setParameter("id", id);
            pedido = query.uniqueResult();
        } catch (Exception e) {
            System.out.println("Error al buscar profesor: " + e.getMessage());
        } 

        return pedido;
    }
    
    public static void listDetalles(Pedido pedido){
        ArrayList<DetallePedido> detalles = pedido.getDetallePedido();
        
    }
}
