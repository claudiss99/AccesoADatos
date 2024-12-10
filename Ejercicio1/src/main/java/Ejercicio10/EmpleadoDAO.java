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
import java.util.ArrayList;

/**
 *
 * @author ClaudissPerez
 */
public class EmpleadoDAO {
    private static Connection conn;

    public EmpleadoDAO(Connection conn) {
        this.conn = conn;
    }
    
    public void findEmpleadoByID(int id){
        String query = "SELECT * FROM empleado WHERE id =?";
        
        try{
            PreparedStatement stmt = Conexion.getPreparedStatement(query);
            stmt.setInt(1, id);
            
            ResultSet rs =stmt.executeQuery();
            if(rs.next()){
                System.out.println("Empleado "+id+": verificado");
            }
        }catch(SQLException e){
            System.out.println("Error al verificar empleado");
        }
    }

    public void addEmpleados(Empleado empleado){
        String query= "SELECT id FROM empleado WHERE dni=?";
        
        try{
            PreparedStatement stmt= Conexion.getPreparedStatement(query);
            stmt.setString(1, empleado.getDni());
            
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                //UPDATE (No actualizar el dni) Falta poner la fecha de finalizacion a null
                String query2= "UPDATE empleado SET nombre=?, dni=?, departamento=?, sueldo=?, fecha_contratacion=?, fecha_finalizacion=NULL WHERE id=? AND fecha_finalizacion !=NULL";
                
                try{
                    PreparedStatement stmt2= Conexion.getPreparedStatement(query2);
                    stmt2.setString(1, empleado.getNombre());
                    stmt2.setString(2, empleado.getDni());
                    stmt2.setString(3, empleado.getDepartamento());
                    stmt2.setDouble(4, empleado.getSueldo());
                    stmt2.setDate(5, empleado.getFechaContratacion());
                    stmt2.setInt(7, empleado.getId());
                    
                    int filas = stmt2.executeUpdate();
                    if(filas == 0){
                        System.out.println("Comprueba que el usuario no este activo");
                    }else{
                        System.out.println("Se ha actualizado el empelado en la opcion añadir");
                    }
                    
                 
                }catch(SQLException e){
                    System.err.println("Error al actualizar empleado en la opcion añadir");
                }
            }else{
                //INSERT
                String query2= "INSERT INTO empleado (nombre, dni, departamento, sueldo, fecha_contratacion) VALUES (?,?,?,?,?)";
                
                try{
                    PreparedStatement stmt2= Conexion.getPreparedStatement(query2);
                    stmt2.setString(1, empleado.getNombre());
                    stmt2.setString(2, empleado.getDni());
                    stmt2.setString(3, empleado.getDepartamento());
                    stmt2.setDouble(4, empleado.getSueldo());
                    stmt2.setDate(5, empleado.getFechaContratacion());
                    
                    stmt2.executeUpdate();

                    System.out.println("Se ha insertado el empleado en la opcion añadir");
                 
                }catch(SQLException e){
                    System.err.println("Error al añadir empleado en la opcion añadir");
                }
                
            }
        }catch(SQLException e){
            System.err.println("Error al seleccionar empleado");
        }
    }
    
    
    public void modEmpleado(Empleado empleado){
        String query = "UPDATE empleado SET nombre =?, departamento=?, sueldo=? WHERE id=?";
        
        try{
            PreparedStatement stmt = Conexion.getPreparedStatement(query);
            stmt.setString(1, empleado.getNombre());
            stmt.setString(2, empleado.getDepartamento());
            stmt.setDouble(3, empleado.getSueldo());
            stmt.setInt(4, empleado.getId());
            
            if(stmt.executeUpdate()>0){
                System.out.println("Se ha modificado empleado con exito");
            }else{
                System.err.println("No se ha podido modificar el empleado");
            }
            
        }catch(SQLException e){
            System.err.println("Error al modificar empleado");
        }
    }
    
    public void despedirEmpleado(Empleado empleado){
        String query = "UPDATE empleado SET fecha_finalizacion=? WHERE id =?";
        
        try{
            PreparedStatement stmt = Conexion.getPreparedStatement(query);
            stmt.setDate(1, empleado.getFechaFinalizacion());
            stmt.setInt(2, empleado.getId());
            
            if(stmt.executeUpdate()>0){
                System.out.println("Se ha despedido el empleado con exito");
            }else{
                System.out.println("No se ha podido despedir al empleado");
            }
        }catch(SQLException e){
            System.err.println("Error al despedir empleado");
        }
    }
    
    public ArrayList<Empleado> listarEmpleadosActivos(){
        ArrayList<Empleado> empleados = new ArrayList<>();
        
        String query = "SELECT id, nombre, dni, departamento, sueldo, fecha_contratacion FROM  empleado WHERE fecha_finalizacion=null";
        
        try{
            PreparedStatement stmt = Conexion.getPreparedStatement(query);
            
            ResultSet rs= stmt.executeQuery();
            
            while(rs.next()){
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String dni = rs.getString("dni");
                String departamento = rs.getString("departamento");
                int sueldo = rs.getInt("sueldo");
                Date fechaContratacion = rs.getDate("fecha_contratacion");
                
                Empleado empleado = new Empleado(id, nombre, dni, departamento, sueldo, fechaContratacion);
                empleados.add(empleado);
            }
            
        }catch(SQLException e){
            System.err.println("Error al listar empleados activos");
        }
        return empleados;
    }
    
    public ArrayList<Empleado> listarEmpleadosDespedidos(){
        ArrayList<Empleado> empleados = new ArrayList<>();
        
        String query = "SELECT id, nombre, dni, departamento, sueldo, fecha_contratacion, fecha_finalizacion FROM  empleado WHERE fecha_finalizacion!=null";
        
        try{
            PreparedStatement stmt = Conexion.getPreparedStatement(query);
            
            ResultSet rs= stmt.executeQuery();
            
            while(rs.next()){
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String dni = rs.getString("dni");
                String departamento = rs.getString("departamento");
                int sueldo = rs.getInt("sueldo");
                Date fechaContratacion = rs.getDate("fecha_contratacion");
                Date fechaFinalizacion = rs.getDate("fecha_finalizacion");
                
                Empleado empleado = new Empleado(id, nombre, dni, departamento, sueldo, fechaContratacion, fechaFinalizacion);
                empleados.add(empleado);
            }
            
        }catch(SQLException e){
            System.err.println("Error al listar empleados activos");
        }
        return empleados;
    }
    
}
