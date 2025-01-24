/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.biblioteca_hibernate;

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
                    addAutor();
                    break;
                case "2":
                    updateAutor();
                    break;
                case "3":
                    deleteAutor();
                    break;
                case "4":
                    listarAutores();
                    break;
                case "5":
                    searchAutorByID();
                    break;
                case "6":
                    searchAutorByFragment();
                    break;
                case "7":
                    addLibro();
                    break;
                case "8":
                    deleteLibro();
                    break;
                case "9":
                    updateLibro();
                    break;
                case "10":
                    listarLibros();
                    break;
                case "11":
                    searchLibroById();
                    break;
                case "12":
                    searchLibroByName();
                    break;
                case "13":
                    searchLibroByIdAutor();
                    break;
                
            }
        }while(!menuOption.equals("14"));
    }
    
    private static String getMenuOption(){
        System.out.println("\nIntroduce una opción:");
        System.out.println("1. Crear Autor");
        System.out.println("2. Modificar un Autor");
        System.out.println("3. Borrar Autor");
        System.out.println("4. Consultar todos los autores");
        System.out.println("5. Buscar un autor por ID");
        System.out.println("6. Buscar un autor por fragmento de nombre");
        System.out.println("7. Crear Libro");
        System.out.println("8. Borrar Libro");
        System.out.println("9. Actualizar Libro");
        System.out.println("10. Listar libros");
        System.out.println("11. Buscar un libro por ID");
        System.out.println("12. Buscar un libro por fragmento de nombre");
        System.out.println("13. Buscar libro por id del autor");
        System.out.println("14. Salir");
        
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
    
    private static void searchAutorByID(){
        System.out.println("Dame el ID del autor a buscar: ");
        int id = Integer.valueOf(scanner.nextLine());
        
        Autor autor = AutorDAO.searchAutorById(id);
        if (autor == null){
            System.out.println("No se ha encontrado autor");
        }else{
            System.out.println("Autor: "+autor.toString());
        }
    }
    
    private static void searchAutorByFragment(){
        System.out.println("Dime el fragmento del nombre que quieres buscar");
        String fragmento = scanner.nextLine();
        ArrayList<Autor> autores = AutorDAO.searhAutorByName(fragmento);
        if(autores == null){
            System.out.println("No se ha encontrado autor");
        }else{
            for (Autor a:autores){
                System.out.println("Autor: "+a.toString());
            }
            
        }
    }
    
    private static void searchLibroByIdAutor(){
        System.out.println("Dime el id del autor: ");
        int idAutor = Integer.valueOf(scanner.nextLine());
        ArrayList<Libro> libros = LibroDAO.searchLibroByIdAutor(idAutor);
        if(libros == null){
            System.out.println("No se ha encontrado autor");
        }else{
            for (Libro l:libros){
                System.out.println("Libros: "+l.toString());
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
        System.out.println("Numero de obras publicadas: ");
        int obrasPubli = Integer.valueOf(scanner.nextLine());
        System.out.println("Biografia: ");
        String biografia = scanner.nextLine();
        
        Autor autor = new Autor(nombre, fechaNacim, nacionalidad, obrasPubli, biografia);
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
        System.out.println("Id Autor: ");
        int id_autor = Integer.valueOf(scanner.nextLine());
        
        Libro libro = new Libro(0, titulo, fechaPubli, genero, isbn, editorial, id_autor);
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
    
    private static void searchLibroById(){
        System.out.println("Dame el ID del libro a buscar: ");
        int id = Integer.valueOf(scanner.nextLine());
        
        Libro libro = LibroDAO.searchLibroById(id);
        if (libro == null){
            System.out.println("No se ha encontrado autor");
        }else{
            System.out.println("Autor: "+libro.toString());
        }
    }
    
    private static void searchLibroByName(){
        System.out.println("Dime el fragmento del nombre que quieres buscar");
        String fragmento = scanner.nextLine();
        ArrayList<Libro> libros = LibroDAO.searchLibroByName(fragmento);
        if(libros == null){
            System.out.println("No se ha encontrado autor");
        }else{
            for (Libro l:libros){
                System.out.println("Autor: "+l.toString());
            }
            
        }
    }
}
