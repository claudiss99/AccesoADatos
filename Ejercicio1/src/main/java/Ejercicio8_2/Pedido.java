/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ejercicio8_2;

import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author Claudiss
 */
public class Pedido {
    private int id;
    private Date fecha;
    private String cliente;
    private double subTotal;
    private ArrayList<Producto> listaProductos;

    public Pedido(int id, Date fecha, String cliente) {
        this.id = id;
        this.fecha = fecha;
        this.cliente = cliente;
    }

    public Pedido(int id, Date fecha, String cliente, double subTotal) {
        this.id = id;
        this.fecha = fecha;
        this.cliente = cliente;
        this.subTotal = subTotal;
    }

    public Pedido(int id, Date fecha, String cliente, double subTotal, ArrayList<Producto> listaProductos) {
        this.id = id;
        this.fecha = fecha;
        this.cliente = cliente;
        this.subTotal = subTotal;
        this.listaProductos = listaProductos;
    }
    
    public int getId() {
        return id;
    }

    public Date getFecha() {
        return fecha;
    }

    public String getCliente() {
        return cliente;
    }
    
    public double getSubTotal() {
        return subTotal;
    }

    public ArrayList<Producto> getListaProductos() {
        return listaProductos;
    }
    
    @Override
    public String toString() {
        return "Pedido{" + "id=" + id + ", fecha=" + fecha + ", cliente=" + cliente + '}';
    }
    
    
}
