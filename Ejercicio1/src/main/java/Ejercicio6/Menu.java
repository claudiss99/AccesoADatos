/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ejercicio6;


/**
 *
 * @author ClaudissPerez
 */
import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    private final PeliculaDAO peliculaDAO;
    private Scanner sc;

    public Menu(PeliculaDAO peliculaDAO) {
        this.peliculaDAO = peliculaDAO;
    }
    
    public void iniciarMenu(){
        int opcion;
        sc = new Scanner(System.in);
        do{
            mostrarOpciones();
            System.out.println("Seleccione una opción: ");
            opcion = Integer.valueOf(sc.nextLine());
            
            switch (opcion) {
                case 1:
                    listarPeliculas();
                    break;
                case 2:
                    buscarPeliXFragmento();
                    break;
                case 3:
                    buscarPeliXAgno();
                    break;
                case 4:
                    addPelicula();
                    break;
                case 5:
                    updatePelicula();
                    break;
                case 6:
                    deletePelicula();
                    break;
                case 7:
                    System.out.println("Saliendo...");
                    break;
                            
                default:
                    System.err.println("Opción no válida");
                    break;
            }
        }while(opcion !=7);
    }

    private void mostrarOpciones() {
        System.out.println("\n-----Menú------");
        System.out.println("1 --> Listar todas las peliculas");
        System.out.println("2 --> Buscar pelicula por titulo");
        System.out.println("3 --> Buscar pelicula por año");
        System.out.println("4 --> Añadir una pelicula");
        System.out.println("5 --> Actualizar una pelicula");
        System.out.println("6 --> Borrar una película");
        System.out.println("7 --> Salir");
    }

    private void listarPeliculas() {
        ArrayList<Pelicula> pelis = peliculaDAO.listarPeliculas();
        System.out.println("Peliculas (ID, Titulo, Director, Año, Genero)");
        for (Pelicula peli: pelis){
            System.out.println(peli.getId()+", "+peli.getTitulo()+", "+peli.getDirector()+", "+peli.getAgno()+", "+peli.getGenero());
        }
    }

    private void buscarPeliXFragmento() {
        System.out.println("Introduzca fragmento a buscar");
        String fragmento = sc.nextLine();
        ArrayList<Pelicula> pelis = peliculaDAO.buscarPeliXFragmento(fragmento);
        System.out.println("Peliculas (ID, Titulo, Director, Año, Genero)");
        System.out.println(pelis.size());
        for (Pelicula peli: pelis){
            System.out.println(peli.getId()+", "+peli.getTitulo()+", "+peli.getDirector()+", "+peli.getAgno()+", "+peli.getGenero());
        }
    }

    private void buscarPeliXAgno() {
        System.out.println("Introduce el año para buscar la pelicula");
        int agno = Integer.parseInt(sc.nextLine());
        ArrayList<Pelicula> pelis = peliculaDAO.buscarPeliXAgno(agno);
        System.out.println("Peliculas (ID, Titulo, Director, Año, Genero)");
        for (Pelicula peli: pelis){
            System.out.println(peli.getId()+", "+peli.getTitulo()+", "+peli.getDirector()+", "+peli.getAgno()+", "+peli.getGenero());
        }
    }

    private void addPelicula() {
        System.out.println("Introduce el titulo de la pelicula");
        String titulo = sc.nextLine();
        System.out.println("Introduce el director de la pelicula");
        String director = sc.nextLine();
        System.out.println("Introduce el agno de la pelicula");
        int agno = Integer.parseInt(sc.nextLine());
        System.out.println("Introduce el genero de la pelicula");
        String genero = sc.nextLine();
        
        Pelicula pelicula = new Pelicula(0, titulo, director, agno, genero);
        peliculaDAO.addPelicula(pelicula);
    }

    private void updatePelicula() {
        System.out.println("Introduce el ID de la película a actualizar");
        int id = Integer.parseInt(sc.nextLine());
        System.out.println("Introduce el nuevo título de la película");
        String titulo = sc.nextLine();
        System.out.println("Introduce el nuevo director de la película");
        String director = sc.nextLine();
        System.out.println("Introduce el nuevo año de la película");
        int agno = Integer.parseInt(sc.nextLine());
        System.out.println("Introduce el nuevo género de la película");
        String genero = sc.nextLine();
        
        Pelicula pelicula = new Pelicula(id, titulo, director, agno, genero);
        peliculaDAO.updatePelicula(pelicula);
    }

    private void deletePelicula() {
        System.out.println("Dime el ID de la pelicula a eliminar");
        int id = Integer.parseInt(sc.nextLine());
        Pelicula peliculaEliminar = peliculaDAO.findPeliculaByID(id);
        peliculaDAO.deletePelicula(peliculaEliminar);
        System.out.println("Película eliminada con éxito");
    }
}
