/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ejercicio10;

/**
 *
 * @author ClaudissPerez
 */
public class Empleado_proyecto {
    private int idEmpleado;
    private int idProyecto;

    public Empleado_proyecto(int idEmpleado, int idProyecto) {
        this.idEmpleado = idEmpleado;
        this.idProyecto = idProyecto;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public int getIdProyecto() {
        return idProyecto;
    }

    @Override
    public String toString() {
        return "Empleado_proyecto{" + "idEmpleado=" + idEmpleado + ", idProyecto=" + idProyecto + '}';
    }
    
    
}
