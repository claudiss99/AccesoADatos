/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.orhncompany.ejercicio5_empleados;

import java.sql.Date;

/**
 *
 * @author Juan Antonio
 */
public class Empleado {
    private int id;
    private String nombre;
    private String puesto;
    private double salario;
    private Date fechaIngreso;

    public Empleado(int id, String nombre, String puesto, double salario, Date fechaIngreso) {
        this.id = id;
        this.nombre = nombre;
        this.puesto = puesto;
        this.salario = salario;
        this.fechaIngreso = fechaIngreso;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPuesto() {
        return puesto;
    }

    public double getSalario() {
        return salario;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    @Override
    public String toString() {
        return "Empleado{" + "Id=" + id + ", Nombre=" + nombre + ", Puesto=" + puesto + ", Salario=" + salario + ", Fecha de Ingreso=" + fechaIngreso + '}';
    }
    
    
}
