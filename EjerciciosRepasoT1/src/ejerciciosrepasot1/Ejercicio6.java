/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejerciciosrepasot1;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Usuario
 */
public class Ejercicio6 {
    public static void main(String[] args) throws IOException {
        
        Scanner sc = new Scanner(System.in);
        String linea = sc.nextLine();
        ArrayList<String> texto = new ArrayList<>();
        while (linea != null){
            linea = sc.nextLine();
            texto.add(linea);
        }
        
        String fileName = args[0];
        System.out.println("Escribe SI si quieres sobreescribir el fichero y NO en el caso de que quieras agregar el texto al final");
        String overWrite = sc.nextLine();
        if(overWrite.toLowerCase == "si"){
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));
        }else if (overWrite.toLowerCase == "no"){
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        }else{
            System.err.println("No se ha introducido el parametro correcto");
            System.exit(1);
        }
        
        for (String t: texto){
            writer.write(t);
            writer.newLine();
        }
        writer.flush();
        writer.close();
    }
}
