/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.orhncompany.ejercicio5_empleados;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Juan Antonio
 */
public class EmpleadoDAO {
    private static Connection conn;

    public EmpleadoDAO(Connection conn) {
        this.conn = conn;
    }
    
    public static ArrayList<Empleado> ListarEmpleados(){
        String query = "SELECT * FROM empleado";
        ArrayList<Empleado> listaEmpleados = new ArrayList();
        try{
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String puesto = rs.getString("puesto");
                double salario = rs.getDouble("salario");
                Date fechaIngreso = rs.getDate("fecha_ingreso");
                listaEmpleados.add(new Empleado(id,nombre,puesto,salario,fechaIngreso));
            }
        }
        catch (SQLException e) {
            System.out.println("Error al listar clientes.");
        }
        return listaEmpleados;
    }
    
    public static void addEmpleado(Empleado empleado){
        String query = "INSERT INTO empleado (nombre, puesto, salario, fecha_ingreso) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, empleado.getNombre());
            stmt.setString(2, empleado.getPuesto());
            stmt.setDouble(3, empleado.getSalario());
            stmt.setDate(4, empleado.getFechaIngreso());
            stmt.executeUpdate();
        } 
        catch (SQLException e) {
            System.out.println("Error al agregar empleado.");
            e.printStackTrace();
        }
    }
    
    public static void modEmpleado(Empleado empleado){
        String query = "UPDATE empleado SET nombre = ?, puesto = ?, salario = ?, fecha_ingreso = ? WHERE id = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, empleado.getNombre());
            stmt.setString(2, empleado.getPuesto());
            stmt.setDouble(3, empleado.getSalario());
            stmt.setDate(4, empleado.getFechaIngreso());
            stmt.setInt(5, empleado.getId());
            stmt.executeUpdate();
        } 
        catch (SQLException e) {
            System.out.println("Error al modificar empleado.");
            e.printStackTrace();
        }
    }
    
    public static void deleteEmpleado(Empleado empleado){
        String query = "DELETE FROM empleado WHERE id = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, empleado.getId());
            stmt.executeUpdate();
        } 
        catch (SQLException e) {
            System.out.println("Error al eliminar empleado.");
            e.printStackTrace();
        }
    }
    
    public static Empleado findEmpleadoById(int id){
        String query = "SELECT * FROM empleado WHERE id = ?";
        Empleado empleado = null;
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                int idEmpleado = rs.getInt("id");
                String nombreEmpleado = rs.getString("nombre");
                String puestoEmpleado = rs.getString("puesto");
                double salarioEmpleado = rs.getDouble("salario");
                Date fechaIngresoEmpleado = rs.getDate("fecha_ingreso");
                empleado = new Empleado(idEmpleado,nombreEmpleado,puestoEmpleado,salarioEmpleado,fechaIngresoEmpleado);
            }
        } 
        catch (SQLException e) {
            System.out.println("Error al eliminar empleado.");
            e.printStackTrace();
        }
        return empleado;
    }
}
