/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.practicahibernate;

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
public class PracticaHibernate {
    private static final Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        String opc;
        do {
            opc = getMenuOption();
            switch (opc) {
                case "1":
                    addProveedor();
                    break;
                case "2":
                    updateProveedor();
                    break;
                case "3":
                    deleteProveedor();
                    break;
                case "4":
                    addActivity();
                    break;
                case "5":
                    deleteActivity();
                    break;
                case "6":
                    addClient();
                    break;
                case "7":
                    updateClient();
                    break;
                case "8":
                    deleteClient();
                    break;
                case "9":
                    buyActivity();
                    break;
                case "10":
                    cancelBuys();
                    break;
                case "11":
                    ListAllClients();
                    break;
                case "12":
                    listFututeActivities();
                    break;
                case "13":
                    listDetailsClient();
                    break;
                case "14":
                    listDetailsProveedor();
                    break;
                case "15":
                    listDetalleActivity();
                    break;

            }
        } while (!opc.equals("16"));
    }
    
    private static String getMenuOption() {
        System.out.println("\n Introduce una opción:");
        System.out.println("1. Añadir proveedor");
        System.out.println("2. Modificar proveedor");
        System.out.println("3. Borrar proveedor");
        System.out.println("4. Añadir actividad");
        System.out.println("5. Borrar actividad");
        System.out.println("6. Añadir un cliente");
        System.out.println("7. Modificar cliente");
        System.out.println("8. Borrar cliente");
        System.out.println("9. Comprar actividad");
        System.out.println("10. Cancelar compra");
        System.out.println("11. Listar todos los clientes");
        System.out.println("12. Listar todas las actividades futuras");
        System.out.println("13. Listar detalles de un cliente");
        System.out.println("14. Listar detalles de un proveedor");
        System.out.println("15. Listar detalles de una actividad");
        System.out.println("16. Salir");
        
        return sc.nextLine();
    }
    
    private static void addProveedor(){
        
        System.out.println("Escribe el nombre: ");
        String nombre = sc.nextLine();
        System.out.println("Escribe el EMAIL: ");
        String email = sc.nextLine();
        System.out.println("Escribe el CIF: ");
        String cif = sc.nextLine();
        
        Proveedor proveedor = new Proveedor(null, nombre, email, cif);
        ProveedorDAO.addProveedor(proveedor);
    }
    
    private static void updateProveedor(){
        System.out.println("Escribe el id: ");
        int id = Integer.valueOf(sc.nextLine());
        System.out.println("Escribe el nombre: ");
        String nombre = sc.nextLine();
        System.out.println("Escribe el email: ");
        String email = sc.nextLine();
        System.out.println("Escribe el cif: ");
        String cif = sc.nextLine();
        
        ProveedorDAO.updateProveedor(id, nombre, email, cif);
    }
    
    
    private static void deleteProveedor(){
     
        System.out.println("Escribe el id: ");
        int id = Integer.valueOf(sc.nextLine());
        
        
        ProveedorDAO.deleteProveedor(id);
    }
   
    private static void addActivity(){
        System.out.println("Dame el nombre");
        String nombre = sc.nextLine();
        System.out.println("Dame la fecha");
        String fecha = sc.nextLine();
        System.out.println("Dame la ubicación");
        String ubicacion = sc.nextLine();
        System.out.println("Dame las plazas");
        int plazas = Integer.valueOf(sc.nextLine());
        System.out.println("Dame el cif del proveedor");
        String cif = sc.nextLine();
        
        Actividad actividad = new Actividad(nombre, fecha, ubicacion, plazas);
        ActividadDAO.addActivity(actividad, cif);
    }
    
    private static void deleteActivity(){
//        ArrayList<Empleado> empleados = EmpleadoDAO.listEmpleadoDes();
//        System.out.println("Lista de empleados despedidos: ");
//        for (Empleado e: empleados){
//            System.out.println("ID: "+e.getId() + "Nombre: "+e.getNombre()+"DNI: "+e.getDni()+" Departamento: "+e.getDepartamento()+" Sueldo: "+e.getSueldo()+"Fecha de contratación: "+e.getFechaContratacion()+"Fecha de despido: "+e.getFechaFinalizacion());
//        }
    }
    
    private static void addClient(){
        
//        System.out.println("Escribe el nombre: ");
//        String nombre = sc.nextLine();
//        System.out.println("Escribe la fecha de inicio: ");
//        String fechaInicio = sc.nextLine();
//        System.out.println("Escribe la fecha de fin: ");
//        String fechaFin = sc.nextLine();
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
//   
//        try {
//            Date fecha1 = format.parse(fechaInicio);
//            Date fecha2 = format.parse(fechaFin); 
//            while (fecha1.after(fecha2)){
//                System.out.println("Escribe la fecha de inicio: ");
//                fechaInicio = sc.nextLine();
//                System.out.println("Escribe la fecha de fin: ");
//                fechaFin = sc.nextLine();
//            }
//        } catch (ParseException ex) {
//            Logger.getLogger(Ejercicio5EmpleadosHibernate.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//        Proyecto proyecto = new Proyecto(0, nombre, fechaInicio, fechaFin);
//        ProyectoDAO.addProyect(proyecto);
        
    }
    
    private static void updateClient(){
        
//        //Pedir datos del proyecto y comprobamos la fecha
//        System.out.println("Escribe el nombre: ");
//        String nombre = sc.nextLine();
//        System.out.println("Escribe la fecha de inicio: ");
//        String fechaInicio = sc.nextLine();
//        System.out.println("Escribe la fecha de fin: ");
//        String fechaFin = sc.nextLine();
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
//   
//        try {
//            Date fecha1 = format.parse(fechaInicio);
//            Date fecha2 = format.parse(fechaFin); 
//            while (fecha1.after(fecha2)){
//                System.out.println("Escribe la fecha de inicio: ");
//                fechaInicio = sc.nextLine();
//                System.out.println("Escribe la fecha de fin: ");
//                fechaFin = sc.nextLine();
//            }
//        } catch (ParseException ex) {
//            Logger.getLogger(Ejercicio5EmpleadosHibernate.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//        //Se pide lista de empleados
//        System.out.println("Dime por espacios una lista de ID");
//        String lista = sc.nextLine();
//        //Convertimos a []
//        String[] empleados = lista.split(" ");
//        Proyecto proyecto = new Proyecto(0, nombre, fechaInicio, fechaFin);
//        
//        ProyectoEmpleadoDAO.addproyectEmple(proyecto, empleados);
    }
    
    private static void deleteClient(){
//        System.out.println("Escribe el id: ");
//        int id = Integer.valueOf(sc.nextLine());
//        System.out.println("Escribe el nombre: ");
//        String nombre = sc.nextLine();
//        System.out.println("Escribe la fecha de inicio: ");
//        String fechaInicio = sc.nextLine();
//        System.out.println("Escribe la fecha de fin: ");
//        String fechaFin = sc.nextLine();
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
//   
//        try {
//            Date fecha1 = format.parse(fechaInicio);
//            Date fecha2 = format.parse(fechaFin); 
//            while (fecha1.after(fecha2)){
//                System.out.println("Escribe la fecha de inicio: ");
//                fechaInicio = sc.nextLine();
//                System.out.println("Escribe la fecha de fin: ");
//                fechaFin = sc.nextLine();
//            }
//        } catch (ParseException ex) {
//            Logger.getLogger(Ejercicio5EmpleadosHibernate.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//        ProyectoDAO.updateProyect(id, nombre, fechaInicio, fechaFin);
        
    }
    
    private static void buyActivity(){
        
//        System.out.println("Escribe el id del proyecto: ");
//        int idProyect = Integer.valueOf(sc.nextLine());
//        System.out.println("Escribe el id del empleado: ");
//        int idEmpleado = Integer.valueOf(sc.nextLine());
//        
//        ProyectoEmpleadoDAO.addEmpleToProyect(idProyect, idEmpleado);
    }
    
    private static void cancelBuys(){
//        System.out.println("Escribe el id del proyecto: ");
//        int idProyect = Integer.valueOf(sc.nextLine());
//        //Se pide lista de empleados
//        System.out.println("Dime por espacios una lista de ID");
//        String lista = sc.nextLine();
//        //Convertimos a []
//        String[] empleados = lista.split(" ");
//        
//        ProyectoEmpleadoDAO.addEmplesToProyect(idProyect, empleados);
    }
    
    private static void ListAllClients(){
        
//        System.out.println("Escribe el id del proyecto: ");
//        int idProyect = Integer.valueOf(sc.nextLine());
//        System.out.println("Escribe el id del empleado: ");
//        int idEmpleado = Integer.valueOf(sc.nextLine());
//        
        
    }
    
    private static void listFututeActivities(){
//        ArrayList<Proyecto> proyectos = ProyectoDAO.listFutureProyect();
//        System.out.println("Lista de proyectos futuros: ");
//        for (Proyecto p: proyectos){
//            System.out.println("ID: "+p.getId() + "Nombre: "+p.getNombre()+" Fecha de inicio: "+p.getNombre()+"Fecha de finalización: "+p.getFechaFin());
//        }
        
    }
    
    private static void listDetailsClient(){
//        ArrayList<Proyecto> proyectos = ProyectoDAO.listPastProyect();
//        System.out.println("Lista de proyectos futuros: ");
//        for (Proyecto p: proyectos){
//            System.out.println("ID: "+p.getId() + "Nombre: "+p.getNombre()+" Fecha de inicio: "+p.getNombre()+"Fecha de finalización: "+p.getFechaFin());
//        }
    }
    
    private static void listDetailsProveedor(){
//        ArrayList<Proyecto> proyectos = ProyectoDAO.listFutureProyect();
//        System.out.println("Lista de proyectos futuros: ");
//        for (Proyecto p: proyectos){
//            System.out.println("ID: "+p.getId() + "Nombre: "+p.getNombre()+" Fecha de inicio: "+p.getNombre()+"Fecha de finalización: "+p.getFechaFin());
//        }
    }
    
    private static void listDetalleActivity(){
//        System.out.println("Escribe el id del proyecto: ");
//        int idProyect = Integer.valueOf(sc.nextLine());
//        Proyecto proyecto = ProyectoDAO.getByID(idProyect);
//        System.out.println("Lista de empleados del proyecto: ");
//        ArrayList<Empleado> empleados = proyecto.getEmpleado();
//        for (Empleado e: empleados){
//            String estado;
//            if (e.getFechaFinalizacion() == null){
//                estado = "Activo";
//            }else{
//                estado= "Inactivo";
//            }
//            System.out.println("ID: "+e.getId() + "Nombre: "+e.getNombre()+"DNI: "+e.getDni()+" Departamento: "+e.getDepartamento()+" Estado: "+estado);
//        }
        
    }
}
