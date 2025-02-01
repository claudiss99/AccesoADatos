/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.ejercicio2instituto;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Claudia
 */
public class Ejercicio2Instituto {
    private static final Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        
        String opc;
        do{
            opc= getMenuOption();
            switch (opc) {
                case "1":
                    addProfesor();
                    break;
                case "2":
                    updateProfesor();
                    break;
                case "3":
                    deleteProfesor();
                    break;
                case "4":
                    addAsignatura();
                    break;
                case "5":
                    updateAsignatura();
                    break;
                case "6":
                    deleteAsignatura();
                    break;
                case "7":
                    ListarProfesores();
                    break;
                case "8":
                    ListarAsignaturas();
                    break;
                case "9":
                    searchProfesorByID();
                    break;
                case "10":
                    searchAsignaturaByID();
                    break;
            }
        }while (!opc.equals("11"));
        
    }
    
    private static String getMenuOption(){
        System.out.println("\n Introduce una opción:");
        System.out.println("1. Añadir profesor");
        System.out.println("2. Modificar profesor");
        System.out.println("3. Eliminar profesor");
        System.out.println("4. Añadir asignatura");
        System.out.println("5. Modificar asignatura");
        System.out.println("6. Eliminar asignatura");
        System.out.println("7. Consultar todos los profesores");
        System.out.println("8. Consultar todas las asignaturas");
        System.out.println("9. Consultar profesor por ID");
        System.out.println("10. Consultar asignatura por ID");
        System.out.println("11. Salir");
        
        return scanner.nextLine();
    }
    
    private static void addProfesor(){
        System.out.println("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.println("Departamento: ");
        String departamento = scanner.nextLine();
        
        Profesor profesor = new Profesor(0, nombre, departamento);
        ProfesorDAO.addProfesor(profesor);
    }
    
    private static void updateProfesor(){
        System.out.println("Ingresa el id del profesor que quieras actualizar: ");
        int id= Integer.valueOf(scanner.nextLine());
        System.out.println("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.println("Departamento: ");
        String departamento = scanner.nextLine();
        
        Profesor profesor = new Profesor(id, nombre, departamento);
        ProfesorDAO.updateProfesor(profesor);
    }
    
    private static void deleteProfesor(){
        System.out.println("Dime el id del profesor para eliminarlo: ");
        int id = Integer.valueOf(scanner.nextLine());        
        ProfesorDAO.deleteProfesor(id);
    }
    
    private static void addAsignatura(){
        System.out.println("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.println("Curso: ");
        String curso = scanner.nextLine();
        System.out.println("Grupo: ");
        String grupo = scanner.nextLine();
        int id_profesor = Integer.valueOf(scanner.nextLine());
        
        Asignatura asignatura = new Asignatura(0, nombre, curso, grupo, id_profesor);
        AsignaturaDAO.addAsignatura(asignatura);
    }
    
    private static void updateAsignatura(){
        System.out.println("Ingresa el id de la asignatura que quieras actualizar: ");
        int id= Integer.valueOf(scanner.nextLine());
        System.out.println("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.println("Curso: ");
        String curso = scanner.nextLine();
        System.out.println("Grupo: ");
        String grupo = scanner.nextLine();
        int id_profesor = Integer.valueOf(scanner.nextLine());
        
        Asignatura asignatura = new Asignatura(id, nombre, curso, grupo, id_profesor);
        AsignaturaDAO.updateAsignatura(asignatura);
    }
    
    private static void deleteAsignatura(){
        System.out.println("Dime el id de la asignatura para eliminarlo: ");
        int id = Integer.valueOf(scanner.nextLine());        
        AsignaturaDAO.deleteAsignatura(id);
    }
    
    private static void ListarProfesores(){
        ArrayList<Profesor> profesores = ProfesorDAO.listarProfesores();
        if (profesores.isEmpty()){
            System.out.println("No hay profesores en la base de datos");
        }else{
            System.out.println("ID\t nombre\t  departamento");
            for(Profesor p: profesores){
                System.out.println(p.toString());
            }
        }
    }
    
    private static void ListarAsignaturas(){
        ArrayList<Asignatura> asignaturas =  AsignaturaDAO.listarAsignaturas();
        if (asignaturas.isEmpty()){
            System.out.println("No hay asignaturas en la base de datos");
        }else{
            System.out.println("ID\t nombre\t  curso\t grupo");
            for(Asignatura a: asignaturas){
                System.out.println(a.toString());
            }
        }
    }
    
    private static void searchProfesorByID(){
       System.out.println("Dame el ID del profesor a buscar: ");
        int id = Integer.parseInt(scanner.nextLine());

        Profesor profesor = ProfesorDAO.searchProfesorByID(id); // Asegúrate de usar el DAO correcto

        if (profesor == null) {
            System.out.println("No se ha encontrado profesor.");
        } else {
            System.out.println("Profesor encontrado:");
            System.out.println("Nombre: " + profesor.getNombre());
            System.out.println("Departamento: " + profesor.getDepartamento());
            System.out.println("Asignaturas que imparte:");

            if (profesor.getAsignaturas().isEmpty()) {
                System.out.println("Este profesor no tiene asignaturas.");
            } else {
                for (Asignatura asignatura : profesor.getAsignaturas()) {
                    System.out.println("- " + asignatura.getNombre() + " (Curso: " 
                        + asignatura.getCurso() + ", Grupo: " + asignatura.getGrupo() + ")");
                }
            }
        }
    }
    
    private static void searchAsignaturaByID(){
        System.out.println("Dame el ID de la asignatura a buscar: ");
        int id = Integer.parseInt(scanner.nextLine());

        Asignatura asignatura = AsignaturaDAO.searchAsignaturaByID(id);

        if (asignatura == null) {
            System.out.println("No se ha encontrado la asignatura.");
        } else {
            System.out.println("Asignatura encontrada:");
            System.out.println("Nombre: " + asignatura.getNombre());
            System.out.println("Curso: " + asignatura.getCurso());
            System.out.println("Grupo: " + asignatura.getGrupo());

            Profesor profesor = asignatura.getProfesor();
            if (profesor == null) {
                System.out.println("No tiene un profesor asignado.");
            } else {
                System.out.println("Profesor: " + profesor.getNombre() 
                    + " (Departamento: " + profesor.getDepartamento() + ")");
            }
        }
    }
}
