/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.ejercicio2instituto;

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
        
        return scanner.nextLine();
    }
    
    private static void addProfesor(){
        System.out.println("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.println("Departamento: ");
        String departamento = scanner.nextLine();
    }
    
    private static void updateProfesor(){
        
    }
    
    private static void deleteProfesor(){
        
    }
    
    private static void addAsignatura(){
        
    }
    
    private static void updateAsignatura(){
        
    }
    
    private static void deleteAsignatura(){
        
    }
    
    private static void ListarProfesores(){
        
    }
    
    private static void ListarAsignaturas(){
        
    }
    
    private static void searchProfesorByID(){
        
    }
    
    private static void searchAsignaturaByID(){
        
    }
}
