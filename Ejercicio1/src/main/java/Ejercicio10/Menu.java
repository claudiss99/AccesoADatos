/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ejercicio10;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author ClaudissPerez
 */
public class Menu {
    private final EmpleadoDAO empleadoDAO;
    private final EmpleadoProyectoDAO empleadoProyectoDAO;
    private final ProyectoDAO proyectoDAO;
    private Scanner sc;

    public Menu(EmpleadoDAO empleadoDAO, EmpleadoProyectoDAO empleadoProyectoDAO, ProyectoDAO proyectoDAO) {
        this.empleadoDAO = empleadoDAO;
        this.empleadoProyectoDAO = empleadoProyectoDAO;
        this.proyectoDAO = proyectoDAO;
    }
    
    public void iniciarMenu(){
        int opcion;
        sc = new Scanner(System.in);
        do {
            mostrarOpciones();
            System.out.println("Seleccione una opción: ");
            opcion = sc.nextInt();
            sc.nextLine();
            switch(opcion){
                case 1: 
                    addEmpleados();
                    break;
                case 2:
                    modEmpleado();
                    break;
                case 3:
                    //devolverPrestamo();
                    break;
                case 4:
                   // listarPrestamosPendientes();
                    break;
                case 5:
                    //listarPrestamos();
                    break;
                case 6:
                    System.out.println("Saliendo...");;
                    break;
                case 7:
                    addProyectoEmpleados();
                    break;
                default:
                    System.out.println("Opción no válida.");
                    break;
            }
        } while(opcion !=16);
    }
    
    
    private void mostrarOpciones() {
        System.out.println("\n--- Menú ---");
        System.out.println("1 --> Añadir empleados");
        System.out.println("2 --> Modificar empleado");
        System.out.println("3 --> Despedir empleado");
        System.out.println("4 --> Listar empleados activos");
        System.out.println("5 --> Listar empleados despedidos");
        System.out.println("6 --> Añadir un proyecto"); 
        System.out.println("7 --> Añadir proyecto con empleados");
        System.out.println("8 --> Modificar un proyecto");
        System.out.println("9 --> Añadir empleado a proyecto");
        System.out.println("10 --> Añadir varios empleados a un proyecto");
        System.out.println("11 --> Eliminar un empleado de un proyecto");
        System.out.println("12 --> Listar todos los proyectos futuros");
        System.out.println("13 --> Listar todos los proyectos pasados");
        System.out.println("14 --> Listar todos los proyectos activos");
        System.out.println("15 --> Listar los detalles de un proyecto");
        System.out.println("16 --> Salir");
    }
    
   
    private void addEmpleados(){
        System.out.println("Dime tu nombre: ");
        String nombre= sc.nextLine();
        System.out.println("Dime el DNI: ");
        String dni = sc.nextLine();
        System.out.println("Dime el departamento: ");
        String departamento = sc.nextLine();
        System.out.println("Dime el sueldo: ");
        double sueldo = Double.valueOf(sc.nextLine());
        System.out.println("Dime el fecha de contratacion: ");
        Date fechaContratacion = Date.valueOf(sc.nextLine());
        
        Empleado empleado = new Empleado(nombre, dni, departamento, sueldo, fechaContratacion);
        empleadoDAO.addEmpleados(empleado);
        
    }
    
    
    private void modEmpleado(){
        System.out.println("Dime el ID del empleado que quieres modificar");
        int id = Integer.parseInt(sc.nextLine());
        //Verificar que exista el empleado
        empleadoDAO.findEmpleadoByID(id);
        System.out.println("Dime el nombre para modificar");
        String nombre = sc.nextLine();
        System.out.println("Dime el departamento para modificar");
        String departamento = sc.nextLine();
        System.out.println("Dime el sueldo para modificar");
        int sueldo= Integer.parseInt(sc.nextLine());
        
        Empleado empleado = new Empleado(id, nombre, departamento, sueldo);
        empleadoDAO.modEmpleado(empleado);
    }
    
    private void despedirEmpleado(){
        System.out.println("Dime el ID del empleado a despedir");
        int id = Integer.valueOf(sc.nextLine());
        empleadoDAO.findEmpleadoByID(id);
        System.out.println("Dime la fecha de despido");
        Date fechaFinalizacion = Date.valueOf(sc.nextLine());
        
        Empleado empleado = new Empleado(id, fechaFinalizacion);
        empleadoDAO.despedirEmpleado(empleado);
    }
    
    private void listarEmpleadosActivos(){
        ArrayList<Empleado> empleados = new ArrayList<>();
        System.out.println("Empleados (ID, nombre, dni, departamento, sueldo, fecha Contratación");
        for (Empleado e: empleados){
            System.out.println(e.getId()+", "+e.getNombre()+", "+e.getDni()+", "+e.getDepartamento()+", "+e.getSueldo()+", "+e.getFechaContratacion());
        }
    }
    
    private void listarEmpleadosDespedidos(){
        ArrayList<Empleado> empleados = new ArrayList<>();
        System.out.println("Empleados (ID, nombre, dni, departamento, sueldo, fecha Contratación, fecha de Despido");
        for (Empleado e: empleados){
            System.out.println(e.getId()+", "+e.getNombre()+", "+e.getDni()+", "+e.getDepartamento()+", "+e.getSueldo()+", "+e.getFechaContratacion()+", "+e.getFechaFinalizacion());
        }
    }
    
    private void addProyectoEmpleados(){
        System.out.println("Dime el nombre del proyecto");
        String nombreProyecto = sc.nextLine();
        System.out.println("Dime fecha inicio");
        Date fechaIncio = Date.valueOf(sc.nextLine());
        System.out.println("Dime fecha fin");
        Date fechaFin = Date.valueOf(sc.nextLine());
        
        //Como compruebo las fechas ?
        System.out.println("Dime los empleados separados por espacio");
        String emple = sc.nextLine();
        String[] empleados = emple.split(" ");
        for (String e: empleados){
            empleadoDAO.findEmpleadoByID(Integer.parseInt(e));
        }
        
        proyectoDAO.addProyecto(nombreProyecto, fechaIncio, fechaFin, empleados);
    }
    
    private void modProyecto(){
        System.out.println("Dime el nombre del proyecto que quieres modificar");
        int id = Integer.parseInt(sc.nextLine());
        //Verificar que exista el proyecto
        proyectoDAO.findProyectoByID(id);
        System.out.println("Dime el nombre para modificar");
        String nombre = sc.nextLine();
        System.out.println("Dime la fecha de inicio para modificar");
        Date fechaInicio = Date.valueOf(sc.nextLine());
        System.out.println("Dime la fecha de fin para modificar");
        Date fechaFin = Date.valueOf(sc.nextLine());
        
        Proyecto proyecto = new Proyecto(id, nombre, fechaInicio, fechaFin);
        proyectoDAO.modProyecto(proyecto);
    }
    
    private void addEmple
}
