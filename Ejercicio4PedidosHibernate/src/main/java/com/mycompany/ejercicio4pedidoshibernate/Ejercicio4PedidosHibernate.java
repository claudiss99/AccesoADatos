/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.ejercicio4pedidoshibernate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author Claudia
 */
public class Ejercicio4PedidosHibernate {

public class GestionPedidos {
    private static final Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        String opc;
        do {
            opc = getMenuOption();
            switch (opc) {
                case "1":
                    addPedido();
                    break;
                case "2":
                    listPedidos();
                    break;
                case "3":
                    searchPedidoByID();
                    break;
                case "4":
                    addProducto();
                    break;
                case "5":
                    listProductos();
                    break;
                case "6":
                    listProductosByStock();
                    break;
            }
        } while (!opc.equals("7"));
    }
    
    private static String getMenuOption() {
        System.out.println("\n Introduce una opción:");
        System.out.println("1. Añadir pedido");
        System.out.println("2. Listar pedidos");
        System.out.println("3. Consultar pedido por ID");
        System.out.println("4. Añadir producto");
        System.out.println("5. Listar productos");
        System.out.println("6. Listar productos por stock");
        System.out.println("7. Salir");
        
        return scanner.nextLine();
    }
    
    private static void addPedido() {
        System.out.println("Ingrese el ID del cliente: ");
        int idCliente = Integer.parseInt(scanner.nextLine());
        
        Pedido pedido = new Pedido(idCliente);
        HashMap<Integer, Integer> cantidades = new HashMap<>();
        
        String opcion;
        do {
            System.out.println("Ingrese el ID del producto: ");
            int idProducto = Integer.parseInt(scanner.nextLine());
            System.out.println("Ingrese la cantidad: ");
            int cantidad = Integer.parseInt(scanner.nextLine());
            
            cantidades.put(idProducto, cantidad);
            
            System.out.println("¿Agregar otro producto? (s/n)");
            opcion = scanner.nextLine();
        } while (opcion.equalsIgnoreCase("s"));
        
        PedidoDAO.addPedido(pedido, cantidades);
    }
    
    private static void listPedidos() {
        ArrayList<Pedido> pedidos = PedidoDAO.listPedido();
        if (pedidos.isEmpty()) {
            System.out.println("No hay pedidos en la base de datos");
        } else {
            for (Pedido p : pedidos) {
                System.out.println(p.toString());
            }
        }
    }
    
    private static void searchPedidoByID() {
        System.out.println("Ingrese el ID del pedido a buscar: ");
        int id = Integer.parseInt(scanner.nextLine());
        Pedido pedido = PedidoDAO.getPedidoById(id);
        
        if (pedido == null) {
            System.out.println("No se ha encontrado el pedido.");
        } else {
            System.out.println("Pedido encontrado: ");
            System.out.println(pedido.toString());
            DetallesPedidoDAO.listDetalles(pedido);
        }
    }
    
    private static void addProducto() {
        System.out.print("Nombre del producto: ");
        String nombre = scanner.nextLine();
        System.out.print("Stock: ");
        int stock = scanner.nextInt();
        System.out.print("Precio: ");
        double precio = scanner.nextDouble();
        scanner.nextLine();

        Producto productoNuevo = new Producto(null, nombre, stock, precio);
        ProductoDAO.addProducto(productoNuevo);
        System.out.println("Producto agregado correctamente");
    }
    
    private static void listProductos() {
        List<Producto> productos = ProductoDAO.listProducto();
        System.out.println("Lista de productos:");
        for (Producto producto : productos) {
            System.out.println("ID: " + producto.getId() + " Nombre: " + producto.getNombre() + " Stock: " + producto.getStock() + " Precio: " + producto.getPrecio());
        }
    }
    
    private static void listProductosByStock() {
        System.out.print("Ingrese el stock: ");
        int stockMaximo = scanner.nextInt();
        scanner.nextLine(); 
        List<Producto> productos = (List<Producto>) ProductoDAO.listProductByStock(stockMaximo);
        System.out.println("Productos con stock inferior a " + stockMaximo + ":");
        for (Producto producto : productos) {
            System.out.println("ID: " + producto.getId() + " Nombre: " + producto.getNombre() + " Stock: " + producto.getStock() + " Precio: " + producto.getPrecio());
        }
    }
}

}
