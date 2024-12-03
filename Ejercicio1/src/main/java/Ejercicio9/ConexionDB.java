/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ejercicio9;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author ClaudissPerez
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
    
    public static PreparedStatement getPreparedStatement(String sql) throws SQLException {
        return conn.prepareStatement(sql);
    }
    
    public static PreparedStatement getPreparedStatementGeneratedKeys(String sql) throws SQLException {
        return conn.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
    }
    
    public static void commit() {
        try {
            conn.commit();
        } 
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void activateAutoCommit() {
        try {
            conn.setAutoCommit(true);
        } 
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void deactivateAutoCommit() {
        try {
            conn.setAutoCommit(false);
        } 
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void rollback() {
        try {
            conn.rollback();
        } 
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
