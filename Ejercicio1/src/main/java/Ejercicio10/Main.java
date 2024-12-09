/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ejercicio10;

import Ejercicio9.ConexionDB;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author ClaudissPerez
 */
public class Main {
    private static final String URL = "jdbc:mysql://localhost:3306/empresa_10";
    private static final String USER = "2dam";
    private static final String PASSWORD = "2dam";
    private static Connection conn;
    
    public static void main(String[] args) {
        conn = Conexion.iniciarConexion(URL, USER, PASSWORD);
        
        if(conn !=null){
            EmpleadoDAO empleadoDAO = new EmpleadoDAO(conn);
            EmpleadoProyectoDAO empleadoProyectoDAO = new EmpleadoProyectoDAO(conn);
            ProyectoDAO proyectoDAO = new ProyectoDAO(conn);
            Menu menu = new Menu(empleadoDAO, empleadoProyectoDAO, proyectoDAO);
            menu.iniciarMenu(); 
        }
        
        try{
            ConexionDB.finalizarConexion();
        }catch (SQLException e){
            System.err.println("Error al cerrar la conexión");
        }
        
    }
}
