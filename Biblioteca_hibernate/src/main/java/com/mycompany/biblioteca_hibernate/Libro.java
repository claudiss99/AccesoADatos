/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.biblioteca_hibernate;

import jakarta.persistence.*;

/**
 *
 * @author Claudia
 * 
 */

@Entity
@Table(name="libro")

public class Libro {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    
    @Column (name = "titulo")
    private String titulo;
    
    @Column (name="fecha_publicacion")
    private String fPubli;
            
    @Column (name ="genero")
    private String genero;
    
    @Column (name = "isbn")
    private String isbn;
    
    @Column (name= "editorial")
    private String editorial;
    
    @Column (name = "id_autor")
    private int idAutor;

    public Libro(int id, String titulo, String fPubli, String genero, String isbn, String editorial, int idAutor) {
        this.id = id;
        this.titulo = titulo;
        this.fPubli = fPubli;
        this.genero = genero;
        this.isbn = isbn;
        this.editorial = editorial;
        this.idAutor = idAutor;
    }

    public Libro() {
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

    public String getfPubli() {
        return fPubli;
    }

    public void setfPubli(String fPubli) {
        this.fPubli = fPubli;
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
    
    
}
