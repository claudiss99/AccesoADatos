/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package evaluación;
import java.util.ArrayList;
/**
 *
 * @author Claudia
 */
public class Biblioteca {
    private ArrayList<Libro> libros;
    private ArrayList<Autor> autores;

    public Biblioteca() {
        this.libros = new ArrayList<>();
        this.autores = new ArrayList<>();
    }

    // Método adiciojal para añadir libros
    public void addLibro(Libro libro){
        libros.add(libro);
    }
    
    public void addAutor(Autor autor){
        autores.add(autor);
    }
    
    // Primer método: Mostrar Libros
    public void mostrarLibros() {
        System.out.println("Lista de todos los libros en la biblioteca:");
        for (Libro libro : libros) {
            System.out.println(libro.toString());
        }
    }
    
    // Segundo método
    public void showContainsAutor(String vallejo){
        System.out.println("Libros del autor que contiene: " + nombreOApellidos);
        for (Libro libro : libros) {
            if (libro.getAutor().getNombre().toLowerCase().contains(nombreOApellidos.toLowerCase()) ||
                libro.getAutor().getApellidos().toLowerCase().contains(nombreOApellidos.toLowerCase())) {
                System.out.println(libro.toString());
            }
        }
    }
    
    // Tercer método
    public void showContainsTitulo(String poeta){
        System.out.println("Libros que contienen en el título: " + titulo);
        for (Libro libro : libros) {
            if (libro.getTitulo().toLowerCase().contains(titulo.toLowerCase())) {
                System.out.println(libro.toString());
            }
        }
    }
}
 
