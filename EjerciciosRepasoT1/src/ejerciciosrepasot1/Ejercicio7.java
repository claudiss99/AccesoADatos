/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejerciciosrepasot1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class Ejercicio7 {
    public static void main(String[] args) {
        //File fileName = new File(args[0]);
        File fileName = new File("claudia.txt");
        Scanner sc = new Scanner(System.in);
        String palabra = sc.nextLine();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String linea= "";
            int bingo = 0;
            while((linea = reader.readLine()) != null){
                String[] palabras = linea.split(" ");
                for(String p:palabras){  
                    if (p.equalsIgnoreCase(palabra)){
                        bingo++;
                    }
                }
            }
            reader.close();
            System.out.println("La palabra "+palabra+"aparece"+bingo);
            
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Ejercicio7.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Ejercicio7.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
