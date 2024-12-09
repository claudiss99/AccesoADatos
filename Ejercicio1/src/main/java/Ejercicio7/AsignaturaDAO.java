/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ejercicio7;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author ClaudissPerez
 */
public class AsignaturaDAO {
    public static Connection conn;
    
    public AsignaturaDAO( Connection conn){
        this.conn = conn;
    }
    
    public void addAsignatura(Asignatura asignatura){
        String query = "INSERT INTO asignatura (nombre, curso, horas) VALUES (?,?,?)";
        
        try{
            PreparedStatement stmt = Conexion.getPreparedStatement(query);
            stmt.setString(1, asignatura.getNombre());
            stmt.setString(2, asignatura.getCurso());
            stmt.setInt(3, asignatura.getHoras());
            
            stmt.executeUpdate();
            System.out.println("Asignatura agregada con exito");
        }catch(SQLException e){
            System.err.println("Error al añadir asignatura "+e.getLocalizedMessage());
        }
    }
    
    public Asignatura findAsignaturaByName(String nombre){
        String query = "SELECT * FROM asignatura WHERE nombre LIKE ?";
        Asignatura asignatura = null;
        try{
            PreparedStatement stmt = Conexion.getPreparedStatement(query);
            stmt.setString(1, nombre);
            ResultSet rs = stmt.executeQuery();
            
            if(rs.next()){
                int idAsignatura = rs.getInt("id");
                String nombreAsignatura = rs.getString("nombre");
                String cursoAsignatura = rs.getString("curso");
                int horasAsignatura = rs.getInt("horas");
                
                asignatura = new Asignatura(idAsignatura, nombreAsignatura, cursoAsignatura, horasAsignatura);
            }
        }catch(SQLException e){
            System.err.println("Error al encontrar asigntura");
        }
        
        return asignatura;
    }
    

}
