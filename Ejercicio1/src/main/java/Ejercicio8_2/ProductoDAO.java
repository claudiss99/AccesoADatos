/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ejercicio8_2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author ClaudissPerez
 */
public class ProductoDAO {
    private static Connection conn;
    
    public ProductoDAO(Connection conn) {
        this.conn = conn;
    }
    
    public Producto obtenerProductoPorId(int idProducto) {
    
        return null;
    
    }
    
    public void addProducto(Producto producto){
       String query = "INSERT INTO producto (nombre, stock, precio) VALUES (?,?,?)";
       
       try{
           PreparedStatement stmt = ConexionDB.getPreparedStatement(query);
           
           stmt.setString(1, producto.getNombre());
           stmt.setInt(2, producto.getStock());
           stmt.setDouble(3, producto.getPrecio());
           
           stmt.executeUpdate();
           
           System.out.println("Fila insertada con exito");
       }catch(SQLException e){
           System.err.println("Error al añadir producto");
       }
    } 
    
    public ArrayList<Producto> listarProductos(){
        ArrayList<Producto> productos = new ArrayList<>();
        String query = "SELECT * FROM producto";
        
        try{
            PreparedStatement stmt = ConexionDB.getPreparedStatement(query);
            
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                String nombre= rs.getString("nombre");
                int stock = rs.getInt("stock");
                double precio = rs.getDouble("precio");
                
                Producto producto = new Producto(id, nombre, stock, precio);
                productos.add(producto);
            }
        }catch(SQLException e){
            System.err.println("Error al listar productos");
        }
        return productos;
        
    }
}
