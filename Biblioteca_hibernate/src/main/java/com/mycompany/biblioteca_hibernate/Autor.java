/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.biblioteca_hibernate;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Claudia
 */

@Entity
@Table(name = "autor")

public class Autor {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    
    @Column(name = "nombre")
    private String nombre;
    
    @Column(name = "fecha_nacimiento")
    private String fNacimiento;
    
    @Column(name = "nacionalidad")
    private String nacionalidad;
    
    @Column(name = "numero_obras")
    private int nObras;
    
    @Column(name = "biografia")
    private String biografia;
    
    @OneToMany(mappedBy = "autor")
    private List<Libro> libros = new ArrayList<>();

    public Autor(int id, String nombre, String fNacimiento, String nacionalidad, int nObras, String biografia) {
        this.id = id;
        this.nombre = nombre;
        this.fNacimiento = fNacimiento;
        this.nacionalidad = nacionalidad;
        this.nObras = nObras;
        this.biografia = biografia;
    }

    public Autor(String nombre, String fNacimiento, String nacionalidad, int nObras, String biografia) {
        this.nombre = nombre;
        this.fNacimiento = fNacimiento;
        this.nacionalidad = nacionalidad;
        this.nObras = nObras;
        this.biografia = biografia;
    }

    

    public Autor() {
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getfNacimiento() {
        return fNacimiento;
    }

    public void setfNacimiento(String fNacimiento) {
        this.fNacimiento = fNacimiento;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public int getnObras() {
        return nObras;
    }

    public void setnObras(int nObras) {
        this.nObras = nObras;
    }

    public String getBiografia() {
        return biografia;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }

    @Override
    public String toString() {
        return "Autor{" + "id=" + id + ", nombre=" + nombre + ", fNacimiento=" + fNacimiento + ", nacionalidad=" + nacionalidad + ", nObras=" + nObras + ", biografia=" + biografia + '}';
    }
    
    

}
