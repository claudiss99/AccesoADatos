/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ejercicio10;

import java.sql.Connection;

/**
 *
 * @author ClaudissPerez
 */
public class EmpleadoProyectoDAO {
    private static Connection conn;
    
    public EmpleadoProyectoDAO(Connection conn) {
        this.conn = conn;
    }
}
