/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ejercicio9;

/**
 *
 * @author ClaudissPerez
 */
public class Libro {
    private int id;
    private String titulo;
    private String autor;
    private int anio_publicacion;
    private int cantidad_disponible;
    private String categoria;

    public Libro(int id, String titulo, String autor, int anio_publicacion, int cantidad_disponible, String categoria) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.anio_publicacion = anio_publicacion;
        this.cantidad_disponible = cantidad_disponible;
        this.categoria = categoria;
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public int getAnio_publicacion() {
        return anio_publicacion;
    }

    public int getCantidad_disponible() {
        return cantidad_disponible;
    }

    public String getCategoria() {
        return categoria;
    }

    @Override
    public String toString() {
        return "Libro{" + "id=" + id + ", titulo=" + titulo + ", autor=" + autor + ", anio_publicacion=" + anio_publicacion + ", cantidad_disponible=" + cantidad_disponible + ", categoria=" + categoria + '}';
    }
    
    
}
