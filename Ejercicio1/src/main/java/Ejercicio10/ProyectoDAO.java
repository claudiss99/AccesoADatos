/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ejercicio10;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author ClaudissPerez
 */
public class ProyectoDAO {
    private static Connection conn;
    
    public ProyectoDAO(Connection conn) {
        this.conn = conn;
    }
    
    public void findProyectoByID(int id){
        String query = "SELECT * FROM proyecto WHERE id =?";
        
        try{
            PreparedStatement stmt = Conexion.getPreparedStatement(query);
            stmt.setInt(1, id);
            
            ResultSet rs =stmt.executeQuery();
            if(rs.next()){
                System.out.println("proyecto "+id+": verificado");
            }
        }catch(SQLException e){
            System.out.println("Error al verificar empleado");
        }
    }
    
    public void addProyectoEmple(String nombreProyecto, Date fechaInicio, Date fechaFin, String[] empleados){
        String queryProyecto = "INSERT INTO proyecto (nombre, fecha_inicio, fecha_fin) VALUES (?,?,?)";
        
        try{
            Conexion.deactivateAutoCommit();
            
            PreparedStatement stmtProyecto = Conexion.getPreparedStatementGeneratedKeys(queryProyecto);
            stmtProyecto.setString(1, nombreProyecto);
            stmtProyecto.setDate(2, fechaInicio);
            stmtProyecto.setDate(3, fechaFin);
            stmtProyecto.executeUpdate();
            
            ResultSet rs = stmtProyecto.getGeneratedKeys();
            int idProyecto =0;
            if(rs.next()){
                idProyecto= rs.getInt(1);
            }
            
            String queryVincular = "INSERT INTO empleado_proyecto (id_empleado, id_proyecto) VALUES (?,?)";
            
            PreparedStatement stmtVincular = Conexion.getPreparedStatement(queryVincular);
        
            for(String e: empleados){
                int idEmpleado = Integer.valueOf(e);
                
                
                stmtVincular.setInt(1, idEmpleado);
                stmtVincular.setInt(2, idProyecto);
                stmtVincular.addBatch();
                
            }
            stmtVincular.executeBatch();
            Conexion.commit();
            System.out.println("Proyecto agregado con exito");
        }catch(SQLException e){
            Conexion.rollback();
            System.err.println("Error al añadir proyecto");
        }finally{
            Conexion.activateAutoCommit();
        }
    }
    
    public void addProyecto(Proyecto proyecto){
        String query= "INSERT INTO proyecto (nombre, fecha_inicio, fecha_fin) VALUES (?,?,?)";
        
        try{
            PreparedStatement stmt = Conexion.getPreparedStatement(query);
            stmt.setString(1, proyecto.getNombre());
            stmt.setDate(2, proyecto.getFecha_inicio());
            stmt.setDate(3, proyecto.getFecha_fin());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            int idProyecto =0;
            if(rs.next()){
                idProyecto= rs.getInt(1);
                System.out.println("Se ha creado el proyecto con ID: "+idProyecto);
            }
            
        }catch(SQLException e){
            System.err.println("Error al insertar proyecto");
        }
    }
    
    public void modProyecto(Proyecto proyecto){
        String query = "UPDATE proyecto SET nombre =?, fecha_inicio=?, fecha_fin=? WHERE id=?";
        
        try{
            PreparedStatement stmt = Conexion.getPreparedStatement(query);
            stmt.setString(1, proyecto.getNombre());
            stmt.setDate(2, proyecto.getFecha_inicio());
            stmt.setDate(3, proyecto.getFecha_fin());
            stmt.setInt(4, proyecto.getId());
            
            if(stmt.executeUpdate()>0){
                System.out.println("Se ha modificado el proyecto con exito");
            }else{
                System.err.println("No se ha podido modificar el proyecto");
            }
            
        }catch(SQLException e){
            System.err.println("Error al modificar el proyecto");
        }
    }
    
    public void listarProyectosFuturos(){
        //Duda con fecha_inicio posterior a hoy
        String query="SELECT * FROM proyecto WHERE fecha_inicio>CURRENT DATE";
        System.out.println("id Proyecto, nombre, fecha Inicio, fecha Fin");
        try{
            PreparedStatement stmt = Conexion.getPreparedStatement(query);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                Date fechaInicio = rs.getDate("fecha_inicio");
                Date fechaFin = rs.getDate("fecha_fin");
                
                System.out.println(id+", "+nombre+", "+fechaInicio+", "+fechaFin);
            }
        }catch(SQLException e){
            System.err.println("Error al listar proyectos futuros");
        }
    }
    
    public void listarProyectosPasados(){
        //Duda con fecha_fin anterior a hoy
        String query="SELECT * FROM proyecto WHERE fecha_fin<CURRENT DATE";
        System.out.println("id Proyecto, nombre, fecha Inicio, fecha Fin");
        try{
            PreparedStatement stmt = Conexion.getPreparedStatement(query);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                Date fechaInicio = rs.getDate("fecha_inicio");
                Date fechaFin = rs.getDate("fecha_fin");
                
                System.out.println(id+", "+nombre+", "+fechaInicio+", "+fechaFin);
            }
        }catch(SQLException e){
            System.err.println("Error al listar proyectos pasados");
        }
    }
    
    public void listarProyectosActivos(){
        String query="SELECT * FROM proyecto WHERE CURRENT DATE BETWEEN fecha_inicio AND fecha_fin";
        System.out.println("id Proyecto, nombre, fecha Inicio, fecha Fin");
        try{
            PreparedStatement stmt = Conexion.getPreparedStatement(query);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                Date fechaInicio = rs.getDate("fecha_inicio");
                Date fechaFin = rs.getDate("fecha_fin");
                
                System.out.println(id+", "+nombre+", "+fechaInicio+", "+fechaFin);
            }
        }catch(SQLException e){
            System.err.println("Error al listar proyectos activos");
        }
    }
    
    
}
