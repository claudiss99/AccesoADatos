/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestion_autores_libros;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Claudia
 */
public class Main {
    private static final Scanner scanner = new Scanner (System.in);
    
    public static void main(String[] args) {
        String menuOption;
        do{
            menuOption = getMenuOption();
            switch (menuOption) {
                case "1":
                    listarAutores();
                    break;
                case "2":
                    addAutor();
                    break;
                case "3":
                    deleteAutor();
                    break;
                case "4":
                    updateAutor();
                    break;
                case "5":
                    listarLibros();
                    break;
                case "6":
                    addLibro();
                    break;
                case "7":
                    deleteLibro();
                    break;
                case "8":
                    updateLibro();
                    break;
                
            }
        }while(!menuOption.equals("9"));
    }
    
    private static String getMenuOption(){
        System.out.println("\nIntroduce una opción:");
        System.out.println("1. Listar todos los autores");
        System.out.println("2. Crear Autor");
        System.out.println("3. Borrar Autor");
        System.out.println("4. Actualizar Autor");
        System.out.println("5. Listar todos los libros");
        System.out.println("6. Crear Libro");
        System.out.println("7. Borrar Libro");
        System.out.println("8. Actualizar Libro");
        System.out.println("9. Salir");
        
        return scanner.nextLine();
    }
    
    private static void listarAutores(){
        ArrayList<Autor> autores = AutorDAO.listarAutores();
        if (autores.isEmpty()){
            System.out.println("No hay autores en la base de datos");
        }else{
            System.out.println("ID\t nombre\t fecha nacimiento\t numero de obras\t biografia");
            for(Autor a: autores){
                System.out.println(a.toString());
            }
        }
    }
    
    private static void addAutor(){
        System.out.println("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.println("Fecha Nacimiento: ");
        String fechaNacim = scanner.nextLine();
        System.out.println("Nacionalidad: ");
        String nacionalidad = scanner.nextLine();
        System.out.println("Obras publicadas: ");
        int obrasPubli = Integer.valueOf(scanner.nextLine());
        System.out.println("Biografia: ");
        String biografia = scanner.nextLine();
        
        Autor autor = new Autor(1, nombre, fechaNacim, nacionalidad, obrasPubli, biografia);
        AutorDAO.addAutor(autor);
    }
    
    private static void deleteAutor(){
        System.out.println("Dime el id del autor para eliminarlo: ");
        int id = Integer.valueOf(scanner.nextLine());
        
        AutorDAO.deleteAutor(id);
    }
    
    private static void updateAutor(){
        System.out.println("Ingresa el id del autor que quieras actualizar: ");
        int id= Integer.valueOf(scanner.nextLine());
        System.out.println("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.println("Fecha Nacimiento: ");
        String fechaNacim = scanner.nextLine();
        System.out.println("Nacionalidad: ");
        String nacionalidad = scanner.nextLine();
        System.out.println("Obras publicadas: ");
        int obrasPubli = Integer.valueOf(scanner.nextLine());
        System.out.println("Biografia: ");
        String biografia = scanner.nextLine();
        
        Autor autor = new Autor(id, nombre, fechaNacim, nacionalidad, obrasPubli, biografia);
        AutorDAO.updateAutor(autor);
    }
    
    private static void listarLibros(){
        ArrayList<Libro> libros = LibroDAO.listarLibros();
        if (libros.isEmpty()){
            System.out.println("No hay libros en la base de datos");
        }else{
            for(Libro l: libros){
                System.out.println(l.toString());
            }
        }
    }
    
    private static void addLibro(){
        System.out.println("Titulo: ");
        String titulo = scanner.nextLine();
        System.out.println("Fecha publicacion: ");
        String fechaPubli = scanner.nextLine();
        System.out.println("Genero: ");
        String genero = scanner.nextLine();
        System.out.println("ISBN: ");
        String isbn = scanner.nextLine();
        System.out.println("Editorial: ");
        String editorial = scanner.nextLine();
        System.out.println("Autor: ");
        int id_autor = Integer.valueOf(scanner.nextLine());
        
        Libro libro = new Libro(1, titulo, fechaPubli, genero, isbn, editorial, id_autor);
        LibroDAO.addLibro(libro);
    }
    
    private static void deleteLibro(){
        System.out.println("Dime el id del libro para eliminarlo: ");
        int id = Integer.valueOf(scanner.nextLine());        
        LibroDAO.deleteLibro(id);
    }
    
    private static void updateLibro(){
        System.out.println("Ingresa el id del libro que quieras actualizar: ");
        int id= Integer.valueOf(scanner.nextLine());
        System.out.println("Titulo: ");
        String titulo = scanner.nextLine();
        System.out.println("Fecha publicacion: ");
        String fechaPubli = scanner.nextLine();
        System.out.println("Genero: ");
        String genero = scanner.nextLine();
        System.out.println("ISBN: ");
        String isbn = scanner.nextLine();
        System.out.println("Editorial: ");
        String editorial = scanner.nextLine();
        System.out.println("Autor: ");
        int id_autor = Integer.valueOf(scanner.nextLine());
        
        
        Libro libro = new Libro(id, titulo, fechaPubli, genero, isbn, editorial, id_autor);
        LibroDAO.updateLibro(libro);
    }
}
