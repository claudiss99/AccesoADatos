/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejerciciosrepasot1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Claudia
 */
public class Ejercicio15 {
    public static void main(String[] args) {
        // Leer el CSV y almacenar los productos en un ArrayList
        ArrayList<String[]> productos = leerCSV("ej_15.csv");

        // Verificamos si el archivo fue leído correctamente
        if (productos.isEmpty()) {
            System.out.println("No se pudo leer el archivo o está vacío.");
            return; // Finalizamos el programa si no se leyeron productos
        }

        // Usamos un Scanner para leer la entrada del usuario
        Scanner scanner = new Scanner(System.in);

        // Pedimos al usuario que elija el criterio de filtro
        System.out.println("Elige el criterio de filtro: ");
        System.out.println("1. Precio mayor que");
        System.out.println("2. Precio menor que");
        System.out.println("3. Stock mayor que");
        System.out.println("4. Stock menor que");

        // Leemos la opción elegida por el usuario
        int opcion = scanner.nextInt();
        
        // Pedimos al usuario que introduzca el valor límite para el filtro
        System.out.print("Introduce el valor límite: ");
        double limite = scanner.nextDouble();

        // Filtramos los productos según el criterio y límite introducido por el usuario
        ArrayList<String[]> productosFiltrados = filtrarProductos(productos, opcion, limite);

        // Mostramos el resultado de la filtración
        if (productosFiltrados.isEmpty()) {
            System.out.println("No se encontraron productos con los criterios indicados.");
        } else {
            System.out.println("Productos filtrados:");
            // Iteramos sobre los productos filtrados y los mostramos
            for (String[] producto : productosFiltrados) {
                System.out.println("Nombre: " + producto[0] + ", Precio: " + producto[1] + ", Stock: " + producto[2]);
            }
        }
    }

    // Método para leer el archivo CSV y retornar una lista de productos
    public static ArrayList<String[]> leerCSV(String archivo) {
        ArrayList<String[]> productos = new ArrayList<>();
        String linea;

        // Utilizamos un BufferedReader para leer el archivo línea por línea
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            // Leer la primera línea (encabezados) y la ignoramos
            br.readLine();

            // Leer cada línea del archivo hasta el final
            while ((linea = br.readLine()) != null) {
                // Dividimos cada línea en partes usando la coma como separador
                String[] datos = linea.split(",");
                // Agregamos el arreglo de datos al ArrayList
                productos.add(datos);
            }

        } catch (IOException e) {
            // Si ocurre un error durante la lectura, lo mostramos
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }

        // Retornamos la lista de productos
        return productos;
    }

    // Método para filtrar productos según el criterio seleccionado
    public static ArrayList<String[]> filtrarProductos(ArrayList<String[]> productos, int opcion, double limite) {
        ArrayList<String[]> productosFiltrados = new ArrayList<>();

        // Iteramos sobre cada producto en la lista
        for (String[] producto : productos) {
            // Convertimos el precio (índice 1) y stock (índice 2) de String a números
            double precio = Double.parseDouble(producto[1].trim());
            int stock = Integer.parseInt(producto[2].trim());

            // Filtramos según la opción elegida por el usuario
            switch (opcion) {
                case 1: // Precio mayor que el valor límite
                    if (precio > limite) {
                        productosFiltrados.add(producto);
                    }
                    break;
                case 2: // Precio menor que el valor límite
                    if (precio < limite) {
                        productosFiltrados.add(producto);
                    }
                    break;
                case 3: // Stock mayor que el valor límite
                    if (stock > limite) {
                        productosFiltrados.add(producto);
                    }
                    break;
                case 4: // Stock menor que el valor límite
                    if (stock < limite) {
                        productosFiltrados.add(producto);
                    }
                    break;
                default:
                    // Si la opción es inválida, mostramos un mensaje y devolvemos la lista vacía
                    System.out.println("Opción no válida.");
                    return productosFiltrados;
            }
        }

        // Retornamos la lista de productos filtrados
        return productosFiltrados;
    
    }
}
