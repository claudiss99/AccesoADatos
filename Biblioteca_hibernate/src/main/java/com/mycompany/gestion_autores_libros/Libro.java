/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestion_autores_libros;

/**
 *
 * @author Claudia
 * 
 */
public class Libro {
    private int id;
    private String titulo;
    private String fechaPubli;
    private String genero;
    private String isbn;
    private String editorial;
    private int idAutor;

    public Libro(int id, String titulo, String fechaPubli, String genero, String isbn, String editorial, int idAutor) {
        this.id = id;
        this.titulo = titulo;
        this.fechaPubli = fechaPubli;
        this.genero = genero;
        this.isbn = isbn;
        this.editorial = editorial;
        this.idAutor = idAutor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getFechaPubli() {
        return fechaPubli;
    }

    public void setFechaPubli(String fechaPubli) {
        this.fechaPubli = fechaPubli;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public int getIdAutor() {
        return idAutor;
    }

    public void setIdAutor(int idAutor) {
        this.idAutor = idAutor;
    }

    @Override
    public String toString() {
        return "Libro{" + "id=" + id + ", titulo=" + titulo + ", fechaPubli=" + fechaPubli + ", genero=" + genero + ", isbn=" + isbn + ", editorial=" + editorial + ", idAutor=" + idAutor + '}';
    }
    
    
}
