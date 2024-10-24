/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejerciciosrepasot1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ClaudissPerez
 */
public class Ejercicio8 {
    public static void main(String[] args) {
        File fileName = new File(args[0]);
        Scanner sc = new Scanner(System.in);
        String palabra = sc.nextLine();
        String reemplazo = sc.nextLine();
        String texto = "";
        try {
            try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
                String linea= "";
                while((linea = reader.readLine()) != null){
                    linea = linea.replace(palabra, reemplazo);
                    texto += linea;
                }
            }
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))){
                writer.write(texto);
                writer.newLine();
                writer.flush();
                writer.close();
            }
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Ejercicio8.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Ejercicio8.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
