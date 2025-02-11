/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.ejercicio5empleadoshibernate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Claudia
 */
public class Ejercicio5EmpleadosHibernate {
    private static final Scanner sc = new Scanner(System.in);
    
    public static void main(String[] args) {
        String opc;
        do {
            opc = getMenuOption();
            switch (opc) {
                case "1":
                    addEmpleado();
                    break;
                case "2":
                    updateEmpleado();
                    break;
                case "3":
                    deleteEmpleado();
                    break;
                case "4":
                    listEmpleadoActive();
                    break;
                case "5":
                    listEmpleadoDes();
                    break;
                case "6":
                    addProyect();
                    break;
                case "7":
                    addproyectEmple();
                    break;
                case "8":
                    updateProyect();
                    break;
                case "9":
                    addEmpleToProyect();
                    break;
                case "10":
                    addEmplesToProyect();
                    break;
                case "11":
                    deleteEmpleFromProyect();
                    break;
                case "12":
                    listFututeProyect();
                    break;
                case "13":
                    listPastProyect();
                    break;
                case "14":
                    listActiveProyect();
                    break;
                case "15":
                    listDetalleProyecto();
                    break;

            }
        } while (!opc.equals("16"));
    }
    
    private static String getMenuOption() {
        System.out.println("\n Introduce una opción:");
        System.out.println("1. Añadir empleado");
        System.out.println("2. Modificar empleado");
        System.out.println("3. Despedir empleado");
        System.out.println("4. Listar empleados activos");
        System.out.println("5. Listar empleados despedidos");
        System.out.println("6. Añadir proyecto");
        System.out.println("7. Añadir un proyecto con empleados");
        System.out.println("8. Modificar un proyecto");
        System.out.println("9. Añadir empleado a proyecto");
        System.out.println("10. Añadir varios empleados a un proyecto");
        System.out.println("11. Eliminar un empleado de un proyecto");
        System.out.println("12. Listar proyectos futuros");
        System.out.println("13. Listar proyectos pasados");
        System.out.println("14. Listar proyectos activos");
        System.out.println("15. Listar detalles de un proyecto");
        System.out.println("16. Salir");
        
        return sc.nextLine();
    }
    
    private static void addEmpleado(){
        System.out.println("Escribe el nombre: ");
        String nombre = sc.nextLine();
        System.out.println("Escribe el dni: ");
        String dni = sc.nextLine();
        System.out.println("Escribe el departamento: ");
        String departamento = sc.nextLine();
        System.out.println("Escribe el sueldo: ");
        double sueldo = Double.valueOf(sc.nextLine());
        System.out.println("Escribe la fecha de contratacion: ");
        String fechaContratacion = sc.nextLine();
        
        Empleado empleado = new Empleado(0, nombre, dni, departamento, sueldo, fechaContratacion);
        EmpleadoDAO.addEmpleado(empleado);
    }
    
    private static void updateEmpleado(){
        System.out.println("Escribe el id: ");
        int id = Integer.valueOf(sc.nextLine());
        System.out.println("Escribe el nombre: ");
        String nombre = sc.nextLine();
        System.out.println("Escribe el departamento: ");
        String departamento = sc.nextLine();
        System.out.println("Escribe el sueldo: ");
        double sueldo = Double.valueOf(sc.nextLine());
        
        EmpleadoDAO.updateEmpleado(nombre, departamento, sueldo, id);
    }
    
    private static void deleteEmpleado(){
     
        System.out.println("Escribe el id: ");
        int id = Integer.valueOf(sc.nextLine());
        System.out.println("Escribe fecha de finalización: ");
        String fechaFinalizacion = sc.nextLine();
        
        EmpleadoDAO.deleteEmpleado(id, fechaFinalizacion);
    }
    
    private static void listEmpleadoActive(){
        ArrayList<Empleado> empleados = EmpleadoDAO.listEmpleadoActive();
        System.out.println("Lista de empleados activos: ");
        for (Empleado e: empleados){
            System.out.println("ID: "+e.getId() + "Nombre: "+e.getNombre()+"DNI: "+e.getDni()+" Departamento: "+e.getDepartamento()+" Sueldo: "+e.getSueldo()+"Fecha de contratación: "+e.getFechaContratacion());
        }
    }
    
    private static void listEmpleadoDes(){
        ArrayList<Empleado> empleados = EmpleadoDAO.listEmpleadoActive();
        System.out.println("Lista de empleados despedidos: ");
        for (Empleado e: empleados){
            System.out.println("ID: "+e.getId() + "Nombre: "+e.getNombre()+"DNI: "+e.getDni()+" Departamento: "+e.getDepartamento()+" Sueldo: "+e.getSueldo()+"Fecha de contratación: "+e.getFechaContratacion()+"Fecha de despido: "+e.getFechaFinalizacion());
        }
    }
    
