/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ejercicio7;

import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author ClaudissPerez
 */
public class Main {
    private static final String URL = "jdbc:mysql://localhost:3306/universidad";
    private static final String USER = "2dam";
    private static final String PASSWORD = "2dam";
    private static Connection conn;
    
    public static void main(String[] args) {
        conn= Conexion.iniciarConexion(URL, USER, PASSWORD);
        if (conn!= null){
            AsignaturaDAO asignaturaDAO = new AsignaturaDAO(conn);
            EstudianteDAO estudianteDAO = new EstudianteDAO(conn);
            MatriculaDAO matriculaDAO = new MatriculaDAO(conn);
            Menu menu = new Menu(estudianteDAO, asignaturaDAO, matriculaDAO);
            
            menu.iniciarMenu();
        }
        
        try{
            Ejercicio6.Conexion.finalizarConexion();
        }catch(SQLException e){
            System.err.println("Error al cerrar conexion");
        }
    }
}
