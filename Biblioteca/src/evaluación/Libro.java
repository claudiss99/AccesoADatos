/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package evaluación;

/**
 *
 * @author Claudia
 */
public class Libro {
    private int idLibro;
    private String titulo;
    private Autor autor;
    private String editorial;
    private int fPubli;

    public Libro(int idLibro, String titulo, Autor autor, String editorial, int fPubli) {
        this.idLibro = idLibro;
        this.titulo = titulo;
        this.autor = autor;
        this.editorial = editorial;
        this.fPubli = fPubli;
    }

    public int getIdLibro() {
        return idLibro;
    }

    public String getTitulo() {
        return titulo;
    }

    public Autor getAutor() {
        return autor;
    }

    public String getEditorial() {
        return editorial;
    }

    public int getfPubli() {
        return fPubli;
    }
    
    @Override
    public String toString(){
        return "ID Libro: " + idLibro + ", Título: " + titulo + ", Autor: " + autor.getNombre() + " " + autor.getApellidos() +
                ", Editorial: " + editorial + ", Año de publicación: " + fPubli;
    }
}
