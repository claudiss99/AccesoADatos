/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package evaluación;

/**
 *
 * @author Claudia
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Biblioteca biblioteca = new Biblioteca();
        
        Autor autor1 = new Autor(1,"Irene","Vallejo");
        Autor autor2 = new Autor(2,"Federico","García Lorca");
        
        Libro libro1 = new Libro (1,"El infinito en un junco",autor1,"Siruela",2022);
        Libro libro2 = new Libro (2,"Poeta en Nueva York",autor2,"Séneca",1940);
        Libro libro3 = new Libro (3,"El silbido del arquero",autor1,"Contraseña editorial",2015);
        
         // Agregar libros a la biblioteca
        biblioteca.addLibro(libro1);
        biblioteca.addLibro(libro2);
        biblioteca.addLibro(libro3);

        // Mostrar todos los libros
        biblioteca.mostrarLibros();

        System.out.println();

        // Buscar libros por autor
        biblioteca.showContainsAutor("Vallejo");
        System.out.println();
        biblioteca.showContainsAutor("García Lorca");

        System.out.println();

        // Buscar libros por título
        biblioteca.showContainsTitulo("El infinito");
        System.out.println();
        biblioteca.showContainsTitulo("Poeta");
        
    }
    
}
