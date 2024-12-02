package Ejercicio2;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ClaudissPerez
 */
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class GestionCliente {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            ClienteDAO clienteDAO = new ClienteDAO();
            PedidoDAO pedidoDAO = new PedidoDAO();
            boolean salir = false;

            while (!salir) {
                System.out.println("Menú:");
                System.out.println("1- Listar todos los clientes");
                System.out.println("2- Añadir un cliente");
                System.out.println("3- Buscar un cliente por fragmento del email");
                System.out.println("4- Modificar datos de un cliente");
                System.out.println("5- Borrar un cliente");
                System.out.println("6- Ranking Clientes");
                System.out.println("7- Añadir pedido");
                System.out.println("8- Actualizar datos de un pedido");
                System.out.println("9- Borrar un pedido");
                System.out.println("10- Salir ");
                System.out.print("Selecciona una opción: ");

                int opcion = scanner.nextInt();
                scanner.nextLine(); // Consumir el salto de línea

                switch (opcion) {
                    case 1:
                        clienteDAO.listarClientes().forEach(c -> 
                            System.out.println(c.getNombre() + " - " + c.getEmail())
                        );
                        break;
                    case 2:
                        System.out.print("Nombre: ");
                        String nombre = scanner.nextLine();
                        System.out.print("Email: ");
                        String email = scanner.nextLine();
                        System.out.print("Ciudad: ");
                        String ciudad = scanner.nextLine();
                        System.out.print("Teléfono: ");
                        String telefono = scanner.nextLine();
                        System.out.print("Password: ");
                        String password = scanner.nextLine();

                        Cliente nuevoCliente = new Cliente(0, nombre, email, ciudad, telefono, password);

                        if (clienteDAO.agregarCliente(nuevoCliente)) {
                            System.out.println("Cliente añadido correctamente.");
                        } else {
                            System.out.println("El email ya está registrado.");
                        }
                        break;
                    case 3:
                        System.out.print("Introduce un fragmento del email: ");
                        String fragmento = scanner.nextLine();
                        Cliente cliente = clienteDAO.buscarPorEmail(fragmento);
                        if (cliente != null) {
                            System.out.println("Cliente encontrado: " + cliente.getNombre());
                        } else {
                            System.out.println("No se encontró ningún cliente con ese fragmento de email.");
                        }
                        break;
                    case 4:
                        System.out.print("Email del cliente a modificar: ");
                        String emailModificar = scanner.nextLine();
                        System.out.print("Nuevo nombre: ");
                        String nuevoNombre = scanner.nextLine();
                        System.out.print("Nuevo teléfono: ");
                        String nuevoTelefono = scanner.nextLine();
                        System.out.print("Nueva ciudad: ");
                        String nuevaCiudad = scanner.nextLine();

                        if (clienteDAO.modificarCliente(emailModificar, nuevoNombre, nuevoTelefono, nuevaCiudad)) {
                            System.out.println("Cliente modificado correctamente.");
                        } else {
                            System.out.println("No se encontró el cliente con el email especificado.");
                        }
                        break;
                    case 5:
                        System.out.print("Email del cliente a borrar: ");
                        String emailBorrar = scanner.nextLine();

                        if (clienteDAO.borrarCliente(emailBorrar)) {
                            System.out.println("Cliente borrado correctamente.");
                        } else {
                            System.out.println("No se encontró el cliente con el email especificado.");
                        }
                        break;
                    case 6:
                        List<Map<String, Object>> ranking = clienteDAO.obtenerRankingClientes();
                        if (ranking.isEmpty()) {
                            System.out.println("No hay clientes registrados.");
                        } else {
                            System.out.println("Ranking de Clientes (Ordenado por Gasto Total):");
                            for (Map<String, Object> clienteData : ranking) {
                                System.out.println(clienteData.get("nombre") + " - " + clienteData.get("email") + 
                                                   " | Pedidos: " + clienteData.get("num_pedidos") + 
                                                   " | Gasto Total: " + clienteData.get("gasto_total"));
                            }
                        }
                        break;
                    case 7:
                System.out.print("Email del cliente: ");
                String emailCliente = scanner.nextLine();
                Cliente clientePedido = clienteDAO.buscarPorEmail(emailCliente);
                if (clientePedido == null) {
                    System.out.println("Cliente no encontrado. Introduzca los datos para crear uno nuevo.");
                    System.out.print("Nombre: ");
                    String nombreNuevo = scanner.nextLine();
                    System.out.print("Ciudad: ");
                    String ciudadNueva = scanner.nextLine();
                    System.out.print("Teléfono: ");
                    String telefonoNuevo = scanner.nextLine();
                    System.out.print("Password: ");
                    String passwordNuevo = scanner.nextLine();

                    clientePedido = new Cliente(0, nombreNuevo, emailCliente, ciudadNueva, telefonoNuevo, passwordNuevo);
                    clienteDAO.agregarCliente(clientePedido);
                    System.out.println("Cliente creado y asociado al pedido.");
                }

                System.out.print("Fecha del pedido (YYYY-MM-DD): ");
                String fechaPedido = scanner.nextLine();
                System.out.print("Gasto total: ");
                double gastoTotal = scanner.nextDouble();
                scanner.nextLine(); // Consumir el salto de línea

                if (pedidoDAO.agregarPedido(clientePedido.getId(), fechaPedido, gastoTotal)) {
                    System.out.println("Pedido agregado correctamente.");
                } else {
                    System.out.println("Error al agregar el pedido.");
                }
                break;

            case 8:
                System.out.print("ID del pedido a actualizar: ");
                int idPedido = scanner.nextInt();
                scanner.nextLine(); // Consumir el salto de línea

                Pedido pedido = pedidoDAO.buscarPedidoPorId(idPedido);
                if (pedido != null) {
                    System.out.print("Nueva fecha (YYYY-MM-DD): ");
                    String nuevaFecha = scanner.nextLine();
                    System.out.print("Nuevo gasto total: ");
                    double nuevoGasto = scanner.nextDouble();
                    scanner.nextLine(); // Consumir el salto de línea

                    if (pedidoDAO.actualizarPedido(idPedido, nuevaFecha, nuevoGasto)) {
                        System.out.println("Pedido actualizado correctamente.");
                    } else {
                        System.out.println("Error al actualizar el pedido.");
                    }
                } else {
                    System.out.println("No se encontró el pedido con el ID especificado.");
                }
                break;

            case 9:
                System.out.print("ID del pedido a borrar: ");
                int idPedidoBorrar = scanner.nextInt();
                scanner.nextLine(); // Consumir el salto de línea

                if (pedidoDAO.borrarPedido(idPedidoBorrar)) {
                    System.out.println("Pedido borrado correctamente.");
                } else {
                    System.out.println("No se encontró el pedido con el ID especificado.");
                }
                break;

                    default:
                        System.out.println("Opción no válida.");
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

