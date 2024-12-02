/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ejercicio8;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Claudiss
 */
public class ProductoDAO {
    private static Connection conn;
    
    public ProductoDAO(Connection conn) {
        this.conn = conn;
    }
    
    public Producto obtenerProductoPorId(int idProducto) {
    String query = "SELECT * FROM producto WHERE id = ?";
    Producto producto = null;
    try {
        PreparedStatement stmt = ConexionDB.getPreparedStatement(query);
        stmt.setInt(1, idProducto);
        
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            String nombre = rs.getString("nombre");
            int stock = rs.getInt("stock");
            double precio = rs.getDouble("precio");
            producto = new Producto(idProducto, nombre, stock, precio);
        }
    } 
    catch (SQLException e) {
        System.out.println("Error al obtener el producto");
        e.printStackTrace();
    }
    return producto;
}
    
    public void addProducto(Producto producto){
        String query = "INSERT INTO producto (nombre, stock, precio) VALUES (?, ?, ?)";
        try {
            PreparedStatement stmt = ConexionDB.getPreparedStatement(query);
            stmt.setString(1, producto.getNombre());
            stmt.setInt(2, producto.getStock());
            stmt.setDouble(3, producto.getPrecio());
            stmt.executeUpdate();
            System.out.println("Produto agregado con exito.");
        } 
        catch (SQLException e) {
            System.out.println("Error al agregar producto.");
            e.printStackTrace();
        }
    } 
    
    public ArrayList<Producto> listarProductos(){
        ArrayList<Producto> listaProductos = new ArrayList();
        String query = "SELECT * FROM producto";
        try {
            PreparedStatement stmt = ConexionDB.getPreparedStatement(query);
            
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                int idProducto = rs.getInt("id");
                String nombreProducto = rs.getString("nombre");
                int stockProducto = rs.getInt("stock");
                double precioProducto = rs.getDouble("precio");
                Producto producto = new Producto(idProducto, nombreProducto, stockProducto, precioProducto);
                
                listaProductos.add(producto);
            }
            
        } 
        catch (SQLException e) {
            System.out.println("Error al listar productos.");
            e.printStackTrace();
        }
        return listaProductos;
    }
}
