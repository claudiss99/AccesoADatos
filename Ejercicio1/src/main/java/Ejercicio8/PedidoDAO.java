/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ejercicio8;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Claudiss
 */
public class PedidoDAO {
    private static Connection conn;

    public PedidoDAO(Connection conn) {
        this.conn = conn;
    }
    
    public void addPedido(Pedido pedido, ArrayList<Producto> productos){
        String queryPedido = "INSERT INTO pedido (fecha, cliente) VALUES (?, ?)";
        String queryDetallePedido = "INSERT INTO detalle_pedido (id_pedido, id_producto, cantidad, subtotal) VALUES (?, ?, ?, ?)";
        String queryUpdateStock = "UPDATE producto SET stock = stock + ? WHERE id = ?";

        try {
            ConexionDB.deactivateAutoCommit();

            PreparedStatement stmtPedido = ConexionDB.getPreparedStatementGeneratedKeys(queryPedido);
            stmtPedido.setDate(1, pedido.getFecha());
            stmtPedido.setString(2, pedido.getCliente());
            stmtPedido.executeUpdate();

            ResultSet rs = stmtPedido.getGeneratedKeys();
            int idPedido = 0;
            if (rs.next()) {
                idPedido = rs.getInt(1);
            }

            for (Producto producto : productos) {
                
                double subtotal = producto.getStock() * producto.getPrecio();
                
                PreparedStatement stmtDetalle = ConexionDB.getPreparedStatement(queryDetallePedido);
                stmtDetalle.setInt(1, idPedido);
                stmtDetalle.setInt(2, producto.getId());
                stmtDetalle.setInt(3, producto.getStock());
                stmtDetalle.setDouble(4, subtotal);
                stmtDetalle.executeUpdate();

                PreparedStatement stmtUpdateStock = ConexionDB.getPreparedStatement(queryUpdateStock);
                stmtUpdateStock.setInt(1, producto.getStock());
                stmtUpdateStock.setInt(2, producto.getId());
                stmtUpdateStock.executeUpdate();
            }

            ConexionDB.commit();
            System.out.println("Pedido agregado con exito.");
        } 
        catch (SQLException e) {
            ConexionDB.rollback();
            System.out.println("Error al agregar el pedido.");
            e.printStackTrace();
        } 
        finally {
            ConexionDB.activateAutoCommit();
        }
    }  
    
    public ArrayList<Pedido> listarPedido(){
        ArrayList<Pedido> listaPedidos = new ArrayList();
        String queryPedido = "SELECT p.*, SUM(dp.subtotal) " +
                             "FROM pedido p " +
                             "JOIN detalle_pedido dp ON p.id = dp.id_pedido " +
                             "GROUP BY dp.id_pedido";
        
        try {
            PreparedStatement stmt = ConexionDB.getPreparedStatement(queryPedido);
            
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                int idPedido = rs.getInt("id");
                Date fechaPedido = rs.getDate("fecha");
                String clientePedido = rs.getString("cliente");
                double subTotalPedido = rs.getDouble(4);
                Pedido pedido = new Pedido(idPedido, fechaPedido, clientePedido, subTotalPedido);
                
                listaPedidos.add(pedido);
            }
            
        } 
        catch (SQLException e) {
            System.out.println("Error al listar pedidos.");
            e.printStackTrace();
        }
        return listaPedidos;
    }
    
    public ArrayList<Pedido> listarDetallesPedidos(){
        ArrayList<Pedido> listaPedidos = new ArrayList();
        String queryPedido = "SELECT p.*, SUM(dp.subtotal) " +
                             "FROM pedido p " +
                             "JOIN detalle_pedido dp ON p.id = dp.id_pedido " +
                             "GROUP BY dp.id_pedido";
        String queryProducto = "SELECT p.id,p.nombre AS producto, dp.cantidad, dp.subtotal " +
                               "FROM detalle_pedido dp " +
                               "JOIN producto p ON dp.id_producto = p.id " +
                               "WHERE dp.id_pedido = ?";
        
        try {
            PreparedStatement stmt = ConexionDB.getPreparedStatement(queryPedido);
            
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                int idPedido = rs.getInt("id");
                Date fechaPedido = rs.getDate("fecha");
                String clientePedido = rs.getString("cliente");
                double subTotalPedido = rs.getDouble(4);
                
                ArrayList<Producto> listaProducto = new ArrayList();
                try{
                    PreparedStatement stmt2 = ConexionDB.getPreparedStatement(queryProducto);
                    stmt2.setInt(1, idPedido);
            
                    ResultSet rs2 = stmt2.executeQuery();
                    while(rs2.next()){
                        int idProducto = rs2.getInt("id");
                        String nombreProducto = rs2.getString("producto");
                        int cantidadProducto = rs2.getInt("cantidad");
                        double subTotalProducto = rs2.getDouble("subtotal");
                        Producto producto = new Producto(idProducto, nombreProducto, cantidadProducto, subTotalProducto);

                        listaProducto.add(producto);
                    }
                }
                catch (SQLException e) {
                    System.out.println("Error al listar productos de un pedido.");
                    e.printStackTrace();
                }
                listaPedidos.add(new Pedido(idPedido, fechaPedido, clientePedido, subTotalPedido,listaProducto));
            }          
        } 
        catch (SQLException e) {
            System.out.println("Error al listar detalles pedidos.");
            e.printStackTrace();
        }
        return listaPedidos;
    }
}
