/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ejercicio9;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author ClaudissPerez
 */
public class Menu {
    private final PrestamoDAO prestamoDAO;
    private final LibroDAO libroDAO;
    private Scanner sc;

    public Menu(PrestamoDAO prestamoDAO, LibroDAO libroDAO) {
        this.prestamoDAO = prestamoDAO;
        this.libroDAO = libroDAO;
    }

    public void iniciarMenu() {
        int opcion;
        sc = new Scanner(System.in);
        do {
            mostrarOpciones();
            System.out.println("Seleccione una opción: ");
            opcion = sc.nextInt();
            sc.nextLine();
            switch(opcion){
                case 1: 
                    listarLibros();
                    break;
                case 2:
                    addPrestamo();
                    break;
                case 3:
                    devolverPrestamo();
                    break;
                case 4:
                    listarPrestamosPendientes();
                    break;
                case 5:
                    listarPrestamos();
                    break;
                case 6:
                    System.out.println("Saliendo...");;
                    break;
                default:
                    System.out.println("Opción no válida.");
                    break;
            }
            
        } while (opcion != 6);
    }

    private void mostrarOpciones() {
        System.out.println("\n--- Menú ---");
        System.out.println("1 --> Listar libros");
        System.out.println("2 --> Registrar préstamo");
        System.out.println("3 --> Devolver préstamo");
        System.out.println("4 --> Listar préstamos pendientes");
        System.out.println("5 --> Listar préstamos");
        System.out.println("6 --> Salir"); 
    }
    
    private void listarLibros(){
        ArrayList<Libro> libros = libroDAO.listarLibros();
        System.out.println("Libros (ID, titulo, autor, anio_publicacion, cantidad_disponible, categoria):");
        for ( Libro libro: libros){
            System.out.println(libro.getId()+" , "+libro.getTitulo()+" , "+libro.getAutor()+" , "+libro.getAnio_publicacion()+" , "+libro.getCantidad_disponible()+" , "+libro.getCategoria());
        }
    }
    
    private void addPrestamo(){
        System.out.println("Dime el nombre del lector:");
        String lector = sc.nextLine();
        
        System.out.println("Dime la fecha del prestamo (YYY-MM-DD): ");
        String fecha = sc.nextLine();
        
        System.out.println("Dime el ID del libro");
        String idLibro = sc.nextLine();
        
        Prestamo prestamo = new Prestamo(Integer.parseInt(idLibro), lector, Date.valueOf(fecha));
        prestamoDAO.addPrestamo(prestamo);
    }
    
    private void devolverPrestamo(){
        System.out.println("Dime el ID del prestamo: ");
        int prestamoID = sc.nextInt();
        sc.nextLine();
        
        prestamoDAO.devolverPrestamo(prestamoID);
    }
    
    private void listarPrestamosPendientes(){
        ArrayList<Prestamo> prestamos = prestamoDAO.listarPrestamosPendientes();
        System.out.println("Prestamos (id, id_libro, lector, fecha_prestamo, estado): ");
        for(Prestamo prestamo:prestamos){
            System.out.println(prestamo.getId()+", "+prestamo.getId_libro()+", "+prestamo.getLector()+", "+prestamo.getFecha_prestamo()+", "+prestamo.getEstado());
        }
    }
    
    private void listarPrestamos(){
        ArrayList<Prestamo> prestamos = prestamoDAO.listarPrestamos();
        System.out.println("Prestamos (id, id_libro, lector, fecha_prestamo, estado): ");
        for(Prestamo prestamo:prestamos){
            System.out.println(prestamo.getId()+", "+prestamo.getId_libro()+", "+prestamo.getLector()+", "+prestamo.getFecha_prestamo()+", "+prestamo.getEstado());
        }
    }
}
