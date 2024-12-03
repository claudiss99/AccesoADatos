/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ejercicio9;

import java.sql.Date;

/**
 *
 * @author ClaudissPerez
 */
public class Prestamo {
    private int id;
    private int id_libro;
    private String lector;
    private Date fecha_prestamo;
    private String estado;

    public Prestamo(int id, int id_libro, String lector, Date fecha_prestamo, String estado) {
        this.id = id;
        this.id_libro = id_libro;
        this.lector = lector;
        this.fecha_prestamo = fecha_prestamo;
        this.estado = estado;
    }

    public Prestamo(int id_libro, String lector, Date fecha_prestamo) {
        this.id_libro = id_libro;
        this.lector = lector;
        this.fecha_prestamo = fecha_prestamo;
    }
    
    

    public int getId() {
        return id;
    }

    public int getId_libro() {
        return id_libro;
    }

    public String getLector() {
        return lector;
    }

    public Date getFecha_prestamo() {
        return fecha_prestamo;
    }

    public String getEstado() {
        return estado;
    }

    @Override
    public String toString() {
        return "Prestamo{" + "id=" + id + ", id_libro=" + id_libro + ", lector=" + lector + ", fecha_prestamo=" + fecha_prestamo + ", estado=" + estado + '}';
    }
    
    
}
