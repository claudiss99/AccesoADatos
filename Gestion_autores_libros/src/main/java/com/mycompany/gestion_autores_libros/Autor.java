/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestion_autores_libros;

/**
 *
 * @author Claudia
 */
public class Autor {
    private int id;
    private String nombre;
    private String fechaNacim;
    private String nacionalidad;
    private int obrasPubli;
    private String biografia;

    public Autor(int id, String nombre, String fechaNacim, String nacionalidad, int obrasPubli, String biografia) {
        this.id = id;
        this.nombre = nombre;
        this.fechaNacim = fechaNacim;
        this.nacionalidad = nacionalidad;
        this.obrasPubli = obrasPubli;
        this.biografia = biografia;
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

    public String getFechaNacim() {
        return fechaNacim;
    }

    public void setFechaNacim(String fechaNacim) {
        this.fechaNacim = fechaNacim;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public int getObrasPubli() {
        return obrasPubli;
    }

    public void setObrasPubli(int obrasPubli) {
        this.obrasPubli = obrasPubli;
    }

    public String getBiografia() {
        return biografia;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }

    @Override
    public String toString() {
        return "Autor{" + "id=" + id + ", nombre=" + nombre + ", fechaNacim=" + fechaNacim + ", nacionalidad=" + nacionalidad + ", obrasPubli=" + obrasPubli + ", biografia=" + biografia + '}';
    }
    
    
}
