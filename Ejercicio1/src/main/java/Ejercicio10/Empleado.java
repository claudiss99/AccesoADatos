/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ejercicio10;

import java.sql.Date;

/**
 *
 * @author ClaudissPerez
 */
public class Empleado {
    private int id;
    private String nombre;
    private String dni;
    private String departamento;
    private double sueldo;
    private Date fechaContratacion;
    private Date fechaFinalizacion;

    public Empleado(int id, String nombre, String dni, String departamento, double sueldo, Date fechaContratacion, Date fechaFinalizacion) {
        this.id = id;
        this.nombre = nombre;
        this.dni = dni;
        this.departamento = departamento;
        this.sueldo = sueldo;
        this.fechaContratacion = fechaContratacion;
        this.fechaFinalizacion = fechaFinalizacion;
    }

    public Empleado(int id, String nombre, String dni, String departamento, double sueldo, Date fechaContratacion) {
        this.id = id;
        this.nombre = nombre;
        this.dni = dni;
        this.departamento = departamento;
        this.sueldo = sueldo;
        this.fechaContratacion = fechaContratacion;
    }
    
    
    
     
    public Empleado(String nombre, String dni, String departamento, double sueldo, Date fechaContratacion) {
        this.nombre = nombre;
        this.dni = dni;
        this.departamento = departamento;
        this.sueldo = sueldo;
        this.fechaContratacion = fechaContratacion;
    }

    public Empleado(int id, String nombre, String departamento, double sueldo) {
        this.id= id;
        this.nombre = nombre;
        this.departamento = departamento;
        this.sueldo = sueldo;
    }
    
    public Empleado(int id, Date fechaFinalizacion){
        this.id = id;
        this.fechaFinalizacion = fechaFinalizacion;
    }
    
   
    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDni() {
        return dni;
    }

    public String getDepartamento() {
        return departamento;
    }

    public double getSueldo() {
        return sueldo;
    }

    public Date getFechaContratacion() {
        return fechaContratacion;
    }

    public Date getFechaFinalizacion() {
        return fechaFinalizacion;
    }

    @Override
    public String toString() {
        return "Empleado{" + "id=" + id + ", nombre=" + nombre + ", dni=" + dni + ", departamento=" + departamento + ", sueldo=" + sueldo + ", fechaContratacion=" + fechaContratacion + ", fechaFinalizacion=" + fechaFinalizacion + '}';
    }
    
    
}
