/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejerciciosrepasot1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Usuario
 */
public class Ejercicio5 {
    public static void main(String[] args) {
        File file = new File(args[0]);
        
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String linea = "";
            while ((linea = reader.readLine()) != null){
                System.out.println(reader.readLine());
            }
        } catch (IOException e) {
        }
    }
}
