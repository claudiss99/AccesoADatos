/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.orhncompany.ejercicio5_empleados;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Juan Antonio
 */
public class Main {
    private static final String URL = "jdbc:mysql://localhost:3306/empresa_ej5";
    private static final String USER = "2dam";
    private static final String PASSWORD = "2dam";
    private static int respuesta;
    private static Connection conn;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        conn = ConexionDB.iniciarConexion(URL, USER, PASSWORD);
        EmpleadoDAO empleadoDAO = new EmpleadoDAO(conn);
        
        System.out.println("1 --> Listar empleados\n2 --> Add Empleado\n3 --> Mod Empleado\n4 --> Delete Empleado\n5 --> Salir ");

        do {
            respuesta = sc.nextInt();
            sc.nextLine();
            switch (respuesta) {
                case 1:
                    ArrayList<Empleado> listaEmpleados = empleadoDAO.ListarEmpleados();
                    for(Empleado empleado:listaEmpleados){
                        System.out.println("ID: " + empleado.getId() + "\nNombre: " + empleado.getNombre() + "\nPuesto: " + empleado.getPuesto() + "\nSalario: " + empleado.getSalario() + "\nFecha de Ingreso: " + empleado.getFechaIngreso() + "\n-----------------");
                    }
                    break;
                case 2:
                    System.out.println("Dime el nombre del empleado que vas a agregar");
                    String nombre = sc.nextLine();
                    System.out.println("Dime el puesto que le vas a asignar");
                    String puesto = sc.nextLine();
                    System.out.println("Dime el salario que va a tener");
                    String salarioLeer = sc.nextLine();
                    double salario = Double.parseDouble(salarioLeer);
                    System.out.println("Dime la fecha en la que ingresara(Formato: AA-MM-DD)");
                    String fechaLeer = sc.nextLine();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        
                    Date fechaIngreso = null;
                    try {
                        java.util.Date utilDate = dateFormat.parse(fechaLeer);
                        fechaIngreso = new Date(utilDate.getTime());
                    } 
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                    
                    empleadoDAO.addEmpleado(new Empleado(0,nombre,puesto,salario,fechaIngreso));
                    System.out.println("Empleado agregado con exito");
                    break;
                case 3:
                    System.out.println("Dime el id del cliente que vas a modificar");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Dime el nuevo nombre del empleado");
                    nombre = sc.nextLine();
                    System.out.println("Dime el nuevo puesto que le vas a asignar");
                    puesto = sc.nextLine();
                    System.out.println("Dime el nuevo salario que va a tener");
                    salarioLeer = sc.nextLine();
                    salario = Double.parseDouble(salarioLeer);
                    System.out.println("Dime la nueva fecha en la que ingresara(Formato: AA-MM-DD)");
                    fechaLeer = sc.nextLine();
                    dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        
                    fechaIngreso = null;
                    try {
                        java.util.Date utilDate = dateFormat.parse(fechaLeer);
                        fechaIngreso = new Date(utilDate.getTime());
                    } 
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                    empleadoDAO.modEmpleado(new Empleado(id,nombre,puesto,salario,fechaIngreso));
                    System.out.println("Empleado modificado con exito");
                    break;
                case 4:
                    System.out.println("Dime el id del cliente que vas a eliminar");
                    id = sc.nextInt();
                    sc.nextLine();
                    Empleado empleadoAEliminar = EmpleadoDAO.findEmpleadoById(id);
                    empleadoDAO.deleteEmpleado(empleadoAEliminar);
                    System.out.println("Empleado eliminado con exito");
                    break;
                case 5:
                    System.out.println("Saliendo...");
                    break;               
                default:
                    System.out.println("Introducido respuesta incorrecta");
                    break;
            }
        } while (respuesta != 5);

        try {
            ConexionDB.finalizarConexion();
        } 
        catch (SQLException e) {
            System.out.println("Error al cerrar la conexión.");
        }
    }
}
