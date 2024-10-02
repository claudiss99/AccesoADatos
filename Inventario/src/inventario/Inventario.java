/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package inventario;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Claudia
 */
public class Inventario {

    /**
     * @param args the command line arguments
     */
    
    private final String archivo = "inventario.bin";
    //NO USAR OBJECT OUTPUT
    //Guardar productos en el archivo
    public void saveProducts (List<Product> products){
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {
            oos.writeObject(products);
        } catch (IOException e) {
            System.out.println("Error al guardar los productos: " + e.getMessage());
        }
    }
    
     // Método para cargar los productos desde el archivo
    private List<Product> fileProducts() {
        List<Product> products = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            products = (List<Product>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado. Se creará un nuevo inventario.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al cargar los productos: " + e.getMessage());
        }
        return products;
    }
    
     // Agregar un nuevo producto
    public void addProduct(Product newProduct){
        List<Product> products = fileProducts();
        products.add(newProduct);
        saveProducts((products));
        System.out.println("Producto agregado correctamente");
    }


    // Modificar un producto existente por código
    public void modProduct(int codigo) {
        List<Product> products = fileProducts();
        boolean encontrado = false;
        for (Product p : products) {
            if (p.getCodigo() == codigo) {
                encontrado = true;
                // Pedir los nuevos datos
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                try {
                    System.out.print("Ingrese el nuevo nombre: ");
                    p.setNombre(br.readLine());
                    System.out.print("Ingrese el nuevo precio: ");
                    p.setPrecio(Double.parseDouble(br.readLine()));
                    System.out.print("Ingrese el nuevo stock: ");
                    p.setStock(Integer.parseInt(br.readLine()));
                } catch (IOException e) {
                    System.out.println("Error al leer datos: " + e.getMessage());
                }
                saveProducts(products);
                System.out.println("Producto modificado con éxito.");
                break;
            }
        }
        if (!encontrado) {
            System.out.println("Producto no encontrado.");
        }
    }

    // Eliminar un producto por código
    public void elimProduct(int codigo) {
        List<Product> products = (List<Product>) saveProducts(products);
        boolean encontrado = false;
        for (Product p : products) {
            if (p.getCodigo() == codigo) {
                product.remove(p);
                saveProducts(products);
                System.out.println("Producto eliminado con éxito.");
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            System.out.println("Producto no encontrado.");
        }
    }

    // Consultar un producto por código
    public void consultarProducto(int codigo) {
        List<Product> products = fileProducts();
        boolean encontrado = false;
        for (Product p : products) {
            if (p.getCodigo() == codigo) {
                System.out.println(p);
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            System.out.println("Producto no encontrado.");
        }
    }

    // Listar todos los productos
    public void listarProductos() {
        List<Product> products = fileProducts();
        if (products.isEmpty()) {
            System.out.println("No hay productos en el inventario.");
        } else {
            products.forEach(System.out::println);
        }
    }
}

}
