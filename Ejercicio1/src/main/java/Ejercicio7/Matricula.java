/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ejercicio7;

/**
 *
 * @author ClaudissPerez
 */
public class Matricula {
    private int id_estudiante;
    private int id_asignatura;
    private int agno;
    private String estado;
    private double calificacion;

    public Matricula(int id_estudiante, int id_asignatura, int agno, String estado, double calificacion) {
        this.id_estudiante = id_estudiante;
        this.id_asignatura = id_asignatura;
        this.agno = agno;
        this.estado = estado;
        this.calificacion = calificacion;
    }
    
    public int getId_estudiante() {
        return id_estudiante;
    }

    public int getId_asignatura() {
        return id_asignatura;
    }

    public int getAgno() {
        return agno;
    }

    public String getEstado() {
        return estado;
    }

    public double getCalificacion() {
        return calificacion;
    }

    @Override
    public String toString() {
        return "Matricula{" + "id_estudiante=" + id_estudiante + ", id_matricula=" + id_asignatura + ", agno=" + agno + ", estado=" + estado + ", calificacion=" + calificacion + '}';
    }
    
    
}
