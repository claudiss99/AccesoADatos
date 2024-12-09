/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ejercicio6;

import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author ClaudissPerez
 */
public class main {
    private static final String URL = "jdbc:mysql://localhost:3306/peliculasdb";
    private static final String USER = "2dam";
    private static final String PASSWORD = "2dam";
    private static Connection conn;
    
    public static void main(String[] args) {
        conn= Conexion.iniciarConexion(URL, USER, PASSWORD);
        if (conn!= null){
            PeliculaDAO peliculaDAO = new PeliculaDAO(conn);
            Menu menu = new Menu(peliculaDAO);
            menu.iniciarMenu();
        }
        
        try{
            Conexion.finalizarConexion();
        }catch(SQLException e){
            System.err.println("Error al cerrar conexion");
        }
    }
}
