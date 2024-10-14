/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tema1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author ClaudissPerez
 */
public class Personas {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // pregunte al usuario un rango de edad (min y max)
        System.out.print("Introduce la edad mínima: ");
        int edadMin = sc.nextInt();
        System.out.print("Introduce la edad máxima: ");
        int edadMax = sc.nextInt();

        String fileName = "personas.csv";

        
        int contPeople = 0;
        // Usaremos un fichero temporal donde se irá guardando las personas que cumplen el rango de edad introducido
        try {
            File fTemp = File.createTempFile("personas_filtradas", ".csv");
            try (BufferedReader reader = new BufferedReader(new FileReader(fileName));
                 BufferedWriter writer = new BufferedWriter(new FileWriter(fTemp))) {

                String linea;
                //¿Puede igualarse a cero?
                while ((linea = reader.readLine()) != null) {
                    String[] datos = linea.split(",");
                    String nombre = datos[0];
                    int edad = Integer.parseInt(datos[1]);

                    // Filtrar por rango de edades
                    if (edad >= edadMin && edad <= edadMax) {
                        writer.write(nombre + "," + edad);
                        writer.newLine();
                        contPeople++;
                    }
                }
                writer.flush();
                writer.close();
            }

            // Debe comunicar al usuario cuantas personas se han encontrado dentro del rango
            System.out.println("Se encontraron " + contPeople + " personas en el rango de edad");

            // Preguntar si se quiere mostrar o guardar en un fichero
            System.out.print("¿Deseas mostrar los resultados por pantalla o guardarlos en un archivo? (mostrar/guardar): ");
            String opcion = sc.next();

            if (opcion.equalsIgnoreCase("mostrar")) {
                // Mostrar las personas filtradas
                try (BufferedReader reader = new BufferedReader(new FileReader(fTemp))) {
                    String linea;
                    while ((linea = reader.readLine()) != null) {
                        System.out.println(linea);
                    }
                }
                fTemp.deleteOnExit();
            } else if (opcion.equalsIgnoreCase("guardar")) {
                // Preguntar por el nombre del fichero
                System.out.print("Introduce el nom"
                        + "bre del fichero: ");
                String fileN = sc.next();
                File fSave = new File(fileN + ".csv");
                //¿Quiero que mi fichero temporal ya no sea temporal?
                fTemp.renameTo(fSave);
            }

        } catch (IOException e) {
            System.err.println("Error al procesar el archivo: " + e.getMessage());
        } 
    }
}
