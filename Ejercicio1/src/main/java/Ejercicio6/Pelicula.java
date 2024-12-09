/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ejercicio6;

/**
 *
 * @author ClaudissPerez
 */
public class Pelicula {
    private int id;
    private String titulo;
    private String director;
    private int agno;
    private String genero;

    public Pelicula(int id, String titulo, String director, int agno, String genero) {
        this.id = id;
        this.titulo = titulo;
        this.director = director;
        this.agno = agno;
        this.genero = genero;
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDirector() {
        return director;
    }

    public int getAgno() {
        return agno;
    }

    public String getGenero() {
        return genero;
    }

    @Override
    public String toString() {
        return "Pelicula{" + "id=" + id + ", titulo=" + titulo + ", director=" + director + ", agno=" + agno + ", genero=" + genero + '}';
    }
    
    
}
