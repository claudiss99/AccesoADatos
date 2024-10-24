/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejerciciosrepasot1;

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
public class Ejercicio13 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Pedir al usuario el nombre del archivo original
        System.out.println("Introduce el nombre del fichero:");
        String fileName = sc.nextLine();

        File file = new File(fileName);

        if (!file.exists()) {
            System.out.println("El archivo no existe. Por favor, verifica el nombre.");
            System.exit(1);
        }

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String linea;
            //Para saber en que capitulo estamos(suponemos que va en orden¿es así?)
            int capitulo = 0;  
            BufferedWriter writer = null;  

            while ((linea = reader.readLine()) != null) {
                if (linea.startsWith("Capítulo")) {
                    if (writer != null){
                        writer.flush();
                        writer.close();
                    }
                    String newFile = fileName.replace(".txt", "") + "_cap" + capitulo + ".txt";
                    writer = new BufferedWriter(new FileWriter(newFile));
                }
                writer.write(linea);
                writer.newLine();
                
            }
            if (writer != null){
                writer.flush();
                writer.close();
            }
            System.out.println("Se han creado " + capitulo + " archivos de salida.");
        } catch (IOException e) {
            System.out.println("Se produjo un error al leer o escribir archivos.");
        }
    }
}

    

