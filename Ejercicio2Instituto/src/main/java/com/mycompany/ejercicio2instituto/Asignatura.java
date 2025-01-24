/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ejercicio2instituto;

import jakarta.persistence.*;

/**
 *
 * @author Claudia
 */

@Entity
@Table(name="asignatura")

public class Asignatura {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    
    @Column(name="nombre")
    private String nombre;
    
    @Column(name="curso")
    private int curso;
    
    @Column(name="grupo")
    private String grupo;
    
    @ManyToOne
    @JoinColumn (name="id_profesor", referencedColumnName = "id")
    private Profesor profesor;

    public Asignatura(int id, String nombre, int curso, String grupo, int idProfesor) {
        this.id = id;
        this.nombre = nombre;
        this.curso = curso;
        this.grupo = grupo;
        //this.profesor = ProfesorDAO.sea;
    }

    public Asignatura() {
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

    public int getCurso() {
        return curso;
    }

    public void setCurso(int curso) {
        this.curso = curso;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    @Override
    public String toString() {
        return "Asignatura{" + "id=" + id + ", nombre=" + nombre + ", curso=" + curso + ", grupo=" + grupo + ", profesor=" + profesor + '}';
    }
    
    
    
    
}
