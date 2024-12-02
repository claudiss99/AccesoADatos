/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ejercicio2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author ClaudissPerez
 */
public class Conexion {
    private static final String URL = "jdbc:mysql://localhost:3306/ejercicio_primero";
    private static final String USER = "2dam";
    private static final String PASSWORD = "2dam";
    
    private static Connection conexion = null;
    
    public Conexion() throws SQLException{
        getConexion();
    }
    
    private static Connection getConexion() throws SQLException{
        if(conexion == null){
            conexion = DriverManager.getConnection(URL, USER, PASSWORD);
        }
        return conexion;
    }
    
    protected PreparedStatement getPreparedStament(String sql) throws SQLException{
        return getConexion().prepareStatement(sql);
    }
    
    public void close() throws SQLException{
        getConexion().close();
    }
}
