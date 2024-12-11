/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ejercicio10;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author ClaudissPerez
 */
public class EmpleadoProyectoDAO {
    private static Connection conn;
    
    public EmpleadoProyectoDAO(Connection conn) {
        this.conn = conn;
    }
    
    public void addEmpleadoProyecto(Empleado_proyecto empleadoProyecto){
        String query = "INSERT INTO empleado_proyecto (id_empleado, id_proyecto) VALUES (?,?)";
        
        try{
            PreparedStatement stmt = Conexion.getPreparedStatement(query);
            stmt.setInt(1, empleadoProyecto.getIdEmpleado());
            stmt.setInt(2, empleadoProyecto.getIdProyecto());
            
            stmt.executeUpdate();
            System.out.println("Se ha insertado el empleado al proyecto correctamente");
        }catch(SQLException e){
            System.err.println("Error al insertar el empleado en el proyecto");
        }
    }
    
    public void addEmpleadosProyecto(int idProyect, String[] empleados){
        String query= "INSERT INTO empleado_proyecto (id_empleado, id_proyecto) VALUES (?,?)";
        
        try{
            Conexion.deactivateAutoCommit();
            PreparedStatement stmt = Conexion.getPreparedStatement(query);
            
            for(String e: empleados){
                stmt.setInt(1, Integer.valueOf(e));
                stmt.setInt(2, idProyect);
                stmt.addBatch();
            }
            
            stmt.executeBatch();
            Conexion.commit();
            System.out.println("Empleados insertados correctamente al proyecto");
        }catch(SQLException e){
            Conexion.rollback();
            System.err.println("Error al añadir empleados al proyecto");
        }finally{
            Conexion.activateAutoCommit();
        }
    }
    
    public void deleteEmpleadoFromProyect(Empleado_proyecto empleadoProyecto){
        String query = "DELETE FROM empleado_proyecto WHERE id_empleado =? AND id_proyecto=?";
        
        try{
            PreparedStatement stmt = Conexion.getPreparedStatement(query);
            stmt.setInt(1, empleadoProyecto.getIdEmpleado());
            stmt.setInt(2, empleadoProyecto.getIdProyecto());
            stmt.executeUpdate(query);
            
            System.out.println("Empleado eliminada con exito");
            
        }catch(SQLException e){
            System.err.println("Error al eliminar empleado del proyecto");
        }
        
    }
}
