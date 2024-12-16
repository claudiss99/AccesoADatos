/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ejercicio8_2;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author ClaudissPerez
 */
public class PedidoDAO {
    private static Connection conn;

    public PedidoDAO(Connection conn) {
        this.conn = conn;
    }
    
    public void addPedido(Pedido pedido, ArrayList<Producto> productos){
        String queryPedido = "INSERT INTO pedido (fecha, cliente) VALUES (?,?)";
        
        try{
            ConexionDB.deactivateAutoCommit();
            
            PreparedStatement stmtPedido = ConexionDB.getPreparedStatement(queryPedido);
            stmtPedido.setDate(1, pedido.getFecha());
            stmtPedido.setString(2, pedido.getCliente());
            stmtPedido.executeUpdate();
            ResultSet rs = stmtPedido.getGeneratedKeys();
            
            int idPedido = 0;
            if(rs.next()){
                idPedido = rs.getInt(1);
            }
            
            //Insertar detalles del producto
            
            String queryDetalles = "INSERT INTO detalle_pedido (id_pedido, id_producto, cantidad, subtotal) VALUES (?,?,?,?)";
            
            PreparedStatement stmtDetalles = ConexionDB.getPreparedStatement(queryDetalles);
            double subtotal;
            //Recorremos los productos
            for(Producto p:productos){
                subtotal = p.getStock() * p.getPrecio();
                stmtDetalles.setInt(1, idPedido);
                stmtDetalles.setInt(2, p.getId());
                //stock es la cantidad comprada de productos
                stmtDetalles.setInt(3, p.getStock());
                stmtDetalles.setDouble(4, subtotal);
                stmtDetalles.addBatch();
            }
            
            stmtDetalles.executeBatch();
            //Tengo que saber el stock del producto
            //¿Quiero la transaccion aqui??
            String queryStock= "SELECT stock FROM producto WHERE id =?";
            
            PreparedStatement stmtStock = ConexionDB.getPreparedStatement(queryStock);
            ResultSet rsStock = stmtStock.executeQuery();
            PreparedStatement stmtProducto = null;
            int stock;
            for (Producto p:productos){
                stmtStock.setInt(1, p.getId());
                
                while(rsStock.next()){
                    stock = rsStock.getInt("stock");
                    //Si el stock disponible es menor a la cantidad pedida rollback
                    if(stock<p.getStock()){
                        throw new SQLException();
                    }
                    //Recorremos los productos
                    String queryProducto = "UPDATE producto SET stock=stock-? WHERE id=?";

                    stmtProducto = ConexionDB.getPreparedStatement(queryProducto);

                    //Se le resta la cantidad
                    stmtProducto.setInt(1, p.getStock());
                    stmtProducto.setInt(2, p.getId());
                    stmtProducto.addBatch();
                }  
            }
            stmtProducto.executeBatch();
            ConexionDB.commit();
        }catch(SQLException e){
            ConexionDB.rollback();
            System.err.println("Error al insertar pedido");
        }finally{
            ConexionDB.activateAutoCommit();
        }
    }  
    
    public ArrayList<Pedido> listarPedido(){
        
        return null;
        
    }
    
    public ArrayList<Pedido> listarDetallesPedidos(){
        
        return null;
        
    }
}
