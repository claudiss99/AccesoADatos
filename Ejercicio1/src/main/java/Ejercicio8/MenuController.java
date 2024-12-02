/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ejercicio8;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Claudiss
 */
public class MenuController {
    private final PedidoDAO pedidoDAO;
    private final ProductoDAO productoDAO;
    private Scanner sc;

    public MenuController(PedidoDAO pedidoDAO, ProductoDAO productoDAO) {
        this.pedidoDAO = pedidoDAO;
        this.productoDAO = productoDAO;
    }

    public void iniciarMenu() {
        int opcion;
        sc = new Scanner(System.in);
        do {
            mostrarOpciones();
            System.out.println("Seleccione una opción: ");
            opcion = sc.nextInt();
            sc.nextLine();
            switch(opcion){
                case 1: 
                    addProduct();
                    break;
                case 2:
                    listarProductos();
                    break;
                case 3:
                    addPedido();
                    break;
                case 4:
                    listarPedidos();
                    break;
                case 5:
                    listarDetallesPedidos();
                    break;
                case 6:
                    System.out.println("Saliendo...");;
                    break;
                default:
                    System.out.println("Opción no válida.");
                    break;
            }
            
        } while (opcion != 6);
    }

    private void mostrarOpciones() {
        System.out.println("\n--- Menú ---");
        System.out.println("1 --> Add Producto");
        System.out.println("2 --> Listar Productos");
        System.out.println("3 --> Add Pedido");
        System.out.println("4 --> Listar Pedidos");
        System.out.println("5 --> Listar detalles de Pedidos");
        System.out.println("6 --> Salir"); 
    }
    
    private void addProduct() {
        System.out.print("Dime el nombre del producto: ");
        String nombre = sc.nextLine();
        
        System.out.print("Dime el stock del producto: ");
        int stock = sc.nextInt();
        
        System.out.print("Dime el precio del producto: ");
        double precio = sc.nextDouble();
        
        Producto producto = new Producto(0, nombre, stock, precio); 
        productoDAO.addProducto(producto);
    }

    private void listarProductos() {
        ArrayList<Producto> productos = productoDAO.listarProductos();
        System.out.println("Productos (ID, nombre, stock, precio):");
        for (Producto producto : productos) {
            System.out.println(producto.getId() + ", " + producto.getNombre() + ", " + producto.getStock() + ", " + producto.getPrecio() + "€");
        }
    }

    private void addPedido() {
        System.out.print("Dime la fecha del pedido (YYYY-MM-DD): ");
        String fecha = sc.nextLine();
        
        System.out.print("Dime el nombre del cliente: ");
        String cliente = sc.nextLine();
        
        ArrayList<Producto> productos = new ArrayList<>();
        boolean continuar = true;
        while (continuar) {
            System.out.print("Dime el id del producto: ");
            int idProducto = sc.nextInt();
            
            System.out.print("Dime la cantidad del producto: ");
            int cantidad = sc.nextInt();
            sc.nextLine();
            
            Producto producto = productoDAO.obtenerProductoPorId(idProducto);
            producto.setStock(cantidad);
            productos.add(producto);
            System.out.println("Quieres agregar otro producto? (Y/N)");
            String respuesta = sc.nextLine();
            if("N".equals(respuesta)){
                continuar = false;
            }
        }
        
        Pedido pedido = new Pedido(0, Date.valueOf(fecha), cliente, 0); 
        pedidoDAO.addPedido(pedido, productos);
    }

    private void listarPedidos() {
        ArrayList<Pedido> pedidos = pedidoDAO.listarPedido();
        System.out.println("Pedidos (ID, fecha, cliente, total):");
        for (Pedido pedido : pedidos) {
            System.out.println(pedido.getId() + ", " + pedido.getFecha() + ", " + pedido.getCliente() + ", " + pedido.getSubTotal()+ "€");
        }
    }

    private void listarDetallesPedidos() {
        ArrayList<Pedido> pedidos = pedidoDAO.listarDetallesPedidos();
        for (Pedido pedido : pedidos) {
            System.out.println("Detalles del pedido " + pedido.getId() + ":");
            System.out.println("Fecha: " + pedido.getFecha());
            System.out.println("Cliente: " + pedido.getCliente());
            System.out.println("Total: " + pedido.getSubTotal()+ "€");
            System.out.println("Productos (nombre, cantidad, subtotal):");
            for (Producto producto : pedido.getListaProductos()) {
                System.out.println(producto.getNombre() + ", " + producto.getStock() + ", " + producto.getPrecio() + "€");
            }
        }
    }
}
