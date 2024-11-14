/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ejercicio1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author ClaudissPerez
 */
public class ConexionMySQLBasica {
    private static final String URL = "http://localhost/phpmyadmin/index.php?route=/database/structure&db=ad_ej1.sql";
    private static final String USER = "2dam";
    private static final String PASSWORD = "2dam";
    
    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)){
            String sql = "SELECT * FROM cliente";
            try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)){
                System.out.println("Clientes:");
                System.out.println("ID\tNombre\tEmail\tCiudad\tTelefono");
                while (rs.next()){
                    int id = rs.getInt("id");
                    String nombre = rs.getString("nombre");
                    String email = rs.getString("email");
                    String ciudad = rs.getString("ciudad");
                    String telefono = rs.getString("telefono");
                    // %d numero %s string \t tabulador \n salto de linea
                    System.out.printf("%d\t%s\t%s\t%s\t%s%n", id, nombre, email, ciudad, telefono);
                }
            }
        }catch (SQLException e){
            System.out.println("Error al conectar a la base de datos");
            System.out.println(e.getLocalizedMessage());
        }
    }
}