    private static void addProyect(){
        
        System.out.println("Escribe el nombre: ");
        String nombre = sc.nextLine();
        System.out.println("Escribe la fecha de inicio: ");
        String fechaInicio = sc.nextLine();
        System.out.println("Escribe la fecha de fin: ");
        String fechaFin = sc.nextLine();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
   
        try {
            Date fecha1 = format.parse(fechaInicio);
            Date fecha2 = format.parse(fechaFin); 
            while (fecha1.after(fecha2)){
                System.out.println("Escribe la fecha de inicio: ");
                fechaInicio = sc.nextLine();
                System.out.println("Escribe la fecha de fin: ");
                fechaFin = sc.nextLine();
            }
        } catch (ParseException ex) {
            Logger.getLogger(Ejercicio5EmpleadosHibernate.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Proyecto proyecto = new Proyecto(0, nombre, fechaInicio, fechaFin);
        ProyectoDAO.addProyect(proyecto);
        
    }
    
    private static void addproyectEmple(){
        
        //Pedir datos del proyecto y comprobamos la fecha
        System.out.println("Escribe el nombre: ");
        String nombre = sc.nextLine();
        System.out.println("Escribe la fecha de inicio: ");
        String fechaInicio = sc.nextLine();
        System.out.println("Escribe la fecha de fin: ");
        String fechaFin = sc.nextLine();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
   
        try {
            Date fecha1 = format.parse(fechaInicio);
            Date fecha2 = format.parse(fechaFin); 
            while (fecha1.after(fecha2)){
                System.out.println("Escribe la fecha de inicio: ");
                fechaInicio = sc.nextLine();
                System.out.println("Escribe la fecha de fin: ");
                fechaFin = sc.nextLine();
            }
        } catch (ParseException ex) {
            Logger.getLogger(Ejercicio5EmpleadosHibernate.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //Se pide lista de empleados
        System.out.println("Dime por espacios una lista de ID");
        String lista = sc.nextLine();
        //Convertimos a []
        String[] empleados = lista.split(" ");
        Proyecto proyecto = new Proyecto(0, nombre, fechaInicio, fechaFin);
        
        ProyectoEmpleadoDAO.addproyectEmple(proyecto, empleados);
    }
    
    private static void updateProyect(){
        System.out.println("Escribe el id: ");
        int id = Integer.valueOf(sc.nextLine());
        System.out.println("Escribe el nombre: ");
        String nombre = sc.nextLine();
        System.out.println("Escribe la fecha de inicio: ");
        String fechaInicio = sc.nextLine();
        System.out.println("Escribe la fecha de fin: ");
        String fechaFin = sc.nextLine();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
   
        try {
            Date fecha1 = format.parse(fechaInicio);
            Date fecha2 = format.parse(fechaFin); 
            while (fecha1.after(fecha2)){
                System.out.println("Escribe la fecha de inicio: ");
                fechaInicio = sc.nextLine();
                System.out.println("Escribe la fecha de fin: ");
                fechaFin = sc.nextLine();
            }
        } catch (ParseException ex) {
            Logger.getLogger(Ejercicio5EmpleadosHibernate.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        ProyectoDAO.updateProyect(id, nombre, fechaInicio, fechaFin);
        
    }
    
    private static void addEmpleToProyect(){
        
        System.out.println("Escribe el id del proyecto: ");
        int idProyect = Integer.valueOf(sc.nextLine());
        System.out.println("Escribe el id del empleado: ");
        int idEmpleado = Integer.valueOf(sc.nextLine());
        
        ProyectoEmpleadoDAO.addEmpleToProyect(idProyect, idEmpleado);
    }
    
    private static void addEmplesToProyect(){
        System.out.println("Escribe el id del proyecto: ");
        int idProyect = Integer.valueOf(sc.nextLine());
        //Se pide lista de empleados
        System.out.println("Dime por espacios una lista de ID");
        String lista = sc.nextLine();
        //Convertimos a []
        String[] empleados = lista.split(" ");
        
        ProyectoEmpleadoDAO.addEmplesToProyect(idProyect, empleados);
    }
    
    private static void deleteEmpleFromProyect(){
        
    }
    
    private static void listFututeProyect(){
        
    }
    
    private static void listPastProyect(){
        
    }
    
    private static void listActiveProyect(){
        
    }
    
    private static void listDetalleProyecto(){
        
    }
    
}
