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
public class EstudianteDAO {
    public static Connection conn;

    public EstudianteDAO(Connection conn) {
        this.conn = conn;
    }
    
    
    public void addEstudiante (Estudiante estudiante){
        String query = "INSERT INTO estudiante(nombre, email, telefono, direccion) VALUES (?,?,?,?)";
        
        try{
            PreparedStatement stmt= Conexion.getPreparedStatement(query);
            stmt.setString(1, estudiante.getNombre());
            stmt.setString(2, estudiante.getEmail());
            stmt.setString(3, estudiante.getTelefono());
            stmt.setString(4, estudiante.getDireccion());
            
            stmt.executeUpdate();
            System.out.println("Estudiante agregado con exito");
        }catch(SQLException e){
            System.err.println("Error al añadir estudiante "+e.getLocalizedMessage());
        }
    }
    
    public Estudiante findEstudianteByEmail(String email){
        String query = "SELECT * FROM estudiante WHERE email LIKE ?";
        Estudiante estudiante = null;
        
        try{
            PreparedStatement stmt = Conexion.getPreparedStatement(query);
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()){
                int idEstudiante = rs.getInt("id");
                String nombreEstudiante = rs.getString("nombre");
                String emailEstudiante = rs.getString("email");
                String telefonoEstudiante = rs.getString("telefono");
                String direccionEstudiante = rs.getString("direccion");
                
                estudiante = new Estudiante(idEstudiante, nombreEstudiante, emailEstudiante, telefonoEstudiante, direccionEstudiante);  
            }
        }catch(SQLException e){
            System.err.println("Error al encontrar estudiante");
        }
        return estudiante;
    }
}
