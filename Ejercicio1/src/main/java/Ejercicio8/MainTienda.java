/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ejercicio8;

import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author Claudiss
 */
public class MainTienda {
    private static final String URL = "jdbc:mysql://localhost:3306/tienda";
    private static final String USER = "2dam";
    private static final String PASSWORD = "2dam";
    private static Connection conn;

    public static void main(String[] args) {
        conn = ConexionDB.iniciarConexion(URL, USER, PASSWORD);
        if (conn != null) {
            PedidoDAO pedidoDAO = new PedidoDAO(conn);
            ProductoDAO productoDAO = new ProductoDAO(conn);
            MenuController menuController = new MenuController(pedidoDAO, productoDAO);
            menuController.iniciarMenu();
        }

        try {
            ConexionDB.finalizarConexion();
        } 
        catch (SQLException e) {
            System.out.println("Error al cerrar la conexión.");
        }
    }
}
