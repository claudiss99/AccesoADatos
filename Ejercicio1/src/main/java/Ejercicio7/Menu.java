/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ejercicio7;

import java.util.Scanner;

/**
 *
 * @author ClaudissPerez
 */
public class Menu {
    private final EstudianteDAO estudianteDAO;
    private final AsignaturaDAO asignaturaDAO;
    private final MatriculaDAO matriculaDAO;
    private Scanner sc;

    public Menu(EstudianteDAO estudianteDAO, AsignaturaDAO asignaturaDAO, MatriculaDAO matriculaDAO) {
        this.estudianteDAO = estudianteDAO;
        this.asignaturaDAO = asignaturaDAO;
        this.matriculaDAO = matriculaDAO;
    }
    
    public void iniciarMenu(){
        int opcion;
        sc=new Scanner(System.in);
        do{
            mostrarOpciones();
            System.out.println("Elige una opcion: ");
            opcion =Integer.valueOf(sc.nextLine());
            
            switch (opcion) {
                case 1:
                    addEstudiante();
                    break;
                case 2:
                    addAsignatura();
                    break;
                case 3:
                    matricularEstudiante();
                    break;
                case 4:
                    addCalificacion();
                    break;
                case 5:
                    verHistorial();
                    break;
                case 6:
                    cancelarMatricula();
                    break;
                case 7:
                    System.out.println("Saliendo...");
                    break;                  
                default:
                    System.err.println("Opcion no valida");
                    break;
            }
        }while(opcion!=7);
    }
    
    private void mostrarOpciones(){
        System.out.println("\n---------Menu---------");
        System.out.println("1 --> Añadir estudiante");
        System.out.println("2 --> Añadir asignatura");
        System.out.println("3 --> Matricular estudiante en una asignatura");
        System.out.println("4 --> Añadir una calificacion");
        System.out.println("5 --> Ver historial de la matricula de un estudiante");
        System.out.println("6 --> Cancelar matricula");
        System.out.println("7 --> Salir");
    }

    private void addEstudiante() {
        System.out.println("Introduce el nombre del estudiante");
        String nombre = sc.nextLine();
        System.out.println("Introduce email");
        String email = sc.nextLine();
        System.out.println("Introduce telefono");
        String telefono = sc.nextLine();
        System.out.println("Introduce direccion");
        String direccion = sc.nextLine();
        
        Estudiante estudiante = new Estudiante(0, nombre, email, telefono, direccion);
        estudianteDAO.addEstudiante(estudiante);
    }

    private void addAsignatura() {
        System.out.println("Introduce el nombre");
        String nombre= sc.nextLine();
        System.out.println("Introduce el curso");
        String curso= sc.nextLine();
        System.out.println("Introduce las horas");
        int horas = Integer.parseInt(sc.nextLine());
        
        Asignatura asignatura = new Asignatura(0, nombre, curso, horas);
        asignaturaDAO.addAsignatura(asignatura);
    }

    private void matricularEstudiante() {
        System.out.println("Dime el correo del estudiante");
        String email = sc.nextLine();
        Estudiante estudiante = estudianteDAO.findEstudianteByEmail(email);
        System.out.println("Dime el nombre de la asigntura");
        String nombreAsignatura = sc.nextLine();
        Asignatura asignatura = asignaturaDAO.findAsignaturaByName(nombreAsignatura);
        System.out.println("Dime el año de matriculacion");
        int agno = Integer.parseInt(sc.nextLine());
        matriculaDAO.matricularEstudiante(estudiante, asignatura, agno);
    }

    private void addCalificacion() {
        System.out.println("Dime el correo del estudiante");
        String email = sc.nextLine();
        Estudiante estudiante = estudianteDAO.findEstudianteByEmail(email);
        System.out.println("Dime el nombre de la asigntura");
        String nombreAsignatura = sc.nextLine();
        Asignatura asignatura = asignaturaDAO.findAsignaturaByName(nombreAsignatura);
        System.out.println("Dime el año de matriculacion");
        int agno = Integer.parseInt(sc.nextLine());
        System.out.println("Dime la calificacion de la asignatura");
        int calificacion = Integer.parseInt(sc.nextLine());
        matriculaDAO.addCalificacion(estudiante, asignatura, agno, calificacion);
    }

    private void verHistorial() {
        System.out.println("Dime el correo del estudiante");
        String email = sc.nextLine();
        Estudiante estudiante = estudianteDAO.findEstudianteByEmail(email);
        matriculaDAO.listarHistorial(estudiante);
    }
    
    private void cancelarMatricula() {
        System.out.println("Dime el correo del estudiante");
        String email = sc.nextLine();
        Estudiante estudiante = estudianteDAO.findEstudianteByEmail(email);
        System.out.println("Dime el nombre de la asigntura");
        String nombreAsignatura = sc.nextLine();
        Asignatura asignatura = asignaturaDAO.findAsignaturaByName(nombreAsignatura);
        System.out.println("Dime el año de matriculacion");
        int agno = Integer.parseInt(sc.nextLine());
        matriculaDAO.cancelarMatricula(estudiante, asignatura, agno);
        
    }
}
