/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ejercicio7;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author ClaudissPerez
 */
public class Conexion {
    private static Connection conn;
    
    public static Connection iniciarConexion(String url, String user, String password){
        try{
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Conexion establecida con exito");
        }catch(SQLException e){
            System.err.println("Error al conectarse con la base de datos "+e.getLocalizedMessage());
        }
        return conn;
    }
    
    public static void finalizarConexion() throws SQLException{
        if (conn!=null && !conn.isClosed()){
            conn.close();
            System.out.println("Conexion cerrada con exito");
        }
    }
    
    public static PreparedStatement getPreparedStatement (String sql) throws SQLException{
        return  conn.prepareStatement(sql);
    }
}
