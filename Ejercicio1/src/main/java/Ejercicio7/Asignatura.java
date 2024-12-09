/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ejercicio7;

/**
 *
 * @author ClaudissPerez
 */
public class Asignatura {
    private int id;
    private String nombre;
    private String curso;
    private int horas;

    public Asignatura(int id, String nombre, String curso, int horas) {
        this.id = id;
        this.nombre = nombre;
        this.curso = curso;
        this.horas = horas;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCurso() {
        return curso;
    }

    public int getHoras() {
        return horas;
    }

    @Override
    public String toString() {
        return "Asignatura{" + "id=" + id + ", nombre=" + nombre + ", curso=" + curso + ", horas=" + horas + '}';
    }
    
    
}
