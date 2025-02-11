/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ejercicio4pedidoshibernate;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Claudia
 */
public class DetallesPedidoDAO {
    public static List<DetallePedido> listDetalles(Pedido pedido){
        List<DetallePedido> detalles = pedido.getDetallePedido();
        return detalles;
        
    }
}
