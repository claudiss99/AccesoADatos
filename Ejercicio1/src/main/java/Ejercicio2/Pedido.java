/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ejercicio2;

/**
 *
 * @author ClaudissPerez
 */
public class Pedido {
    private int id;
    private int id_cliente;
    private String fecha;
    private int precio_total;

    public Pedido(int id, int id_cliente, String fecha, int precio_total) {
        this.id = id;
        this.id_cliente = id_cliente;
        this.fecha = fecha;
        this.precio_total = precio_total;
    }

    public int getId() {
        return id;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public String getFecha() {
        return fecha;
    }

    public int getPrecio_total() {
        return precio_total;
    }

    @Override
    public String toString() {
        return "Pedido{" + "id=" + id + ", id_cliente=" + id_cliente + ", fecha=" + fecha + ", precio_total=" + precio_total + '}';
    }
    
    
            
}
