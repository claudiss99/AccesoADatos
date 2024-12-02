/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.orhncompany.ejercicio5_empleados;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Juan Antonio
 */
public class ConexionDB {
    private static Connection conn;
    
    public static Connection iniciarConexion(String url, String user, String password) {
        try {
            conn = DriverManager.getConnection(url, user, password);
        } 
        catch (SQLException e) {
            System.out.println("Error al conectarse a la base de datos.");
            System.out.println(e.getLocalizedMessage());
        }
        return conn;
    }
    
    public static void finalizarConexion() throws SQLException {
        if (conn != null) {
            conn.close();
        }
    }
}
